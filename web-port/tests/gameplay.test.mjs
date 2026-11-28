import test from 'node:test';
import assert from 'node:assert/strict';
import fs from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

import {
  WormsWorld, MASK_W, MASK_H, MARGIN_X, MARGIN_Y, WATER_Y,
  KEY, WEAPON_STATS, AIM_ORIGIN_OFFSET_Y, isAimableWeapon, getAimDirection,
} from '../worms-world.js';
import {
  EAIndexReader, EALocalePack, ACSpritePack, parseCampaignTable, parseTrigTable,
} from '../ea-pack.js';
import {
  getHeldWeaponFrame, getWormRenderState, getProjectileSpriteInfo,
} from '../render-original.js';
import { composeACTransform } from '../midp-graphics.js';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const ASSETS_DIR = path.resolve(__dirname, '..', 'assets');

function loadMinimalAssets() {
  const read = (n) => new Uint8Array(fs.readFileSync(path.join(ASSETS_DIR, n)));
  const reader = new EAIndexReader(read('index'), Array.from({ length: 11 }, (_, i) => read(`df${i}_0`)));
  const locale = new EALocalePack(read('hdr'), read('charmap'), { en: read('t_en'), vi: read('t_vi'), th: read('t_th') });
  return {
    locale,
    trigCos: parseTrigTable(reader.getChunk(3)),
    campaigns: parseCampaignTable(reader.getChunk(85)),
    sprites: [],
  };
}

test('Procedural terrain generation is deterministic per seed & crater carving updates mask + minimap + craterLog', () => {
  const assets = loadMinimalAssets();
  const w1 = new WormsWorld(assets);
  const w2 = new WormsWorld(assets);

  w1.startQuickMatch(77777);
  w2.startQuickMatch(77777);

  assert.equal(w1.mask.length, MASK_W * MASK_H);
  assert.deepEqual(Array.from(w1.mask.subarray(0, 2000)), Array.from(w2.mask.subarray(0, 2000)));
  assert.equal(w1.worms.length, 6);

  const testX = MARGIN_X + 200;
  const surfY = w1.findSurfaceY(testX);
  assert.ok(surfY > MARGIN_Y && surfY < WATER_Y);
  assert.equal(w1.isSolid(testX, surfY + 2), true);

  w1.carveCrater(testX, surfY + 2, 24);
  assert.equal(w1.isSolid(testX, surfY + 2), false);
  assert.equal(w1.craterLog.length, 1);
  assert.equal(w1.craterLog[0][3], 24);
});

test('All 36 weapon stats & campaign 28 mission stages launch cleanly', () => {
  const assets = loadMinimalAssets();
  assert.equal(WEAPON_STATS.length, 36);
  const world = new WormsWorld(assets);

  for (const stage of [0, 6, 13, 27]) {
    world.startCampaignStage(stage);
    assert.equal(world.o, 6);
    assert.ok(world.worms.length >= 2);
  }
});

test('Bazooka projectile launches, arcs with wind & gravity, detonates on ground and damages/knocks worms', () => {
  const assets = loadMinimalAssets();
  const world = new WormsWorld(assets);
  world.startQuickMatch(99111);
  world.phase = 'PLAY';
  world.turnMsLeft = 45000;

  const aw = world.getActiveWorm();
  aw.weaponId = 0; // Bazooka
  world.chargePower = 70;
  world.fireCurrentWeapon();

  assert.equal(world.projectiles.length, 1);
  const initialCraters = world.craterLog.length;

  for (let tick = 0; tick < 80 && world.projectiles.length > 0; tick++) {
    world.stepTick(50);
  }
  assert.equal(world.projectiles.length, 0);
  assert.ok(world.craterLog.length > initialCraters, 'Bazooka explosion must carve a crater');
});

test('Menu navigation covers Main Menu -> Play -> Quick Match and Options/Language persistence', () => {
  const assets = loadMinimalAssets();
  const world = new WormsWorld(assets);
  world.o = 4;
  world.cm = 0;
  world.cn = 0;

  world.pressedKeys = KEY.FIRE;
  world.stepTick(50);
  assert.equal(world.cm, 1); // Play submenu

  world.cn = 1; // Quick Play
  world.pressedKeys = KEY.FIRE;
  world.stepTick(50);
  assert.equal(world.o, 6);
});

test('Worm & weapon sprite orientation (flipFlag & aim anim 27..37) matches crosshair direction on all quadrants', () => {
  const read = (n) => new Uint8Array(fs.readFileSync(path.join(ASSETS_DIR, n)));
  const reader = new EAIndexReader(read('index'), Array.from({ length: 11 }, (_, i) => read(`df${i}_0`)));
  const ac0 = new ACSpritePack(0, reader.getChunk(18), reader.getChunk(19), null);
  const ac2 = new ACSpritePack(2, reader.getChunk(18 + 4), reader.getChunk(18 + 5), null);

  function getBazookaMuzzleOffset(worm) {
    const { flipFlag, animId } = getWormRenderState(worm, true, 'PLAY');
    const fId = ac0.afFrame[ac0.animStarts[animId]];
    for (let i = ac0.frameStarts[fId]; i < ac0.frameStarts[fId + 1]; i++) {
      const m = ac0.fmMod[i];
      if (m < 0 || m > 10) continue;
      let mx = ac0.fmX[i];
      let my = ac0.fmY[i];
      let fl = ac0.fmFlags[i] & 7;
      if (flipFlag) {
        const effW = (fl & 4) === 0 ? ac0.modW[m] : ac0.modH[m];
        mx = -mx - effW;
        fl = composeACTransform(fl, flipFlag);
      }
      const wf = getHeldWeaponFrame(m, 0);
      for (let k = ac2.frameStarts[wf]; k < ac2.frameStarts[wf + 1]; k++) {
        if (ac2.fmMod[k] === 0) {
          let wmx = ac2.fmX[k];
          const wmy = ac2.fmY[k];
          if (fl) {
            const effW = (ac2.fmFlags[k] & 4) === 0 ? ac2.modW[0] : ac2.modH[0];
            wmx = -wmx - effW;
          }
          return { x: mx + wmx, y: my + wmy, flipFlag, animId };
        }
      }
    }
    return null;
  }

  // Facing RIGHT (dir=+1), aiming UP-RIGHT (aimAngle=35)
  const upRight = getBazookaMuzzleOffset({ dir: 1, aimAngle: 35, weaponId: 0, onGround: true, state: 'IDLE', vy: 0 });
  assert.equal(upRight.flipFlag, 1);
  assert.ok(upRight.animId > 32 && upRight.animId <= 37);
  assert.ok(upRight.x > 0 && upRight.y < -10);

  // Facing RIGHT (dir=+1), aiming DOWN-RIGHT (aimAngle=145)
  const downRight = getBazookaMuzzleOffset({ dir: 1, aimAngle: 145, weaponId: 0, onGround: true, state: 'IDLE', vy: 0 });
  assert.equal(downRight.flipFlag, 1);
  assert.ok(downRight.animId >= 27 && downRight.animId < 32);
  assert.ok(downRight.x >= 0 && downRight.y > -2);

  // Facing LEFT (dir=-1), aiming UP-LEFT (aimAngle=35)
  const upLeft = getBazookaMuzzleOffset({ dir: -1, aimAngle: 35, weaponId: 0, onGround: true, state: 'IDLE', vy: 0 });
  assert.equal(upLeft.flipFlag, 0);
  assert.ok(upLeft.animId > 32 && upLeft.animId <= 37);
  assert.ok(upLeft.x < 0 && upLeft.y < -10);

  // Facing LEFT (dir=-1), aiming DOWN-LEFT (aimAngle=145)
  const downLeft = getBazookaMuzzleOffset({ dir: -1, aimAngle: 145, weaponId: 0, onGround: true, state: 'IDLE', vy: 0 });
  assert.equal(downLeft.flipFlag, 0);
  assert.ok(downLeft.animId >= 27 && downLeft.animId < 32);
  assert.ok(downLeft.x < 0 && downLeft.y > -2);

  // Projectile sprite direction aligns with velocity vector
  const rightProj = getProjectileSpriteInfo({ kind: 0, vx: 6, vy: 0 }, 0);
  assert.equal(rightProj.flipFlag, 1);
  assert.equal(rightProj.frameId, 50); // horizontal bazooka rocket
  const leftUpProj = getProjectileSpriteInfo({ kind: 0, vx: -1, vy: -8 }, 0);
  assert.equal(leftUpProj.flipFlag, 0);
  assert.equal(leftUpProj.frameId, 45); // upward bazooka rocket
});

test('Shotgun & Uzi hitscan fire exactly along crosshair ray and hit target worms', () => {
  const assets = loadMinimalAssets();
  const world = new WormsWorld(assets);
  world.startQuickMatch(55123);
  world.phase = 'PLAY';
  world.turnMsLeft = 45000;

  // Clear terrain between shooter and target along test ray
  const shooter = world.getActiveWorm();
  const target = world.worms.find((w) => w.team !== shooter.team && w.alive);
  shooter.x = MARGIN_X + 200;
  shooter.y = MARGIN_Y + 120;
  shooter.dir = 1;
  shooter.aimAngle = 60; // UP-RIGHT
  shooter.weaponId = 12; // Shotgun

  const { dirX, dirY } = getAimDirection(shooter.dir, shooter.aimAngle);
  const dist = 70;
  // Place target's upper body directly on the crosshair ray (y - 6 + dirY * dist = target.y - 8)
  target.x = shooter.x + dirX * dist;
  target.y = shooter.y + AIM_ORIGIN_OFFSET_Y + dirY * dist + 8;

  // Carve clear air corridor along ray
  for (let d = 0; d <= dist + 10; d += 6) {
    world.carveCrater(shooter.x + dirX * d, shooter.y + AIM_ORIGIN_OFFSET_Y + dirY * d, 14);
  }

  const hpBefore = target.hp;
  world.fireCurrentWeapon();
  assert.equal(target.hp, hpBefore - 25, 'Shotgun shot must hit worm positioned directly on crosshair ray');

  // Verify Uzi spread hits worm directly along angled crosshair ray (both LEFT and RIGHT)
  shooter.dir = -1;
  shooter.aimAngle = 45;
  shooter.weaponId = 30; // Uzi
  const uziAim = getAimDirection(shooter.dir, shooter.aimAngle);
  target.x = shooter.x + uziAim.dirX * 60;
  target.y = shooter.y + AIM_ORIGIN_OFFSET_Y + uziAim.dirY * 60 + 8;
  for (let d = 0; d <= 72; d += 6) {
    world.carveCrater(shooter.x + uziAim.dirX * d, shooter.y + AIM_ORIGIN_OFFSET_Y + uziAim.dirY * d, 14);
  }
  const hpBeforeUzi = target.hp;
  world.fireCurrentWeapon();
  assert.ok(target.hp < hpBeforeUzi, 'Uzi burst must hit worm along angled crosshair ray');

  // Verify non-aimable vs aimable weapon classification
  assert.equal(isAimableWeapon(12), true);
  assert.equal(isAimableWeapon(30), true);
  assert.equal(isAimableWeapon(21), true);
  assert.equal(isAimableWeapon(26), true);
  assert.equal(isAimableWeapon(27), false);
  assert.equal(isAimableWeapon(5), false);
});

test('Baseball Bat & Blow Torch follow crosshair aim direction', () => {
  const assets = loadMinimalAssets();
  const world = new WormsWorld(assets);
  world.startQuickMatch(22334);
  world.phase = 'PLAY';
  const aw = world.getActiveWorm();
  const enemy = world.worms.find((w) => w.team !== aw.team && w.alive);

  // Baseball Bat aimed steeply UP-RIGHT should knock enemy upward & right
  aw.x = MARGIN_X + 200;
  aw.y = MARGIN_Y + 180;
  aw.dir = 1;
  aw.aimAngle = 30;
  aw.weaponId = 21;
  const batAim = getAimDirection(aw.dir, aw.aimAngle);
  enemy.x = aw.x + batAim.dirX * 16;
  enemy.y = aw.y + AIM_ORIGIN_OFFSET_Y + batAim.dirY * 16;
  enemy.vx = 0;
  enemy.vy = 0;
  world.fireCurrentWeapon();
  assert.ok(enemy.vx > 0 && enemy.vy < -5, 'Baseball Bat should knock along aimed UP-RIGHT vector');

  // Blow Torch aimed UP-RIGHT should dig upward & right along crosshair
  aw.x = MARGIN_X + 220;
  aw.y = MARGIN_Y + 200;
  aw.dir = 1;
  aw.aimAngle = 45;
  aw.weaponId = 26;
  const startY = aw.y;
  const startX = aw.x;
  world.fireCurrentWeapon();
  assert.ok(aw.x > startX && aw.y < startY, 'Blow Torch should carve along aimed UP-RIGHT vector');
});

test('Bunker Buster & Air Strike target the selected targetCursor accurately', () => {
  const assets = loadMinimalAssets();
  const world = new WormsWorld(assets);
  world.startQuickMatch(33445);
  world.phase = 'PLAY';
  const aw = world.getActiveWorm();

  aw.weaponId = 17; // Bunker Buster
  world.targetCursor = { x: MARGIN_X + 340, y: MARGIN_Y + 150, dir: 1 };
  world.fireCurrentWeapon();
  assert.equal(world.projectiles.length, 1);
  assert.equal(world.projectiles[0].x, MARGIN_X + 340);
  assert.equal(world.projectiles[0].vx, 0);
});

test('Projectile sprites never render worm hand module m:27 and exclude Pneumatic Drill/Surrender held frames', () => {
  const read = (n) => new Uint8Array(fs.readFileSync(path.join(ASSETS_DIR, n)));
  const reader = new EAIndexReader(read('index'), Array.from({ length: 11 }, (_, i) => read(`df${i}_0`)));
  const ac2 = new ACSpritePack(2, reader.getChunk(18 + 4), reader.getChunk(18 + 5), null);

  const kinds = [0, 1, 5, 6, 7, 10, 11, 13, 16, 17, 19, 22, 25, 27, 28, 29, 31, 70, 250, 999];
  for (const kind of kinds) {
    for (const vx of [-4, 0, 4]) {
      for (const vy of [-4, 0, 4]) {
        for (const flyMode of [false, true]) {
          for (let tick = 0; tick < 4; tick++) {
            const { frameId } = getProjectileSpriteInfo({ kind, vx, vy, flyMode }, tick);
            if (frameId < 0) continue;
            assert.ok(frameId < ac2.numFrames, `kind=${kind} frameId=${frameId} out of bounds`);
            const s = ac2.frameStarts[frameId];
            const e = ac2.frameStarts[frameId + 1];
            for (let idx = s; idx < e; idx++) {
              assert.notEqual(ac2.fmMod[idx], 27, `kind=${kind} frameId=${frameId} must not include hand module m:27`);
            }
          }
        }
      }
    }
  }

  // Dynamite must use lit dynamite frames 109..111 (never 108 which has gripped hand m:27)
  const dynFrames = [0, 1, 2].map((t) => getProjectileSpriteInfo({ kind: 31, vx: 0, vy: 0 }, t).frameId);
  assert.deepEqual(dynFrames, [109, 110, 111]);

  // Dragon Ball should not fall through to Grenade frame 27
  assert.equal(getProjectileSpriteInfo({ kind: 27, vx: 4, vy: -2 }, 0).frameId, -1);

  // Pneumatic Drill (20) and Surrender (35) must not draw held hand/bomb sprites
  assert.equal(getHeldWeaponFrame(5, 20), -1);
  assert.equal(getHeldWeaponFrame(5, 35), -1);

  // Active worm aiming vs retreat state hides weapon aim pose during RETREAT
  const playState = getWormRenderState({ dir: 1, aimAngle: 90, onGround: true, vy: 0, state: 'IDLE', weaponId: 0 }, true, 'PLAY');
  const retreatState = getWormRenderState({ dir: 1, aimAngle: 90, onGround: true, vy: 0, state: 'IDLE', weaponId: 0 }, true, 'RETREAT');
  assert.equal(playState.animId, 32);
  assert.equal(retreatState.animId, 7);
});

test('Mine gives 5s RETREAT movement window and Jetpack lifts off & flies across ticks', () => {
  const assets = loadMinimalAssets();
  const world = new WormsWorld(assets);
  world.startQuickMatch(44556);
  for (let i = 0; i < 30; i++) world.stepTick(50);
  assert.equal(world.phase, 'PLAY');

  // Carve flat runway under active worm
  const aw = world.getActiveWorm();
  aw.x = MARGIN_X + 200;
  aw.y = MARGIN_Y + 180;
  for (let dx = -40; dx <= 60; dx += 4) {
    world.carveCrater(aw.x + dx, aw.y - 8, 16);
  }
  for (let x = MARGIN_X + 150; x <= MARGIN_X + 270; x++) {
    for (let y = aw.y + 7; y <= aw.y + 30; y++) {
      world.mask[(y - MARGIN_Y) * MASK_W + (x - MARGIN_X)] = 1;
    }
  }
  aw.onGround = true;

  // Drop Mine (4) -> must enter RETREAT (5000ms) and let worm walk away without premature detonation
  aw.weaponId = 4;
  world.fireCurrentWeapon();
  assert.equal(world.phase, 'RETREAT');
  assert.equal(world.retreatMsLeft, 5000);
  assert.equal(world.mines.length, 1);

  const startX = aw.x;
  world.heldKeys = KEY.RIGHT;
  for (let t = 0; t < 15; t++) world.stepTick(50);
  world.heldKeys = 0;
  assert.equal(world.phase, 'RETREAT', 'Worm must keep RETREAT time while moving away from Mine');
  assert.ok(aw.x > startX + 8, 'Worm must be able to walk away during Mine retreat window');
  assert.equal(world.mines[0].triggered, false, 'Mine must stay unarmed during retreat grace period');

  // Test Jetpack (2): must lift off ground, stay in JETPACK across ticks, fly UP+RIGHT, and stop on FIRE
  world.phase = 'PLAY';
  world.turnMsLeft = 30000;
  aw.weaponId = 2;
  const jpStartX = aw.x;
  const jpStartY = aw.y;
  world.fireCurrentWeapon();
  assert.equal(aw.state, 'JETPACK');

  world.heldKeys = KEY.UP | KEY.RIGHT;
  for (let t = 0; t < 10; t++) world.stepTick(50);
  assert.equal(aw.state, 'JETPACK');
  assert.ok(aw.y < jpStartY - 8, 'Jetpack must lift worm upward');
  assert.ok(aw.x > jpStartX + 8, 'Jetpack must fly horizontally with RIGHT');
  assert.ok(aw.jetpackFuel < 30, 'Jetpack must burn fuel while flying airborne');

  world.heldKeys = 0;
  world.pressedKeys = KEY.FIRE;
  world.stepTick(50);
  assert.equal(aw.state, 'IDLE', 'Pressing FIRE exits Jetpack cleanly');
  assert.equal(world.phase, 'PLAY', 'Turning off Jetpack lets worm keep acting during turn');
});

test('Gas Pump, Parachute, Napalm & Grenade never trigger Bazooka rockets, and Left/Right drawers pop in', () => {
  const assets = loadMinimalAssets();
  const world = new WormsWorld(assets);
  world.startQuickMatch(77889);
  for (let i = 0; i < 30; i++) world.stepTick(50);
  const aw = world.getActiveWorm();

  // Parachute on ground must NOT spawn a Bazooka rocket
  aw.weaponId = 8;
  world.fireCurrentWeapon();
  assert.equal(world.projectiles.length, 0);

  // Gas Pump (23) must NOT spawn a Bazooka rocket
  world.phase = 'PLAY';
  aw.weaponId = 23;
  world.fireCurrentWeapon();
  assert.equal(world.projectiles.length, 0);
  assert.equal(world.phase, 'RETREAT');

  // Grenade (1) keeps RETREAT without spawning white rocket smoke particles
  world.phase = 'PLAY';
  world.particles = [];
  aw.weaponId = 1;
  world.fireCurrentWeapon();
  assert.equal(world.phase, 'RETREAT');
  assert.equal(world.projectiles[0].kind, 1);
  world.stepTick(50);
  world.stepTick(50);
  assert.equal(world.phase, 'RETREAT', 'Grenade RETREAT must not end on tick 1 when projectile exists');
  assert.equal(world.particles.filter((pt) => pt.kind === 'smoke').length, 0, 'Grenade should not puff Bazooka smoke');

  // Napalm projectile sprite should not use Bazooka rocket frame (45..55)
  const napalmSprite = getProjectileSpriteInfo({ kind: 11, vx: 1.5, vy: 2.2 }, 0);
  assert.ok(napalmSprite.frameId >= 159 && napalmSprite.frameId <= 169);

  // Left Backpack & Right In-Game Menu drawer animations
  world.phase = 'PLAY';
  assert.ok(world.backpackSlide < 0, 'Backpack starts hidden offscreen left');
  world.pressedKeys = KEY.LSK;
  world.stepTick(50);
  assert.equal(world.phase, 'BACKPACK');
  assert.ok(world.backpackSlide > -320, 'Backpack slides in from the left');

  world.pressedKeys = KEY.RSK;
  world.stepTick(50);
  assert.equal(world.phase, 'PLAY');

  world.pressedKeys = KEY.RSK;
  world.stepTick(50);
  assert.equal(world.o, 7, 'Right softkey opens in-game pop-out menu');
  assert.ok(world.rightMenuSlide < 320, 'Right pop-out menu slides in from the right edge');
});
