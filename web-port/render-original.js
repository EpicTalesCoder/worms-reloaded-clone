/**
 * 1:1 Original J2ME Renderer for Worms Reloaded (320x240 Landscape)
 * Ports:
 * - y.java:471-1080,2723-3015,5290-5466,6616-7005,7265-7333 (splashes, menus, shop, HUD, minimap, backpack, softkeys)
 * - a.java:1809-1909 (parallax backdrops ac[26..30], 32x32 RLE masked terrain cache ac[21..25], water, forts)
 * - ah.java:2033-2894,5921-6092 (worms, team hats ac[4], held weapons ac[2], crosshairs ac[11], power dots ac[7], objects)
 */

import {
  SCREEN_W, SCREEN_H, WORLD_W, WORLD_H, MARGIN_X, MARGIN_Y, WATER_Y,
  MASK_W, MASK_H, MINIMAP_W, MINIMAP_H,
  THEME_CONFIGS, TEAM_STYLES, SHOP_PACKS,
} from './worms-world.js';
import {
  drawImageAnchor, fillRectMIDP, fillARGBRectMIDP, drawRectMIDP,
  fillRoundRectMIDP, drawRoundRectMIDP, drawLineMIDP, withMIDPClip,
} from './midp-graphics.js';

/**
 * Port of ah.java:5965-6092: map worm module id (0..10) & weaponId -> ac[2] frame.
 */
export function getHeldWeaponFrame(modId, weaponId) {
  if (modId < 0 || modId > 10) return -1;
  switch (weaponId) {
    case -1: case 2: case 3: case 5: case 8: case 9: case 11: case 14: case 15:
    case 17: case 18: case 21: case 24: case 26: case 27: case 28: case 29:
    case 32: case 33: case 34:
      return -1;
    case 1: return modId + 68;
    case 4: return 157;
    case 6: return modId;
    case 7: return modId + 135;
    case 10:
    case 16: return 79 + ((Date.now() >> 6) % 3);
    case 12: return modId + 11;
    case 13: return modId + 198;
    case 19: return modId + 209;
    case 22: return 182 + ((Date.now() >> 6) % 4);
    case 23: return modId + 170;
    case 25: return modId + 221;
    case 30: return modId + 112;
    case 31: return 108;
    default: return weaponId * 11 + modId;
  }
}

export class WormsRenderer {
  constructor(assets) {
    this.assets = assets;
    // Cached 640x320 offscreen terrain canvas rebuilt incrementally on craters (a.java:1852)
    this.terrainCanvas = document.createElement('canvas');
    this.terrainCanvas.width = MASK_W;
    this.terrainCanvas.height = MASK_H;
    this.terrainCtx = this.terrainCanvas.getContext('2d');
    this.terrainCtx.imageSmoothingEnabled = false;
    this.terrainImgData = this.terrainCtx.createImageData(MASK_W, MASK_H);
    this.terrainPixels32 = new Uint32Array(this.terrainImgData.data.buffer);
    this.cachedThemeId = -1;

    // Cached 170x64 minimap canvas (y.java:6889)
    this.minimapCanvas = document.createElement('canvas');
    this.minimapCanvas.width = MINIMAP_W;
    this.minimapCanvas.height = MINIMAP_H;
    this.minimapCtx = this.minimapCanvas.getContext('2d');
    this.minimapImgData = this.minimapCtx.createImageData(MINIMAP_W, MINIMAP_H);
    this.minimapPixels32 = new Uint32Array(this.minimapImgData.data.buffer);
  }

  packCssToU32ABGR(hex) {
    const clean = hex.replace('#', '');
    const r = parseInt(clean.slice(0, 2), 16);
    const g = parseInt(clean.slice(2, 4), 16);
    const b = parseInt(clean.slice(4, 6), 16);
    return ((255 << 24) | (b << 16) | (g << 8) | r) >>> 0;
  }

  /**
   * Port of a.java:1852-1907 & 2347-2375: update cached destructible 32x32 texture canvas + minimap.
   */
  syncTerrainCache(world) {
    if (!world.terrainDirty && this.cachedThemeId === world.themeId) return;
    const theme = THEME_CONFIGS[world.themeId] || THEME_CONFIGS[0];
    const tilePack = this.assets?.sprites?.[theme.tilePack];
    const tilePixels = tilePack?.rleRawRgba?.[0];
    const topABGR = this.packCssToU32ABGR(theme.crustTop);
    const subABGR = this.packCssToU32ABGR(theme.crustSub);
    const scorchABGR = 0xff16110e;
    const fallbackDirtABGR = 0xff2b4c6e;

    const rects = this.cachedThemeId !== world.themeId
      ? [{ x: 0, y: 0, w: MASK_W, h: MASK_H }]
      : world.dirtyRects;
    this.cachedThemeId = world.themeId;

    for (const rc of rects) {
      const x0 = Math.max(0, rc.x | 0);
      const y0 = Math.max(0, rc.y | 0);
      const x1 = Math.min(MASK_W - 1, (rc.x + rc.w) | 0);
      const y1 = Math.min(MASK_H - 1, (rc.y + rc.h) | 0);
      for (let y = y0; y <= y1; y++) {
        const ty = y & 31;
        const rowOff = y * MASK_W;
        const tRow = ty * 32;
        for (let x = x0; x <= x1; x++) {
          const idx = rowOff + x;
          if (world.mask[idx] === 0) {
            this.terrainPixels32[idx] = 0;
            continue;
          }
          const c = world.crust[idx];
          if (c === 1) this.terrainPixels32[idx] = topABGR;
          else if (c === 2) this.terrainPixels32[idx] = subABGR;
          else if (c === 3) this.terrainPixels32[idx] = scorchABGR;
          else this.terrainPixels32[idx] = tilePixels ? tilePixels[tRow + (x & 31)] : fallbackDirtABGR;
        }
      }
    }

    this.terrainCtx.putImageData(this.terrainImgData, 0, 0);

    // Sync minimap bitmap
    for (let i = 0; i < world.minimap.length; i++) {
      this.minimapPixels32[i] = world.minimap[i] ? 0xff46c88a : 0x00000000;
    }
    this.minimapCtx.putImageData(this.minimapImgData, 0, 0);

    world.dirtyRects.length = 0;
    world.terrainDirty = false;
  }

  renderFrame(ctx, world) {
    ctx.imageSmoothingEnabled = false;

    // Boot / EA / Team17 / Title Splashes (y.java:478-499, 1023-1030)
    if (world.o === 0) {
      fillRectMIDP(ctx, 0, 0, SCREEN_W, SCREEN_H, '#000000');
      return;
    }
    if (world.o === 1 || world.o === 2 || world.o === 17) {
      fillRectMIDP(ctx, 0, 0, SCREEN_W, SCREEN_H, world.o === 17 ? '#000000' : '#ffffff');
      const spIdx = world.o === 1 ? 0 : world.o === 2 ? 1 : 2;
      const img = this.assets?.splashes?.[spIdx];
      if (img) drawImageAnchor(ctx, img, SCREEN_W >> 1, SCREEN_H >> 1, 3);
      return;
    }

    if (world.o === 4) {
      this.renderMenus(ctx, world);
      return;
    }

    if (world.o === 6 || world.o === 7 || world.o === 9) {
      this.renderMatchScene(ctx, world);
      if (world.o === 7) this.renderPauseOverlay(ctx, world);
      else if (world.o === 9) this.renderResultsOverlay(ctx, world);
      return;
    }
  }

  // ==================== MENUS & CHROME (y.java:550-810, 2723-3015) ====================

  renderMenuBackground(ctx, world) {
    fillRectMIDP(ctx, 0, 0, SCREEN_W, SCREEN_H, '#11115c'); // y.java:2724 sky theme backdrop
    // Parallax theme backdrop pinned flush to bottom edge so zero cyan seam shows underneath
    const bgPack = this.assets?.sprites?.[26];
    if (bgPack?.sheet) {
      const tileW = bgPack.modW[0] || 176;
      const tileH = bgPack.modH[0] || 220;
      const parX = -Math.round((world.menuTick * 0.45) % tileW);
      const yTop = SCREEN_H - tileH;
      for (let x = parX; x < SCREEN_W; x += tileW) {
        bgPack.drawModule(ctx, 0, x, yTop, 0);
      }
    }
  }

  drawSoftkeys(ctx, leftIcon = 33, rightIcon = 29) {
    const ac14 = this.assets?.sprites?.[14];
    if (!ac14) return;
    if (leftIcon >= 0) {
      ac14.drawFrame(ctx, 41, 0, SCREEN_H, 0);
      ac14.drawFrame(ctx, leftIcon, 10, SCREEN_H - 7, 0);
    }
    if (rightIcon >= 0) {
      ac14.drawFrame(ctx, 40, SCREEN_W, SCREEN_H, 0);
      ac14.drawFrame(ctx, rightIcon, SCREEN_W - 14, SCREEN_H - 7, 0);
    }
  }

  renderMenus(ctx, world) {
    this.renderMenuBackground(ctx, world);
    const ac13 = this.assets?.sprites?.[13];
    const ac14 = this.assets?.sprites?.[14];
    const fonts = this.assets?.fonts;
    const locale = this.assets?.locale;
    if (!fonts || !locale) return;

    // Outer metallic/blue bezel panel (ac[14] frame 0/1) centered at y=132 so top + bottom sit neatly in viewport
    if (ac14) {
      ac14.drawFrame(ctx, world.cm === 0 ? 1 : 0, SCREEN_W >> 1, 134, 0);
    }
    // Animated Worms logo banner ac[13] frame 1 rendered ABOVE menu panel with top margin (y.java:2739)
    if (ac13) ac13.drawFrame(ctx, 1, SCREEN_W >> 1, 31, 0);

    // Header Title Pill
    let titleText = 'WORMS RELOADED';
    if (world.cm === 0) titleText = locale.getString(11); // MAIN MENU
    else if (world.cm === 1) titleText = locale.getString(13); // PLAY
    else if (world.cm === 16) titleText = locale.getString(17); // CAMPAIGN
    else if (world.cm === 2) titleText = locale.getString(38); // CUSTOM GAME
    else if (world.cm === 11) titleText = locale.getString(27); // OPTIONS
    else if (world.cm === 13) titleText = locale.getString(46); // CHOOSE LANGUAGE
    else if (world.cm === 29) titleText = locale.getString(25); // SHOP
    else if (world.cm === 26) titleText = locale.getString(23); // ACHIEVEMENTS
    else if (world.cm === 22) titleText = locale.getString(16); // HELP
    else if (world.cm === 14) titleText = locale.getString(24); // ABOUT

    fillRoundRectMIDP(ctx, 64, 52, SCREEN_W - 128, 20, 8, 8, '#0b3559');
    drawRoundRectMIDP(ctx, 64, 52, SCREEN_W - 128, 20, 8, 8, '#66b8ff');
    fonts.cyan.drawString(ctx, titleText, SCREEN_W >> 1, 59 - 4, 3);

    // Info Screens: Help (22) & About (14)
    if (world.cm === 22 || world.cm === 14) {
      fillARGBRectMIDP(ctx, 0xc8051a30, 26, 76, SCREEN_W - 52, 134);
      drawRectMIDP(ctx, 26, 76, SCREEN_W - 52, 134, '#5094ce');
      const bodyStr = world.cm === 14
        ? `${locale.getString(51, '4.9.10')}`
        : `${locale.getString(140)}|${locale.getString(399)}|${locale.getString(400)}`;
      const lines = fonts.white.wrapLines(bodyStr, SCREEN_W - 72);
      const maxL = Math.min(7, lines.length);
      for (let i = 0; i < maxL; i++) {
        fonts.white.drawString(ctx, lines[i], SCREEN_W >> 1, 86 + i * 17, 17);
      }
      this.drawSoftkeys(ctx, 33, 29);
      return;
    }

    // Shop header balance bar
    if (world.cm === 29) {
      fillRoundRectMIDP(ctx, 76, 72, SCREEN_W - 152, 18, 6, 6, '#143f66');
      const ac15 = this.assets?.sprites?.[15];
      if (ac15) ac15.drawFrame(ctx, 35, 92, 81, 0);
      fonts.white.drawString(ctx, `${world.save.coins}`, (SCREEN_W >> 1) + 10, 77, 3);
    }

    // List Items
    const items = world.getMenuItems();
    const isGrid = world.cm === 16;

    if (isGrid) {
      // 28 Campaign Levels in 4x7 or paged selector (y.java:722-785)
      const cols = 7;
      const startX = 36;
      const startY = 82;
      const cell = 36;
      for (let i = 0; i < items.length; i++) {
        const col = i % cols;
        const row = Math.floor(i / cols);
        const cx = startX + col * cell + 16;
        const cy = startY + row * 28;
        const sel = i === world.cn;
        const unlocked = items[i].unlocked;
        const bg = sel ? '#dd7200' : (unlocked ? '#1c72a4' : '#3a4a5a');
        const bd = sel ? '#fff299' : (unlocked ? '#4fa8db' : '#556575');
        fillRoundRectMIDP(ctx, cx - 15, cy - 11, 30, 22, 6, 6, bg);
        drawRoundRectMIDP(ctx, cx - 15, cy - 11, 30, 22, 6, 6, bd);
        if (world.save.campaignStars[i]) {
          drawRectMIDP(ctx, cx + 8, cy - 8, 4, 4, '#ffee44');
        }
        fonts.white.drawString(ctx, `${i + 1}`, cx, cy, 3);
      }
      const themeName = locale.getString(THEME_CONFIGS[Math.floor(world.cn / 7)]?.nameId || 105);
      const hintStr = `${locale.getString(209)} ${world.cn + 1} · ${themeName}`;
      fillRoundRectMIDP(ctx, 44, 190, SCREEN_W - 88, 22, 6, 6, '#0a2540');
      drawRoundRectMIDP(ctx, 44, 190, SCREEN_W - 88, 22, 6, 6, '#4fa8db');
      withMIDPClip(ctx, 48, 191, SCREEN_W - 96, 20, () => {
        const tw = fonts.cyan.measureWidth(hintStr);
        const boxW = SCREEN_W - 96;
        if (tw <= boxW) {
          fonts.cyan.drawString(ctx, hintStr, SCREEN_W >> 1, 191, 17);
        } else {
          const sx = 52 - ((world.menuTick * 2) % (tw + 40));
          fonts.cyan.drawString(ctx, hintStr, sx, 191, 20);
          fonts.cyan.drawString(ctx, hintStr, sx + tw + 40, 191, 20);
        }
      });
      this.drawSoftkeys(ctx, items[world.cn]?.unlocked ? 33 : -1, 29);
      return;
    }

    // Vertical Pill List (4 visible rows cleanly fitting 78..210)
    const maxVisible = 5;
    const itemStart = Math.max(0, Math.min(Math.max(0, items.length - maxVisible), world.cn - 2));
    const topY = world.cm === 29 ? 98 : 84;
    const stepY = world.cm === 29 ? 23 : 25;

    for (let v = 0; v < maxVisible && itemStart + v < items.length; v++) {
      const idx = itemStart + v;
      const it = items[idx];
      const sel = idx === world.cn;
      const y = topY + v * stepY;

      if (sel) {
        fillRoundRectMIDP(ctx, 42, y - 10, SCREEN_W - 84, 21, 9, 9, '#dd7200'); // y.java:2981
        drawRoundRectMIDP(ctx, 42, y - 10, SCREEN_W - 84, 21, 9, 9, '#ffd878');
      } else {
        fillARGBRectMIDP(ctx, 0x80103a62, 42, y - 10, SCREEN_W - 84, 21);
      }

      let label = '';
      if (it.customLabel) label = it.customLabel;
      else if (it.text) label = it.text;
      else if (it.pack) {
        const wName = locale.getString(345 + it.pack.weaponId);
        label = `${wName} - ${it.pack.cost}`;
      } else if (it.achIdx !== undefined) {
        label = `#${it.achIdx + 1} ${it.unlocked ? '★' : '---'}`;
      } else if (it.key) {
        const keyMap = { numTeams: 'TEAMS', wormsPerTeam: 'WORMS', themeId: 'THEME', mapStyle: 'MAP', turnTime: 'TIME' };
        label = `${keyMap[it.key] || it.key}: < ${it.value} >`;
      } else if (it.val !== undefined) {
        label = `${locale.getString(it.labelId)} ${locale.getString(it.val)}`;
      } else if (it.valText !== undefined) {
        label = `${locale.getString(it.labelId)}: ${it.valText}`;
      } else if (it.labelId !== undefined) {
        label = locale.getString(it.labelId);
      }

      const fontToUse = sel ? fonts.white : fonts.cyan;
      fontToUse.drawString(ctx, label, SCREEN_W >> 1, y - 4, 3);
    }

    // Up / Down arrows (ac[14] frames 14..17)
    if (ac14) {
      if (itemStart > 0) ac14.drawFrame(ctx, 15, SCREEN_W >> 1, topY - 14, 0);
      if (itemStart + maxVisible < items.length) ac14.drawFrame(ctx, 17, SCREEN_W >> 1, topY + maxVisible * stepY - 4, 0);
    }

    this.drawSoftkeys(ctx, 33, world.cm === 0 || world.cm === 13 ? -1 : 29);
  }

  // ==================== IN-MATCH SCENE (a.java + ah.java + y.java) ====================

  renderMatchScene(ctx, world) {
    this.syncTerrainCache(world);

    const shakeX = world.shakeTicks > 0 ? ((Math.random() - 0.5) * 2 * world.shakeMag) | 0 : 0;
    const shakeY = world.shakeTicks > 0 ? ((Math.random() - 0.5) * 2 * world.shakeMag) | 0 : 0;
    const cx = Math.max(0, Math.min(WORLD_W - SCREEN_W, Math.round(world.smoothCamX) + shakeX));
    const cy = Math.max(0, Math.min(WORLD_H - SCREEN_H, Math.round(world.smoothCamY) + shakeY));

    const theme = THEME_CONFIGS[world.themeId] || THEME_CONFIGS[0];

    // 1. Sky Fill + Gradient Horizon + Parallax Backdrop (a.java:1810-1850)
    fillRectMIDP(ctx, 0, 0, SCREEN_W, SCREEN_H, theme.skyHex);
    fillARGBRectMIDP(ctx, 0x44ffffff & 0x00ffffff, 0, 0, 0, 0);
    const bgPack = this.assets?.sprites?.[theme.bgPack];
    if (bgPack?.sheet) {
      const tileW = bgPack.modW[0] || 176;
      const tileH = bgPack.modH[0] || 220;
      const parX = -Math.round((cx * 0.45) % tileW);
      const bgTop = (world.waterY - tileH + 24) - cy;
      for (let x = parX; x < SCREEN_W; x += tileW) {
        bgPack.drawModule(ctx, 0, x, bgTop, 0);
      }
    }

    // 2. Clouds (world space)
    for (const cl of world.clouds) {
      const sx = Math.round(cl.x - cx * 0.65);
      const sy = Math.round(cl.y - cy * 0.4);
      if (sx > -50 && sx < SCREEN_W + 50) {
        fillARGBRectMIDP(ctx, 0x55ffffff, sx - 18, sy, 36, 9);
        fillARGBRectMIDP(ctx, 0x66ffffff, sx - 11, sy - 5, 22, 6);
      }
    }

    // 3. Cached Destructible Terrain Surface (a.java:1852-1907)
    const terrainScreenX = MARGIN_X - cx;
    const terrainScreenY = MARGIN_Y - cy;
    ctx.drawImage(this.terrainCanvas, terrainScreenX, terrainScreenY);

    // 4. Forts Decorative Overlay (ac[17..20])
    for (const f of world.forts) {
      const fp = this.assets?.sprites?.[f.packIdx];
      if (fp) fp.drawFrame(ctx, 0, Math.round(f.wx - cx), Math.round(f.wy - cy), 0);
    }

    // 5. Gravestones (ac[16]), Crates, Mines, Drums
    const ac16 = this.assets?.sprites?.[16];
    const ac2 = this.assets?.sprites?.[2];
    const ac10 = this.assets?.sprites?.[10];
    for (const g of world.graves) {
      const gx = Math.round(g.x - cx);
      const gy = Math.round(g.y - cy);
      if (ac16) ac16.drawFrame(ctx, g.team % Math.max(1, ac16.numFrames), gx, gy, 0);
    }
    for (const d of world.drums) {
      if (!d.alive) continue;
      const dx = Math.round(d.x - cx);
      const dy = Math.round(d.y - cy);
      if (ac2 && ac2.numFrames > 233) ac2.drawFrame(ctx, 233, dx, dy, 0);
      else {
        fillRectMIDP(ctx, dx - 6, dy - 10, 12, 16, '#bb2211');
        fillRectMIDP(ctx, dx - 6, dy - 4, 12, 3, '#ffcc22');
      }
    }
    for (const m of world.mines) {
      const mx = Math.round(m.x - cx);
      const my = Math.round(m.y - cy);
      if (ac2 && ac2.numFrames > 111) {
        const frame = 109 + ((m.triggered ? (world.tickCount % 3) : 0));
        ac2.drawFrame(ctx, frame, mx, my, 0);
      } else {
        fillRectMIDP(ctx, mx - 4, my - 3, 8, 5, m.triggered && (world.tickCount % 4 < 2) ? '#ff2200' : '#444444');
      }
    }
    for (const cr of world.crates) {
      const kx = Math.round(cr.x - cx);
      const ky = Math.round(cr.y - cy);
      if (!cr.onGround) {
        drawLineMIDP(ctx, kx, ky - 5, kx - 6, ky - 14, '#dddddd');
        drawLineMIDP(ctx, kx, ky - 5, kx + 6, ky - 14, '#dddddd');
        fillRoundRectMIDP(ctx, kx - 8, ky - 18, 16, 6, 4, 4, '#ffffff');
      }
      if (ac10 && cr.kind === 'health') ac10.drawFrame(ctx, 26, kx, ky - 6, 0);
      else if (ac10 && cr.kind === 'coin') ac10.drawFrame(ctx, 25, kx, ky - 6, 0);
      else {
        fillRectMIDP(ctx, kx - 6, ky - 6, 12, 12, cr.kind === 'health' ? '#eeeeee' : '#a8692c');
        drawRectMIDP(ctx, kx - 6, ky - 6, 12, 12, '#3a220c');
        fillRectMIDP(ctx, kx - 1, ky - 4, 2, 8, cr.kind === 'health' ? '#dd2222' : '#ffdd44');
        fillRectMIDP(ctx, kx - 4, ky - 1, 8, 2, cr.kind === 'health' ? '#dd2222' : '#ffdd44');
      }
    }

    // 6. Worms (`ac[0]` + `ac[4]` Hats + `ac[2]` Held Weapons + Health Tags) (ah.java:2774-2894)
    const ac0 = this.assets?.sprites?.[0];
    const ac3 = this.assets?.sprites?.[3];
    const ac4 = this.assets?.sprites?.[4];
    const ac7 = this.assets?.sprites?.[7];
    const ac11 = this.assets?.sprites?.[11];
    const fonts = this.assets?.fonts;

    for (let i = 0; i < world.worms.length; i++) {
      const w = world.worms[i];
      if (!w.alive) continue;
      const wx = Math.round(w.x - cx);
      const wy = Math.round(w.y - cy);
      if (wx < -30 || wx > SCREEN_W + 30 || wy < -40 || wy > SCREEN_H + 40) continue;

      const isActive = i === world.activeWormIdx && world.phase !== 'RESOLVE';
      const flipFlag = w.dir === 1 ? 0 : 1; // ac.java:1=flipX when facing right vs left! Wait: check orientation visually if needed!

      let animId = 7; // idle
      if (w.state === 'JETPACK') animId = 39;
      else if (w.parachuteOpen) animId = 65;
      else if (!w.onGround && Math.abs(w.vy) > 1.5) animId = 8;
      else if (w.state === 'WALK') animId = 11;
      else if (isActive && world.phase === 'PLAY') {
        animId = Math.min(37, 27 + Math.floor((11 * w.aimAngle) / 180));
      }

      if (ac0) {
        const frameIdx = ac0.resolveAnimFrameIndex(animId, w.animTick, true);
        ac0.drawAnim(ctx, animId, frameIdx, wx, wy, flipFlag, {
          hat: TEAM_STYLES[w.team]?.hat ?? 0,
          hatsPack: ac4,
          weaponId: isActive ? w.weaponId : -1,
          handsPack: ac2,
          weaponFrameFn: getHeldWeaponFrame,
          fxPack: ac3,
          tick: world.tickCount,
        });
      } else {
        fillRoundRectMIDP(ctx, wx - 5, wy - 12, 10, 14, 4, 4, '#ffaaaa');
      }

      // Parachute canopy
      if (w.parachuteOpen) {
        fillRoundRectMIDP(ctx, wx - 11, wy - 28, 22, 10, 8, 8, '#f0f0f0');
        drawLineMIDP(ctx, wx - 9, wy - 19, wx, wy - 8, '#cccccc');
        drawLineMIDP(ctx, wx + 9, wy - 19, wx, wy - 8, '#cccccc');
      }

      // Marked for Death skull
      if (w.markedForDeath && ac3) {
        ac3.drawAnim(ctx, 22, world.tickCount % 12, wx, wy - 44, 0);
      }

      // Health tag centered inside colored pill above worm (ah.java:2780; EAFont digits sit +10px below y)
      if (fonts) {
        const tStyle = TEAM_STYLES[w.team] || TEAM_STYLES[0];
        const hpStr = `${w.hp}`;
        const tagW = Math.max(22, fonts.white.measureWidth(hpStr) + 8);
        const bx = wx - (tagW >> 1);
        const by = wy - 40;
        fillARGBRectMIDP(ctx, 0xc8040a14, bx, by, tagW, 14);
        drawRectMIDP(ctx, bx, by, tagW, 14, tStyle.hex);
        const fnt = w.team === 0 ? fonts.red : w.team === 1 ? fonts.cyan : fonts.white;
        fnt.drawString(ctx, hpStr, wx, by - 7, 17);
      }

      // Active Aiming Crosshair & Power Charge Dots (ah.java:5921-5946)
      if (isActive && (world.phase === 'PLAY' || world.phase === 'INTRO')) {
        const dirX = w.dir * Math.sin((w.aimAngle * Math.PI) / 180);
        const dirY = -Math.cos((w.aimAngle * Math.PI) / 180);
        if (ac11) {
          const crossX = Math.round(wx + dirX * 52);
          const crossY = Math.round(wy - 6 + dirY * 52);
          ac11.drawAnim(ctx, 0, Math.floor(world.tickCount / 5) % 4, crossX, crossY, flipFlag);
        }
        if (world.charging && ac7) {
          const dots = Math.min(5, Math.ceil(world.chargePower / 20));
          for (let d = 0; d < dots; d++) {
            const dist = 14 + d * 8;
            ac7.drawFrame(ctx, 7 + d, Math.round(wx + dirX * dist), Math.round(wy - 6 + dirY * dist), 0);
          }
        }
      }
    }

    // 7. Flames, Projectiles & Explosions
    for (const f of world.flames) {
      const fx = Math.round(f.x - cx);
      const fy = Math.round(f.y - cy);
      if (ac3) ac3.drawAnim(ctx, 19, world.tickCount % 2, fx, fy, 0);
      else fillRectMIDP(ctx, fx - 3, fy - 6, 6, 8, '#ff7711');
    }

    for (const p of world.projectiles) {
      const px = Math.round(p.x - cx);
      const py = Math.round(p.y - cy);
      if (p.kind === 28) { // Concrete Donkey
        fillRoundRectMIDP(ctx, px - 12, py - 14, 24, 24, 5, 5, '#8c949e');
        drawRectMIDP(ctx, px - 12, py - 14, 24, 24, '#444b54');
      } else if (ac2 && p.kind < ac2.numFrames) {
        const frameId = p.kind === 1 ? 68 : p.kind === 19 ? 209 : p.kind === 10 ? 79 : p.kind === 31 ? 108 : p.kind;
        ac2.drawFrame(ctx, Math.min(ac2.numFrames - 1, Math.max(0, frameId)), px, py, p.vx < 0 ? 1 : 0);
      } else {
        fillRoundRectMIDP(ctx, px - 3, py - 3, 6, 6, 3, 3, p.kind === 25 ? '#ffe833' : '#ff4422');
      }
    }

    for (const part of world.particles) {
      const px = Math.round(part.x - cx);
      const py = Math.round(part.y - cy);
      if (part.kind === 'blast') {
        const t = 1 - part.ttl / part.maxTtl;
        if (ac7 && ac7.numAnims > 0) {
          const fi = Math.min(ac7.getAnimFrameCount(0) - 1, Math.floor(t * ac7.getAnimFrameCount(0)));
          ac7.drawAnim(ctx, 0, fi, px, py, 0);
        } else {
          fillRoundRectMIDP(ctx, px - 16, py - 16, 32, 32, 16, 16, '#ffaa22');
        }
      } else if (part.kind === 'lightning') {
        drawLineMIDP(ctx, px, 0, px - 6, Math.round(py * 0.5), '#ffffff');
        drawLineMIDP(ctx, px - 6, Math.round(py * 0.5), px, py, '#99eeff');
      } else if (part.kind === 'smoke') {
        fillARGBRectMIDP(ctx, 0x66eeeeee, px - 2, py - 2, 4, 4);
      } else {
        fillRectMIDP(ctx, px - 1, py - 1, 3, 3, part.color);
      }
    }

    // 8. Target Cursor (p === 'TARGET')
    if (world.phase === 'TARGET' && ac11) {
      const tx = Math.round(world.targetCursor.x - cx);
      const ty = Math.round(world.targetCursor.y - cy);
      ac11.drawAnim(ctx, 0, (world.tickCount >> 1) % 4, tx, ty, 0);
    }

    // 9. Animated Water Overlay (y.java:6958-6968)
    const waterScreenY = Math.round(world.waterY - cy);
    if (waterScreenY < SCREEN_H) {
      fillARGBRectMIDP(ctx, 0xb82e6dc7, 0, waterScreenY + 12, SCREEN_W, SCREEN_H - waterScreenY);
      const ac9 = this.assets?.sprites?.[9];
      if (ac9 && ac9.sheet) {
        const tileW = ac9.modW[0] || 24;
        const step = tileW > 0 ? tileW : 24;
        for (let wx = -((world.tickCount * 1) % step); wx < SCREEN_W + step; wx += step) {
          ac9.drawAnim(ctx, 0, Math.floor(world.tickCount / 3) % Math.max(1, ac9.getAnimFrameCount(0)), wx, waterScreenY, 0);
        }
      }
    }

    // 10. Floating Damage / Pickup Popups
    for (const pop of world.popups) {
      if (fonts) {
        const px = Math.round(pop.x - cx);
        const py = Math.round(pop.y - cy);
        fonts.white.drawString(ctx, pop.text, px, py, 3);
      }
    }

    // 11. Match HUD (y.java:6616-6899)
    this.renderMatchHUD(ctx, world);

    // 12. Slide-in 9x4 Weapon Backpack Drawer (y.java:5290-5348)
    if (world.phase === 'BACKPACK' || world.backpackSlide < SCREEN_W) {
      this.renderBackpackDrawer(ctx, world);
    }
  }

  renderMatchHUD(ctx, world) {
    const ac5 = this.assets?.sprites?.[5];
    const ac1 = this.assets?.sprites?.[1];
    const fonts = this.assets?.fonts;
    const locale = this.assets?.locale;

    // Top-right Wind Bar & Turn Timer (y.java:6616-6739)
    if (ac5) {
      ac5.drawFrame(ctx, 20, SCREEN_W - 42, SCREEN_H - 36, 0); // Clock pill clear of right softkey
      const windSec = Math.round((world.windDisplay / 100) * 44);
      fillRoundRectMIDP(ctx, SCREEN_W - 96, 6, 90, 14, 6, 6, '#0a1f36');
      drawRoundRectMIDP(ctx, SCREEN_W - 96, 6, 90, 14, 6, 6, '#5bb4f2');
      fillRectMIDP(ctx, SCREEN_W - 51, 9, 1, 8, '#ffffff');
      if (windSec !== 0) {
        const bx = windSec > 0 ? SCREEN_W - 50 : SCREEN_W - 51 + windSec;
        fillRectMIDP(ctx, bx, 9, Math.abs(windSec), 8, windSec > 0 ? '#55ee77' : '#ffaa33');
      }
    }

    // Turn countdown number bottom-right aligned cleanly inside clock frame (to the left of || glyph)
    const secLeft = Math.max(0, Math.ceil(world.turnMsLeft / 1000));
    if (fonts) {
      const fnt = (secLeft <= 5 && world.tickCount % 10 < 5) ? fonts.red : fonts.white;
      fnt.drawString(ctx, `${secLeft}`, SCREEN_W - 49, SCREEN_H - 50, 17);
    }

    // Active Weapon Slot Badge bottom-left (above LSK)
    const aw = world.getActiveWorm();
    if (aw && world.phase === 'PLAY') {
      if (ac1) ac1.drawFrame(ctx, aw.weaponId, 26, SCREEN_H - 36, 0);
      if (world.charging) {
        fillRoundRectMIDP(ctx, 50, SCREEN_H - 20, 82, 10, 4, 4, '#111111');
        fillRectMIDP(ctx, 52, SCREEN_H - 18, Math.round((world.chargePower / 100) * 78), 6, '#ff6622');
      }
    }

    // Team Total HP Bars Top-Left
    if (fonts) {
      for (let t = 0; t < world.numTeams; t++) {
        const team = world.teams[t];
        const sumHp = world.worms.filter((w) => w.team === t && w.alive).reduce((a, b) => a + b.hp, 0);
        const y = 6 + t * 13;
        fillARGBRectMIDP(ctx, 0x99081422, 6, y, 76, 11);
        const barW = Math.min(72, Math.round((sumHp / (world.wormsPerTeam * 100)) * 72));
        if (barW > 0) fillRectMIDP(ctx, 8, y + 2, barW, 7, team.hex);
        drawRectMIDP(ctx, 6, y, 76, 11, team.hex);
      }
    }

    // Slide-down Minimap (KEY 7) (y.java:6883-6956)
    if (world.minimapSlide > -64) {
      const mx = (SCREEN_W - MINIMAP_W) >> 1;
      const my = Math.round(world.minimapSlide);
      fillARGBRectMIDP(ctx, 0xcc081628, mx - 4, my, MINIMAP_W + 8, MINIMAP_H + 6);
      drawRectMIDP(ctx, mx - 4, my, MINIMAP_W + 8, MINIMAP_H + 6, '#62b6ff');
      ctx.drawImage(this.minimapCanvas, mx, my + 2);
      for (const w of world.worms) {
        if (!w.alive) continue;
        const dx = mx + Math.round(((w.x - MARGIN_X) * MINIMAP_W) / MASK_W);
        const dy = my + 2 + Math.round(((w.y - MARGIN_Y) * MINIMAP_H) / MASK_H);
        fillRectMIDP(ctx, dx - 1, dy - 1, 3, 3, TEAM_STYLES[w.team]?.hex || '#ffffff');
      }
    }

    // Top Turn / Action Banner (hidden while slide-down minimap is open so zero overlap)
    if (world.bannerMs > 0 && world.bannerText && fonts && !world.minimapOpen) {
      fillARGBRectMIDP(ctx, 0xb8081426, 40, 40, SCREEN_W - 80, 24);
      drawRectMIDP(ctx, 40, 40, SCREEN_W - 80, 24, '#ffd84c');
      fonts.white.drawString(ctx, world.bannerText, SCREEN_W >> 1, 41, 17);
    }

    // Softkey buttons (Left: Backpack 32 / Select 33; Right: Pause 35 / Back 29)
    this.drawSoftkeys(ctx, world.phase === 'BACKPACK' ? 33 : 32, 35);
  }

  renderBackpackDrawer(ctx, world) {
    const ac1 = this.assets?.sprites?.[1];
    const fonts = this.assets?.fonts;
    const locale = this.assets?.locale;
    const dx = Math.round(world.backpackSlide);
    const team = world.teams[world.activeTeamIdx];

    fillRectMIDP(ctx, dx, 0, SCREEN_W, SCREEN_H, '#054f77'); // y.java:5295 (348023)
    fillRoundRectMIDP(ctx, dx + 4, 4, SCREEN_W - 8, SCREEN_H - 12, 8, 8, '#0788ff'); // y.java:5297
    fillRectMIDP(ctx, dx + 8, 8, SCREEN_W - 16, SCREEN_H - 20, '#073d66');

    const startX = dx + 22;
    const startY = 30;
    for (let id = 0; id < 36; id++) {
      const col = id % 9;
      const row = Math.floor(id / 9);
      const sx = startX + col * 31;
      const sy = startY + row * 31;
      const qty = team?.ammo[id] ?? 0;
      const sel = id === world.backpackCursor;

      if (ac1) {
        ac1.drawFrame(ctx, 36, sx, sy, 0);
        if (sel) ac1.drawFrame(ctx, qty === 0 ? 38 : 37, sx, sy, 0);
        if (qty !== 0) ac1.drawFrame(ctx, id, sx, sy, 0);
      } else {
        fillRectMIDP(ctx, sx - 12, sy - 12, 24, 24, sel ? '#dd7200' : '#124b78');
      }

      if (fonts && qty > 0) {
        fonts.white.drawString(ctx, `x${qty}`, sx - 12, sy - 14, 20);
      }
    }

    if (fonts && locale) {
      const wName = locale.getString(345 + world.backpackCursor);
      fillRoundRectMIDP(ctx, dx + 24, 152, SCREEN_W - 48, 26, 6, 6, '#052742');
      fonts.cyan.drawString(ctx, wName, dx + (SCREEN_W >> 1), 161, 3);
    }

    this.drawSoftkeys(ctx, 33, 29);
  }

  renderPauseOverlay(ctx, world) {
    fillARGBRectMIDP(ctx, 0xbb04101e, 0, 0, SCREEN_W, SCREEN_H);
    const fonts = this.assets?.fonts;
    const locale = this.assets?.locale;
    if (!fonts || !locale) return;

    fillRoundRectMIDP(ctx, 48, 38, SCREEN_W - 96, 158, 10, 10, '#0a3a66');
    drawRoundRectMIDP(ctx, 48, 38, SCREEN_W - 96, 158, 10, 10, '#66b8ff');
    fonts.cyan.drawString(ctx, locale.getString(36), SCREEN_W >> 1, 52, 3); // GAME PAUSED

    const labels = [
      locale.getString(14), // RESUME
      locale.getString(15), // RESTART
      locale.getString(27), // OPTIONS
      locale.getString(11), // MAIN MENU
    ];
    for (let i = 0; i < labels.length; i++) {
      const sel = i === world.cn;
      const y = 84 + i * 25;
      if (sel) fillRoundRectMIDP(ctx, 70, y - 9, SCREEN_W - 140, 19, 6, 6, '#dd7200');
      fonts.white.drawString(ctx, labels[i], SCREEN_W >> 1, y - 4, 3);
    }
    this.drawSoftkeys(ctx, 33, 29);
  }

  renderResultsOverlay(ctx, world) {
    fillARGBRectMIDP(ctx, 0xc204101e, 0, 0, SCREEN_W, SCREEN_H);
    const fonts = this.assets?.fonts;
    const locale = this.assets?.locale;
    if (!fonts || !locale) return;

    fillRoundRectMIDP(ctx, 34, 24, SCREEN_W - 68, 180, 10, 10, '#0c3b68');
    drawRoundRectMIDP(ctx, 34, 24, SCREEN_W - 68, 180, 10, 10, '#ffaa22');

    const headline = world.matchResult === 'WIN' ? locale.getString(54) : world.matchResult === 'DRAW' ? locale.getString(52) : locale.getString(55);
    fonts.white.drawString(ctx, headline, SCREEN_W >> 1, 48, 3);

    fonts.cyan.drawString(ctx, `${locale.getString(425)}: ${world.matchStats.damageDealt}`, SCREEN_W >> 1, 88, 3);
    fonts.white.drawString(ctx, `KILLS: ${world.matchStats.kills}`, SCREEN_W >> 1, 114, 3);
    fonts.cyan.drawString(ctx, `+${world.matchStats.coinsEarned} COINS`, SCREEN_W >> 1, 140, 3);
    fonts.white.drawString(ctx, locale.getString(12), SCREEN_W >> 1, 178, 3);
    this.drawSoftkeys(ctx, 33, -1);
  }
}
