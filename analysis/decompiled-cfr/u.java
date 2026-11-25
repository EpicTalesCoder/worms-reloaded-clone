/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Image;

public final class u
extends af {
    public Image a;

    public u() {
    }

    public u(x object) {
        String string;
        String string2 = string;
        string = new String(ae.a((x)object));
        object = string2;
        if (string2.charAt(0) != '/') {
            object = '/' + string2;
        }
        try {
            this.a = Image.createImage((String)object);
        }
        catch (Exception exception) {}
        if (this.a != null) {
            ((af)this).a = (short)this.a.getWidth();
            this.b = (short)this.a.getHeight();
        }
    }

    public final Image a() {
        return this.a;
    }
}

