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
public final class aa
extends Canvas {
    private boolean a;
    public int a;
    private ae a;

    public aa() {
        this.setFullScreenMode(true);
        this.a = ae.a;
    }

    public final void hideNotify() {
        ae.a(ae.b);
        ae.b.h = true;
    }

    public final void showNotify() {
        ae.b.b();
        ae.a((short)7, (short)0, (short)0, ae.b);
    }

    public final void sizeChanged(int n2, int n3) {
    }

    public final void keyPressed(int n2) {
        ae.a((short)1, n2 >> 16, (short)n2, ae.b);
    }

    public final void keyReleased(int n2) {
        ae.a((short)2, n2 >> 16, (short)n2, ae.b);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void paint(Graphics object) {
        Object object2 = ae.a;
        synchronized (object2) {
            if (!canvas.a) {
                canvas.a = true;
            }
            canvas.a.a = object;
            ae ae2 = ae.a;
            if (ae2.a > 0) {
                --ae2.a;
                short[] sArray = new short[3];
                object = sArray;
                sArray[0] = 0;
                object[1] = (Graphics)false;
                object[2] = (Graphics)false;
                short s2 = ae.a.a[0];
                short s3 = ae.a.a[1];
                short s4 = ae.a.a[2];
                int n2 = ae.a.a[3];
                int n3 = ae2.a.getColor();
                ae2.a.setColor((int)object[0], (int)object[1], (int)object[2]);
                ae2.a.fillRect((int)s2, (int)s3, (int)s4, n2);
                n2 = n3 & 0xFFFFFF;
                ae2.a.setColor(n2);
            }
            object = ae2;
            Canvas canvas = ((l)ae.a.a).a;
            ae2 = object;
            object = canvas.a;
            if (object.a) {
                object.a = null;
                object.a = false;
            }
            object.a = ae2;
            ae.a().a.a(canvas.a);
            ae.a.a.d = false;
        }
        ae.a = false;
        if (ae.b.g) {
            ae.a = true;
            ae.a.a.d = true;
            ae.b.g = false;
        }
    }
}

