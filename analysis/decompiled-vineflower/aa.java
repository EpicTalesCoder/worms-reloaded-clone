import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public final class aa extends Canvas {
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

   public final void sizeChanged(int var1, int var2) {
   }

   public final void keyPressed(int var1) {
      ae.a((short)1, (short)(var1 >> 16), (short)var1, ae.b);
   }

   public final void keyReleased(int var1) {
      ae.a((short)2, (short)(var1 >> 16), (short)var1, ae.b);
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   // $VF: Could not create synchronized statement, marking monitor enters and exits
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public final void paint(Graphics var1) {
      Object var2 = ae.a;
      synchronized (ae.a){} // $VF: monitorenter 

      try {
         if (!this.a) {
            this.a = true;
         }

         this.a.a = var1;
         ae var14 = ae.a;
         if (ae.a.a > 0) {
            var14.a--;
            short[] var11;
            (var11 = new short[3])[0] = 0;
            var11[1] = 0;
            var11[2] = 0;
            short var9 = ae.a.a[0];
            short var4 = ae.a.a[1];
            short var5 = ae.a.a[2];
            int var6 = ae.a.a[3];
            int var10000 = var14.a.getColor();
            var14.a.setColor(var11[0], var11[1], var11[2]);
            var14.a.fillRect(var9, var4, var5, var6);
            var6 = var10000 & 16777215;
            var14.a.setColor(var6);
         }

         ae var12 = var14;
         s var10 = ((l)ae.a.a).a;
         var14 = var12;
         n var13 = ((l)ae.a.a).a.a;
         if (((l)ae.a.a).a.a.a) {
            var13.a = null;
            var13.a = false;
         }

         var13.a = var14;
         ae.a().a.a(var10.a);
         ae.a.a.d = false;
         // $VF: monitorexit
      } catch (Throwable var8) {
         Throwable var3 = var8;

         while (true) {
            try {
               // $VF: monitorexit
               throw var3;
            } catch (Throwable var7) {
               var3 = var7;
            }
         }
      }

      ae.a = false;
      if (ae.b.g) {
         ae.a = true;
         ae.a.a.d = true;
         ae.b.g = false;
      }
   }
}
