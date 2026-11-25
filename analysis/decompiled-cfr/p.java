/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Font
 */
import com.ea.sdk.SDKMIDlet;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Font;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class p {
    public static Object a(e e2, byte[] object) {
        Object[] objectArray = object;
        object = ae.a();
        int n2 = objectArray[0];
        int n3 = (n2 & 0x80) != 0 ? 1 : 0;
        int n4 = n3;
        n3 = (n2 & 0x40) != 0 ? 1 : 0;
        n2 = n3;
        byte[] byArray = new byte[12];
        System.arraycopy(objectArray, 1, byArray, 0, 7);
        byArray[7] = 0;
        byArray[8] = byArray[0];
        byArray[9] = byArray[1];
        byArray[10] = n4 != 0 ? 2 : 1;
        if (n2 != 0) {
            byArray[10] = (byte)(byArray[10] | 0x80);
        }
        short s2 = ae.a(objectArray, 8);
        n3 = ae.a(objectArray, 10);
        int n5 = 12;
        short[] sArray = new short[s2];
        for (n2 = 0; n2 < s2; ++n2) {
            sArray[n2] = ae.a(objectArray, n5);
            n5 += 2;
        }
        short[] sArray2 = new short[s2 + 1];
        for (n2 = 0; n2 < s2; ++n2) {
            sArray2[n2] = ae.a(objectArray, n5);
            n5 += 2;
        }
        sArray2[s2] = -1;
        n3 = (s2 << 1) + n3 * 6;
        byte[] byArray2 = new byte[n3];
        System.arraycopy(objectArray, n5, byArray2, 0, n3);
        short[] sArray3 = new short[256];
        for (n4 = 0; n4 < 128; n4 = (int)((short)(n4 + 1))) {
            sArray3[n4] = (short)ae.a(sArray, n4, 0, s2 - 1, sArray.length, (m)object);
        }
        byArray[11] = 1;
        Object[] objectArray2 = new Object[6];
        Object[] objectArray3 = objectArray2;
        objectArray2[0] = byArray;
        objectArray3[1] = sArray;
        objectArray3[2] = sArray3;
        objectArray3[3] = sArray2;
        objectArray3[4] = byArray2;
        objectArray3[5] = null;
        object = objectArray3;
        Object[] objectArray4 = new Object[8];
        objectArray = objectArray4;
        objectArray4[0] = e2;
        objectArray[1] = object[0];
        objectArray[2] = object[1];
        objectArray[3] = object[2];
        objectArray[4] = object[3];
        objectArray[5] = object[4];
        objectArray[6] = object[5];
        objectArray[7] = (byte)new Integer(-1);
        return objectArray;
    }

    public static void a(Object object) {
        m m2 = ae.a();
        ae.a().a.b = null;
        m2.a.a = null;
        m2.a.a = null;
        m2.a.b = null;
        m2.a.a = null;
        m2.a.c = null;
        m2.a.b = null;
        m2.a.a = null;
        if (object != null) {
            if (object instanceof Font) {
                m2.a.a = new byte[12];
                m2.a.a[0] = 1;
                m2.a.a[1] = 2;
                m2.a.a[7] = 0;
                m2.a.a[8] = 1;
                m2.a.a[9] = 2;
                m2.a.a[10] = 4;
                if (m2.a != null) {
                    Object object2 = object;
                    m2.a.a.a(object2);
                }
            } else {
                m2.a.a = ((Object[])object)[0];
                m2.a.a = (byte[])((Object[])object)[1];
                m2.a.b = (Integer)((Object[])object)[7];
                m2.a.b = (short[])((Object[])object)[2];
                m2.a.a = (short[])((Object[])object)[3];
                m2.a.c = (short[])((Object[])object)[4];
                m2.a.b = (byte[])((Object[])object)[5];
                m2.a.a = (int[])((Object[])object)[6];
                if (m2.a != null && m2.a.a[11] != m2.a[m2.d] && m2.a[m2.d] > 1) {
                    m m3 = m2;
                    int n2 = m3.a.b.length;
                    for (int n3 = 128; n3 < 256; n3 = (int)((short)(n3 + 1))) {
                        m3.a.a[n3] = (short)ae.a(m3.a.b, m3.d[n3 - 128], 0, n2 - 1, n2, m3);
                    }
                    m3.a.a[11] = m3.a[m3.d];
                }
            }
        }
        m2.a.b = object;
    }

    public static Object a(e objectArray) {
        m m2 = ae.a();
        ae.a().a.b = null;
        m2.a.a = objectArray;
        m2.a.b = -1;
        Object[] objectArray2 = new Object[8];
        objectArray = objectArray2;
        objectArray2[0] = m2.a.a;
        objectArray[1] = m2.a.a;
        objectArray[2] = m2.a.b;
        objectArray[3] = m2.a.a;
        objectArray[4] = m2.a.c;
        objectArray[5] = m2.a.b;
        objectArray[6] = m2.a.a;
        objectArray[7] = new Integer(m2.a.b);
        m2.a.b = objectArray;
        return ae.a().a.b;
    }

    public static Object a() {
        return ae.a().a.b;
    }

    public static int a() {
        return ae.a().a.a[2];
    }

    public static int b() {
        m m2 = ae.a();
        if ((m2.a.a[10] & 4) != 0) {
            return ((Font)m2.a.b).getHeight();
        }
        return (m2.a.a[3] & 0xFF) + (m2.a.a[4] & 0xFF) + m2.a.a[2];
    }

    public static void a(c c2, int n2, int n3, int n4) {
        p.a(c2, 0, c2.b, n2, n3, n4);
    }

    private static void a(c c2, int n2, int n3, int n4, int n5, int n6) {
        short[] sArray = new short[]{0, 0};
        m m2 = ae.a();
        if ((m2.a.a[10] & 4) != 0) {
            if (m2.a == null) {
                m2.a = new StringBuffer();
            }
            m2.a.setLength(0);
            for (int i2 = n2; i2 < n2 + n3; ++i2) {
                char c3 = c2.a(i2);
                if (c3 == '\u00ad' && i2 != n2 + n3 - 1) continue;
                m2.a.append(c3);
            }
            int n7 = n4;
            int n8 = n5;
            n5 = n6;
            n4 = n8;
            n3 = n7;
            String string = m2.a.toString();
            m2.a.a.a(string, n3, n4, n5);
            return;
        }
        int n9 = m2.a.a[3] & 0xFF;
        int n10 = p.b();
        int n11 = m2.a.a[8];
        int n12 = m2.a.a[9];
        int n13 = (m2.a.a[10] & 2) != 0 ? 1 : 0;
        int n14 = n13;
        int n15 = 0;
        n13 = 0;
        if ((n6 & 8) != 0) {
            n15 = 0 - p.a(c2, n2, n3);
        } else if ((n6 & 1) != 0) {
            n15 = 0 - (p.a(c2, n2, n3) >> 1);
        }
        if ((n6 & 0x40) != 0) {
            n13 = 0 - n9;
        } else if ((n6 & 0x20) != 0) {
            n13 = 0 - n10;
        }
        if (n11 == 1) {
            n4 += n15;
            n5 += n13;
        } else if (n11 == 4) {
            n4 -= n15;
            n5 -= n13;
        } else if (n11 == 2) {
            n4 -= n13;
            n5 += n15;
        } else if (n11 == 8) {
            n4 -= n13;
            n5 -= n15;
        }
        m2.a[0] = n4;
        m2.a[1] = n5;
        int n16 = c2.a & 0x3F;
        n13 = (n11 & 5) != 0 ? (n11 & 4) >> 1 : ((n12 & 1) << 1) + ((~n12 & 2) >> 1);
        byte by = (byte)n13;
        n13 = (n11 & 0xA) != 0 ? (n11 & 8) >> 2 : (n12 & 2) + (~n12 & 1);
        byte by2 = (byte)n13;
        int n17 = m2.a.a.b();
        for (int i3 = n2; i3 < n2 + n3; ++i3) {
            n11 = c2.a(i3);
            if (n11 == 173 && i3 != n2 + n3 - 1) continue;
            n13 = m2.a((short)n11, n16);
            n15 = m2.a((short)n13);
            if (n13 == -1) {
                if (n11 != 32) {
                    m2.a.a(65280);
                    m2.a.c(m2.a[0], m2.a[1], n15, n10);
                    m2.a.a(0xFF0000);
                    m2.a.a(m2.a[0], m2.a[1], m2.a[0] + n15, m2.a[1] + n10);
                    m2.a.a(m2.a[0], m2.a[1] + n10, m2.a[0] + n15, m2.a[1]);
                }
                m2.a(m2.a, n15);
                continue;
            }
            n13 = n13 + 1;
            short s2 = (short)(n13 + 1);
            int n18 = m2.a.b[n13] & 0xFF;
            for (n13 = 0; n13 < n18; ++n13) {
                int n19;
                if (n14 != 0) continue;
                n12 = m2.a.b[s2] & 0xFF;
                s2 = (short)(s2 + 1);
                n6 = m2.a.b[s2] & 0xFF;
                s2 = (short)(s2 + 1);
                n5 = m2.a.b[s2];
                s2 = (short)(s2 + 1);
                byte by3 = m2.a.b[s2];
                s2 = (short)(s2 + 1);
                n4 = m2.a.b[s2] & 0xFF;
                short s3 = (short)(s2 + 1);
                s2 = (short)(s3 + 1);
                n11 = m2.a.b[s3] & 0xFF;
                m2.b[0] = n5;
                m2.b[1] = n19 = by3 - n9;
                m2.b[2] = -n5 - n4;
                m2.b[3] = n9 - by3 - n11;
                m2.a.a((e)m2.a.a, n12, n6, n4, n11, m2.a.a[7], m2.a[0] + m2.b[by], m2.a[1] + m2.b[by2], 0);
            }
            m2.a(m2.a, n15);
        }
        m2.a.a(n17);
    }

    public static void a(c c2, short[] sArray, int n2, int n3, int n4, int n5, int n6) {
        m m2 = ae.a();
        int n7 = p.b();
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        if ((n6 & 2) != 0) {
            n10 = sArray[0] * n7 - m2.a.a[2] >> 1;
            n6 ^= 2;
        } else if ((n6 & 0x20) != 0) {
            n10 = sArray[0] * n7 - m2.a.a[2];
            n6 ^= 0x20;
        }
        n6 |= 0x10;
        if (m2.a.a[9] == 1) {
            n4 -= n10;
            n8 = n7;
        } else if (m2.a.a[9] == 4) {
            n4 += n10;
            n8 = -n7;
        } else if (m2.a.a[9] == 2) {
            n5 -= n10;
            n9 = n7;
        } else {
            n5 += n10;
            n9 = -n7;
        }
        short s2 = sArray[(sArray[0] & 0xFFFF) + 2];
        for (int i2 = n2; i2 < n2 + n3; ++i2) {
            int n11 = sArray[i2] & 0xFFFF;
            n10 = (sArray[i2 + 1] & 0xFFFF) - n11;
            short s3 = c2.a((sArray[i2 + 1] & 0xFFFF) - 1);
            n7 = s3;
            if (s3 == s2 || n7 == 32) {
                --n10;
            }
            p.a(c2, n11, n10, n4, n5, n6);
            n4 += n8;
            n5 += n9;
        }
    }

    public static short[] a(c c2, short[] sArray, int n2, short n3) {
        n3 = n2;
        short[] sArray2 = sArray;
        int n4 = c2.b;
        m m2 = ae.a();
        boolean bl = false;
        if (sArray2 == null) {
            if (m2.e == null) {
                m2.e = new short[50];
                m2.b = 50;
            }
            sArray2 = m2.e;
            bl = true;
        }
        int n5 = c2.a & 0x3F;
        int n6 = 0;
        int n7 = 0;
        int n8 = -1;
        sArray2[1] = 0;
        int n9 = 2;
        for (int i2 = 0; i2 < n4; ++i2) {
            short s2 = c2.a(i2);
            if (bl && n9 + 3 >= m2.b) {
                m2.e = new short[m2.b + 50];
                ae.a(sArray2, m2.e, m2.b);
                sArray2 = m2.e;
                m2.b += 50;
            }
            if (s2 == 124) {
                sArray2[n9++] = (short)(i2 + 1);
                n6 = 0;
                n7 = 0;
                n8 = -1;
                continue;
            }
            int n10 = (m2.a.a[10] & 4) != 0 ? ((Font)m2.a.b).charWidth(c2.a(i2)) : m2.a(m2.a(s2, n5));
            n7 += n10;
            if ((n6 += n10) > n3) {
                if (n8 == -1) {
                    sArray2[n9++] = (short)i2;
                    n6 = n10;
                    n7 = 0;
                    continue;
                }
                sArray2[n9++] = (short)(n8 + 1);
                n8 = s2 == 32 || s2 == 173 ? i2 : -1;
                n6 = n7;
                n7 = 0;
                continue;
            }
            if (s2 != 32 && s2 != 173) continue;
            n8 = i2;
            n7 = 0;
        }
        sArray2[0] = (short)(n9 - 1);
        sArray2[n9] = (short)n4;
        sArray2[n9 + 1] = 124;
        if (bl) {
            short[] sArray3 = new short[n9 + 2];
            ae.a(sArray2, sArray3, n9 + 2);
            sArray2 = sArray3;
        }
        return sArray2;
    }

    public static int a(c c2) {
        return p.a(c2, 0, c2.b);
    }

    private static int a(c c2, int n2, int n3) {
        m m2 = ae.a();
        int n4 = 0;
        if ((m2.a.a[10] & 4) != 0) {
            n4 = ((Font)m2.a.b).substringWidth(c2.toString(), n2, n3);
        } else {
            int n5 = c2.a & 0x3F;
            for (int i2 = n2; i2 < n2 + n3; ++i2) {
                n4 += m2.a(m2.a(c2.a(i2), n5));
            }
        }
        return n4;
    }

    private static boolean c(int n2) {
        m m2 = ae.a();
        boolean bl = m2.c[n2];
        bl = m2.b[bl] != null && m2.d[bl] == n2;
        return bl;
    }

    public static int a(int n2) {
        m m2 = ae.a();
        SDKMIDlet sDKMIDlet = SDKMIDlet.a;
        byte[] byArray = new byte[2];
        if (p.c(n2) && !m2.b) {
            return m2.a[n2];
        }
        InputStream inputStream = null;
        try {
            int n3 = (m2.b[n2] & 0x40) == 0 ? m2.d : 0;
            int n4 = n3;
            if (m2.a == 0) {
                inputStream = sDKMIDlet.getClass().getResourceAsStream("/t_" + new String(m2.a[n4]).trim());
                for (n3 = 1; n3 < n2; ++n3) {
                    if ((m2.b[n3] & 0x40) != 0 && n4 != 0) continue;
                    long l2 = (m2.a[n3] + 1 << 1) + (m2.a[n4][n3] & 0xFFFF);
                    inputStream.skip(l2);
                }
            } else if (m2.a == 1) {
                inputStream = sDKMIDlet.getClass().getResourceAsStream("/t_" + new String(m2.a[n4]).trim() + n2);
            }
            if (m2.b[n3 = m2.c[n2]] == null) {
                m2.b[n3] = new short[m2.c[n3]];
            }
            for (int i2 = 0; i2 < m2.a[n2] + 1; ++i2) {
                byArray[0] = (byte)inputStream.read();
                byArray[1] = (byte)inputStream.read();
                m2.b[n3][i2] = ae.a(byArray, 0);
            }
            if (m2.b[n3] == null) {
                m2.b[n3] = new byte[m2.b[n3] & 0xFFFF];
            }
            int n5 = m2.a[n4][n2] & 0xFFFF;
            byte[] byArray2 = m2.b[n3];
            inputStream.read(byArray2, 0, n5);
            m2.d[n3] = (byte)n2;
            inputStream.close();
        }
        catch (IOException iOException) {}
        return m2.a[n2];
    }

    public static int a(byte[] byArray) {
        int n2;
        int n3;
        m m2 = ae.a();
        if (m2.a) {
            return m2.e;
        }
        m2.a = byArray[1];
        m2.a = (byArray[2] & 0xFF) << 16 | (byArray[3] & 0xFF) << 8 | byArray[4] & 0xFF;
        short s2 = ae.a(byArray, 5);
        m2.e = byArray[7];
        int n4 = 10;
        m2.c = (byte)(byArray[8] + 1);
        m2.b = (byte)(byArray[9] + 1);
        m2.a = new byte[m2.e];
        m2.a = new byte[m2.e][];
        m2.e = new byte[m2.e];
        m2.a = new short[m2.b];
        m2.b = new byte[m2.b];
        m2.c = new byte[m2.b];
        m2.a = new short[m2.e][];
        m2.b = new short[m2.b];
        for (n3 = 0; n3 < m2.b.length; ++n3) {
            m2.b[n3] = 0;
        }
        m2.c = new short[m2.b];
        for (n3 = 0; n3 < m2.c.length; ++n3) {
            m2.c[n3] = 0;
        }
        m2.d = new byte[m2.b];
        m2.b = new byte[m2.c][];
        m2.b = new short[m2.c][];
        for (n3 = 0; n3 < m2.c; ++n3) {
            m2.b[n3] = null;
        }
        m2.a[0] = s2;
        for (n3 = 0; n3 < m2.e; ++n3) {
            m2.a[n3] = new short[m2.b];
        }
        for (n3 = 0; n3 < m2.c; ++n3) {
            m2.b[n3] = null;
        }
        int n5 = s2 + 1;
        for (n2 = 0; n2 < m2.e; ++n2) {
            m2.a[n2] = new byte[5];
            for (n3 = 0; n3 < 5; ++n3) {
                m2.a[n2][n3] = byArray[n4 + n3];
            }
            short s3 = (short)(n4 + 5);
            n3 = (short)(s3 + 1);
            m2.a[n2] = byArray[s3];
            n4 = (short)(n3 + 1);
            m2.e[n2] = byArray[n3];
            if (s2 <= 0) continue;
            if (m2.b[0] == null) {
                m2.b[0] = new short[m2.e * n5];
            }
            for (n3 = 0; n3 < n5; ++n3) {
                m2.b[0][n2 * n5 + n3] = ae.a(byArray, n4);
                n4 = (short)(n4 + 2);
            }
        }
        if (s2 > 0) {
            n2 = m2.b[0][m2.e * n5 - 1];
            m2.b[0] = new byte[n2];
            ae.a(m2.b[0], byArray, n4, n2);
            n4 = (short)(n4 + n2);
        }
        for (n2 = 1; n2 < m2.b; ++n2) {
            m2.b[n2] = (byte)(byArray[n4] & 0xC0);
            m2.c[n2] = (byte)(byArray[n4] & 0x3F);
            n5 = n4 + 1;
            short[] sArray = m2.a;
            sArray[n2] = n4 = (int)ae.a(byArray, n5);
            n4 = (short)(n5 + 2);
            if (m2.a[n2] + 1 > m2.c[m2.c[n2]]) {
                m2.c[m2.c[n2]] = (short)(m2.a[n2] + 1);
            }
            for (n5 = 0; n5 < m2.e; ++n5) {
                m2.a[n5][n2] = ae.a(byArray, n4);
                if ((m2.a[n5][n2] & 0xFFFF) > (m2.b[m2.c[n2]] & 0xFFFF)) {
                    m2.b[m2.c[n2]] = m2.a[n5][n2];
                }
                n4 = (short)(n4 + 2);
            }
        }
        if (m2.a[m2.d] > 1) {
            m2.a(m2.a[m2.d]);
        }
        m2.a = true;
        return m2.e;
    }

    public static c a(int n2, int n3, c c2) {
        m m2 = ae.a();
        if (2 >= m2.a[0]) {
            return c2;
        }
        n3 = 2 + (m2.a[0] + 1) * n2;
        if (c2 == null) {
            c2 = new c();
        } else if ((c2.a & 0x40) != 0) {
            c2.a = null;
            c2.a = null;
        }
        c2.a = m2.b[0];
        c2.a = m2.b[0][n3];
        c2.c = c2.b = m2.b[0][n3 + 1] - m2.b[0][n3] >> 1;
        c2.a = 0;
        return c2;
    }

    public static c a(int n2, c c2) {
        int n3;
        m m2 = ae.a();
        int n4 = -1;
        int n5 = 0;
        for (n3 = 0; n3 < m2.b; ++n3) {
            if (n2 >= (n5 += m2.a[n3])) continue;
            n4 = n3;
            break;
        }
        n3 = n2 - (n5 - m2.a[n4]);
        n5 = m2.c[n4];
        if (c2 == null) {
            c2 = new c();
        } else if ((c2.a & 0x40) != 0) {
            c2.a = null;
            c2.a = null;
        }
        c2.a = m2.b[n5];
        c2.a = m2.b[n5][n3] & 0xFFFF;
        c2.b = (m2.b[n5][n3 + 1] & 0xFFFF) - (m2.b[n5][n3] & 0xFFFF);
        n3 = m2.a[m2.d];
        if ((m2.b[n4] & 0xFFFFFFC1) != 0) {
            n3 = 0;
        }
        if (n3 == 0) {
            c2.b = (short)(c2.b >> 1);
        }
        c2.a = (byte)n3;
        c2.c = c2.b;
        return c2;
    }

    public static int c() {
        return ae.a().d;
    }

    public static boolean a(int n2) {
        int n3;
        m m2 = ae.a();
        byte by = m2.d;
        if (m2.a != null && n2 >= 0 && n2 < m2.e) {
            m2.d = (byte)n2;
        }
        if (m2.d != by) {
            m2.b = true;
            for (n3 = 1; n3 < m2.b; ++n3) {
                if (!p.c(n3) || (m2.b[n3] & 0x40) != 0) continue;
                p.a(n3);
            }
            m2.b = false;
            if (m2.a[m2.d] != m2.a[by] && m2.a[m2.d] > 1) {
                m2.a(m2.a[m2.d]);
            }
        }
        n3 = m2.d == n2 ? 1 : 0;
        return n3 != 0;
    }

    public static void a(n n2) {
        ae.a().a = n2;
    }

    public static void a(byte[] byArray) {
        ae.a(1, byArray, byArray.length, ae.b());
        ae.b();
    }

    public static byte[] a(int n2) {
        n2 = ae.a(1, ae.b());
        if (n2 == -1) {
            return null;
        }
        ae.b();
        byte[] byArray = new byte[n2];
        ae.a(1, byArray);
        return byArray;
    }

    public static boolean b(int n2) {
        n2 = !ae.a(1, ae.b()) ? 1 : 0;
        return n2 != 0;
    }

    public static long a(InputStream inputStream, long l2) {
        return inputStream.skip(l2);
    }

    public static int a(InputStream inputStream, byte[] byArray, int n2, int n3) {
        return inputStream.read(byArray, n2, n3);
    }

    public static int b(int n2) {
        int n3;
        if (ae.a().a.length == 1) {
            return 0;
        }
        String string = SDKMIDlet.a("Force-Locale");
        int n4 = ae.a().a.length;
        String[] stringArray = new String[n4];
        for (n3 = 0; n3 < n4; ++n3) {
            stringArray[n3] = new String(ae.a().a[n3]);
        }
        if (n2 == -1) {
            int n5;
            if (string == null) {
                n5 = 2;
            } else if ((string = string.toLowerCase().trim()).length() < 2) {
                n5 = 2;
            } else if (string.equals("menu")) {
                n5 = 1;
            } else if (string.startsWith("auto-")) {
                n5 = 3;
                string = string.substring(5);
            } else {
                n5 = 4;
            }
            for (n3 = 0; n3 < stringArray.length; ++n3) {
                if (stringArray[n3].length() <= 2) continue;
                String string2 = stringArray[n3].substring(0, 2);
                n4 = 1;
                for (int i2 = 0; i2 < stringArray.length; ++i2) {
                    if (n3 == i2 || !stringArray[i2].startsWith(string2)) continue;
                    n4 = 0;
                    break;
                }
                if (n4 == 0) continue;
                stringArray[n3] = string2;
            }
            if (n5 == 2 || n5 == 3 || n5 == 1) {
                n2 = p.a(stringArray);
                if (n5 == 1 && n2 >= 0) {
                    n2 = -1 - n2;
                }
            }
            if (n5 == 4 || n5 == 3 && n2 == -1) {
                for (int i3 = 0; i3 < stringArray.length; ++i3) {
                    if (!string.startsWith(stringArray[i3])) continue;
                    n2 = i3;
                    break;
                }
            }
            if (n5 == 4 && n2 == -1) {
                n2 = p.a(stringArray);
            }
        }
        return n2;
    }

    public static short a(byte[] byArray, int n2) {
        return (short)((byArray[n2] & 0xFF) << 8 | byArray[n2 + 1] & 0xFF);
    }

    public static boolean a(String string) {
        ae.a();
        return ae.a(ae.a(ae.a(ae.a(new String(string)))));
    }

    public static e a(int n2, int n3) {
        return new e(n2, n3);
    }

    public static e a(byte[] byArray, int n2) {
        return new e(byArray, 0, n2);
    }

    public static e a(String string) {
        e e2;
        e e3 = e2;
        e2 = new e(string);
        if (e3.a.a() == null) {
            throw new IOException("Error creating SDKImage from a file path.");
        }
        return e3;
    }

    public static e a(b b2) {
        byte[] byArray = new byte[b2.b()];
        b2.read(byArray, 0, b2.b());
        return new e(byArray, 0, byArray.length);
    }

    public static n a() {
        return new n();
    }

    private static byte a(String[] stringArray) {
        int n2 = -1;
        String string = System.getProperty("microedition.locale");
        if (string != null) {
            String string2 = string.toLowerCase().trim();
            int n3 = string2.length();
            if (n3 < 2) {
                return -1;
            }
            for (int i2 = 0; i2 < stringArray.length; ++i2) {
                if (stringArray[i2].length() > n3 && stringArray[i2].startsWith(string2)) {
                    n2 = -1;
                    break;
                }
                if (!string2.startsWith(stringArray[i2])) continue;
                n2 = (byte)i2;
                break;
            }
        }
        return (byte)n2;
    }
}

