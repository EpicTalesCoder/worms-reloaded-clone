import com.ea.sdk.SDKMIDlet;
import java.io.IOException;
import java.io.InputStream;

public final class b extends InputStream {
   private static boolean a;
   private static int a;
   private static int b;
   private static int[] a;
   private static int[][] a;
   private static int c;
   private static boolean[][] a;
   private static int[] b;
   private static int[][] b;
   private static InputStream a;
   private static int d = -1;
   private static int e = -1;
   private static int f = -1;
   private static final byte[] a;
   private InputStream b;
   private int g = 0;
   private int h;
   private int i;
   private int j;
   private int k;
   private String a;

   private static int a(InputStream var0, int var1) {
      int var2 = 0;
      byte var3 = 0;

      for (int var4 = var1 << 3; var3 < var4; var3 += 8) {
         var2 |= (var0.read() & 0xFF) << var3;
      }

      return var2;
   }

   private static void b() {
      InputStream var0 = SDKMIDlet.a.getClass().getResourceAsStream("/index");

      try {
         a(var0, 1);
         a = a(var0, 2);
         int var5 = a(var0, 1);
         b = a(var0, 1);
         int var1 = a;
         int var2 = b;
         a = new int[var1];
         a = new int[var1][var2 + 1];

         for (int var3 = 0; var3 < var1; var3++) {
            a[var3] = a(var0, 2);

            for (int var4 = 0; var4 < var2 + 1; var4++) {
               a[var3][var4] = a(var0, var5);
            }

            a(var0, var5);
         }

         int var6 = c = a(var0, 2);
         int var7 = var2 / 8;
         if (var2 % 8 > 0) {
            var7++;
         }

         a = new boolean[var6][var2];
         b = new int[var6];
         b = new int[var6][];

         for (int var15 = 0; var15 < var6; var15++) {
            int var14 = a(var0, var7);
            int var8 = 0;

            for (int var13 = 0; var13 < var2; var13++) {
               boolean[] var9 = a[var15];
               int var10 = var13;
               boolean var11;
               if ((var14 & 1 << var13) != 0) {
                  var11 = true;
               } else {
                  var11 = false;
               }

               var9[var10] = var11;
               if (a[var15][var13]) {
                  var8++;
               }
            }

            b[var15] = a(var0, var5);
            b[var15] = new int[var8];
            int var17 = 0;

            for (int var16 = 0; var16 < var2; var16++) {
               if (a[var15][var16]) {
                  b[var15][var17++] = a(var0, var5);
               }
            }
         }
      } catch (IOException var12) {
      }

      a = true;
   }

   private static int[] a(int var0) {
      int var1 = b[var0];
      int[] var3 = a;
      int var4 = 0;

      for (int var2 = 0; var2 < a; var2++) {
         if (var3[var2] >= var0 && var2 > 0 && var3[var2 - 1] < var0) {
            var4 = var2;
            break;
         }
      }

      int var6 = 0;
      if (var0 == var3[var4]) {
         var6 = a[var4][0] - var1;
      } else if (++var0 < c) {
         var6 = b[var0] - var1;
      }

      (var3 = new int[4])[0] = var4;
      var3[1] = var1;
      var3[2] = var6;
      var3[3] = 0;
      return var3;
   }

   public static void a() {
      if (a != null) {
         try {
            a.close();
         } catch (IOException var0) {
         }

         a = null;
      }
   }

   public static byte[] a(int var0) {
      try {
         b var1;
         var1 = new b./* $VF: Unable to resugar constructor */<init>(var0);
         byte[] var3 = new byte[var1.i];
         var1.read(var3);
         var1.close();
         return var3;
      } catch (IOException var2) {
         return null;
      }
   }

   public static e a(int var0) {
      try {
         b var1;
         var1 = new b./* $VF: Unable to resugar constructor */<init>(var0);
         e var3 = p.a(var1);
         var1.close();
         return var3;
      } catch (Throwable var2) {
         return null;
      }
   }

   public static final e[] a(int var0, int[] var1) {
      try {
         b var3;
         var3 = new b./* $VF: Unable to resugar constructor */<init>(11);
         byte[] var7 = new byte[var3.i];
         var3.read(var7);
         var3.close();
         e[] var9 = new e[var1.length];

         for (int var2 = 0; var2 < var9.length; var2++) {
            if (var1[var2] == -1) {
               var9[var2] = p.a(var7, var7.length);
               break;
            }
         }

         for (int var4 = 0; var4 < var9.length; var4++) {
            if (var1[var4] != -1) {
               b var5;
               var5 = new b./* $VF: Unable to resugar constructor */<init>(var1[var4]);
               byte[] var8 = new byte[var5.i];
               var5.read(var8);
               var5.close();
               int var10 = 0;

               while (var10 < var7.length - 4 && (var7[var10] != a[0] || var7[var10 + 1] != a[1] || var7[var10 + 2] != a[2] || var7[var10 + 3] != a[3])) {
                  var10++;
               }

               System.arraycopy(var8, 0, var7, var10 + -4, var8.length);
               var9[var4] = p.a(var7, var7.length);
            }
         }

         return var9;
      } catch (IOException var6) {
         return null;
      }
   }

   public b(int var1) {
      if (!a) {
         b();
      }

      int[] var2 = a(var1);
      this.j = var2[0];
      this.h = var2[1];
      this.i = var2[2];
      this.k = var2[3];
      this.a = "/df" + this.j + "_" + this.k;
      if (a != null && this.j == d && f == this.k && e <= this.h) {
         this.b = a;
         a = null;
         p.a(this.b, this.h - e);
      } else {
         this.b = SDKMIDlet.a.getClass().getResourceAsStream(this.a);
         if (this.h > 0) {
            p.a(this.b, this.h);
         }
      }

      this.g = 0;
   }

   public final void close() {
      if (a != null && a != this.b) {
         a.close();
      }

      a = this.b;
      d = this.j;
      e = this.h + this.g;
      f = this.k;
   }

   public final int read() {
      if (this.g >= this.i) {
         return -1;
      }

      this.g++;
      return this.b.read();
   }

   public final int read(byte[] var1) {
      return this.read(var1, 0, var1.length);
   }

   public final int read(byte[] var1, int var2, int var3) {
      if (this.g >= this.i) {
         return -1;
      }

      int var4 = var3;
      if (this.g + var3 > this.i) {
         var4 = this.i - this.g;
      }

      if ((var4 = p.a(this.b, var1, var2, var4)) != -1) {
         this.g += var4;
      }

      return var4;
   }

   public final short a() {
      return (short)(this.read() & 0xFF | (this.read() & 0xFF) << 8);
   }

   public final int a() {
      return this.read() | this.read() << 8 | this.read() << 16 | this.read() << 24;
   }

   public final int b() {
      return this.i;
   }

   public final int available() {
      return this.i - this.g;
   }

   public final long skip(long var1) {
      if ((var1 = p.a(this.b, var1)) > 0L) {
         this.g = (int)(this.g + var1);
      }

      return var1;
   }

   public final void reset() {
      this.b = SDKMIDlet.a.getClass().getResourceAsStream(this.a);
      p.a(this.b, this.h);
      this.g = 0;
   }

   static {
      byte[] var0;
      (var0 = new byte[4])[0] = 80;
      var0[1] = 76;
      var0[2] = 84;
      var0[3] = 69;
      a = var0;
   }
}
