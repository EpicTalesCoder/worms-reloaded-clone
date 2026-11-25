/*
 * Decompiled with CFR 0.152.
 */
/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class ac
implements j {
    static int a;
    private static int[] a;
    private static n a;
    private static int[] h;
    private int d;
    int b;
    private short[] a;
    private short[] b;
    private byte[] a;
    private byte[] w;
    private int e;
    private short[] c;
    private int f;
    private short[] d;
    private short[] e;
    private short[] f;
    private byte[] x;
    private int g;
    private short[] g;
    private int h;
    private byte[] y;
    private byte[] z;
    private short[] h;
    private byte[] A;
    private byte[] B;
    private int i;
    private e[] a;
    private int j;
    private int[] i;
    private byte[] C;
    private int[][] a;
    private int k;
    private static int l;
    static int c;
    private static int m;
    private static int n;
    private static int o;
    private static int p;

    static void a(n n2) {
        a = n2;
    }

    ac() {
    }

    ac(int n2) {
        this.k = n2;
        this.b(18 + (n2 << 1));
        this.c(18 + (n2 << 1) + 1);
    }

    final void a() {
        this.a = null;
        this.b = null;
        this.a = null;
        this.w = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.x = null;
        this.g = null;
        this.y = null;
        this.z = null;
        this.h = null;
        this.A = null;
        this.B = null;
        this.a = null;
        this.i = null;
        this.C = null;
        this.a = null;
    }

    private void b(int n2) {
        try {
            int n3;
            b b2 = new b(n2);
            this.d = b2.read() & 0xFF;
            this.b = b2.a() & 0xFFFF;
            if (this.d == 0) {
                this.a = y.a(b2, this.b, null, 0);
                this.b = y.a(b2, this.b, null, 0);
            }
            this.a = new byte[this.b];
            this.w = new byte[this.b];
            b2.read(this.a, 0, this.b);
            b2.read(this.w, 0, this.b);
            this.e = b2.a() & 0xFFFF;
            this.c = new short[this.e + 1];
            this.c[0] = 0;
            int n4 = 0;
            for (n3 = 1; n3 <= this.e; ++n3) {
                this.c[n3] = (short)(n4 += b2.read() & 0xFF);
            }
            this.f = b2.a() & 0xFFFF;
            this.d = y.a(b2, this.f, null, 0);
            this.e = y.a(b2, this.f, null, 0);
            this.f = new short[this.f];
            this.x = new byte[this.f];
            for (n3 = 0; n3 < this.f; ++n3) {
                this.f[n3] = (short)(b2.read() & 0xFF);
            }
            b2.read(this.x, 0, this.f);
            for (n3 = 0; n3 < this.f; ++n3) {
                int n5 = n3;
                this.f[n5] = (short)(this.f[n5] + ((this.x[n3] & 0xD0) << 2));
            }
            this.g = b2.a() & 0xFFFF;
            this.g = new short[this.g + 1];
            this.g[0] = 0;
            n3 = 0;
            for (n4 = 1; n4 <= this.g; ++n4) {
                this.g[n4] = (short)(n3 += b2.read() & 0xFF);
            }
            this.h = b2.a() & 0xFFFF;
            this.y = new byte[this.h];
            this.z = new byte[this.h];
            this.h = new short[this.h];
            this.A = new byte[this.h];
            this.B = new byte[this.h];
            b2.read(this.y, 0, this.h);
            b2.read(this.z, 0, this.h);
            for (n4 = 0; n4 < this.h; ++n4) {
                this.h[n4] = (short)(b2.read() & 0xFF);
            }
            b2.read(this.A, 0, this.h);
            b2.read(this.B, 0, this.h);
            for (n4 = 0; n4 < this.h; ++n4) {
                int n6 = n4;
                this.h[n6] = (short)(this.h[n6] + ((this.B[n4] & 0xD0) << 2));
            }
            b2.close();
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private void c(int n2) {
        try {
            if (this.d == 1) {
                int n3;
                int n4;
                b b2 = new b(n2);
                this.j = b2.read() & 0xFF;
                int n5 = b2.read() & 0xFF;
                int n6 = b2.read() & 0xFF;
                int n7 = b2.read() & 0xFF;
                this.a = new int[n6][];
                block6: for (n4 = 0; n4 < n6; ++n4) {
                    this.a[n4] = new int[n7];
                    switch (n5) {
                        case 129: {
                            for (n3 = 0; n3 < n7; ++n3) {
                                this.a[n4][n3] = b2.a();
                            }
                            continue block6;
                        }
                        case 5: {
                            short[] sArray = y.a(b2, n7, null, 0);
                            for (int i2 = 0; i2 < n7; ++i2) {
                                int n8 = sArray[i2];
                                n8 = (n8 & 0xFFFF) == 63519 ? 0xFF00FF : 0xFF000000 | (n8 & 0xF800) << 8 | (n8 & 0x7E0) << 5 | (n8 & 0x1F) << 3;
                                this.a[n4][i2] = n8;
                            }
                            continue block6;
                        }
                    }
                }
                this.i = new int[this.b + 1];
                n4 = 0;
                for (n3 = 0; n3 < this.b; ++n3) {
                    this.i[n3] = n4;
                    n4 += b2.a() & 0xFFFF;
                }
                this.i[this.b] = n4;
                this.C = new byte[n4];
                b2.read(this.C, 0, n4);
                b2.close();
                return;
            }
            this.a = new e[1];
            this.a[0] = b.a(n2);
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    final void b() {
        this.i = 0;
    }

    private static int c(int n2, int n3) {
        if ((n2 & 4) == 0) {
            return n2 ^ n3;
        }
        if ((n3 & 1) != 0) {
            n2 ^= 2;
        }
        if ((n3 & 2) != 0) {
            n2 ^= 1;
        }
        if ((n3 & 4) != 0) {
            n2 ^= 7;
        }
        return n2;
    }

    final void a(int n2, int n3, int n4, int n5, int n6) {
        n2 = this.g[n2] + n3;
        this.a(this.h[n2] & 0xFFFF, n4 + this.y[n2], n5 + this.z[n2], ac.c(this.B[n2] & 7, n6));
    }

    final void a(int n2, int n3, int n4, int n5) {
        int n6 = this.c[n2 + 1] - this.c[n2];
        for (int i2 = 0; i2 < n6; ++i2) {
            int n7;
            int n8;
            int n9 = i2;
            Object object = this;
            int n10 = ((ac)object).c[n2] + n9;
            n9 = ((ac)object).d[n10];
            int n11 = ((ac)object).e[n10];
            int n12 = ((ac)object).f[n10] & 0xFFFF;
            n10 = ((ac)object).x[n10] & 7;
            if ((n5 & 7) != 0) {
                if ((n10 & 4) == 0) {
                    n8 = ((ac)object).a[n12] & 0xFF;
                    n7 = ((ac)object).w[n12] & 0xFF;
                } else {
                    n8 = ((ac)object).w[n12] & 0xFF;
                    n7 = ((ac)object).a[n12] & 0xFF;
                }
                if ((n5 & 1) != 0) {
                    n9 = -n9 - n8;
                }
                if ((n5 & 2) != 0) {
                    n11 = -n11 - n7;
                }
                if ((n5 & 4) != 0) {
                    n8 = n9;
                    n9 = -n11 - n7;
                    n11 = n8;
                }
                n10 = ac.c(n10, n5);
            }
            int n13 = n10;
            int n14 = n4 + n11;
            n9 = n3 + n9;
            n10 = n12;
            if (a == 0) {
                n8 = ((ac)object).a[n10] & 0xFF;
                n7 = ((ac)object).w[n10] & 0xFF;
                if (((ac)object).d == 0) {
                    if (((ac)object).a == null) continue;
                } else {
                    object = ((ac)object).b(n10, n13, 0);
                    if ((n13 & 4) == 0) {
                        a.a((int[])object, n8, n9, n14, n8, n7);
                        continue;
                    }
                    a.a((int[])object, n7, n9, n14, n7, n8);
                    continue;
                }
                e e2 = ((ac)object).a[0];
                n11 = ((ac)object).a[n10] & 0xFFFF;
                n12 = ((ac)object).b[n10] & 0xFFFF;
                if (((ac)object).k == 0) {
                    if (ah.b(n10)) {
                        n11 = n10;
                        int n15 = l == -1 || !ah.b(n11) ? -1 : 0 + (n11 - 11) + (l << 2);
                        if (n15 < 0) continue;
                        y.a[4].a(n15, n9, n14, n13);
                        continue;
                    }
                    if (ah.c(n10)) {
                        int n16 = ah.a(n10, c);
                        if (n16 < 0) continue;
                        n10 = 0;
                        int n17 = 0;
                        if (n16 >= 79 && n16 < 82) {
                            n10 = -10;
                            if ((n13 & 1) != 0) {
                                n10 = 10;
                            }
                            n17 = 8;
                        }
                        if (c == 12 || c == 30) {
                            n11 = 3;
                            if (c == 30) {
                                n11 = 2;
                            }
                            n12 = n11;
                            n7 = n14 + n17;
                            n8 = n9 + n10;
                            Object object2 = y.a[2];
                            n12 = ((ac)object2).c[n16] + n12;
                            ac.a[0] = ((ac)object2).d[n12] + n8;
                            ac.a[1] = ((ac)object2).e[n12] + n7;
                            object2 = a;
                            if ((n13 & 1) == 1) {
                                object2[0] = 2 * (n9 + n10) - object2[0];
                            }
                            ah.i = (int)object2[0];
                            ah.j = (int)object2[1];
                        }
                        y.a[2].a(n16, n9 + n10, n14 + n17, n13);
                        continue;
                    }
                    if (n10 == 173) {
                        y.a[3].a(19, y.n % 2, n9, n14, 0);
                        continue;
                    }
                }
                if (e2 == null) continue;
                a.a(e2, n11, n12, n8, n7, h[n13], n9, n14, 20);
                continue;
            }
            if (a == 2 || a != 1) continue;
            int n18 = ((ac)object).a[n10] & 0xFF;
            n11 = ((ac)object).w[n10] & 0xFF;
            if ((n13 & 4) != 0) {
                n12 = n18;
                n18 = n11;
                n11 = n12;
            }
            if (((ac)object).k == 0 && (y.a != null && ah.a(n10, c) >= 0 || ah.b(n10) && l >= 0)) continue;
            y.a(a, a, y.a(y.n, n9, n14, n18, n11));
        }
    }

    static void a(int n2) {
        l = n2;
    }

    final int[] a(int n2, int n3, int n4) {
        y.a(a, 0x40000000, 0x40000000, -1073741824, -1073741824);
        n3 = a;
        a = 1;
        this.a(n2, 0, 0, n4);
        a = n3;
        return a;
    }

    final int a(int n2) {
        return this.g[n2 + 1] - this.g[n2];
    }

    final int a(int n2, int n3) {
        return this.A[this.g[n2] + n3] & 0xFF;
    }

    final int b(int n2, int n3) {
        return this.h[this.g[n2] + n3] & 0xFF;
    }

    final int[] b(int n2, int n3, int n4) {
        int n5;
        int n6;
        int n7 = this.a[n2] & 0xFF;
        int n8 = this.w[n2] & 0xFF;
        int[] nArray = this.a[n4];
        int[] nArray2 = new int[n7 * n8];
        int n9 = n7;
        int n10 = n8;
        if ((n3 & 4) != 0) {
            n9 = n8;
            n10 = n7;
        }
        int n11 = n10;
        int n12 = n9;
        m = 0;
        n = 0;
        o = n12;
        p = n11;
        int[] nArray3 = nArray;
        int n13 = this.i[n2];
        int n14 = n7;
        int n15 = n8;
        n8 = n3;
        n7 = n9;
        nArray = nArray2;
        n3 = n15;
        n2 = n14;
        int n16 = n13;
        byte[] byArray = this.C;
        int[] nArray4 = nArray3;
        int n17 = this.j;
        n9 = Math.max(0, 0);
        n10 = Math.max(0, 0);
        if ((n8 & 4) != 0) {
            n6 = n3;
            n5 = n2;
        } else {
            n6 = n2;
            n5 = n3;
        }
        n6 = Math.min(o, n6);
        n5 = Math.min(p, n5);
        if ((n6 -= n9) > 0 && (n5 -= n10) > 0) {
            int n18 = 0;
            int n19 = 0;
            int n20 = 0;
            int n21 = 0;
            int n22 = 0;
            int n23 = 0;
            switch (n8) {
                case 0: {
                    n21 = n10 * n7 + n9;
                    n22 = n7 - n6;
                    n23 = 1;
                    n18 = n10 * n2 + n9;
                    n19 = n2 - n6;
                    n20 = n5;
                    break;
                }
                case 1: {
                    n21 = n10 * n7 + n9 + n6 - 1;
                    n22 = n7 + n6;
                    n23 = -1;
                    n18 = n10 * n2 + n2 - n9 - n6;
                    n19 = n2 - n6;
                    n20 = n5;
                    break;
                }
                case 2: {
                    n21 = (n10 + n5 - 1) * n7 + n9;
                    n22 = -n7 - n6;
                    n23 = 1;
                    n18 = (n3 - n10 - n5) * n2 + n9;
                    n19 = n2 - n6;
                    n20 = n5;
                    break;
                }
                case 3: {
                    n21 = (n10 + n5 - 1) * n7 + n9 + n6 - 1;
                    n22 = -n7 + n6;
                    n23 = -1;
                    n18 = (n3 - n10 - n5) * n2 + n2 - n9 - n6;
                    n19 = n2 - n6;
                    n20 = n5;
                    break;
                }
                case 4: {
                    n21 = n10 * n7 + n9 + n6 - 1;
                    n22 = -n5 * n7 - 1;
                    n23 = n7;
                    n18 = (n3 - n9 - n6) * n2 + n10;
                    n19 = n2 - n5;
                    n20 = n6;
                    break;
                }
                case 5: {
                    n21 = (n10 + n5 - 1) * n7 + n9 + n6 - 1;
                    n22 = n5 * n7 - 1;
                    n23 = -n7;
                    n18 = (n3 - n9 - n6) * n2 + n2 - n10 - n5;
                    n19 = n2 - n5;
                    n20 = n6;
                    break;
                }
                case 6: {
                    n21 = n10 * n7 + n9;
                    n22 = -n5 * n7 + 1;
                    n23 = n7;
                    n18 = n9 * n2 + n10;
                    n19 = n2 - n5;
                    n20 = n6;
                    break;
                }
                case 7: {
                    n21 = (n10 + n5 - 1) * n7 + n9;
                    n22 = n5 * n7 + 1;
                    n23 = -n7;
                    n18 = n9 * n2 + n2 - n10 - n5;
                    n19 = n2 - n5;
                    n20 = n6;
                }
            }
            n2 -= n19;
            block10 : switch (n17) {
                case 17: 
                case 18: 
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: {
                    n3 = n17 - 17 + 1;
                    n7 = (1 << n3) - 1;
                    n16 += (n18 *= n3) >> 3;
                    n8 = 8 - (n18 & 7);
                    n9 = byArray[n16++] << n8 - n3;
                    n10 = 0;
                    while (true) {
                        n17 = 0;
                        while (n17 < n2) {
                            if ((n8 -= n3) < 0) {
                                n8 += 8;
                                n9 = n9 << 8 | byArray[n16++] & 0xFF;
                            }
                            n6 = n9 >> n8 & n7;
                            if ((n6 = nArray4[n6]) < 0) {
                                nArray[n21] = n6;
                            }
                            ++n17;
                            n21 += n23;
                        }
                        if (++n10 >= n20) break block10;
                        n21 += n22;
                        if ((n8 -= n3 * n19) >= 0) continue;
                        n16 += Math.abs(n8) >> 3;
                        n8 = -(Math.abs(n8) & 7) + 8;
                        n9 = ((byArray[n16 - 1] & 0xFF) << 8 | byArray[n16++] & 0xFF) << n8 - n3;
                    }
                }
                case 49: {
                    break;
                }
                case 50: {
                    int n24 = n19;
                    int n25 = n18;
                    n17 = n20;
                    n10 = n23;
                    n9 = n22;
                    n8 = n21;
                    int[] nArray5 = nArray;
                    int n26 = n2;
                    n3 = n16;
                    byte[] byArray2 = byArray;
                    int[] nArray6 = nArray4;
                    n6 = 0;
                    n5 = -1;
                    while (n25 > 0) {
                        if (((n5 = byArray2[n3++] & 0xFF) & 0x80) != 0) {
                            n6 = byArray2[n3++] & 0xFF;
                            n18 = (n5 &= 0x7F) + 1;
                            if (n18 > n25) {
                                n5 -= n25;
                                n25 = 0;
                                continue;
                            }
                            n5 = -1;
                            n25 -= n18;
                            continue;
                        }
                        n5 = -1;
                        --n25;
                    }
                    n18 = 0;
                    while (true) {
                        n19 = n26;
                        if (n5 >= 0) {
                            n25 = n5 + 1;
                            if (n25 > n26) {
                                n5 -= n26;
                                n25 = n26;
                                n19 = 0;
                            } else {
                                n19 = n26 - n25;
                                n5 = -1;
                            }
                            n20 = nArray6[n6];
                            while (n25 > 0) {
                                nArray5[n8] = n20;
                                --n25;
                                n8 += n10;
                            }
                        }
                        while (n19 > 0) {
                            if (((n5 = byArray2[n3++] & 0xFF) & 0x80) != 0) {
                                n6 = byArray2[n3++] & 0xFF;
                                n25 = (n5 &= 0x7F) + 1;
                                if (n25 > n19) {
                                    n5 -= n19;
                                    n25 = n19;
                                    n19 = 0;
                                } else {
                                    n19 -= n25;
                                    n5 = -1;
                                }
                                n20 = nArray6[n6];
                                while (n25 > 0) {
                                    nArray5[n8] = n20;
                                    --n25;
                                    n8 += n10;
                                }
                                continue;
                            }
                            nArray5[n8] = nArray6[n5];
                            n8 += n10;
                            n5 = -1;
                            --n19;
                        }
                        if (++n18 >= n17) break block10;
                        n8 += n9;
                        n25 = n24 - (n5 + 1);
                        if (n25 <= 0) {
                            n5 -= n24;
                            continue;
                        }
                        do {
                            if (((n5 = byArray2[n3++] & 0xFF) & 0x80) != 0) {
                                n6 = byArray2[n3++] & 0xFF;
                                n20 = (n5 &= 0x7F) + 1;
                                if (n20 > n25) {
                                    n5 -= n25;
                                    n25 = 0;
                                    continue;
                                }
                                n5 = -1;
                                n25 -= n20;
                                continue;
                            }
                            n5 = -1;
                            --n25;
                        } while (n25 > 0);
                    }
                }
                case 51: {
                    if (a != 0) break;
                    int n27 = n19;
                    int n28 = n18;
                    n17 = n20;
                    n10 = n23;
                    n9 = n22;
                    n8 = n21;
                    int[] nArray7 = nArray;
                    int n29 = n2;
                    n3 = n16;
                    byte[] byArray3 = byArray;
                    int[] nArray8 = nArray4;
                    n6 = 0;
                    n5 = -1;
                    n18 = 0;
                    while (n28 > 0) {
                        if (((n5 = byArray3[n3++] & 0xFF) & 0x80) != 0) {
                            n6 = byArray3[n3++] & 0xFF;
                            n19 = (n5 &= 0x7F) + 1;
                            if (n19 > n28) {
                                n5 -= n28;
                                n28 = 0;
                                continue;
                            }
                            n5 = -1;
                            n28 -= n19;
                            continue;
                        }
                        n19 = n5 + 1;
                        if (n19 > n28) {
                            n5 -= n28;
                            n3 += n28;
                            n28 = 0;
                            n18 = 1;
                            continue;
                        }
                        n5 = -1;
                        n28 -= n19;
                        n3 += n19;
                    }
                    n19 = 0;
                    while (true) {
                        n28 = n29;
                        if (n5 >= 0) {
                            n20 = n5 + 1;
                            if (n20 > n29) {
                                n5 -= n29;
                                n20 = n29;
                                n28 = 0;
                            } else {
                                n28 = n29 - n20;
                                n5 = -1;
                            }
                            if (n18 != 0) {
                                while (n20 > 0) {
                                    nArray7[n8] = nArray8[byArray3[n3++] & 0xFF];
                                    --n20;
                                    n8 += n10;
                                }
                            } else {
                                n21 = nArray8[n6];
                                while (n20 > 0) {
                                    nArray7[n8] = n21;
                                    --n20;
                                    n8 += n10;
                                }
                            }
                        }
                        while (n28 > 0) {
                            if (((n5 = byArray3[n3++] & 0xFF) & 0x80) != 0) {
                                n6 = byArray3[n3++] & 0xFF;
                                n20 = (n5 &= 0x7F) + 1;
                                if (n20 > n28) {
                                    n5 -= n28;
                                    n20 = n28;
                                    n28 = 0;
                                } else {
                                    n28 -= n20;
                                    n5 = -1;
                                }
                                n21 = nArray8[n6];
                                while (n20 > 0) {
                                    nArray7[n8] = n21;
                                    --n20;
                                    n8 += n10;
                                }
                                n18 = 0;
                                continue;
                            }
                            n18 = 1;
                            n20 = n5 + 1;
                            if (n20 > n28) {
                                n5 -= n28;
                                n20 = n28;
                                n28 = 0;
                            } else {
                                n28 -= n20;
                                n5 = -1;
                            }
                            while (n20 > 0) {
                                nArray7[n8] = nArray8[byArray3[n3++] & 0xFF];
                                --n20;
                                n8 += n10;
                            }
                        }
                        if (++n19 >= n17) break block10;
                        n8 += n9;
                        n20 = n27 - (n5 + 1);
                        if (n20 <= 0) {
                            n5 -= n27;
                            continue;
                        }
                        do {
                            if (((n5 = byArray3[n3++] & 0xFF) & 0x80) != 0) {
                                n6 = byArray3[n3++] & 0xFF;
                                n5 &= 0x7F;
                            }
                            if ((n21 = n5 + 1) > n20) {
                                n5 -= n20;
                                n20 = 0;
                                continue;
                            }
                            n5 = -1;
                            n20 -= n21;
                        } while (n20 > 0);
                    }
                }
            }
        }
        return nArray2;
    }

    static {
        a = new int[4];
        h = new int[]{0, 2, 1, 3, 5, 7, 4, 6};
        l = -1;
        c = -1;
    }
}

