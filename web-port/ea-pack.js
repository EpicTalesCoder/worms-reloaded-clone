/**
 * EA J2ME Resource Pack, Bitmap Font, Localization & Sprite Decoders
 * Ports:
 * - b.java:39-299 (/index + /df0_0..df10_0 + PNG PLTE swap on entry 11)
 * - p.java:6-698 + m.java:1-165 + c.java:1-296 (EA bitmap font & /hdr + /charmap + /t_*)
 * - ac.java:48-844 (31 sprite/animation packs including RGB565 & RLE 50/51 tiles)
 * - y.java:1114-1264 (trig table 3, 28 campaign levels 85, MIDI 80..84)
 * - a.java:2555-2638 (map script/trigger packs 86..89 & boss splines 90..93)
 */

import {
  s8, u8, s16, u16,
  AC_TO_MIDP_TRANS, composeACTransform,
  drawRegionMIDP, applyAnchor,
} from './midp-graphics.js';

function readUIntLE(bytes, offset, nBytes) {
  let v = 0;
  for (let i = 0; i < nBytes; i++) {
    v |= (bytes[offset + i] & 0xff) << (i * 8);
  }
  return v;
}

function readU16BE(bytes, offset) {
  return ((bytes[offset] & 0xff) << 8) | (bytes[offset + 1] & 0xff);
}

function readS16BE(bytes, offset) {
  return s16(readU16BE(bytes, offset));
}

function readU16LE(bytes, offset) {
  return (bytes[offset] & 0xff) | ((bytes[offset + 1] & 0xff) << 8);
}

function readS16LE(bytes, offset) {
  return s16(readU16LE(bytes, offset));
}

function readS32LE(bytes, offset) {
  return (
    (bytes[offset] & 0xff) |
    ((bytes[offset + 1] & 0xff) << 8) |
    ((bytes[offset + 2] & 0xff) << 16) |
    ((bytes[offset + 3] & 0xff) << 24)
  ) | 0;
}

/**
 * Port of b.java:39-132: /index + /df0_0..df10_0 chunk index reader.
 */
export class EAIndexReader {
  constructor(indexBytes, dfBuffers) {
    this.dfBuffers = dfBuffers;
    let pos = 1; // b.java:43 skip 1 byte
    this.numFiles = readU16LE(indexBytes, pos); pos += 2; // b.java:44
    this.intSz = indexBytes[pos++]; // b.java:45
    this.numLangs = indexBytes[pos++]; // b.java:46

    this.fileMaxId = new Int32Array(this.numFiles);
    this.fileSizes = [];
    for (let f = 0; f < this.numFiles; f++) {
      this.fileMaxId[f] = readU16LE(indexBytes, pos); pos += 2; // b.java:53
      const arr = new Int32Array(this.numLangs + 1);
      for (let k = 0; k < this.numLangs + 1; k++) {
        arr[k] = readUIntLE(indexBytes, pos, this.intSz); pos += this.intSz; // b.java:56
      }
      pos += this.intSz; // b.java:59
      this.fileSizes.push(arr);
    }

    this.totalEntries = readU16LE(indexBytes, pos); pos += 2; // b.java:62
    const flagBytes = Math.ceil(this.numLangs / 8);
    this.entryMask = new Uint32Array(this.totalEntries);
    this.entryOffset = new Int32Array(this.totalEntries);
    this.entryExt = [];

    for (let i = 0; i < this.totalEntries; i++) {
      const mask = readUIntLE(indexBytes, pos, flagBytes); pos += flagBytes; // b.java:73
      this.entryMask[i] = mask;
      this.entryOffset[i] = readUIntLE(indexBytes, pos, this.intSz); pos += this.intSz; // b.java:92
      const ext = [];
      for (let k = 0; k < this.numLangs; k++) {
        if ((mask & (1 << k)) !== 0) {
          ext.push(readUIntLE(indexBytes, pos, this.intSz)); pos += this.intSz; // b.java:98
        }
      }
      this.entryExt.push(ext);
    }
  }

  getChunkMeta(resId) {
    let fidx = 0;
    for (let f = 0; f < this.numFiles; f++) {
      if (this.fileMaxId[f] >= resId && f > 0 && this.fileMaxId[f - 1] < resId) {
        fidx = f;
        break;
      }
    }
    const off = this.entryOffset[resId];
    let sz = 0;
    if (resId === this.fileMaxId[fidx]) {
      sz = this.fileSizes[fidx][0] - off; // b.java:122
    } else if (resId + 1 < this.totalEntries) {
      sz = this.entryOffset[resId + 1] - off; // b.java:124
    }
    return { fidx, off, sz };
  }

  getChunk(resId) {
    const { fidx, off, sz } = this.getChunkMeta(resId);
    const df = this.dfBuffers[fidx];
    return df.subarray(off, off + sz);
  }
}

/**
 * Port of b.java:172-204: replace PNG 'PLTE' chunk inside basePng with plteChunk (len=780).
 */
export function swapPNGPalette(basePng, plteChunk) {
  if (!plteChunk) return basePng;
  let pltePos = -1;
  for (let i = 8; i < basePng.length - 8; i++) {
    if (
      basePng[i] === 0x50 &&
      basePng[i + 1] === 0x4c &&
      basePng[i + 2] === 0x54 &&
      basePng[i + 3] === 0x45
    ) {
      pltePos = i;
      break;
    }
  }
  if (pltePos < 4) return basePng;
  const out = new Uint8Array(basePng);
  out.set(plteChunk, pltePos - 4); // b.java:196 copies [4B len]['PLTE'][768B][4B CRC]
  return out;
}

/**
 * Port of p.java:6-455: EA binary bitmap font decoder & glyph layout.
 */
export class EAFont {
  constructor(metricsBytes, sheetCanvas) {
    this.sheet = sheetCanvas;
    const flags = metricsBytes[0];
    this.isDoubleByte = (flags & 128) !== 0; // p.java:14
    this.hasFlags = (flags & 64) !== 0; // p.java:21
    this.charSpacing = s8(metricsBytes[1]); // a[0]
    this.spaceWidth = s8(metricsBytes[2]);  // a[1]
    this.lineDelta = s8(metricsBytes[3]);   // a[2]
    this.ascent = u8(metricsBytes[4]);      // a[3]
    this.descent = u8(metricsBytes[5]);     // a[4]
    this.height = this.ascent + this.descent + this.lineDelta; // p.java:169

    this.numGlyphs = readS16BE(metricsBytes, 8); // p.java:43
    this.numSub = readS16BE(metricsBytes, 10);   // p.java:44

    let pos = 12;
    this.codepoints = new Uint16Array(this.numGlyphs);
    this.cpMap = new Map();
    for (let i = 0; i < this.numGlyphs; i++) {
      const cp = readU16BE(metricsBytes, pos); pos += 2;
      this.codepoints[i] = cp;
      this.cpMap.set(cp, i);
    }

    this.subOffsets = new Int16Array(this.numGlyphs + 1);
    for (let i = 0; i < this.numGlyphs; i++) {
      this.subOffsets[i] = readS16BE(metricsBytes, pos); pos += 2; // p.java:56
    }
    this.subOffsets[this.numGlyphs] = -1;

    const tableLen = (this.numGlyphs << 1) + this.numSub * 6; // p.java:62
    this.glyphBytes = metricsBytes.subarray(pos, pos + tableLen);
  }

  getGlyphIndex(chCode) {
    let idx = this.cpMap.get(chCode);
    if (idx !== undefined) return idx;
    // Fallback for lower-case -> upper-case if a font variant lacks lowercase, or stripped accent
    const s = String.fromCharCode(chCode);
    const up = s.toUpperCase().charCodeAt(0);
    idx = this.cpMap.get(up);
    if (idx !== undefined) return idx;
    const base = s.normalize('NFD').replace(/[\u0300-\u036f]/g, '').replace(/\u0111/g, 'd').replace(/\u0110/g, 'D');
    if (base.length > 0) {
      idx = this.cpMap.get(base.charCodeAt(0));
      if (idx !== undefined) return idx;
      idx = this.cpMap.get(base.toUpperCase().charCodeAt(0));
      if (idx !== undefined) return idx;
    }
    return -1;
  }

  getCharAdvance(chCode) {
    if (chCode === 173 || chCode === 10 || chCode === 124) return 0;
    if (chCode === 32) return this.spaceWidth + this.charSpacing;
    const idx = this.getGlyphIndex(chCode);
    if (idx < 0) return this.spaceWidth + this.charSpacing;
    const adv = u8(this.glyphBytes[this.subOffsets[idx]]); // gb[off+0] = glyph advance
    return adv + this.charSpacing;
  }

  measureWidth(str) {
    if (!str) return 0;
    let w = 0;
    for (let i = 0; i < str.length; i++) {
      const code = str.charCodeAt(i);
      if (code === 173 && i < str.length - 1) continue;
      w += this.getCharAdvance(code);
    }
    return w;
  }

  wrapLines(str, maxWidth) {
    if (!str) return [''];
    const lines = [];
    const hardParts = str.split('|');
    for (const part of hardParts) {
      if (!part) {
        lines.push('');
        continue;
      }
      if (maxWidth <= 0 || this.measureWidth(part) <= maxWidth) {
        lines.push(part);
        continue;
      }
      const words = part.split(' ');
      let cur = '';
      for (const word of words) {
        const cand = cur ? `${cur} ${word}` : word;
        if (this.measureWidth(cand) <= maxWidth || !cur) {
          cur = cand;
        } else {
          lines.push(cur);
          cur = word;
        }
      }
      if (cur) lines.push(cur);
    }
    return lines;
  }

  /**
   * Port of p.java:201-305: render single-line string using extracted PNG sheet glyphs.
   */
  drawString(ctx, str, x, y, anchor = 20) {
    if (!str || !this.sheet) return;
    let dx = x | 0;
    let dy = y | 0;
    const totalW = this.measureWidth(str);
    if ((anchor & 8) !== 0) dx -= totalW;
    else if ((anchor & 1) !== 0) dx -= (totalW >> 1);

    if ((anchor & 64) !== 0) dy -= this.ascent;
    else if ((anchor & 32) !== 0) dy -= this.height;
    else if ((anchor & 2) !== 0) dy -= (this.height >> 1);

    let cx = dx;
    const cy = dy;
    const gb = this.glyphBytes;
    const so = this.subOffsets;

    for (let i = 0; i < str.length; i++) {
      const code = str.charCodeAt(i);
      if (code === 173 && i < str.length - 1) continue;
      if (code === 32) {
        cx += this.spaceWidth + this.charSpacing;
        continue;
      }
      const gIdx = this.getGlyphIndex(code);
      if (gIdx < 0) {
        cx += this.spaceWidth + this.charSpacing;
        continue;
      }
      const baseOff = so[gIdx];
      const adv = u8(gb[baseOff]);     // gb[off+0] = advance
      const nSub = u8(gb[baseOff + 1]); // gb[off+1] = sub-glyph count (p.java:276)
      let p = baseOff + 2;
      for (let s = 0; s < nSub; s++) {
        const sx = u8(gb[p]);
        const sy = u8(gb[p + 1]);
        const ox = s8(gb[p + 2]);
        const oy = s8(gb[p + 3]);
        const w = u8(gb[p + 4]);
        const h = u8(gb[p + 5]);
        p += 6;
        if (w > 0 && h > 0) {
          // Port of p.java:254,294: var6.b[3] = ascent - oy - h (y grows downward from baseline-ascent)
          ctx.drawImage(this.sheet, sx, sy, w, h, cx + ox, cy + (this.ascent - oy - h), w, h);
        }
      }
      cx += adv + this.charSpacing;
    }
  }

  drawWrapped(ctx, str, x, y, maxWidth, anchor = 17, maxLines = 999, scrollLine = 0) {
    const lines = this.wrapLines(str, maxWidth);
    const stepY = Math.max(10, this.height);
    const total = Math.min(maxLines, lines.length - scrollLine);
    let sy = y | 0;
    if ((anchor & 2) !== 0) {
      sy -= ((total * stepY) >> 1);
    } else if ((anchor & 32) !== 0) {
      sy -= total * stepY;
    }
    const hAnchor = anchor & (1 | 4 | 8);
    for (let i = 0; i < total; i++) {
      const line = lines[scrollLine + i];
      if (line !== undefined) {
        this.drawString(ctx, line, x, sy + i * stepY, hAnchor | 16);
      }
    }
    return lines.length;
  }
}

/**
 * Port of p.java:469-698 & m.java:130-164: /hdr, /charmap, /t_* localization.
 */
export class EALocalePack {
  constructor(hdrBytes, charmapBytes, langFiles) {
    this.charmap = new Uint16Array(128);
    for (let i = 0; i < 128; i++) {
      this.charmap[i] = readU16BE(charmapBytes, i * 2);
    }

    const v2Short = readS16BE(hdrBytes, 5);
    this.numLangs = hdrBytes[7];
    const bVal = hdrBytes[9] + 1;
    let p = 10;
    const sv = v2Short + 1;

    this.langs = [];
    this.headerStrings = [];
    const b0Offs = [];
    for (let l = 0; l < this.numLangs; l++) {
      let code = '';
      for (let k = 0; k < 5; k++) {
        const ch = hdrBytes[p + k];
        if (ch > 0x20) code += String.fromCharCode(ch);
      }
      const ba = s8(hdrBytes[p + 5]);
      const be = s8(hdrBytes[p + 6]);
      p += 7;
      const offs = [];
      for (let k = 0; k < sv; k++) {
        offs.push(readS16BE(hdrBytes, p)); p += 2;
      }
      b0Offs.push(offs);
      this.langs.push({ code: code.trim(), charsetFlag: ba, eFlag: be });
    }

    const totB0 = v2Short > 0 ? b0Offs[this.numLangs - 1][sv - 1] : 0;
    const b0Blob = hdrBytes.subarray(p, p + totB0);
    p += totB0;

    for (let l = 0; l < this.numLangs; l++) {
      const strs = [];
      for (let s = 0; s < v2Short; s++) {
        const a = b0Offs[l][s];
        const b = b0Offs[l][s + 1];
        strs.push(this.decodeUTF16(b0Blob.subarray(a, b)));
      }
      this.headerStrings.push(strs);
    }

    const groups = [];
    for (let g = 1; g < bVal; g++) {
      const gf = hdrBytes[p] & 192;
      const gs = hdrBytes[p] & 63;
      const gcnt = readS16BE(hdrBytes, p + 1);
      const gsizes = [];
      for (let l = 0; l < this.numLangs; l++) {
        gsizes.push(readU16BE(hdrBytes, p + 3 + 2 * l));
      }
      p += 3 + 2 * this.numLangs;
      groups.push({ g, gf, gs, gcnt, gsizes });
    }

    // Build flat string tables [b0(0..5), Group1, Group2, Group3] per language matching p.java:661-698
    this.stringsByLang = {};
    for (let l = 0; l < this.numLangs; l++) {
      const { code, charsetFlag } = this.langs[l];
      const raw = langFiles[code];
      if (!raw) continue;
      let rp = 0;
      const flat = [...this.headerStrings[l]]; // Indices 0..5 (p.java:666 starts var5=0 -> m.a[0]=6)
      for (const grp of groups) {
        if ((grp.gf & 64) !== 0 && l !== 0) continue;
        const nstr = grp.gcnt + 1;
        const offs = [];
        for (let i = 0; i < nstr; i++) {
          offs.push(readU16BE(raw, rp)); rp += 2;
        }
        const blob = raw.subarray(rp, rp + grp.gsizes[l]);
        rp += grp.gsizes[l];
        for (let i = 0; i < grp.gcnt; i++) {
          const slice = blob.subarray(offs[i], offs[i + 1]);
          flat.push(charsetFlag === 0 ? this.decodeUTF16(slice) : this.decodeSingleByte(slice));
        }
      }
      this.stringsByLang[code] = flat;
    }
    this.activeCode = this.langs[0]?.code || 'en';
  }

  decodeUTF16(slice) {
    let out = '';
    for (let i = 0; i + 1 < slice.length; i += 2) {
      out += String.fromCharCode((slice[i] << 8) | slice[i + 1]);
    }
    return out;
  }

  decodeSingleByte(slice) {
    let out = '';
    for (let i = 0; i < slice.length; i++) {
      const b = slice[i];
      out += b < 128 ? String.fromCharCode(b) : String.fromCharCode(this.charmap[b - 128]);
    }
    return out;
  }

  setLanguage(langCodeOrIndex) {
    if (typeof langCodeOrIndex === 'number') {
      const entry = this.langs[langCodeOrIndex];
      if (entry) this.activeCode = entry.code;
    } else if (this.stringsByLang[langCodeOrIndex]) {
      this.activeCode = langCodeOrIndex;
    }
  }

  getLanguageIndex() {
    return Math.max(0, this.langs.findIndex((l) => l.code === this.activeCode));
  }

  getLanguageDisplayName(index) {
    return this.headerStrings[index]?.[2] || this.langs[index]?.code?.toUpperCase() || 'EN';
  }

  getString(id, ...args) {
    const arr = this.stringsByLang[this.activeCode] || this.stringsByLang.en || [];
    let str = arr[id] ?? '';
    if (args.length > 0) {
      for (let i = 0; i < args.length; i++) {
        str = str.replace('\\0', String(args[i]));
      }
    }
    return str;
  }
}

/**
 * Port of ac.java:48-844: Sprite, frame, animation & RLE 50/51 decoder.
 */
export class ACSpritePack {
  constructor(packId, metaBytes, pixBytes, pngCanvasOrNull, createCanvasFn) {
    this.packId = packId;
    this.sheet = pngCanvasOrNull;
    this.createCanvas = createCanvasFn || ((w, h) => {
      const c = document.createElement('canvas');
      c.width = w;
      c.height = h;
      return c;
    });

    let p = 0;
    this.dMode = metaBytes[p++]; // ac.java:79
    this.numModules = readU16LE(metaBytes, p); p += 2; // ac.java:80

    if (this.dMode === 0) {
      this.modX = new Int16Array(this.numModules);
      this.modY = new Int16Array(this.numModules);
      for (let i = 0; i < this.numModules; i++) { this.modX[i] = readS16LE(metaBytes, p); p += 2; }
      for (let i = 0; i < this.numModules; i++) { this.modY[i] = readS16LE(metaBytes, p); p += 2; }
    } else {
      this.modX = null;
      this.modY = null;
    }

    this.modW = metaBytes.subarray(p, p + this.numModules); p += this.numModules;
    this.modH = metaBytes.subarray(p, p + this.numModules); p += this.numModules;

    this.numFrames = readU16LE(metaBytes, p); p += 2; // ac.java:90
    this.frameStarts = new Uint16Array(this.numFrames + 1);
    let acc = 0;
    for (let i = 1; i <= this.numFrames; i++) {
      acc += metaBytes[p++];
      this.frameStarts[i] = acc;
    }

    this.numFrameMods = readU16LE(metaBytes, p); p += 2; // ac.java:100
    this.fmX = new Int16Array(this.numFrameMods);
    this.fmY = new Int16Array(this.numFrameMods);
    this.fmMod = new Uint16Array(this.numFrameMods);
    this.fmFlags = new Uint8Array(this.numFrameMods);

    for (let i = 0; i < this.numFrameMods; i++) { this.fmX[i] = readS16LE(metaBytes, p); p += 2; }
    for (let i = 0; i < this.numFrameMods; i++) { this.fmY[i] = readS16LE(metaBytes, p); p += 2; }
    for (let i = 0; i < this.numFrameMods; i++) { this.fmMod[i] = metaBytes[p++]; }
    for (let i = 0; i < this.numFrameMods; i++) {
      const f = metaBytes[p++];
      this.fmFlags[i] = f;
      this.fmMod[i] = this.fmMod[i] + ((f & 208) << 2); // ac.java:113 high bits in flag!
    }

    this.numAnims = readU16LE(metaBytes, p); p += 2; // ac.java:116
    this.animStarts = new Uint16Array(this.numAnims + 1);
    acc = 0;
    for (let i = 1; i <= this.numAnims; i++) {
      acc += metaBytes[p++];
      this.animStarts[i] = acc;
    }

    this.numAnimFrames = readU16LE(metaBytes, p); p += 2; // ac.java:126
    this.afX = new Int8Array(this.numAnimFrames);
    this.afY = new Int8Array(this.numAnimFrames);
    this.afFrame = new Uint16Array(this.numAnimFrames);
    this.afDur = new Uint8Array(this.numAnimFrames);
    this.afFlags = new Uint8Array(this.numAnimFrames);

    for (let i = 0; i < this.numAnimFrames; i++) { this.afX[i] = s8(metaBytes[p++]); }
    for (let i = 0; i < this.numAnimFrames; i++) { this.afY[i] = s8(metaBytes[p++]); }
    for (let i = 0; i < this.numAnimFrames; i++) { this.afFrame[i] = metaBytes[p++]; }
    for (let i = 0; i < this.numAnimFrames; i++) { this.afDur[i] = metaBytes[p++]; }
    for (let i = 0; i < this.numAnimFrames; i++) {
      const f = metaBytes[p++];
      this.afFlags[i] = f;
      this.afFrame[i] = this.afFrame[i] + ((f & 208) << 2); // ac.java:143
    }

    // Parse d === 1 RLE indexed pixel data (ac.java:157-200)
    this.rleCanvases = null;
    this.rleRawRgba = null;
    if (this.dMode === 1 && pixBytes) {
      this.decodeRLEPack(pixBytes);
    }
  }

  decodeRLEPack(pixBytes) {
    let p = 0;
    this.rleType = pixBytes[p++]; // 50 or 51 (ac.java:158)
    const palFmt = pixBytes[p++]; // 5 (RGB565) or 129 (ARGB32)
    const nPals = pixBytes[p++];
    const nCols = pixBytes[p++];

    this.palettes = [];
    for (let palIdx = 0; palIdx < nPals; palIdx++) {
      const pal = new Uint32Array(nCols);
      if (palFmt === 5) {
        for (let c = 0; c < nCols; c++) {
          const v = readU16LE(pixBytes, p); p += 2;
          if (v === 63519) { // 0xF81F transparent magenta (ac.java:172-176)
            pal[c] = 0x00000000;
          } else {
            const r = ((v >> 11) & 0x1f) * 255 / 31;
            const g = ((v >> 5) & 0x3f) * 255 / 63;
            const b = (v & 0x1f) * 255 / 31;
            pal[c] = ((255 << 24) | ((b & 0xff) << 16) | ((g & 0xff) << 8) | (r & 0xff)) >>> 0; // ABGR for ImageData Uint32 view
          }
        }
      } else if (palFmt === 129) {
        for (let c = 0; c < nCols; c++) {
          const argb = readS32LE(pixBytes, p) >>> 0; p += 4;
          const a = (argb >>> 24) & 0xff;
          const r = (argb >>> 16) & 0xff;
          const g = (argb >>> 8) & 0xff;
          const b = argb & 0xff;
          pal[c] = ((a << 24) | (b << 16) | (g << 8) | r) >>> 0;
        }
      }
      this.palettes.push(pal);
    }

    const modOffs = new Int32Array(this.numModules + 1);
    let total = 0;
    for (let m = 0; m < this.numModules; m++) {
      modOffs[m] = total;
      total += readU16LE(pixBytes, p); p += 2;
    }
    modOffs[this.numModules] = total;
    const stream = pixBytes.subarray(p, p + total);

    this.rleRawRgba = new Array(this.numModules);
    this.rleCanvases = new Array(this.numModules);
    const pal0 = this.palettes[0];

    for (let m = 0; m < this.numModules; m++) {
      const w = this.modW[m];
      const h = this.modH[m];
      const rgba32 = new Uint32Array(w * h);
      let dp = 0;
      let cp = modOffs[m];
      const cEnd = modOffs[m + 1];

      while (cp < cEnd && dp < rgba32.length) {
        const b = stream[cp++];
        if ((b & 128) !== 0) {
          const cnt = (b & 127) + 1;
          const val = stream[cp++];
          const col = pal0[val] ?? 0;
          for (let k = 0; k < cnt && dp < rgba32.length; k++) rgba32[dp++] = col;
        } else if (this.rleType === 50) {
          rgba32[dp++] = pal0[b] ?? 0;
        } else if (this.rleType === 51) {
          const cnt = b + 1;
          for (let k = 0; k < cnt && cp < cEnd && dp < rgba32.length; k++) {
            rgba32[dp++] = pal0[stream[cp++]] ?? 0;
          }
        }
      }

      this.rleRawRgba[m] = rgba32;
      if (typeof document !== 'undefined' && w > 0 && h > 0) {
        const canvas = this.createCanvas(w, h);
        const ctx = canvas.getContext('2d');
        const imgData = ctx.createImageData(w, h);
        new Uint32Array(imgData.data.buffer).set(rgba32);
        ctx.putImageData(imgData, 0, 0);
        this.rleCanvases[m] = canvas;
      }
    }
  }

  getAnimFrameCount(animId) {
    if (animId < 0 || animId >= this.numAnims) return 0;
    return this.animStarts[animId + 1] - this.animStarts[animId];
  }

  getAnimDurationTicks(animId) {
    const start = this.animStarts[animId] ?? 0;
    const end = this.animStarts[animId + 1] ?? start;
    let sum = 0;
    for (let i = start; i < end; i++) sum += Math.max(1, this.afDur[i]);
    return Math.max(1, sum);
  }

  resolveAnimFrameIndex(animId, tickCount, loop = true) {
    const count = this.getAnimFrameCount(animId);
    if (count <= 0) return 0;
    const start = this.animStarts[animId];
    let t = Math.max(0, tickCount | 0);
    if (loop) {
      t = t % this.getAnimDurationTicks(animId);
    }
    let acc = 0;
    for (let i = 0; i < count; i++) {
      const dur = Math.max(1, this.afDur[start + i]);
      acc += dur;
      if (t < acc) return i;
    }
    return count - 1;
  }

  drawModule(ctx, modId, dx, dy, acTransform = 0) {
    if (modId < 0 || modId >= this.numModules) return;
    const w = this.modW[modId];
    const h = this.modH[modId];
    if (w <= 0 || h <= 0) return;
    const midpTrans = AC_TO_MIDP_TRANS[acTransform & 7];
    if (this.dMode === 0 && this.sheet) {
      drawRegionMIDP(ctx, this.sheet, this.modX[modId], this.modY[modId], w, h, midpTrans, dx, dy, 20);
    } else if (this.dMode === 1 && this.rleCanvases?.[modId]) {
      drawRegionMIDP(ctx, this.rleCanvases[modId], 0, 0, w, h, midpTrans, dx, dy, 20);
    }
  }

  /**
   * Port of ac.java:234-375: draw composed frame with optional worm overlays (packId === 0).
   */
  drawFrame(ctx, frameId, dx, dy, acTransform = 0, hooks = null) {
    if (frameId < 0 || frameId >= this.numFrames) return;
    const start = this.frameStarts[frameId];
    const end = this.frameStarts[frameId + 1];

    for (let idx = start; idx < end; idx++) {
      let mx = this.fmX[idx];
      let my = this.fmY[idx];
      const modId = this.fmMod[idx];
      let flags = this.fmFlags[idx] & 7;
      const w = this.modW[modId] || 0;
      const h = this.modH[modId] || 0;

      if ((acTransform & 7) !== 0) {
        const effW = (flags & 4) === 0 ? w : h;
        const effH = (flags & 4) === 0 ? h : w;
        if ((acTransform & 1) !== 0) mx = -mx - effW;
        if ((acTransform & 2) !== 0) my = -my - effH;
        if ((acTransform & 4) !== 0) {
          const tmp = mx;
          mx = -my - effH;
          my = tmp;
        }
        flags = composeACTransform(flags, acTransform & 7);
      }

      const rx = (dx | 0) + mx;
      const ry = (dy | 0) + my;

      // Port of ac.java:288-345 special sub-sprite attachment hooks on worm sprite pack (k === 0)
      if (this.packId === 0 && hooks) {
        // Hat module hook (11..14)
        if (modId >= 11 && modId <= 14) {
          if (hooks.hat >= 0 && hooks.hatsPack) {
            const hatFrame = (modId - 11) + (hooks.hat << 2);
            hooks.hatsPack.drawFrame(ctx, hatFrame, rx, ry, flags);
          }
          continue;
        }
        // Weapon / hand module hook (0..10)
        if (modId >= 0 && modId <= 10) {
          if (hooks.weaponFrameFn && hooks.handsPack) {
            const wf = hooks.weaponFrameFn(modId, hooks.weaponId);
            if (wf >= 0) {
              let ox = 0;
              let oy = 0;
              if (wf >= 79 && wf < 82) {
                ox = (flags & 1) !== 0 ? 10 : -10;
                oy = 8;
              }
              hooks.handsPack.drawFrame(ctx, wf, rx + ox, ry + oy, flags);
            }
          }
          continue;
        }
        // Super sheep star/sparks overlay module 173
        if (modId === 173 && hooks.fxPack) {
          hooks.fxPack.drawAnim(ctx, 19, (hooks.tick || 0) % 2, rx, ry, 0);
          continue;
        }
      }

      this.drawModule(ctx, modId, rx, ry, flags);
    }
  }

  drawAnim(ctx, animId, animFrameIdx, dx, dy, acTransform = 0, hooks = null) {
    if (animId < 0 || animId >= this.numAnims) return;
    const afIdx = (this.animStarts[animId] ?? 0) + (animFrameIdx | 0);
    if (afIdx >= this.numAnimFrames) return;
    const frameId = this.afFrame[afIdx];
    const ox = this.afX[afIdx];
    const oy = this.afY[afIdx];
    const flags = composeACTransform(this.afFlags[afIdx] & 7, acTransform & 7);
    const [fx, fy] = [((dx | 0) + ox), ((dy | 0) + oy)];
    this.drawFrame(ctx, frameId, fx, fy, flags, hooks);
  }
}

export async function loadImageFromBytes(bytes, mime = 'image/png') {
  const blob = new Blob([bytes], { type: mime });
  const url = URL.createObjectURL(blob);
  try {
    const img = new Image();
    await new Promise((resolve, reject) => {
      img.onload = resolve;
      img.onerror = reject;
      img.src = url;
    });
    const canvas = document.createElement('canvas');
    canvas.width = img.width;
    canvas.height = img.height;
    const ctx = canvas.getContext('2d');
    ctx.imageSmoothingEnabled = false;
    ctx.drawImage(img, 0, 0);
    return canvas;
  } finally {
    URL.revokeObjectURL(url);
  }
}

/**
 * Port of y.java:1236-1262: 28 campaign level rows from chunk 85.
 */
export function parseCampaignTable(bytes85) {
  const stages = [];
  let p = 0;
  for (let i = 0; i < 28 && p + 22 <= bytes85.length; i++) {
    const pad = bytes85[p];
    const arg = readS16LE(bytes85, p + 1);
    const u = [];
    for (let k = 0; k < 15; k++) u.push(bytes85[p + 3 + k]);
    const s = [];
    for (let k = 0; k < 4; k++) s.push(s8(bytes85[p + 18 + k]));
    p += 22;
    // Map array indices a[var56][0..19] matching y.java:1241-1260
    const row = [
      ...u.slice(0, 15),
      s[0], s[1], s[2], s[3],
      u[3], // a[i][19] = a[i][3]
    ];
    stages.push({ index: i, pad, arg, fields: row });
  }
  return stages;
}

/**
 * Port of y.java:1114-1122: cosine lookup table (0..90 deg in 16.16 fixed point) from chunk 3.
 */
export function parseTrigTable(bytes3) {
  const lut = new Int32Array(91);
  for (let i = 0; i < 91 && i * 4 + 4 <= bytes3.length; i++) {
    lut[i] = readS32LE(bytes3, i * 4);
  }
  return lut;
}

/**
 * Load all binary assets under baseUrl (`assets/`) cleanly.
 */
export async function loadAllEAAssets(baseUrl = 'assets') {
  const fetchBin = async (rel) => {
    const res = await fetch(`${baseUrl}/${rel}`);
    if (!res.ok) throw new Error(`Missing EA asset ${rel}: HTTP ${res.status}`);
    return new Uint8Array(await res.arrayBuffer());
  };

  const [indexBytes, hdrBytes, charmapBytes, tEn, tVi, tTh, ...dfBytes] = await Promise.all([
    fetchBin('index'),
    fetchBin('hdr'),
    fetchBin('charmap'),
    fetchBin('t_en'),
    fetchBin('t_vi'),
    fetchBin('t_th'),
    ...Array.from({ length: 11 }, (_, i) => fetchBin(`df${i}_0`)),
  ]);

  const indexReader = new EAIndexReader(indexBytes, dfBytes);
  const locale = new EALocalePack(hdrBytes, charmapBytes, { en: tEn, vi: tVi, th: tTh });
  const trigCos = parseTrigTable(indexReader.getChunk(3));
  const campaigns = parseCampaignTable(indexReader.getChunk(85));

  // Decode splash PNGs (0=EA, 1=Team17, 2=Worms Reloaded 320x240)
  const splashes = await Promise.all([
    loadImageFromBytes(indexReader.getChunk(0)),
    loadImageFromBytes(indexReader.getChunk(1)),
    loadImageFromBytes(indexReader.getChunk(2)),
  ]);

  // Decode primary font sheets with PLTE palette swaps S = [-1, 7, 8] (+ bonus green 9)
  const fontMetrics = indexReader.getChunk(10);
  const fontPngBase = indexReader.getChunk(11);
  const [fontWhiteImg, fontCyanImg, fontRedImg, fontGreenImg] = await Promise.all([
    loadImageFromBytes(fontPngBase),
    loadImageFromBytes(swapPNGPalette(fontPngBase, indexReader.getChunk(7))),
    loadImageFromBytes(swapPNGPalette(fontPngBase, indexReader.getChunk(8))),
    loadImageFromBytes(swapPNGPalette(fontPngBase, indexReader.getChunk(9))),
  ]);

  const fonts = {
    white: new EAFont(fontMetrics, fontWhiteImg),
    cyan: new EAFont(fontMetrics, fontCyanImg),
    red: new EAFont(fontMetrics, fontRedImg),
    green: new EAFont(fontMetrics, fontGreenImg),
  };

  // Decode all 31 AC sprite packs (ids 0..30)
  const sprites = new Array(31);
  await Promise.all(
    Array.from({ length: 31 }, async (_, sid) => {
      const meta = indexReader.getChunk(18 + (sid << 1));
      const pix = indexReader.getChunk(18 + (sid << 1) + 1);
      const isPng = pix.length >= 4 && pix[0] === 0x89 && pix[1] === 0x50;
      const sheet = isPng ? await loadImageFromBytes(pix) : null;
      sprites[sid] = new ACSpritePack(sid, meta, pix, sheet);
    }),
  );

  // Extract 5 MIDI tracks (ids 80..84) and map/spline packs (ids 86..93)
  const midiTracks = [80, 81, 82, 83, 84].map((id) => indexReader.getChunk(id));
  const mapPacks = {};
  for (let id = 86; id <= 93; id++) {
    mapPacks[id] = indexReader.getChunk(id);
  }

  return {
    indexReader,
    locale,
    fonts,
    splashes,
    sprites,
    trigCos,
    campaigns,
    midiTracks,
    mapPacks,
  };
}
