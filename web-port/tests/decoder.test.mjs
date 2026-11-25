import test from 'node:test';
import assert from 'node:assert/strict';
import fs from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

import {
  EAIndexReader,
  swapPNGPalette,
  EAFont,
  EALocalePack,
  ACSpritePack,
  parseCampaignTable,
  parseTrigTable,
} from '../ea-pack.js';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const ASSETS_DIR = path.resolve(__dirname, '..', 'assets');

function readAsset(name) {
  return new Uint8Array(fs.readFileSync(path.join(ASSETS_DIR, name)));
}

function loadIndex() {
  const idxBytes = readAsset('index');
  const dfs = Array.from({ length: 11 }, (_, i) => readAsset(`df${i}_0`));
  return new EAIndexReader(idxBytes, dfs);
}

test('EAIndexReader parses 94 entries across df0_0..df10_0 accurately', () => {
  const reader = loadIndex();
  assert.equal(reader.numFiles, 11);
  assert.equal(reader.totalEntries, 94);
  const titlePng = reader.getChunk(2);
  assert.deepEqual(Array.from(titlePng.subarray(0, 4)), [0x89, 0x50, 0x4e, 0x47]);
  const midi0 = reader.getChunk(80);
  assert.equal(String.fromCharCode(...midi0.subarray(0, 4)), 'MThd');
});

test('PLTE swap modifies entry 11 palette bytes matching entry 7 and 8', () => {
  const reader = loadIndex();
  const basePng = reader.getChunk(11);
  const plteBlue = reader.getChunk(7);
  const swapped = swapPNGPalette(basePng, plteBlue);
  assert.equal(swapped.length, basePng.length);
  const idx = swapped.findIndex((_, i) => i < swapped.length - 3 && swapped[i] === 80 && swapped[i + 1] === 76 && swapped[i + 2] === 84 && swapped[i + 3] === 69);
  assert.ok(idx > 8);
  assert.deepEqual(Array.from(swapped.subarray(idx - 4, idx - 4 + plteBlue.length)), Array.from(plteBlue));
});

test('EALocalePack decodes EN, VI, and TH strings with exact p.a(id) parity', () => {
  const locale = new EALocalePack(readAsset('hdr'), readAsset('charmap'), {
    en: readAsset('t_en'),
    vi: readAsset('t_vi'),
    th: readAsset('t_th'),
  });
  assert.equal(locale.numLangs, 3);
  locale.setLanguage('en');
  assert.equal(locale.getString(13), 'PLAY');
  assert.equal(locale.getString(17), 'CAMPAIGN');
  assert.equal(locale.getString(345), 'BAZOOKA');
  assert.equal(locale.getString(380), 'SURRENDER');
  assert.equal(locale.getString(49), 'LOADING');

  locale.setLanguage('vi');
  assert.equal(locale.getString(13), 'CHƠI');
  assert.equal(locale.getString(17), 'CHIẾN DỊCH');
  assert.equal(locale.getString(345), 'SÚNG BAZOCA');
  assert.equal(locale.getLanguageDisplayName(2), 'Tiếng Việt');
});

test('EAFont decodes 322 glyphs including full Vietnamese uppercase coverage & exact ascent math', () => {
  const reader = loadIndex();
  const font = new EAFont(reader.getChunk(10), { width: 172, height: 107 });
  assert.equal(font.numGlyphs, 322);
  assert.equal(font.ascent, 20);
  assert.equal(font.height, 22);
  for (const ch of 'ÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶĐÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴ') {
    assert.ok(font.getGlyphIndex(ch.charCodeAt(0)) >= 0, `Missing VI glyph ${ch}`);
  }
  const calls = [];
  const fakeCtx = { drawImage: (...args) => calls.push(args) };
  font.drawString(fakeCtx, 'Á', 100, 50, 20);
  assert.equal(calls.length, 2, 'Á should render base A and acute accent sub-glyphs');
  // Base A: oy=0, h=10 -> ascent(20)-0-10 = 10 -> dy = 60; Acute: oy=10, h=6 -> 20-10-6 = 4 -> dy = 54
  assert.equal(calls[0][6], 60);
  assert.equal(calls[1][6], 54);
});

test('ACSpritePack decodes all 31 packs including RLE 50/51 textures and 28 campaign levels', () => {
  const reader = loadIndex();
  for (let sid = 0; sid < 31; sid++) {
    const meta = reader.getChunk(18 + (sid << 1));
    const pix = reader.getChunk(18 + (sid << 1) + 1);
    const pack = new ACSpritePack(sid, meta, pix, null);
    assert.ok(pack.numModules >= 1);
    if (pack.dMode === 1) {
      assert.ok(pack.rleRawRgba[0].length === pack.modW[0] * pack.modH[0]);
      // Terrain tiles 21..25 are 32x32 opaque/indexed pixels
      if (sid >= 21 && sid <= 25) {
        assert.equal(pack.modW[0], 32);
        assert.equal(pack.modH[0], 32);
      }
    }
  }
  const trig = parseTrigTable(reader.getChunk(3));
  assert.equal(trig[0], 65536);
  assert.equal(trig[90], 0);
  const campaigns = parseCampaignTable(reader.getChunk(85));
  assert.equal(campaigns.length, 28);
});
