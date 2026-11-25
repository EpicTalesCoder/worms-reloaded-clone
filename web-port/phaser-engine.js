/**
 * Phaser 3 (WebGL / Canvas 60 FPS) Hardware-Accelerated Runtime Engine
 * for Prehistoric Tribes (Bộ lạc thời tiền sử) and future J2ME (.jar) web ports.
 *
 * Features:
 * - Drives an authentic fixed-timestep J2ME simulation clock (120ms/tick) inside Phaser.Scene.update(time, delta)
 * - 60 FPS sub-tick unit position interpolation & silky-smooth camera lerp (smoothCamX / smoothCamY)
 * - Uploads the 240×320 logical offscreen framebuffer into a pixel-art Phaser CanvasTexture rendered via WebGL
 * - Automatic graceful fallback if Phaser CDN is offline
 */

export function mountPhaserRuntime({
  canvas,
  width = 240,
  height = 320,
  tickMs = 120,
  onStepSimulation,
  onFrameInterpolate,
  onRenderFrame,
}) {
  let curWidth = width;
  let curHeight = height;
  const offscreen = document.createElement('canvas');
  offscreen.width = curWidth;
  offscreen.height = curHeight;
  const offCtx = offscreen.getContext('2d');
  offCtx.imageSmoothingEnabled = false;

  // Background Web Worker ticker: keeps simulation & 1v1 multiplayer snapshots running at full 120ms speed
  // even when the player switches to another browser tab or minimizes the window (where rAF pauses completely)!
  let lastSimAt = performance.now();
  const runBackgroundCatchup = () => {
    const now = performance.now();
    const elapsed = now - lastSimAt;
    if (!document.hidden && elapsed < tickMs * 1.75) return;
    const steps = Math.min(5, Math.floor(elapsed / tickMs));
    if (steps <= 0) return;
    lastSimAt += steps * tickMs;
    if (now - lastSimAt > tickMs * 4) lastSimAt = now;
    for (let i = 0; i < steps; i++) {
      onStepSimulation(now);
    }
  };
  let bgWorker = null;
  try {
    const blob = new Blob([`setInterval(() => postMessage(1), ${Math.max(40, Math.round(tickMs))});`], {
      type: 'application/javascript',
    });
    bgWorker = new Worker(URL.createObjectURL(blob));
    bgWorker.onmessage = runBackgroundCatchup;
  } catch {
    setInterval(runBackgroundCatchup, Math.max(40, Math.round(tickMs)));
  }

  const PhaserGlobal = typeof window !== 'undefined' ? window.Phaser : null;

  if (!PhaserGlobal) {
    // Fallback to native 2D canvas + requestAnimationFrame if offline
    canvas.width = curWidth;
    canvas.height = curHeight;
    const directCtx = canvas.getContext('2d');
    directCtx.imageSmoothingEnabled = false;
    let lastTime = performance.now();
    let acc = 0;
    function fallbackLoop(now) {
      const dt = Math.min(250, Math.max(0, now - lastTime));
      lastTime = now;
      acc += dt;
      while (acc >= tickMs) {
        acc -= tickMs;
        lastSimAt = performance.now();
        onStepSimulation(now);
      }
      const subTick = Math.max(0, Math.min(0.99, acc / tickMs));
      const needsDraw = onFrameInterpolate(subTick, dt);
      if (needsDraw) {
        onRenderFrame(directCtx);
      }
      window.requestAnimationFrame(fallbackLoop);
    }
    window.requestAnimationFrame(fallbackLoop);
    return {
      mode: 'Canvas2D',
      ctx: directCtx,
      resize(newW, newH) {
        curWidth = Math.max(1, Math.round(newW));
        curHeight = Math.max(1, Math.round(newH));
        canvas.width = curWidth;
        canvas.height = curHeight;
        directCtx.imageSmoothingEnabled = false;
        onRenderFrame(directCtx);
      },
      requestRedraw() {
        if (!document.hidden) onRenderFrame(directCtx);
      },
      markDirty() {
        // Canvas2D fallback draws on next rAF frame
      },
    };
  }

  let phaserTexture = null;
  let displayImg = null;
  let dirty = true;
  let acc = 0;

  class J2MEPhaserScene extends PhaserGlobal.Scene {
    constructor() {
      super({ key: 'J2MEPhaserScene' });
    }

    create() {
      this.rebuildTexture(curWidth, curHeight);
      this.flushToGpu();
    }

    rebuildTexture(w, h) {
      if (this.textures.exists('j2me_framebuffer')) {
        this.textures.remove('j2me_framebuffer');
      }
      phaserTexture = this.textures.createCanvas('j2me_framebuffer', w, h);
      if (phaserTexture.setFilter && PhaserGlobal.Textures?.FilterMode) {
        phaserTexture.setFilter(PhaserGlobal.Textures.FilterMode.NEAREST);
      }
      if (displayImg) {
        displayImg.setTexture('j2me_framebuffer');
        displayImg.setPosition(0, 0);
        displayImg.setDisplaySize(w, h);
      } else {
        displayImg = this.add.image(0, 0, 'j2me_framebuffer').setOrigin(0, 0);
        displayImg.setDisplaySize(w, h);
      }
      if (this.cameras?.main) {
        this.cameras.main.setViewport(0, 0, w, h);
      }
    }

    flushToGpu() {
      if (!phaserTexture || document.hidden) {
        dirty = true;
        return;
      }
      onRenderFrame(offCtx);
      const texCtx = phaserTexture.getContext();
      texCtx.imageSmoothingEnabled = false;
      texCtx.clearRect(0, 0, curWidth, curHeight);
      texCtx.drawImage(offscreen, 0, 0);
      phaserTexture.refresh();
      dirty = false;
    }

    update(time, delta) {
      const dt = Math.min(250, Math.max(0, delta));
      acc += dt;
      let stepped = false;
      while (acc >= tickMs) {
        acc -= tickMs;
        lastSimAt = performance.now();
        onStepSimulation(time);
        stepped = true;
      }
      const subTick = Math.max(0, Math.min(0.99, acc / tickMs));
      const changed = onFrameInterpolate(subTick, dt);
      if (stepped || changed || dirty) {
        this.flushToGpu();
      }
    }
  }

  const game = new PhaserGlobal.Game({
    type: PhaserGlobal.WEBGL,
    canvas,
    width: curWidth,
    height: curHeight,
    pixelArt: true,
    antialias: false,
    roundPixels: true,
    transparent: false,
    backgroundColor: '#000000',
    audio: { noAudio: true },
    pauseOnBlur: false,
    banner: false,
    scene: [J2MEPhaserScene],
  });

  document.addEventListener('visibilitychange', () => {
    if (!document.hidden) {
      lastSimAt = performance.now();
      dirty = true;
      const scene = game.scene?.getScene?.('J2MEPhaserScene');
      if (scene && phaserTexture) scene.flushToGpu();
    }
  });

  return {
    mode: 'Phaser 3 WebGL',
    game,
    ctx: offCtx,
    resize(newW, newH) {
      const nextW = Math.max(1, Math.round(newW));
      const nextH = Math.max(1, Math.round(newH));
      if (nextW === curWidth && nextH === curHeight) return;
      curWidth = nextW;
      curHeight = nextH;
      offscreen.width = curWidth;
      offscreen.height = curHeight;
      offCtx.imageSmoothingEnabled = false;
      game.scale.resize(curWidth, curHeight);
      canvas.style.width = '100%';
      canvas.style.height = '100%';
      const scene = game.scene?.getScene?.('J2MEPhaserScene');
      if (scene && scene.textures) {
        scene.rebuildTexture(curWidth, curHeight);
        dirty = true;
        scene.flushToGpu();
      }
    },
    requestRedraw() {
      dirty = true;
      const scene = game.scene?.getScene?.('J2MEPhaserScene');
      if (scene && phaserTexture) {
        scene.flushToGpu();
      }
    },
    markDirty() {
      dirty = true;
    },
  };
}
