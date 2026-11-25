/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 *  javax.microedition.m3g.Graphics3D
 *  javax.microedition.media.Manager
 *  javax.microedition.media.control.VolumeControl
 *  javax.microedition.midlet.MIDlet
 *  javax.microedition.rms.RecordStore
 */
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.m3g.Graphics3D;
import javax.microedition.media.Manager;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class ae {
    public int a;
    public short[] a;
    private int[] a;
    public Graphics a;
    private static int b = 1;
    private static int[] b = new int[2];
    public static ab a;
    public static Object a;
    public static z a;
    public static v a;
    public static aa a;
    public static long a;
    public static ae a;
    public static boolean a;
    public static z b;
    public static short a;
    private static byte[] a;
    private static RecordStore a;
    private static byte[] b;
    private static int c;
    private static int d;
    private static int e;
    private static boolean b;

    public static ae a(af af2) {
        return new ae(af2);
    }

    public ae(int[] nArray) {
        ae.a(nArray, this);
        Graphics3D.getInstance();
    }

    public final void a(int n2, int n3, int n4, int n5) {
        this.a.drawLine(n2, n3, n4, n5);
    }

    public final void a(int[] nArray, int n2, int n3, int n4, int n5, int n6, int n7, boolean bl) {
        this.a.drawRGB(nArray, 0, n7, n3, n4, n5, n6, true);
    }

    public final void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.a.drawRoundRect(n2, n3, n4 + -1, n5 + -1, 10, 10);
    }

    public final void b(int n2, int n3, int n4, int n5) {
        this.a.fillRect(n2, n3, n4, n5);
    }

    public final void b(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.a.fillRoundRect(n2, n3, n4, n5, n6, n7);
    }

    public final void c(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.a.fillTriangle(n2, n3, n4, n5, n6, n7);
    }

    public final void a(Image image, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9) {
        this.a.drawRegion(image, n2, n3, n4, n5, n6 >>> 16, n7, n8, n9);
    }

    public final int a() {
        return this.a.getClipWidth();
    }

    public final void c(int n2, int n3, int n4, int n5) {
        this.a.setClip(n2, n3, n4, n5);
    }

    public final int b() {
        return this.a.getColor();
    }

    public final void a(int n2) {
        this.a.setColor(n2 & 0xFFFFFF);
    }

    public final void a(Object object) {
        this.a.setFont((Font)object);
    }

    public final void a(String string, int n2, int n3, int n4) {
        this.a.drawString(string, n2, n3, n4);
    }

    public final short[] a() {
        return this.a;
    }

    private ae(af af2) {
        ae.a(ae.a((int)af2.a, (int)af2.b), this);
        Graphics3D.getInstance();
        this.a = ((u)af2).a.getGraphics();
    }

    private static void a(int[] nArray, ae ae2) {
        int[] nArray2 = new int[3];
        Object[] objectArray = nArray2;
        nArray2[0] = nArray[0];
        objectArray[1] = nArray[1];
        objectArray[2] = nArray[2];
        ae2.a = objectArray;
        short s2 = (short)nArray[0];
        short s3 = (short)nArray[1];
        short[] sArray = new short[4];
        objectArray = sArray;
        sArray[0] = 0;
        objectArray[1] = 0;
        objectArray[2] = s2;
        objectArray[3] = s3;
        ae2.a = (short[])objectArray;
    }

    /*
     * Unable to fully structure code
     */
    public static void a(d var0) {
        block31: {
            block32: {
                while (var0.a != 2) {
                    var0 = var0.a;
                }
                var1_1 = var0.b;
                if (ae.a(var1_1) != 200 || var1_1.a == null && !ae.a(var1_1)) break block32;
                var2_2 = ae.a();
                var3_3 = true;
                var4_4 = false;
                while (!var4_4) {
                    ae.b();
                    try {
                        block29: {
                            block30: {
                                if (var1_1.a.getState() == 300) ** GOTO lbl-1000
                                var5_6 = var2_2.b;
                                var6_8 = var1_1.a;
                                var7_10 = var3_3;
                                var8_11 = 0x7FFFFFFF;
                                var9_12 = -1;
                                var10_13 = false;
                                var11_14 = var5_6.a;
                                var12_15 = 0x7FFFFFFF;
                                var13_16 = -1;
                                var14_17 = 0;
                                ae.b();
                                for (var15_18 = 0; var15_18 < ae.a; ++var15_18) {
                                    if (var11_14[var15_18] == null) {
                                        if (var7_10 && !var10_13) {
                                            var14_17 = ae.a(Thread.currentThread());
                                            ae.b[var14_17] = var13_16 = ae.b[var14_17] - 1;
                                            var5_5 = var15_18;
                                            break block29;
                                        }
                                    } else if (var11_14[var15_18].a == var6_8) {
                                        var14_17 = (short)(var14_17 + 1);
                                    }
                                    if (var6_8.equals("audio/midi")) {
                                        if (var14_17 == 2) {
                                            var10_13 = true;
                                        }
                                    } else if (var6_8.equals("audio/x-wav")) {
                                        if (var14_17 == 8) {
                                            var10_13 = true;
                                        }
                                    } else if (var6_8.equals("audio/amr") && var14_17 == 8) {
                                        var10_13 = true;
                                    }
                                    if (var11_14[var15_18] != null && var11_14[var15_18].a == var6_8 && var5_6.a[var15_18] < var12_15) {
                                        var12_15 = var5_6.a[var15_18];
                                        var13_16 = var15_18;
                                    }
                                    if (var5_6.a[var15_18] >= var8_11 || var10_13 || var11_14[var15_18] == null) continue;
                                    var8_11 = var5_6.a[var15_18];
                                    var9_12 = var15_18;
                                }
                                if (var10_13) {
                                    var9_12 = var13_16;
                                }
                                if (var9_12 != -1) {
                                    var15_19 = var11_14[var9_12];
                                    for (var14_17 = 0; var14_17 <= 0; ++var14_17) {
                                        if (ae.a().a[var14_17].a.a.a != var15_19) continue;
                                        ae.b();
                                        try {
                                            ae.b(var15_19, ae.a().b);
                                            var15_19.a.deallocate();
                                        }
                                        catch (Throwable v0) {}
                                        var15_20 = ae.a(Thread.currentThread());
                                        ae.b[var15_20] = var14_17 = ae.b[var15_20] - 1;
                                        break block30;
                                    }
                                    ae.a(var15_19);
                                }
                            }
                            var15_18 = ae.a(Thread.currentThread());
                            ae.b[var15_18] = var14_17 = ae.b[var15_18] - 1;
                            var5_5 = var9_12;
                        }
                        if (var5_5 == -1) lbl-1000:
                        // 2 sources

                        {
                            var4_4 = true;
                        } else {
                            var1_1.a.prefetch();
                            var6_8 = var2_2.b;
                            ae.b();
                            var6_8.a[var5_5] = var1_1;
                            v1 = var5_5;
                            v2 = var6_8.a;
                            var6_8.a = var5_5 = v2 + 1;
                            var6_8.a[v1] = v2;
                            var6_9 = ae.a(Thread.currentThread());
                            ae.b[var6_9] = var5_5 = ae.b[var6_9] - 1;
                        }
                    }
                    catch (Throwable v3) {
                        var3_3 = false;
                    }
                    var6_7 = ae.a(Thread.currentThread());
                    ae.b[var6_7] = var5_5 = ae.b[var6_7] - 1;
                }
            }
            ae.b();
            try {
                if (ae.a(var1_1) == 300) {
                    ae.a();
                    if (!ae.a().a) {
                        ae.c(var0);
                        if (var0.b) {
                            var0.b = false;
                        }
                        var1_1.a.start();
                    }
                }
            }
            catch (Throwable v4) {
                if (var0.a >= 20) break block31;
                ++var0.a;
                ae.a(var0);
            }
        }
        var6_7 = ae.a(Thread.currentThread());
        ae.b[var6_7] = var5_5 = ae.b[var6_7] - 1;
    }

    private static int a(int n2, boolean bl) {
        int n3;
        g g2;
        int n4;
        if (n2 == -9996) {
            return 1;
        }
        if (n2 == -9997) {
            return 2;
        }
        if (n2 == -9998) {
            return 3;
        }
        if (n2 == -9999) {
            return 4;
        }
        if (n2 == 99999) {
            return 9;
        }
        if (n2 == 8) {
            return 10;
        }
        if (n2 == 99999) {
            return 42;
        }
        if (n2 == 99999) {
            return 35;
        }
        if (n2 == -5) {
            return 7;
        }
        if (n2 == -6) {
            return 13;
        }
        if (n2 == -7) {
            return 14;
        }
        ab ab2 = a;
        if (ab2.b == null) {
            ab2.b = new g(4);
        }
        if ((n4 = ae.b(n2, g2 = ab2.b)) != 0) {
            if (144 == n4 && 144 == g2.a && bl) {
                g2.b = false;
                g2.b = g2.b == 0 ? 1 : (g2.b == 1 ? 2 : 0);
            } else if (144 == n4 && !bl) {
                g2.d = true;
                g2.b = true;
            } else if (144 == n4 && bl) {
                g2.d = false;
                g2.b = false;
                if (g2.b == 1) {
                    g2.b = 2;
                }
            } else {
                n3 = g2.b == 1 || g2.b == 2 || g2.b ? 1 : 0;
                g2.d = n3;
                if (g2.b == 1 && bl) {
                    g2.b = 0;
                }
            }
            if (!bl) {
                g2.a = n4;
            }
        }
        if (n4 != 0 && !g2.b) {
            if (ae.a(g2) && 145 == n4) {
                g2.b = 0;
                g2.d = false;
                if (g2.b == 0) {
                    g2.b = 1;
                }
            }
            if (145 == n4 && 145 == g2.a && bl) {
                g2.a = false;
                g2.b = g2.b == 0 ? (short)1 : (g2.b == 1 ? (short)2 : (short)0);
            } else if (145 == n4 && !bl) {
                g2.e = true;
                g2.a = true;
            } else if (145 == n4 && bl) {
                g2.e = false;
                g2.a = false;
                if (g2.b == 1) {
                    g2.b = (short)2;
                }
            } else if (!ae.a(g2)) {
                n3 = g2.b == 1 || g2.b == 2 || g2.a ? 1 : 0;
                g2.e = n3;
                if (g2.b == 1 && bl) {
                    g2.b = 0;
                }
            }
            if (!bl) {
                g2.a = n4;
            }
        }
        n4 = ae.b(n2, g2);
        if (g2.e) {
            n3 = n4;
            if (n3 >= 97 && n3 <= 122) {
                n3 &= 0xFFFFFFDF;
            }
        } else {
            n3 = n4;
        }
        if (n3 != 0) {
            return n3;
        }
        if (n2 == -10) {
            return 0;
        }
        if (n2 == 99999) {
            return 0;
        }
        if (n2 == 99999) {
            return 0;
        }
        if (n2 == 99999) {
            return 0;
        }
        if (n2 == 99999) {
            return 0;
        }
        if (n2 == 99999) {
            return 0;
        }
        return 0;
    }

    private static int b(int n2, g g2) {
        int n3;
        if (ae.a(g2)) {
            int n4 = n2;
            if (n2 >= 65 && n2 <= 90) {
                n4 = n2 - 65 + 97;
            }
            switch (n4) {
                case 98: {
                    return 56;
                }
                case 101: {
                    return 41;
                }
                case 102: {
                    return 52;
                }
                case 103: {
                    return 53;
                }
                case 104: {
                    return 54;
                }
                case 105: {
                    return 43;
                }
                case 106: {
                    return 35;
                }
                case 107: {
                    return 45;
                }
                case 108: {
                    return 39;
                }
                case 109: {
                    return 48;
                }
                case 110: {
                    return 57;
                }
                case 111: {
                    return 61;
                }
                case 113: {
                    return 34;
                }
                case 114: {
                    return 49;
                }
                case 116: {
                    return 50;
                }
                case 117: {
                    return 42;
                }
                case 118: {
                    return 55;
                }
                case 119: {
                    return 40;
                }
                case 121: {
                    return 51;
                }
                case 44: {
                    return 59;
                }
                case 46: {
                    return 58;
                }
                case 64: {
                    return 47;
                }
                case 63: {
                    return 33;
                }
            }
        }
        if (n2 == 99999) {
            return 31;
        }
        if (n2 == 99998) {
            return 144;
        }
        if (n2 == 99999) {
            return 145;
        }
        if (n2 == 99999) {
            return 145;
        }
        if (n2 == 99999) {
            return 8;
        }
        if (n2 == 32) {
            return 32;
        }
        if (n2 == 99999) {
            return 35;
        }
        if (n2 == 99999) {
            return 42;
        }
        if (n2 >= 48 && n2 <= 57) {
            n3 = n2 - 48 + 48;
        } else {
            switch (n2) {
                case 42: {
                    n3 = 42;
                    break;
                }
                case 35: {
                    n3 = 35;
                    break;
                }
                case 0: {
                    n3 = 0;
                    break;
                }
                default: {
                    if (n2 >= 32 && n2 <= 126) {
                        n3 = n2;
                    } else {
                        switch (n2) {
                            case 8: {
                                n3 = 10;
                                break;
                            }
                            case 9: {
                                n3 = 32;
                                break;
                            }
                            case 13: {
                                n3 = 31;
                                break;
                            }
                            case 128: {
                                n3 = 15;
                                break;
                            }
                            case 163: {
                                n3 = 16;
                                break;
                            }
                            case 165: {
                                n3 = 17;
                                break;
                            }
                            case 192: {
                                n3 = 18;
                                break;
                            }
                            case 194: {
                                n3 = 19;
                                break;
                            }
                            case 196: {
                                n3 = 29;
                                break;
                            }
                            case 199: {
                                n3 = 20;
                                break;
                            }
                            case 200: {
                                n3 = 21;
                                break;
                            }
                            case 201: {
                                n3 = 22;
                                break;
                            }
                            case 202: {
                                n3 = 23;
                                break;
                            }
                            case 203: {
                                n3 = 24;
                                break;
                            }
                            case 206: {
                                n3 = 25;
                                break;
                            }
                            case 207: {
                                n3 = 26;
                                break;
                            }
                            case 209: {
                                n3 = 30;
                                break;
                            }
                            case 212: {
                                n3 = 27;
                                break;
                            }
                            case 214: {
                                n3 = 28;
                                break;
                            }
                            case 220: {
                                n3 = 127;
                                break;
                            }
                            case 224: {
                                n3 = 128;
                                break;
                            }
                            case 226: {
                                n3 = 129;
                                break;
                            }
                            case 228: {
                                n3 = 130;
                                break;
                            }
                            case 231: {
                                n3 = 132;
                                break;
                            }
                            case 232: {
                                n3 = 131;
                                break;
                            }
                            case 233: {
                                n3 = 133;
                                break;
                            }
                            case 234: {
                                n3 = 134;
                                break;
                            }
                            case 235: {
                                n3 = 135;
                                break;
                            }
                            case 238: {
                                n3 = 136;
                                break;
                            }
                            case 239: {
                                n3 = 137;
                                break;
                            }
                            case 241: {
                                n3 = 140;
                                break;
                            }
                            case 244: {
                                n3 = 138;
                                break;
                            }
                            case 246: {
                                n3 = 139;
                                break;
                            }
                            case 252: {
                                n3 = 141;
                                break;
                            }
                            case 8364: {
                                n3 = 15;
                                break;
                            }
                            default: {
                                n3 = 0;
                            }
                        }
                    }
                    int n5 = n3;
                    if (n3 != 0) break;
                    try {
                        switch (a.getGameAction(n2)) {
                            case 1: {
                                n5 = 1;
                                break;
                            }
                            case 2: {
                                n5 = 3;
                                break;
                            }
                            case 5: {
                                n5 = 4;
                                break;
                            }
                            case 6: {
                                n5 = 2;
                                break;
                            }
                            case 8: {
                                n5 = 7;
                            }
                        }
                    }
                    catch (Exception exception) {}
                    n3 = n5;
                }
            }
        }
        return n3;
    }

    public static void a(int n2, byte[] byArray, int n3, g g2) {
        while (true) {
            if (g2.a != 5) {
                try {
                    ae.a(1, true);
                    if (b == null) {
                        byte[] byArray2 = new byte[n3 + 3];
                        b = byArray2;
                        byArray2[0] = (byte)e;
                        ae.b[1] = (byte)(n3 >> 8);
                        ae.b[2] = (byte)n3;
                        System.arraycopy(byArray, 0, b, 3, n3);
                        a.setRecord(d, b, 0, b.length);
                    } else {
                        n2 = 0;
                        if (c < b.length) {
                            n2 = (b[c + 1] & 0xFF) << 8 | b[c + 2] & 0xFF;
                        }
                        if (n2 == n3) {
                            System.arraycopy(byArray, 0, b, c += 3, n3);
                            a.setRecord(d, b, 0, b.length);
                        } else if (n2 > n3) {
                            ++c;
                            ae.b[ae.c++] = (byte)(n3 >> 8);
                            ae.b[ae.c++] = (byte)n3;
                            System.arraycopy(byArray, 0, b, c, n3);
                            System.arraycopy(b, c + n2, b, c + n3, b.length - c - n2);
                            a.setRecord(d, b, 0, b.length - n2 + n3);
                        } else {
                            int n4 = b.length + n3 - n2;
                            if (n2 == 0) {
                                n4 += 3;
                            }
                            byte[] byArray3 = new byte[n4];
                            System.arraycopy(b, 0, byArray3, 0, c);
                            byArray3[ae.c++] = (byte)e;
                            byArray3[ae.c++] = (byte)(n3 >> 8);
                            byArray3[ae.c++] = (byte)n3;
                            System.arraycopy(byArray, 0, byArray3, c, n3);
                            if (n2 != 0) {
                                System.arraycopy(b, c + n2, byArray3, c + n3, b.length - (c + n2));
                            }
                            a.setRecord(d, byArray3, 0, byArray3.length);
                        }
                    }
                    a.closeRecordStore();
                    return;
                }
                catch (Exception exception) {
                    return;
                }
            }
            g2 = g2.a;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(short s2, short s3, short s4, z z2) {
        if ((z2.c || z2.a) && s2 != 6) {
            return;
        }
        switch (s2) {
            case 5: {
                z2.c = true;
                ae.a();
                z2.d = ae.a().a;
                ae.a();
                ae.a();
                ae.a(true);
                ae.a(0, ae.a().a);
                long[] lArray = z2.a;
                synchronized (z2.a) {
                    z2.a = 0;
                    // ** MonitorExit[var5_4] (shouldn't be in output)
                    break;
                }
            }
            case 6: {
                ae.a();
                ae.a();
                ae.a(false);
                break;
            }
        }
        {
            int n2 = 1;
            long[] lArray = z2.a;
            synchronized (z2.a) {
                switch (s2) {
                    case 1: {
                        if (z2.a < 5) break;
                        return;
                    }
                    case 5: 
                    case 6: {
                        if (z2.a < 8) break;
                        return;
                    }
                    default: {
                        if (z2.a < 6) break;
                        return;
                    }
                }
                if (n2 == 0) return;
                int n3 = z2.a;
                z2.a = n2 = n3 + 1;
                z2.a[n3] = (long)s2 << 48 | (long)s3 << 32 & 0xFFFF00000000L | (long)s4 << 16 & 0xFFFF0000L;
                // ** MonitorExit[var4_6] (shouldn't be in output)
                return;
            }
        }
    }

    public static int a(int n2) {
        int n3;
        if (n2 >= 48 && n2 <= 57) {
            return n2 - 48 + 48;
        }
        switch (n2) {
            case 42: {
                return 42;
            }
            case 35: {
                return 35;
            }
            case 0: {
                return 0;
            }
        }
        if (n2 >= 32 && n2 <= 126) {
            n3 = n2;
        } else {
            switch (n2) {
                case 8: {
                    n3 = 10;
                    break;
                }
                case 9: {
                    n3 = 32;
                    break;
                }
                case 13: {
                    n3 = 31;
                    break;
                }
                case 128: {
                    n3 = 15;
                    break;
                }
                case 163: {
                    n3 = 16;
                    break;
                }
                case 165: {
                    n3 = 17;
                    break;
                }
                case 192: {
                    n3 = 18;
                    break;
                }
                case 194: {
                    n3 = 19;
                    break;
                }
                case 196: {
                    n3 = 29;
                    break;
                }
                case 199: {
                    n3 = 20;
                    break;
                }
                case 200: {
                    n3 = 21;
                    break;
                }
                case 201: {
                    n3 = 22;
                    break;
                }
                case 202: {
                    n3 = 23;
                    break;
                }
                case 203: {
                    n3 = 24;
                    break;
                }
                case 206: {
                    n3 = 25;
                    break;
                }
                case 207: {
                    n3 = 26;
                    break;
                }
                case 209: {
                    n3 = 30;
                    break;
                }
                case 212: {
                    n3 = 27;
                    break;
                }
                case 214: {
                    n3 = 28;
                    break;
                }
                case 220: {
                    n3 = 127;
                    break;
                }
                case 224: {
                    n3 = 128;
                    break;
                }
                case 226: {
                    n3 = 129;
                    break;
                }
                case 228: {
                    n3 = 130;
                    break;
                }
                case 231: {
                    n3 = 132;
                    break;
                }
                case 232: {
                    n3 = 131;
                    break;
                }
                case 233: {
                    n3 = 133;
                    break;
                }
                case 234: {
                    n3 = 134;
                    break;
                }
                case 235: {
                    n3 = 135;
                    break;
                }
                case 238: {
                    n3 = 136;
                    break;
                }
                case 239: {
                    n3 = 137;
                    break;
                }
                case 241: {
                    n3 = 140;
                    break;
                }
                case 244: {
                    n3 = 138;
                    break;
                }
                case 246: {
                    n3 = 139;
                    break;
                }
                case 252: {
                    n3 = 141;
                    break;
                }
                case 8364: {
                    n3 = 15;
                    break;
                }
                default: {
                    n3 = 0;
                }
            }
        }
        int n4 = n3;
        if (n3 != 0) {
            return n3;
        }
        try {
            switch (a.getGameAction(n2)) {
                case 1: {
                    n4 = 1;
                    break;
                }
                case 2: {
                    n4 = 3;
                    break;
                }
                case 5: {
                    n4 = 4;
                    break;
                }
                case 6: {
                    n4 = 2;
                    break;
                }
                case 8: {
                    n4 = 7;
                }
            }
        }
        catch (Exception exception) {}
        return n4;
    }

    public static final void a(int n2, boolean bl, v v2) {
        block18: {
            int n3;
            while (true) {
                switch (n2) {
                    case 0: {
                        return;
                    }
                    case 12: {
                        d d2;
                        if (!bl && (d2 = ae.a.a) != null) {
                            ae.a((short)(d2.b - 51), d2);
                        }
                        return;
                    }
                    case 11: {
                        d d3;
                        if (!bl && (d3 = ae.a.a) != null) {
                            ae.a((short)(d3.b + 51), d3);
                        }
                        return;
                    }
                    case 144: 
                    case 145: {
                        return;
                    }
                }
                n3 = n2;
                if (v2.a[n2] != 0) {
                    n3 = v2.a[n2];
                }
                int n4 = n3;
                short s2 = v2.a[n3];
                int n5 = v2.b[s2];
                if (bl) {
                    if (n5 == n2) {
                        v2.b[s2] = 0;
                        if (v2.a == n2) {
                            v2.a = 0;
                        }
                        ((l)v2).a.a(n4, false);
                        return;
                    }
                    if (v2.f) {
                        bl = true;
                        n2 = n5;
                        continue;
                    }
                    break block18;
                }
                if (n5 == 0) break;
                if (s2 != 0) {
                    n3 = n5;
                    if (v2.a[n5] != 0) {
                        n3 = v2.a[n5];
                    }
                    if (n3 == n4) break block18;
                }
                ae.a(n5, true, v2);
                if (!v2.f) {
                    bl = false;
                    continue;
                }
                break block18;
                break;
            }
            v2.b[s2] = (short)n2;
            v2.a = n2;
            v2.b = Integer.MAX_VALUE;
            ((l)v2).a.a(n3, true);
        }
    }

    private static void a(int n2, boolean n3) {
        try {
            b = true;
            d = n2 % 1024 + 1;
            e = (byte)(n2 / 1024);
            a = RecordStore.openRecordStore((String)"DataStore", (boolean)true);
            c = 0;
            if (n3 != 0) {
                for (n3 = a.getNumRecords() + 1; n3 <= d; ++n3) {
                    a.addRecord(a, 0, 0);
                }
            } else if (a.getNumRecords() < d) {
                b = false;
                return;
            }
            if ((b = a.getRecord(d)) == null) {
                b = false;
                return;
            }
            while (c < b.length && b[c] != e) {
                c += (b[c + 1] & 0xFF) << 8 | b[c + 2] & 0xFF;
                c += 3;
            }
            n3 = c < b.length ? 1 : 0;
            b = n3;
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static final void a(t t2, d d2) {
        int n2;
        int n3;
        block13: {
            block10: {
                block12: {
                    block11: {
                        d2 = d2.a;
                        ae.b();
                        if (d2.b == null) break block10;
                        if (ae.a(d2)) break block11;
                        ae.b();
                        n3 = ae.a(Thread.currentThread());
                        b[n3] = n2 = b[n3] - 1;
                        if (!d2.b || ae.a.a.b) break block12;
                    }
                    ae.b(d2);
                }
                if (d2.a == t2 && d2.b.a != null) break block13;
                if (ae.a(d2.b) != 300 && d2.b.a != null) {
                    ae.a(d2.b);
                }
            }
            d2.a = t2;
            d2.a = 0;
            if (d2.a != null) {
                d2.b = t2 = t2.a;
                if (t2.a == null) {
                    ae.a(t2);
                }
                ae.a(d2.a, d2);
            } else {
                d2.b = null;
            }
        }
        n2 = ae.a(Thread.currentThread());
        b[n2] = n3 = b[n2] - 1;
    }

    public static final boolean a(x object) {
        boolean bl = ((x)object).a[0] == 0;
        if (bl) {
            bl = false;
        } else {
            ad ad2;
            object = ae.a((x)object);
            z z2 = a;
            boolean bl2 = false;
            ad ad3 = ad2;
            ad2 = new ad();
            x x2 = ad3.a;
            x x3 = ae.a(ae.b(ae.a(ae.a("Browser-Launch-Enabled"))));
            short[] sArray = x3.a;
            int n2 = ae.a(x3.a);
            ae.a(sArray, n2, x2);
            ae.a(ad3.a);
            try {
                z2.e = true;
                z2.a = object;
                ae.b(z2);
                bl2 = true;
            }
            catch (Throwable throwable) {}
            bl = bl2;
        }
        return bl;
    }

    public static void b(d d2) {
        while (d2.a != 2) {
            d2 = d2.a;
        }
        t t2 = d2.b;
        if (t2 != null && t2.a != null) {
            int n2;
            block7: {
                int n3;
                ae.b();
                t2.a.equals("audio/x-wav");
                t2.a.equals("audio/midi");
                t2.a.equals("audio/amr");
                d d3 = d2;
                ae.b();
                try {
                    if (!ae.a(d3)) {
                        int n4;
                        int n5 = ae.a(Thread.currentThread());
                        b[n5] = n4 = b[n5] - 1;
                        break block7;
                    }
                    d3.b.a.stop();
                    d3.b = true;
                }
                catch (Throwable throwable) {}
                int n6 = ae.a(Thread.currentThread());
                b[n6] = n3 = b[n6] - 1;
            }
            try {
                d2.b = false;
                t2.a.setMediaTime(0L);
            }
            catch (Throwable throwable) {}
            int n7 = ae.a(Thread.currentThread());
            b[n7] = n2 = b[n7] - 1;
        }
    }

    public static final int a(int n2, byte[] byArray) {
        byte[] byArray2 = byArray;
        int n3 = -1;
        try {
            ae.a(1, false);
            if (b) {
                n3 = (b[c + 1] & 0xFF) << 8 | b[c + 2] & 0xFF;
                if (byArray.length != n3) {
                    byArray2 = new byte[n3];
                }
                System.arraycopy(b, c + 3, byArray2, 0, n3);
            }
            a.closeRecordStore();
        }
        catch (Exception exception) {}
        return n3;
    }

    public static int b(int n2) {
        if (n2 >= 32 && n2 <= 126) {
            return n2;
        }
        switch (n2) {
            case 10: {
                return 8;
            }
            case 31: {
                return 13;
            }
            case 15: {
                return 128;
            }
            case 16: {
                return 163;
            }
            case 17: {
                return 165;
            }
            case 18: {
                return 192;
            }
            case 19: {
                return 194;
            }
            case 29: {
                return 196;
            }
            case 20: {
                return 199;
            }
            case 21: {
                return 200;
            }
            case 22: {
                return 201;
            }
            case 23: {
                return 202;
            }
            case 24: {
                return 203;
            }
            case 25: {
                return 206;
            }
            case 26: {
                return 207;
            }
            case 30: {
                return 209;
            }
            case 27: {
                return 212;
            }
            case 28: {
                return 214;
            }
            case 127: {
                return 220;
            }
            case 128: {
                return 224;
            }
            case 129: {
                return 226;
            }
            case 130: {
                return 228;
            }
            case 132: {
                return 231;
            }
            case 131: {
                return 232;
            }
            case 133: {
                return 233;
            }
            case 134: {
                return 234;
            }
            case 135: {
                return 235;
            }
            case 136: {
                return 238;
            }
            case 137: {
                return 239;
            }
            case 140: {
                return 241;
            }
            case 138: {
                return 244;
            }
            case 139: {
                return 246;
            }
            case 141: {
                return 252;
            }
        }
        return 0;
    }

    public static boolean a(d object) {
        boolean bl;
        while (((d)object).a != 2) {
            object = ((d)object).a;
        }
        object = ((d)object).b;
        if (object == null || ((t)object).a == null) {
            bl = false;
        } else {
            int n2;
            ae.b();
            bl = object != null && ae.a((t)object) == 400;
            int n3 = ae.a(Thread.currentThread());
            b[n3] = n2 = b[n3] - 1;
        }
        return bl;
    }

    /*
     * WARNING - void declaration
     */
    public static void a(boolean bl, d d2) {
        int n2;
        void var0_1;
        d d3;
        while (d3.a != 2) {
            d3 = d3.a;
        }
        ae.b();
        d3.a = var0_1;
        if (d3.b != null && d3.b.a != null) {
            try {
                void var0_4;
                d3 = d3.b.a;
                if (var0_1 != false) {
                    int n3 = -1;
                } else {
                    boolean bl2 = true;
                }
                d3.setLoopCount((int)var0_4);
            }
            catch (Exception exception) {}
        }
        int n4 = ae.a(Thread.currentThread());
        b[n4] = n2 = b[n4] - 1;
    }

    public static int a(int n2, g g2) {
        while (true) {
            if (g2.a != 5) {
                int n3 = -1;
                try {
                    ae.a(1, false);
                    if (b) {
                        n3 = (b[c + 1] & 0xFF) << 8 | b[c + 2] & 0xFF;
                    }
                    a.closeRecordStore();
                }
                catch (Exception exception) {}
                return n3;
            }
            g2 = g2.a;
        }
    }

    public static final void a(short[] sArray, int n2, x x2) {
        if (x2.a == sArray) {
            return;
        }
        x2.a = null;
        short[] sArray2 = x2.a = new short[n2 + 1];
        for (int i2 = 0; i2 < n2; ++i2) {
            sArray2[i2] = sArray[i2];
            if (sArray[i2] == 0) break;
        }
        x2.a[x2.a.length - 1] = 0;
    }

    /*
     * WARNING - void declaration
     */
    private static boolean a(t t2) {
        void var1_4;
        int n2;
        ae.b();
        try {
            t2.a = Manager.createPlayer((InputStream)new ByteArrayInputStream(t2.b.a), (String)t2.a);
            t2.a.realize();
        }
        catch (Throwable throwable) {
            if (t2.a != null) {
                t2.a.close();
            }
            t2.a = null;
        }
        int bl = ae.a(Thread.currentThread());
        b[bl] = n2 = b[bl] - 1;
        if (t2.a != null) {
            boolean bl2 = true;
        } else {
            boolean bl3 = false;
        }
        return (boolean)var1_4;
    }

    private static void b() {
        Thread thread = Thread.currentThread();
        int n2 = ae.a(thread);
        if (b[n2] <= 0) {
            int n3 = thread == ae.a.a ? 0 : 1;
            int n4 = n3;
            ae.b[n2] = 1;
            b = n3;
            while (b[n4] > 0 && b != n2) {
                try {
                    Thread.sleep(10L);
                }
                catch (InterruptedException interruptedException) {}
            }
        } else {
            int n5;
            b[n2] = n5 = b[n2] + 1;
        }
    }

    private static void c(d d2) {
        int n2;
        ae.b();
        try {
            VolumeControl volumeControl;
            if (d2.b != null && (volumeControl = (VolumeControl)d2.b.a.getControl("VolumeControl")) != null) {
                ae.a();
                volumeControl.setLevel(100 * d2.b * ae.a.a.c >> 16);
            }
        }
        catch (Exception exception) {}
        int n3 = ae.a(Thread.currentThread());
        b[n3] = n2 = b[n3] - 1;
    }

    private static void a(t t2) {
        int n2;
        if (t2.a == null) {
            return;
        }
        ae.b();
        ae.b(t2, ae.a().b);
        try {
            t2.a.close();
        }
        catch (Exception exception) {}
        t2.a = null;
        int n3 = ae.a(Thread.currentThread());
        b[n3] = n2 = b[n3] - 1;
    }

    public static short[] a(short[] sArray) {
        short[] sArray2 = new short[4];
        short[] sArray3 = sArray2;
        sArray2[0] = sArray[0];
        sArray3[1] = sArray[1];
        sArray3[2] = sArray[2];
        sArray3[3] = sArray[3];
        return sArray3;
    }

    public static boolean a(int n2, g g2) {
        while (true) {
            if (g2.a != 5) {
                try {
                    ae.a(1, false);
                    a.closeRecordStore();
                }
                catch (Exception exception) {}
                return b;
            }
            g2 = g2.a;
        }
    }

    private static void a(short s2, d d2) {
        if (s2 > 255) {
            s2 = (short)255;
        }
        if (s2 < 0) {
            s2 = 0;
        }
        d2.c = d2.b = s2;
        for (s2 = 0; s2 <= 0; s2 = (short)(s2 + 1)) {
            ae.c(d2.a[0].a);
        }
    }

    public static void a(z z2) {
        long l2 = System.currentTimeMillis();
        if (z2.a == -1L) {
            z2.a = l2;
        }
        if (!(z2.c || z2.a || z2.b || z2.f)) {
            ae.a((short)5, (short)0, (short)0, z2);
        }
    }

    private static void b(t t2, d d2) {
        int n2;
        int n3;
        ae.b();
        for (n3 = 0; n3 < a; ++n3) {
            if (d2.a[n3] != t2) continue;
            d2.a[n3] = null;
            break;
        }
        n3 = ae.a(Thread.currentThread());
        b[n3] = n2 = b[n3] - 1;
    }

    public static String a(x x2) {
        int n2 = ae.a(x2.a);
        int n3 = 0;
        char[] cArray = new char[n2];
        for (int i2 = 0; i2 < n2; ++i2) {
            int n4 = n3++;
            cArray[i2] = (char)x2.a[n4];
        }
        return new String(cArray);
    }

    public static final int a(short[] sArray, int n2, int n3, int n4, int n5, m m2) {
        int n6;
        int n7;
        while (true) {
            if (n3 > n4) {
                return n5;
            }
            n7 = n3 + n4 >> 1;
            n6 = sArray[n7] & 0xFFFF;
            if (n2 == n6) {
                return n7;
            }
            if (n2 < n6) break;
            n3 = n7 + 1;
        }
        n6 = ae.a(sArray, n2, n3, n7 - 1, n5, m2);
        return n6;
    }

    public static final short a(d d2) {
        return d2.b;
    }

    public static x a(String object) {
        char[] cArray = ((String)object).toCharArray();
        object = new short[cArray.length + 1];
        for (int i2 = 0; i2 < cArray.length; ++i2) {
            object[i2] = (short)cArray[i2];
        }
        object[((Object)object).length - 1] = false;
        return new x((short[])object);
    }

    private static void d(d d2) {
        int n2;
        int n3;
        ae.b();
        for (n3 = 0; n3 <= 0; ++n3) {
            ae.b(d2.a[0].a);
        }
        n3 = ae.a(Thread.currentThread());
        b[n3] = n2 = b[n3] - 1;
    }

    public static void b(z z2) {
        z2.f = true;
        ae.d(ae.a());
        System.gc();
        if (z2.e) {
            try {
                z2.platformRequest(z2.a);
            }
            catch (Exception exception) {}
        }
        z2.notifyDestroyed();
    }

    public static String a(String string) {
        new ad();
        return ae.b(string);
    }

    private static int a(t t2) {
        int n2;
        ae.b();
        int n3 = 0;
        if (t2.a != null) {
            n3 = t2.a.getState();
        }
        int n4 = ae.a(Thread.currentThread());
        b[n4] = n2 = b[n4] - 1;
        return n3;
    }

    private static void a(int n2, i i2) {
        if (i2.a) {
            Display.getDisplay((MIDlet)ae.a.a).vibrate(n2);
            System.currentTimeMillis();
        }
    }

    public static int[] a(int n2, int n3) {
        int[] nArray = new int[3];
        int[] nArray2 = nArray;
        nArray[0] = n2;
        nArray2[1] = n3;
        nArray2[2] = 24;
        return nArray2;
    }

    public static final void b(int n2) {
        if (ae.a.a != (n2 = ae.a(n2, false))) {
            ae.a(n2, false, a);
        }
    }

    public static short a(byte[] byArray, int n2) {
        return (short)((byArray[n2] & 0xFF) << 8 | byArray[n2 + 1] & 0xFF);
    }

    public static final void a(v v2) {
        short[] sArray = v2.b;
        for (int i2 = 0; i2 < 149; ++i2) {
            sArray[i2] = 0;
        }
        v2.a = 0;
    }

    public static ag a() {
        ab ab2 = a;
        if (ab2.a == null) {
            ab2.a = new ag();
        }
        return ab2.a;
    }

    public static g a() {
        if (ae.a.c == null) {
            ae.a.c = new g(0);
        }
        return ae.a.c;
    }

    public static d a() {
        if (ae.a.a == null) {
            ae.a.a = new d(1);
        }
        return ae.a.a;
    }

    private static String b(String string) {
        String string2 = null;
        try {
            string2 = a.getAppProperty(string);
        }
        catch (Exception exception) {}
        if (string2 == null) {
            string2 = "NULL";
        }
        return string2;
    }

    private static boolean a(g g2) {
        boolean bl = g2.c || g2.d;
        return bl;
    }

    public static void a(boolean bl) {
        ae.a().a = bl;
        if (bl) {
            ae.d(ae.a());
        }
    }

    public static m a() {
        return ((l)ae.a.a).a;
    }

    public static g b() {
        ab ab2 = a;
        if (ab2.a == null) {
            ab2.a = new g(5);
        }
        return ab2.a;
    }

    public static g c() {
        ab ab2 = a;
        if (ab2.b == null) {
            ab2.b = new g(4);
        }
        return ab2.b;
    }

    public static final void c(int n2) {
        ae.a(ae.a(n2, true), true, a);
    }

    private static int a(Thread thread) {
        int n2 = thread == ae.a.a ? 1 : 0;
        return n2;
    }

    public static final void a(int n2, int n3, v v2) {
        ae.a(v2);
        v2.a[n2] = (short)n3;
    }

    public static void a() {
        v v2 = ae.a.a;
        if (!v2.e) {
            v2.e = true;
        }
        ae.b(a);
    }

    public static int a(short[] sArray) {
        int n2 = 0;
        while (sArray[n2] != 0) {
            ++n2;
        }
        return n2;
    }

    public static void a(byte[] byArray, byte[] byArray2, int n2, int n3) {
        System.arraycopy(byArray2, n2, byArray, 0, n3);
    }

    public static void a(short[] sArray, short[] sArray2, int n2) {
        System.arraycopy(sArray, 0, sArray2, 0, n2);
    }

    public static final short b(d d2) {
        int n2;
        d d3 = d2.a;
        ae.b();
        int n3 = ae.a(Thread.currentThread());
        b[n3] = n2 = b[n3] - 1;
        return d3.b;
    }

    public static final void a(int n2, g g2) {
        ae.a(n2, g2.a);
    }

    public static final d a(d d2) {
        return d2.a[0];
    }

    public static x a(short[] sArray) {
        return new x(sArray);
    }

    public static final void a(boolean bl, g g2) {
        g2.c = true;
    }

    public static final void a(g g2) {
        ae.a(0, g2.a);
    }

    public static short[] a(x x2) {
        return x2.a;
    }

    public static final int a(int[] nArray) {
        return nArray[1];
    }

    public static final int b(int[] nArray) {
        return nArray[0];
    }

    public static int c(int n2) {
        return n2 << 16;
    }

    public static int[] a() {
        return ae.a.a;
    }

    public static l a() {
        return (l)ae.a.a;
    }

    public static long a() {
        return System.currentTimeMillis();
    }

    static {
        a = new Object();
        a = 50;
        a = true;
        short[] sArray = new short[2];
        short[] sArray2 = sArray;
        sArray[0] = -1;
        sArray2[1] = 0;
        short[] sArray3 = new short[4];
        sArray2 = sArray3;
        sArray3[0] = -1;
        sArray2[1] = -1;
        sArray2[2] = -1;
        sArray2[3] = -1;
        a = (short)Math.max(Math.max(Math.max(2, 8), 8), 1);
        short[] sArray4 = new short[4];
        sArray2 = sArray4;
        sArray4[0] = -1;
        sArray2[1] = -1;
        sArray2[2] = -1;
        sArray2[3] = -1;
        a = new byte[0];
        a = null;
        b = null;
        c = -1;
        d = -1;
        e = -1;
        b = false;
        short[] sArray5 = new short[7];
        sArray2 = sArray5;
        sArray5[0] = -1;
        sArray2[1] = -1;
        sArray2[2] = 3;
        sArray2[3] = -1;
        sArray2[4] = -1;
        sArray2[5] = -1;
        sArray2[6] = -1;
        short[] sArray6 = new short[2];
        sArray2 = sArray6;
        sArray6[0] = -1;
        sArray2[1] = 0;
    }
}

