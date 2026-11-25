#!/usr/bin/env python3
"""
Standalone Anti-Hallucination Parity Gate for this J2ME Web Port project.
Run: python3 tools/verify-port-parity.py
"""
import pathlib
import re
import subprocess
import sys

ROOT = pathlib.Path(__file__).resolve().parent.parent


def main() -> None:
    orig_dir = ROOT / "original"
    analysis_dir = ROOT / "analysis"
    web_dir = ROOT / "web-port"

    failures: list[str] = []
    warnings: list[str] = []
    passed: list[str] = []

    jars = sorted(orig_dir.glob("*.jar")) if orig_dir.exists() else []
    if not jars or jars[0].stat().st_size == 0:
        failures.append("Missing original .jar in original/")
    else:
        passed.append(f"Original JAR intact: {jars[0].name} ({jars[0].stat().st_size:,} B)")

    rec_dir = analysis_dir / "recovered"
    rec_classes = list(rec_dir.rglob("*.class")) if rec_dir.exists() else []
    if not rec_classes:
        failures.append("No extracted .class files in analysis/recovered/")
    else:
        passed.append(f"Recovered {len(rec_classes)} .class files")

    jp_dir = analysis_dir / "bytecode-evidence"
    jp_files = list(jp_dir.rglob("*.javap.txt")) if jp_dir.exists() else []
    valid_jp = [f for f in jp_files if f.stat().st_size > 100]
    if not valid_jp:
        failures.append("No valid (>100B) javap dumps in analysis/bytecode-evidence/")
    elif len(valid_jp) < len(rec_classes):
        warnings.append(f"Only {len(valid_jp)}/{len(rec_classes)} classes have valid javap dumps")
    else:
        passed.append(f"Valid javap bytecode dumps: {len(valid_jp)} files")

    vf_files = list((analysis_dir / "decompiled-vineflower").rglob("*.java")) if (analysis_dir / "decompiled-vineflower").exists() else []
    cfr_files = list((analysis_dir / "decompiled-cfr").rglob("*.java")) if (analysis_dir / "decompiled-cfr").exists() else []
    if not vf_files and not cfr_files:
        failures.append("No decompiled .java files in decompiled-vineflower/ or decompiled-cfr/")
    else:
        passed.append(f"Decompiled Java sources: Vineflower={len(vf_files)}, CFR={len(cfr_files)}")

    if (analysis_dir / "CLASS-RESPONSIBILITIES.md").exists():
        passed.append("CLASS-RESPONSIBILITIES.md present")
    else:
        failures.append("Missing analysis/CLASS-RESPONSIBILITIES.md")

    js_files = [
        f for f in sorted(web_dir.glob("*.js"))
        if f.name not in ("phaser-engine.js", "multiplayer.js")
    ] if web_dir.exists() else []

    if not js_files:
        failures.append("No JS source files found in web-port/")
    else:
        cite_re = re.compile(r"\b[A-Za-z0-9_$]+\.java:\d+")
        unverified_re = re.compile(r"\bUNVERIFIED\b")
        draw_img_re = re.compile(r"\bdrawImage\s*\(")
        fill_rect_re = re.compile(r"\bfillRect\s*\(")

        total_cites = 0
        total_unverified = 0
        total_draw_img = 0
        total_fill_rect = 0
        for jf in js_files:
            txt = jf.read_text(encoding="utf-8", errors="replace")
            total_cites += len(cite_re.findall(txt))
            total_unverified += len(unverified_re.findall(txt))
            total_draw_img += len(draw_img_re.findall(txt))
            total_fill_rect += len(fill_rect_re.findall(txt))

        if total_cites < 3:
            failures.append(f"Only {total_cites} decompiled Java citations (Class.java:line) found in web-port/*.js (minimum 3)")
        else:
            passed.append(f"Decompiled Java citations found: {total_cites} references")

        if total_unverified > 0:
            failures.append(f"Found {total_unverified} unresolved UNVERIFIED markers in web-port/*.js")

        assets_dir = web_dir / "assets"
        asset_count = len([f for f in assets_dir.rglob("*") if f.is_file()]) if assets_dir.exists() else 0
        if asset_count == 0:
            failures.append("web-port/assets/ is empty")
        else:
            passed.append(f"Extracted web-port assets: {asset_count} files")

        if total_draw_img == 0:
            failures.append(f"web-port/*.js has 0 drawImage() calls (fillRect={total_fill_rect})")
        else:
            passed.append(f"Real sprite rendering detected: {total_draw_img} drawImage() calls (fillRect={total_fill_rect})")

        stroke_rect_re = re.compile(r"\bstrokeRect\s*\(")
        total_stroke_rect = sum(len(stroke_rect_re.findall(jf.read_text(encoding="utf-8", errors="replace"))) for jf in js_files)
        if total_stroke_rect > 0:
            warnings.append(f"Found {total_stroke_rect} Canvas strokeRect() call(s) — prefer drawRectMIDP()")
        else:
            passed.append("Crisp integer borders verified (0 strokeRect() calls)")

        if assets_dir.exists() and (assets_dir / "fnt.png").exists():
            font_refs = sum(1 for jf in js_files if "createOriginalFont" in jf.read_text(encoding="utf-8", errors="replace"))
            if font_refs == 0:
                warnings.append("assets/fnt.png present but createOriginalFont() not wired in web-port/*.js")
            else:
                passed.append("Original bitmap font sheet (fnt.png) wired via createOriginalFont()")

        css_path = web_dir / "style.css"
        if css_path.exists():
            css_txt = css_path.read_text(encoding="utf-8", errors="replace")
            tb_match = re.search(r"\.floating-toolbar\s*\{([^}]*)\}", css_txt, re.S)
            if tb_match and re.search(r"position\s*:\s*fixed\b", tb_match.group(1)):
                warnings.append("style.css .floating-toolbar uses position:fixed")
            elif tb_match:
                passed.append("Viewport shell toolbar docked cleanly outside #game canvas")

        mp_js = web_dir / "multiplayer.js"
        html_path = web_dir / "index.html"
        game_js_path = web_dir / "game.js"
        html_txt = html_path.read_text(encoding="utf-8", errors="replace") if html_path.exists() else ""
        game_js_txt = game_js_path.read_text(encoding="utf-8", errors="replace") if game_js_path.exists() else ""
        if not mp_js.exists():
            failures.append("Missing mandatory multiplayer module: web-port/multiplayer.js")
        elif "mp-lobby" not in html_txt or "btn-mp-lobby" not in html_txt:
            failures.append("web-port/index.html missing mandatory multiplayer UI (#mp-lobby / #btn-mp-lobby)")
        elif "MultiplayerClient" not in game_js_txt:
            failures.append("web-port/game.js does not wire MultiplayerClient")
        else:
            passed.append("Mandatory Multiplayer wired (multiplayer.js + #mp-lobby + MultiplayerClient)")

    all_js = sorted(web_dir.glob("*.js")) if web_dir.exists() else []
    syntax_ok = True
    for jf in all_js:
        proc = subprocess.run(["node", "--check", str(jf)], capture_output=True, text=True)
        if proc.returncode != 0:
            syntax_ok = False
            failures.append(f"Syntax error in {jf.name}: {(proc.stderr or proc.stdout).strip()[:200]}")
    if all_js and syntax_ok:
        passed.append(f"Syntax OK (`node --check` passed on {len(all_js)} JS files)")

    test_files = sorted((web_dir / "tests").glob("*.test.mjs")) if (web_dir / "tests").exists() else []
    if not test_files:
        warnings.append("No unit tests found in web-port/tests/*.test.mjs")
    else:
        proc = subprocess.run(["node", "--test", *[str(t) for t in test_files]], capture_output=True, text=True)
        if proc.returncode != 0:
            failures.append(f"Unit tests FAILED: {(proc.stderr or proc.stdout).strip()[:300]}")
        else:
            passed.append(f"Unit tests PASSED ({len(test_files)} test file(s))")

    print(f"=== J2ME Parity Gate Report: {ROOT.name} ===")
    for p in passed:
        print(f"  [PASS] {p}")
    for w in warnings:
        print(f"  [WARN] {w}")
    for f in failures:
        print(f"  [FAIL] {f}")

    if failures:
        print(f"\nRESULT: FAIL ({len(failures)} hard gate(s) failed)")
        sys.exit(1)
    print(f"\nRESULT: PASS ({len(passed)} checks passed, {len(warnings)} warning(s))")
    sys.exit(0)


if __name__ == "__main__":
    main()
