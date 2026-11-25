/**
 * MIDP 2.0 Graphics, Fixed-Point (16.16) & Transform Primitives
 * Port of n.java:1-61, ae.java:45-101, ac.java:5,209-272
 * Zero invented graphics paths: integer coordinates, NEAREST sampling,
 * exact Sprite.TRANS_* orientation & MIDP anchor bitmasks.
 */

export const FP_SHIFT = 16;
export const FP_ONE = 65536;

// Anchor bitmasks (javax.microedition.lcdui.Graphics)
export const HCENTER = 1;
export const VCENTER = 2;
export const LEFT = 4;
export const RIGHT = 8;
export const TOP = 16;
export const BOTTOM = 32;
export const BASELINE = 64;

// MIDP 2.0 Sprite transforms (ac.java:5 maps ac 0..7 -> h[] = [0, 2, 1, 3, 5, 7, 4, 6])
export const TRANS_NONE = 0;
export const TRANS_MIRROR_ROT180 = 1; // Flip Y
export const TRANS_MIRROR = 2;        // Flip X
export const TRANS_ROT180 = 3;        // Rotate 180
export const TRANS_MIRROR_ROT270 = 4;
export const TRANS_ROT90 = 5;         // Rotate 90 CW
export const TRANS_ROT270 = 6;        // Rotate 270 CW (90 CCW)
export const TRANS_MIRROR_ROT90 = 7;

export const AC_TO_MIDP_TRANS = [0, 2, 1, 3, 5, 7, 4, 6]; // ac.java:5

export function s8(v) {
  return (v << 24) >> 24;
}

export function u8(v) {
  return v & 0xff;
}

export function s16(v) {
  return (v << 16) >> 16;
}

export function u16(v) {
  return v & 0xffff;
}

export function s32(v) {
  return v | 0;
}

export function idiv(a, b) {
  return (a / b) | 0;
}

export function fpMul(a, b) {
  // Exact 32x32 -> 64-bit >> 16 fixed point multiply using BigInt to match Java (int)((long)a * b >> 16)
  return Number((BigInt(a | 0) * BigInt(b | 0)) >> 16n) | 0;
}

export function fpDiv(a, b) {
  if ((b | 0) === 0) return 0;
  return Number((BigInt(a | 0) << 16n) / BigInt(b | 0)) | 0;
}

/**
 * Port of ac.java:209-227: compose child transform flag (0..7) with parent flip flag (0..7).
 */
export function composeACTransform(child, parent) {
  if ((child & 4) === 0) {
    return (child ^ parent) & 7;
  }
  let res = child;
  if ((parent & 1) !== 0) res ^= 2;
  if ((parent & 2) !== 0) res ^= 1;
  if ((parent & 4) !== 0) res ^= 7;
  return res & 7;
}

export function applyAnchor(dx, dy, w, h, anchor) {
  let ax = dx | 0;
  let ay = dy | 0;
  const a = anchor || (LEFT | TOP);
  if ((a & HCENTER) !== 0) ax -= (w >> 1);
  else if ((a & RIGHT) !== 0) ax -= w;

  if ((a & VCENTER) !== 0) ay -= (h >> 1);
  else if ((a & (BOTTOM | BASELINE)) !== 0) ay -= h;
  return [ax, ay];
}

export function withMIDPClip(ctx, x, y, w, h, fn) {
  ctx.save();
  ctx.beginPath();
  ctx.rect(x | 0, y | 0, Math.max(0, w | 0), Math.max(0, h | 0));
  ctx.clip();
  try {
    fn();
  } finally {
    ctx.restore();
  }
}

export function colorToCss(argbOrRgb, defaultOpaque = true) {
  const u = argbOrRgb >>> 0;
  if (defaultOpaque && (u & 0xff000000) === 0) {
    const r = (u >> 16) & 0xff;
    const g = (u >> 8) & 0xff;
    const b = u & 0xff;
    return `rgb(${r},${g},${b})`;
  }
  const a = ((u >>> 24) & 0xff) / 255;
  const r = (u >> 16) & 0xff;
  const g = (u >> 8) & 0xff;
  const b = u & 0xff;
  return `rgba(${r},${g},${b},${a.toFixed(3)})`;
}

/**
 * Port of Graphics.fillRect with integer pixel coordinates (ae.java:62-67).
 */
export function fillRectMIDP(ctx, x, y, w, h, color) {
  if ((w | 0) <= 0 || (h | 0) <= 0) return;
  if (color !== undefined) {
    ctx.fillStyle = typeof color === 'string' ? color : colorToCss(color, true);
  }
  ctx.fillRect(x | 0, y | 0, w | 0, h | 0);
}

/**
 * Port of y.java translucent/ARGB fill helper a(n, int argb, x, y, w, h).
 */
export function fillARGBRectMIDP(ctx, argb, x, y, w, h) {
  if ((w | 0) <= 0 || (h | 0) <= 0) return;
  const u = argb >>> 0;
  const a = ((u >>> 24) & 0xff) / 255;
  const r = (u >> 16) & 0xff;
  const g = (u >> 8) & 0xff;
  const b = u & 0xff;
  ctx.fillStyle = `rgba(${r},${g},${b},${a.toFixed(3)})`;
  ctx.fillRect(x | 0, y | 0, w | 0, h | 0);
}

/**
 * Port of ae.java:58-60 drawRoundRect without strokeRect fuzzy antialiasing.
 */
export function drawRectMIDP(ctx, x, y, w, h, color) {
  const ix = x | 0;
  const iy = y | 0;
  const iw = w | 0;
  const ih = h | 0;
  if (iw <= 0 || ih <= 0) return;
  if (color !== undefined) {
    ctx.fillStyle = typeof color === 'string' ? color : colorToCss(color, true);
  }
  ctx.fillRect(ix, iy, iw, 1);
  ctx.fillRect(ix, iy + ih - 1, iw, 1);
  ctx.fillRect(ix, iy, 1, ih);
  ctx.fillRect(ix + iw - 1, iy, 1, ih);
}

export function drawLineMIDP(ctx, x0, y0, x1, y1, color) {
  if (color !== undefined) {
    ctx.fillStyle = typeof color === 'string' ? color : colorToCss(color, true);
  }
  let x = x0 | 0;
  let y = y0 | 0;
  const tx = x1 | 0;
  const ty = y1 | 0;
  const dx = Math.abs(tx - x);
  const dy = -Math.abs(ty - y);
  const sx = x < tx ? 1 : -1;
  const sy = y < ty ? 1 : -1;
  let err = dx + dy;
  for (;;) {
    ctx.fillRect(x, y, 1, 1);
    if (x === tx && y === ty) break;
    const e2 = err << 1;
    if (e2 >= dy) { err += dy; x += sx; }
    if (e2 <= dx) { err += dx; y += sy; }
  }
}

export function fillRoundRectMIDP(ctx, x, y, w, h, arcW, arcH, color) {
  const ix = x | 0;
  const iy = y | 0;
  const iw = w | 0;
  const ih = h | 0;
  if (iw <= 0 || ih <= 0) return;
  const r = Math.min(arcW >> 1, arcH >> 1, iw >> 1, ih >> 1);
  if (color !== undefined) {
    ctx.fillStyle = typeof color === 'string' ? color : colorToCss(color, false);
  }
  ctx.beginPath();
  if (r <= 1) {
    ctx.rect(ix, iy, iw, ih);
  } else {
    ctx.roundRect(ix, iy, iw, ih, r);
  }
  ctx.fill();
}

export function drawRoundRectMIDP(ctx, x, y, w, h, arcW, arcH, color) {
  const ix = x | 0;
  const iy = y | 0;
  const iw = w | 0;
  const ih = h | 0;
  if (iw <= 2 || ih <= 2) return;
  const r = Math.min(arcW >> 1, arcH >> 1, (iw - 1) >> 1, (ih - 1) >> 1);
  if (color !== undefined) {
    ctx.strokeStyle = typeof color === 'string' ? color : colorToCss(color, true);
  }
  ctx.lineWidth = 1;
  ctx.beginPath();
  ctx.roundRect(ix + 0.5, iy + 0.5, iw - 1, ih - 1, r);
  ctx.stroke();
}

export function fillTriangleMIDP(ctx, x1, y1, x2, y2, x3, y3, color) {
  if (color !== undefined) {
    ctx.fillStyle = typeof color === 'string' ? color : colorToCss(color, true);
  }
  ctx.beginPath();
  ctx.moveTo(x1 | 0, y1 | 0);
  ctx.lineTo(x2 | 0, y2 | 0);
  ctx.lineTo(x3 | 0, y3 | 0);
  ctx.closePath();
  ctx.fill();
}

/**
 * Port of MIDP Graphics.drawRegion(img, sx, sy, w, h, transform, dx, dy, anchor) (ae.java:79)
 */
export function drawRegionMIDP(ctx, img, sx, sy, w, h, midpTransform, dx, dy, anchor = 20) {
  if (!img || w <= 0 || h <= 0) return;
  const rotSwapped = (midpTransform & 4) !== 0;
  const dstW = rotSwapped ? h : w;
  const dstH = rotSwapped ? w : h;
  const [ax, ay] = applyAnchor(dx, dy, dstW, dstH, anchor);

  if (midpTransform === TRANS_NONE) {
    ctx.drawImage(img, sx | 0, sy | 0, w | 0, h | 0, ax, ay, w | 0, h | 0);
    return;
  }

  ctx.save();
  ctx.translate(ax + dstW * 0.5, ay + dstH * 0.5);
  switch (midpTransform) {
    case TRANS_MIRROR: // 2: flipX
      ctx.scale(-1, 1);
      break;
    case TRANS_MIRROR_ROT180: // 1: flipY
      ctx.scale(1, -1);
      break;
    case TRANS_ROT180: // 3: rot 180
      ctx.rotate(Math.PI);
      break;
    case TRANS_ROT90: // 5: 90 CW
      ctx.rotate(Math.PI * 0.5);
      break;
    case TRANS_ROT270: // 6: 90 CCW
      ctx.rotate(-Math.PI * 0.5);
      break;
    case TRANS_MIRROR_ROT90: // 7: flipX then 90 CW (wait: MIDP TRANS_MIRROR_ROT90 = reflect then rot90)
      ctx.rotate(Math.PI * 0.5);
      ctx.scale(-1, 1);
      break;
    case TRANS_MIRROR_ROT270: // 4
      ctx.rotate(-Math.PI * 0.5);
      ctx.scale(-1, 1);
      break;
    default:
      break;
  }
  ctx.drawImage(img, sx | 0, sy | 0, w | 0, h | 0, -w * 0.5, -h * 0.5, w | 0, h | 0);
  ctx.restore();
}

export function drawImageAnchor(ctx, img, dx, dy, anchor = 20) {
  if (!img) return;
  const w = img.width || img.naturalWidth || 0;
  const h = img.height || img.naturalHeight || 0;
  const [ax, ay] = applyAnchor(dx, dy, w, h, anchor);
  ctx.drawImage(img, ax, ay);
}
