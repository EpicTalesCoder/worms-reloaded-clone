import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public final class s extends Canvas {
   public int a;
   public int b;
   public int c;
   public int d;
   public n a = p.a();

   public static boolean a(int var0) {
      boolean var1;
      if ((ae.a().a.a & var0) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static boolean b(int var0) {
      boolean var1;
      if ((ae.a().a.b & var0) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static boolean c(int var0) {
      boolean var1;
      if (!b(var0) && (0 & var0) == 0) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public static boolean d(int var0) {
      boolean var1;
      if ((ae.a().a.c & 4112) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static long a() {
      return ae.a() - ae.a().a;
   }

   public final int getWidth() {
      return ae.b(ae.a());
   }

   public final int getHeight() {
      return ae.a(ae.a());
   }

   public final void keyPressed(int var1) {
      this.a(ae.a(var1), true);
   }

   public final void keyReleased(int var1) {
      this.a(ae.a(var1), false);
   }

   public final void keyRepeated(int var1) {
   }

   public s() {
      g var1 = ae.c();
      ae.a(true, var1);
   }

   public final void paint(Graphics var1) {
   }

   public final void a(int var1, boolean var2) {
      if (this.d <= 0) {
         int var10000;
         switch (var1) {
            case 1:
               var10000 = 1;
               break;
            case 2:
               var10000 = 2;
               break;
            case 3:
               var10000 = 4;
               break;
            case 4:
               var10000 = 8;
               break;
            case 5:
            case 6:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            default:
               var10000 = 0;
               break;
            case 7:
               var10000 = 16;
               break;
            case 8:
               var10000 = 1048576;
               break;
            case 9:
               var10000 = 524288;
               break;
            case 10:
               var10000 = 8388608;
               break;
            case 11:
               var10000 = 2097152;
               break;
            case 12:
               var10000 = 4194304;
               break;
            case 13:
               var10000 = 32;
               break;
            case 14:
               var10000 = 64;
               break;
            case 35:
               var10000 = 262144;
               break;
            case 42:
               var10000 = 131072;
               break;
            case 48:
               var10000 = 128;
               break;
            case 49:
               var10000 = 256;
               break;
            case 50:
               var10000 = 512;
               break;
            case 51:
               var10000 = 1024;
               break;
            case 52:
               var10000 = 2048;
               break;
            case 53:
               var10000 = 4096;
               break;
            case 54:
               var10000 = 8192;
               break;
            case 55:
               var10000 = 16384;
               break;
            case 56:
               var10000 = 32768;
               break;
            case 57:
               var10000 = 65536;
         }

         int var3 = var10000;
         if (var10000 == 0) {
            if (ae.b(var1) != 0) {
               a();
               if (var2) {
                  return;
               }

               return;
            }
         } else {
            boolean var5 = var2;
            int var4 = var3;
            s var7 = this;
            a();
            w var6 = ae.a().a;
            if (var5) {
               var7.b |= var4;
               var7.a |= var4;
               var6.a(var4);
               return;
            }

            var7.c |= var4;
            var7.a &= ~var4;
         }
      }
   }
}
