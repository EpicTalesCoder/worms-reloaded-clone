/**
 * Worms Reloaded J2ME Core World, Terrain, Physics, 36 Weapons & AI Engine
 * Ports:
 * - a.java:1810-2750 (832x448 world, 640x320 bitmask, B-spline terrain, crater carving, tile cache & minimap)
 * - ah.java:140-8546 (entities, 16.16 physics, collision, 36 weapons, projectile flight, craters, AI solver)
 * - y.java:255-397,1236-1262,4150-4550,7052-7542,7787-8000 (weapon table, 28 campaign stages, match flow, saves)
 * - j.java:1-121 (constants, team weapon schemes & campaign trigger tables)
 */

import { FP_ONE, fpMul, fpDiv, s32, idiv } from './midp-graphics.js';

export const SCREEN_W = 320;
export const SCREEN_H = 240;

// Port of a.java:1-60 world geometry constants
export const WORLD_W = 832;
export const WORLD_H = 448;
export const MARGIN_X = 96;  // a.G
export const MARGIN_Y = 96;  // a.H
export const WATER_MARGIN = 32; // a.I
export const WATER_Y = WORLD_H - WATER_MARGIN; // a.b = 416
export const MASK_W = 640; // WORLD_W - 2 * MARGIN_X
export const MASK_H = 320; // WORLD_H - MARGIN_Y - WATER_MARGIN
export const TILE_SIZE = 32;
export const TILES_X = WORLD_W / TILE_SIZE; // 26
export const TILES_Y = WORLD_H / TILE_SIZE; // 14
export const MINIMAP_W = 170;
export const MINIMAP_H = 64;

// Port of s.java:86-226 keypad bitmasks & combined navigation masks from y.java
export const KEY = {
  UP: 1,
  DOWN: 2,
  LEFT: 4,
  RIGHT: 8,
  FIRE: 16,
  LSK: 32,
  RSK: 64,
  NUM0: 128,
  NUM1: 256,
  NUM2: 512,
  NUM3: 1024,
  NUM4: 2048,
  NUM5: 4096,
  NUM6: 8192,
  NUM7: 16384,
  NUM8: 32768,
  NUM9: 65536,
  STAR: 131072,
  HASH: 262144,
  BACK: 8388608,
};

export const MASK_UP = KEY.UP | KEY.NUM2;         // 513
export const MASK_DOWN = KEY.DOWN | KEY.NUM8;     // 32770
export const MASK_LEFT = KEY.LEFT | KEY.NUM4;     // 2052
export const MASK_RIGHT = KEY.RIGHT | KEY.NUM6;   // 8200
export const MASK_FIRE = KEY.FIRE | KEY.NUM5;     // 4112

// Port of y.java:255-292: 36 Weapons & Utilities Table
// [isMeleeOrSingle, maxDmgOrFuse, blastRadius, dmgRadius, gravityScale, windScale, bounces]
export const WEAPON_STATS = [
  [1, 45, 40, 60, 65536, 65536, 0],  // 0: Bazooka
  [1, 45, 45, 67, 65536, 0, 1],      // 1: Grenade
  [30, 30, 10, 20, 0, 0, 0],         // 2: Jetpack
  [1, 45, 40, 60, 65536, 65536, 1],  // 3: Fire Punch
  [1, 30, 40, 60, 0, 0, 0],          // 4: Mine
  [1, 45, 40, 60, 32768, 65536, 0],  // 5: Air Strike
  [5, 16, 30, 45, 65536, 0, 1],      // 6: Homing Missile
  [1, 16, 30, 45, 65536, 0, 0],      // 7: Cluster Bomb
  [30, 30, 10, 20, 0, 0, 0],         // 8: Parachute
  [1, 65, 60, 90, 65536, 0, 1],      // 9: Marked for Death
  [25, 25, 20, 20, 0, 0, 0],         // 10: Sheep
  [15, 25, 45, 67, 65536, 0, 1],     // 11: Napalm Strike
  [5, 5, 0, 20, 0, 0, 0],            // 12: Shotgun
  [30, 30, 6, 24, 16384, 0, 0],      // 13: Petrol Bomb
  [30, 30, 6, 24, 0, 0, 0],          // 14: Teleport
  [1, 75, 70, 105, 65536, 0, 1],     // 15: Battle Axe
  [30, 30, 0, 20, 0, 0, 0],          // 16: Super Sheep
  [20, 30, 60, 90, 65536, 0, 1],     // 17: Bunker Buster
  [1, 30, 60, 90, 65536, 0, 0],      // 18: Worship
  [1, 1, 0, 0, 0, 0, 0],             // 19: Holy Hand Grenade
  [10, 10, 10, 10, 0, 0, 0],         // 20: Pneumatic Drill
  [50, 80, 6, 60, 32768, 0, 0],      // 21: Baseball Bat
  [5, 50, 60, 90, 65536, 0, 0],      // 22: Buffalo of Lies
  [8, 8, 10, 10, 0, 0, 0],           // 23: Gas Pump
  [1, 65, 60, 90, 0, 0, 1],          // 24: Lightning Strike
  [5, 5, 20, 20, 0, 0, 0],           // 25: Banana Bomb
  [40, 40, 0, 0, 0, 0, 0],           // 26: Blow Torch
  [40, 40, 0, 0, 0, 0, 0],           // 27: Dragon Ball
  [1, 50, 40, 60, 65536, 65536, 0],  // 28: Concrete Donkey
  [25, 25, 20, 30, 0, 0, 0],         // 29: Armageddon
  [25, 25, 40, 40, 0, 0, 0],         // 30: Uzi
  [10, 10, 10, 10, 65536, 0, 0],     // 31: Dynamite
  [30, 90, 10, 10, 65536, 0, 0],     // 32: Kamikaze
  [5, 50, 40, 60, 65536, 65536, 0],  // 33: Emergency Teleport
  [10, 50, 45, 67, 0, 0, 0],         // 34: Skip Go
  [8, 8, 10, 10, 0, 0, 0],           // 35: Surrender
];

// Default full tactical arsenal (Quick/Custom/Multiplayer Match) matching standard Worms Reloaded scheme
export function createStandardAmmo() {
  const ammo = new Int16Array(36);
  // Infinite (-1) core weapons & utilities + limited tactical & super weapons
  ammo[0] = -1; // Bazooka
  ammo[1] = -1; // Grenade
  ammo[2] = 2;  // Jetpack
  ammo[3] = -1; // Fire Punch
  ammo[4] = 2;  // Mine
  ammo[5] = 1;  // Air Strike
  ammo[6] = 2;  // Homing Missile
  ammo[7] = 3;  // Cluster Bomb
  ammo[8] = 3;  // Parachute
  ammo[9] = 1;  // Marked for Death
  ammo[10] = 1; // Sheep
  ammo[11] = 1; // Napalm Strike
  ammo[12] = -1; // Shotgun
  ammo[13] = 2; // Petrol Bomb
  ammo[14] = 2; // Teleport
  ammo[15] = 1; // Battle Axe
  ammo[16] = 1; // Super Sheep
  ammo[17] = 1; // Bunker Buster
  ammo[18] = 1; // Worship
  ammo[19] = 1; // Holy Hand Grenade
  ammo[20] = 2; // Pneumatic Drill
  ammo[21] = 2; // Baseball Bat
  ammo[22] = 0; // Buffalo of Lies (crate/shop unlock!)
  ammo[23] = 1; // Gas Pump
  ammo[24] = 1; // Lightning Strike
  ammo[25] = 1; // Banana Bomb
  ammo[26] = 2; // Blow Torch
  ammo[27] = 1; // Dragon Ball
  ammo[28] = 0; // Concrete Donkey (crate/shop)
  ammo[29] = 0; // Armageddon (crate/shop)
  ammo[30] = -1; // Uzi
  ammo[31] = 1; // Dynamite
  ammo[32] = -1; // Kamikaze
  ammo[33] = 2; // Emergency Teleport
  ammo[34] = -1; // Skip Go
  ammo[35] = -1; // Surrender
  return ammo;
}

// Shop unlockable items matching y.java:344-352
export const SHOP_PACKS = [
  { id: 0, tier: 0, weaponId: 7, cost: 70, qty: 2 },   // Cluster Bomb
  { id: 1, tier: 0, weaponId: 6, cost: 140, qty: 2 },  // Homing Missile
  { id: 2, tier: 0, weaponId: 13, cost: 140, qty: 2 }, // Petrol Bomb
  { id: 3, tier: 0, weaponId: 10, cost: 210, qty: 1 }, // Sheep
  { id: 4, tier: 0, weaponId: 2, cost: 170, qty: 2 },  // Jetpack
  { id: 5, tier: 0, weaponId: 11, cost: 280, qty: 1 }, // Napalm Strike
  { id: 6, tier: 0, weaponId: 25, cost: 280, qty: 1 }, // Banana Bomb
  { id: 7, tier: 1, weaponId: 19, cost: 350, qty: 1 }, // Holy Hand Grenade
  { id: 8, tier: 1, weaponId: 16, cost: 350, qty: 1 }, // Super Sheep
  { id: 9, tier: 1, weaponId: 17, cost: 280, qty: 1 }, // Bunker Buster
  { id: 10, tier: 2, weaponId: 28, cost: 560, qty: 1 }, // Concrete Donkey
  { id: 11, tier: 2, weaponId: 22, cost: 490, qty: 1 }, // Buffalo of Lies
  { id: 12, tier: 3, weaponId: 29, cost: 630, qty: 1 }, // Armageddon
];

export const SHOP_TIER_COSTS = [0, 300, 600, 900]; // y.java:348

// Port of y.java:5368 / theme visuals (`by = 21..25`, `bz = 26..30`)
export const THEME_CONFIGS = [
  { id: 0, nameId: 105, skyHex: '#11115c', skyTopHex: '#1d4d8f', crustTop: '#56c23b', crustSub: '#358822', tilePack: 21, bgPack: 26 },
  { id: 1, nameId: 106, skyHex: '#140000', skyTopHex: '#3f1111', crustTop: '#e08928', crustSub: '#a84b14', tilePack: 22, bgPack: 27 },
  { id: 2, nameId: 107, skyHex: '#2e162e', skyTopHex: '#d779a5', crustTop: '#fff48f', crustSub: '#d9ad26', tilePack: 23, bgPack: 28 },
  { id: 3, nameId: 108, skyHex: '#101918', skyTopHex: '#254a4d', crustTop: '#d7ecfb', crustSub: '#84b7d9', tilePack: 24, bgPack: 29 },
  { id: 4, nameId: 109, skyHex: '#29133d', skyTopHex: '#592978', crustTop: '#73df64', crustSub: '#3a913a', tilePack: 25, bgPack: 30 },
];

// Team colors & hats matching original team customization (Red, Blue, Green, Yellow)
export const TEAM_STYLES = [
  { id: 0, name: 'TEAM 1', hex: '#ee3322', dark: '#78100a', barIdx: 0, hat: 0 },
  { id: 1, name: 'TEAM 2', hex: '#2898ff', dark: '#0d3f75', barIdx: 1, hat: 1 },
  { id: 2, name: 'TEAM 3', hex: '#38d256', dark: '#115822', barIdx: 2, hat: 2 },
  { id: 3, name: 'TEAM 4', hex: '#f4c628', dark: '#6d5409', barIdx: 3, hat: 3 },
];

export const ACHIEVEMENTS_COUNT = 23; // y.dw bitmask

export const DEFAULT_SAVE = {
  version: 1,
  langIdx: -1, // -1 shows Language Select on very first launch, or auto-selected
  soundOn: true,
  vibroOn: true,
  timerPause: false,
  wormSelect: false,
  coins: 150,
  campaignUnlockedBitmask: 1, // bit 0 = mission 1 unlocked
  campaignStars: {},
  shopTierUnlocked: 1,
  shopStock: Array(36).fill(0),
  achievementsMask: 0,
  totalKills: 0,
  totalCrates: 0,
  totalMatches: 0,
  totalWins: 0,
};

export function loadPersistentSave() {
  try {
    const raw = typeof localStorage !== 'undefined' && localStorage.getItem('worms_reloaded_save_v1');
    if (raw) {
      const parsed = JSON.parse(raw);
      return { ...DEFAULT_SAVE, ...parsed };
    }
  } catch {}
  return { ...DEFAULT_SAVE };
}

export function writePersistentSave(save) {
  try {
    if (typeof localStorage !== 'undefined') {
      localStorage.setItem('worms_reloaded_save_v1', JSON.stringify(save));
    }
  } catch {}
}

/**
 * Trig helpers matching y.java:M[0..90] cosine LUT (y.java:4855-4900).
 */
export function cosDegFP(trigCos, deg) {
  let d = ((deg | 0) % 360 + 360) % 360;
  if (d <= 90) return trigCos[d];
  if (d <= 180) return -trigCos[180 - d];
  if (d <= 270) return -trigCos[d - 180];
  return trigCos[360 - d];
}

export function sinDegFP(trigCos, deg) {
  return cosDegFP(trigCos, 90 - (deg | 0));
}

/**
 * Destructible Worms World Simulation (Port of a.java + ah.java + y.java gameplay)
 */
export class WormsWorld {
  constructor(assets, audio = null) {
    this.assets = assets;
    this.audio = audio;
    this.save = loadPersistentSave();
    if (this.save.langIdx >= 0 && this.assets?.locale) {
      this.assets.locale.setLanguage(this.save.langIdx);
    }

    // Terrain buffers (a.java:1850-2447)
    this.mask = new Uint8Array(MASK_W * MASK_H); // 1 = solid, 0 = air (fast 1 byte/px runtime mask derived from bitmask math)
    this.crust = new Uint8Array(MASK_W * MASK_H); // 0=empty, 1=crust top, 2=subcrust, 3=scorch/fort
    this.minimap = new Uint8Array(MINIMAP_W * MINIMAP_H);
    this.terrainDirty = true;
    this.dirtyRects = [];
    this.craterLog = [];
    this.nextCraterSeq = 1;
    this.appliedCraterSeq = 0;

    // Core loop state (z.java / y.java)
    this.tickCount = 0;
    this.menuTick = 0;
    this.o = 0; // Top-level state (y.o: 0=boot, 1=EA splash, 2=Team17, 17=Title splash, 4=Menu, 5=Gen, 6=Match, 7=Pause, 9=Results)
    this.cm = 0; // Sub-menu state
    this.cn = 0; // Cursor index
    this.co = 0; // Scroll start index
    this.stateTimerMs = 0;

    // Match config
    this.mode = 'quick'; // 'campaign' | 'quick' | 'custom' | 'training' | 'bodycount' | 'multiplayer'
    this.campaignStage = 0; // 0..27
    this.themeId = 0;
    this.mapStyle = 0; // 0=Hills, 1=Island, 2=TwinPeaks, 3=Cavern, 4=Fort
    this.numTeams = 2;
    this.wormsPerTeam = 3;
    this.turnSecondsMax = 45;
    this.turnMsLeft = 45000;
    this.retreatMsLeft = 0;
    this.suddenDeathWaterRise = false;
    this.waterY = WATER_Y;
    this.turnNumber = 0;
    this.wind = 0; // -16384..16384 (16.16 FP -> +/- 0.25 px/tick^2)
    this.windDisplay = 0; // -100..100 for HUD
    this.rngState = 1234567;
    this.matchSeed = 1234567;

    // Match phase (y.p)
    // 'INTRO' (0), 'PLAY' (2), 'TARGET' (3), 'BACKPACK' (4), 'RESOLVE' (5), 'RETREAT' (6), 'OVER' (8)
    this.phase = 'PLAY';
    this.phaseTimerMs = 0;
    this.bannerText = '';
    this.bannerMs = 0;

    // Entities
    this.worms = [];
    this.teams = [];
    this.projectiles = [];
    this.crates = [];
    this.mines = [];
    this.drums = [];
    this.graves = [];
    this.particles = [];
    this.popups = [];
    this.flames = [];
    this.forts = [];
    this.clouds = Array.from({ length: 5 }, (_, i) => ({
      x: i * 180 + 40,
      y: 28 + (i % 3) * 24,
      speed: 0.25 + (i % 2) * 0.15,
      variant: i % 3,
    }));

    // Active controls
    this.activeWormIdx = -1;
    this.activeTeamIdx = 0;
    this.selectedWeapon = 0;
    this.backpackCursor = 0;
    this.chargePower = 0; // 0..100
    this.charging = false;
    this.fuseSeconds = 3;
    this.targetCursor = { x: WORLD_W >> 1, y: WORLD_H >> 1, dir: 1 };
    this.minimapOpen = false;
    this.minimapSlide = -64;
    this.backpackSlide = 320;
    this.teamBarsTimerMs = 0;
    this.binocularsMode = false;
    this.cameraLockTimer = 0;
    this.followEntity = null;
    this.camX = (WORLD_W - SCREEN_W) >> 1;
    this.camY = WORLD_H - SCREEN_H;
    this.smoothCamX = this.camX;
    this.smoothCamY = this.camY;
    this.shakeTicks = 0;
    this.shakeMag = 0;

    // Custom Match settings
    this.customCfg = {
      numTeams: 2,
      aiMask: 0b0010, // bit i = AI controlled
      wormsPerTeam: 3,
      themeId: 0,
      mapStyle: 0,
      turnTime: 45,
      crateFreq: 1, // 0=Off, 1=Normal, 2=Crazy
      suddenDeath: 1, // turns threshold * 5
    };

    // Campaign/End results
    this.matchResult = null;
    this.matchStats = { damageDealt: 0, kills: 0, coinsEarned: 0, cratesPicked: 0, winnerTeam: -1 };

    // Multiplayer integration callbacks (set by game.js)
    this.mp = {
      enabled: false,
      isHost: false,
      localTeamMask: 0b0001, // bitmask of teams controlled by this client
      peerNames: ['P1', 'P2', 'P3', 'P4'],
      onLocalAction: null, // (action) => void
      onSyncState: null,   // () => void (host emits snapshot)
    };

    // Input state for current 50ms tick
    this.heldKeys = 0;
    this.pressedKeys = 0;
    this.aiPlan = null;
    this.activeProjectilesFired = 0;
    this.lastDamageTick = 0;
    this.worshipTeam = -1;
    this.pendingCrateSpawn = false;
    this.bodyCountWave = 0;

    // Initialize boot splash sequence (y.java:1112-1225)
    this.initBootState();
  }

  initBootState() {
    if (this.save.langIdx >= 0) {
      this.o = 4; // Skip directly to Main Menu if language saved (y.java:1170), else Splash -> Lang
      this.cm = 0;
      this.cn = 0;
    } else {
      this.o = 1; // EA Logo -> Team17 -> Title -> Lang
      this.cm = 13;
      this.stateTimerMs = 0;
    }
  }

  nextRand() {
    // Port of a.java:2034 deterministic LCG
    this.rngState = (Math.imul(this.rngState, 1664525) + 1013904223) | 0;
    return this.rngState;
  }

  randRange(min, max) {
    if (max <= min) return min;
    const u = (this.nextRand() >>> 0);
    return min + (u % (max - min));
  }

  // ==================== TERRAIN ENGINE (a.java:1810-2447) ====================

  isMaskSolid(mx, my) {
    if (mx < 0 || mx >= MASK_W || my < 0 || my >= MASK_H) return false;
    return this.mask[my * MASK_W + mx] !== 0;
  }

  isSolid(wx, wy) {
    const ix = wx | 0;
    const iy = wy | 0;
    if (iy >= WATER_Y) return false;
    const mx = ix - MARGIN_X;
    const my = iy - MARGIN_Y;
    if (mx >= 0 && mx < MASK_W && my >= 0 && my < MASK_H) {
      return this.mask[my * MASK_W + mx] !== 0;
    }
    return false;
  }

  checkBoxSolid(wx, wy, w, h) {
    const x0 = Math.floor(wx);
    const y0 = Math.floor(wy);
    const x1 = Math.floor(wx + w - 1);
    const y1 = Math.floor(wy + h - 1);
    for (let y = y0; y <= y1; y += 2) {
      for (let x = x0; x <= x1; x += 2) {
        if (this.isSolid(x, y)) return true;
      }
      if (this.isSolid(x1, y)) return true;
    }
    return this.isSolid(x0, y1) || this.isSolid(x1, y1);
  }

  findSurfaceY(wx, startWy = MARGIN_Y) {
    const ix = Math.max(MARGIN_X + 4, Math.min(MARGIN_X + MASK_W - 4, wx | 0));
    for (let y = Math.max(MARGIN_Y, startWy | 0); y < WATER_Y; y++) {
      if (this.isSolid(ix, y) && !this.isSolid(ix, y - 4)) {
        return y;
      }
    }
    return -1;
  }

  /**
   * Port of a.java:1810-2375: B-spline + midpoint displacement + XOR scanline terrain generation.
   */
  generateTerrain({ seed, themeId, mapStyle, fortCount = 0 }) {
    this.matchSeed = seed | 0;
    this.rngState = seed | 0;
    this.themeId = themeId % THEME_CONFIGS.length;
    this.mapStyle = mapStyle | 0;
    this.mask.fill(0);
    this.crust.fill(0);
    this.forts = [];
    this.craterLog = [];
    this.nextCraterSeq = 1;
    this.appliedCraterSeq = 0;

    // Generate heightfield or cavern profile in mask coordinates [0..MASK_W, 0..MASK_H]
    const heights = new Float32Array(MASK_W);
    const p1 = (this.randRange(0, 628)) / 100;
    const p2 = (this.randRange(0, 628)) / 100;
    const p3 = (this.randRange(0, 628)) / 100;

    if (mapStyle === 4 || fortCount > 0) {
      // Fort Battle: two balanced islands facing across water gap (left + right!)
      for (let x = 0; x < MASK_W; x++) {
        if ((x > 30 && x < 235) || (x > 405 && x < 610)) {
          const ridge = Math.sin(x * 0.03 + p1) * 14 + Math.cos(x * 0.07 + p2) * 6;
          heights[x] = 175 + ridge;
        } else {
          heights[x] = MASK_H + 20; // Water channel
        }
      }
      // Build destructible forts on both platforms (matching ac[17..20])
      this.stampSolidPlatform(55, 165, 130, 52);
      this.stampSolidPlatform(445, 165, 130, 52);
      this.forts.push(
        { wx: MARGIN_X + 120, wy: MARGIN_Y + 165, packIdx: 17 + (this.themeId % 4) },
        { wx: MARGIN_X + 510, wy: MARGIN_Y + 165, packIdx: 17 + ((this.themeId + 1) % 4) },
      );
    } else if (mapStyle === 1) {
      // Island arch + bridges
      for (let x = 0; x < MASK_W; x++) {
        const nx = (x - MASK_W * 0.5) / (MASK_W * 0.45);
        const edgeDrop = Math.pow(Math.max(0, Math.abs(nx) - 0.15), 2.1) * 260;
        const n = Math.sin(x * 0.019 + p1) * 38 + Math.cos(x * 0.043 + p2) * 18 + Math.sin(x * 0.09 + p3) * 8;
        heights[x] = Math.max(58, 130 + n + edgeDrop);
      }
    } else if (mapStyle === 2) {
      // Twin Peaks + central valley
      for (let x = 0; x < MASK_W; x++) {
        const dL = Math.exp(-Math.pow((x - 160) / 95, 2)) * 135;
        const dR = Math.exp(-Math.pow((x - 480) / 95, 2)) * 135;
        const n = Math.sin(x * 0.025 + p1) * 16 + Math.cos(x * 0.06 + p2) * 9;
        heights[x] = Math.max(52, 235 - dL - dR + n);
      }
    } else if (mapStyle === 3) {
      // Cavern roof + rocky floor (roof at top y=22..72, floor undulating y=155..270)
      for (let x = 0; x < MASK_W; x++) {
        const baseY = 165 + Math.sin(x * 0.02 + p1) * 35 + Math.cos(x * 0.055 + p2) * 14;
        const roofH = x > 24 && x < MASK_W - 24
          ? 34 + Math.sin(x * 0.03 + p3) * 16 + Math.cos(x * 0.08 + p1) * 8
          : 0;
        for (let y = 0; y < roofH && y < MASK_H; y++) {
          this.mask[y * MASK_W + x] = 1;
        }
        heights[x] = Math.max(92, baseY);
      }
    } else {
      // Standard Rolling Worms Hills (mode 0) + hanging ledges
      for (let x = 0; x < MASK_W; x++) {
        const edge = Math.max(0, 48 - x, x - (MASK_W - 48));
        const hills =
          148 +
          Math.sin(x * 0.016 + p1) * 44 +
          Math.cos(x * 0.038 + p2) * 24 +
          Math.sin(x * 0.085 + p3) * 9;
        heights[x] = hills + edge * 3.8;
      }
    }

    // Fill floor heights down to MASK_H
    for (let x = 0; x < MASK_W; x++) {
      const topY = Math.max(0, Math.round(heights[x]));
      if (topY < MASK_H) {
        for (let y = topY; y < MASK_H; y++) {
          this.mask[y * MASK_W + x] = 1;
        }
      }
    }

    // Carve random tunnels / arches on non-fort maps for authentic Worms Swiss-cheese geometry
    if (mapStyle !== 4) {
      const nBlobs = mapStyle === 3 ? 4 : 3;
      for (let i = 0; i < nBlobs; i++) {
        const cx = this.randRange(110, MASK_W - 110);
        const cy = this.randRange(165, MASK_H - 55);
        const r = this.randRange(20, 34);
        this.carveRawMaskCircle(cx, cy, r);
      }
      // Floating island girders/rocks in upper sky
      for (let i = 0; i < 2; i++) {
        const lx = 110 + i * 310 + this.randRange(0, 75);
        const ly = 72 + this.randRange(0, 45);
        this.stampFloatingIsland(lx, ly, 76, 16);
      }
    }

    this.recomputeCrustAndMinimap();
    this.terrainDirty = true;
    this.dirtyRects = [{ x: 0, y: 0, w: MASK_W, h: MASK_H }];
  }

  stampSolidPlatform(x, y, w, h) {
    for (let py = Math.max(0, y); py < Math.min(MASK_H, y + h); py++) {
      for (let px = Math.max(0, x); px < Math.min(MASK_W, x + w); px++) {
        this.mask[py * MASK_W + px] = 1;
      }
    }
  }

  stampFloatingIsland(cx, cy, w, h) {
    const rx = w >> 1;
    const ry = h >> 1;
    for (let dy = -ry; dy <= ry; dy++) {
      for (let dx = -rx; dx <= rx; dx++) {
        const nx = dx / rx;
        const ny = dy < 0 ? dy / (ry * 0.65) : dy / (ry * 1.15);
        if (nx * nx + ny * ny <= 1.0) {
          const px = (cx + dx) | 0;
          const py = (cy + dy) | 0;
          if (px >= 0 && px < MASK_W && py >= 0 && py < MASK_H) {
            this.mask[py * MASK_W + px] = 1;
          }
        }
      }
    }
  }

  carveRawMaskCircle(cx, cy, r) {
    const r2 = r * r;
    const x0 = Math.max(0, cx - r);
    const x1 = Math.min(MASK_W - 1, cx + r);
    const y0 = Math.max(0, cy - r);
    const y1 = Math.min(MASK_H - 1, cy + r);
    for (let y = y0; y <= y1; y++) {
      const dy = y - cy;
      for (let x = x0; x <= x1; x++) {
        const dx = x - cx;
        if (dx * dx + dy * dy <= r2) {
          this.mask[y * MASK_W + x] = 0;
          this.crust[y * MASK_W + x] = 0;
        }
      }
    }
  }

  /**
   * Port of a.java:2270-2375: Bresenham/circular crater explosion carving + dark scorch rim + minimap update.
   */
  carveCrater(wx, wy, radius, recordNet = true) {
    const cx = Math.round(wx) - MARGIN_X;
    const cy = Math.round(wy) - MARGIN_Y;
    const r = Math.max(4, radius | 0);
    if (recordNet && (!this.mp.enabled || this.mp.isHost)) {
      this.craterLog.push([this.nextCraterSeq++, Math.round(wx), Math.round(wy), r]);
      if (this.craterLog.length > 64) this.craterLog.shift();
    }
    const r2 = r * r;
    const scorchOuterR2 = (r + 3) * (r + 3);
    const x0 = Math.max(0, cx - r - 4);
    const x1 = Math.min(MASK_W - 1, cx + r + 4);
    const y0 = Math.max(0, cy - r - 4);
    const y1 = Math.min(MASK_H - 1, cy + r + 4);

    for (let y = y0; y <= y1; y++) {
      const dy = y - cy;
      const row = y * MASK_W;
      for (let x = x0; x <= x1; x++) {
        const dx = x - cx;
        const d2 = dx * dx + dy * dy;
        if (d2 <= r2) {
          this.mask[row + x] = 0;
          this.crust[row + x] = 0;
        } else if (d2 <= scorchOuterR2 && this.mask[row + x] !== 0) {
          this.crust[row + x] = 3; // Scorched blast rim
        }
      }
    }

    // Update 170x64 minimap slice matching a.java:2337-2342
    this.syncMinimapRegion(x0, y0, x1 - x0 + 1, y1 - y0 + 1);
    this.dirtyRects.push({ x: x0, y: y0, w: x1 - x0 + 1, h: y1 - y0 + 1 });
    this.terrainDirty = true;
  }

  placeGirder(wx, wy, w = 44, h = 8) {
    const mx0 = Math.round(wx) - MARGIN_X - (w >> 1);
    const my0 = Math.round(wy) - MARGIN_Y - (h >> 1);
    for (let py = Math.max(0, my0); py < Math.min(MASK_H, my0 + h); py++) {
      for (let px = Math.max(0, mx0); px < Math.min(MASK_W, mx0 + w); px++) {
        this.mask[py * MASK_W + px] = 1;
        this.crust[py * MASK_W + px] = (py === my0 || py === my0 + 1) ? 3 : 2;
      }
    }
    this.syncMinimapRegion(mx0, my0, w, h);
    this.dirtyRects.push({ x: Math.max(0, mx0), y: Math.max(0, my0), w, h });
    this.terrainDirty = true;
  }

  recomputeCrustAndMinimap() {
    for (let x = 0; x < MASK_W; x++) {
      for (let y = 0; y < MASK_H; y++) {
        const idx = y * MASK_W + x;
        if (this.mask[idx] === 0) {
          this.crust[idx] = 0;
          continue;
        }
        const airAbove1 = y === 0 || this.mask[(y - 1) * MASK_W + x] === 0;
        const airAbove2 = y < 2 || this.mask[(y - 2) * MASK_W + x] === 0;
        const airAbove4 = y < 4 || this.mask[(y - 4) * MASK_W + x] === 0;
        if (airAbove1 || airAbove2) this.crust[idx] = 1;
        else if (airAbove4) this.crust[idx] = 2;
        else this.crust[idx] = 0;
      }
    }
    this.syncMinimapRegion(0, 0, MASK_W, MASK_H);
  }

  syncMinimapRegion(x, y, w, h) {
    const mx0 = Math.max(0, Math.floor((x * MINIMAP_W) / MASK_W));
    const mx1 = Math.min(MINIMAP_W - 1, Math.ceil(((x + w) * MINIMAP_W) / MASK_W));
    const my0 = Math.max(0, Math.floor((y * MINIMAP_H) / MASK_H));
    const my1 = Math.min(MINIMAP_H - 1, Math.ceil(((y + h) * MINIMAP_H) / MASK_H));
    for (let my = my0; my <= my1; my++) {
      const sy = Math.min(MASK_H - 1, Math.floor((my * MASK_H) / MINIMAP_H));
      for (let mx = mx0; mx <= mx1; mx++) {
        const sx = Math.min(MASK_W - 1, Math.floor((mx * MASK_W) / MINIMAP_W));
        this.minimap[my * MINIMAP_W + mx] = this.mask[sy * MASK_W + sx];
      }
    }
  }

  // ==================== MATCH LIFECYCLE ====================

  startCampaignStage(stageIndex) {
    const st = this.assets?.campaigns?.[stageIndex];
    const f = st?.fields || [];
    this.mode = 'campaign';
    this.campaignStage = stageIndex;
    this.matchResult = null;
    this.startMatch({
      seed: 10007 + stageIndex * 7919,
      themeId: Math.floor(stageIndex / 7) % 5,
      mapStyle: (stageIndex % 7 === 6) ? 4 : (stageIndex % 4),
      numTeams: 2,
      wormsPerTeam: Math.min(4, Math.max(2, (f[4] || 3))),
      aiMask: 0b0010,
      turnTime: 45,
      crateFreq: 1,
      suddenDeath: 15,
      wormHpPlayer: 100,
      wormHpAI: Math.min(180, 60 + stageIndex * 4),
      isBoss: (stageIndex % 7) === 6,
    });
  }

  startQuickMatch(seed = ((Date.now() & 0x7fffffff) || 12345)) {
    this.mode = 'quick';
    this.matchResult = null;
    this.startMatch({
      seed,
      themeId: Math.abs(seed) % 5,
      mapStyle: (Math.abs(seed >> 3)) % 4,
      numTeams: 2,
      wormsPerTeam: 3,
      aiMask: 0b0010,
      turnTime: 45,
      crateFreq: 1,
      suddenDeath: 15,
    });
  }

  startCustomMatch(cfg = this.customCfg, seed = ((Date.now() & 0x7fffffff) || 54321)) {
    this.mode = 'custom';
    this.matchResult = null;
    this.startMatch({ ...cfg, seed });
  }

  startTraining(kind = 0) {
    this.mode = 'training';
    this.matchResult = null;
    this.startMatch({
      seed: 42424 + kind * 131,
      themeId: 0,
      mapStyle: 0,
      numTeams: 2,
      wormsPerTeam: kind === 0 ? 1 : 2,
      aiMask: 0b0000, // Training dummies don't fire back
      turnTime: 99,
      crateFreq: 0,
      suddenDeath: 99,
      dummiesOnly: true,
    });
  }

  startBodyCount(seed = ((Date.now() & 0x7fffffff) || 98765)) {
    this.mode = 'bodycount';
    this.bodyCountWave = 1;
    this.matchResult = null;
    this.startMatch({
      seed,
      themeId: 1,
      mapStyle: 2,
      numTeams: 2,
      wormsPerTeam: 1,
      aiMask: 0b0010,
      turnTime: 45,
      crateFreq: 2,
      suddenDeath: 99,
      wormHpPlayer: 200,
      wormHpAI: 35,
      aiCountOverride: 3,
    });
  }

  startMatch(opts) {
    this.o = 6;
    this.phase = 'INTRO';
    this.phaseTimerMs = 1500;
    this.waterY = WATER_Y;
    this.suddenDeathWaterRise = false;
    this.turnNumber = 1;
    this.projectiles = [];
    this.crates = [];
    this.mines = [];
    this.drums = [];
    this.graves = [];
    this.particles = [];
    this.popups = [];
    this.flames = [];
    this.worshipTeam = -1;
    this.matchStats = { damageDealt: 0, kills: 0, coinsEarned: 0, cratesPicked: 0, winnerTeam: -1 };
    this.dummiesOnly = Boolean(opts.dummiesOnly);
    this.turnSecondsMax = opts.turnTime || 45;
    this.suddenDeathTurns = opts.suddenDeath || 15;
    this.crateFreq = opts.crateFreq ?? 1;

    this.generateTerrain({
      seed: opts.seed,
      themeId: opts.themeId ?? 0,
      mapStyle: opts.mapStyle ?? 0,
      fortCount: (opts.mapStyle === 4) ? 2 : 0,
    });

    const nTeams = Math.max(2, Math.min(4, opts.numTeams || 2));
    this.numTeams = nTeams;
    this.teams = [];
    for (let t = 0; t < nTeams; t++) {
      const style = TEAM_STYLES[t];
      const ammo = createStandardAmmo();
      // Merge player team 0 shop inventory!
      if (t === 0 && (this.mode === 'campaign' || this.mode === 'quick' || this.mode === 'bodycount')) {
        for (let w = 0; w < 36; w++) {
          if (this.save.shopStock[w] > 0 && ammo[w] >= 0) {
            ammo[w] += this.save.shopStock[w];
          }
        }
      }
      this.teams.push({
        id: t,
        name: this.mp.enabled ? (this.mp.peerNames[t] || style.name) : style.name,
        hex: style.hex,
        dark: style.dark,
        hat: style.hat,
        isAI: ((opts.aiMask || 0) & (1 << t)) !== 0,
        ammo,
        lastWormCursor: 0,
      });
    }

    // Spawn worms spread cleanly across solid ground
    this.worms = [];
    const totalPerTeam = opts.wormsPerTeam || 3;
    const spawnPositions = this.pickSpreadSpawns(nTeams, opts.aiCountOverride || totalPerTeam, totalPerTeam);

    let wId = 0;
    for (let t = 0; t < nTeams; t++) {
      const count = (t === 1 && opts.aiCountOverride) ? opts.aiCountOverride : totalPerTeam;
      for (let k = 0; k < count; k++) {
        const sp = spawnPositions[wId] || { x: MARGIN_X + 90 + wId * 56, y: MARGIN_Y + 60 };
        const hp = t === 0 ? (opts.wormHpPlayer || 100) : (opts.wormHpAI || 100);
        this.worms.push({
          id: wId++,
          team: t,
          name: `W${t + 1}-${k + 1}`,
          x: sp.x,
          y: sp.y,
          vx: 0,
          vy: 0,
          dir: sp.x > WORLD_W * 0.5 ? -1 : 1,
          aimAngle: 60, // 0=up, 90=horizontal, 180=down (matches ah.a[5])
          hp,
          maxHp: hp,
          alive: true,
          onGround: false,
          poisoned: 0,
          markedForDeath: false,
          weaponId: 0,
          state: 'IDLE',
          animTick: 0,
          parachuteOpen: false,
          jetpackFuel: 30,
          rope: null,
        });
      }
    }

    // Spawn Mines & Exploding Oil Drums with safe distance (>= 72px) from all worms
    if (!this.dummiesOnly) {
      for (let i = 0; i < 4; i++) {
        const mx = this.randRange(MARGIN_X + 60, MARGIN_X + MASK_W - 60);
        const my = this.findSurfaceY(mx);
        if (my > MARGIN_Y && my < WATER_Y - 12 && !this.isNearAnyWorm(mx, my, 72)) {
          this.mines.push({ x: mx, y: my - 4, vx: 0, vy: 0, triggered: false, fuseMs: 3000, armed: true });
        }
      }
      for (let i = 0; i < 3; i++) {
        const dx = this.randRange(MARGIN_X + 70, MARGIN_X + MASK_W - 70);
        const dy = this.findSurfaceY(dx);
        if (dy > MARGIN_Y && dy < WATER_Y - 14 && !this.isNearAnyWorm(dx, dy, 60)) {
          this.drums.push({ x: dx, y: dy - 8, vx: 0, vy: 0, hp: 20, alive: true });
        }
      }
    }

    this.rollWind();
    this.activeTeamIdx = 0;
    this.selectNextWormForTeam(0);
    this.snapCameraToActive();
    this.bannerText = this.teams[0].name;
    this.bannerMs = 1500;
    this.audio?.playTrack?.(1);
    this.audio?.playSfx?.('turnStart');
  }

  isNearAnyWorm(wx, wy, dist) {
    return this.worms.some((w) => w.alive && Math.hypot(w.x - wx, w.y - wy) < dist);
  }

  pickSpreadSpawns(nTeams, aiCountOverride, basePerTeam) {
    const slots = [];
    const totalNeeded = nTeams === 2 ? basePerTeam + aiCountOverride : nTeams * basePerTeam;
    const minX = MARGIN_X + 44;
    const maxX = MARGIN_X + MASK_W - 44;
    const span = maxX - minX;

    const candidates = [];
    for (let x = minX; x <= maxX; x += 14) {
      const y = this.findSurfaceY(x, MARGIN_Y + 4);
      if (y > MARGIN_Y + 12 && y < WATER_Y - 20) {
        candidates.push({ x, y: y - 10 });
      }
    }

    // Fort mode separates Team 0 left island & Team 1 right island
    if (this.mapStyle === 4) {
      const leftC = candidates.filter((c) => c.x < WORLD_W * 0.45);
      const rightC = candidates.filter((c) => c.x > WORLD_W * 0.55);
      for (let i = 0; i < totalNeeded; i++) {
        const team = i < basePerTeam ? 0 : 1;
        const pool = team === 0 ? leftC : rightC;
        if (pool.length > 0) {
          slots.push(pool[this.randRange(0, pool.length)]);
        } else {
          slots.push({ x: team === 0 ? 180 : 620, y: 200 });
        }
      }
      return slots;
    }

    for (let i = 0; i < totalNeeded; i++) {
      if (candidates.length > 0) {
        const pick = candidates[this.randRange(0, candidates.length)];
        // Avoid identical spot
        if (!slots.some((s) => Math.abs(s.x - pick.x) < 16)) {
          slots.push(pick);
          continue;
        }
      }
      const fx = minX + ((i + 0.5) / totalNeeded) * span;
      const fy = this.findSurfaceY(fx, MARGIN_Y + 4);
      slots.push({ x: fx, y: (fy > 0 ? fy : WATER_Y - 80) - 10 });
    }
    return slots;
  }

  rollWind() {
    this.windDisplay = this.randRange(-100, 101);
    this.wind = (this.windDisplay * 16384) / 100; // fraction px/tick^2 scaled
  }

  getActiveWorm() {
    return this.worms[this.activeWormIdx] || null;
  }

  selectNextWormForTeam(teamIdx) {
    const teamWorms = this.worms.filter((w) => w.team === teamIdx && w.alive);
    if (teamWorms.length === 0) return null;
    const team = this.teams[teamIdx];
    team.lastWormCursor = (team.lastWormCursor + 1) % teamWorms.length;
    const chosen = teamWorms[team.lastWormCursor];
    this.activeWormIdx = this.worms.indexOf(chosen);
    // Ensure valid ammo on worm's selected weapon
    if (team.ammo[chosen.weaponId] === 0) {
      chosen.weaponId = 0;
    }
    this.selectedWeapon = chosen.weaponId;
    this.turnMsLeft = this.turnSecondsMax * 1000;
    this.retreatMsLeft = 0;
    this.charging = false;
    this.chargePower = 0;
    this.aiPlan = null;
    this.targetCursor = { x: chosen.x + chosen.dir * 80, y: Math.max(MARGIN_Y + 30, chosen.y - 40), dir: chosen.dir };
    return chosen;
  }

  snapCameraToActive() {
    const w = this.getActiveWorm();
    if (!w) return;
    this.camX = Math.max(0, Math.min(WORLD_W - SCREEN_W, w.x - SCREEN_W * 0.5));
    this.camY = Math.max(0, Math.min(WORLD_H - SCREEN_H, w.y - SCREEN_H * 0.5));
    this.smoothCamX = this.camX;
    this.smoothCamY = this.camY;
  }

  // ==================== 50ms FIXED GAME TICK (y.a / z.run) ====================

  stepTick(dtMs = 50) {
    this.tickCount++;
    this.menuTick++;

    // Scroll clouds horizontally
    for (const cl of this.clouds) {
      cl.x += cl.speed + this.windDisplay * 0.004;
      if (cl.x > WORLD_W + 60) cl.x = -60;
      if (cl.x < -60) cl.x = WORLD_W + 60;
    }

    if (this.shakeTicks > 0) this.shakeTicks--;
    if (this.bannerMs > 0) this.bannerMs = Math.max(0, this.bannerMs - dtMs);
    if (this.teamBarsTimerMs > 0) this.teamBarsTimerMs = Math.max(0, this.teamBarsTimerMs - dtMs);

    // Splash sequence (y.java:1215-1225)
    if (this.o === 1 || this.o === 2 || this.o === 17) {
      this.stateTimerMs += dtMs;
      if (this.stateTimerMs >= 1400 || this.pressedKeys !== 0) {
        this.stateTimerMs = 0;
        if (this.o === 1) this.o = 2;
        else if (this.o === 2) this.o = 17;
        else {
          this.o = 4;
          this.cm = this.save.langIdx < 0 ? 13 : 0;
          this.cn = 0;
        }
        this.pressedKeys = 0;
      }
      return;
    }

    if (this.o === 4 || this.o === 7 || this.o === 9) {
      this.handleMenuInput();
      this.pressedKeys = 0;
      return;
    }

    if (this.o !== 6) {
      this.pressedKeys = 0;
      return;
    }

    // In-Match Tick (o === 6)
    this.stepMatch(dtMs);
    this.pressedKeys = 0;
  }

  canLocalControlActiveTeam() {
    const team = this.teams[this.activeTeamIdx];
    if (!team) return false;
    if (team.isAI) return false;
    if (this.mp.enabled) {
      return (this.mp.localTeamMask & (1 << this.activeTeamIdx)) !== 0;
    }
    return true;
  }

  stepMatch(dtMs) {
    // Pause shortcut (RSK or Esc/E when not targeting)
    if ((this.pressedKeys & KEY.BACK) !== 0 || ((this.pressedKeys & KEY.RSK) !== 0 && this.phase === 'PLAY')) {
      if (this.phase === 'BACKPACK') {
        this.phase = 'PLAY';
      } else if (!this.mp.enabled) {
        this.o = 7; // Pause Menu
        this.cn = 0;
        return;
      }
    }

    // Minimap slide toggle (KEY.NUM7)
    if ((this.pressedKeys & KEY.NUM7) !== 0) {
      this.minimapOpen = !this.minimapOpen;
      this.audio?.playSfx?.('uiMove');
    }
    this.minimapSlide += this.minimapOpen ? Math.min(16, 0 - this.minimapSlide) : Math.max(-16, -64 - this.minimapSlide);

    // Team HP bars toggle (KEY.NUM0 / STAR)
    if ((this.pressedKeys & KEY.NUM0) !== 0) {
      this.teamBarsTimerMs = 2500;
    }

    // In Multiplayer Guest mode: do light particle/camera smoothing, wait for Host snapshots & local input relay
    if (this.mp.enabled && !this.mp.isHost) {
      this.stepParticlesAndPopups(dtMs);
      this.updateCamera();
      return;
    }

    // Step world physics (worms, projectiles, crates, mines, drums, flames, particles)
    this.stepWormsPhysics(dtMs);
    this.stepProjectiles(dtMs);
    this.stepCratesAndMines(dtMs);
    this.stepFlames(dtMs);
    this.stepParticlesAndPopups(dtMs);

    // Phase state machine
    if (this.phase === 'INTRO') {
      this.phaseTimerMs -= dtMs;
      if (this.phaseTimerMs <= 0) {
        this.phase = 'PLAY';
        this.turnMsLeft = this.turnSecondsMax * 1000;
      }
      this.updateCamera();
      return;
    }

    if (this.phase === 'BACKPACK') {
      this.backpackSlide = Math.max(0, this.backpackSlide - 48);
      this.handleBackpackInput();
      this.updateCamera();
      return;
    } else {
      this.backpackSlide = Math.min(SCREEN_W, this.backpackSlide + 48);
    }

    if (this.phase === 'TARGET') {
      this.handleTargetInput();
      this.updateCamera();
      return;
    }

    if (this.phase === 'PLAY') {
      const aw = this.getActiveWorm();
      if (!aw || !aw.alive) {
        this.beginTurnResolve();
        return;
      }

      // Countdown turn timer (unless paused while aiming if option enabled)
      const shouldPauseTimer = this.save.timerPause && this.charging;
      if (!shouldPauseTimer && !this.dummiesOnly) {
        this.turnMsLeft -= dtMs;
        if (this.turnMsLeft <= 0) {
          this.turnMsLeft = 0;
          if (this.charging) {
            this.fireCurrentWeapon();
          } else {
            this.audio?.playSfx?.('timerOut');
            this.beginTurnResolve();
          }
          return;
        }
      }

      if (this.teams[this.activeTeamIdx]?.isAI) {
        this.stepAI(aw, dtMs);
      } else if (this.canLocalControlActiveTeam()) {
        this.handleActiveWormInput(aw, dtMs);
      }
    } else if (this.phase === 'RETREAT') {
      const aw = this.getActiveWorm();
      if (aw && aw.alive && !this.teams[this.activeTeamIdx]?.isAI && this.canLocalControlActiveTeam()) {
        this.handleActiveWormInput(aw, dtMs, true);
      }
      this.retreatMsLeft -= dtMs;
      if (this.retreatMsLeft <= 0 || this.projectiles.length > 0) {
        this.phase = 'RESOLVE';
        this.phaseTimerMs = 600;
      }
    } else if (this.phase === 'RESOLVE') {
      const busy =
        this.projectiles.length > 0 ||
        this.flames.length > 0 ||
        this.worms.some((w) => w.alive && (!w.onGround || Math.abs(w.vx) > 0.1 || Math.abs(w.vy) > 0.1));
      if (!busy) {
        this.phaseTimerMs -= dtMs;
        if (this.phaseTimerMs <= 0) {
          this.finishTurnAndAdvance();
        }
      } else {
        this.phaseTimerMs = 450;
      }
    }

    this.updateCamera();
  }

  handleActiveWormInput(aw, dtMs, retreatOnly = false) {
    // Open Backpack (LSK)
    if (!retreatOnly && !this.charging && (this.pressedKeys & KEY.LSK) !== 0) {
      this.phase = 'BACKPACK';
      this.backpackCursor = Math.max(0, aw.weaponId);
      this.audio?.playSfx?.('uiSelect');
      return;
    }

    // Adjust fuse # (1..5s) for timed weapons (1, 7, 19, 25)
    if (!retreatOnly && (this.pressedKeys & KEY.HASH) !== 0) {
      this.fuseSeconds = (this.fuseSeconds % 5) + 1;
      this.spawnPopup(aw.x, aw.y - 26, `${this.fuseSeconds}S`, '#ffd84c');
      this.audio?.playSfx?.('uiMove');
    }

    // Jetpack movement when equipped (weaponId === 2)
    if (aw.weaponId === 2 && aw.state === 'JETPACK') {
      if ((this.heldKeys & MASK_UP) !== 0 && aw.jetpackFuel > 0) {
        aw.vy = Math.max(-3.6, aw.vy - 0.55);
        aw.jetpackFuel = Math.max(0, aw.jetpackFuel - 0.35);
        this.spawnFlameParticle(aw.x - aw.dir * 4, aw.y + 8);
      }
      if ((this.heldKeys & MASK_LEFT) !== 0) { aw.vx = -2.2; aw.dir = -1; }
      else if ((this.heldKeys & MASK_RIGHT) !== 0) { aw.vx = 2.2; aw.dir = 1; }
      if ((this.pressedKeys & MASK_FIRE) !== 0 || aw.jetpackFuel <= 0 || aw.onGround) {
        aw.state = 'IDLE';
        this.consumeAmmo(aw.team, 2);
        this.beginRetreatOrResolve(1500);
      }
      return;
    }

    // Walk Left / Right
    if (!this.charging) {
      const moveLeft = (this.heldKeys & MASK_LEFT) !== 0;
      const moveRight = (this.heldKeys & MASK_RIGHT) !== 0;
      if (moveLeft || moveRight) {
        aw.dir = moveLeft ? -1 : 1;
        if (aw.onGround) {
          this.tryWalkStep(aw, aw.dir * 1.45);
          aw.state = 'WALK';
          aw.animTick++;
        } else {
          aw.vx = aw.dir * 1.3;
        }
      } else if (aw.onGround) {
        aw.state = 'IDLE';
      }

      // Forward Jump (NUM3 / Space/J? Also NUM1 backflip!)
      if (aw.onGround && (this.pressedKeys & KEY.NUM3) !== 0) {
        aw.vx = aw.dir * 2.2;
        aw.vy = -4.2;
        aw.onGround = false;
        aw.state = 'JUMP';
        this.audio?.playSfx?.('jump');
      } else if (aw.onGround && (this.pressedKeys & KEY.NUM1) !== 0) {
        aw.vx = -aw.dir * 1.25;
        aw.vy = -5.6;
        aw.onGround = false;
        aw.state = 'JUMP';
        this.audio?.playSfx?.('jump');
      }
    }

    if (retreatOnly) return;

    // Parachute auto-deploy if falling and weapon === 8 (or press FIRE midair)
    if (!aw.onGround && aw.weaponId === 8 && (this.pressedKeys & MASK_FIRE) !== 0) {
      aw.parachuteOpen = !aw.parachuteOpen;
      if (aw.parachuteOpen) this.consumeAmmo(aw.team, 8);
    }

    // Aim up/down (0..180 deg)
    if ((this.heldKeys & MASK_UP) !== 0) {
      aw.aimAngle = Math.max(5, aw.aimAngle - 3);
    } else if ((this.heldKeys & MASK_DOWN) !== 0) {
      aw.aimAngle = Math.min(170, aw.aimAngle + 3);
    }

    // Binoculars camera pan toggle (KEY.NUM9)
    if ((this.pressedKeys & KEY.NUM9) !== 0) {
      this.binocularsMode = !this.binocularsMode;
    }

    // Fire / Charge
    const wId = aw.weaponId;
    const isTargetWeapon = wId === 5 || wId === 6 || wId === 11 || wId === 14 || wId === 24 || wId === 28;
    const needsCharge = wId === 0 || wId === 1 || wId === 7 || wId === 13 || wId === 19 || wId === 25;

    if ((this.pressedKeys & MASK_FIRE) !== 0) {
      if (isTargetWeapon && !this.targetPicked) {
        this.phase = 'TARGET';
        this.targetCursor = { x: aw.x + aw.dir * 90, y: Math.max(MARGIN_Y + 40, aw.y - 30), dir: aw.dir };
        this.audio?.playSfx?.('uiSelect');
        return;
      }
      if (needsCharge) {
        this.charging = true;
        this.chargePower = 12;
      } else {
        this.fireCurrentWeapon();
        return;
      }
    }

    if (this.charging) {
      if ((this.heldKeys & MASK_FIRE) !== 0) {
        this.chargePower = Math.min(100, this.chargePower + 3.8);
        if (this.chargePower >= 100) {
          this.fireCurrentWeapon();
        }
      } else {
        this.fireCurrentWeapon();
      }
    }
  }

  tryWalkStep(aw, dx) {
    const nx = aw.x + dx;
    if (nx < MARGIN_X + 6 || nx > MARGIN_X + MASK_W - 6) return;
    // Climb slopes up to 7px high (standard Worms step-up)
    for (let lift = 0; lift <= 7; lift++) {
      if (!this.checkBoxSolid(nx - 5, aw.y - 8 - lift, 10, 14)) {
        aw.x = nx;
        aw.y -= lift;
        return;
      }
    }
  }

  handleBackpackInput() {
    if ((this.pressedKeys & KEY.LSK) !== 0 || (this.pressedKeys & KEY.RSK) !== 0 || (this.pressedKeys & KEY.BACK) !== 0) {
      this.phase = 'PLAY';
      this.audio?.playSfx?.('uiBack');
      return;
    }
    if ((this.pressedKeys & MASK_LEFT) !== 0) {
      this.backpackCursor = this.backpackCursor % 9 === 0 ? this.backpackCursor + 8 : this.backpackCursor - 1;
      this.audio?.playSfx?.('uiMove');
    } else if ((this.pressedKeys & MASK_RIGHT) !== 0) {
      this.backpackCursor = this.backpackCursor % 9 === 8 ? this.backpackCursor - 8 : this.backpackCursor + 1;
      this.audio?.playSfx?.('uiMove');
    } else if ((this.pressedKeys & MASK_UP) !== 0) {
      this.backpackCursor = (this.backpackCursor + 27) % 36;
      this.audio?.playSfx?.('uiMove');
    } else if ((this.pressedKeys & MASK_DOWN) !== 0) {
      this.backpackCursor = (this.backpackCursor + 9) % 36;
      this.audio?.playSfx?.('uiMove');
    }

    if ((this.pressedKeys & MASK_FIRE) !== 0) {
      const team = this.teams[this.activeTeamIdx];
      const aw = this.getActiveWorm();
      if (aw && team && team.ammo[this.backpackCursor] !== 0) {
        aw.weaponId = this.backpackCursor;
        this.selectedWeapon = this.backpackCursor;
        this.targetPicked = false;
        this.phase = 'PLAY';
        this.audio?.playSfx?.('uiSelect');
      } else {
        this.audio?.playSfx?.('error');
      }
    }
  }

  handleTargetInput() {
    const speed = 5;
    if ((this.heldKeys & MASK_LEFT) !== 0) this.targetCursor.x -= speed;
    if ((this.heldKeys & MASK_RIGHT) !== 0) this.targetCursor.x += speed;
    if ((this.heldKeys & MASK_UP) !== 0) this.targetCursor.y -= speed;
    if ((this.heldKeys & MASK_DOWN) !== 0) this.targetCursor.y += speed;
    this.targetCursor.x = Math.max(MARGIN_X + 10, Math.min(MARGIN_X + MASK_W - 10, this.targetCursor.x));
    this.targetCursor.y = Math.max(MARGIN_Y + 10, Math.min(WATER_Y - 12, this.targetCursor.y));

    if ((this.pressedKeys & (KEY.RSK | KEY.BACK)) !== 0) {
      this.phase = 'PLAY';
      return;
    }
    if ((this.pressedKeys & MASK_FIRE) !== 0) {
      const aw = this.getActiveWorm();
      if (aw && aw.weaponId === 14 && this.isSolid(this.targetCursor.x, this.targetCursor.y)) {
        this.audio?.playSfx?.('error'); // Cannot teleport inside solid ground (ah.java:2809)
        return;
      }
      this.phase = 'PLAY';
      if (aw && aw.weaponId === 6) {
        // Homing Missile picked target, now charge power!
        this.targetPicked = true;
        this.charging = true;
        this.chargePower = 15;
      } else {
        this.fireCurrentWeapon();
      }
    }
  }

  // ==================== WEAPONS & PROJECTILES (ah.java:5965-8546) ====================

  consumeAmmo(teamIdx, weaponId) {
    const team = this.teams[teamIdx];
    if (!team) return;
    if (team.ammo[weaponId] > 0) {
      team.ammo[weaponId]--;
      if (teamIdx === 0 && this.save.shopStock[weaponId] > 0) {
        this.save.shopStock[weaponId] = Math.max(0, this.save.shopStock[weaponId] - 1);
      }
    }
  }

  fireCurrentWeapon() {
    const aw = this.getActiveWorm();
    if (!aw) return;
    const wId = aw.weaponId;
    this.charging = false;
    const pwr = Math.max(15, this.chargePower || 65) / 100;
    this.chargePower = 0;

    const trig = this.assets?.trigCos;
    const rad = ((90 - aw.aimAngle) * Math.PI) / 180;
    const cosA = trig ? cosDegFP(trig, aw.aimAngle) / FP_ONE : Math.sin(rad);
    const sinA = trig ? sinDegFP(trig, aw.aimAngle) / FP_ONE : Math.cos(rad);
    // Notice aimAngle=0 points UP (x=0, y=-1), 90 horizontal (x=dir, y=0)
    const dirX = aw.dir * Math.sin((aw.aimAngle * Math.PI) / 180);
    const dirY = -Math.cos((aw.aimAngle * Math.PI) / 180);
    const speedBase = 3.2 + pwr * 8.4;

    this.consumeAmmo(aw.team, wId);

    switch (wId) {
      case 0: // Bazooka
      case 6: // Homing Missile
      case 17: // Bunker Buster
      {
        const spd = wId === 17 ? 5.5 : speedBase;
        this.projectiles.push({
          kind: wId,
          ownerTeam: aw.team,
          ownerWorm: aw.id,
          x: aw.x + dirX * 10,
          y: aw.y - 4 + dirY * 10,
          vx: dirX * spd,
          vy: dirY * spd,
          gravity: WEAPON_STATS[wId][4] / FP_ONE,
          windScale: WEAPON_STATS[wId][5] / FP_ONE,
          bounces: 0,
          fuseMs: 12000,
          targetX: this.targetCursor.x,
          targetY: this.targetCursor.y,
          homingTicks: wId === 6 ? 12 : 0,
          drillTicks: wId === 17 ? 24 : 0,
        });
        this.audio?.playSfx?.('launch');
        this.beginRetreatOrResolve(2500);
        break;
      }

      case 1: // Grenade
      case 7: // Cluster Bomb
      case 13: // Petrol Bomb
      case 19: // Holy Hand Grenade
      case 25: // Banana Bomb
      {
        const spd = 2.6 + pwr * 7.8;
        this.projectiles.push({
          kind: wId,
          ownerTeam: aw.team,
          ownerWorm: aw.id,
          x: aw.x + dirX * 10,
          y: aw.y - 4 + dirY * 10,
          vx: dirX * spd,
          vy: dirY * spd,
          gravity: 0.45,
          windScale: 0,
          bounces: 99,
          elasticity: wId === 25 ? 0.82 : 0.56,
          impactDetonate: wId === 13,
          fuseMs: (wId === 19 ? 4 : this.fuseSeconds) * 1000,
        });
        if (wId === 19) this.audio?.playSfx?.('holy');
        else this.audio?.playSfx?.('throw');
        this.beginRetreatOrResolve(3000);
        break;
      }

      case 2: // Jetpack active flight!
        aw.state = 'JETPACK';
        aw.onGround = false;
        this.audio?.playSfx?.('jetpack');
        break;

      case 3: // Fire Punch
      case 15: // Battle Axe
      case 21: // Baseball Bat
      case 27: // Dragon Ball
      {
        const isBat = wId === 21;
        const isAxe = wId === 15;
        const hitX = aw.x + aw.dir * 16;
        const hitY = aw.y - 2;
        if (wId === 3) {
          aw.vy = -5.4;
          aw.vx = aw.dir * 1.4;
          aw.onGround = false;
        }
        if (wId === 27) {
          this.projectiles.push({
            kind: 27, ownerTeam: aw.team, ownerWorm: aw.id,
            x: hitX, y: hitY, vx: aw.dir * 7.5, vy: 0,
            gravity: 0, windScale: 0, bounces: 0, fuseMs: 700,
          });
        } else {
          this.applyMeleeHit(hitX, hitY, isAxe ? 22 : 24, isBat ? 30 : 30, aw, isAxe, isBat);
        }
        this.audio?.playSfx?.('punch');
        this.beginRetreatOrResolve(2500);
        break;
      }

      case 4: // Drop Mine
      case 31: // Dynamite
      {
        if (wId === 4) {
          this.mines.push({ x: aw.x + aw.dir * 8, y: aw.y, vx: aw.dir * 1.1, vy: -1.5, triggered: false, fuseMs: 3000, armed: true });
        } else {
          this.projectiles.push({
            kind: 31, ownerTeam: aw.team, ownerWorm: aw.id,
            x: aw.x + aw.dir * 6, y: aw.y - 2, vx: 0, vy: 0,
            gravity: 0.45, windScale: 0, bounces: 0, fuseMs: 5000,
          });
        }
        this.audio?.playSfx?.('place');
        this.beginRetreatOrResolve(5000);
        break;
      }

      case 5: // Air Strike
      case 11: // Napalm Strike
      {
        const count = 5;
        for (let i = 0; i < count; i++) {
          const offsetX = (i - 2) * 20;
          this.projectiles.push({
            kind: wId,
            ownerTeam: aw.team,
            ownerWorm: aw.id,
            x: this.targetCursor.x + offsetX,
            y: MARGIN_Y - 10 - i * 14,
            vx: (this.targetCursor.dir || 1) * 1.5,
            vy: 2.2,
            gravity: 0.32,
            windScale: wId === 5 ? 0.35 : 0.15,
            bounces: 0,
            fuseMs: 9000,
          });
        }
        this.audio?.playSfx?.('airstrike');
        this.beginRetreatOrResolve(0);
        break;
      }

      case 9: // Marked for Death
      {
        const enemies = this.worms.filter((w) => w.alive && w.team !== aw.team);
        let nearest = null;
        let bestD = Infinity;
        for (const e of enemies) {
          const d = Math.hypot(e.x - this.targetCursor.x, e.y - this.targetCursor.y);
          if (d < bestD) { bestD = d; nearest = e; }
        }
        if (nearest) {
          nearest.markedForDeath = true;
          this.spawnPopup(nearest.x, nearest.y - 28, '2X DMG!', '#ff4444');
        }
        this.audio?.playSfx?.('powerup');
        this.beginTurnResolve();
        break;
      }

      case 10: // Sheep
      case 16: // Super Sheep
      case 22: // Buffalo of Lies
      {
        this.projectiles.push({
          kind: wId,
          ownerTeam: aw.team,
          ownerWorm: aw.id,
          x: aw.x + aw.dir * 10,
          y: aw.y - 2,
          vx: aw.dir * (wId === 22 ? 3.2 : 2.1),
          vy: -1.8,
          dir: aw.dir,
          gravity: wId === 16 ? 0.08 : 0.42,
          windScale: 0,
          bounces: 99,
          walker: wId === 10 || wId === 22,
          fuseMs: wId === 22 ? 6000 : 8000,
          flyMode: wId === 16,
        });
        this.audio?.playSfx?.('sheep');
        this.beginRetreatOrResolve(0);
        break;
      }

      case 12: // Shotgun
      case 30: // Uzi
      {
        const shots = wId === 30 ? 8 : 1;
        for (let s = 0; s < shots; s++) {
          const spread = wId === 30 ? (this.randRange(-12, 13) * 0.012) : 0;
          const bvx = (dirX + spread) * 14;
          const bvy = (dirY + spread) * 14;
          this.traceBulletHit(aw, aw.x + dirX * 10, aw.y - 4 + dirY * 10, bvx, bvy, wId === 30 ? 6 : 25, wId === 30 ? 5 : 14);
        }
        this.audio?.playSfx?.(wId === 30 ? 'uzi' : 'shotgun');
        if (wId === 12 && !this.shotgunSecondShotDone) {
          // Shotgun allows 2 shots per turn (ah.java:6099)
          this.shotgunSecondShotDone = true;
          this.bannerText = 'SHOT 2';
          this.bannerMs = 900;
        } else {
          this.shotgunSecondShotDone = false;
          this.beginRetreatOrResolve(1500);
        }
        break;
      }

      case 14: // Teleport
      case 33: // Emergency Teleport
      {
        if (wId === 33) {
          const sx = this.randRange(MARGIN_X + 40, MARGIN_X + MASK_W - 40);
          const sy = this.findSurfaceY(sx, MARGIN_Y + 4);
          aw.x = sx;
          aw.y = (sy > 0 ? sy : MARGIN_Y + 80) - 10;
        } else {
          aw.x = this.targetCursor.x;
          aw.y = this.targetCursor.y;
        }
        aw.vy = 0;
        aw.onGround = false;
        this.spawnExplosionParticles(aw.x, aw.y, 18, '#66d8ff');
        this.audio?.playSfx?.('teleport');
        this.beginTurnResolve();
        break;
      }

      case 18: // Worship
      {
        this.worshipTeam = aw.team;
        this.spawnPopup(aw.x, aw.y - 28, 'WORSHIP!', '#ffd84c');
        this.audio?.playSfx?.('powerup');
        this.beginTurnResolve();
        break;
      }

      case 20: // Pneumatic Drill
      case 26: // Blow Torch
      {
        const steps = 6;
        for (let i = 0; i < steps; i++) {
          const cx = wId === 20 ? aw.x : aw.x + aw.dir * (10 + i * 7);
          const cy = wId === 20 ? aw.y + i * 8 : aw.y - 3;
          this.carveCrater(cx, cy, 12);
          if (wId === 26) aw.x = cx;
          else aw.y = cy;
        }
        this.audio?.playSfx?.('drill');
        this.beginRetreatOrResolve(1500);
        break;
      }

      case 24: // Lightning Strike
      {
        const tx = this.targetCursor.x;
        const ty = this.targetCursor.y;
        this.explodeAt(tx, ty, 30, 50, 24, aw.team);
        this.particles.push({ kind: 'lightning', x: tx, y: ty, ttl: 260, maxTtl: 260, color: '#88eeff' });
        this.audio?.playSfx?.('thunder');
        this.beginTurnResolve();
        break;
      }

      case 28: // Concrete Donkey
      {
        this.projectiles.push({
          kind: 28,
          ownerTeam: aw.team,
          ownerWorm: aw.id,
          x: this.targetCursor.x,
          y: MARGIN_Y - 12,
          vx: 0,
          vy: 5.5,
          gravity: 0.4,
          windScale: 0,
          bounces: 6,
          donkeySmash: true,
          fuseMs: 9000,
        });
        this.audio?.playSfx?.('donkey');
        this.beginTurnResolve();
        break;
      }

      case 29: // Armageddon
      {
        for (let i = 0; i < 10; i++) {
          this.projectiles.push({
            kind: 29,
            ownerTeam: aw.team,
            ownerWorm: aw.id,
            x: this.randRange(MARGIN_X + 30, MARGIN_X + MASK_W - 30),
            y: MARGIN_Y - i * 28,
            vx: this.randRange(-2, 3),
            vy: 4.5,
            gravity: 0.25,
            windScale: 0.2,
            bounces: 0,
            fuseMs: 8000,
          });
        }
        this.audio?.playSfx?.('armageddon');
        this.beginTurnResolve();
        break;
      }

      case 32: // Kamikaze
      {
        aw.hp = 0;
        aw.alive = false;
        this.explodeAt(aw.x, aw.y, 50, 75, 32, aw.team);
        this.beginTurnResolve();
        break;
      }

      case 34: // Skip Go
        this.beginTurnResolve();
        break;

      case 35: // Surrender
        for (const w of this.worms) {
          if (w.team === aw.team) {
            w.alive = false;
            w.hp = 0;
          }
        }
        this.beginTurnResolve();
        break;

      default: // Fallback tactical shot
        this.projectiles.push({
          kind: 0, ownerTeam: aw.team, ownerWorm: aw.id,
          x: aw.x + dirX * 10, y: aw.y - 4 + dirY * 10,
          vx: dirX * speedBase, vy: dirY * speedBase,
          gravity: 0.45, windScale: 0.25, bounces: 0, fuseMs: 8000,
        });
        this.audio?.playSfx?.('launch');
        this.beginRetreatOrResolve(2000);
        break;
    }
  }

  traceBulletHit(shooter, sx, sy, vx, vy, dmg, blastR) {
    let px = sx;
    let py = sy;
    const stepX = vx / 4;
    const stepY = vy / 4;
    for (let s = 0; s < 120; s++) {
      px += stepX;
      py += stepY;
      if (py >= WATER_Y || px < MARGIN_X || px > MARGIN_X + MASK_W) break;
      if (this.isSolid(px, py)) {
        this.carveCrater(px, py, blastR);
        this.spawnExplosionParticles(px, py, blastR, '#ffaa44');
        return;
      }
      for (const w of this.worms) {
        if (w.alive && w.id !== shooter.id && Math.hypot(w.x - px, w.y - py) < 10) {
          this.damageWorm(w, dmg, stepX >= 0 ? 3.5 : -3.5, -2.5, shooter.team);
          this.spawnExplosionParticles(px, py, 10, '#ff4444');
          return;
        }
      }
      for (const d of this.drums) {
        if (d.alive && Math.hypot(d.x - px, d.y - py) < 12) {
          d.alive = false;
          this.explodeAt(d.x, d.y, 40, 60, 4, shooter.team);
          return;
        }
      }
    }
  }

  applyMeleeHit(hx, hy, radius, baseDmg, attacker, halveHp = false, superBat = false) {
    for (const w of this.worms) {
      if (!w.alive || w.id === attacker.id) continue;
      if (Math.hypot(w.x - hx, w.y - hy) <= radius) {
        const dmg = halveHp ? Math.max(1, Math.floor(w.hp * 0.5)) : baseDmg;
        const kx = attacker.dir * (superBat ? 8.2 : 3.8);
        const ky = superBat ? -5.8 : -3.4;
        this.damageWorm(w, dmg, kx, ky, attacker.team);
      }
    }
    this.spawnExplosionParticles(hx, hy, 16, '#ffffff');
  }

  beginRetreatOrResolve(retreatMs) {
    this.shotgunSecondShotDone = false;
    if (retreatMs > 0 && this.projectiles.some((p) => p.bounces > 0 || p.kind === 4 || p.kind === 31)) {
      this.phase = 'RETREAT';
      this.retreatMsLeft = retreatMs;
    } else {
      this.phase = 'RESOLVE';
      this.phaseTimerMs = 600;
    }
  }

  beginTurnResolve() {
    this.shotgunSecondShotDone = false;
    this.phase = 'RESOLVE';
    this.phaseTimerMs = 500;
  }

  // ==================== PHYSICS STEP ====================

  stepWormsPhysics(dtMs) {
    for (const w of this.worms) {
      if (!w.alive) continue;

      if (w.y >= WATER_Y) {
        w.alive = false;
        w.hp = 0;
        w.drowned = true;
        this.spawnSplashParticles(w.x, WATER_Y);
        this.audio?.playSfx?.('splash');
        continue;
      }

      if (w.state !== 'JETPACK') {
        if (w.parachuteOpen && w.vy > 1.2) {
          w.vy = 1.2;
          w.vx += (this.windDisplay * 0.008);
        } else if (!w.onGround) {
          w.vy = Math.min(9.5, w.vy + 0.42);
        }
      }

      // Horizontal step
      const stepsX = Math.max(1, Math.ceil(Math.abs(w.vx) / 2));
      for (let s = 0; s < stepsX; s++) {
        const nx = w.x + w.vx / stepsX;
        if (nx < MARGIN_X + 4 || nx > MARGIN_X + MASK_W - 4) {
          w.vx = -w.vx * 0.4;
          break;
        }
        if (!this.checkBoxSolid(nx - 5, w.y - 8, 10, 14)) {
          w.x = nx;
        } else {
          w.vx = -w.vx * 0.35;
          break;
        }
      }

      // Vertical step
      const prevGround = w.onGround;
      w.onGround = false;
      const stepsY = Math.max(1, Math.ceil(Math.abs(w.vy) / 2));
      for (let s = 0; s < stepsY; s++) {
        const ny = w.y + w.vy / stepsY;
        if (ny >= WATER_Y) {
          w.y = WATER_Y;
          break;
        }
        if (!this.checkBoxSolid(w.x - 5, ny - 8, 10, 15)) {
          w.y = ny;
        } else {
          if (w.vy > 0) {
            if (w.vy > 6.4 && !w.parachuteOpen) {
              const fallDmg = Math.round((w.vy - 5.5) * 6);
              if (fallDmg > 0) this.damageWorm(w, fallDmg, 0, -1, -1);
            }
            w.onGround = true;
            w.parachuteOpen = false;
            w.vx *= 0.55;
            if (Math.abs(w.vx) < 0.2) w.vx = 0;
          }
          w.vy = 0;
          break;
        }
      }

      // Check resting support 1px under feet
      if (w.onGround === false && Math.abs(w.vy) < 0.1 && this.checkBoxSolid(w.x - 5, w.y - 6, 10, 16)) {
        w.onGround = true;
        w.vx *= 0.6;
      }

      if (w.state === 'JUMP' && w.onGround && !prevGround) {
        w.state = 'IDLE';
      }
      if (w.state === 'IDLE' || w.state === 'WALK') {
        w.animTick++;
      }

      // Check crate pickup
      for (let i = this.crates.length - 1; i >= 0; i--) {
        const cr = this.crates[i];
        if (Math.hypot(cr.x - w.x, cr.y - w.y) < 16) {
          this.pickupCrate(w, cr);
          this.crates.splice(i, 1);
        }
      }
    }
  }

  pickupCrate(worm, crate) {
    this.audio?.playSfx?.('crate');
    if (crate.kind === 'health') {
      worm.hp = Math.min(999, worm.hp + 25);
      worm.poisoned = 0;
      this.spawnPopup(worm.x, worm.y - 24, '+25 HP', '#55ff66');
    } else if (crate.kind === 'coin') {
      if (worm.team === 0 && !this.mp.enabled) {
        this.save.coins += 25;
        this.matchStats.coinsEarned += 25;
      }
      this.spawnPopup(worm.x, worm.y - 24, '+25 COIN', '#ffd84c');
    } else {
      const bonusWeapons = [6, 7, 10, 11, 15, 16, 17, 19, 25, 28, 29];
      const pick = bonusWeapons[this.randRange(0, bonusWeapons.length)];
      const team = this.teams[worm.team];
      if (team && team.ammo[pick] >= 0) team.ammo[pick]++;
      this.matchStats.cratesPicked++;
      const wName = this.assets?.locale?.getString?.(345 + pick) || `WEAPON #${pick}`;
      this.spawnPopup(worm.x, worm.y - 24, `+1 ${wName}`, '#66d8ff');
    }
  }

  stepProjectiles(dtMs) {
    for (let i = this.projectiles.length - 1; i >= 0; i--) {
      const p = this.projectiles[i];
      this.followEntity = p;

      p.fuseMs -= dtMs;
      if (p.fuseMs <= 0) {
        this.detonateProjectile(p, i);
        continue;
      }

      // Super Sheep air control
      if (p.flyMode && this.canLocalControlActiveTeam()) {
        if ((this.heldKeys & MASK_LEFT) !== 0) p.vx -= 0.25;
        if ((this.heldKeys & MASK_RIGHT) !== 0) p.vx += 0.25;
        if ((this.heldKeys & MASK_UP) !== 0) p.vy -= 0.25;
        if ((this.heldKeys & MASK_DOWN) !== 0) p.vy += 0.25;
        const mag = Math.hypot(p.vx, p.vy) || 1;
        p.vx = (p.vx / mag) * 4.5;
        p.vy = (p.vy / mag) * 4.5;
        if ((this.pressedKeys & MASK_FIRE) !== 0) {
          this.detonateProjectile(p, i);
          continue;
        }
      } else if (p.homingTicks !== undefined) {
        if (p.homingTicks > 0) p.homingTicks--;
        else {
          const dx = p.targetX - p.x;
          const dy = p.targetY - p.y;
          const d = Math.hypot(dx, dy) || 1;
          p.vx += (dx / d) * 0.52;
          p.vy += (dy / d) * 0.52;
          const mag = Math.hypot(p.vx, p.vy) || 1;
          if (mag > 6.5) {
            p.vx = (p.vx / mag) * 6.5;
            p.vy = (p.vy / mag) * 6.5;
          }
        }
      }

      // Gravity & wind (y.bu scaled per weapon)
      p.vy += p.gravity * 0.46;
      p.vx += (this.windDisplay * 0.0022) * p.windScale;

      if (this.tickCount % 2 === 0) {
        this.particles.push({ kind: 'smoke', x: p.x, y: p.y, ttl: 280, maxTtl: 280, color: '#eeeeee' });
      }

      // Sub-step collision
      const subSteps = Math.max(1, Math.ceil(Math.hypot(p.vx, p.vy) / 3));
      let hit = false;
      for (let s = 0; s < subSteps; s++) {
        p.x += p.vx / subSteps;
        p.y += p.vy / subSteps;

        if (p.y >= WATER_Y) {
          this.spawnSplashParticles(p.x, WATER_Y);
          this.audio?.playSfx?.('splash');
          this.projectiles.splice(i, 1);
          hit = true;
          break;
        }

        // Worm proximity collision (except dynamite/sheep on owner launch frame)
        const wormHit = this.worms.find((w) => w.alive && (w.id !== p.ownerWorm || p.fuseMs < 4500) && Math.hypot(w.x - p.x, w.y - p.y) < 10);
        if (wormHit && p.bounces === 0 && !p.walker) {
          this.detonateProjectile(p, i);
          hit = true;
          break;
        }

        if (this.isSolid(p.x, p.y)) {
          if (p.drillTicks > 0) {
            p.drillTicks--;
            this.carveCrater(p.x, p.y, 10);
            continue;
          }
          if (p.donkeySmash) {
            this.explodeAt(p.x, p.y, 34, 48, 28, p.ownerTeam);
            p.bounces--;
            p.vy = -2;
            if (p.bounces <= 0) {
              this.projectiles.splice(i, 1);
              hit = true;
              break;
            }
            continue;
          }
          if (p.bounces > 0 && !p.impactDetonate) {
            const el = p.elasticity ?? 0.55;
            if (this.isSolid(p.x - p.vx / subSteps, p.y)) {
              p.vy = -p.vy * el;
              p.vx *= 0.85;
            } else {
              p.vx = -p.vx * el;
            }
            p.y -= 2;
            if (p.walker) {
              p.vx = (p.dir || 1) * (p.kind === 22 ? 3.2 : 2.1);
            }
            this.audio?.playSfx?.('bounce');
          } else {
            this.detonateProjectile(p, i);
            hit = true;
            break;
          }
        }
      }
      if (hit) continue;
    }
    if (this.projectiles.length === 0 && this.followEntity && this.followEntity.kind !== undefined) {
      this.followEntity = null;
    }
  }

  detonateProjectile(p, arrayIdx) {
    this.projectiles.splice(arrayIdx, 1);
    const stat = WEAPON_STATS[p.kind] || WEAPON_STATS[0];
    const dmg = stat[1] || 40;
    const rad = stat[2] || 38;
    this.explodeAt(p.x, p.y, rad, dmg, p.kind, p.ownerTeam);

    // Special split effects matching ah.java:8160-8460!
    if (p.kind === 7 || p.kind === 25) { // Cluster Bomb / Banana Bomb split!
      for (let k = 0; k < 5; k++) {
        const bvx = (k < 2 ? -1 : 1) * (1.8 + (k % 3) * 0.9);
        const bvy = -(3.2 + (k % 2) * 1.4);
        this.projectiles.push({
          kind: p.kind === 25 ? 250 : 70, // Bomblet subkind
          ownerTeam: p.ownerTeam,
          ownerWorm: p.ownerWorm,
          x: p.x,
          y: p.y - 4,
          vx: bvx,
          vy: bvy,
          gravity: 0.42,
          windScale: 0,
          bounces: p.kind === 25 ? 2 : 0,
          impactDetonate: p.kind !== 25,
          fuseMs: 1400,
        });
      }
    } else if (p.kind === 11 || p.kind === 13) { // Napalm / Petrol Flames!
      for (let f = -2; f <= 2; f++) {
        this.flames.push({
          x: p.x + f * 6,
          y: p.y - 2,
          vx: f * 0.65,
          vy: -2.2,
          ttl: 3800,
        });
      }
    }
  }

  explodeAt(wx, wy, radius, maxDamage, weaponKind, ownerTeam) {
    this.carveCrater(wx, wy, radius);
    this.spawnExplosionParticles(wx, wy, radius, '#ff9922');
    this.shakeTicks = 8;
    this.shakeMag = Math.min(8, Math.round(radius / 7));
    this.audio?.playSfx?.(radius >= 55 ? 'bigBoom' : 'boom');

    // Damage & knockback all worms in blast radius (ah.java:5800-5910)
    const dmgRadius = radius * 1.5;
    for (const w of this.worms) {
      if (!w.alive) continue;
      const dx = w.x - wx;
      const dy = w.y - wy;
      const dist = Math.hypot(dx, dy);
      if (dist <= dmgRadius) {
        const factor = 1 - dist / dmgRadius;
        let dmg = Math.max(1, Math.round(maxDamage * factor));
        if (w.markedForDeath) dmg *= 2;
        const angle = Math.atan2(dy - 8, dx || 0.1);
        const force = (4.5 + radius * 0.08) * factor;
        this.damageWorm(w, dmg, Math.cos(angle) * force, -Math.abs(Math.sin(angle) * force) - 2.2, ownerTeam);
      }
    }

    // Chain-react nearby drums & mines!
    for (const d of this.drums) {
      if (d.alive && Math.hypot(d.x - wx, d.y - wy) < dmgRadius) {
        d.alive = false;
        this.explodeAt(d.x, d.y, 40, 55, 4, ownerTeam);
      }
    }
    for (const m of this.mines) {
      if (!m.triggered && Math.hypot(m.x - wx, m.y - wy) < dmgRadius) {
        m.triggered = true;
        m.fuseMs = 400;
      }
    }
  }

  damageWorm(worm, dmg, kVx, kVy, attackerTeam) {
    if (!worm.alive) return;
    worm.hp = Math.max(0, worm.hp - dmg);
    worm.vx += kVx;
    worm.vy += kVy;
    worm.onGround = false;
    worm.state = 'FALL';
    this.spawnPopup(worm.x, worm.y - 22, `-${dmg}`, '#ff4433');
    this.audio?.playSfx?.('hurt');

    if (attackerTeam === 0 && worm.team !== 0) {
      this.matchStats.damageDealt += dmg;
    }

    if (worm.hp <= 0) {
      worm.alive = false;
      worm.state = 'DEAD';
      this.graves.push({ x: worm.x, y: worm.y, team: worm.team });
      if (attackerTeam === 0 && worm.team !== 0 && !this.mp.enabled) {
        this.matchStats.kills++;
        this.matchStats.coinsEarned += 20;
        this.save.coins += 20;
        this.save.totalKills++;
      }
      // Small death explosion (ah.java:6650)
      this.carveCrater(worm.x, worm.y, 20);
      this.spawnExplosionParticles(worm.x, worm.y, 22, '#ff6644');
      this.audio?.playSfx?.('wormDie');
    }
  }

  stepCratesAndMines(dtMs) {
    for (let i = this.crates.length - 1; i >= 0; i--) {
      const cr = this.crates[i];
      if (!cr.onGround) {
        cr.vy = Math.min(2.2, cr.vy + 0.15);
        cr.y += cr.vy;
        if (cr.y >= WATER_Y) {
          this.crates.splice(i, 1);
          continue;
        }
        if (this.isSolid(cr.x, cr.y + 8)) {
          cr.onGround = true;
          cr.vy = 0;
        }
      } else if (!this.isSolid(cr.x, cr.y + 9)) {
        cr.onGround = false;
      }
    }

    for (let i = this.mines.length - 1; i >= 0; i--) {
      const m = this.mines[i];
      if (!this.isSolid(m.x, m.y + 5)) {
        m.vy += 0.35;
        m.y += m.vy;
        if (m.y >= WATER_Y) {
          this.mines.splice(i, 1);
          continue;
        }
      } else {
        m.vy = 0;
      }

      if (!m.triggered) {
        const nearWorm = this.worms.some((w) => w.alive && Math.hypot(w.x - m.x, w.y - m.y) < 22);
        if (nearWorm) {
          m.triggered = true;
          this.audio?.playSfx?.('mineBeep');
        }
      } else {
        m.fuseMs -= dtMs;
        if (m.fuseMs <= 0) {
          this.mines.splice(i, 1);
          this.explodeAt(m.x, m.y, 38, 45, 4, -1);
        }
      }
    }

    for (let i = this.drums.length - 1; i >= 0; i--) {
      const d = this.drums[i];
      if (!d.alive) {
        this.drums.splice(i, 1);
        continue;
      }
      if (!this.isSolid(d.x, d.y + 9)) {
        d.vy += 0.35;
        d.y += d.vy;
        if (d.y >= WATER_Y) this.drums.splice(i, 1);
      } else {
        d.vy = 0;
      }
    }
  }

  stepFlames(dtMs) {
    for (let i = this.flames.length - 1; i >= 0; i--) {
      const f = this.flames[i];
      f.ttl -= dtMs;
      if (f.ttl <= 0 || f.y >= WATER_Y) {
        this.flames.splice(i, 1);
        continue;
      }
      f.vy = Math.min(2.5, f.vy + 0.25);
      f.x += f.vx;
      f.y += f.vy;
      if (this.isSolid(f.x, f.y)) {
        f.vy = 0;
        f.vx *= 0.4;
        if (this.tickCount % 6 === 0) this.carveCrater(f.x, f.y, 5);
      }
      if (this.tickCount % 8 === 0) {
        for (const w of this.worms) {
          if (w.alive && Math.hypot(w.x - f.x, w.y - f.y) < 12) {
            this.damageWorm(w, 3, 0, -1.5, -1);
          }
        }
      }
    }
  }

  stepParticlesAndPopups(dtMs) {
    for (let i = this.particles.length - 1; i >= 0; i--) {
      const p = this.particles[i];
      p.ttl -= dtMs;
      if (p.ttl <= 0) {
        this.particles.splice(i, 1);
        continue;
      }
      if (p.vx) p.x += p.vx;
      if (p.vy) p.y += p.vy;
      if (p.gravity) p.vy += p.gravity;
    }
    for (let i = this.popups.length - 1; i >= 0; i--) {
      const pop = this.popups[i];
      pop.ttl -= dtMs;
      pop.y -= 0.35;
      if (pop.ttl <= 0) this.popups.splice(i, 1);
    }
  }

  spawnFlameParticle(x, y) {
    this.particles.push({ kind: 'spark', x, y, vx: (this.randRange(-10, 11)) * 0.1, vy: 1.5, ttl: 220, maxTtl: 220, color: '#ff9933' });
  }

  spawnExplosionParticles(x, y, radius, color) {
    this.particles.push({ kind: 'blast', x, y, radius, ttl: 360, maxTtl: 360, color });
    const n = Math.min(18, Math.max(6, Math.round(radius / 4)));
    for (let i = 0; i < n; i++) {
      const ang = (Math.PI * 2 * i) / n + (this.randRange(-10, 11) * 0.05);
      const spd = 1.5 + (i % 3) * 1.4;
      this.particles.push({
        kind: 'spark',
        x, y,
        vx: Math.cos(ang) * spd,
        vy: Math.sin(ang) * spd - 1.0,
        gravity: 0.14,
        ttl: 450,
        maxTtl: 450,
        color: i % 2 === 0 ? '#ffee55' : '#ff6622',
      });
    }
  }

  spawnSplashParticles(x, y) {
    for (let i = 0; i < 8; i++) {
      this.particles.push({
        kind: 'spark',
        x: x + (i - 3.5) * 3,
        y,
        vx: (i - 3.5) * 0.5,
        vy: -2.6 - (i % 3) * 0.7,
        gravity: 0.2,
        ttl: 450,
        maxTtl: 450,
        color: '#88ddff',
      });
    }
  }

  spawnPopup(x, y, text, color) {
    this.popups.push({ x, y, text, color, ttl: 1200, maxTtl: 1200 });
  }

  // ==================== AI SOLVER (ah.java:7092-7491) ====================

  stepAI(aw, dtMs) {
    if (this.dummiesOnly) {
      this.beginTurnResolve();
      return;
    }

    if (!this.aiPlan) {
      this.aiPlan = this.buildAIShotPlan(aw);
      this.aiThinkMs = 600;
    }
    if (this.aiThinkMs > 0) {
      this.aiThinkMs -= dtMs;
      return;
    }

    // Face & aim toward solved angle
    aw.dir = this.aiPlan.dir;
    const diff = this.aiPlan.angle - aw.aimAngle;
    if (Math.abs(diff) > 3) {
      aw.aimAngle += diff > 0 ? 3 : -3;
      return;
    }
    aw.aimAngle = this.aiPlan.angle;
    aw.weaponId = this.aiPlan.weaponId;
    this.selectedWeapon = aw.weaponId;

    if (aw.weaponId === 5) { // Air Strike
      this.targetCursor = { x: this.aiPlan.targetX, y: this.aiPlan.targetY, dir: this.aiPlan.dir };
      this.fireCurrentWeapon();
      return;
    }

    if (!this.charging) {
      this.charging = true;
      this.chargePower = 12;
      return;
    }
    this.chargePower += 4.2;
    if (this.chargePower >= this.aiPlan.power) {
      this.chargePower = this.aiPlan.power;
      this.fireCurrentWeapon();
    }
  }

  buildAIShotPlan(aw) {
    const enemies = this.worms.filter((w) => w.alive && w.team !== aw.team);
    if (enemies.length === 0) return { dir: 1, angle: 60, power: 50, weaponId: 34, targetX: aw.x, targetY: aw.y };

    // Pick closest enemy
    let target = enemies[0];
    let bestD = Infinity;
    for (const e of enemies) {
      const d = Math.hypot(e.x - aw.x, e.y - aw.y);
      if (d < bestD) { bestD = d; target = e; }
    }

    const dir = target.x >= aw.x ? 1 : -1;
    // Try Shotgun if close & clear line of sight
    if (bestD < 95 && Math.abs(target.y - aw.y) < 35) {
      const ang = Math.max(25, Math.min(140, Math.round((Math.atan2(Math.abs(target.x - aw.x), aw.y - target.y) * 180) / Math.PI)));
      return { dir, angle: ang, power: 60, weaponId: 12, targetX: target.x, targetY: target.y };
    }

    // Try Air Strike if ceiling blocked & available
    const team = this.teams[aw.team];
    if (team?.ammo[5] > 0 && this.randRange(0, 10) < 3) {
      return { dir, angle: 60, power: 60, weaponId: 5, targetX: target.x, targetY: target.y };
    }

    // Ballistic test grid over (angle, power) with Bazooka (0) or Grenade (1)
    const chosenWeapon = (team?.ammo[1] !== 0 && bestD < 140) ? 1 : 0;
    let bestPlan = { dir, angle: 48, power: Math.min(92, Math.max(28, Math.round(bestD * 0.36))), weaponId: chosenWeapon, targetX: target.x, targetY: target.y };
    let bestDist = Infinity;

    for (const ang of [30, 42, 54, 66, 78, 92]) {
      for (const pwr of [30, 46, 62, 78, 94]) {
        const err = this.simulateBallisticDist(aw, dir, ang, pwr, chosenWeapon, target.x, target.y);
        if (err < bestDist) {
          bestDist = err;
          bestPlan = { dir, angle: ang, power: pwr, weaponId: chosenWeapon, targetX: target.x, targetY: target.y };
        }
      }
    }
    return bestPlan;
  }

  simulateBallisticDist(aw, dir, angle, power, weaponId, tx, ty) {
    const dirX = dir * Math.sin((angle * Math.PI) / 180);
    const dirY = -Math.cos((angle * Math.PI) / 180);
    const spd = 3.2 + (power / 100) * 8.4;
    let x = aw.x + dirX * 10;
    let y = aw.y - 4 + dirY * 10;
    let vx = dirX * spd;
    let vy = dirY * spd;
    const windScale = weaponId === 0 ? 1 : 0;
    let minD = Infinity;

    for (let t = 0; t < 48; t++) {
      vy += 0.46;
      vx += this.windDisplay * 0.0022 * windScale;
      x += vx;
      y += vy;
      const d = Math.hypot(x - tx, y - ty);
      if (d < minD) minD = d;
      if (d < 18) return d;
      if (y >= WATER_Y || this.isSolid(x, y)) return minD;
    }
    return minD;
  }

  // ==================== TURN ADVANCE & VICTORY ====================

  finishTurnAndAdvance() {
    // Worship periodic damage
    if (this.worshipTeam >= 0) {
      for (const w of this.worms) {
        if (w.alive && w.team !== this.worshipTeam) {
          this.damageWorm(w, 6, 0, -1, this.worshipTeam);
        }
      }
    }

    // Check Body Count next wave!
    const livingTeams = this.teams.filter((t) => this.worms.some((w) => w.team === t.id && w.alive));
    if (this.mode === 'bodycount' && livingTeams.length === 1 && livingTeams[0].id === 0) {
      this.bodyCountWave++;
      for (let k = 0; k < 2 + (this.bodyCountWave % 3); k++) {
        const sx = this.randRange(MARGIN_X + 60, MARGIN_X + MASK_W - 60);
        const sy = this.findSurfaceY(sx, MARGIN_Y + 4);
        this.worms.push({
          id: this.worms.length,
          team: 1,
          name: `WAVE-${this.bodyCountWave}`,
          x: sx,
          y: (sy > 0 ? sy : 150) - 10,
          vx: 0, vy: 0, dir: -1, aimAngle: 60,
          hp: 35 + this.bodyCountWave * 5, maxHp: 35 + this.bodyCountWave * 5,
          alive: true, onGround: false, poisoned: 0, markedForDeath: false,
          weaponId: 0, state: 'IDLE', animTick: 0, parachuteOpen: false, jetpackFuel: 30,
        });
      }
    } else if (!this.dummiesOnly && livingTeams.length <= 1) {
      const winnerTeam = livingTeams.length === 1 ? livingTeams[0].id : -1;
      this.completeMatch(winnerTeam);
      return;
    }

    // Spawn Crate chance
    if (this.crateFreq > 0 && (this.turnNumber % (this.crateFreq === 2 ? 1 : 2) === 0)) {
      const cx = this.randRange(MARGIN_X + 55, MARGIN_X + MASK_W - 55);
      const r = this.randRange(0, 10);
      const kind = r < 4 ? 'health' : r < 8 ? 'weapon' : 'coin';
      this.crates.push({ x: cx, y: MARGIN_Y + 6, vy: 0, kind, onGround: false });
    }

    // Advance turn
    this.turnNumber++;
    if (this.turnNumber >= this.suddenDeathTurns && !this.dummiesOnly) {
      this.suddenDeathWaterRise = true;
      this.waterY = Math.max(MARGIN_Y + 90, this.waterY - 12);
    }

    for (let step = 1; step <= this.numTeams; step++) {
      const cand = (this.activeTeamIdx + step) % this.numTeams;
      if (this.worms.some((w) => w.team === cand && w.alive)) {
        this.activeTeamIdx = cand;
        break;
      }
    }

    this.rollWind();
    const aw = this.selectNextWormForTeam(this.activeTeamIdx);
    this.phase = 'INTRO';
    this.phaseTimerMs = 1100;
    this.bannerText = aw ? `${this.teams[this.activeTeamIdx].name}` : '';
    this.bannerMs = 1100;
    this.snapCameraToActive();
    this.audio?.playSfx?.('turnStart');
  }

  completeMatch(winnerTeam) {
    this.matchStats.winnerTeam = winnerTeam;
    if (!this.mp.enabled) {
      this.save.totalMatches++;
      if (winnerTeam === 0) {
        this.save.totalWins++;
        const winBonus = this.mode === 'campaign' ? 100 : 50;
        this.save.coins += winBonus;
        this.matchStats.coinsEarned += winBonus;
        if (this.mode === 'campaign') {
          this.save.campaignUnlockedBitmask |= (1 << Math.min(27, this.campaignStage + 1));
          this.save.campaignStars[this.campaignStage] = true;
        }
      }
      if (this.save.totalKills >= 1) this.save.achievementsMask |= 1;
      if (this.save.totalWins >= 1) this.save.achievementsMask |= 2;
      if (this.save.totalCrates >= 5 || this.matchStats.cratesPicked > 0) this.save.achievementsMask |= 4;
      writePersistentSave(this.save);
    }
    this.matchResult = winnerTeam === 0 ? 'WIN' : winnerTeam === -1 ? 'DRAW' : 'LOSE';
    this.o = 9; // Results screen (y.java:822)
    this.audio?.playSfx?.(winnerTeam === 0 ? 'victory' : 'defeat');
  }

  updateCamera() {
    let tx = this.camX;
    let ty = this.camY;
    if (this.binocularsMode) {
      if ((this.heldKeys & MASK_LEFT) !== 0) tx -= 6;
      if ((this.heldKeys & MASK_RIGHT) !== 0) tx += 6;
      if ((this.heldKeys & MASK_UP) !== 0) ty -= 6;
      if ((this.heldKeys & MASK_DOWN) !== 0) ty += 6;
    } else if (this.phase === 'TARGET') {
      tx = this.targetCursor.x - SCREEN_W * 0.5;
      ty = this.targetCursor.y - SCREEN_H * 0.5;
    } else if (this.followEntity) {
      tx = this.followEntity.x - SCREEN_W * 0.5;
      ty = this.followEntity.y - SCREEN_H * 0.5;
    } else {
      const aw = this.getActiveWorm();
      if (aw) {
        tx = aw.x - SCREEN_W * 0.5 + aw.dir * 26;
        ty = aw.y - SCREEN_H * 0.52;
      }
    }
    this.camX = Math.max(0, Math.min(WORLD_W - SCREEN_W, tx));
    this.camY = Math.max(0, Math.min(WORLD_H - SCREEN_H, ty));
    this.smoothCamX += (this.camX - this.smoothCamX) * 0.25;
    this.smoothCamY += (this.camY - this.smoothCamY) * 0.25;
  }

  // ==================== MENUS & NAVIGATION (y.java:4150-4550) ====================

  getMenuItems() {
    switch (this.cm) {
      case 0: // Main Menu + Multiplayer button cleanly integrated!
        return [
          { id: 'PLAY', labelId: 13 },
          { id: 'MP', customLabel: this.isVI() ? 'CHƠI ONLINE (2-4)' : 'MULTIPLAYER (2-4)' },
          { id: 'SHOP', labelId: 25 },
          { id: 'ACHIEVEMENTS', labelId: 23 },
          { id: 'OPTIONS', labelId: 27 },
          { id: 'HELP', labelId: 16 },
          { id: 'ABOUT', labelId: 24 },
        ];
      case 1: // Play submenu
        return [
          { id: 'CAMPAIGN', labelId: 17 },
          { id: 'QUICK', labelId: 37 },
          { id: 'CUSTOM', labelId: 38 },
          { id: 'TRAINING', labelId: 10 },
          { id: 'BODYCOUNT', labelId: 123 },
        ];
      case 16: // Campaign 28 stages
        return Array.from({ length: 28 }, (_, i) => ({
          id: `STAGE_${i}`,
          stageIdx: i,
          unlocked: (this.save.campaignUnlockedBitmask & (1 << i)) !== 0 || i === 0,
        }));
      case 2: // Custom Match Config
        return [
          { id: 'CFG_TEAMS', key: 'numTeams', value: `${this.customCfg.numTeams}` },
          { id: 'CFG_WORMS', key: 'wormsPerTeam', value: `${this.customCfg.wormsPerTeam}` },
          { id: 'CFG_THEME', key: 'themeId', value: `${this.customCfg.themeId + 1}` },
          { id: 'CFG_MAP', key: 'mapStyle', value: ['HILLS', 'ISLAND', 'TWIN PEAKS', 'CAVERN', 'FORT'][this.customCfg.mapStyle] },
          { id: 'CFG_TIME', key: 'turnTime', value: `${this.customCfg.turnTime}S` },
          { id: 'CFG_START', labelId: 34 },
        ];
      case 11: // Options
        return [
          { id: 'OPT_SOUND', labelId: 18, val: this.save.soundOn ? 35 : 36 },
          { id: 'OPT_TIMER', labelId: 20, val: this.save.timerPause ? 35 : 36 },
          { id: 'OPT_LANG', labelId: 33, valText: this.assets?.locale?.getLanguageDisplayName(this.assets.locale.getLanguageIndex()) || 'EN' },
          { id: 'OPT_RESET', labelId: 72 },
        ];
      case 13: // Choose Language
        return [
          { id: 'LANG_0', langIdx: 0, text: 'English' },
          { id: 'LANG_1', langIdx: 1, text: 'ไทย' },
          { id: 'LANG_2', langIdx: 2, text: 'Tiếng Việt' },
        ];
      case 29: // Shop
        return SHOP_PACKS.map((p) => ({
          id: `BUY_${p.id}`,
          pack: p,
        }));
      case 26: // Achievements
        return Array.from({ length: ACHIEVEMENTS_COUNT }, (_, i) => ({
          id: `ACH_${i}`,
          achIdx: i,
          unlocked: (this.save.achievementsMask & (1 << Math.min(30, i))) !== 0,
        }));
      default:
        return [];
    }
  }

  isVI() {
    return this.assets?.locale?.activeCode === 'vi';
  }

  handleMenuInput() {
    if (this.o === 9) { // Results Screen
      if ((this.pressedKeys & (MASK_FIRE | KEY.LSK | KEY.RSK | KEY.BACK)) !== 0) {
        this.o = 4;
        this.cm = this.mode === 'campaign' ? 16 : 0;
        this.cn = 0;
        this.audio?.playSfx?.('uiSelect');
      }
      return;
    }

    if (this.o === 7) { // Pause Menu
      const pauseItems = [0, 1, 2, 3]; // Resume, Restart, Options, Main Menu
      if ((this.pressedKeys & MASK_UP) !== 0) {
        this.cn = (this.cn + pauseItems.length - 1) % pauseItems.length;
        this.audio?.playSfx?.('uiMove');
      } else if ((this.pressedKeys & MASK_DOWN) !== 0) {
        this.cn = (this.cn + 1) % pauseItems.length;
        this.audio?.playSfx?.('uiMove');
      } else if ((this.pressedKeys & (KEY.RSK | KEY.BACK)) !== 0) {
        this.o = 6;
      } else if ((this.pressedKeys & (MASK_FIRE | KEY.LSK)) !== 0) {
        this.audio?.playSfx?.('uiSelect');
        if (this.cn === 0) this.o = 6;
        else if (this.cn === 1) this.startMatch({ seed: this.matchSeed, themeId: this.themeId, mapStyle: this.mapStyle, numTeams: this.numTeams, wormsPerTeam: this.wormsPerTeam, aiMask: 0b0010, turnTime: this.turnSecondsMax });
        else if (this.cn === 2) { this.o = 4; this.cm = 11; this.cn = 0; }
        else { this.o = 4; this.cm = 0; this.cn = 0; }
      }
      return;
    }

    const items = this.getMenuItems();
    const len = items.length;
    if (len > 0) {
      if ((this.pressedKeys & MASK_UP) !== 0) {
        this.cn = (this.cn + len - 1) % len;
        this.audio?.playSfx?.('uiMove');
      } else if ((this.pressedKeys & MASK_DOWN) !== 0) {
        this.cn = (this.cn + 1) % len;
        this.audio?.playSfx?.('uiMove');
      }
    }

    // Left / Right adjusters in Custom Match / Campaign / Options
    if ((this.pressedKeys & (MASK_LEFT | MASK_RIGHT)) !== 0) {
      const dir = (this.pressedKeys & MASK_RIGHT) !== 0 ? 1 : -1;
      if (this.cm === 2 && items[this.cn]) {
        const cur = items[this.cn];
        if (cur.id === 'CFG_TEAMS') this.customCfg.numTeams = Math.max(2, Math.min(4, this.customCfg.numTeams + dir));
        else if (cur.id === 'CFG_WORMS') this.customCfg.wormsPerTeam = Math.max(1, Math.min(4, this.customCfg.wormsPerTeam + dir));
        else if (cur.id === 'CFG_THEME') this.customCfg.themeId = (this.customCfg.themeId + 5 + dir) % 5;
        else if (cur.id === 'CFG_MAP') this.customCfg.mapStyle = (this.customCfg.mapStyle + 5 + dir) % 5;
        else if (cur.id === 'CFG_TIME') {
          const opts = [30, 45, 60, 90];
          const idx = (opts.indexOf(this.customCfg.turnTime) + opts.length + dir) % opts.length;
          this.customCfg.turnTime = opts[idx];
        }
        this.audio?.playSfx?.('uiMove');
      } else if (this.cm === 16) {
        this.cn = (this.cn + len + dir) % len;
        this.audio?.playSfx?.('uiMove');
      }
    }

    // Back / RSK
    if ((this.pressedKeys & (KEY.RSK | KEY.BACK)) !== 0) {
      if (this.cm !== 0 && this.cm !== 13) {
        this.cm = (this.cm === 16 || this.cm === 2) ? 1 : 0;
        this.cn = 0;
        this.audio?.playSfx?.('uiBack');
      }
      return;
    }

    // Confirm / Fire / LSK
    if ((this.pressedKeys & (MASK_FIRE | KEY.LSK)) !== 0) {
      const sel = items[this.cn];
      if (!sel) {
        this.cm = 0;
        this.cn = 0;
        return;
      }
      this.audio?.playSfx?.('uiSelect');
      if (this.cm === 13) {
        this.save.langIdx = sel.langIdx;
        this.assets?.locale?.setLanguage(sel.langIdx);
        writePersistentSave(this.save);
        this.cm = 0;
        this.cn = 0;
        return;
      }
      if (this.cm === 0) {
        if (sel.id === 'PLAY') { this.cm = 1; this.cn = 0; }
        else if (sel.id === 'MP') { this.mp.openLobbyFn?.(); }
        else if (sel.id === 'SHOP') { this.cm = 29; this.cn = 0; }
        else if (sel.id === 'ACHIEVEMENTS') { this.cm = 26; this.cn = 0; }
        else if (sel.id === 'OPTIONS') { this.cm = 11; this.cn = 0; }
        else if (sel.id === 'HELP') { this.cm = 22; this.cn = 0; }
        else if (sel.id === 'ABOUT') { this.cm = 14; this.cn = 0; }
        return;
      }
      if (this.cm === 1) {
        if (sel.id === 'CAMPAIGN') { this.cm = 16; this.cn = 0; }
        else if (sel.id === 'QUICK') this.startQuickMatch();
        else if (sel.id === 'CUSTOM') { this.cm = 2; this.cn = 0; }
        else if (sel.id === 'TRAINING') this.startTraining(0);
        else if (sel.id === 'BODYCOUNT') this.startBodyCount();
        return;
      }
      if (this.cm === 16 && sel.unlocked) {
        this.startCampaignStage(sel.stageIdx);
        return;
      }
      if (this.cm === 2 && sel.id === 'CFG_START') {
        this.startCustomMatch();
        return;
      }
      if (this.cm === 11) {
        if (sel.id === 'OPT_SOUND') {
          this.save.soundOn = !this.save.soundOn;
          this.audio?.setMuted?.(!this.save.soundOn);
        } else if (sel.id === 'OPT_TIMER') {
          this.save.timerPause = !this.save.timerPause;
        } else if (sel.id === 'OPT_LANG') {
          const nextIdx = ((this.assets?.locale?.getLanguageIndex() || 0) + 1) % 3;
          this.save.langIdx = nextIdx;
          this.assets?.locale?.setLanguage(nextIdx);
        } else if (sel.id === 'OPT_RESET') {
          const curLang = this.save.langIdx;
          this.save = { ...DEFAULT_SAVE, langIdx: curLang };
        }
        writePersistentSave(this.save);
        return;
      }
      if (this.cm === 29 && sel.pack) {
        if (this.save.coins >= sel.pack.cost) {
          this.save.coins -= sel.pack.cost;
          this.save.shopStock[sel.pack.weaponId] += sel.pack.qty;
          writePersistentSave(this.save);
          this.audio?.playSfx?.('coin');
        } else {
          this.audio?.playSfx?.('error');
        }
      }
    }
  }

  // ==================== MULTIPLAYER SNAPSHOT SERIALIZATION ====================

  serializeInitPacket() {
    return {
      seed: this.matchSeed,
      themeId: this.themeId,
      mapStyle: this.mapStyle,
      numTeams: this.numTeams,
      wormsPerTeam: this.wormsPerTeam,
      turnSecondsMax: this.turnSecondsMax,
      suddenDeathTurns: this.suddenDeathTurns,
      crateFreq: this.crateFreq,
    };
  }

  serializeSnapshot() {
    return {
      t: this.turnNumber,
      at: this.activeTeamIdx,
      aw: this.activeWormIdx,
      ph: this.phase,
      tml: Math.round(this.turnMsLeft),
      wy: this.waterY,
      wd: this.windDisplay,
      sw: this.selectedWeapon,
      bt: this.bannerText,
      bm: Math.round(this.bannerMs),
      worms: this.worms.map((w) => [
        Math.round(w.x), Math.round(w.y), w.dir, w.aimAngle, w.hp,
        w.alive ? 1 : 0, w.weaponId, w.state, w.parachuteOpen ? 1 : 0, w.markedForDeath ? 1 : 0,
      ]),
      projs: this.projectiles.map((p) => [p.kind, Math.round(p.x), Math.round(p.y), Math.round(p.vx * 10), Math.round(p.vy * 10)]),
      crates: this.crates.map((c) => [Math.round(c.x), Math.round(c.y), c.kind]),
      mines: this.mines.map((m) => [Math.round(m.x), Math.round(m.y), m.triggered ? 1 : 0]),
      drums: this.drums.map((d) => [Math.round(d.x), Math.round(d.y)]),
      graves: this.graves.map((g) => [Math.round(g.x), Math.round(g.y), g.team]),
      craters: this.craterLog.slice(-16),
      res: this.matchResult,
    };
  }

  applySnapshot(snap) {
    if (!snap) return;
    this.o = snap.res ? 9 : 6;
    if (snap.res) this.matchResult = snap.res;
    this.turnNumber = snap.t;
    this.activeTeamIdx = snap.at;
    this.activeWormIdx = snap.aw;
    this.phase = snap.ph;
    this.turnMsLeft = snap.tml;
    this.waterY = snap.wy;
    this.windDisplay = snap.wd;
    this.selectedWeapon = snap.sw;
    this.bannerText = snap.bt || '';
    this.bannerMs = snap.bm || 0;

    if (Array.isArray(snap.worms)) {
      for (let i = 0; i < snap.worms.length && i < this.worms.length; i++) {
        const s = snap.worms[i];
        const w = this.worms[i];
        w.x = s[0]; w.y = s[1]; w.dir = s[2]; w.aimAngle = s[3];
        w.hp = s[4]; w.alive = s[5] === 1; w.weaponId = s[6]; w.state = s[7];
        w.parachuteOpen = s[8] === 1; w.markedForDeath = s[9] === 1;
      }
    }
    this.projectiles = (snap.projs || []).map((p) => ({ kind: p[0], x: p[1], y: p[2], vx: p[3] / 10, vy: p[4] / 10 }));
    this.crates = (snap.crates || []).map((c) => ({ x: c[0], y: c[1], kind: c[2], onGround: true }));
    this.mines = (snap.mines || []).map((m) => ({ x: m[0], y: m[1], triggered: m[2] === 1 }));
    this.drums = (snap.drums || []).map((d) => ({ x: d[0], y: d[1], alive: true }));
    this.graves = (snap.graves || []).map((g) => ({ x: g[0], y: g[1], team: g[2] }));
    if (Array.isArray(snap.craters)) {
      for (const cr of snap.craters) {
        if (cr[0] > this.appliedCraterSeq) {
          this.appliedCraterSeq = cr[0];
          this.carveCrater(cr[1], cr[2], cr[3], false);
        }
      }
    }
    this.followEntity = this.projectiles[0] || null;
    this.updateCamera();
    if (this.phase === 'OVER') this.o = 9;
  }
}
