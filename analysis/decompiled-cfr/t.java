/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.media.Player
 */
import javax.microedition.media.Player;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class t {
    public byte[] a;
    public t a;
    public t b;
    public Player a;
    public String a;
    public t c;
    public int a;

    public t() {
    }

    private t(int n2) {
        if (n2 != 1) {
            if (n2 != 2) {
                return;
            }
        } else {
            this.a = new t(2);
            return;
        }
        this.a = new String();
        this.b = new t(0);
    }

    private t(t t2, String string) {
        this.a = new t(2);
        this.a = new t(2);
        this.a.a = string;
        this.a.b = t2;
    }

    public t(byte[] byArray, int n2) {
        t t2;
        t t3;
        this.a = n2;
        t t4 = t3;
        t3 = new t(0);
        t4.a = byArray;
        String string = new String("Undefined");
        switch (byArray[0]) {
            case 77: {
                string = "audio/midi";
                break;
            }
            case 35: {
                string = "audio/amr";
                break;
            }
            case 82: {
                string = "audio/x-wav";
                break;
            }
            case -1: 
            case 73: {
                string = "audio/mpeg";
            }
        }
        this.c = t2 = new t(t4, string);
    }
}

