/**
 * Worms Reloaded Web Audio & Real JAR MIDI Music Engine
 * Ports q.java:1-120 & ae.java MMAPI audio:
 * - Plays authentic Standard MIDI tracks (index 80..84) extracted from /df8_0
 * - Synthesizes crisp retro explosion, launch, grenade bounce, hallelujah, sheep, shotgun & UI SFX
 */

class SimpleMIDIParser {
  static parse(bytes) {
    if (!bytes || bytes.length < 14) return null;
    if (String.fromCharCode(...bytes.subarray(0, 4)) !== 'MThd') return null;
    const readU16 = (o) => (bytes[o] << 8) | bytes[o + 1];
    const readU32 = (o) => ((bytes[o] << 24) | (bytes[o + 1] << 16) | (bytes[o + 2] << 8) | bytes[o + 3]) >>> 0;
    const nTracks = readU16(10);
    const division = readU16(12) || 480;

    let pos = 14;
    const events = [];
    for (let t = 0; t < nTracks && pos + 8 <= bytes.length; t++) {
      if (String.fromCharCode(...bytes.subarray(pos, pos + 4)) !== 'MTrk') break;
      const trkLen = readU32(pos + 4);
      pos += 8;
      const end = Math.min(bytes.length, pos + trkLen);
      let absTick = 0;
      let runStatus = 0;

      while (pos < end) {
        let delta = 0;
        let b = 0;
        do {
          b = bytes[pos++];
          delta = (delta << 7) | (b & 0x7f);
        } while ((b & 0x80) !== 0 && pos < end);
        absTick += delta;

        if (pos >= end) break;
        let status = bytes[pos];
        if (status < 0x80) {
          status = runStatus;
        } else {
          pos++;
          if (status < 0xf0) runStatus = status;
        }

        const cmd = status & 0xf0;
        if (cmd === 0x90 && pos + 1 < end) {
          const note = bytes[pos++];
          const vel = bytes[pos++];
          if (vel > 0) events.push({ tick: absTick, type: 'on', ch: status & 0x0f, note, vel });
          else events.push({ tick: absTick, type: 'off', ch: status & 0x0f, note });
        } else if (cmd === 0x80 && pos + 1 < end) {
          const note = bytes[pos++];
          pos++;
          events.push({ tick: absTick, type: 'off', ch: status & 0x0f, note });
        } else if ((cmd === 0xa0 || cmd === 0xb0 || cmd === 0xe0) && pos + 1 < end) {
          pos += 2;
        } else if ((cmd === 0xc0 || cmd === 0xd0) && pos < end) {
          pos += 1;
        } else if (status === 0xff && pos + 1 < end) {
          const metaType = bytes[pos++];
          let mLen = 0;
          do {
            b = bytes[pos++];
            mLen = (mLen << 7) | (b & 0x7f);
          } while ((b & 0x80) !== 0 && pos < end);
          if (metaType === 0x51 && mLen === 3 && pos + 3 <= end) {
            const usPerBeat = (bytes[pos] << 16) | (bytes[pos + 1] << 8) | bytes[pos + 2];
            events.push({ tick: absTick, type: 'tempo', usPerBeat });
          }
          pos += mLen;
        } else if ((status === 0xf0 || status === 0xf7) && pos < end) {
          let sLen = 0;
          do {
            b = bytes[pos++];
            sLen = (sLen << 7) | (b & 0x7f);
          } while ((b & 0x80) !== 0 && pos < end);
          pos += sLen;
        }
      }
      pos = end;
    }

    events.sort((a, b) => a.tick - b.tick);
    return { division, events };
  }
}

export class WormsAudio {
  constructor(midiTracks = []) {
    this.midiTracks = midiTracks;
    this.ctx = null;
    this.muted = false;
    this.activeTrack = -1;
    this.midiTimer = null;
  }

  ensureCtx() {
    if (this.muted) return null;
    if (!this.ctx && typeof window !== 'undefined') {
      const AC = window.AudioContext || window.webkitAudioContext;
      if (AC) this.ctx = new AC();
    }
    if (this.ctx?.state === 'suspended') {
      this.ctx.resume().catch(() => {});
    }
    return this.ctx;
  }

  setMuted(mute) {
    this.muted = Boolean(mute);
    if (this.muted) this.stopTrack();
  }

  stopTrack() {
    if (this.midiTimer) {
      clearInterval(this.midiTimer);
      this.midiTimer = null;
    }
    this.activeTrack = -1;
  }

  playTrack(idx) {
    if (this.muted || this.activeTrack === idx) return;
    this.stopTrack();
    const raw = this.midiTracks[idx];
    const parsed = SimpleMIDIParser.parse(raw);
    if (!parsed || !parsed.events.length) return;
    this.activeTrack = idx;

    let usPerBeat = 500000;
    const msEvents = [];
    let lastTick = 0;
    let curMs = 0;
    for (const ev of parsed.events) {
      const dtTicks = ev.tick - lastTick;
      curMs += (dtTicks * (usPerBeat / 1000)) / parsed.division;
      lastTick = ev.tick;
      if (ev.type === 'tempo') usPerBeat = ev.usPerBeat;
      else if (ev.type === 'on') msEvents.push({ ms: curMs, ...ev });
    }
    if (!msEvents.length) return;

    const totalMs = Math.max(1200, curMs + 400);
    let cursor = 0;
    let startAt = performance.now();

    this.midiTimer = setInterval(() => {
      if (this.muted) return;
      const ctx = this.ctx;
      if (!ctx || ctx.state !== 'running') return;
      const elapsed = performance.now() - startAt;
      while (cursor < msEvents.length && msEvents[cursor].ms <= elapsed) {
        const ev = msEvents[cursor++];
        if (ev.ch !== 9) { // Melodic note
          const freq = 440 * Math.pow(2, (ev.note - 69) / 12);
          this.tone(freq, 0.14, ev.ch === 0 ? 'triangle' : 'square', Math.min(0.055, (ev.vel / 127) * 0.06));
        }
      }
      if (elapsed >= totalMs) {
        cursor = 0;
        startAt = performance.now();
      }
    }, 30);
  }

  tone(freq, dur = 0.08, type = 'square', vol = 0.08, sweepTo = 0) {
    const ctx = this.ensureCtx();
    if (!ctx) return;
    const now = ctx.currentTime;
    const osc = ctx.createOscillator();
    const gain = ctx.createGain();
    osc.type = type;
    osc.frequency.setValueAtTime(Math.max(40, freq), now);
    if (sweepTo > 0) {
      osc.frequency.exponentialRampToValueAtTime(Math.max(40, sweepTo), now + dur);
    }
    gain.gain.setValueAtTime(vol, now);
    gain.gain.exponentialRampToValueAtTime(0.001, now + dur);
    osc.connect(gain);
    gain.connect(ctx.destination);
    osc.start(now);
    osc.stop(now + dur + 0.01);
  }

  noise(dur = 0.25, vol = 0.14) {
    const ctx = this.ensureCtx();
    if (!ctx) return;
    const sampleRate = ctx.sampleRate;
    const len = Math.max(1, Math.floor(sampleRate * dur));
    const buf = ctx.createBuffer(1, len, sampleRate);
    const data = buf.getChannelData(0);
    for (let i = 0; i < len; i++) {
      data[i] = (Math.random() * 2 - 1) * (1 - i / len);
    }
    const src = ctx.createBufferSource();
    src.buffer = buf;
    const filter = ctx.createBiquadFilter();
    filter.type = 'lowpass';
    filter.frequency.value = 950;
    const gain = ctx.createGain();
    gain.gain.value = vol;
    src.connect(filter);
    filter.connect(gain);
    gain.connect(ctx.destination);
    src.start();
  }

  playSfx(kind) {
    if (this.muted) return;
    switch (kind) {
      case 'uiMove': this.tone(620, 0.03, 'square', 0.04); break;
      case 'uiSelect': this.tone(880, 0.06, 'triangle', 0.07); break;
      case 'uiBack': this.tone(420, 0.06, 'triangle', 0.06, 260); break;
      case 'error': this.tone(180, 0.12, 'sawtooth', 0.07, 130); break;
      case 'jump': this.tone(240, 0.11, 'sine', 0.09, 520); break;
      case 'launch': this.tone(360, 0.16, 'sawtooth', 0.09, 140); break;
      case 'throw': this.tone(480, 0.08, 'triangle', 0.08, 660); break;
      case 'bounce': this.tone(320, 0.04, 'triangle', 0.07); break;
      case 'boom': this.noise(0.32, 0.18); this.tone(110, 0.24, 'sawtooth', 0.12, 42); break;
      case 'bigBoom': this.noise(0.55, 0.24); this.tone(85, 0.4, 'sawtooth', 0.16, 35); break;
      case 'shotgun': case 'uzi': this.noise(0.11, 0.14); break;
      case 'punch': this.noise(0.14, 0.12); this.tone(220, 0.1, 'square', 0.09, 90); break;
      case 'hurt': this.tone(540, 0.14, 'square', 0.08, 220); break;
      case 'wormDie': this.tone(440, 0.32, 'sawtooth', 0.1, 140); break;
      case 'splash': this.noise(0.28, 0.12); this.tone(300, 0.14, 'sine', 0.06, 160); break;
      case 'crate': case 'coin': this.tone(780, 0.06, 'triangle', 0.08); setTimeout(() => this.tone(1040, 0.09, 'triangle', 0.08), 55); break;
      case 'holy': this.tone(523, 0.18, 'triangle', 0.1); setTimeout(() => this.tone(659, 0.18, 'triangle', 0.1), 110); setTimeout(() => this.tone(784, 0.28, 'triangle', 0.1), 220); break;
      case 'sheep': this.tone(460, 0.09, 'sawtooth', 0.07); setTimeout(() => this.tone(490, 0.11, 'sawtooth', 0.07), 95); break;
      case 'turnStart': this.tone(520, 0.08, 'triangle', 0.08); setTimeout(() => this.tone(690, 0.12, 'triangle', 0.08), 80); break;
      case 'victory': this.tone(523, 0.12, 'triangle', 0.1); setTimeout(() => this.tone(659, 0.12, 'triangle', 0.1), 110); setTimeout(() => this.tone(784, 0.25, 'triangle', 0.12), 220); break;
      case 'defeat': this.tone(380, 0.16, 'sawtooth', 0.09); setTimeout(() => this.tone(310, 0.28, 'sawtooth', 0.09), 150); break;
      default: break;
    }
  }
}
