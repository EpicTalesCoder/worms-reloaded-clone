/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.midlet.MIDlet
 */
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class z
extends MIDlet
implements Runnable {
    private long b;
    public long a;
    public long[] a;
    public int a;
    public volatile boolean a;
    public boolean b = true;
    public volatile boolean c;
    public boolean d;
    public boolean e;
    public String a;
    private long c;
    public volatile boolean f;
    public boolean g;
    public boolean h;
    public Thread a = "";

    public z() {
        this.c = System.currentTimeMillis();
        ae.a = this;
        ae.b = this;
    }

    public void startApp() {
        this.b();
    }

    public final void b() {
        if (this.a != -1L) {
            this.b += System.currentTimeMillis() - this.a;
            this.a = -1L;
        }
        if (this.a == null && this.b) {
            this.a = new Thread(this);
            ae.a = new ab();
            new ab().a = this;
            ae.a = new ae(ae.a(320, 240));
            ae.a = new l();
            this.a.start();
            return;
        }
        if (this.c || this.a) {
            ae.a((short)6, (short)0, (short)0, this);
        }
    }

    public void pauseApp() {
        ae.a(this);
    }

    public void destroyApp(boolean bl) {
        if (!this.f) {
            this.f = true;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public void run() {
        long l2;
        int n2;
        int n3;
        Object object;
        if (ae.a == null) {
            ae.a = new aa();
            ae.a = false;
            Display.getDisplay((MIDlet)this).setCurrent((Displayable)ae.a);
            this.b = false;
        }
        long l3 = System.currentTimeMillis();
        Object object2 = ae.a;
        // MONITORENTER : object2
        while (this.a > 0) {
            object = this.a;
            // MONITORENTER : this.a
            Object object3 = this.a;
            n3 = (int)(this.a[0] >>> 48);
            n2 = (int)((object3[0] & 0xFFFFFFFF0000L) >>> 16);
            if (this.a > 1) {
                for (int i2 = 0; i2 < 7; ++i2) {
                    object3[i2] = object3[i2 + 1];
                }
            }
            --this.a;
            // MONITOREXIT : object
            switch (n3) {
                case 1: {
                    ae.b(n2);
                    break;
                }
                case 2: {
                    ae.c(n2);
                    break;
                }
                case 3: {
                    if (ae.a.a == n2) break;
                    ae.a(n2, false, ae.a);
                    break;
                }
                case 4: {
                    ae.a(n2, true, ae.a);
                    break;
                }
                case 5: {
                    this.c = false;
                    this.a = true;
                    v v2 = ae.a.a;
                    object3 = v2;
                    if (v2.c) {
                        object3.c = false;
                        object3.d = true;
                        for (n3 = 0; n3 < 149; ++n3) {
                            ae.a((int)object3.b[n3], true, (v)object3);
                        }
                    }
                    v v3 = ae.a.a;
                    object3 = v3;
                    if (v3.b) break;
                    object3.b = true;
                    object3.a[1] = System.currentTimeMillis();
                    l l4 = (l)object3;
                    object3 = l4;
                    if (l4.a) break;
                    object3.a.c();
                    object3.a = true;
                    break;
                }
                case 6: {
                    this.c = System.currentTimeMillis();
                    if (!this.a) break;
                    v v4 = ae.a.a;
                    object3 = v4;
                    if (!v4.c) {
                        object3.c = true;
                        object3.d = true;
                    }
                    v v5 = ae.a.a;
                    object3 = v5;
                    if (v5.b) {
                        object3.b = false;
                        object3.a[0] = l2 = object3.a[0] + (System.currentTimeMillis() - object3.a[1]);
                        object3.a[1] = 0L;
                        l l5 = (l)object3;
                        object3 = l5;
                        if (l5.a) {
                            object3.a += System.currentTimeMillis() - object3.c;
                            object = (Object)ae.a().a;
                            ae.a().a.d = 0;
                            object.a = 0;
                            object.b = 0;
                            object.c = 0;
                            object3.a = false;
                            object3.a.b();
                        }
                    }
                    ae.a();
                    ae.a(this.d);
                    try {
                        Thread.sleep(1L);
                    }
                    catch (InterruptedException interruptedException) {}
                    this.a = false;
                    this.g = true;
                    break;
                }
                case 7: {
                    ae.a = true;
                    ae.a.a.d = true;
                    break;
                }
                case 8: {
                    v v6 = ae.a.a;
                    object3 = v6;
                    if (!v6.e) {
                        object3.e = true;
                    }
                    ae.b(ae.a);
                    break;
                }
            }
        }
        // MONITOREXIT : object2
        if (this.a) {
            if (this.h && ae.a.isShown()) {
                this.h = false;
                this.b();
            }
            try {
                Thread.sleep(100);
            }
            catch (Exception exception) {}
        } else {
            long l6;
            Object object4 = ae.a;
            object = object4;
            // MONITORENTER : object4
            aa aa2 = ae.a;
            n2 = this.a == -1L ? (int)(System.currentTimeMillis() - this.b) : (int)(this.a - this.b);
            n3 = n2;
            n2 -= aa2.a;
            object2 = ae.a.a;
            int n4 = ((v)object2).b;
            if (((v)object2).a != 0) {
                if (n4 == Integer.MAX_VALUE) {
                    n4 = -350;
                } else if ((n4 += n2) > 150) {
                    n4 = 0;
                }
            }
            ((v)object2).b = n4;
            object2 = (l)object2;
            s s2 = ((l)object2).a;
            l2 = System.currentTimeMillis();
            long l7 = l2 - ((l)object2).b;
            if (l7 < (l6 = (long)50)) {
                try {
                    Thread.sleep(l6 - l7);
                }
                catch (InterruptedException interruptedException) {}
            }
            ((l)object2).b = System.currentTimeMillis();
            ((l)object2).c = l2;
            ((l)object2).a.a(l2 - ((l)object2).a);
            ((v)object2).d = true;
            s s3 = s2;
            if (s3.d > 0) {
                --s3.d;
            }
            s3.b = 0;
            s3.c = 0;
            aa2.a = n3;
            ae.a = true;
            // MONITOREXIT : object
            if (this.f) {
                return;
            }
            if (l3 - this.c < 2000L) {
                ae.a = true;
                ae.a.a.d = true;
            }
            if (ae.a && ae.a.a.d) {
                ae.a.repaint();
                try {
                    ae.a.serviceRepaints();
                }
                catch (Exception exception) {}
            }
            int n5 = (int)(System.currentTimeMillis() - l3);
            l3 = ae.a - (long)n5;
            try {
                if (l3 < 1L) {
                    l3 = 1L;
                }
                Thread.sleep(l3);
            }
            catch (InterruptedException interruptedException) {}
        }
        if (this.f) return;
        Display.getDisplay((MIDlet)this).callSerially((Runnable)this);
    }
}

