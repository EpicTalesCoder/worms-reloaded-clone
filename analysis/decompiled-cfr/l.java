/*
 * Decompiled with CFR 0.152.
 */
/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class l
extends v {
    public s a;
    public w a;
    public m a;
    public long a;
    public long b;
    public boolean a;
    public long c;

    public l() {
        ae.a(9, 0, (v)this);
        ae.a(7, 0, (v)this);
        ae.a(13, 0, (v)this);
        ae.a(14, 0, (v)this);
        ae.a(10, 0, (v)this);
        ae.a(this);
        for (int n2 = 0; n2 < 149; n2 = (int)((short)(n2 + 1))) {
            if (((v)this).a[n2] != 0) continue;
            ((v)this).a[n2] = n2;
        }
        i i2 = ae.a().a;
        if (!i2.a) {
            i2.a = true;
        }
        this.a = new m();
        this.a = new s();
        this.a = new y(this.a);
        this.a.a();
        this.b = System.currentTimeMillis();
    }
}

