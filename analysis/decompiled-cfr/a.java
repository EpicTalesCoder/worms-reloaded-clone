/*
 * Decompiled with CFR 0.152.
 */
/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class a
implements j {
    static int a = 2;
    private static int br;
    private static long e;
    private static int[] h;
    static int b;
    private static int bs;
    static int c;
    static int d;
    static int e;
    static boolean a;
    private static boolean l;
    static boolean b;
    static boolean c;
    static boolean d;
    static boolean e;
    static boolean f;
    public static int f;
    public static int g;
    public static int h;
    public static int i;
    public static int j;
    public static boolean g;
    private static int bt;
    static int k;
    static int l;
    private static int bu;
    private static int bv;
    public static int m;
    public static int n;
    private static int bw;
    private static int bx;
    public static int o;
    public static boolean h;
    private static byte[] w;
    private static int by;
    private static int bz;
    private static int bA;
    private static int bB;
    static boolean i;
    private static int bC;
    private static int[][] b;
    private static int[] i;
    private static int[] j;
    private static boolean m;
    private static int bD;
    private static int bE;
    private static int bF;
    private static int bG;
    private static int bH;
    private static int[][] c;
    private static int bI;
    static int[][] a;
    private static int bJ;
    private static int bK;
    private static int bL;
    private static int bM;
    private static int bN;
    private static int bO;
    static int p;
    static int q;
    private static int bP;
    private static int bQ;
    private static int bR;
    private static int bS;
    static int r;
    static int s;
    static ah[] a;
    private static ah[][] a;
    private static int[] k;
    static ah a;
    static byte[] a;
    static int[] a;
    static int t;
    static int u;
    static int v;
    static int w;
    static int x;
    static int y;
    static int z;
    static int A;
    static int B;
    static boolean j;
    static int C;
    static int D;
    static int E;
    static int F;
    private static byte[] x;
    static int G;
    static int H;
    static int I;
    public static long a;
    private static long f;
    private static long g;
    public static long b;
    public static int J;
    public static long c;
    public static long d;
    public static boolean k;
    private static int bT;
    private static int bU;
    private static r[] a;
    private static r[] b;

    static void a() {
        bU = 0;
        bT = 0;
        a = null;
        b = null;
        System.gc();
        b = null;
        f = 0;
        g = 0;
        System.gc();
        a.n();
        y.o = false;
        System.gc();
    }

    static void a(boolean n2, int n3, int n4, int n5) {
        int n6;
        int n7 = 0;
        if (bz != -1) {
            y.g(bz);
        }
        a = new int[4096][2];
        w = null;
        System.gc();
        y.a(x, (byte)0);
        e = s.a();
        br = 0;
        v = -1;
        w = 0;
        t = -1;
        u = 0;
        x = -1;
        y = 0;
        E = -1;
        F = 0;
        z = 0;
        B = 0;
        d = 0L;
        bI = 0;
        A = 0;
        y.a = false;
        a.e();
        ah.p = 0;
        y.a(a, -1);
        y.o = false;
        ah.f = false;
        ah.d = false;
        ah.g = false;
        y.J = 2;
        y.a(y.r, 0);
        y.a(y.q, 0);
        y.a(y.p, 0);
        a = false;
        l = false;
        b = false;
        c = false;
        d = false;
        f = false;
        e = false;
        if (y.c()) {
            f = true;
        }
        a.j();
        bU = 0;
        bT = 0;
        a = new r[250];
        b = new r[250];
        for (n6 = 0; n6 < 250; ++n6) {
            a.a[n6] = new r();
        }
        for (n6 = 0; n6 < 250; ++n6) {
            a.b[n6] = new r();
        }
        y.F = 0;
        y.bG = 0;
        p = 640;
        G = 0;
        q = 320;
        H = 0;
        I = 0;
        b = q - 32;
        y.bv = 32768;
        y.m = false;
        y.g = false;
        y.c = null;
        y.d = null;
        y.i = false;
        k = false;
        if (y.A == 5) {
            y.g();
        }
        c = 0;
        d = 0;
        e = 0;
        y.a.d(0, 0, y.e, y.f);
        if (n2 != 0) {
            n2 = n5;
            n6 = n4;
            if (n6 == -1) {
                n6 = y.c(4);
            }
            if (n2 == -1) {
                bJ = y.a.nextInt();
                n2 = a.a();
            }
            G = 0;
            n3 = n6;
            n4 = n2 & 0xFFFF;
            bL = n4 % 101;
            bM = n4 / 101 % 101;
            bJ = n2;
            bK = n3;
            bL = (bL << 16) / 50 + 32768;
            bM = (bM << 16) / 50 + 32768;
            bN = 65536 + (a.a() & 0xFFFF);
            bO = 65536 + (a.a() & 0xFFFF) / 2;
            n2 = bK;
            switch (n2) {
                case 0: {
                    bH = 4;
                    a.c[0][0] = 1 * bP / 16 << 16;
                    a.c[0][1] = 16 * bQ / 16 - 1 << 16;
                    a.c[1][0] = 2 * bP / 16 << 16;
                    a.c[1][1] = 6 * bQ / 16 << 16;
                    a.c[2][0] = 14 * bP / 16 << 16;
                    a.c[2][1] = 6 * bQ / 16 << 16;
                    a.c[3][0] = 15 * bP / 16 << 16;
                    a.c[3][1] = 16 * bQ / 16 - 1 << 16;
                    break;
                }
                case 3: {
                    bH = 8;
                    a.c[0][0] = 1 * bP / 16 << 16;
                    a.c[0][1] = 16 * bQ / 16 - 1 << 16;
                    a.c[1][0] = 2 * bP / 16 << 16;
                    a.c[1][1] = 6 * bQ / 16 << 16;
                    a.c[2][0] = 5 * bP / 16 << 16;
                    a.c[2][1] = 6 * bQ / 16 << 16;
                    a.c[3][0] = 6 * bP / 16 << 16;
                    a.c[3][1] = 16 * bQ / 16 << 16;
                    a.c[4][0] = 10 * bP / 16 << 16;
                    a.c[4][1] = 16 * bQ / 16 << 16;
                    a.c[5][0] = 11 * bP / 16 << 16;
                    a.c[5][1] = 6 * bQ / 16 << 16;
                    a.c[6][0] = 14 * bP / 16 << 16;
                    a.c[6][1] = 6 * bQ / 16 << 16;
                    a.c[7][0] = 15 * bP / 16 << 16;
                    a.c[7][1] = 16 * bQ / 16 - 1 << 16;
                    break;
                }
                case 1: {
                    bH = 4;
                    a.c[0][0] = 1 * bP / 16 << 16;
                    a.c[0][1] = 16 * bQ / 16 - 1 << 16;
                    a.c[1][0] = 2 * bP / 16 << 16;
                    a.c[1][1] = 12 * bQ / 16 << 16;
                    a.c[2][0] = 14 * bP / 16 << 16;
                    a.c[2][1] = 12 * bQ / 16 << 16;
                    a.c[3][0] = 15 * bP / 16 << 16;
                    a.c[3][1] = 16 * bQ / 16 - 1 << 16;
                    break;
                }
                case 2: {
                    bH = 5;
                    a.c[0][0] = 1 * bP / 16 << 16;
                    a.c[0][1] = 16 * bQ / 16 - 1 << 16;
                    a.c[1][0] = 2 * bP / 16 << 16;
                    a.c[1][1] = 4 * bQ / 16 << 16;
                    a.c[2][0] = 8 * bP / 16 << 16;
                    a.c[2][1] = 16 * bQ / 16 - 1 << 16;
                    a.c[3][0] = 14 * bP / 16 << 16;
                    a.c[3][1] = 4 * bQ / 16 - 1 << 16;
                    a.c[4][0] = 15 * bP / 16 << 16;
                    a.c[4][1] = 16 * bQ / 16 - 1 << 16;
                    break;
                }
                case 5: {
                    bH = 16;
                    a.c[0][0] = 0;
                    a.c[0][1] = 16 * bQ / 16 << 16;
                    a.c[1][0] = 1 * bP / 16 << 16;
                    a.c[1][1] = 15 * bQ / 16 << 16;
                    a.c[2][0] = 2 * bP / 16 << 16;
                    a.c[2][1] = 14 * bQ / 16 << 16;
                    a.c[3][0] = 3 * bP / 16 << 16;
                    a.c[3][1] = 13 * bQ / 16 << 16;
                    a.c[4][0] = 4 * bP / 16 << 16;
                    a.c[4][1] = 13 * bQ / 16 << 16;
                    a.c[5][0] = 5 * bP / 16 << 16;
                    a.c[5][1] = 14 * bQ / 16 << 16;
                    a.c[6][0] = 6 * bP / 16 << 16;
                    a.c[6][1] = 15 * bQ / 16 << 16;
                    a.c[7][0] = 13 * bP / 32 << 16;
                    a.c[7][1] = 16 * bQ / 16 << 16;
                    a.c[8][0] = 19 * bP / 32 << 16;
                    a.c[8][1] = 16 * bQ / 16 << 16;
                    a.c[9][0] = 10 * bP / 16 << 16;
                    a.c[9][1] = 15 * bQ / 16 << 16;
                    a.c[10][0] = 11 * bP / 16 << 16;
                    a.c[10][1] = 14 * bQ / 16 << 16;
                    a.c[11][0] = 12 * bP / 16 << 16;
                    a.c[11][1] = 13 * bQ / 16 << 16;
                    a.c[12][0] = 13 * bP / 16 << 16;
                    a.c[12][1] = 13 * bQ / 16 << 16;
                    a.c[13][0] = 14 * bP / 16 << 16;
                    a.c[13][1] = 14 * bQ / 16 << 16;
                    a.c[14][0] = 15 * bP / 16 << 16;
                    a.c[14][1] = 15 * bQ / 16 << 16;
                    a.c[15][0] = 16 * bP / 16 << 16;
                    a.c[15][1] = 16 * bQ / 16 << 16;
                }
            }
            if (n2 == 1) {
                a.b(n2, j.bn);
            } else {
                a.b(n2, 64);
            }
        } else {
            if (n3 >= 90 && n3 <= 93) {
                a.h(n3);
            } else {
                y.A = 0;
                n6 = n3;
                n2 = 86;
                for (n3 = 0; n3 < n6; ++n3) {
                    n2 += 2;
                }
                a.h[n6] = 0;
                n7 = n2;
                a.h(n7);
            }
            y.a(x, (byte)0);
            a.B();
        }
        bs = n7;
    }

    private static void x() {
        int n2;
        int n3;
        j = true;
        for (n3 = 0; n3 < 36; ++n3) {
            y.a[0][n3] = 0;
        }
        if (y.z == 0 || y.z == 2) {
            for (n3 = 0; n3 < 3; ++n3) {
                if (y.A == 1 && y.z == 2) {
                    for (n2 = 0; n2 < 36; ++n2) {
                        y.a[n3][n2] = 0;
                    }
                    y.a[n3][0] = -1;
                    y.a[n3][1] = -1;
                    y.a[n3][34] = -1;
                    y.a[n3][35] = -1;
                    continue;
                }
                if (y.A == 4 && y.z == 2) {
                    for (n2 = 0; n2 < 36; ++n2) {
                        y.a[n3][n2] = 0;
                    }
                    y.a[n3][0] = -1;
                    y.a[n3][1] = -1;
                    y.a[n3][3] = -1;
                    y.a[n3][31] = 3;
                    y.a[n3][7] = 3;
                    y.a[n3][8] = 3;
                    y.a[n3][12] = 3;
                    y.a[n3][14] = 3;
                    y.a[n3][17] = 1;
                    y.a[n3][21] = 3;
                    y.a[n3][30] = 1;
                    y.a[n3][22] = 1;
                    y.a[n3][34] = -1;
                    y.a[n3][35] = -1;
                    continue;
                }
                if (y.A == 2 && y.z == 2) {
                    for (n2 = 0; n2 < 36; ++n2) {
                        y.a[n3][n2] = 0;
                    }
                    y.a[n3][0] = -1;
                    y.a[n3][2] = -1;
                    y.a[n3][8] = -1;
                    y.a[n3][34] = -1;
                    y.a[n3][35] = -1;
                } else {
                    System.arraycopy(y.k, 0, y.a[n3], 0, 36);
                    for (n2 = 0; n2 < 36; ++n2) {
                        int[] nArray = y.a[n3];
                        int n4 = n2;
                        nArray[n4] = nArray[n4] + y.l[n2];
                    }
                }
                if (y.z != 3 && y.A != 1) {
                    for (n2 = 0; n2 < 36; ++n2) {
                        int[] nArray = y.a[n3];
                        int n5 = n2;
                        nArray[n5] = nArray[n5] + y.m[n2];
                    }
                }
                a.a(y.a[n3]);
            }
        } else if (y.z == 3) {
            int n6;
            int n7;
            for (n2 = 0; n2 < 9; ++n2) {
                y.a[0][j.t[n2]] = j.u[n2];
            }
            n2 = y.b(17, 20);
            n3 = j.t.length - 9;
            byte[] byArray = new byte[n3];
            byte[] byArray2 = new byte[n3];
            System.arraycopy(j.t, 9, byArray, 0, n3);
            System.arraycopy(j.u, 9, byArray2, 0, n3);
            n3 = j.g.length;
            for (n7 = 0; n7 < j.g.length - 1; ++n7) {
                int n8 = y.a.nextInt(n3);
                n6 = byArray[n7];
                byte by = byArray2[n7];
                byArray[n7] = byArray[n7 + n8];
                byArray2[n7] = byArray2[n7 + n8];
                byArray[n7 + n8] = n6;
                byArray2[n7 + n8] = by;
                --n3;
            }
            n7 = 9;
            n6 = 0;
            while (n7 < n2) {
                y.a[0][byArray[n6]] = byArray2[n6];
                ++n7;
                ++n6;
            }
        } else if (y.z == 1) {
            if (y.D == 6 || y.D == 20) {
                return;
            }
            if (y.D == 13 || y.D == 27) {
                y.a[0][0] = -1;
                y.a[0][1] = -1;
                y.a[0][12] = 2;
                y.a[0][31] = 2;
                y.a[0][3] = -1;
                y.a[0][34] = -1;
                y.a[0][35] = -1;
                y.a[0][13] = 2;
                y.a[0][25] = 2;
                y.a[0][2] = 2;
                y.a[0][19] = 2;
                return;
            }
            if (!y.f) {
                System.arraycopy(y.k, 0, y.a[0], 0, 36);
                for (n3 = 0; n3 < 36; ++n3) {
                    int[] nArray = y.a[0];
                    int n9 = n3;
                    nArray[n9] = nArray[n9] + y.l[n3];
                    int[] nArray2 = y.a[0];
                    int n10 = n3;
                    nArray2[n10] = nArray2[n10] + y.m[n3];
                }
            } else {
                for (n3 = 0; n3 < 36; ++n3) {
                    y.a[0][n3] = -1;
                }
            }
            if (y.c()) {
                j = false;
            }
            a.a(y.a[0]);
        }
        for (n3 = 0; n3 < 3; ++n3) {
            y.a[n3][34] = -1;
            y.a[n3][35] = -1;
        }
    }

    private static void a(int[] nArray) {
        if (y.A == 6) {
            for (int i2 = 0; i2 < j.c.length; ++i2) {
                nArray[j.c[i2]] = 0;
            }
            return;
        }
        if (y.A == 3) {
            for (int i3 = 0; i3 < 3; ++i3) {
                y.a[i3][18] = 0;
            }
        }
    }

    static void b() {
        long l2 = s.a();
        br = (int)((long)br + (l2 - e));
        e = l2;
        if (y.A == 5) {
            y.f();
        }
        try {
            if ((y.b() != 7 || y.p != 11 && y.p != 4 && y.p != 7) && (h == 2 || h == 0)) {
                if (s.a(2052)) {
                    if (bw > 0) {
                        bw = 0;
                    }
                    if ((bw -= 13) < -26) {
                        bw = -26;
                    }
                } else if (s.a(8200)) {
                    if (bw < 0) {
                        bw = 0;
                    }
                    if ((bw += 13) > 26) {
                        bw = 26;
                    }
                } else {
                    bw = 0;
                }
                if (s.a(513)) {
                    if (bx > 0) {
                        bx = 0;
                    }
                    if ((bx -= 13) < -26) {
                        bx = -26;
                    }
                } else if (s.a(32770)) {
                    if (bx < 0) {
                        bx = 0;
                    }
                    if ((bx += 13) > 26) {
                        bx = 26;
                    }
                } else {
                    bx = 0;
                }
                if (bw > 0) {
                    if ((m += bw) > p - 15) {
                        m = p - 15;
                    }
                } else if (bw < 0 && (m += bw) < 15) {
                    m = 15;
                }
                if (bx > 0) {
                    if ((n += bx) > b - 10) {
                        n = b - 10;
                    }
                } else if (bx < 0) {
                    n += bx;
                    int n2 = -55;
                    if (y.b() == 7 && (y.a.a[15] == 14 || y.a.a[15] == 28 || y.a.a[15] == 17 || y.a.a[15] == 6 || y.a.a[15] == 17 || y.a.a[15] == 5 || y.a.a[15] == 11) || y.b() == 18) {
                        n2 = 25;
                    }
                    if (n < n2) {
                        n = n2;
                    }
                }
            }
            a.f();
            a.h();
            a.g();
            a.l();
            block21: for (int i2 = 0; i2 < bU; ++i2) {
                r r2 = b[i2];
                if (!r2.a) continue;
                if (r2.a[0] < r2.a[1]) {
                    r2.a[0] = (short)(r2.a[0] + 1);
                    continue;
                }
                r2.a = false;
                switch (r2.a) {
                    case 80: {
                        ah ah2 = a.e(r2.a[2]);
                        if (ah2 == null) continue block21;
                        a.i(ah2.b);
                        continue block21;
                    }
                    case 81: {
                        if (y.a != null && y.a.a[12] == 1) {
                            y.a.b((byte)0);
                        }
                        y.b(r2.a[2], -1, (int)r2.a[1]);
                        if (r2.a[2] <= 0) continue block21;
                        y.s = true;
                        continue block21;
                    }
                    case 82: {
                        for (int i3 = 2; i3 <= 8; ++i3) {
                            if (r2.a[i3] == -1) continue;
                            a.a((int)r2.a[i3]).a = true;
                        }
                        continue block21;
                    }
                    case 83: {
                        for (int i4 = 2; i4 <= 7; ++i4) {
                            if (r2.a[i4] == -1) continue;
                            a.a((int)r2.a[i4]).a = false;
                        }
                        continue block21;
                    }
                    case 84: {
                        ah ah2 = a.e(r2.a[2]);
                        if (ah2 == null) continue block21;
                        if (ah.c != null) {
                            a.e(ah.c.b);
                            ah.c = null;
                        }
                        ah ah3 = a.b(5);
                        ah3.a(ah2.e, ah2.f);
                        ah3.a(3);
                        ah3.a[0] = r2.a[0];
                        ah3.a[1] = r2.a[1];
                        if (ah3.a[1] != -1 && ah2.a[5] == 1) {
                            ah3.b((int)ah3.a[1]);
                        } else {
                            ah3.b((int)ah3.a[0]);
                        }
                        ah3.d &= 0xFFFFFFBF;
                        ah.c = ah3;
                        continue block21;
                    }
                    case 85: {
                        if (a.e(r2.a[2]) == null || ah.c == null) continue block21;
                        a.e(ah.c.b);
                        ah.c = null;
                        continue block21;
                    }
                    case 86: {
                        a = r2.a[0] > 0;
                        l = r2.a[1] > 0;
                        b = r2.a[2] > 0;
                        c = r2.a[3] > 0;
                        d = r2.a[4] > 0;
                        boolean bl = e = r2.a[5] > 0;
                        if (c) continue block21;
                        j = true;
                        C = y.a();
                        continue block21;
                    }
                    case 87: {
                        ah ah2 = a.e(r2.a[4]);
                        if (ah2 == null) continue block21;
                        int n3 = r2.a[2];
                        int n4 = r2.a[3];
                        a.i(ah2.b);
                        ah2.q = 0;
                        ah2.a = 0;
                        ah2.e(-1);
                        ah2.a[5] = 32768;
                        ah2.h = ah2.a[11];
                        ah2.b(7);
                        ah2.c();
                        ah2.a[18] = 0;
                        ah.o = 0;
                        ah2.l = false;
                        ah2.b((byte)16);
                        ah2.a[10] = 0;
                        ah2.b(48);
                        ah2.d |= 0x40;
                        n4 = ah2.a(n3, n4, false);
                        ah2.a(n3 << 16, n4 << 16);
                        if (!a) continue block21;
                        y.br = 0;
                        continue block21;
                    }
                    case 88: {
                        ah ah2 = a.e(r2.a[2]);
                        if (ah2 == null) continue block21;
                        ah2.a[15] = r2.a[0];
                        continue block21;
                    }
                    case 89: {
                        ah ah2 = a.e(r2.a[2]);
                        if (ah2 == null) continue block21;
                        if (r2.a[0] == -1) {
                            y.a(y.a[ah2.a[3]], 0);
                            ah2.a[15] = -1;
                            ah2.b((byte)1);
                            continue block21;
                        }
                        y.a[ah2.a[3]][r2.a[0]] = 0;
                        if (y.a == null || y.a != ah2 || y.a.a[15] != r2.a[0]) continue block21;
                        y.a.a[15] = -1;
                        ah2.b((byte)1);
                        continue block21;
                    }
                    case 90: {
                        int n3 = r2.a[2] - (y.a >> 1);
                        int n4 = r2.a[3] - (y.b >> 1);
                        a.a(n3, n4);
                        y.c(11);
                        y.bD = r2.a[4];
                        continue block21;
                    }
                    case 91: {
                        ah ah2 = a.e(r2.a[2]);
                        if (ah2 == null) continue block21;
                        ah2.d |= 0x18;
                        if (ah2.a != 19) continue block21;
                        a.a(ah2);
                        continue block21;
                    }
                    case 92: {
                        y.bt = 0;
                        y.c(12);
                        continue block21;
                    }
                    case 95: {
                        while (y.bF >= 0) {
                            y.k(1);
                        }
                        y.d = null;
                        continue block21;
                    }
                    case 93: {
                        ah ah2 = a.e(r2.a[2]);
                        if (ah2 == null) continue block21;
                        y.a[ah2.a[3]][r2.a[0]] = r2.a[1];
                        continue block21;
                    }
                    case 94: {
                        ah ah2 = a.e(r2.a[4]);
                        if (ah2 == null) continue block21;
                        int n3 = r2.a[2];
                        int n4 = r2.a[3];
                        if (ah2.a == 1 && (ah2.d & 0x18) == 0) {
                            if (r2.a[0] == 1) {
                                ah2.d(n3 << 16, n4 << 16);
                                continue block21;
                            }
                            ah2.c(n3 << 16, n4 << 16);
                            continue block21;
                        }
                        if (ah2.a == 19 && (ah2.d & 0x18) == 0) {
                            int n5 = ah2.q;
                            ah2.a(0);
                            ah2.r = -1;
                            ah2.b((int)ah2.a[1]);
                            ah2.a[0] = n3 << 16;
                            ah2.a[1] = n4 << 16;
                            ah2.a[0] = 1;
                            ah2.a(n5);
                            continue block21;
                        }
                        ah2.e = n3 << 16;
                        ah2.f = n4 << 16;
                        continue block21;
                    }
                    case 96: {
                        y.i = r2.a[0] == 0;
                    }
                }
            }
            if (y.r != y.s) {
                if (y.t == 2) {
                    if (y.w < 0) {
                        if ((y.w += y.x) > 0) {
                            y.w = y.x;
                        }
                    } else {
                        y.e(3);
                    }
                }
            } else if (y.u < y.v) {
                ++y.u;
            } else {
                y.e(1);
            }
            if (y.t == 1) {
                if (y.w > y.y) {
                    if ((y.w -= y.x) <= y.y) {
                        y.w = y.y;
                        return;
                    }
                } else {
                    y.e(0);
                }
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    static void a(n n2) {
        try {
            n2.d(0, 0, j.K, j.L);
            a.d();
            a.a(n2, true);
            if (y.z == 1 && !y.f && y.p != 12) {
                y.b.a();
                p.a(y.d);
                int n3 = p.b();
                n3 = 32 - n3 >> 1;
                if (y.t == 2) {
                    y.a[5].a(60, 50, y.w, 0);
                    y.b.a(y.q + y.s);
                    y.a(y.b, 50, y.w + n3, 24, y.d);
                }
                if (y.t == 3) {
                    y.a[5].a(60, 50, 0, 0);
                    if (y.s < y.r) {
                        y.s += 2;
                    }
                    if (y.s > y.r) {
                        y.s = y.r;
                    }
                    y.b.a(y.q + y.s);
                    y.a(y.b, 50, n3, 24, y.d);
                }
                if (y.t == 1) {
                    y.a[5].a(60, 50, y.w, 0);
                    y.b.a(y.q + y.s);
                    y.a(y.b, 50, y.w + n3, 24, y.d);
                }
                if (y.t == 0) {
                    y.a[5].a(60, 50, y.y, 0);
                    y.b.a(y.q + y.s);
                    y.a(y.b, 50, y.y + n3, 24, y.d);
                }
            }
            if (y.z == 3 && y.p != 17 && y.o != 20) {
                y.i();
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private static void y() {
        a = 0;
        for (int i2 = 0; i2 < 3; ++i2) {
            if (y.s[i2] == 0) continue;
            ++a;
        }
    }

    private static void z() {
        int n2;
        if (a) {
            return;
        }
        y.a(y.s, 0);
        for (n2 = t; n2 < t + u; ++n2) {
            if (a.a[n2].a != 1 || a[n2].b()) continue;
            if (y.A == 1 && y.z == 2) {
                a.a[n2].h = 200;
            }
            byte by = a.a[n2].a[3];
            y.s[by] = y.s[by] + a.a[n2].h;
        }
        y.bw = y.s[0];
        for (n2 = 1; n2 < y.s.length; ++n2) {
            if (y.bw >= y.s[n2]) continue;
            y.bw = y.s[n2];
        }
    }

    static void c() {
        int n2;
        if (a) {
            return;
        }
        System.arraycopy(y.s, 0, y.t, 0, 3);
        y.a(y.s, 0);
        for (n2 = t; n2 < t + u; ++n2) {
            if (a.a[n2].a != 1 || a[n2].b()) continue;
            byte by = a.a[n2].a[3];
            y.s[by] = y.s[by] + a.a[n2].h;
        }
        y.g = true;
        for (n2 = 0; n2 < 3; ++n2) {
            y.u[n2] = (y.t[n2] - y.s[n2]) / 20;
            y.u[n2] = y.u[n2] + ((y.u[n2] > 0 ? 1 : -1) << 1);
        }
        y.b = s.a();
    }

    static void d() {
        y.a(k, 0);
        for (int i2 = 0; i2 < D; ++i2) {
            if (a[i2] != 1) continue;
            ah ah2 = a[i2];
            int n2 = ah2.g;
            if (!y.a(f - 100, g - 80, y.a + 100 + 100, y.b + 80 + 80, (ah2.e >> 16) + ah2.w[0], (ah2.f >> 16) + ah2.w[1], ah2.w[2] - ah2.w[0], ah2.w[3] - ah2.w[1]) && ah2 != y.a) continue;
            a.a[n2][a.k[n2]] = a[i2];
            int n3 = n2;
            k[n3] = k[n3] + 1;
        }
    }

    private static void A() {
        int n2;
        if (y.z == 0 || y.z == 2 && y.B == 0) {
            if (y.z == 0) {
                y.h[0] = y.C[0];
                y.i[0] = y.D[0];
                y.j[0] = y.E[0];
                y.h[1] = y.C[y.a + 1];
                y.i[1] = y.D[y.a + 1];
                y.j[1] = y.E[y.a + 1];
            } else {
                y.h[1] = y.h[0];
                y.h[2] = y.h[0];
                while (y.h[0] == y.h[1] || y.h[1] == y.h[2] || y.h[0] == y.h[2]) {
                    n2 = y.b(0, 4);
                    int n3 = y.b(0, 4);
                    y.h[1] = y.C[n2];
                    y.i[1] = y.D[n2];
                    y.j[1] = y.E[n2];
                    y.h[2] = y.C[n3];
                    y.i[2] = y.D[n3];
                    y.j[2] = y.E[n3];
                }
            }
        } else if (y.z == 1) {
            y.h[0] = y.C[0];
            y.i[0] = y.D[0];
            y.j[0] = y.E[0];
            if (y.D != -1 && !y.c()) {
                y.h[1] = y.C[y.a[y.D][3] + 1];
                y.i[1] = y.D[y.a[y.D][3] + 1];
                y.j[1] = y.E[y.a[y.D][3] + 1];
                y.h[2] = y.C[y.a[y.D][7] + 1];
                y.i[2] = y.D[y.a[y.D][7] + 1];
                y.j[2] = y.E[y.a[y.D][7] + 1];
            }
        }
        for (n2 = 0; n2 < D; ++n2) {
            if (a[n2] != 1 || a.a[n2].a != 1) continue;
            a[n2].d(y.h[a.a[n2].a[3]]);
        }
    }

    public static void a(boolean n2, int n3) {
        int n4;
        int n5;
        n3 = 0;
        int n6 = y.c && y.D % 7 == 6 ? 1 : 0;
        i = true;
        if (n2 == 0) {
            n3 = bs;
        }
        if (y.A == 6) {
            int n7 = 17 + y.g;
            if (y.a[n7] == null) {
                try {
                    y.f(n7);
                }
                catch (Exception exception) {}
            }
            if (y.a[n7 = 17 + y.h] == null) {
                try {
                    y.f(n7);
                }
                catch (Exception exception) {}
            }
        }
        a = a.b(7);
        if (y.A == 6) {
            ah ah2 = a.b(34);
            n5 = G + 100;
            n4 = G + 220;
            n5 = n5 + n4 >> 1;
            n4 = H;
            ah2.e = n5 << 16;
            ah2.f = n4 << 16;
            ah2.q = 17 + y.g;
            ah2.b(0);
            ah2.d &= 0xFFFFFFBF;
            ah2 = a.b(34);
            n5 = G + 420;
            n4 = G + 540;
            n5 = n5 + n4 >> 1;
            n4 = H;
            ah2.e = n5 << 16;
            ah2.f = n4 << 16;
            ah2.q = 17 + y.h;
            ah2.b(0);
            ah2.d &= 0xFFFFFFBF;
        }
        if (n2 != 0 || n6 != 0) {
            int[] nArray = y.a;
            t = -1;
            n3 = 0;
            for (n6 = 0; n6 < nArray.length; ++n6) {
                for (n2 = 0; n2 < nArray[n6]; ++n2) {
                    int n8;
                    n4 = n3;
                    n5 = n6;
                    int n9 = 0;
                    int n10 = 0;
                    ah ah3 = a.b(1);
                    boolean bl = false;
                    int n11 = n8 = y.c && y.D % 7 == 6 ? 1 : 0;
                    if (y.A == 6) {
                        if (n5 == 0) {
                            n5 = G + 20;
                            n8 = G + 220;
                        } else {
                            n5 = G + 340;
                            n8 = G + 640;
                        }
                    } else if (n8 != 0) {
                        if (n5 == 0) {
                            n5 = G + 40;
                            n8 = G + 1080;
                        } else {
                            n5 = G + 40;
                            n8 = G + 1080;
                        }
                    } else {
                        n5 = 196;
                        n8 = 736;
                    }
                    block6: while (!bl) {
                        n9 = a.a(n5, n8);
                        n10 = a.a(96, 416);
                        ah3.e = n9 << 16;
                        ah3.f = n10 << 16;
                        if (ah3.b(n9 << 16, n10 << 16)) {
                            if ((n10 = ah3.a(n9, n10, true)) > 24 && n10 < b) {
                                bl = true;
                            }
                        } else if ((n10 = ah3.a(n9, n10, false)) < b) {
                            bl = true;
                        }
                        if (!bl || t == -1) continue;
                        for (int i2 = t; i2 < t + n4; ++i2) {
                            if ((Math.abs((a.a[i2].e >> 16) - n9) > Math.abs((a.a[i2].f >> 16) - n10) ? Math.abs((a.a[i2].e >> 16) - n9) : Math.abs((a.a[i2].f >> 16) - n10)) >= 30) continue;
                            bl = false;
                            continue block6;
                        }
                    }
                    ah3.a(n9 << 16, n10 << 16);
                    if (t == -1) {
                        t = ah3.b;
                    }
                    ah ah4 = ah3;
                    ++n3;
                    ah4.f(n6);
                    if (!y.c()) {
                        ah4.d |= 0x10;
                    }
                    if (y.z == 1) {
                        if (y.A == 3) {
                            ah4.h = 75;
                        } else if (y.D == 6 || y.D == 20) {
                            ah4.h = 250;
                        } else if (ah4.a[3] == 0) {
                            ah4.h = 100;
                        } else if (ah4.a[3] == 1) {
                            ah4.h = y.a[y.D][5];
                        } else if (ah4.a[3] == 2) {
                            ah4.h = y.a[y.D][9];
                        }
                    }
                    int n12 = n4 = y.c && y.D % 7 == 6 ? 1 : 0;
                    if (y.A == 6 || n4 != 0) {
                        ah4.e(n6 == 0 ? 1 : -1);
                    }
                    int n13 = n6;
                    y.q[n13] = y.q[n13] + 1;
                }
            }
            u = n3;
            if (y.z != 3 && y.A != 6) {
                a.c(3, 12);
                a.c(3, 13);
            }
            if (y.z == 1) {
                String cfr_ignored_0 = "total coins required:" + y.a[y.D][14];
                a.c(y.a[y.D][14], 31);
            }
        } else {
            a.g(n3 + 1);
            a.a((short)62, null, false);
        }
        b.a();
        System.gc();
        a.e();
        a.z();
        a.x();
        a.y();
        a.A();
        ah.j = true;
        if (y.a != null) {
            n5 = t;
            for (int i3 = 0; i3 < y.a[0]; ++i3) {
                ah.h[i3] = n5++;
            }
        }
        for (int i4 = 0; i4 < j.e.length; ++i4) {
            j.e[i4] = 0;
            j.f[i4] = 0;
        }
    }

    static void a(int n2) {
        int n3;
        if (bz != -1) {
            y.g(bz);
        }
        if (n2 != -1) {
            by = y.i;
        } else {
            n3 = y.c(5);
            by = n3 + 21;
        }
        try {
            int n4;
            y.f(by);
            switch (by) {
                case 21: {
                    n4 = 26;
                    break;
                }
                case 22: {
                    n4 = 27;
                    break;
                }
                case 23: {
                    n4 = 28;
                    break;
                }
                case 24: {
                    n4 = 29;
                    break;
                }
                case 25: {
                    n4 = 30;
                    break;
                }
                default: {
                    n4 = 21;
                }
            }
            bz = n4;
            y.f(n4);
        }
        catch (Exception exception) {}
        G = 96;
        H = 96;
        I = 32;
        bC = 2 * G / 32;
        bA = 20 + bC;
        bB = 10 + (H + I) / 32;
        p = 640 + 2 * G;
        q = 320 + H + I;
        b = q - 32;
        if (w == null) {
            w = new byte[bA * bA];
        }
        y.a(w, (byte)32);
        for (int i2 = 0; i2 < 200; ++i2) {
            int n5;
            n3 = 0;
            boolean bl = false;
            int n6 = -1;
            int n7 = i2 % 20;
            int n8 = i2 / 20;
            for (n5 = 0; n5 < 32; ++n5) {
                for (int i3 = 0; i3 < 8; ++i3) {
                    n2 = n8 * 2560 + n5 * 80 + (n7 << 2) + i3;
                    if (n2 >= x.length) {
                        n3 = 1;
                        continue;
                    }
                    n2 = x[n2];
                    if (n2 == -1) {
                        bl = true;
                        continue;
                    }
                    if (n2 == 0) {
                        n3 = 1;
                        continue;
                    }
                    n6 = 16;
                    break;
                }
                if (n6 == 16) break;
                n6 = n3 != 0 && bl ? 16 : (n3 != 0 ? 32 : 8);
            }
            n5 = i2 + bC / 2 + i2 / 20 * bC + H / 32 * bA;
            a.w[n5] = n6;
        }
    }

    public static void e() {
        l = 30;
        bt = 8;
        k = 0;
        g = false;
        m = j.K / 2;
        n = j.L / 2;
        f = 0;
        g = 0;
    }

    public static void a(int n2, int n3) {
        i = n2 < 0 ? 0 : n2;
        j = n3 < 0 ? 0 : n3;
        bu = m - f;
        bv = n - g;
        bt = 8;
        a.c(1);
    }

    public static void b(int n2) {
        o = n2;
        bt = 8;
        a.c(3);
    }

    static void c(int n2) {
        if (h == n2) {
            return;
        }
        switch (h) {
            case 3: {
                o = -1;
                break;
            }
            case 2: {
                m = f + j.K / 2;
                n = g + j.L / 2;
                break;
            }
            case 0: {
                ah.d = false;
                break;
            }
            case 4: {
                h = false;
            }
        }
        h = n2;
    }

    /*
     * Unable to fully structure code
     */
    static void f() {
        switch (a.h) {
            case 0: {
                if (a.f > a.m - 80 && (a.f = a.m - 80) < 0) {
                    a.f = 0;
                }
                if (a.f < a.m + 80 - y.a && (a.f = a.m + 80 - y.a) > a.p - y.a) {
                    a.f = a.p - y.a;
                }
                if (a.g > a.n - 80 && (a.g = a.n - 80) < 0) {
                    a.g = 0;
                }
                if (a.g + y.b < a.n + 80 && (a.g = a.n + 80 - y.b) > a.q - y.b) {
                    a.g = a.q - y.b;
                }
                return;
            }
            case 2: {
                a.f = a.m - (y.a >> 1);
                a.g = a.n - (y.b >> 1);
                if (a.f < 0) {
                    a.f = 0;
                }
                if (a.f > a.p - y.a) {
                    a.f = a.p - y.a;
                }
                if (a.g < 0) {
                    a.g = 0;
                }
                if (a.g > a.q - y.b) {
                    a.g = a.q - y.b;
                }
                return;
            }
            case 1: {
                var0 = false;
                if ((a.bt += 4) > a.l) {
                    a.bt = a.l;
                }
                if (!a.a()) ** GOTO lbl41
                if (a.f < 0) {
                    a.f = 0;
                    var0 = true;
                }
                if (a.f > a.p - y.a) {
                    a.f = a.p - y.a;
                    var0 = true;
                }
                if (a.g < 0) {
                    a.g = 0;
                    var0 = true;
                }
                if (a.g <= a.q - y.b) ** GOTO lbl50
                a.g = a.q - y.b;
                ** GOTO lbl49
lbl41:
                // 1 sources

                if (a.f < 0) {
                    a.f = 0;
                }
                if (a.f > a.p - y.a) {
                    a.f = a.p - y.a;
                }
                if (a.g < 0) {
                    a.g = 0;
                }
                if (a.g > a.q - y.b) {
                    a.g = a.q - y.b;
                }
lbl49:
                // 4 sources

                var0 = true;
lbl50:
                // 2 sources

                a.m = a.f + a.bu;
                a.n = a.g + a.bv;
                if (var0) {
                    a.c(2);
                }
                return;
            }
            case 3: {
                var0_1 = a.a(a.o);
                if (var0_1 == null) break;
                if ((a.bt += 4) > a.l) {
                    a.bt = a.l;
                }
                var1_2 = 0;
                if (y.b() == 7 && y.d != null && y.c()) {
                    var1_2 = 40;
                }
                a.i = var0_1.a == 1 && var0_1 == y.a && y.j == false && ah.a(var0_1.a[15]) != false ? (var0_1.e >> 16) - (y.a >> 1) + var0_1.a[5] * j.bq : (var0_1.e >> 16) - (y.a >> 1);
                a.j = (var0_1.f >> 16) - (y.b >> 1) - var1_2;
                a.bu = a.m - a.f;
                a.bv = a.n - a.g;
                if (a.i < 0) {
                    a.i = 0;
                }
                if (a.i > a.p - y.a) {
                    a.i = a.p - y.a;
                }
                if (a.j < 0) {
                    a.j = 0;
                }
                if (a.j > a.q - y.b) {
                    a.j = a.q - y.b;
                }
                a.a();
                a.m = a.f + a.bu;
                a.n = a.g + a.bv;
            }
        }
    }

    private static boolean a() {
        int n2 = i - f;
        int n3 = j - g;
        int n4 = Math.abs(n2);
        int n5 = Math.abs(n3);
        int n6 = n4 + n5;
        g = false;
        if (n6 <= 0) {
            f = i;
            g = j;
            g = true;
            return false;
        }
        if (n6 <= bt) {
            f = i;
            g = j;
            g = false;
            return true;
        }
        n6 = n4 > n5 ? n4 : n5;
        n2 = (n2 * bt << 6) / n6;
        n3 = (n3 * bt << 6) / n6;
        if (k != 0 && (n4 = Math.abs(n2) + Math.abs(n3)) > k) {
            n2 = (n2 * k << 6) / n4;
            n3 = (n3 * k << 6) / n4;
        }
        f = (f << 6) + n2 >> 6;
        g = (g << 6) + n3 >> 6;
        return true;
    }

    static void g() {
        if (m) {
            int n2;
            m = false;
            int n3 = j[3] - j[1];
            int n4 = j[2] - j[0];
            int n5 = j[1];
            int n6 = n2 = j[0];
            int n7 = n5;
            n4 = n2 + n4;
            n3 = n5 + n3;
            int n8 = bD << 5;
            int n9 = bE << 5;
            int n10 = bF << 5;
            int n11 = bG << 5;
            int n12 = y.e;
            int n13 = y.f;
            if (n2 < n8) {
                n6 = n8;
            }
            if (n4 > n8 + n12) {
                n4 = n8 + n12;
            }
            if (n4 - n6 > 0) {
                if (n5 < n9) {
                    n7 = n9;
                }
                if (n3 > n9 + n13) {
                    n3 = n9 + n13;
                }
                if (n3 - n7 > 0) {
                    n2 = n6 + n10 - n8;
                    n5 = n2 - n12;
                    if (n5 < 0) {
                        n5 = 0;
                    }
                    if ((n8 = n12 - n2) > (n4 -= n6)) {
                        n8 = n4;
                    }
                    if ((n10 = n4 - n8) > n4) {
                        n10 = n4;
                    }
                    n4 = n6 + n4 - n10;
                    if ((n11 = (n9 = n7 + n11 - n9) - n13) < 0) {
                        n11 = 0;
                    }
                    if ((n12 = n13 - n9) > (n3 -= n7)) {
                        n12 = n3;
                    }
                    if ((n13 = n3 - n12) > n3) {
                        n13 = n3;
                    }
                    n3 = n7 + n3 - n13;
                    if (n8 > 0) {
                        if (n12 > 0) {
                            a.a(n2, n9, n8, n12, n6, n7, false);
                        }
                        if (n13 > 0) {
                            a.a(n2, n11, n8, n13, n6, n3, false);
                        }
                    }
                    if (n10 > 0) {
                        if (n12 > 0) {
                            a.a(n5, n9, n10, n12, n4, n7, false);
                        }
                        if (n13 > 0) {
                            a.a(n5, n11, n10, n13, n4, n3, false);
                        }
                    }
                }
            }
            y.a(j);
        }
    }

    static void h() {
        int n2;
        int n3;
        if (!i) {
            n3 = f - (bD << 5);
            if (n3 < 0) {
                if ((n3 = (31 - n3) / 32) >= y.c) {
                    i = true;
                } else {
                    bD -= n3;
                    if ((bF -= n3) < 0) {
                        n2 = n3 + bF;
                        if (n2 != 0) {
                            a.b(0, n2, bD - bF);
                        }
                        a.b(bF += y.c, n3 - n2, bD);
                    } else {
                        a.b(bF, n3, bD);
                    }
                }
            } else if (n3 > y.e - y.a) {
                if ((n3 = (n3 - (y.e - y.a) + 31) / 32) >= y.c) {
                    i = true;
                } else {
                    n2 = bF + n3 - y.c;
                    if (n2 >= 0) {
                        a.b(bF, y.c - bF, bD + y.c);
                        if (n2 != 0) {
                            a.b(0, n2, bD + y.c + y.c - bF);
                        }
                        bF = n2;
                    } else {
                        a.b(bF, n3, bD + y.c);
                        bF += n3;
                    }
                    bD += n3;
                }
            }
        }
        if (!i) {
            n3 = g - (bE << 5);
            if (n3 < 0) {
                if ((n3 = (31 - n3) / 32) >= y.d) {
                    i = true;
                } else {
                    bE -= n3;
                    if ((bG -= n3) < 0) {
                        n2 = n3 + bG;
                        if (n2 != 0) {
                            a.c(0, n2, bE - bG);
                        }
                        a.c(bG += y.d, n3 - n2, bE);
                    } else {
                        a.c(bG, n3, bE);
                    }
                }
            } else if (n3 > y.f - y.b) {
                if ((n3 = (n3 - (y.f - y.b) + 31) / 32) >= y.d) {
                    i = true;
                } else {
                    n2 = bG + n3 - y.d;
                    if (n2 >= 0) {
                        a.c(bG, y.d - bG, bE + y.d);
                        if (n2 != 0) {
                            a.c(0, n2, bE + y.d + y.d - bG);
                        }
                        bG = n2;
                    } else {
                        a.c(bG, n3, bE + y.d);
                        bG += n3;
                    }
                    bE += n3;
                }
            }
        }
        if (i) {
            i = false;
            bD = f / 32;
            if (bD > bA - y.c) {
                bD = bA - y.c;
            }
            if ((bE = g / 32) > bB - y.d) {
                bE = bB - y.d;
            }
            bF = 0;
            bG = 0;
            a.a(0, 0, y.c, y.d, bD, bE);
        }
    }

    private static void b(int n2, int n3, int n4) {
        if (bG > 0) {
            a.a(n2, 0, n3, bG, n4, bE + y.d - bG);
            a.a(n2, bG, n3, y.d - bG, n4, bE);
            return;
        }
        a.a(n2, 0, n3, y.d, n4, bE);
    }

    private static void c(int n2, int n3, int n4) {
        if (bF > 0) {
            a.a(0, n2, bF, n3, bD + y.c - bF, n4);
            a.a(bF, n2, y.c - bF, n3, bD, n4);
            return;
        }
        a.a(0, n2, y.c, n3, bD, n4);
    }

    private static void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        int n8 = n4 << 5;
        int n9 = n5 << 5;
        y.a.d(n2 <<= 5, n3 <<= 5, n8, n9);
        ac.a(y.a);
        if (n4 == 1) {
            n4 = n3 + n9;
            n5 = n6 + n7 * bA;
            n6 = n3;
            while (n6 < n4) {
                a.d(n5, n2, n6);
                n6 += 32;
                n5 += bA;
            }
            return;
        }
        if (n5 == 1) {
            n4 = n2 + n8;
            n5 = n6 + n7 * bA;
            n6 = n2;
            while (n6 < n4) {
                a.d(n5, n6, n3);
                n6 += 32;
                ++n5;
            }
            return;
        }
        n4 = n2 + n8;
        n5 = n3 + n9;
        n6 += n7 * bA;
        while (n3 < n5) {
            n7 = n6;
            n8 = n2;
            while (n8 < n4) {
                a.d(n7, n8, n3);
                n8 += 32;
                ++n7;
            }
            n3 += 32;
            n6 += bA;
        }
    }

    private static void a(int n2, int n3, int n4, int n5, int n6, int n7, boolean n8) {
        int n9;
        while (n4 * n5 > 20480) {
            if (n4 > n5) {
                a.a(n2, n3, n4 >> 1, n5, n6, n7, n8 != 0);
                int n10 = n2 + (n4 >> 1);
                n6 += n4 >> 1;
                n4 -= n4 >> 1;
                n2 = n10;
                continue;
            }
            a.a(n2, n3, n4, n5 >> 1, n6, n7, n8 != 0);
            int n11 = n3 + (n5 >> 1);
            n7 += n5 >> 1;
            n5 -= n5 >> 1;
            n3 = n11;
        }
        ac.a = 0;
        ac.a(y.a);
        n2 = n6 / 32;
        n3 = n7 / 32;
        n8 = n2 + n3 * bA;
        n2 = (n2 << 5) - n6;
        n3 = (n3 << 5) - n7;
        while (n3 < n5) {
            n9 = n8;
            int n12 = n2;
            while (n12 < n4) {
                a.d(n9, n12, n3);
                n12 += 32;
                ++n9;
            }
            n3 += 32;
            n8 += bA;
        }
        n3 = f;
        n9 = g;
        f = n6;
        g = n7;
        f = n3;
        g = n9;
        ac.a = 0;
    }

    /*
     * Unable to fully structure code
     */
    private static void d(int var0, int var1_1, int var2_2) {
        block24: {
            block23: {
                if (var0 < 0) {
                    return;
                }
                y.a.d(var1_1, var2_2, 32, 32);
                ac.a(y.a);
                var3_3 = var0 / a.bA;
                var4_4 = a.bB - 1;
                var5_5 = var4_4 - 6;
                if (var3_3 >= var4_4 || var3_3 < var5_5) break block23;
                var4_4 = var0 % a.bA % 10 << 5;
                var3_3 = (var3_3 - var5_5) % 6 << 5;
                y.a[a.bz].a(0, var1_1 - var4_4, var2_2 - var3_3, 0);
                break block24;
            }
            if (a.bz == 26) ** GOTO lbl-1000
            if (a.bz == 27) {
                v0 = 0x140000;
            } else if (a.bz == 28) {
                v0 = 14121317;
            } else if (a.bz == 29) {
                v0 = 1055000;
            } else if (a.bz == 30) {
                v0 = 2691901;
            } else lbl-1000:
            // 2 sources

            {
                v0 = 0x11115C;
            }
            y.a.a(v0);
            y.a.c(var1_1, var2_2, 32, 32);
        }
        if (0 < y.a[a.by].b) {
            if ((a.w[var0] & 8) != 0) {
                ac.a = 0;
                y.a[a.by].a(0, var1_1, var2_2, 0);
                return;
            }
            if ((a.w[var0] & 16) != 0) {
                y.a(a.i, 0);
                var3_3 = var0 % a.bA;
                var4_4 = var0 / a.bA;
                if (var3_3 < a.bC / 2 || var3_3 >= 20 + a.bC / 2) {
                    v1 = a.i;
                } else if (var4_4 < a.H / 32 || var4_4 >= 10 + a.H / 32) {
                    v1 = a.i;
                } else {
                    if (a.w[var0] == 16) {
                        if (a.b == null) {
                            a.b = new int[y.a[a.by].b][];
                        }
                        if (a.b[0] == null) {
                            a.b[0] = new int[1024];
                            System.arraycopy(y.a[a.by].b(0, 0, 0), 0, a.b[0], 0, 1024);
                        }
                        System.arraycopy(a.b[0], 0, a.i, 0, 1024);
                        var4_4 = 0;
                        var3_3 = var0 % a.bA << 5;
                        var5_5 = var0 / a.bA << 5;
                        for (var8_6 = 0; var8_6 < 32; ++var8_6) {
                            for (var9_9 = 0; var9_9 < 32; ++var9_9) {
                                var7_8 = a.b(var3_3 + var8_6, var5_5 + var9_9);
                                var6_7 = var8_6 + (var9_9 << 5);
                                if (var7_8 < 1) {
                                    a.i[var6_7] = 0;
                                    continue;
                                }
                                ++var4_4;
                            }
                        }
                        if (var4_4 == 0) {
                            a.w[var0] = 32;
                        }
                    }
                    v1 = a.i;
                }
                y.a.a(v1, 32, var1_1, var2_2, 32, 32);
            }
        }
    }

    private static int[] a(int n2, int n3) {
        int[] nArray = new int[4];
        int n4 = (int)((long)n2 * (long)n2 >> 16);
        int n5 = (int)((long)n2 * (long)n4 >> 16);
        nArray[0] = -n5 + n4 * 3 - n2 * 3 + 65536;
        nArray[1] = n5 * 3 - n4 * 6 + 262144;
        nArray[2] = n5 * -3 + n4 * 3 + n2 * 3 + 65536;
        nArray[3] = n5;
        int[] nArray2 = new int[2];
        for (n4 = 0; n4 < 2; ++n4) {
            nArray2[n4] = ((int)((long)nArray[0] * (long)a[n3][n4] >> 16) + (int)((long)nArray[1] * (long)a[n3 + 1][n4] >> 16) + (int)((long)nArray[2] * (long)a[n3 + 2][n4] >> 16) + (int)((long)nArray[3] * (long)a[n3 + 3][n4] >> 16)) / 6;
        }
        return nArray2;
    }

    static void i() {
        int n2 = 0x600000;
        int n3 = 0x600000;
        B = bI;
        for (int i2 = 0; i2 < bI; ++i2) {
            ah ah2 = a.b(200);
            ah2.c();
            if (i2 == 0) {
                A = ah2.b;
            }
            ah2.a = new short[20];
            ah2.a = new int[20];
            ah2.a = new byte[10];
            ah2.e = a[i2][0] + n2;
            ah2.f = a[i2][1] + n3;
        }
        ah.l();
    }

    private static void B() {
        int n2 = 0;
        int n3 = 0;
        boolean bl = true;
        for (int i2 = 0; i2 < bI - 4; ++i2) {
            for (int i3 = 0; i3 < 65536; i3 += 13107) {
                int[] nArray;
                if (bl) {
                    bl = false;
                    nArray = a.a(i3, i2);
                } else {
                    int n4;
                    int n5;
                    int n6;
                    nArray = a.a(i3, i2);
                    int n7 = nArray[1] >> 8;
                    int n8 = nArray[0] >> 8;
                    n3 >>= 8;
                    if ((n2 >>= 8) > n8) {
                        n6 = n2;
                        n2 = n8;
                        n8 = n6;
                        n6 = n3;
                        n3 = n7;
                        n7 = n6;
                    }
                    if ((n2 = (n5 = n2) + 128 - 1 >> 8) != (n8 = (n4 = n8) + 128 - 1 >> 8)) {
                        n6 = n4 - n5;
                        int n9 = ((n7 <<= 8) - (n3 <<= 8)) / n6;
                        n6 = (n2 << 8) + 128 - n5;
                        n3 += n9 * n6;
                        n6 = (n8 << 8) + 128 - n4;
                        n7 += n9 * n6;
                        n6 = n8 - n2;
                        n9 = (n7 - (n3 += 32768)) / n6;
                        while (n6-- > 0) {
                            int n10 = n2++;
                            n7 = n3 >> 16;
                            n8 = n10;
                            if (n7 < 0) {
                                n7 = 0;
                            }
                            a.a(n8, n7, (byte)(a.b(n8, n7) ^ 1));
                            n3 += n9;
                        }
                    }
                }
                n2 = nArray[0];
                n3 = nArray[1];
            }
        }
    }

    static void d(int n2) {
        for (int i2 = n2 << 5; i2 < n2 + 1 << 5; ++i2) {
            byte by = 0;
            for (int i3 = 0; i3 < bQ; ++i3) {
                byte by2 = (byte)(by ^ a.b(i2, i3));
                by = by2;
                if (by2 == 0) continue;
                byte by3 = by;
                a.a(i2, i3, by3);
            }
        }
    }

    static int a() {
        bJ = bJ * 1664525 + 1013904223;
        return bJ;
    }

    static int a(int n2, int n3) {
        return n2 + Math.abs(a.a()) % (n3 - n2);
    }

    private static void b(int n2, int n3) {
        int n4;
        bI = 0;
        for (n4 = 0; n4 < 3; ++n4) {
            a.a[a.bI][0] = c[0][0];
            a.a[a.bI][1] = c[0][1];
            ++bI;
        }
        int n5 = 0;
        n4 = 1;
        while (n4 < bH) {
            a.a(c[n5][0], c[n5][1], c[n4][0], c[n4][1], true, 1);
            n5 = n4++;
        }
        n5 = 0;
        n4 = 1;
        while (n4 < bH) {
            a.a(c[n5][0], c[n5][1], c[n4][0], c[n4][1], n3, a.a(), n2);
            n5 = n4++;
        }
        for (n4 = 0; n4 < 4; ++n4) {
            a.a[a.bI][0] = c[bH - 1][0];
            a.a[a.bI][1] = c[bH - 1][1];
            ++bI;
        }
        y.a(x, (byte)0);
        a.B();
    }

    static int a(int n2, int n3, int n4, int n5, boolean n6, int n7) {
        if (Math.abs((n4 >>= 8) - (n2 >>= 8)) >= Math.abs((n5 >>= 8) - (n3 >>= 8))) {
            int n8;
            int n9 = 0;
            int n10 = n2;
            int n11 = n4;
            if ((n2 = n2 + 128 - 1 >> 8) == (n4 = n4 + 128 - 1 >> 8)) {
                n8 = 0;
            } else {
                int n12 = n11 - n10;
                int n13 = ((n5 <<= 8) - (n3 <<= 8)) / n12;
                n10 = (n2 << 8) + 128 - n10;
                n3 += n13 * n10;
                n10 = (n4 << 8) + 128 - n11;
                n5 += n13 * n10;
                n12 = n4 - n2;
                n13 = n5 - n3;
                n4 = Math.abs(n12);
                n5 = n12 < 0 ? -1 : 1;
                n3 += 32768;
                n13 /= n4;
                if (n6 != 0) {
                    while (n4-- > 0) {
                        a.a(n2, n3 >> 16, (byte)n7);
                        n2 += n5;
                        n3 += n13;
                    }
                    n8 = 0;
                } else {
                    int n14 = 0;
                    while (n4-- > 0) {
                        n7 = n14;
                        n6 = n3 >> 16;
                        n14 += a.b(n2, n6);
                        if ((n3 += n13) >> 16 != n6) {
                            n14 += a.b(n2, n3 >> 16);
                        }
                        if (n14 != n7) {
                            bR = n2 << 16;
                            bS = n3;
                            if (++n9 == 1) {
                                r = bR;
                                s = bS;
                            }
                        }
                        n2 += n5;
                    }
                    n8 = n14;
                }
            }
            n2 = n8;
        } else {
            int n15;
            int n16 = 0;
            int n17 = n3;
            int n18 = n5;
            if ((n3 = n3 + 128 - 1 >> 8) == (n5 = n5 + 128 - 1 >> 8)) {
                n15 = 0;
            } else {
                int n19 = n18 - n17;
                int n20 = ((n4 <<= 8) - (n2 <<= 8)) / n19;
                n17 = (n3 << 8) + 128 - n17;
                n2 += n20 * n17;
                n17 = (n5 << 8) + 128 - n18;
                n20 = n4 + n20 * n17 - n2;
                n19 = n5 - n3;
                n4 = Math.abs(n19);
                n5 = n19 < 0 ? -1 : 1;
                n2 += 32768;
                n20 /= n4;
                if (n6 != 0) {
                    while (n4-- > 0) {
                        a.a(n2 >> 16, n3, (byte)n7);
                        n3 += n5;
                        n2 += n20;
                    }
                    n15 = 0;
                } else {
                    int n21 = 0;
                    while (n4-- > 0) {
                        n7 = n21;
                        n6 = n2 >> 16;
                        n21 += a.b(n6, n3);
                        if ((n2 += n20) >> 16 != n6) {
                            n21 += a.b(n2 >> 16, n3);
                        }
                        if (n21 != n7) {
                            bR = n2;
                            bS = n3 << 16;
                            if (++n16 == 1) {
                                r = bR;
                                s = bS;
                            }
                        }
                        n3 += n5;
                    }
                    n15 = n21;
                }
            }
            n2 = n15;
        }
        return n2;
    }

    private static void a(int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
        while (true) {
            y.o[0] = n4 - n2;
            y.o[1] = n5 - n3;
            int n9 = y.a(y.o);
            if (n9 >> 16 < n6) break;
            a.a(n2, n3, n4, n5, true, 0);
            int n10 = n2 + n4 >> 1;
            int n11 = n3 + n5 >> 1;
            if (n10 >> 16 >= 16 && n11 >> 16 >= 16 && n10 >> 16 < bP - 16 && n11 >> 16 < bQ - 16) {
                int n12 = y.c(n9 / bP, 65536);
                int n13 = (int)((long)(bL + (int)((long)(bM - bL) * (long)n12 >> 16)) * (long)n9 >> 16);
                n12 = y.o[0];
                n9 = y.o[1];
                if ((n10 -= (int)((long)((int)((long)n9 * (long)(n13 = (int)((long)((n7 & 0xFFFF) - 32768) * (long)n13 >> 16)) >> 16)) * (long)bN >> 16)) >> 16 < 16 || (n11 += (int)((long)((int)((long)n12 * (long)n13 >> 16)) * (long)bO >> 16)) >> 16 < 16 || n10 >> 16 >= bP - 16 || n11 >> 16 >= bQ - 16 || a.a(n2, n3, n10, n11, false, 0) != 0 || a.a(n10, n11, n4, n5, false, 0) != 0) {
                    n10 = n2 + n4 >> 1;
                    n11 = n3 + n5 >> 1;
                }
            }
            a.a(n2, n3, n10, n11, true, 1);
            a.a(n10, n11, n4, n5, true, 1);
            a.a(n2, n3, n10, n11, n6, (int)((long)(n7 * 1664525) + 1013904223L), n8);
            n7 = (int)((long)(n7 * 1164525) + 1013904223L + 2000000000L);
            n3 = n11;
            n2 = n10;
        }
        a.a[a.bI][0] = n2;
        a.a[a.bI][1] = n8 == 1 ? (n3 >> 16 < j.bp ? n3 + (j.bn << 16) : (n3 >> 16 < j.bn && n3 >> 16 >= j.bp ? n3 + (j.bo << 16) : n3)) : n3;
        ++bI;
    }

    static void a(int n2, int n3, int n4) {
        int n5;
        if (l) {
            return;
        }
        byte[] byArray = new byte[4 * (n4 + 1) * (n4 + 1)];
        int n6 = n4;
        byte[] byArray2 = byArray;
        int n7 = 1 - n4;
        int n8 = 1;
        int n9 = n4 * -2;
        int n10 = 0;
        int n11 = n4;
        int n12 = (n4 << 1) + 1;
        byArray2[n6 + (n11 + n4) * n12] = 1;
        byArray2[n6 + 0] = 1;
        byArray2[n6 + n4 + n11 * n12] = 1;
        byArray2[0 + n11 * n12] = 1;
        int n13 = n11 * n12;
        for (n5 = 0; n5 <= n6 + n4; ++n5) {
            byArray2[n5 + n13] = 1;
        }
        while (n10 < n11) {
            if (n7 >= 0) {
                --n11;
                n7 += (n9 += 2);
            }
            n7 += (n8 += 2);
            n13 = (n6 + n11) * n12;
            for (n5 = n4 - ++n10; n5 <= n4 + n10; ++n5) {
                byArray2[n5 + n13] = 1;
            }
            n13 = (n6 - n11) * n12;
            for (n5 = n4 - n10; n5 <= n4 + n10; ++n5) {
                byArray2[n5 + n13] = 1;
            }
            n13 = (n6 + n10) * n12;
            for (n5 = n4 - n11; n5 <= n4 + n11; ++n5) {
                byArray2[n5 + n13] = 1;
            }
            n13 = (n6 - n10) * n12;
            for (n5 = n4 - n11; n5 <= n4 + n11; ++n5) {
                byArray2[n5 + n13] = 1;
            }
        }
        n8 = n4 << 1;
        n7 = n3 - n4;
        n6 = n2 - n4;
        byArray2 = byArray;
        for (n2 = 0; n2 < n8; ++n2) {
            for (n12 = 0; n12 < n8; ++n12) {
                if (byArray2[n2 + n12 * (n8 + 1)] == 0) continue;
                a.a(n6 + n2, n7 + n12, (byte)0);
                n5 = (n6 + n2 - G) * 170 / (p - 2 * G);
                n13 = (n7 + n12 - H) * (64 - y.by) / (q - H - I);
                if (n5 < 0 || n5 >= 170 || n13 < 0 + y.by || n13 >= 64) continue;
                n10 = n13 * 170 + n5;
                y.v[n10] = 0;
            }
        }
        n12 = n6 / 32 << 5;
        n5 = n7 / 32 << 5;
        for (n11 = n12; n11 < n6 + n8; n11 += 32) {
            for (n9 = n5; n9 < n7 + n8; n9 += 32) {
                int n14;
                int n15;
                n2 = n11 / 32 + n9 / 32 * bA;
                if (n2 < 0 || n2 >= bA * (bB - I / 32)) continue;
                if (w[n2] == 8) {
                    a.w[n2] = 16;
                } else if (w[n2] == 32) continue;
                n3 = n9;
                n2 = n11;
                n4 = n2 / 32;
                int n16 = n3 / 32;
                n2 = (n2 + 32 - 1) / 32 + 1;
                n3 = (n3 + 32 - 1) / 32 + 1;
                if (n4 < bD) {
                    n4 = bD;
                }
                if (n2 > bD + y.c) {
                    n2 = bD + y.c;
                }
                if (n2 - n4 <= 0) continue;
                if (n16 < bE) {
                    n16 = bE;
                }
                if (n3 > bE + y.d) {
                    n3 = bE + y.d;
                }
                if (n3 - n16 <= 0) continue;
                int n17 = n4 + bF - bD;
                n10 = n17 - y.c;
                if (n10 < 0) {
                    n10 = 0;
                }
                if ((n12 = y.c - n17) > (n2 -= n4)) {
                    n12 = n2;
                }
                if ((n13 = n2 - n12) > n2) {
                    n13 = n2;
                }
                n2 = n4 + n2 - n13;
                int n18 = n16 + bG - bE;
                int n19 = n18 - y.d;
                if (n19 < 0) {
                    n19 = 0;
                }
                if ((n15 = y.d - n18) > (n3 -= n16)) {
                    n15 = n3;
                }
                if ((n14 = n3 - n15) > n3) {
                    n14 = n3;
                }
                n3 = n16 + n3 - n14;
                if (n12 > 0) {
                    if (n15 > 0) {
                        a.a(n17, n18, n12, n15, n4, n16);
                    }
                    if (n14 > 0) {
                        a.a(n17, n19, n12, n14, n4, n3);
                    }
                }
                if (n13 <= 0) continue;
                if (n15 > 0) {
                    a.a(n10, n18, n13, n15, n2, n16);
                }
                if (n14 <= 0) continue;
                a.a(n10, n19, n13, n14, n2, n3);
            }
        }
        for (int i2 = t; i2 < t + u; ++i2) {
            if (a[i2] != 1 || a.a[i2].a != 1) continue;
            a.a[i2].d |= 0x400;
        }
    }

    static void j() {
        if (a == null) {
            a = new ah[150];
            a = new byte[150];
            for (int i2 = 0; i2 < 150; ++i2) {
                a.a[i2] = new ah(i2);
            }
            a = new ah[7][150];
            k = new int[7];
        }
        a.k();
    }

    static void k() {
        for (int i2 = 0; i2 < 150; ++i2) {
            if (a[i2] == null) continue;
            a[i2].a();
            a.a[i2] = 0;
        }
        D = 0;
        a = null;
    }

    static void a(n n2, boolean n3) {
        int n4;
        ac.a(n2);
        if (n3 != 0) {
            int n5;
            int n6;
            int n7;
            n n8 = n2;
            n4 = f - (bD - bF << 5);
            int n9 = g - (bE - bG << 5);
            n4 = 0 - n4;
            n9 = 0 - n9;
            int n10 = 0 + y.a - y.e - n4;
            if (n10 > 0) {
                n7 = y.e + n4;
                n6 = n4 + y.e;
                n5 = 0 + y.b - y.f - n9;
                if (n5 > 0) {
                    int n11 = y.f + n9;
                    int n12 = n9 + y.f;
                    n8.d(0, 0, n7, n11);
                    n8.a(y.a, n4, n9, 0);
                    n8.d(0, n12, n7, n5);
                    n8.a(y.a, n4, n12, 0);
                    n8.d(n6, 0, n10, y.b);
                    n8.a(y.a, n6, n9, 0);
                    n8.d(n6, n12, n10, n5);
                    n8.a(y.a, n6, n12, 0);
                } else {
                    n8.d(0, 0, n7, y.b);
                    n8.a(y.a, n4, n9, 0);
                    n8.d(n6, 0, n10, y.b);
                    n8.a(y.a, n6, n9, 0);
                }
            } else {
                n7 = 0 + y.b - y.f - n9;
                if (n7 > 0) {
                    n6 = y.f + n9;
                    n5 = n9 + y.f;
                    n8.d(0, 0, y.a, n6);
                    n8.a(y.a, n4, n9, 0);
                    n8.d(0, n5, y.a, n7);
                    n8.a(y.a, n4, n5, 0);
                } else {
                    n8.d(0, 0, y.a, y.b);
                    n8.a(y.a, n4, n9, 0);
                }
            }
            n8.d(0, 0, y.a, y.b);
            a.g();
        }
        for (n3 = 1; n3 < 6; ++n3) {
            for (n4 = 0; n4 < k[n3]; ++n4) {
                a[n3][n4].a(n2);
            }
        }
        for (n3 = 0; n3 < k[6]; ++n3) {
            a[6][n3].a(n2);
        }
        if (y.a != null && y.a.a == 3 && (y.a.a[15] == 1 || y.a.a[15] == 7 || y.a.a[15] == 19 || y.a.a[15] == 25)) {
            n3 = 0 + (y.a.e >> 16) - f;
            n4 = 0 + (y.a.f >> 16) - g;
            y.a(y.a.a[15], n3, n4 + -37);
        }
        if (y.b() == 7 || y.b() == 18 || y.b() == 19) {
            y.c(n2);
        }
    }

    static void l() {
        for (int i2 = 0; i2 < D; ++i2) {
            if (a[i2] != 1) continue;
            a[i2].d();
        }
    }

    private static void g(int n2) {
        try {
            b b2 = null;
            try {
                b2 = new b(n2);
                while (b2.available() > 0) {
                    j j2;
                    n2 = b2.read() & 0xFF;
                    if (n2 >= 60 && n2 <= 65) {
                        j j3;
                        if (bT >= 250) {
                            j3 = null;
                        } else {
                            j2 = a[bT++];
                            a[bT++].a = (byte)n2;
                            ((r)j2).a();
                            j3 = j2;
                        }
                        j3.a(b2);
                        continue;
                    }
                    if (n2 >= 80 && n2 <= 96) {
                        j j4;
                        if (bU >= 250) {
                            j4 = null;
                        } else {
                            j2 = b[bU++];
                            b[bU++].a = (byte)n2;
                            ((r)j2).a();
                            j4 = j2;
                        }
                        j4.a(b2);
                        continue;
                    }
                    if (n2 == 0) continue;
                    j2 = a.b(n2);
                    ((ah)j2).a(b2, n2);
                    if (n2 != 1) continue;
                    if (t == -1) {
                        t = ((ah)j2).b;
                    }
                    ++u;
                    if (y.c()) continue;
                    ((ah)j2).d |= 0x10;
                }
            }
            catch (Exception exception) {}
            b2.close();
            b.a();
            System.gc();
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private static void h(int n2) {
        bI = 0;
        try {
            b b2 = null;
            try {
                b2 = new b(n2);
                while (b2.available() > 0) {
                    n2 = (b2.read() & 0xFF) + ((b2.read() & 0xFF) << 8);
                    for (int i2 = 0; i2 < n2; ++i2) {
                        a.a[a.bI][0] = (short)((b2.read() & 0xFF) + ((b2.read() & 0xFF) << 8)) << 16;
                        a.a[a.bI][1] = (short)((b2.read() & 0xFF) + ((b2.read() & 0xFF) << 8)) << 16;
                        ++bI;
                    }
                }
            }
            catch (Exception exception) {}
            b2.close();
            b.a();
            System.gc();
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private static ah e(int n2) {
        for (int i2 = 0; i2 < D; ++i2) {
            if (a.a[i2].c != n2) continue;
            return a[i2];
        }
        return null;
    }

    static ah a(int n2) {
        if (n2 < 0) {
            return null;
        }
        if (a[n2 &= 0xFFFF] == 1) {
            return a[n2];
        }
        return null;
    }

    static void e(int n2) {
        int n3;
        ah ah2 = a[n2];
        if (ah2.a == 12 || ah2.a == 9 || ah2.a == 1) {
            a.a(ah2);
        }
        if (ah2.a == 33) {
            for (n3 = t; n3 < t + u; ++n3) {
                ah ah3;
                if (a[n3] != 1 || (ah3 = a[n3]) == null || ah3.b()) continue;
                ah3.a[27] = 0;
            }
            a.a(ah2);
        }
        if (ah2.a != 1) {
            a.a[n2] = 0;
        }
        if (ah2.a == 19) {
            ah2.d &= 0xFFFFFFDF;
        }
        for (n3 = D - 1; n3 >= 0 && a[n3] != 1; --n3) {
        }
        D = n3 + 1;
        ah2.d |= 0x18;
    }

    private static void i(int n2) {
        a.a[n2] = 1;
        a.a[n2].d &= 0xFFFFFFE7;
        if (a.a[n2].a == 19) {
            a[n2].a(0);
            a.a[n2].r = -1;
            a[n2].b((int)a.a[n2].a[1]);
            a[n2].c();
            a[n2].a(0);
            a[n2].b(48);
            a.a[n2].d |= 0x40;
        }
    }

    static ah b(int n2) {
        ah[] ahArray = a;
        ah ah2 = null;
        int n3 = 0;
        if (ahArray == a) {
            int n4;
            block5: {
                for (int i2 = 0; i2 < 150; ++i2) {
                    if (a[i2] != 0) continue;
                    n4 = i2;
                    break block5;
                }
                n4 = n3 = -1;
            }
            if (n4 < 0) {
                return null;
            }
            ah2 = a[n3];
            a.a[n3] = 1;
            if (n2 == 19) {
                ++z;
            }
            if (n3 >= D) {
                D = n3 + 1;
            }
        }
        ah2.a();
        ah2.a = n2;
        ah2.b = n3;
        ah2.b();
        return ah2;
    }

    static ah a(short s2, byte by, byte by2, byte by3) {
        ah ah2 = a.b(17);
        if (ah2 == null) {
            return null;
        }
        ah2.a(ah2.e, ah2.e);
        ah2.a[0] = s2;
        ah2.a[0] = by;
        ah2.a[1] = by2;
        ah2.a[2] = 1;
        ah2.a = 0L;
        return ah2;
    }

    static ah c(int n2) {
        ah ah2 = a.b(9);
        a.b(9).q = 10;
        ah2.b(2);
        ah2.c();
        ah2.d |= 0x40;
        ah2.d |= 0x20;
        ah2.e = 0;
        boolean bl = false;
        while (!bl) {
            int n3 = a.a(196, 636);
            int n4 = a.a(96, 416);
            ah2.e = n3 << 16;
            ah2.f = n4 << 16;
            if (!ah2.a(a, ah2.e, ah2.f)) continue;
            bl = true;
            ah2.f = 0x320000;
        }
        a.b(ah2.b);
        ah2.q = 10;
        ah2.d |= 0x40;
        ah2.d |= 0x20;
        if (n2 == -1) {
            ah2.a[0] = n2;
            ah2.b(3);
        } else if (n2 == -2) {
            ah2.a[0] = -2;
            ah2.b(4);
        } else {
            n2 = (y.D + 1) / 7;
            if (n2 >= y.B.length) {
                n2 = y.B.length - 1;
            }
            n2 = y.B[n2];
            ah2.a[0] = y.A[y.c(n2)];
            if (y.A == 3) {
                while (ah2.a[0] == 18) {
                    ah2.a[0] = y.A[y.c(n2)];
                }
            }
            ah2.b(2);
        }
        return ah2;
    }

    private static void c(int n2, int n3) {
        for (int i2 = 0; i2 < n2; ++i2) {
            ah ah2 = a.b(n3);
            ah2.c();
            boolean bl = false;
            int n4 = 0;
            while (!bl && n4 < 40) {
                int n5 = y.b(G, 640 + G);
                int n6 = y.b(H, 320 + H);
                ah2.e = n5 << 16;
                ah2.f = n6 << 16;
                if (!ah2.a(a, n5 << 16, n6 << 16)) {
                    if ((n6 = ah2.a(n5, n6, false)) < b) {
                        bl = true;
                    }
                } else {
                    if (n3 == 13 || n3 == 12) {
                        bl = false;
                        continue;
                    }
                    if ((n6 = ah2.a(n5, n6, true)) > 0x180000 && n6 < b) {
                        bl = true;
                    }
                }
                ah2.a(n5 << 16, n6 << 16);
                if (bl && (n3 == 13 || n3 == 31) && ah2.d()) {
                    bl = false;
                }
                if (bl) {
                    n5 = -1;
                    n6 = -1;
                    if (n3 == 12 && v != -1) {
                        n5 = v;
                        n6 = v + w;
                    } else if (n3 == 13 && x != -1) {
                        n5 = x;
                        n6 = x + y;
                    } else if (n3 == 31 && E != -1) {
                        n5 = E;
                        n6 = E + F;
                    }
                    if (n5 != -1) {
                        bl = a.a(ah2, n5, n6, 30);
                    }
                    if (n3 == 13 && bl && v != -1) {
                        bl = a.a(ah2, v, v + w, 6);
                    }
                    if (n3 == 31 && bl && v != -1) {
                        bl = a.a(ah2, v, v + w, 8);
                    }
                    if (n3 == 31 && bl && x != -1) {
                        bl = a.a(ah2, x, x + y, 8);
                    }
                }
                if (bl && n3 == 12) {
                    for (n5 = t; n5 < t + u; ++n5) {
                        if (a[n5] != 1 || a.a[n5].a != 1 || a[n5].b() || !ah2.a(a[n5], ah2.e, ah2.f)) continue;
                        bl = false;
                        break;
                    }
                }
                ++n4;
            }
            if (!bl) {
                a.e(ah2.b);
                continue;
            }
            if (n3 == 12) {
                a.a(ah2, (byte)1);
                if (v == -1) {
                    v = ah2.b;
                }
                ++w;
            }
            if (n3 == 13) {
                if (x == -1) {
                    x = ah2.b;
                }
                ++y;
            }
            if (n3 != 31) continue;
            if (E == -1) {
                E = ah2.b;
            }
            ++F;
        }
    }

    private static boolean a(ah ah2, int n2, int n3, int n4) {
        if (n2 < 0) {
            n2 = 0;
        }
        for (int i2 = n2; i2 < n3; ++i2) {
            int n5;
            n2 = Math.abs(a.a[i2].e - ah2.e) >> 16;
            if (n2 * n2 + (n5 = Math.abs(a.a[i2].f - ah2.f) >> 16) * n5 >= n4 * n4) continue;
            return false;
        }
        return true;
    }

    static ah a(int n2, int n3, int n4) {
        ah ah2 = a.b(10);
        ah2.a(n3 + 25 << 16, n4 + -34 << 16);
        ah2.a[0] = n2;
        return ah2;
    }

    public static int b() {
        int n2 = y.r[y.I];
        do {
            if (++n2 < t) {
                n2 = t;
            }
            if (n2 <= t + u - 1) continue;
            n2 = t;
        } while (a.a[n2].a != 1 || a[n2].b() || a.a[n2].a[3] != y.I || (a.a[n2].d & 0x18) != 0);
        return n2;
    }

    static void a(int n2, int n3, int n4, int n5) {
        n3 <<= 16;
        int n6 = y.b[n2][0];
        int n7 = y.b[n2][1];
        for (int i2 = 0; i2 < D; ++i2) {
            int n8;
            int n9;
            if (a[i2] != 1) continue;
            ah ah2 = a[i2];
            if (ah2.a != 1 && ah2.a != 12 && ah2.a != 13 && ah2.a != 9 && ah2.a != 14 && ah2.a != 19 && ah2.a != 27 && ah2.a != 33 || n3 == 0 || n2 == 31 && ah2 == y.a || (n2 == 28 || n2 == 0) && ah2 == y.a && ah2.a == 22 && ah2.a[15] == 32 || (n2 == 16 || n2 == 12) && ah2.a == 1 && ah2 == y.a || n2 == 19 && ah2.a == 1 && ah2.a == 22 || n2 == 8 && ah2.a == 1 && ah2.a[13] == 1) continue;
            y.o[0] = ah2.e - n4;
            y.o[1] = ah2.c() - n5;
            if (n2 == 31 || n2 == 2) {
                y.o[0] = y.a.a[5] * Math.abs(ah2.e - n4);
            }
            if ((n9 = y.a(y.o)) > n3 || (n8 = n2 == 31 ? 0x1E0000 : (n6 << 16) + (int)((long)(n7 - n6 << 16) * (long)((int)(((long)n3 - (long)n9 << 16) / (long)n3)) >> 16)) >> 16 == 0) continue;
            if (n2 == 2 && ah2.a == 1) {
                if (ah2.a == 18 || ah2.a) continue;
                ah2.a = true;
            }
            n9 = (int)((long)y.o[0] * (long)n8 >> 16) / 3;
            int n10 = (int)((long)y.o[1] * (long)n8 >> 16) / 3;
            if (n2 == 16 && ah2.a == 1) {
                n9 = y.a.a[5] * 180 >> 16;
                n10 = y.b(n9);
                int n11 = y.a(n9);
                n9 = y.a.a[5] * (int)(983040L * (long)n10 >> 16);
                n10 = (int)(983040L * (long)n11 >> 16);
                ah2.a[17] = 1;
            }
            if (n2 == 12) {
                n9 = 0;
                n10 = 0;
            }
            if (n2 == 23) {
                n10 = -131072;
            }
            if (n2 == 2) {
                n9 = 131072 * (y.o[0] > 0 ? 1 : -1);
                n10 = -786432;
            }
            n9 = (int)((long)n9 << 16 >> 16);
            n10 = (int)((long)n10 << 16 >> 16);
            if (y.c()) {
                n9 = 0;
                n10 = 0;
            }
            n9 = (n9 << 2) / 5;
            n10 = (n10 << 2) / 5;
            ah2.b(n9, n10);
            if (ah2.a == 1) {
                ah2.b((byte)11);
            }
            ah2.a(n2, n8 >> 16);
        }
    }

    static int a(int n2, int n3, int n4) {
        n4 = 0;
        int n5 = 0;
        int n6 = -1;
        for (int i2 = 0; i2 < D; ++i2) {
            if (a[i2] != 1 || a.a[i2].a != 1) continue;
            int n7 = Math.abs(a.a[i2].e - n2) + Math.abs(a.a[i2].f - n3);
            if (n4 != 0 && n7 >= n5) continue;
            n5 = n7;
            n4 = 1;
            n6 = i2;
        }
        return n6;
    }

    static void m() {
        for (int i2 = 0; i2 < D; ++i2) {
            if (a.a[i2].a != 17) continue;
            a.e(i2);
        }
    }

    static void n() {
        if (a == null) {
            return;
        }
        for (int i2 = 0; i2 < 150; ++i2) {
            a.e(i2);
        }
        ah.c = null;
    }

    static ah d(int n2) {
        int n3 = 65536000;
        int n4 = -1;
        boolean bl = false;
        if (y.a.a[15] == 9) {
            bl = true;
        }
        for (int i2 = t; i2 < t + u; ++i2) {
            if (i2 == ah.a.b || (a.a[i2].d & 0x10) == 16 || a[i2] != 1 || a.a[i2].a != 1 || bl && a[i2].b() || a.a[i2].f >= (b << 16) + 0x180000 || a.a[i2].f >= q << 16 || a.a[i2].e <= 96 || a.a[i2].e >= p - 96 << 16 || a.a[i2].e >> 16 <= 96) continue;
            int n5 = a.a[i2].e - ah.a.e;
            if (n2 == 0) {
                if (n5 <= 0 || n5 >= n3) continue;
                n3 = n5;
                n4 = i2;
                continue;
            }
            if (n5 >= 0 || Math.abs(n5) >= Math.abs(n3)) continue;
            n3 = n5;
            n4 = i2;
        }
        if (y.f && n4 == -1) {
            n4 = ah.a.b;
        }
        if (n4 == -1) {
            return null;
        }
        return a[n4];
    }

    static void o() {
        if (y.A == 4 || y.q) {
            return;
        }
        for (int i2 = t; i2 < t + u; ++i2) {
            if (a[i2] != 1 || !a.a[i2].b || a.a[i2].a != 1) continue;
            a.a[i2].a[9] = a.a[i2].a[9] + y.b[12][0];
            if (!a.a[i2].c) continue;
            a.b(a.a[i2].b);
            a[i2].a(0);
            a[i2].b(72);
        }
        y.q = true;
    }

    private static void a(int n2, int n3, byte by) {
        if (n2 >= bP + G || n3 >= bQ + H || n2 < G || n3 < H) {
            return;
        }
        n2 = n2 - G + (n3 - H) * bP;
        n3 = n2 >> 3;
        n2 &= 7;
        if (by != 0) {
            int n4 = n3;
            x[n4] = (byte)(x[n4] | 1 << n2);
            return;
        }
        int n5 = n3;
        x[n5] = (byte)(x[n5] & ~(1 << n2));
    }

    public static int b(int n2, int n3) {
        if (n2 >= bP + G || n3 >= bQ + H || n2 < G || n3 < H) {
            return 0;
        }
        n2 = n2 - G + (n3 - H) * bP;
        n3 = n2 >> 3;
        if ((x[n3] & 1 << (n2 &= 7)) != 0) {
            return 1;
        }
        return 0;
    }

    static boolean a(int n2, int n3) {
        return 0 == a.b(n2, n3 + 1);
    }

    static void a(ah ah2, byte n2) {
        n2 = (ah2.e >> 16) + ah2.w[0];
        int n3 = (ah2.f >> 16) + ah2.w[1];
        int n4 = ah2.w[2] - ah2.w[0];
        int n5 = ah2.w[3] - ah2.w[1];
        for (int i2 = n2; i2 < n2 + n4; ++i2) {
            for (int i3 = n3; i3 < n3 + n5; ++i3) {
                a.a(i2, i3, (byte)1);
            }
        }
    }

    private static void a(ah ah2) {
        int n2 = (ah2.e >> 16) + ah2.w[0];
        int n3 = (ah2.f >> 16) + ah2.w[1];
        int n4 = ah2.w[2] - ah2.w[0];
        int n5 = ah2.w[3] - ah2.w[1];
        for (int i2 = n2; i2 < n2 + n4; ++i2) {
            for (int i3 = n3; i3 < n3 + n5; ++i3) {
                a.a(i2, i3, (byte)0);
            }
        }
    }

    static boolean a(int n2, int n3, int n4, int n5) {
        return a.b(n2, n3) > 0 || a.b(n2 + n4, n3) > 0 || a.b(n2, n3 + n5) > 0 || a.b(n2 + n4 / 2, n3 + n5 / 2) > 0 || a.b(n2 + n4, n3 + n5) > 0;
    }

    static boolean b(int n2, int n3, int n4, int n5) {
        return a.b(n2 - n4, n3 - n5) > 0 || a.b(n2 + n4, n3 - n5) > 0 || a.b(n2, n3) > 0 || a.b(n2 - n4, n3 + n5) > 0 || a.b(n2 + n4, n3 + n5) > 0;
    }

    static void f(int n2) {
        if (J == n2) {
            return;
        }
        J = n2;
    }

    static void p() {
        if (y.f) {
            a = true;
        }
        if (J == 0) {
            return;
        }
        if (y.j && !a) {
            if ((s.a() - (c + b) > d || y.k) && !y.l) {
                y.j = false;
                a.f(0);
                return;
            }
        } else {
            if (a) {
                return;
            }
            a = y.A == 1 && y.z == 2 ? f + 16000L - s.a() + b : f + g - s.a() + b;
            if (a < 6000L && (y.C & 1) == 0) {
                y.m(1);
            }
            if (a <= 0L && (y.p == 2 || y.p == 4 || y.p == 3)) {
                y.c(5);
                if (y.a != null && y.a.a == 2) {
                    y.a.b((byte)1);
                }
            }
        }
    }

    static void q() {
        b = 0L;
        f = s.a();
        a.f(2);
    }

    static void r() {
        b = 0L;
        a.f(1);
    }

    static void s() {
        if (y.a != null && y.a.a[12] == 1) {
            y.j = true;
            c = s.a() - b;
        }
        if (y.a.a[12] == 0) {
            a.f(0);
        }
    }

    static void t() {
        a.f(0);
    }

    static void u() {
        a.f(2);
    }

    static void v() {
        b = s.a() - b;
        a.f(0);
    }

    static void w() {
        if (y.a != null) {
            y.a.a += s.a() - b;
        }
        if (J != 0) {
            return;
        }
        b = s.a() - b;
        a.f(1);
    }

    static void a(ah ah2, int n2, int n3) {
        for (int i2 = 0; i2 < bT; ++i2) {
            if (a.a[i2].a != 60) continue;
            int n4 = n3 >> 16;
            int n5 = n2 >> 16;
            ah ah3 = ah2;
            r r2 = a[i2];
            if (!r2.a || r2.a[5] != -1 && ah3.c != r2.a[5]) continue;
            if (r2.a(ah3.e >> 16, ah3.f >> 16) && !r2.a(n5, n4)) {
                r2.a[2] = (byte)(r2.a[2] + 1);
            }
            if (r2.a[2] < r2.a[1]) continue;
            r2.b();
            r2.a[2] = 0;
        }
    }

    static void a(short s2, ah ah2, boolean bl) {
        block7: for (int i2 = 0; i2 < bT; ++i2) {
            if (a.a[i2].a != s2) continue;
            ah ah3 = ah2;
            r r2 = a[i2];
            if (!r2.a) continue;
            switch (r2.a) {
                case 61: {
                    if (ah3.c != r2.a[5] || !r2.a(ah3.e >> 16, ah3.f >> 16)) continue block7;
                    r2.b();
                    continue block7;
                }
                case 62: {
                    r2.b();
                    continue block7;
                }
                case 63: {
                    if (bl) {
                        if (r2.a[1] != 105) continue block7;
                        r2.b();
                        continue block7;
                    }
                    if (ah3.a[15] != r2.a[1]) continue block7;
                    r2.b();
                    continue block7;
                }
                case 64: {
                    if (z != r2.a[1]) continue block7;
                    r2.b();
                    continue block7;
                }
                case 65: {
                    if (y.bE != r2.a[1]) continue block7;
                    r2.b();
                }
            }
        }
    }

    static r a(int n2) {
        int n3;
        for (n3 = 0; n3 < bT; ++n3) {
            if (a.a[n3].a != n2) continue;
            return a[n3];
        }
        for (n3 = 0; n3 < bU; ++n3) {
            if (a.b[n3].a != n2) continue;
            return b[n3];
        }
        return null;
    }

    static {
        h = new int[24];
        c = 0;
        d = 0;
        e = 0;
        int[] nArray = new int[]{8416592, 6775146, 11308650, 5061401, 5987971};
        g = false;
        bt = 0;
        k = 0;
        l = 30;
        m = j.K / 2;
        n = j.L / 2;
        o = -1;
        h = false;
        by = -1;
        bz = -1;
        bC = 0;
        i = new int[1024];
        j = new int[5];
        m = true;
        c = new int[16][2];
        p = 640;
        q = 320;
        bP = 640;
        bQ = 320;
        a = new int[]{-1, -1, -1, -1};
        t = -1;
        z = 0;
        B = 0;
        j = true;
        C = -1;
        x = new byte[25601];
        G = 0;
        H = 0;
        I = 0;
        g = 46000L;
        b = 0L;
        k = false;
    }
}

