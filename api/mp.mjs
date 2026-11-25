// Same-origin Serverless Multiplayer Relay with EMQX MQTT cross-instance bridge!
// - Works 100% inside Discord Activity through the "/" root mapping (ZERO extra URL Mappings, ZERO CSP issues!)
// - Bridges ALL Vercel serverless instances AND direct MQTT web/mobile clients via wss://broker.emqx.io:8084/mqtt!

const rooms = new Map();
const MAX_ROOM_MSGS = 140;
const ROOM_TTL_MS = 30 * 60 * 1000;
const BROKER_URLS = [
  'wss://broker.emqx.io:8084/mqtt',
  'wss://broker.hivemq.com:8884/mqtt',
];

function mqttEncodeRemLen(len) {
  const out = [];
  do {
    let b = len % 128;
    len = Math.floor(len / 128);
    if (len > 0) b |= 0x80;
    out.push(b);
  } while (len > 0);
  return out;
}

function mqttEncodeStr(str) {
  const bytes = Buffer.from(str, 'utf8');
  return [(bytes.length >> 8) & 0xff, bytes.length & 0xff, ...bytes];
}

function mqttConnectPacket(clientId) {
  const vh = [...mqttEncodeStr('MQTT'), 4, 0x02, 0, 60];
  const pl = mqttEncodeStr(clientId);
  return new Uint8Array([0x10, ...mqttEncodeRemLen(vh.length + pl.length), ...vh, ...pl]);
}

function mqttSubscribePacket(pid, topic) {
  const vh = [(pid >> 8) & 0xff, pid & 0xff];
  const pl = [...mqttEncodeStr(topic), 0];
  return new Uint8Array([0x82, ...mqttEncodeRemLen(vh.length + pl.length), ...vh, ...pl]);
}

function mqttPublishPacket(topic, text) {
  const t = mqttEncodeStr(topic);
  const p = Array.from(Buffer.from(text, 'utf8'));
  return new Uint8Array([0x30, ...mqttEncodeRemLen(t.length + p.length), ...t, ...p]);
}

function cleanupRooms(now) {
  for (const [code, r] of rooms.entries()) {
    if (now - r.updatedAt > ROOM_TTL_MS) rooms.delete(code);
  }
}

function getOrCreateRoom(code, now) {
  let r = rooms.get(code);
  if (!r) {
    r = { seq: 0, snapSeq: 0, msgs: [], snap: null, seen: new Set(), updatedAt: now };
    rooms.set(code, r);
  }
  r.updatedAt = now;
  return r;
}

function storePacket(code, dir, from, packet, now) {
  const r = getOrCreateRoom(code, now);
  if (packet.type === 'snap' && dir === 'h2c') {
    const snapKey = packet._from && packet._seq ? `s:${packet._from}:${packet._seq}` : '';
    if (snapKey && r.seen.has(snapKey)) return { snapSeq: r.snapSeq };
    if (snapKey) {
      r.seen.add(snapKey);
      if (r.seen.size > 400) r.seen.clear();
    }
    r.snapSeq += 1;
    r.snap = { seq: r.snapSeq, from, packet, ts: now };
    return { snapSeq: r.snapSeq };
  }
  const msgKey = packet._from && packet._seq ? `m:${packet._from}:${packet._seq}` : '';
  if (msgKey && r.seen.has(msgKey)) return { seq: r.seq };
  if (msgKey) {
    r.seen.add(msgKey);
    if (r.seen.size > 400) r.seen.clear();
  }
  r.seq += 1;
  r.msgs.push({ seq: r.seq, dir, from, packet, ts: now });
  if (r.msgs.length > MAX_ROOM_MSGS) {
    r.msgs.splice(0, r.msgs.length - MAX_ROOM_MSGS);
  }
  return { seq: r.seq };
}

// Persistent per-instance MQTT bridge to synchronize all Vercel serverless instances + direct MQTT browsers
const bridge = {
  ws: null,
  ready: false,
  connecting: null,
  brokerIdx: 0,
  subbedRooms: new Set(),
  nextPid: 1,
};

function ensureBridgeConnected() {
  if (bridge.ready && bridge.ws && bridge.ws.readyState === WebSocket.OPEN) {
    return Promise.resolve(true);
  }
  if (bridge.connecting) return bridge.connecting;

  bridge.connecting = new Promise((resolve) => {
    const url = BROKER_URLS[bridge.brokerIdx % BROKER_URLS.length];
    let settled = false;
    const finish = (ok) => {
      if (settled) return;
      settled = true;
      bridge.connecting = null;
      resolve(ok);
    };
    const timeout = setTimeout(() => {
      try { bridge.ws?.close(); } catch {}
      bridge.brokerIdx = (bridge.brokerIdx + 1) % BROKER_URLS.length;
      finish(false);
    }, 2200);

    let ws;
    try {
      ws = new WebSocket(url, 'mqtt');
      ws.binaryType = 'arraybuffer';
    } catch {
      clearTimeout(timeout);
      finish(false);
      return;
    }
    bridge.ws = ws;
    bridge.ready = false;
    bridge.subbedRooms.clear();
    const cid = `bltts_srv_${Math.random().toString(36).slice(2, 10)}`;

    ws.onopen = () => {
      try {
        ws.send(mqttConnectPacket(cid));
      } catch {
        clearTimeout(timeout);
        finish(false);
      }
    };

    ws.onmessage = (ev) => {
      const u8 = ev.data instanceof ArrayBuffer ? new Uint8Array(ev.data) : null;
      if (!u8 || u8.length < 2) return;
      let offset = 0;
      while (offset + 2 <= u8.length) {
        const header = u8[offset];
        const pktType = header >> 4;
        let mult = 1;
        let rem = 0;
        let idx = offset + 1;
        let lenDone = false;
        while (idx < u8.length) {
          const b = u8[idx++];
          rem += (b & 127) * mult;
          if ((b & 128) === 0) {
            lenDone = true;
            break;
          }
          mult *= 128;
        }
        if (!lenDone || idx + rem > u8.length) break;

        if (pktType === 2) {
          // CONNACK
          bridge.ready = true;
          clearTimeout(timeout);
          finish(true);
        } else if (pktType === 3) {
          // PUBLISH: topic = bltts/mp/v2/<code>/<dir>
          const tLen = (u8[idx] << 8) | u8[idx + 1];
          const topic = Buffer.from(u8.subarray(idx + 2, idx + 2 + tLen)).toString('utf8');
          const qos = (header >> 1) & 3;
          const pStart = idx + 2 + tLen + (qos > 0 ? 2 : 0);
          const pEnd = idx + rem;
          const match = topic.match(/^bltts\/mp\/v2\/([0-9A-Za-z]+)\/(c2h|h2c)$/);
          if (match && pStart <= pEnd) {
            try {
              const parsed = JSON.parse(Buffer.from(u8.subarray(pStart, pEnd)).toString('utf8'));
              storePacket(match[1], match[2], parsed._from || '', parsed, Date.now());
            } catch {}
          }
        }
        offset = idx + rem;
      }
    };

    ws.onerror = () => {
      clearTimeout(timeout);
      finish(false);
    };

    ws.onclose = () => {
      bridge.ready = false;
      bridge.subbedRooms.clear();
      clearTimeout(timeout);
      finish(false);
    };
  });

  return bridge.connecting;
}

async function ensureRoomSubscribed(code) {
  const ok = await ensureBridgeConnected();
  if (!ok || !bridge.ws || bridge.ws.readyState !== WebSocket.OPEN) return false;
  if (!bridge.subbedRooms.has(code)) {
    bridge.subbedRooms.add(code);
    const pid = (bridge.nextPid = (bridge.nextPid % 60000) + 1);
    try {
      bridge.ws.send(mqttSubscribePacket(pid, `bltts/mp/v2/${code}/+`));
    } catch {}
  }
  return true;
}

async function publishToBridge(code, dir, packet) {
  const ok = await ensureRoomSubscribed(code);
  if (!ok || !bridge.ws || bridge.ws.readyState !== WebSocket.OPEN) return false;
  try {
    bridge.ws.send(mqttPublishPacket(`bltts/mp/v2/${code}/${dir}`, JSON.stringify(packet)));
    return true;
  } catch {
    return false;
  }
}

function readJsonBody(req) {
  return new Promise((resolve) => {
    if (req.body && typeof req.body === 'object') {
      resolve(req.body);
      return;
    }
    if (typeof req.body === 'string') {
      try {
        resolve(JSON.parse(req.body));
      } catch {
        resolve(null);
      }
      return;
    }
    let raw = '';
    req.on('data', (chunk) => {
      raw += chunk;
      if (raw.length > 512 * 1024) raw = raw.slice(0, 512 * 1024);
    });
    req.on('end', () => {
      try {
        resolve(raw ? JSON.parse(raw) : null);
      } catch {
        resolve(null);
      }
    });
    req.on('error', () => resolve(null));
  });
}

export default async function handler(req, res) {
  res.setHeader('Cache-Control', 'no-store, no-cache, must-revalidate');
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type');

  if (req.method === 'OPTIONS') {
    res.statusCode = 204;
    res.end();
    return;
  }

  const now = Date.now();
  if (rooms.size > 60) cleanupRooms(now);

  if (req.method === 'POST') {
    const body = await readJsonBody(req);
    const code = String(body?.room || '').replace(/[^0-9A-Za-z]/g, '').slice(0, 8);
    const dir = body?.dir === 'c2h' ? 'c2h' : 'h2c';
    const from = String(body?.from || '').slice(0, 32);
    const packet = body?.packet;
    if (!code || !packet || typeof packet !== 'object') {
      res.statusCode = 400;
      res.setHeader('Content-Type', 'application/json');
      res.end(JSON.stringify({ ok: false, error: 'invalid_payload' }));
      return;
    }
    const stored = storePacket(code, dir, from, packet, now);
    const bridged = await publishToBridge(code, dir, packet);
    res.statusCode = 200;
    res.setHeader('Content-Type', 'application/json');
    res.end(JSON.stringify({ ok: true, ...stored, bridged }));
    return;
  }

  if (req.method === 'GET') {
    const q = req.query || {};
    const code = String(q.room || '').replace(/[^0-9A-Za-z]/g, '').slice(0, 8);
    const dir = q.dir === 'c2h' ? 'c2h' : 'h2c';
    const after = Math.max(0, parseInt(q.after, 10) || 0);
    const afterSnap = Math.max(0, parseInt(q.afterSnap, 10) || 0);
    const from = String(q.from || '').slice(0, 32);

    if (!code) {
      const bridged = await ensureBridgeConnected();
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.end(JSON.stringify({ ok: true, ping: true, bridged }));
      return;
    }

    await ensureRoomSubscribed(code);
    const r = getOrCreateRoom(code, now);
    const msgs = [];
    for (let i = 0; i < r.msgs.length; i++) {
      const m = r.msgs[i];
      if (m.seq > after && m.dir === dir && m.from !== from) {
        msgs.push(m);
      }
    }
    const snap =
      dir === 'h2c' && r.snap && r.snap.seq > afterSnap && r.snap.from !== from
        ? r.snap
        : null;

    res.statusCode = 200;
    res.setHeader('Content-Type', 'application/json');
    res.end(JSON.stringify({ ok: true, seq: r.seq, snapSeq: r.snapSeq, bridged: bridge.ready, msgs, snap }));
    return;
  }

  res.statusCode = 405;
  res.end('Method Not Allowed');
}
