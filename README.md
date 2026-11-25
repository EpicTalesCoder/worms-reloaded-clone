# Worms Reloaded (`Worms Reloaded`) — Standalone Clone

- **Vendor**: EA Mobile / Team17
- **Resolution**: `320×240`
- **Genre**: Pháo binh Chiến thuật · J2ME Port

- 1:1 J2ME 320×240 Engine: giải mã trực tiếp /index + df0_0..df10_0, RLE 50/51, 31 pack sprite ac, PLTE swap & bitmap font VI/EN/TH gốc
- Địa hình phá hủy 832×448 thời gian thực (B-spline + mặt nạ bit + hố bom Bresenham + minimap 170×64 + 5 chủ đề & pháo đài)
- Đầy đủ 36 vũ khí & tiện ích (Bazooka, Holy Hand Grenade, Banana Bomb, Super Sheep, Air Strike, Concrete Donkey, Jetpack...), Chiến dịch 28 màn, Shop & Body Count
- Đấu trường Online 2–4 đội (?mp=WRxxxx, Host-authoritative Blob Worker + đồng bộ hố bom / vật phẩm / lượt bắn thời gian thực)

## Project Layout

- `original/` — Original untouched J2ME `.jar`
- `analysis/` — Decompiled Java sources, `javap` bytecode dumps, and decoded binary tables/assets
- `web-port/` — HTML5 Canvas/WebGL + ES Modules web port & Node unit tests
- `api/mp.mjs` — Same-origin multiplayer relay (`/api/mp`) for Vercel & Discord Activity
- `tools/build-dist.py` — Packages `web-port/` to `dist/` with cache-busting `?v=<timestamp>`
- `tools/verify-port-parity.py` — Runs syntax, unit tests, and decompilation parity checks


## Run, Test & Deploy

```bash
# Run unit tests
npm test

# Run parity checks
python3 tools/verify-port-parity.py

# Build dist/ and preview locally
npm run build
npm start
```
