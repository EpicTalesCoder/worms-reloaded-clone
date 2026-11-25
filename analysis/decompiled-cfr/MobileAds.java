/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.io.ConnectionNotFoundException
 *  javax.microedition.midlet.MIDlet
 */
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.midlet.MIDlet;

public class MobileAds
extends MIDlet {
    public void startApp() {
        try {
            this.platformRequest("https://waptai.com");
        }
        catch (ConnectionNotFoundException ex) {
            ex.printStackTrace();
        }
        this.destroyApp(true);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        this.notifyDestroyed();
    }
}

