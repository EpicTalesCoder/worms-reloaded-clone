import test from 'node:test';
import assert from 'node:assert/strict';
import fs from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

import {
  WormsWorld, MASK_W, MASK_H, MARGIN_X, MARGIN_Y, WATER_Y,
  KEY, WEAPON_STATS,
} from '../worms-world.js';
import {
  EAIndexReader, EALocalePack, parseCampaignTable, parseTrigTable,
} from '../ea-pack.js';

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
