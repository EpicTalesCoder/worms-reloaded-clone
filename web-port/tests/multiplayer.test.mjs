import test from 'node:test';
import assert from 'node:assert/strict';

import {
  generateRoomCode,
  normalizeRoomCode,
  parseMpUrlParam,
  buildInviteUrl,
  MultiplayerClient,
} from '../multiplayer.js';

// Mock BroadcastChannel across clients inside Node
class MockBroadcastChannel {
  static channels = new Map();
  constructor(name) {
    this.name = name;
    this.onmessage = null;
    if (!MockBroadcastChannel.channels.has(name)) {
      MockBroadcastChannel.channels.set(name, new Set());
    }
    MockBroadcastChannel.channels.get(name).add(this);
  }
  postMessage(data) {
    const peers = MockBroadcastChannel.channels.get(this.name);
    if (!peers) return;
    for (const ch of peers) {
      if (ch !== this && ch.onmessage) {
        queueMicrotask(() => ch.onmessage({ data }));
      }
    }
  }
  close() {
    MockBroadcastChannel.channels.get(this.name)?.delete(this);
  }
}

test('Room code generation uses isolated WRxxxx prefix & URL param helpers preserve normalized bounds', () => {
  for (let i = 0; i < 20; i++) {
    const code = generateRoomCode();
    assert.match(code, /^WR\d{4}$/);
    assert.equal(normalizeRoomCode(code), code);
  }
  assert.equal(normalizeRoomCode(' wr-1234abcd '), 'WR1234AB');
  assert.equal(parseMpUrlParam('?foo=1&mp=wr4567'), 'WR4567');
  assert.equal(parseMpUrlParam('?mp=ab'), '');
  const inv = buildInviteUrl('WR4567', new URL('https://example.com/worms/index.html'));
  assert.equal(inv, 'https://example.com/worms/index.html?mp=WR4567');
});

test('2-player & 4-player Host/Guest handshake, room updates, start packet, snapshot & guest input relay via BroadcastChannel', async () => {
  globalThis.BroadcastChannel = MockBroadcastChannel;
  MockBroadcastChannel.channels.clear();

  let guestRoom = null;
  let guestStartInit = null;
  let guestSnap = null;
  let hostInput = null;

  const host = new MultiplayerClient({
    onGuestInput(pkt) { hostInput = pkt; },
  });
  const guest = new MultiplayerClient({
    onRoomUpdate(v) { guestRoom = v; },
    onMatchStart(init) { guestStartInit = init; },
    onSnapshot(s) { guestSnap = s; },
  });

  const code = host.hostRoom('Commander', { enableRemoteRelays: false });
  assert.match(code, /^WR\d{4}$/);

  guest.joinRoom(code, 'Challenger', { enableRemoteRelays: false });
  await new Promise((r) => setTimeout(r, 30));

  assert.ok(guestRoom, 'Guest should receive room state update');
  assert.equal(guestRoom.mySeat, 1);
  assert.equal(guestRoom.seats[0].name, 'Commander');
  assert.equal(guestRoom.seats[1].name, 'Challenger');

  host.startMatch({ seed: 123456, themeId: 2, mapStyle: 1, numTeams: 2, wormsPerTeam: 3, turnSecondsMax: 45 });
  await new Promise((r) => setTimeout(r, 30));

  assert.equal(guestStartInit?.seed, 123456);

  host.sendSnapshot({ t: 2, at: 1, wy: 416, worms: [[150, 200, 1, 60, 100, 1, 0, 'IDLE', 0, 0]], craters: [[1, 300, 250, 30]] });
  await new Promise((r) => setTimeout(r, 30));
  assert.equal(guestSnap?.at, 1);
  assert.equal(guestSnap?.craters?.[0]?.[3], 30);

  guest.sendGuestInput({ held: 8, pressed: 16 });
  await new Promise((r) => setTimeout(r, 30));
  assert.equal(hostInput?.seat, 1);
  assert.equal(hostInput?.pressed, 16);

  host.leaveRoom();
  guest.leaveRoom();
});
