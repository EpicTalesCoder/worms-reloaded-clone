/**
 * Worms Reloaded Web Entry Point, Input Loop, Phaser/Canvas2D Mount & Live Multiplayer Glue
 * Ports:
 * - SDKMIDlet.java:1-21 & z.java:1-323 (50ms simulation clock, repaint lifecycle)
 * - s.java:86-226 (J2ME keypad mapping + D-Pad + 0-9/Star/Hash/LSK/RSK + Back)
 * - y.java:471-1280 (boot, asset pack mount, persistent state, multiplayer launch)
 */

import { mountPhaserRuntime } from './phaser-engine.js';
import { loadAllEAAssets } from './ea-pack.js';
import { WormsAudio } from './worms-audio.js';
import {
  WormsWorld,
  SCREEN_W, SCREEN_H, MARGIN_X, MARGIN_Y, WATER_Y,
  KEY, TEAM_STYLES, writePersistentSave,
} from './worms-world.js';
import { WormsRenderer } from './render-original.js';
import {
  MultiplayerClient,
  parseMpUrlParam,
  buildInviteUrl,
  startHostBlobWorker,
} from './multiplayer.js';

const KEYMAP = new Map([
  ['ArrowUp', KEY.UP], ['KeyW', KEY.UP],
  ['ArrowDown', KEY.DOWN], ['KeyS', KEY.DOWN],
  ['ArrowLeft', KEY.LEFT], ['KeyA', KEY.LEFT],
  ['ArrowRight', KEY.RIGHT], ['KeyD', KEY.RIGHT],
  ['Enter', KEY.FIRE], ['Space', KEY.FIRE],
  ['KeyQ', KEY.LSK], ['BracketLeft', KEY.LSK],
  ['KeyE', KEY.RSK], ['BracketRight', KEY.RSK],
  ['Escape', KEY.BACK], ['Backspace', KEY.BACK],
  ['Digit0', KEY.NUM0], ['Numpad0', KEY.NUM0],
  ['Digit1', KEY.NUM1], ['Numpad1', KEY.NUM1],
  ['Digit2', KEY.NUM2], ['Numpad2', KEY.NUM2],
  ['Digit3', KEY.NUM3], ['Numpad3', KEY.NUM3], ['KeyJ', KEY.NUM3],
  ['Digit4', KEY.NUM4], ['Numpad4', KEY.NUM4],
  ['Digit5', KEY.NUM5], ['Numpad5', KEY.NUM5],
  ['Digit6', KEY.NUM6], ['Numpad6', KEY.NUM6],
  ['Digit7', KEY.NUM7], ['Numpad7', KEY.NUM7], ['KeyM', KEY.NUM7],
  ['Digit8', KEY.NUM8], ['Numpad8', KEY.NUM8],
  ['Digit9', KEY.NUM9], ['Numpad9', KEY.NUM9], ['KeyB', KEY.NUM9],
  ['Tab', KEY.HASH], ['KeyF', KEY.HASH],
]);

async function boot() {
  const canvas = document.getElementById('game');
  const stageWrap = document.getElementById('stage-wrap');
  const assets = await loadAllEAAssets('assets');
  const audio = new WormsAudio(assets.midiTracks);
  const world = new WormsWorld(assets, audio);
  audio.setMuted(!world.save.soundOn);

  const renderer = new WormsRenderer(assets);
  let lastSnapshotAt = 0;

  // ==================== MULTIPLAYER WIRING ====================
  const mpLobby = document.getElementById('mp-lobby');
  const mpNameInput = document.getElementById('mp-name-input');
  const mpCodeInput = document.getElementById('mp-code-input');
  const mpHomeSection = document.getElementById('mp-home-section');
  const mpRoomSection = document.getElementById('mp-room-section');
  const mpRoomCodeEl = document.getElementById('mp-room-code');
  const mpSeatsEl = document.getElementById('mp-seats');
  const mpStatusEl = document.getElementById('mp-status');
  const mpCfgMap = document.getElementById('mp-cfg-map');
  const mpCfgTheme = document.getElementById('mp-cfg-theme');
  const mpCfgWorms = document.getElementById('mp-cfg-worms');
  const mpCfgTime = document.getElementById('mp-cfg-time');
  const mpStartBtn = document.getElementById('btn-mp-start');

  let hostBlobStop = null;
  let guestHeldKeys = 0;
  let guestPressedKeys = 0;

  const isVI = () => world.assets?.locale?.activeCode === 'vi';

  function refreshLobbyText() {
    const vi = isVI();
    const set = (id, t) => { const el = document.getElementById(id); if (el) el.textContent = t; };
    set('mp-title', vi ? '⚔️ ĐẤU TRƯỜNG ONLINE (2–4)' : '⚔️ ONLINE SKIRMISH (2–4)');
    set('btn-mp-create', vi ? '⚔️ TẠO PHÒNG MỚI' : '⚔️ CREATE ROOM');
    set('btn-mp-join', vi ? 'VÀO PHÒNG' : 'JOIN');
    set('btn-mp-copy', vi ? '📋 COPY LINK MỜI' : '📋 COPY INVITE');
    set('btn-mp-tab2', vi ? '↗ MỞ TAB 2' : '↗ OPEN 2ND TAB');
    set('btn-mp-leave', vi ? 'RỜI PHÒNG' : 'LEAVE');
    set('btn-mp-ready', vi ? '✓ SẴN SÀNG' : '✓ READY');
    set('btn-mp-start', vi ? '🚀 BẮT ĐẦU TRẬN' : '🚀 START MATCH');
    set('btn-lang', vi ? '🌐 VI' : (world.assets?.locale?.activeCode === 'th' ? '🌐 TH' : '🌐 EN'));
    set('btn-sound', world.save.soundOn ? (vi ? '🔊 Âm: BẬT' : '🔊 Sound: ON') : (vi ? '🔇 Âm: TẮT' : '🔇 Sound: OFF'));
  }

  function openLobby() {
    refreshLobbyText();
    mpLobby?.classList.remove('hidden');
  }

  function closeLobby() {
    mpLobby?.classList.add('hidden');
  }

  const mpClient = new MultiplayerClient({
    onRoomUpdate(view) {
      const hasRoom = Boolean(view.roomCode);
      mpHomeSection.style.display = hasRoom ? 'none' : 'flex';
      mpRoomSection.style.display = hasRoom ? 'flex' : 'none';
      if (!hasRoom) return;

      mpRoomCodeEl.textContent = view.roomCode;
      const seated = view.seats.filter(Boolean);
      const vi = isVI();

      mpSeatsEl.innerHTML = view.seats.map((s, idx) => {
        const st = TEAM_STYLES[idx];
        const tag = s ? `${s.name} ${s.ready ? '✓' : '…'}` : (vi ? 'Chỗ trống' : 'Empty');
        const activeCls = idx === view.mySeat ? ' active' : '';
        return `<div class="mp-seat${activeCls}" style="border-left: 4px solid ${st.hex}"><span>#${idx + 1} ${tag}</span><span style="color:${st.hex}">●</span></div>`;
      }).join('');

      mpCfgMap.disabled = !view.isHost;
      mpCfgTheme.disabled = !view.isHost;
      mpCfgWorms.disabled = !view.isHost;
      mpCfgTime.disabled = !view.isHost;
      mpCfgMap.value = String(view.roomConfig.mapStyle);
      mpCfgTheme.value = String(view.roomConfig.themeId);
      mpCfgWorms.value = String(view.roomConfig.wormsPerTeam);
      mpCfgTime.value = String(view.roomConfig.turnTime);

      mpStartBtn.style.display = view.isHost ? 'inline-flex' : 'none';
      mpStartBtn.disabled = seated.length < 2;
      mpStatusEl.textContent = view.isHost
        ? (seated.length >= 2 ? (vi ? `Đã sẵn sàng (${seated.length} đội)! Nhấn Bắt đầu.` : `Ready (${seated.length} players)! Press Start.`) : (vi ? 'Đang chờ người chơi khác tham gia...' : 'Waiting for opponent to join...'))
        : (vi ? `Đã vào phòng ${view.roomCode} — Đợi chủ phòng bắt đầu...` : `Joined ${view.roomCode} — Waiting for Host...`);
    },

    onMatchStart(init) {
      closeLobby();
      const view = mpClient.getRoomView();
      const activeSeats = view.seats.filter(Boolean);
      const numTeams = Math.max(2, activeSeats.length);

      world.mp.enabled = true;
      world.mp.isHost = view.isHost;
      world.mp.localTeamMask = 1 << Math.max(0, view.mySeat);
      world.mp.peerNames = view.seats.map((s, idx) => s?.name || `TEAM ${idx + 1}`);
      world.mode = 'multiplayer';

      world.startMatch({
        seed: init.seed,
        themeId: init.themeId,
        mapStyle: init.mapStyle,
        numTeams: init.numTeams || numTeams,
        wormsPerTeam: init.wormsPerTeam,
        turnTime: init.turnSecondsMax,
        crateFreq: init.crateFreq,
        suddenDeath: init.suddenDeathTurns,
        aiMask: 0, // Live human multiplayer!
      });

      if (view.isHost) {
        if (hostBlobStop) hostBlobStop();
        hostBlobStop = startHostBlobWorker(() => {
          if (world.mp.enabled && world.mp.isHost && world.o === 6) {
            mpClient.sendSnapshot(world.serializeSnapshot());
          }
        }, 100);
      }
    },

    onSnapshot(snap) {
      if (!world.mp.enabled || world.mp.isHost) return;
      world.applySnapshot(snap);
    },

    onGuestInput(pkt) {
      if (!world.mp.enabled || !world.mp.isHost) return;
      if (pkt.seat !== world.activeTeamIdx) return;
      if (pkt.pressed) guestPressedKeys |= pkt.pressed;
      if (pkt.held !== undefined) guestHeldKeys = pkt.held;
      if (pkt.selectWeapon !== undefined) {
        const aw = world.getActiveWorm();
        const team = world.teams[pkt.seat];
        if (aw && team && team.ammo[pkt.selectWeapon] !== 0) {
          aw.weaponId = pkt.selectWeapon;
          world.selectedWeapon = pkt.selectWeapon;
          world.phase = 'PLAY';
        }
      }
      if (pkt.targetX !== undefined && pkt.targetY !== undefined) {
        world.targetCursor.x = pkt.targetX;
        world.targetCursor.y = pkt.targetY;
      }
    },
  });

  world.mp.openLobbyFn = openLobby;

  // ==================== BUTTON & MODAL LISTENERS ====================
  document.getElementById('btn-mp-lobby')?.addEventListener('click', openLobby);
  document.getElementById('btn-mp-close')?.addEventListener('click', closeLobby);

  document.getElementById('btn-mp-create')?.addEventListener('click', () => {
    const name = mpNameInput?.value?.trim() || 'Commander';
    mpClient.hostRoom(name);
  });

  document.getElementById('btn-mp-join')?.addEventListener('click', () => {
    const code = mpCodeInput?.value?.trim();
    const name = mpNameInput?.value?.trim() || 'Guest';
    if (code) mpClient.joinRoom(code, name);
  });

  document.getElementById('btn-mp-copy')?.addEventListener('click', () => {
    if (!mpClient.roomCode) return;
    const url = buildInviteUrl(mpClient.roomCode);
    navigator.clipboard?.writeText(url).catch(() => {});
    mpStatusEl.textContent = isVI() ? `Đã copy link mời phòng ${mpClient.roomCode}!` : `Copied invite link for ${mpClient.roomCode}!`;
  });

  document.getElementById('btn-mp-tab2')?.addEventListener('click', () => {
    if (!mpClient.roomCode) return;
    window.open(buildInviteUrl(mpClient.roomCode), '_blank');
  });

  document.getElementById('btn-mp-leave')?.addEventListener('click', () => {
    if (hostBlobStop) { hostBlobStop(); hostBlobStop = null; }
    mpClient.leaveRoom();
    world.mp.enabled = false;
  });

  document.getElementById('btn-mp-ready')?.addEventListener('click', () => mpClient.toggleReady());

  for (const [el, field] of [[mpCfgMap, 'mapStyle'], [mpCfgTheme, 'themeId'], [mpCfgWorms, 'wormsPerTeam'], [mpCfgTime, 'turnTime']]) {
    el?.addEventListener('change', () => {
      mpClient.updateRoomConfig({ [field]: Number(el.value) });
    });
  }

  mpStartBtn?.addEventListener('click', () => {
    const view = mpClient.getRoomView();
    const cfg = view.roomConfig;
    const numTeams = Math.max(2, view.seats.filter(Boolean).length);
    const seed = (Date.now() & 0x7fffffff) || 44444;
    mpClient.startMatch({
      seed,
      themeId: cfg.themeId,
      mapStyle: cfg.mapStyle,
      numTeams,
      wormsPerTeam: cfg.wormsPerTeam,
      turnSecondsMax: cfg.turnTime,
      crateFreq: cfg.crateFreq,
      suddenDeathTurns: 15,
    });
  });

  // Toolbar controls
  document.getElementById('btn-lang')?.addEventListener('click', () => {
    const next = ((assets.locale.getLanguageIndex()) + 1) % 3;
    assets.locale.setLanguage(next);
    world.save.langIdx = next;
    writePersistentSave(world.save);
    refreshLobbyText();
  });

  document.getElementById('btn-sound')?.addEventListener('click', () => {
    world.save.soundOn = !world.save.soundOn;
    audio.setMuted(!world.save.soundOn);
    writePersistentSave(world.save);
    refreshLobbyText();
  });

  document.getElementById('btn-keypad')?.addEventListener('click', () => {
    document.getElementById('keypad-drawer')?.classList.toggle('hidden');
  });

  document.getElementById('btn-fullscreen')?.addEventListener('click', () => {
    if (!document.fullscreenElement) stageWrap?.requestFullscreen?.().catch(() => {});
    else document.exitFullscreen?.().catch(() => {});
  });

  // Auto-join from ?mp=<code> URL parameter
  const initialMp = parseMpUrlParam();
  if (initialMp) {
    openLobby();
    mpCodeInput.value = initialMp;
    mpClient.joinRoom(initialMp, `Player_${Math.floor(Math.random() * 90 + 10)}`);
  }

  // ==================== KEYBOARD & VIRTUAL KEYPAD ====================
  let localHeld = 0;
  let localPressed = 0;

  function triggerKeyDown(mask) {
    if (!mask) return;
    audio.ensureCtx();
    localHeld |= mask;
    localPressed |= mask;
    if (world.mp.enabled && !world.mp.isHost) {
      mpClient.sendGuestInput({ held: localHeld, pressed: mask });
    }
  }

  function triggerKeyUp(mask) {
    if (!mask) return;
    localHeld &= ~mask;
    if (world.mp.enabled && !world.mp.isHost) {
      mpClient.sendGuestInput({ held: localHeld });
    }
  }

  window.addEventListener('keydown', (e) => {
    if (e.target && /^(INPUT|TEXTAREA|SELECT)$/i.test(e.target.tagName)) return;
    const mask = KEYMAP.get(e.code);
    if (mask) {
      e.preventDefault();
      if (!e.repeat) triggerKeyDown(mask);
      else localHeld |= mask;
    }
  });

  window.addEventListener('keyup', (e) => {
    if (e.target && /^(INPUT|TEXTAREA|SELECT)$/i.test(e.target.tagName)) return;
    const mask = KEYMAP.get(e.code);
    if (mask) triggerKeyUp(mask);
  });

  document.querySelectorAll('[data-key]').forEach((btn) => {
    const mask = Number(btn.getAttribute('data-key'));
    const down = (e) => { e.preventDefault(); triggerKeyDown(mask); };
    const up = (e) => { e.preventDefault(); triggerKeyUp(mask); };
    btn.addEventListener('pointerdown', down);
    btn.addEventListener('pointerup', up);
    btn.addEventListener('pointerleave', up);
  });

  // Canvas pointer click ergonomics (Menus, Softkeys, Backpack, Target)
  canvas.addEventListener('pointerdown', (e) => {
    audio.ensureCtx();
    const rect = canvas.getBoundingClientRect();
    const sx = ((e.clientX - rect.left) / rect.width) * SCREEN_W;
    const sy = ((e.clientY - rect.top) / rect.height) * SCREEN_H;

    // Softkey hit boxes bottom-left & bottom-right
    if (sy >= SCREEN_H - 26) {
      if (sx <= 44) { triggerKeyDown(KEY.LSK); triggerKeyUp(KEY.LSK); return; }
      if (sx >= SCREEN_W - 44) { triggerKeyDown(KEY.RSK); triggerKeyUp(KEY.RSK); return; }
    }

    if (world.o === 1 || world.o === 2 || world.o === 17) {
      triggerKeyDown(KEY.FIRE); triggerKeyUp(KEY.FIRE);
      return;
    }

    if (world.o === 6 && world.phase === 'BACKPACK') {
      const col = Math.floor((sx - 7) / 31);
      const row = Math.floor((sy - 15) / 31);
      if (col >= 0 && col < 9 && row >= 0 && row < 4) {
        const wId = row * 9 + col;
        world.backpackCursor = wId;
        if (world.mp.enabled && !world.mp.isHost) {
          mpClient.sendGuestInput({ selectWeapon: wId });
        } else {
          triggerKeyDown(KEY.FIRE); triggerKeyUp(KEY.FIRE);
        }
      }
      return;
    }

    if (world.o === 6 && world.phase === 'TARGET') {
      const wx = Math.max(MARGIN_X + 10, Math.min(MARGIN_X + 630, Math.round(world.smoothCamX + sx)));
      const wy = Math.max(MARGIN_Y + 10, Math.min(WATER_Y - 12, Math.round(world.smoothCamY + sy)));
      world.targetCursor.x = wx;
      world.targetCursor.y = wy;
      if (world.mp.enabled && !world.mp.isHost) {
        mpClient.sendGuestInput({ targetX: wx, targetY: wy, pressed: KEY.FIRE });
      } else {
        triggerKeyDown(KEY.FIRE); triggerKeyUp(KEY.FIRE);
      }
      return;
    }

    if (world.o === 4 || world.o === 7 || world.o === 9) {
      if (world.o === 9) {
        triggerKeyDown(KEY.FIRE); triggerKeyUp(KEY.FIRE);
        return;
      }
      if (world.o === 4 && world.cm === 16) {
        const col = Math.floor((sx - 36) / 36);
        const row = Math.floor((sy - 70) / 28);
        if (col >= 0 && col < 7 && row >= 0 && row < 4) {
          world.cn = row * 7 + col;
          triggerKeyDown(KEY.FIRE); triggerKeyUp(KEY.FIRE);
        }
        return;
      }
      // Click menu pill
      const items = world.getMenuItems();
      const maxVisible = 5;
      const itemStart = Math.max(0, Math.min(Math.max(0, items.length - maxVisible), world.cn - 2));
      const topY = world.cm === 29 ? 98 : 84;
      const stepY = world.cm === 29 ? 23 : 25;
      for (let v = 0; v < maxVisible && itemStart + v < items.length; v++) {
        const y = topY + v * stepY;
        if (sy >= y - 12 && sy <= y + 12) {
          world.cn = itemStart + v;
          triggerKeyDown(KEY.FIRE); triggerKeyUp(KEY.FIRE);
          return;
        }
      }
    }
  });

  // ==================== MOUNT 50ms SIMULATION ENGINE ====================
  const runtime = mountPhaserRuntime({
    canvas,
    width: SCREEN_W,
    height: SCREEN_H,
    tickMs: 50, // Port of z.java:251-263 (ae.a = 50ms)
    onStepSimulation() {
      const isGuestControlling =
        world.mp.enabled &&
        world.mp.isHost &&
        world.activeTeamIdx !== 0 &&
        world.teams[world.activeTeamIdx] &&
        !world.teams[world.activeTeamIdx].isAI;

      if (isGuestControlling) {
        world.heldKeys = guestHeldKeys;
        world.pressedKeys = guestPressedKeys;
        guestPressedKeys = 0;
      } else {
        world.heldKeys = localHeld;
        world.pressedKeys = localPressed;
      }
      localPressed = 0;

      world.stepTick(50);

      if (world.mp.enabled && world.mp.isHost && world.o === 6) {
        const now = performance.now();
        if (now - lastSnapshotAt >= 90) {
          lastSnapshotAt = now;
          mpClient.sendSnapshot(world.serializeSnapshot());
        }
      }
    },
    onFrameInterpolate() {
      return true;
    },
    onRenderFrame(ctx) {
      renderer.renderFrame(ctx, world);
    },
  });

  // Expose live state & QA harness hook
  refreshLobbyText();
  window.__WR_APP__ = {
    world,
    renderer,
    assets,
    audio,
    mpClient,
    runtime,
    openLobby,
    closeLobby,
    setLanguage(code) {
      assets.locale.setLanguage(code);
      world.save.langIdx = assets.locale.getLanguageIndex();
      writePersistentSave(world.save);
      refreshLobbyText();
    },
    pressKey(mask) {
      triggerKeyDown(mask);
      setTimeout(() => triggerKeyUp(mask), 60);
    },
  };
}

boot().catch((err) => {
  console.error('Worms Reloaded boot failed:', err);
});
