/**
 * Live 2-4 Player Online Skirmish Client & Quad Transport for Worms Reloaded
 * Room Namespace: `WRxxxx` (unique 4-digit suffix, <= 8 chars in normalizeRoomCode)
 * Transports:
 *   1) BroadcastChannel (`wr_mp_v1_<room>`) - <5ms instant local multi-tab play
 *   2) SameOriginHttpRelay (`/api/mp` stateless/persistent backend with MQTT bridge)
 *   3) MqttWssRelay (`wss://broker.emqx.io:8084/mqtt` + `hivemq`)
 * Includes Host Blob Web Worker so AI/turn ticks & snapshots keep running even in backgrounded tabs.
 */

const WSS_BROKERS = [
  'wss://broker.emqx.io:8084/mqtt',
  'wss://broker.hivemq.com:8884/mqtt',
];

export function generateRoomCode() {
  const num = 1000 + Math.floor(Math.random() * 9000);
  return `WR${num}`;
}

export function normalizeRoomCode(raw) {
  return String(raw || '').toUpperCase().replace(/[^A-Z0-9]/g, '').slice(0, 8);
}

export function parseMpUrlParam(searchStr = (typeof window !== 'undefined' ? window.location.search : '')) {
  try {
    const params = new URLSearchParams(searchStr);
    const code = normalizeRoomCode(params.get('mp'));
    return code.length >= 4 ? code : '';
  } catch {
    return '';
  }
}

export function buildInviteUrl(roomCode, loc = (typeof window !== 'undefined' ? window.location : null)) {
  if (!loc) return `?mp=${roomCode}`;
  const url = new URL(loc.href);
  url.searchParams.set('mp', roomCode);
  return url.toString();
}

export function createClientId() {
  return `wr_${Math.random().toString(36).slice(2, 8)}${Date.now().toString(36).slice(-4)}`;
}

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
  const bytes = new TextEncoder().encode(str);
  return [(bytes.length >> 8) & 0xff, bytes.length & 0xff, ...bytes];
}

function mqttConnectPacket(clientId) {
  const vh = [...mqttEncodeStr('MQTT'), 4, 0x02, 0, 45];
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
  const p = Array.from(new TextEncoder().encode(text));
  return new Uint8Array([0x30, ...mqttEncodeRemLen(t.length + p.length), ...t, ...p]);
}

class BroadcastTransport {
  constructor(room, onMsg) {
    this.room = room;
    this.onMsg = onMsg;
    this.ch = typeof BroadcastChannel !== 'undefined' ? new BroadcastChannel(`wr_mp_v1_${room}`) : null;
    if (this.ch) {
      this.ch.onmessage = (e) => {
        if (e.data) this.onMsg(e.data);
      };
    }
  }
  send(dir, packet) {
    if (this.ch) {
      try { this.ch.postMessage({ dir, packet }); } catch {}
    }
  }
  close() {
    if (this.ch) {
      try { this.ch.close(); } catch {}
      this.ch = null;
    }
  }
}

class HttpApiTransport {
  constructor(room, clientId, onMsg) {
    this.room = room;
    this.clientId = clientId;
    this.onMsg = onMsg;
    this.closed = false;
    this.after = 0;
    this.afterSnap = 0;
    this.listenDir = 'h2c';
    this.timer = null;
    this.disabled = false;
  }
  setListenDir(dir) {
    this.listenDir = dir;
  }
  start() {
    if (typeof fetch === 'undefined' || this.closed) return;
    const poll = async () => {
      if (this.closed || this.disabled) return;
      try {
        const q = `room=${encodeURIComponent(this.room)}&dir=${this.listenDir}&after=${this.after}&afterSnap=${this.afterSnap}&from=${encodeURIComponent(this.clientId)}`;
        const res = await fetch(`/api/mp?${q}`, { cache: 'no-store' });
        if (res.status === 404 || res.status === 405) {
          this.disabled = true;
          return;
        }
        if (res.ok) {
          const data = await res.json();
          const list = Array.isArray(data.msgs) ? data.msgs : (Array.isArray(data.packets) ? data.packets : []);
          for (const p of list) {
            if (p.seq && p.seq > this.after) this.after = p.seq;
            this.onMsg({ dir: this.listenDir, packet: p.packet ?? p });
          }
          if (data.snap && data.snapSeq > this.afterSnap) {
            this.afterSnap = data.snapSeq;
            this.onMsg({ dir: 'h2c', packet: data.snap.packet ?? data.snap });
          }
        }
      } catch {}
      if (!this.closed && !this.disabled) {
        this.timer = setTimeout(poll, 220);
      }
    };
    this.timer = setTimeout(poll, 50);
  }
  async send(dir, packet) {
    if (typeof fetch === 'undefined' || this.disabled || this.closed) return;
    try {
      const apiPkt = { ...packet, type: packet.t === 'snap' ? 'snap' : packet.t, _from: this.clientId, _seq: this.outCounter = (this.outCounter || 0) + 1 };
      const res = await fetch('/api/mp', {
        method: 'POST',
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify({ room: this.room, dir, from: this.clientId, packet: apiPkt }),
      });
      if (res.status === 404 || res.status === 405) {
        this.disabled = true;
      }
    } catch {}
  }
  close() {
    this.closed = true;
    if (this.timer) clearTimeout(this.timer);
  }
}

class MqttWssRelay {
  constructor(room, subDir, pubDir, onMsg) {
    this.subTopic = `wr_mp_v1/${room}/${subDir}`;
    this.pubTopic = `wr_mp_v1/${room}/${pubDir}`;
    this.onMsg = onMsg;
    this.closed = false;
    this.decoder = new TextDecoder();
    this.sockets = [];
    if (typeof WebSocket !== 'undefined') {
      for (const url of WSS_BROKERS) this._connectBroker(url);
    }
  }
  _connectBroker(url) {
    if (this.closed) return;
    try {
      const ws = new WebSocket(url, 'mqtt');
      ws.binaryType = 'arraybuffer';
      const entry = { ws, ready: false, ping: null };
      this.sockets.push(entry);
      ws.onopen = () => {
        if (this.closed) return;
        try { ws.send(mqttConnectPacket(`wr_${Math.random().toString(36).slice(2, 9)}`)); } catch {}
      };
      ws.onmessage = (ev) => {
        if (this.closed || !(ev.data instanceof ArrayBuffer)) return;
        const u8 = new Uint8Array(ev.data);
        let offset = 0;
        while (offset + 2 <= u8.length) {
          const header = u8[offset];
          const pktType = header >> 4;
          let mult = 1, rem = 0, idx = offset + 1, ok = false;
          while (idx < u8.length) {
            const b = u8[idx++];
            rem += (b & 127) * mult;
            if ((b & 128) === 0) { ok = true; break; }
            mult *= 128;
          }
          if (!ok || idx + rem > u8.length) break;
          if (pktType === 2) {
            try { ws.send(mqttSubscribePacket(1, this.subTopic)); } catch {}
          } else if (pktType === 9) {
            entry.ready = true;
            entry.ping = setInterval(() => {
              if (ws.readyState === WebSocket.OPEN) try { ws.send(new Uint8Array([0xc0, 0x00])); } catch {}
            }, 20000);
          } else if (pktType === 3) {
            const tLen = (u8[idx] << 8) | u8[idx + 1];
            const qos = (header >> 1) & 3;
            const pStart = idx + 2 + tLen + (qos > 0 ? 2 : 0);
            const pEnd = idx + rem;
            if (pStart <= pEnd) {
              try { this.onMsg(JSON.parse(this.decoder.decode(u8.subarray(pStart, pEnd)))); } catch {}
            }
          }
          offset = idx + rem;
        }
      };
      ws.onerror = () => {};
      ws.onclose = () => {
        entry.ready = false;
        if (entry.ping) clearInterval(entry.ping);
      };
    } catch {}
  }
  send(dir, packet) {
    if (this.closed) return;
    const text = JSON.stringify({ dir, packet });
    for (const s of this.sockets) {
      if (s.ready && s.ws?.readyState === WebSocket.OPEN) {
        try { s.ws.send(mqttPublishPacket(this.pubTopic, text)); } catch {}
      }
    }
  }
  close() {
    this.closed = true;
    for (const s of this.sockets) {
      if (s.ping) clearInterval(s.ping);
      try { s.ws?.close(); } catch {}
    }
    this.sockets = [];
  }
}

export function startHostBlobWorker(onTick, intervalMs = 100) {
  if (typeof Worker === 'undefined' || typeof Blob === 'undefined') {
    const id = setInterval(onTick, intervalMs);
    return () => clearInterval(id);
  }
  try {
    const code = `setInterval(() => postMessage(1), ${Math.max(40, intervalMs | 0)});`;
    const url = URL.createObjectURL(new Blob([code], { type: 'application/javascript' }));
    const worker = new Worker(url);
    worker.onmessage = () => onTick();
    return () => {
      try { worker.terminate(); } catch {}
      try { URL.revokeObjectURL(url); } catch {}
    };
  } catch {
    const id = setInterval(onTick, intervalMs);
    return () => clearInterval(id);
  }
}

export class MultiplayerClient {
  constructor({ onRoomUpdate, onMatchStart, onSnapshot, onGuestInput, onStatus } = {}) {
    this.clientId = createClientId();
    this.roomCode = '';
    this.isHost = false;
    this.playerName = 'Commander';
    this.mySeat = -1;
    this.seats = [null, null, null, null];
    this.fillBots = false;
    this.inMatch = false;

    this.roomConfig = {
      themeId: 0,
      mapStyle: 0,
      wormsPerTeam: 3,
      turnTime: 45,
      crateFreq: 1,
    };

    this.onRoomUpdate = onRoomUpdate || (() => {});
    this.onMatchStart = onMatchStart || (() => {});
    this.onSnapshot = onSnapshot || (() => {});
    this.onGuestInput = onGuestInput || (() => {});
    this.onStatus = onStatus || (() => {});

    this.seenMsgs = new Set();
    this.outSeq = 1;
    this.lobbySeq = 0;
    this.snapSeq = 0;
    this.lastSnapSeq = -1;
    this.bc = null;
    this.http = null;
    this.mqtt = null;
    this.hostWorkerStop = null;
    this.joinRetryTimer = null;
  }

  _teardownTransports() {
    if (this.bc) { this.bc.close(); this.bc = null; }
    if (this.http) { this.http.close(); this.http = null; }
    if (this.mqtt) { this.mqtt.close(); this.mqtt = null; }
    if (this.hostWorkerStop) { this.hostWorkerStop(); this.hostWorkerStop = null; }
    if (this.joinRetryTimer) { clearInterval(this.joinRetryTimer); this.joinRetryTimer = null; }
  }

  leaveRoom() {
    this._teardownTransports();
    this.roomCode = '';
    this.isHost = false;
    this.mySeat = -1;
    this.seats = [null, null, null, null];
    this.inMatch = false;
    this.seenMsgs.clear();
    this.snapSeq = 0;
    this.lastSnapSeq = -1;
    this.onRoomUpdate(this.getRoomView());
  }

  getRoomView() {
    return {
      roomCode: this.roomCode,
      isHost: this.isHost,
      mySeat: this.mySeat,
      seats: this.seats.map((s) => s ? { ...s } : null),
      fillBots: this.fillBots,
      roomConfig: { ...this.roomConfig },
      inMatch: this.inMatch,
    };
  }

  _startTransports(enableRemoteRelays = true) {
    const onIncoming = (msg) => this._handleIncoming(msg);
    const subDir = this.isHost ? 'c2h' : 'h2c';
    const pubDir = this.isHost ? 'h2c' : 'c2h';

    this.bc = new BroadcastTransport(this.roomCode, onIncoming);
    this.http = new HttpApiTransport(this.roomCode, this.clientId, onIncoming);
    this.http.setListenDir(subDir);
    this.http.start();

    if (enableRemoteRelays) {
      // BroadcastChannel + /api/mp (with EMQX/HiveMQ bridge) + direct MQTT-WSS cover local, LAN & WAN without ntfy 429 limits
      this.mqtt = new MqttWssRelay(this.roomCode, subDir, pubDir, onIncoming);
    }
  }

  _emit(dir, payload) {
    const packet = {
      ...payload,
      mid: `${this.clientId}_${this.outSeq++}`,
      from: this.clientId,
    };
    this.bc?.send(dir, packet);
    this.http?.send(dir, packet);
    this.mqtt?.send(dir, packet);
  }

  hostRoom(playerName = 'Host', { enableRemoteRelays = true } = {}) {
    this.leaveRoom();
    this.roomCode = generateRoomCode();
    this.isHost = true;
    this.playerName = String(playerName || 'Host').slice(0, 14);
    this.mySeat = 0;
    this.seats = [
      { id: this.clientId, name: this.playerName, seat: 0, ready: true, isBot: false },
      null, null, null,
    ];
    this._startTransports(enableRemoteRelays);
    this.broadcastRoomState();
    this.onRoomUpdate(this.getRoomView());
    return this.roomCode;
  }

  joinRoom(rawCode, playerName = 'Guest', { enableRemoteRelays = true } = {}) {
    const code = normalizeRoomCode(rawCode);
    if (code.length < 4) return false;
    this.leaveRoom();
    this.roomCode = code;
    this.isHost = false;
    this.playerName = String(playerName || 'Guest').slice(0, 14);
    this._startTransports(enableRemoteRelays);

    const sendJoin = () => {
      this._emit('c2h', { t: 'join', name: this.playerName });
    };
    sendJoin();
    this.joinRetryTimer = setInterval(() => {
      if (this.mySeat >= 0) {
        clearInterval(this.joinRetryTimer);
        this.joinRetryTimer = null;
        return;
      }
      sendJoin();
    }, 650);
    return true;
  }

  toggleReady() {
    if (this.isHost) {
      if (this.seats[0]) this.seats[0].ready = !this.seats[0].ready;
      this.broadcastRoomState();
    } else {
      this._emit('c2h', { t: 'ready' });
    }
  }

  updateRoomConfig(partial) {
    if (!this.isHost) return;
    Object.assign(this.roomConfig, partial);
    this.broadcastRoomState();
  }

  broadcastRoomState() {
    if (!this.isHost) return;
    this.lobbySeq++;
    this._emit('h2c', {
      t: 'room',
      seq: this.lobbySeq,
      seats: this.seats,
      roomConfig: this.roomConfig,
      fillBots: this.fillBots,
      inMatch: this.inMatch,
    });
    this.onRoomUpdate(this.getRoomView());
  }

  startMatch(initPacket) {
    if (!this.isHost) return;
    this.inMatch = true;
    this.broadcastRoomState();
    this._emit('h2c', { t: 'start', init: initPacket });
    this.onMatchStart(initPacket);
  }

  sendSnapshot(snap) {
    if (!this.isHost || !this.inMatch) return;
    this.snapSeq++;
    this._emit('h2c', { t: 'snap', seq: this.snapSeq, snap });
  }

  sendGuestInput(inputPayload) {
    if (this.isHost || this.mySeat < 0) return;
    this._emit('c2h', { t: 'input', seat: this.mySeat, ...inputPayload });
  }

  _handleIncoming(msg) {
    if (!msg || !msg.packet) return;
    const expectedDir = this.isHost ? 'c2h' : 'h2c';
    if (msg.dir && msg.dir !== expectedDir) return;
    const p = msg.packet;
    if (!p.t || p.from === this.clientId) return;

    if (p.mid) {
      if (this.seenMsgs.has(p.mid)) return;
      this.seenMsgs.add(p.mid);
      if (this.seenMsgs.size > 600) {
        const first = this.seenMsgs.values().next().value;
        this.seenMsgs.delete(first);
      }
    }

    if (this.isHost) {
      if (p.t === 'join') {
        let seatIdx = this.seats.findIndex((s) => s && s.id === p.from);
        if (seatIdx === -1) {
          seatIdx = this.seats.findIndex((s) => s === null);
          if (seatIdx !== -1) {
            this.seats[seatIdx] = {
              id: p.from,
              name: String(p.name || `P${seatIdx + 1}`).slice(0, 14),
              seat: seatIdx,
              ready: true,
              isBot: false,
            };
          }
        }
        this.broadcastRoomState();
      } else if (p.t === 'ready') {
        const seat = this.seats.find((s) => s && s.id === p.from);
        if (seat) {
          seat.ready = !seat.ready;
          this.broadcastRoomState();
        }
      } else if (p.t === 'input') {
        this.onGuestInput(p);
      }
    } else {
      if (p.t === 'room') {
        this.seats = Array.isArray(p.seats) ? p.seats : [null, null, null, null];
        if (p.roomConfig) this.roomConfig = p.roomConfig;
        this.fillBots = Boolean(p.fillBots);
        this.inMatch = Boolean(p.inMatch);
        this.mySeat = this.seats.findIndex((s) => s && s.id === this.clientId);
        this.onRoomUpdate(this.getRoomView());
      } else if (p.t === 'start') {
        this.inMatch = true;
        this.onMatchStart(p.init);
      } else if (p.t === 'snap' && p.seq > this.lastSnapSeq) {
        this.lastSnapSeq = p.seq;
        this.onSnapshot(p.snap);
      }
    }
  }
}
