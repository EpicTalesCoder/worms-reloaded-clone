# Worms Reloaded — Stage 1 Decompilation Analysis & Class Responsibilities

- **Source JAR**: `games/worms-reloaded/original/WormsReloaded_s40v6-C3-00_320x240.jar` (`458,696` bytes)
- **SHA-256**: `2b8f0c61d2bd3cf804550d6362666033556145f72ca73825fa487c342b3c3ed4`
- **Target Profile**: Java ME CLDC-1.1 / MIDP-2.0, Nokia S40v6 320×240 landscape (`j.K = 320`, `j.L = 240`, `ae.a = 50ms` -> 20 Hz simulation tick).
- **Entry Point**: `com.ea.sdk.SDKMIDlet` (`MIDlet-1`). (`MobileAds` / `WapTai.COM` is `MIDlet-2` cracker/ad launcher and is intentionally excluded from runtime.)
- **Native Locales in JAR**: `en` (`/t_en`), `th` (`/t_th`), `vi` (`/t_vi`), all sharing `/hdr` and `/charmap` and native bitmap fonts (`10`/`11` with 67/67 Vietnamese uppercase glyphs!).

---

## 1. Complete Class Inventory (36 Classes)

| Class | Role & Responsibilities | Key Source Citations |
|---|---|---|
| `com.ea.sdk.SDKMIDlet` | MIDP entry point; instantiates `z` canvas & handles lifecycle (`startApp`, `pauseApp`, `destroyApp`) | `SDKMIDlet.java:1-21` |
| `z` | Main `Canvas` subclass (`j`), fixed 50ms tick loop (`run()`), delegates paint to `y.a(n)` & `o.a(n)`, routes MIDP key events to `s` | `z.java:1-323` |
| `j` | Screen & UI layout constants (`K=320`, `L=240`, menu Y/X coords, weapon grid metrics, HUD offsets, softkey coords) | `j.java:1-121` |
| `s` | Input polling & keypad state (`a`=held, `b`=pressed this tick, `c`=released, `UP..RSK`, `0..9`, `*`, `#`, Back) + monotonic clock (`s.a()`) | `s.java:1-227` |
| `y` | **Master Game Controller & UI/Menu Renderer**: state machines (`o`, `cm`, `p`), splashes, menus, campaign map/select, shop, trophies, options, HUD, minimap, weapon grid, worm turn flow, damage/wind/triggers, RMS persistence | `y.java:1-8602` |
| `a` | **World, Terrain & Entity Manager**: 832×448 world (`640×320` destructible `x` bitmask, `b=416` water level), procedural terrain generator (`b()`, spline `B()`, XOR scanline fill `d(col)`), 32×32 cached tile map `w`, Bresenham crater carving `a(x,y,r)`, camera & shake, entity pool `a[0..149]`, campaign triggers | `a.java:1-3391` |
| `ah` | **Physics, Entities, Weapons & AI**: 33 entity kinds (worms, 36 weapon projectiles, explosions, crates, mines, oil drums, sentry guns, gravestones, parachutes, ropes, flames, coins, HUD popups), `16.16` fixed-point movement & collision (`a(int[])`), crater/damage impulse, AI target evaluation & ballistic solver | `ah.java:1-8546` |
| `h` | AI shot/utility candidate evaluation record (`a`=weaponId, `n`=dir, `m`=angle, `k`=power, `o,p`=vx,vy, `i,j`=impact, `a,b`=score/selfDamage, comparator `a(h)`) | `ah.java` helper (`h.java:1-95`) |
| `ac` | **Sprite/Animation Pack Decoder & Renderer**: parses `18+(id<<1)` metadata (modules, frames, transform flags `x`, animations) & `18+(id<<1)+1` image/RLE (`50`/`51`, RGB565 `5` / ARGB `129` palettes) | `ac.java:1-844` |
| `b` | **`/index` + `/df*_0` Pack Reader**: parses LE `/index`, streams chunks from `/df0_0..df10_0`, performs PNG `PLTE` palette swap (`b.a(11, S)`) | `b.java:1-314` |
| `p` | **EA SDK Bitmap Font & Localization Engine**: loads `/hdr`, `/charmap`, `/t_*`; decodes bitmap font tables (`10`+`11`), wraps lines on `|` / spaces, draws aligned glyphs, wraps RMS read/write | `p.java:1-907` |
| `c` | Custom EA localized mutable UTF-16/Latin string buffer (`a` byte[] or char[], offsets, `a(int)`, `b(c)` format arg `\0` substitution) | `c.java:1-296` |
| `m` | Localization & audio singleton container holding language tables, `/charmap`, font state, active audio controller `q` | `m.java:1-165` |
| `n` | Graphics wrapper around MIDP `javax.microedition.lcdui.Graphics` (`a(e,x,y,sx,sy,w,h,transform)`, `a(int,int,int,int)` rects/lines, clips) | `n.java:1-61` |
| `e` | Image wrapper holding decoded raster/Image handle & dimensions (`a()`, `b()`) | `e.java:1-47` |
| `ae` | EA SDK runtime bridge (font binary lookup/bsearch `a(...)`, byte short reader `ae.a(b,off)`, RecordStore bridge, MMAPI `Manager.createPlayer` audio helpers, endian helpers) | `ae.java:1-2122` |
| `q` | Audio manager (`a(int)` preloads MIDI resources `80..84`, `c(int)` plays track, `b()` stops, `a(boolean)` mute toggle) | `q.java:1-120` |
| `t` | Cached sound/MIDI resource entry (`byte[]`, resource ID) used by `q` | `t.java:1-58` |
| `d` | Active MMAPI `Player` wrapper managed by `q` / `ae` | `d.java:1-47` |
| `g` | Active bitmap font descriptor cache (`byte[12]` metrics, `short[]` codepoints & offsets, `byte[]` frame table) | `g.java:1-55` |
| `r` | Campaign/Tutorial trigger & camera/hint event node (`a`=type `60..65` / `80..96`, `b..g` coords, string/weapon checks) | `r.java:1-211` |
| `o` | EA More Games overlay base class (activated only when `MG_Impl` JAD property exists; returns `false` on this JAR -> item hidden) | `o.java:1-1134` |
| `k` | Subclass of `o` delegating paint to `y.b(n)` (`k.java:1-9`) | `k.java:1-9` |
| `aa` | Device platform constants / softkey / screen helpers (`aa.java:1-99`) | `aa.java:1-99` |
| `ab`, `ad`, `af`, `ag`, `f`, `i`, `l`, `u`, `v`, `w`, `x` | Small SDK structs/locks/timer helpers (`l.a` boot epoch ms, `f` empty sound listener, `i` bool ref, `x`/`ad`/`v` lock/state holders) | `ab/ad/af/ag/f/i/l/u/v/w/x.java` |
| `MobileAds` | External WAP ad MIDlet bundled in JAR (`MIDlet-2`); not part of Worms Reloaded gameplay | `MobileAds.java:1-21` |

---

## 2. Game State Machines & Core Flow

### Top-Level States (`y.o`, updated every 50ms in `y.a(long)`)
- `-1`: Title / Init handshake
- `0`: Boot resource loader (`M[0..90]` trig table `b(3)`, fonts `10`+`11`+`PLTE` swaps `-1, 7, 8`, `/hdr`, RMS `J()`, `/t_*` groups 1..3, images `0, 1, 2`) -> jumps to `4` (Main Menu) if language saved (`cO >= 0`), else `1` (Splash sequence -> Language Select).
- `1, 2, 17`: Splash screens (`b(0)` EA logo, `b(1)` Team17 logo, `b(2)` Worms Reloaded 320×240 title image) -> auto-advance after `3000ms` (`y.java:1215-1225`).
- `3`: Pre-match async asset loader (`cJ = 0..cK`): preloads MIDI `80..84` (`cJ == 1`), parses `b(85)` 28 campaign levels (`cJ == 3`), loads theme `ac` sprite packs -> transitions to `4` / `6`.
- `4`: Main Menu & Sub-menus driven by `y.cm`:
  - `cm=0`: Main Menu (`g[]`: Campaign, Quick Game, Custom Game, Training, Achievements, Options, Help, About, Shop, Exit)
  - `cm=1`: Play sub-menu (`h[]`), `cm=2`: Custom Match setup, `cm=3`: Training (`k[]`), `cm=4`: Custom/Team Config, `cm=5..10`: Help/Info sub-pages (`v..K`)
  - `cm=11`: Options (`i[]`: Sound, Vibration, Timer Pause, Team Name/Color/Grave, Language, Controls)
  - `cm=12`: Confirm Dialog, `cm=13`: Language Select, `cm=14`: About, `cm=15`: Campaign Select, `cm=16`: Campaign Level Grid (`l[]`: 4 worlds × 7 missions = 28 stages + Shop), `cm=17..20`: Match Setup / Weapons Scheme / Scheme Options, `cm=21`: Team Edit, `cm=22..25`: Help Topics, `cm=26`: Achievements (`bw`, `23` trophies?), `cm=27..28`: Shop Results / Info, `cm=29..33`: Shop Category Tabs (Weapon unlocks/packs using Coins `y.ct`!).
- `5`: Loading / Pre-game world generation (`a.b()` -> terrain + objects + worms -> `o = 6`).
- `6`: **Active Match Loop (`y.p`)**:
  - `p=0..6`: Turn Intro Banner ("<Team>'s Turn", wind/crate spawn, sudden death water rise check)
  - `p=2`: Active Player / AI Control (`t` countdown tick, aim, walk, jump, open weapon panel `p=19` / LSK, fire, retreat grace timer)
  - `p=3, 4`: Projectile/Explosion/Gravity resolution wait (`a.f()`)
  - `p=5`: Turn End (damage tally, poison tick, water drown, victory/defeat check -> next worm/team or `o = 7` Pause / `o = 8` Match Over)
  - `p=7`: Sudden Death / Water Rise animation
  - `p=19`: In-Game Weapon & Utility Grid Panel (LSK toggle)
- `7`: In-Game Pause Menu (`Resume`, `Restart`, `Options`, `Help`, `Main Menu`, `Exit`)
- `8, 18, 19`: Post-Match Scoreboard / Campaign Victory / Campaign Defeat / Coins & Trophy Awards -> RMS save `y.H()` -> next mission / Main Menu.

### Fixed-Point & Math Conventions
- Scale factor: **16.16 fixed point** (`65536 === 1.0`, shifts `<< 16` / `>> 16`).
- Trig table `y.M[0..90]` loaded from chunk `3` (`364` bytes = `91` LE `int32` cosine values `0°..90°` scaled by `65536`; `y.M[0] = 65536`, `y.M[1] = 65526`, etc.).
- Gravity per tick (`50ms`): `32768 * weaponGravity >> 16` (`~0.5 px/tick²` scaled by mass `bV`).

---

## 3. Resource Pack Binary Format (`/index` + `/df0_0..df10_0`)

- **`/index`** (`603` bytes, little-endian via `b.a(is, nBytes)`):
  - Byte `0`: reserved `0x00`
  - `numFiles = u16(11)` (`df0_0 .. df10_0`), `intSz = u8(3)` (24-bit LE ints), `numLangs = u8(4)`
  - Per file `f`: `maxId (u16)`, `(numLangs+1) * intSz` file lengths, `intSz` extra
  - `totalEntries = u16(94)` (`id = 0..93`): `mask (u8)`, `offset (24-bit LE)`, plus optional localized offsets.
- **Complete Entry Map (`0..93`)**:
  - `0, 1, 2`: Splashes PNG (`111×117` EA, `171×59` Team17, `320×240` Title)
  - `3`: Cosine LUT (`91` × `int32` LE = `364` B)
  - `4`: `/hdr` copy (`270` B)
  - `6, 11, 13, 15`: PNG font sheets (`11` = `172×107` primary UI/HUD font `d/a/b/c..j` with **322 glyphs** covering ASCII + **67/67 Vietnamese uppercase**; `5/6`, `12/13`, `14/15` = alt/unused EA SDK font sheets)
  - `7, 8, 9`: PNG `PLTE` replacement chunks (`780` B = `4B len(0x300) + 'PLTE' + 768B RGB + 4B CRC`): Cyan (`7`), Red (`8`), Green (`9`). `y.S = {-1, 7, 8}` generates White/Gold (`-1`), Blue/Cyan (`7`), and Red (`8`) font sheets!
  - `10`: Primary font metric binary (`5172` B: EA bitmap font table for image `11`)
  - `17`: `/moregames` copy (`216` B)
  - `18..79`: **31 `ac` sprite/animation packs** (`meta = 18 + (id<<1)`, `pixels = 18 + (id<<1) + 1`):
    - `0` (`18/19`): Worms (262 modules, 300 frames, 80 anims, `150×596` PNG)
    - `1` (`20/21`): Weapons/held items (40 modules/frames, `108×270` PNG)
    - `2` (`22/23`): UI Icons / Medals / Badges? (`d=1`, 103 uniform `11×13`? Check dimensions on decode; RGB565 palette `palFmt=5`, 138 colors, RLE type `51` `18,615` B)
    - `3` (`24/25`): Explosions / smokes / sparks (`127×236` PNG)
    - `4` (`26/27`): Hats / Team skins (`39×320` PNG)
    - `5` (`28/29`): Projectiles / weapon sprites (`96×201` PNG)
    - `6` (`30/31`): Objects / mines / barrels / crates / tombs (`94×80` PNG)
    - `7` (`32/33`): Aiming / crosshairs / cursors (`81×87` PNG)
    - `8` (`34/35`): Power / wind gauge (`116×33` PNG)
    - `9` (`36/37`): Water / waves (`24×200` PNG)
    - `10` (`38/39`): Menu / HUD chrome & panels (`88×37` PNG)
    - `11` (`40/41`): Target / airstrike / pointers (`27×69` PNG)
    - `12` (`42/43`): Loading worm (`43×30` RGBA PNG)
    - `13` (`44/45`): Title / EA UI banner elements (`146×65` PNG)
    - `14` (`46/47`): Softkeys, menu buttons, keypad glyphs, weapon badges (`135×352` PNG)
    - `15` (`48/49`): Weapon pack / HUD strip (`172×84` PNG)
    - `16` (`50/51`): Grave / trophies (`19×278` PNG)
    - `17..20` (`52..59`): 4 Fort miniatures (`40×21` PNGs)
    - `21..25` (`60..69`): 5 Terrain Texture Tiles (`d=1`, 32×32, RGB565 `palFmt=5`, RLE type `50`) — Themes 0..4!
    - `26..30` (`70..79`): 5 Parallax Sky/Background PNGs (`176×220` / `160×192`) — Themes 0..4!
  - `80..84`: **5 Standard MIDI Tracks** (`MThd...`, `619`, `661`, `243`, `243`, `4223` B)
  - `85`: **28 Campaign Levels Table** (`616` B = `28 × 22` bytes; `y.java:1238-1261`)
  - `86..89`: Campaign / Training preset entities & tutorial trigger scripts (`a.g(id)`)
  - `90..93`: 4 Boss terrain spline coordinates (`a.h(id)`)

### `ac` Sprite Decoder Rules (`ac.java:76-203, 229-310, 590-844`)
- Metadata is **little-endian** for `u16` counts (`b`, `e`, `f`, `g`, `h`), followed by raw byte arrays and cumulative delta arrays `c[0..e]` and `g[0..nAnim]`.
- Wait: `d == 0` reads `a[]`, `b[]` (`int16[]`), `a[]` (`uint8` width), `w[]` (`uint8` height); `d == 1` has uniform width/height? Look at `ac[2]`: `01 67 00 ...`: wait `d == 1` skips `a, b` rects and reads `w[b]`, `h[b]`! (Every module still has its own `(w[i], h[i])`, no `(a[i], b[i])` texture coords because `C[]` stores each module packed sequentially with offsets `i[0..b]`!). Wait check `ac[21..25]`: `n_mod = 1`, `w[0] = 32, h[0] = 32`!
- Transform flags (`0..7`): XOR composed (`c(animFlag & 7, flipFlag)`): flips horizontal (`^1` or `2` per MIDP `Sprite.TRANS_*`), vertical, and 90° rotate (`^4`). Matches `Sprite` MIDP transforms `TRANS_NONE=0`, `MIRROR_ROT180=1`, `MIRROR=2`, `ROT180=3`, `MIRROR_ROT270=4`, `ROT90=5`, `ROT270=6`, `MIRROR_ROT90=7` (`n.java:30-55`).

---

## 4. World, Terrain, Physics & Combat Formulas

- **World Dimensions (`a.java`)**: `p = 832`, `q = 448`, `G = 96`, `H = 96`, `I = 32`, water level `b = q - 32 = 416`. Destructible playfield bitmask `x` covers `640 × 320` (`G..G+640`, `H..H+320`) at 1 bit per pixel (`25,601` bytes, `y*80 + (x>>3)`).
- **Terrain Generation (`a.b()`, `a.java:1810-2447`)**:
  1. Generates midpoint-displaced / spline control points (`B()`) or reads boss spline `h(90..93)` / island / bridge / cavern profiles based on level theme (`0..4`) and topology (`a.a`: Cavern, Island, Hills, Twin Peaks, Forts, Bosses).
  2. Vertical scanline XOR fill (`a.d(col)`) converts boundary intersections into solid 1-bit `x` mask + edge autotile dirt/grass/stone shading cached into `32×32` tile array `w` (`8`=air, `16`=textured surface+crust, `32`=interior pattern tile).
  3. **Destruction (`a.a(cx, cy, r)`)**: Bresenham circle clears bits in `x` mask inside `[cx-r, cx+r]`, marks touched `32×32` cache tiles dirty, carves matching scorch outline onto terrain canvas & `170×64` minimap (`y.v`), and wakes resting worms/crates/mines/barrels.
- **36 Weapons & Utilities (`y.b[0..35]`, `ah.java:200-500`, `8091-8345`)**:
  - Bazooka, Homing Missile, Mortar, Homing Pigeon, Sheep Launcher, Grenade, Cluster Bomb, Banana Bomb, Battle Axe, Earthquake, Shotgun, Handgun, Uzi, Minigun, Fire Punch, Dragon Ball, Kamikaze, Prod, Dynamite, Mine, Sheep, Super Sheep, Air Strike, Napalm Strike, Blow Torch, Pneumatic Drill, Girder, Baseball Bat, Ninja Rope, Parachute, Bungee, Freeze, Skip Go, Surrender, Petrol Bomb, Holy Hand Grenade, Concrete Donkey, Buffalo of Lies / Old Woman / Mad Cow / Worship / Marked for Death (verify exact ID `0..35` ordering directly from `y.java` strings + `y.b[][]` stats!).
- **Persistence (`y.H()` / `y.J()`)**: Stores language (`cO`), sound/vibro/timer options, campaign unlock bitmask/progress (`I`), Coins (`ct`), Shop weapon stock (`k[36]`, `l[36]`), Achievement bitmask (`dw`), team customization (names, colors, hats, gravestones, voices) -> mirrored 1:1 into `localStorage`.
