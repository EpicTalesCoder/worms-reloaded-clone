/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Canvas
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class s
extends Canvas {
    public int a;
    public int b;
    public int c;
    public int d;
    public n a = p.a();

    public static boolean a(int n2) {
        n2 = (ae.a().a.a & n2) != 0 ? 1 : 0;
        return n2 != 0;
    }

    public static boolean b(int n2) {
        n2 = (ae.a().a.b & n2) != 0 ? 1 : 0;
        return n2 != 0;
    }

    public static boolean c(int n2) {
        n2 = s.b(n2) || (0 & n2) != 0 ? 1 : 0;
        return n2 != 0;
    }

    public static boolean d(int n2) {
        n2 = (ae.a().a.c & 0x1010) != 0 ? 1 : 0;
        return n2 != 0;
    }

    public static long a() {
        return ae.a() - ae.a().a;
    }

    public final int getWidth() {
        return ae.b(ae.a());
    }

    public final int getHeight() {
        return ae.a(ae.a());
    }

    public final void keyPressed(int n2) {
        this.a(ae.a(n2), true);
    }

    public final void keyReleased(int n2) {
        this.a(ae.a(n2), false);
    }

    public final void keyRepeated(int n2) {
    }

    public s() {
        Object object = ae.c();
        ae.a(true, (g)object);
    }

    public final void paint(Graphics graphics) {
    }

    public final void a(int n2, boolean bl) {
        int n3;
        int n4;
        if (this.d > 0) {
            return;
        }
        switch (n2) {
            case 1: {
                n4 = 1;
                break;
            }
            case 2: {
                n4 = 2;
                break;
            }
            case 3: {
                n4 = 4;
                break;
            }
            case 4: {
                n4 = 8;
                break;
            }
            case 7: {
                n4 = 16;
                break;
            }
            case 8: {
                n4 = 0x100000;
                break;
            }
            case 9: {
                n4 = 524288;
                break;
            }
            case 10: {
                n4 = 0x800000;
                break;
            }
            case 11: {
                n4 = 0x200000;
                break;
            }
            case 12: {
                n4 = 0x400000;
                break;
            }
            case 13: {
                n4 = 32;
                break;
            }
            case 14: {
                n4 = 64;
                break;
            }
            case 35: {
                n4 = 262144;
                break;
            }
            case 42: {
                n4 = 131072;
                break;
            }
            case 48: {
                n4 = 128;
                break;
            }
            case 49: {
                n4 = 256;
                break;
            }
            case 50: {
                n4 = 512;
                break;
            }
            case 51: {
                n4 = 1024;
                break;
            }
            case 52: {
                n4 = 2048;
                break;
            }
            case 53: {
                n4 = 4096;
                break;
            }
            case 54: {
                n4 = 8192;
                break;
            }
            case 55: {
                n4 = 16384;
                break;
            }
            case 56: {
                n4 = 32768;
                break;
            }
            case 57: {
                n4 = 65536;
                break;
            }
            default: {
                n4 = n3 = 0;
            }
        }
        if (n4 == 0) {
            if (ae.b(n2) != 0) {
                s.a();
                if (bl) {
                    return;
                }
                return;
            }
        } else {
            n2 = bl ? 1 : 0;
            int n5 = n3;
            s s2 = this;
            s.a();
            w w2 = ae.a().a;
            if (n2 != 0) {
                s2.b |= n5;
                s2.a |= n5;
                w2.a(n5);
                return;
            }
            s2.c |= n5;
            s2.a &= ~n5;
        }
    }
}

