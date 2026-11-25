/*
 * Decompiled with CFR 0.152.
 */
import com.ea.sdk.SDKMIDlet;
import java.util.Vector;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class o {
    private static final byte a = (byte)8;
    private static final byte b = (byte)(0 + a + 6);
    private int a;
    private int b;
    private String[] a;
    private short[] a;
    private short[] b;
    private short[] c;
    private int[] a;
    private String[] b;
    private String[] c;
    private short[] d;
    private c[] a;
    private Object[] a;
    private int c;
    private boolean a;
    private int d;
    private boolean b;
    private int e;
    private int f;
    private int g;
    private int h;
    private String a;
    private e a;
    private e b;
    private short a;
    private short b;
    private int i;
    private int j;
    private int k;
    private short[] e;
    private short[] f;
    private short[] g = 0;
    private short[] h;
    private String b;
    private c a;
    private c b;
    private c c;
    private boolean c;
    private boolean d;
    private int l = 0;
    private int m;
    private int n;
    private int o = 0;
    private int p;
    private int q = 6;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private boolean e;
    private int x;
    private int y;
    private int z;
    private int A;
    private int B;
    private short[][] a;
    private short[][] b;
    private short[] i;
    private short[][] c = true;
    private String[][] a;
    private c[] b = true;
    private c d = true;
    private int C;
    private int D;
    private c e;
    private c f = 0;
    private e[] a = (short)-1;
    private int E = 0;

    public o(int n2, int n3) {
        this.b = (short)-1;
        this.b = n2;
        this.a = n3;
    }

    public final void a(byte[] byArray) {
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7 = 2;
        int n8 = byArray[1];
        this.a = new String[n8];
        this.a = new short[n8];
        this.b = new short[n8];
        this.c = new short[n8];
        for (n6 = 0; n6 < n8; ++n6) {
            n5 = n7 + 1;
            n4 = byArray[n7];
            this.a[n6] = new String(byArray, n5, n4);
            n7 = n5 + n4;
        }
        n5 = n8 << 1;
        n6 = n8 << 2;
        for (n4 = 0; n4 < n8; ++n4) {
            this.a[n4] = p.a(byArray, n7);
            this.b[n4] = p.a(byArray, n7 + n5);
            this.c[n4] = p.a(byArray, n7 + n6);
            n7 += 2;
        }
        n5 = n7 + n6;
        for (n7 = 0; n7 < this.a.length; ++n7) {
            this.d[n7] = p.a(byArray, n5);
            n5 += 2;
        }
        int n9 = n5 + 1;
        n5 = byArray[n5];
        this.a = new short[n5][];
        this.b = new short[n5][];
        for (n3 = 0; n3 < n8; ++n3) {
            for (n2 = 0; n2 < n5; ++n2) {
                n4 = p.a(byArray, n9);
                n6 = p.a(byArray, n9 + 2);
                n9 += 4;
                if (n4 == -1 || n6 == -1) continue;
                if (this.a[n2] == null) {
                    this.a[n2] = new short[n8];
                    this.b[n2] = new short[n8];
                    for (n7 = 0; n7 < n8; ++n7) {
                        this.a[n2][n7] = -1;
                        this.b[n2][n7] = -1;
                    }
                }
                this.a[n2][n3] = n4;
                this.b[n2][n3] = n6;
            }
        }
        n2 = n9 + 1;
        n3 = byArray[n9];
        this.i = new short[n3];
        this.c = new short[n3][];
        this.a = new String[n3][];
        for (n9 = 0; n9 < n3; ++n9) {
            n5 = n2 + 1;
            n7 = byArray[n2];
            short[] sArray = this.i;
            sArray[n9] = n4 = (int)p.a(byArray, n5);
            n2 = n5 + 2;
            this.c[n9] = new short[n7];
            this.a[n9] = new String[n7];
            for (n5 = 0; n5 < n7; ++n5) {
                this.c[n9][n5] = p.a(byArray, n2);
                int n10 = n2 + 2;
                n2 = n10 + 1;
                n4 = byArray[n10];
                this.a[n9][n5] = new String(byArray, n2, n4);
                n2 += n4;
            }
        }
    }

    public final void a(Object[] objectArray) {
        this.a = objectArray;
        Object object = p.a();
        p.a(objectArray[0]);
        p.b();
        this.D = p.b() - p.a();
        p.a(object);
    }

    public final boolean a() {
        if (this.a == null) {
            this.b();
        }
        boolean bl = this.c != 0;
        return bl;
    }

    private void b() {
        Vector<Short> vector;
        Vector<String> vector2;
        int n2;
        int n3;
        String string = "MG_Impl";
        String string2 = SDKMIDlet.a("MG_Impl");
        if (string2 == null) {
            this.c = 0;
            return;
        }
        String string3 = string2.toLowerCase();
        if (string3.equals("static")) {
            this.c = 1;
        } else {
            if (string3.equals("wapstatic")) {
                // empty if block
            }
            if (string3.equals("wap")) {
                this.c = 2;
            } else if (string3.equals("wapstatic")) {
                this.c = 3;
            } else {
                this.c = 0;
                return;
            }
        }
        string = "MG_PRODUCTS";
        String string4 = SDKMIDlet.a("MG_PRODUCTS");
        if (string4 == null || string4.length() == 0) {
            this.c = 0;
            return;
        }
        int n4 = 0;
        int n5 = -1;
        block0: do {
            String string5 = (n3 = string4.indexOf(44, n5 + 1)) == -1 ? string4.substring(n5 + 1) : string4.substring(n5 + 1, n3);
            n5 = n3;
            if (string5.startsWith("MGPANEL") && string5.length() == "MGPANEL".length() + 1 && string5.charAt(string5.length() - 1) >= '0' && string5.charAt(string5.length() - 1) <= '9') {
                int n6;
                int n7 = string5.charAt(string5.length() - 1) - 48;
                string = "MG_MGPANEL" + n7;
                String string6 = SDKMIDlet.a(string);
                if (string6 == null || n7 >= this.i.length) continue;
                n2 = 0;
                int n8 = -1;
                do {
                    String string7 = (n6 = string6.indexOf(44, n8 + 1)) == -1 ? string6.substring(n8 + 1) : string6.substring(n8 + 1, n6);
                    n8 = n6;
                    for (int i2 = 0; i2 < this.a[n7].length; ++i2) {
                        if (!this.a[n7][i2].equals(string7)) continue;
                        n2 = 1;
                    }
                    if (n2 == 0) continue;
                    ++n4;
                    continue block0;
                } while (n6 != -1);
                continue;
            }
            boolean bl = false;
            for (int i3 = 0; i3 < this.a.length; ++i3) {
                if (!this.a[i3].equals(string5)) continue;
                bl = true;
                break;
            }
            if (!bl) continue;
            ++n4;
        } while (n3 != -1);
        if (n4 == 0) {
            this.c = 0;
        }
        this.a = new int[n4];
        this.b = new String[n4];
        this.c = new String[n4];
        int n9 = -1;
        int n10 = 0;
        Vector<String> vector3 = vector2;
        vector2 = new Vector<String>();
        Vector<Short> vector4 = vector;
        vector = new Vector<Short>();
        for (n2 = 0; n2 < this.a.length; ++n2) {
            int n11 = string4.indexOf(44, n9 + 1);
            String string8 = n11 == -1 ? string4.substring(n9 + 1) : string4.substring(n9 + 1, n11);
            n9 = n11;
            if (string8.startsWith("MGPANEL") && string8.length() == "MGPANEL".length() + 1 && string8.charAt(string8.length() - 1) >= '0' && string8.charAt(string8.length() - 1) <= '9') {
                n11 = string8.charAt(string8.length() - 1) - 48;
                string = "MG_MGPANEL" + n11;
                string8 = SDKMIDlet.a(string);
                if (string8 != null && n11 < this.i.length) {
                    int n12;
                    vector3.setSize(0);
                    vector4.setSize(0);
                    n3 = -1;
                    do {
                        String string9 = (n5 = string8.indexOf(44, n3 + 1)) == -1 ? string8.substring(n3 + 1) : string8.substring(n3 + 1, n5);
                        n3 = n5;
                        for (n12 = 0; n12 < this.a[n11].length; ++n12) {
                            if (!this.a[n11][n12].equals(string9) || vector3.contains(string9)) continue;
                            vector3.addElement(string9);
                            vector4.addElement(new Short(this.c[n11][n12]));
                        }
                    } while (n5 != -1);
                    if (vector3.size() != 0) {
                        this.a[n2] = n11 | Integer.MIN_VALUE;
                        n12 = vector3.size();
                        this.a[n11] = new String[n12];
                        this.c[n11] = new short[n12];
                        for (int i4 = 0; i4 < n12; ++i4) {
                            this.a[n11][i4] = vector3.elementAt(i4).toString();
                            this.c[n11][i4] = (Short)vector4.elementAt(i4);
                        }
                        if (n10 >= n12) continue;
                        n10 = n12;
                        continue;
                    }
                    --n2;
                    continue;
                }
                --n2;
                continue;
            }
            this.a[n2] = -2;
            this.b[n2] = null;
            this.c[n2] = null;
            for (int i5 = 0; i5 < this.a.length; ++i5) {
                if (!this.a[i5].equals(string8)) continue;
                this.a[n2] = i5;
                String string10 = "MG_BUYURI_" + string8;
                this.b[n2] = SDKMIDlet.a(string10);
                string10 = "MG_CATURI_" + string8;
                this.c[n2] = SDKMIDlet.a(string10);
                if (this.b[n2] != null) {
                    this.b[n2] = this.b[n2].trim();
                }
                if (this.c[n2] == null) break;
                this.c[n2] = this.c[n2].trim();
                break;
            }
            if (this.a[n2] != -2) continue;
            --n2;
        }
        this.b = new c[n10];
        this.a = new e[n10];
    }

    public final boolean b() {
        return this.a;
    }

    public final void a() {
        switch (this.f) {
            case 3: {
                if ((this.d & 0x804) != 0 && this.a.length > 0) {
                    --this.e;
                    if (this.e < 0) {
                        this.e = this.a.length - 1;
                    }
                    this.a(this.e);
                } else if ((this.d & 0x2008) != 0 && this.a.length > 0) {
                    ++this.e;
                    this.e %= this.a.length;
                    this.a(this.e);
                } else if ((this.d & 0x80040) != 0) {
                    this.a = false;
                    this.f();
                } else if ((this.d & 0x201) != 0) {
                    --this.i;
                    this.E = 0;
                    if (this.i < 0) {
                        this.i = this.C - 1;
                    }
                    this.c();
                } else if ((this.d & 0x8002) != 0) {
                    ++this.i;
                    this.i %= this.C;
                    this.E = 0;
                    this.c();
                } else if ((this.d & 0x1030) != 0) {
                    Object object = null;
                    if (this.c == 2 || this.c == 3) {
                        object = "MG_BUYURI_" + this.a[this.a[this.e] & Integer.MAX_VALUE][this.i];
                        object = SDKMIDlet.a((String)object);
                    }
                    if (object == null) {
                        if (this.c == 2) break;
                        this.e = null;
                        object = this.a[6];
                        this.f = object;
                        this.g = this.f;
                        this.f = 2;
                    } else {
                        this.a = object;
                        this.g = this.f;
                        this.e();
                    }
                }
                if (this.i < this.j) {
                    this.j = this.i;
                    this.E = 0;
                }
                if (this.i < this.j + this.k) break;
                this.j = this.i - this.k + 1;
                this.E = 0;
                break;
            }
            case 0: {
                if ((this.d & 0x804) != 0) {
                    --this.e;
                    if (this.e < 0) {
                        this.e = this.a.length - 1;
                    }
                    this.a(this.e);
                    break;
                }
                if ((this.d & 0x2008) != 0) {
                    ++this.e;
                    this.e %= this.a.length;
                    this.a(this.e);
                    break;
                }
                if (((this.d & 0x201) != 0 || (this.d & 0x8002) != 0) && this.c && this.d) {
                    if (this.l == 1) {
                        this.l = 0;
                        break;
                    }
                    this.l = 1;
                    break;
                }
                if ((this.d & 0x80040) != 0) {
                    this.a = false;
                    this.f();
                    break;
                }
                if ((this.d & 0x1030) == 0) break;
                if (this.l == 0) {
                    this.a = this.b[this.e];
                    this.g = this.f;
                    this.e();
                    break;
                }
                if (this.l != 1) break;
                if (this.c == 1 || this.c == 3 && (this.c[this.e] == null || this.c[this.e].compareTo("") == 0)) {
                    c c2;
                    this.e = null;
                    this.f = c2 = this.a[6];
                    this.g = this.f;
                    this.f = 2;
                    break;
                }
                this.a = this.c[this.e];
                this.g = this.f;
                this.e();
                break;
            }
            case 1: {
                if ((this.d & 0x80040) != 0) {
                    c c3;
                    this.e = c3 = this.a[5];
                    this.f = c3 = this.a[6];
                    this.f = this.g;
                    break;
                }
                if ((this.d & 0x20) == 0) break;
                String string = this.a(this.a);
                p.a(string);
                break;
            }
            case 2: {
                c c4;
                if ((this.d & 0x80040) == 0) break;
                this.e = c4 = this.a[5];
                this.f = c4 = this.a[6];
                this.f = this.g;
            }
        }
        this.d = 0;
    }

    private void c() {
        c c2;
        c c3;
        String string;
        boolean bl = this.c == 2 && SDKMIDlet.a(string = "MG_BUYURI_" + this.a[this.a[this.e] & Integer.MAX_VALUE][this.i]) == null;
        this.e = bl ? null : (c3 = this.a[5]);
        this.f = c2 = this.a[6];
    }

    public final void b(n n2) {
        this.a(n2);
        switch (this.f) {
            case 0: {
                int n3;
                int n4;
                --this.q;
                if (this.q < 0) {
                    this.q = 6;
                }
                if (this.e) {
                    n2.a(-16757361);
                    p.a(this.a[4]);
                    n2.c(0, 0, this.b, this.n);
                    p.a(this.a[4]);
                    p.a(this.a[9], this.b >> 1, 1, 17);
                }
                if (this.a != null) {
                    n2.a(this.a, this.b >> 1, this.r, 17);
                    if (this.b != null) {
                        n2.a(this.b, (this.b - this.a.a.a >> 1) + this.a, this.r + this.b, 20);
                    }
                    if (this.a.length > 1) {
                        this.a(n2, (this.b - this.a.a.a >> 2) - (a >> 1), this.s);
                        this.b(n2, this.b - (this.b - this.a.a.a >> 2) + (a >> 1), this.s);
                    }
                    p.a(this.a[0]);
                    p.a(this.b, this.h, 1, this.h[0], this.b >> 1, this.o, 17);
                    p.a(this.a, this.g, 1, this.g[0], this.b >> 1, this.t, 17);
                } else {
                    p.a(this.a[0]);
                    p.a(this.b, this.b >> 1, this.r, 17);
                    p.a(this.a, this.g, 1, this.g[0], this.b >> 1, this.t, 17);
                    if (this.a.length > 1) {
                        this.a(n2, 1, this.s);
                        this.b(n2, this.b - 1, this.s);
                    }
                }
                if (this.c) {
                    n4 = this.u;
                    int n5 = this.p;
                    c c2 = this.a[10];
                    n3 = this.l == 0 ? 1 : 0;
                    this.a(n2, n4, n5, c2, n3 != 0);
                }
                if (!this.d) break;
                n3 = this.v;
                n4 = this.p;
                c c3 = this.c;
                boolean bl = this.l == 1;
                this.a(n2, n3, n4, c3, bl);
                break;
            }
            case 3: {
                int n6;
                int n7;
                --this.q;
                if (this.q < 0) {
                    this.q = 6;
                }
                if (this.e) {
                    n2.a(-16757361);
                    n2.c(0, 0, this.b, this.n);
                    p.a(this.a[4]);
                    p.a(this.d, this.b >> 1, 1, 17);
                }
                int n8 = this.z;
                p.a(this.a[1]);
                for (int i2 = this.j; i2 < this.j + this.k; ++i2) {
                    if (i2 == this.i) {
                        p.a(this.a[2]);
                    }
                    n7 = this.D;
                    if (this.a[i2] != null) {
                        n7 = this.a[i2].a.a;
                        n2.a(this.a[i2], this.A, n8 + (this.B >> 1), 6);
                    }
                    n6 = this.A + n7 + 4;
                    n7 = n8 + (this.B >> 1) - (p.b() - p.a() >> 1);
                    n2.d(n6, n7, this.b - this.A - n6, p.b() - p.a());
                    if (i2 == this.i) {
                        p.a(this.b[i2], n6 - (this.E >> 1), n7, 20);
                        ++this.E;
                        if (n6 - (this.E >> 1) + p.a(this.b[i2]) < this.b - this.A) {
                            this.E = 0;
                        }
                    } else {
                        p.a(this.b[i2], n6, n7, 20);
                    }
                    n2.d(0, 0, this.b, this.a);
                    n8 += this.B;
                    if (i2 != this.i) continue;
                    p.a(this.a[1]);
                }
                if (this.j > 0) {
                    n6 = this.b >> 1;
                    n7 = this.y - (this.q >> 1);
                    n2.a(-16757361);
                    n2.b(n6, n7, n6 - a, n7 + a, n6 + a, n7 + a);
                }
                if (this.j + this.k < this.C) {
                    n6 = this.b >> 1;
                    n7 = this.x - (6 - (this.q >> 1));
                    n2.a(-16757361);
                    n2.b(n6, n7, n6 - a, n7 - a, n6 + a, n7 - a);
                }
                if (this.a.length <= 1) break;
                this.a(n2, this.A - a >> 1, this.a >> 1);
                this.b(n2, this.b - (this.A - a >> 1), this.a >> 1);
                break;
            }
            case 2: {
                p.a(this.a[0]);
                p.a(this.a[0], this.e, 1, this.e[0], this.b >> 1, this.a - this.w >> 1, 3);
                break;
            }
            case 1: {
                p.a(this.a[0]);
                p.a(this.a[4], this.f, 1, this.f[0], this.b >> 1, this.a - this.w >> 1, 3);
            }
        }
        if (this.b) {
            p.a(this.a[3]);
            if (this.e != null) {
                p.a(this.e, 2, this.a + p.a() - 2, 36);
            }
            if (this.f != null) {
                p.a(this.f, this.b - 2, this.a + p.a() - 2, 40);
            }
        }
    }

    public final byte a(int n2) {
        if (this.a) {
            this.d |= n2;
        }
        return 0;
    }

    protected void a(n n2) {
        n2.d(0, 0, this.b, this.a);
        n2.a(-1);
        n2.c(0, 0, this.b, this.a);
    }

    public final void a(String string) {
        if (this.a == null) {
            this.b();
        }
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            this.a[i2] = p.a((int)this.d[i2], null);
        }
        this.b = string;
        this.e = 0;
        this.a = true;
        this.f = 0;
        this.a(0);
        p.a(this.a[0]);
        this.e = p.a(this.a[0], null, this.b - 10, (short)124);
        this.f = p.a(this.a[4], null, this.b - 10, (short)124);
    }

    private void a(int n2) {
        if (this.a[n2] == -1 || (this.a[n2] & Integer.MIN_VALUE) == 0) {
            c c2;
            c c3;
            int n3 = n2;
            o o2 = this;
            o2.h = o2.a[n3];
            if (o2.h != -1) {
                try {
                    o2.a = p.a("/mgi" + o2.a[o2.h].toLowerCase() + ".png");
                }
                catch (Exception exception) {
                    o2.a = null;
                }
                int n4 = p.c();
                o2.b = null;
                o2.a = (short)-1;
                o2.b = (short)-1;
                if (o2.a[n4] != null && o2.a[n4][o2.h] != -1) {
                    try {
                        o2.b = p.a("/mgi" + o2.a[o2.h].toLowerCase() + "_" + p.c() + ".png");
                        o2.a = o2.a[n4][o2.h];
                        o2.b = o2.b[n4][o2.h];
                    }
                    catch (Exception exception) {}
                }
                o2.a = p.a((int)o2.c[o2.h], null);
                o2.c = p.a((int)o2.b[o2.h], null);
                o2.b = p.a((int)o2.a[o2.h], null);
                switch (o2.c) {
                    case 1: {
                        o2.c = false;
                        o2.d = true;
                        o2.l = 1;
                        break;
                    }
                    case 2: {
                        n4 = o2.b[n3] != null && o2.b[n3].compareTo("") != 0 ? 1 : 0;
                        o2.c = n4;
                        n4 = o2.c[n3] != null && o2.c[n3].compareTo("") != 0 ? 1 : 0;
                        o2.d = n4;
                        if (o2.c) {
                            o2.l = 0;
                            break;
                        }
                        if (o2.d) {
                            o2.l = 1;
                            break;
                        }
                        o2.l = 2;
                        break;
                    }
                    case 3: {
                        o2.d = true;
                        if (o2.b[n3] != null && o2.b[n3].compareTo("") != 0) {
                            o2.c = true;
                            o2.l = 0;
                            break;
                        }
                        o2.c = false;
                        o2.l = 1;
                    }
                }
            } else {
                try {
                    o2.a = p.a("/mgi" + "LOG".toLowerCase() + ".png");
                }
                catch (Exception exception) {
                    o2.b = null;
                    o2.a = (short)-1;
                    o2.b = (short)-1;
                }
                o2.a = o2.a[1];
                o2.c = o2.a[2];
                o2.b = o2.a[3];
                o2.c = false;
                if (o2.c == 2 && (o2.c[n3] == null || o2.c[n3].compareTo("") == 0)) {
                    o2.d = false;
                    o2.l = 2;
                } else {
                    o2.d = true;
                    o2.l = 1;
                }
            }
            o2.e = o2.l != 2 ? (c3 = o2.a[5]) : null;
            o2.f = c2 = o2.a[6];
            o2.p = (o2.b << 2) / 5;
            n3 = o2.a;
            o2.w = 0;
            if (o2.b) {
                p.a(o2.a[3]);
                o2.w = p.b() - p.a();
                n3 -= o2.w;
            }
            int n5 = n3 + -2;
            if (o2.d) {
                n5 -= 5;
                if (o2.l == 1) {
                    p.a(o2.a[2]);
                } else {
                    p.a(o2.a[1]);
                }
                o2.v = n5 -= p.b() - p.a();
                n3 = p.a(o2.c) + 6 + p.b() - p.a() + 6;
                if (n3 > o2.p) {
                    o2.p = n3;
                }
            }
            if (o2.c) {
                n5 -= 5;
                if (o2.l == 0) {
                    p.a(o2.a[2]);
                } else {
                    p.a(o2.a[1]);
                }
                o2.u = n5 -= p.b() - p.a();
                n3 = p.a(o2.a[10]) + 6 + p.b() - p.a() + 6;
                if (n3 > o2.p) {
                    o2.p = n3;
                }
            }
            if (o2.d && o2.c) {
                o2.u = n5 -= 2;
            }
            n5 -= 2;
            if (o2.a != null) {
                p.a(o2.a[0]);
                o2.g = p.a(o2.a, null, o2.b - 2, (short)124);
                o2.h = p.a(o2.b, null, o2.b - 2, (short)124);
                o2.t = n5 = n5 - (o2.g[0] * p.b() - p.a()) + -5;
                o2.o = n5 -= o2.h[0] * p.b() - p.a();
                o2.r = n5 -= o2.a.a.b;
                o2.s = o2.r + (o2.a.a.b >> 1);
            } else {
                p.a(o2.a[0]);
                o2.g = p.a(o2.a, null, o2.b - (b >> 1), (short)124);
                o2.t = n5 -= o2.g[0] * p.b();
                o2.r = n5 -= p.b();
                o2.s = o2.r + (p.b() - p.a() >> 1);
            }
            int n6 = n5 + -1;
            p.a(o2.a[4]);
            o2.n = p.b() - p.a() + 1 + 1;
            if (n6 >= o2.n) {
                o2.e = true;
                n6 -= o2.n;
            } else {
                o2.e = false;
            }
            if (n6 > 0) {
                int n7 = n6 * 3 / 10;
                n3 = n6 >> 2;
                o2.r -= n6 - n7;
                o2.s -= n6 - n7;
                o2.t -= n6 - n7 - n3;
                o2.o -= n6 - n7 - n3;
                n5 = n6 >> 2;
                o2.u -= n6 - n7 - n3 - n5;
                o2.v -= n6 - n7 - n3 - n5;
            }
            this.f = 0;
            return;
        }
        this.m = this.a[n2] & Integer.MAX_VALUE;
        for (n2 = 0; n2 < this.a.length; ++n2) {
            this.a[n2] = null;
        }
        for (n2 = 0; n2 < this.c[this.m].length; ++n2) {
            this.b[n2] = p.a((int)this.c[this.m][n2], null);
            try {
                this.a[n2] = p.a("/mgc" + this.a[this.m][n2].toLowerCase() + ".png");
                continue;
            }
            catch (Exception exception) {}
        }
        this.d = p.a((int)this.i[this.m], null);
        this.i = 0;
        this.j = 0;
        this.E = 0;
        this.C = this.c[this.m].length;
        this.c();
        this.d();
        this.f = 3;
    }

    private void d() {
        int n2 = this.a;
        if (this.b) {
            p.a(this.a[3]);
            this.w = p.b() - p.a();
            n2 -= this.w;
        }
        p.a(this.a[4]);
        this.n = p.b() - p.a() + 1 + 1;
        this.e = true;
        int n3 = n2 + -2 + -1 - (a << 1) + -8 - this.n;
        p.a(this.a[0]);
        n2 = p.b() - p.a();
        this.B = this.a[0] != null && this.a[0].a.b > n2 ? (int)this.a[0].a.b : n2;
        this.B += 4;
        n2 = n3 / this.B;
        if (n2 >= this.C) {
            n2 = this.C;
        }
        this.k = n2;
        if ((n3 -= this.k * (this.B + 1)) < 0) {
            n3 = 0;
        }
        this.x = this.a - this.w - 2 - (n3 >> 2) + 3;
        this.y = this.n + 1 + (n3 >> 2) + 3;
        this.z = this.n + 1 + a + (n3 >> 1) + 3 + 1;
        n3 = 0;
        p.a(this.a[1]);
        for (int i2 = 0; i2 < this.C; ++i2) {
            if (i2 == this.i) {
                p.a(this.a[2]);
            }
            n2 = p.a(this.b[i2]);
            if (i2 == this.i) {
                p.a(this.a[1]);
            }
            n2 += 4;
            n2 = this.a[i2] == null ? (n2 += this.D) : (n2 += this.a[i2].a.a);
            if (n2 <= n3) continue;
            n3 = n2;
        }
        this.A = this.b - n3 >> 1;
        if (this.A <= a + 6 + 1) {
            this.A = a + 6 + 1;
        }
    }

    private String a(String string) {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2 = stringBuffer;
        stringBuffer = new StringBuffer(string);
        if (string.indexOf(63) == -1) {
            stringBuffer2.append('?');
        } else {
            stringBuffer2.append('&');
        }
        stringBuffer2.append("ctx");
        stringBuffer2.append('=');
        stringBuffer2.append(this.b);
        stringBuffer2.append('&');
        stringBuffer2.append("lng");
        stringBuffer2.append('=');
        stringBuffer2.append(ae.a().a[p.c()]);
        for (int i2 = 1; i2 < 10; ++i2) {
            string = SDKMIDlet.a("MG_REPORTING_NAME" + i2);
            if (string == null) continue;
            stringBuffer2.append('&');
            stringBuffer2.append(string);
            stringBuffer2.append('=');
            stringBuffer2.append(SDKMIDlet.a("MG_REPORTING_VALUE" + i2));
        }
        return stringBuffer2.toString();
    }

    private void e() {
        c c2;
        ae.a();
        this.e = c2 = this.a[7];
        this.f = c2 = this.a[8];
        this.f = 1;
    }

    private void f() {
        int n2;
        this.e = null;
        this.f = null;
        this.g = null;
        this.a = null;
        this.b = null;
        this.a = null;
        this.b = null;
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        for (n2 = 0; n2 < this.a.length; ++n2) {
            this.a[n2] = null;
        }
        for (n2 = 0; n2 < this.b.length; ++n2) {
            this.b[n2] = null;
        }
    }

    private void a(n n2, int n3, int n4) {
        int n5 = n3 + (this.q >> 1);
        n2.a(-16757361);
        for (n3 = 0; n3 <= a; ++n3) {
            n2.a(n5 + n3, n4 - n3, n5 + n3, n4 + n3);
        }
        n2.a(-9462329);
        n2.a(n5, n4, n5 + a, n4 - a);
        n2.a(n5, n4, n5 + a, n4 + a);
        n2.a(n5 + a, n4 - a, n5 + a, n4 + a);
    }

    private void b(n n2, int n3, int n4) {
        int n5 = n3 - (this.q >> 1);
        n2.a(-16757361);
        for (n3 = 0; n3 <= a; ++n3) {
            n2.a(n5 - n3, n4 - n3, n5 - n3, n4 + n3);
        }
        n2.a(-9462329);
        n2.a(n5, n4, n5 - a, n4 - a);
        n2.a(n5, n4, n5 - a, n4 + a);
        n2.a(n5 - a, n4 - a, n5 - a, n4 + a);
    }

    private void a(n n2, int n3, int n4, c c2, boolean n5) {
        int n6 = 0;
        if (n5 != 0) {
            p.a(this.a[2]);
            if (this.a[2] == this.a[1]) {
                n6 = 1;
            }
        } else {
            p.a(this.a[1]);
        }
        int n7 = p.b() - p.a();
        n5 = n7 + 3 + 2;
        int n8 = this.b - n4 >> 1;
        n2.a(-16777216);
        n2.c(n8, n3, n4, n5);
        n2.a(-16757361);
        n2.c(n8, n3, n4 - 1, n5 - 1);
        n2.a(-9462329);
        n2.a(n8 + 1, n3 + 1, n8 + n4 - 2, n3 + 1);
        n2.a(n8 + 1, n3 + 1, n8 + 1, n3 + n5 - 3);
        if (n6 != 0) {
            n2.a(-1);
            n8 = n8 + (n7 >> 1) + 3;
            n6 = n3 + (n5 >> 1);
            n7 = (n7 >> 1) - 1;
            for (n5 = 0; n5 <= n7; ++n5) {
                n2.a(n8 - n5, n6 - n5, n8 - n5, n6 + n5);
            }
        }
        p.a(c2, this.b >> 1, n3 + 2, 17);
    }
}

