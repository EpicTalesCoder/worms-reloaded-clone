#!/usr/bin/env python3
"""
Build standalone web-port into dist/ for Vercel, Netlify, GitHub Pages, or local serving.
Stamps HTML/JS/CSS references with a build version so Discord Activity & browsers
never serve stale cached modules.
"""
import pathlib
import re
import shutil
import time

ROOT = pathlib.Path(__file__).resolve().parent.parent
SRC_WEB = ROOT / "web-port"
DIST = ROOT / "dist"
BUILD_VER = str(int(time.time()))


def bust_web_port_cache(dst_web: pathlib.Path, ver: str) -> None:
    idx_path = dst_web / "index.html"
    if idx_path.exists():
        html = idx_path.read_text(encoding="utf-8")
        html = re.sub(r'src="game\.js(?:\?v=[^"]*)?"', f'src="game.js?v={ver}"', html)
        html = re.sub(r'href="style\.css(?:\?v=[^"]*)?"', f'href="style.css?v={ver}"', html)
        idx_path.write_text(html, encoding="utf-8")

    import_re = re.compile(r"""(from\s+['"]\./[A-Za-z0-9._-]+\.js)(?:\?v=[^'"]*)?(['"])""")
    for js_file in dst_web.glob("*.js"):
        src = js_file.read_text(encoding="utf-8")
        stamped = import_re.sub(rf"\1?v={ver}\2", src)
        if stamped != src:
            js_file.write_text(stamped, encoding="utf-8")


def main() -> None:
    if DIST.exists():
        shutil.rmtree(DIST)
    if not SRC_WEB.exists():
        raise FileNotFoundError(f"Missing web-port directory: {SRC_WEB}")

    shutil.copytree(
        SRC_WEB,
        DIST,
        ignore=shutil.ignore_patterns("tests", "*.test.mjs", ".DS_Store"),
    )

    for page in ("terms", "privacy"):
        src_page = DIST / f"{page}.html"
        if src_page.exists():
            (DIST / page).mkdir(parents=True, exist_ok=True)
            shutil.copy2(src_page, DIST / page / "index.html")

    bust_web_port_cache(DIST, BUILD_VER)
    print(f"[DONE] Packaged web-port -> {DIST} (v={BUILD_VER})")


if __name__ == "__main__":
    main()
