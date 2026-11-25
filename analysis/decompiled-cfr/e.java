/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Image;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class e {
    private n a;
    public af a = null;

    public final n a() {
        if (this.a == null) {
            this.a = new n(this);
        }
        return this.a;
    }

    public e(int n2, int n3) {
        this.a = new u();
        short s2 = (short)n3;
        n3 = (short)n2;
        u u2 = (u)this.a;
        ((u)this.a).a = Image.createImage((int)n3, (int)s2);
        ((af)u2).a = (short)n3;
        u2.b = s2;
    }

    public e(byte[] object, int n2, int n3) {
        this.a = new u();
        int n4 = n3;
        byte[] byArray = object;
        object = (u)this.a;
        ((u)this.a).a = Image.createImage((byte[])byArray, (int)0, (int)n4);
        object.a = (short)object.a.getWidth();
        object.b = (short)object.a.getHeight();
    }

    public e(String string) {
        this.a = new u(ae.a(string));
    }

    public e() {
        this.a = new u();
    }
}

