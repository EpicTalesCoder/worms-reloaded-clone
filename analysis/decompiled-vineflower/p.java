import com.ea.sdk.SDKMIDlet;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Font;

public final class p {
   public static Object a(e var0, byte[] var1) {
      var0 = var0;
      m var10000 = ae.a();
      byte[] var2 = var1;
      m var12 = var10000;
      byte var3;
      boolean var7;
      if (((var3 = var2[0]) & 128) != 0) {
         var7 = 1;
      } else {
         var7 = 0;
      }

      boolean var4 = (boolean)var7;
      if ((var3 & 64) != 0) {
         var7 = (boolean)1;
      } else {
         var7 = (boolean)0;
      }

      boolean var15 = var7;
      byte[] var5 = new byte[12];
      System.arraycopy(var2, 1, var5, 0, 7);
      var5[7] = 0;
      var5[8] = var5[0];
      var5[9] = var5[1];
      if (var4) {
         var5[10] = 2;
      } else {
         var5[10] = 1;
      }

      if (var15) {
         var5[10] = (byte)(var5[10] | 128);
      }

      short var6 = ae.a(var2, 8);
      var7 = ae.a(var2, 10);
      byte var8 = 12;
      short[] var9 = new short[var6];

      for (int var16 = 0; var16 < var6; var16++) {
         var9[var16] = ae.a(var2, var8);
         var8 += 2;
      }

      short[] var10 = new short[var6 + 1];

      for (int var17 = 0; var17 < var6; var17++) {
         var10[var17] = ae.a(var2, var8);
         var8 += 2;
      }

      var10[var6] = -1;
      int var23;
      byte[] var18 = new byte[var23 = (var6 << 1) + var7 * 6];
      System.arraycopy(var2, var8, var18, 0, var23);
      short[] var24 = new short[256];

      for (short var19 = 0; var19 < 128; var19++) {
         var24[var19] = (short)ae.a(var9, var19, 0, var6 - 1, var9.length, var12);
      }

      var5[11] = 1;
      Object[] var20;
      (var20 = new Object[6])[0] = var5;
      var20[1] = var9;
      var20[2] = var24;
      var20[3] = var10;
      var20[4] = var18;
      var20[5] = null;
      Object[] var13 = var20;
      Object[] var14;
      (var14 = new Object[8])[0] = var0;
      var14[1] = var13[0];
      var14[2] = var13[1];
      var14[3] = var13[2];
      var14[4] = var13[3];
      var14[5] = var13[4];
      var14[6] = var13[5];
      var14[7] = new Integer(-1);
      return var14;
   }

   public static void a(Object var0) {
      m var1;
      (var1 = ae.a()).a.b = null;
      var1.a.a = null;
      var1.a.a = null;
      var1.a.b = null;
      var1.a.a = null;
      var1.a.c = null;
      var1.a.b = null;
      var1.a.a = null;
      if (var0 != null) {
         if (var0 instanceof Font) {
            var1.a.a = new byte[12];
            var1.a.a[0] = 1;
            var1.a.a[1] = 2;
            var1.a.a[7] = 0;
            var1.a.a[8] = 1;
            var1.a.a[9] = 2;
            var1.a.a[10] = 4;
            if (var1.a != null) {
               Object var3 = var0;
               var1.a.a.a(var3);
            }
         } else {
            var1.a.a = ((Object[])var0)[0];
            var1.a.a = (byte[])((Object[])var0)[1];
            var1.a.b = (Integer)((Object[])var0)[7];
            var1.a.b = (short[])((Object[])var0)[2];
            var1.a.a = (short[])((Object[])var0)[3];
            var1.a.c = (short[])((Object[])var0)[4];
            var1.a.b = (byte[])((Object[])var0)[5];
            var1.a.a = (int[])((Object[])var0)[6];
            if (var1.a != null && var1.a.a[11] != var1.a[var1.d] && var1.a[var1.d] > 1) {
               m var2 = var1;
               int var5 = var1.a.b.length;

               for (short var4 = 128; var4 < 256; var4++) {
                  var2.a.a[var4] = (short)ae.a(var2.a.b, var2.d[var4 - 128], 0, var5 - 1, var5, var2);
               }

               var2.a.a[11] = var2.a[var2.d];
            }
         }
      }

      var1.a.b = var0;
   }

   public static Object a(e var0) {
      m var10000 = ae.a();
      m var1;
      (var1 = ae.a()).a.b = null;
      var1.a.a = var0;
      var1.a.b = -1;
      g var10001 = var1.a;
      Object[] var2;
      (var2 = new Object[8])[0] = var1.a.a;
      var2[1] = var1.a.a;
      var2[2] = var1.a.b;
      var2[3] = var1.a.a;
      var2[4] = var1.a.c;
      var2[5] = var1.a.b;
      var2[6] = var1.a.a;
      var2[7] = new Integer(var1.a.b);
      var10001.b = var2;
      return var10000.a.b;
   }

   public static Object a() {
      return ae.a().a.b;
   }

   public static int a() {
      return ae.a().a.a[2];
   }

   public static int b() {
      m var0;
      return ((var0 = ae.a()).a.a[10] & 4) != 0 ? ((Font)var0.a.b).getHeight() : (var0.a.a[3] & 0xFF) + (var0.a.a[4] & 0xFF) + var0.a.a[2];
   }

   public static void a(c var0, int var1, int var2, int var3) {
      a(var0, 0, var0.b, var1, var2, var3);
   }

   private static void a(c var0, int var1, int var2, int var3, int var4, int var5) {
      short[] var10000 = new short[]{0, 0};
      m var6;
      if (((var6 = ae.a()).a.a[10] & 4) != 0) {
         if (var6.a == null) {
            var6.a = new StringBuffer();
         }

         var6.a.setLength(0);

         for (int var30 = var1; var30 < var1 + var2; var30++) {
            char var33;
            if ((var33 = var0.a(var30)) != 173 || var30 == var1 + var2 - 1) {
               var6.a.append(var33);
            }
         }

         n var45 = var6.a;
         String var10001 = var6.a.toString();
         int var27 = var5;
         int var25 = var4;
         var2 = var3;
         String var22 = var10001;
         var45.a.a(var22, var2, var25, var27);
      } else {
         int var12 = var6.a.a[3] & 255;
         int var13 = b();
         int var8 = var6.a.a[8];
         int var7 = var6.a.a[9];
         boolean var11;
         if ((var6.a.a[10] & 2) != 0) {
            var11 = 1;
         } else {
            var11 = 0;
         }

         boolean var9 = (boolean)var11;
         int var10 = 0;
         var11 = 0;
         if ((var5 & 8) != 0) {
            var10 = 0 - a(var0, var1, var2);
         } else if ((var5 & 1) != 0) {
            var10 = 0 - (a(var0, var1, var2) >> 1);
         }

         if ((var5 & 64) != 0) {
            var11 = 0 - var12;
         } else if ((var5 & 32) != 0) {
            var11 = 0 - var13;
         }

         if (var8 == 1) {
            var3 += var10;
            var4 += var11;
         } else if (var8 == 4) {
            var3 -= var10;
            var4 -= var11;
         } else if (var8 == 2) {
            var3 -= var11;
            var4 += var10;
         } else if (var8 == 8) {
            var3 -= var11;
            var4 -= var10;
         }

         var6.a[0] = var3;
         var6.a[1] = var4;
         int var14 = var0.a & 63;
         if ((var8 & 5) != 0) {
            var11 = (var8 & 4) >> 1;
         } else {
            var11 = ((var7 & 1) << 1) + ((~var7 & 2) >> 1);
         }

         byte var15 = (byte)var11;
         if ((var8 & 10) != 0) {
            var11 = (var8 & 8) >> 2;
         } else {
            var11 = (var7 & 2) + (~var7 & 1);
         }

         byte var16 = (byte)var11;
         int var17 = var6.a.a.b();

         for (int var18 = var1; var18 < var1 + var2; var18++) {
            if ((var8 = var0.a(var18)) != 173 || var18 == var1 + var2 - 1) {
               short var38 = var6.a(var8, var14);
               var10 = var6.a(var38);
               if (var38 == -1) {
                  if (var8 != 32) {
                     var6.a.a(65280);
                     var6.a.c(var6.a[0], var6.a[1], var10, var13);
                     var6.a.a(16711680);
                     var6.a.a(var6.a[0], var6.a[1], var6.a[0] + var10, var6.a[1] + var13);
                     var6.a.a(var6.a[0], var6.a[1] + var13, var6.a[0] + var10, var6.a[1]);
                  }

                  var6.a(var6.a, var10);
               } else {
                  short var19 = (short)(++var38 + 1);
                  int var20 = var6.a.b[var38] & 255;

                  for (int var40 = 0; var40 < var20; var40++) {
                     if (!var9) {
                        var7 = var6.a.b[var19] & 255;
                        var5 = var6.a.b[++var19] & 255;
                        byte var26 = var6.a.b[++var19];
                        short var10004 = ++var19;
                        byte var10003 = var6.a.b[var10004];
                        byte var47 = var6.a.b[var10004];
                        var3 = var6.a.b[++var19] & 255;
                        short var10006 = (short)(var19 + 1);
                        var19 = (short)((short)(var19 + 1) + 1);
                        var8 = var6.a.b[var10006] & 255;
                        var6.b[0] = var26;
                        int var21 = var47 - var12;
                        var6.b[1] = var21;
                        var6.b[2] = -var26 - var3;
                        var6.b[3] = var12 - var10003 - var8;
                        var6.a.a((e)var6.a.a, var7, var5, var3, var8, var6.a.a[7], var6.a[0] + var6.b[var15], var6.a[1] + var6.b[var16], 0);
                     }
                  }

                  var6.a(var6.a, var10);
               }
            }
         }

         var6.a.a(var17);
      }
   }

   public static void a(c var0, short[] var1, int var2, int var3, int var4, int var5, int var6) {
      m var7 = ae.a();
      int var8 = b();
      int var9 = 0;
      int var10 = 0;
      int var11 = 0;
      if ((var6 & 2) != 0) {
         var11 = var1[0] * var8 - var7.a.a[2] >> 1;
         var6 ^= 2;
      } else if ((var6 & 32) != 0) {
         var11 = var1[0] * var8 - var7.a.a[2];
         var6 ^= 32;
      }

      var6 |= 16;
      if (var7.a.a[9] == 1) {
         var4 -= var11;
         var9 = var8;
      } else if (var7.a.a[9] == 4) {
         var4 += var11;
         var9 = -var8;
      } else if (var7.a.a[9] == 2) {
         var5 -= var11;
         var10 = var8;
      } else {
         var5 += var11;
         var10 = -var8;
      }

      short var15 = var1[(var1[0] & '\uffff') + 2];

      for (int var12 = var2; var12 < var2 + var3; var12++) {
         int var13 = var1[var12] & '\uffff';
         var11 = (var1[var12 + 1] & '\uffff') - var13;
         short var16;
         if ((var16 = var0.a((var1[var12 + 1] & '\uffff') - 1)) == var15 || var16 == 32) {
            var11--;
         }

         a(var0, var13, var11, var4, var5, var6);
         var4 += var9;
         var5 += var10;
      }
   }

   public static short[] a(c var0, short[] var1, int var2, short var3) {
      var3 = var2;
      short[] var16 = var1;
      int var15 = var0.b;
      var0 = var0;
      m var4 = ae.a();
      boolean var5 = false;
      if (var16 == null) {
         if (var4.e == null) {
            var4.e = new short[50];
            var4.b = 50;
         }

         var16 = var4.e;
         var5 = true;
      }

      int var6 = var0.a & 63;
      int var7 = 0;
      int var8 = 0;
      int var9 = -1;
      var16[1] = 0;
      int var10 = 2;

      for (int var11 = 0; var11 < var15; var11++) {
         short var12 = var0.a(var11);
         if (var5 && var10 + 3 >= var4.b) {
            var4.e = new short[var4.b + 50];
            ae.a(var16, var4.e, var4.b);
            var16 = var4.e;
            var4.b += 50;
         }

         if (var12 == 124) {
            var16[var10++] = (short)(var11 + 1);
            var7 = 0;
            var8 = 0;
            var9 = -1;
         } else {
            int var13;
            if ((var4.a.a[10] & 4) != 0) {
               var13 = ((Font)var4.a.b).charWidth(var0.a(var11));
            } else {
               var13 = var4.a(var4.a(var12, var6));
            }

            var7 += var13;
            var8 += var13;
            if (var7 > var3) {
               if (var9 == -1) {
                  var16[var10++] = (short)var11;
                  var7 = var13;
                  var8 = 0;
               } else {
                  var16[var10++] = (short)(var9 + 1);
                  if (var12 != 32 && var12 != 173) {
                     var9 = -1;
                  } else {
                     var9 = var11;
                  }

                  var7 = var8;
                  var8 = 0;
               }
            } else if (var12 == 32 || var12 == 173) {
               var9 = var11;
               var8 = 0;
            }
         }
      }

      var16[0] = (short)(var10 - 1);
      var16[var10] = (short)var15;
      var16[var10 + 1] = 124;
      if (var5) {
         short[] var18;
         ae.a(var16, var18 = new short[var10 + 2], var10 + 2);
         var16 = var18;
      }

      return var16;
   }

   public static int a(c var0) {
      return a(var0, 0, var0.b);
   }

   private static int a(c var0, int var1, int var2) {
      m var3 = ae.a();
      int var4 = 0;
      if ((var3.a.a[10] & 4) != 0) {
         var4 = ((Font)var3.a.b).substringWidth(var0.toString(), var1, var2);
      } else {
         int var5 = var0.a & 63;

         for (int var6 = var1; var6 < var1 + var2; var6++) {
            var4 += var3.a(var3.a(var0.a(var6), var5));
         }
      }

      return var4;
   }

   private static boolean c(int var0) {
      m var1;
      byte var2 = (var1 = ae.a()).c[var0];
      boolean var3;
      if (var1.b[var2] != null && var1.d[var2] == var0) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public static int a(int var0) {
      m var1 = ae.a();
      SDKMIDlet var2 = SDKMIDlet.a;
      byte[] var3 = new byte[2];
      if (c(var0) && !var1.b) {
         return var1.a[var0];
      }

      InputStream var4 = null;

      try {
         byte var6;
         if ((var1.b[var0] & 64) == 0) {
            var6 = var1.d;
         } else {
            var6 = 0;
         }

         byte var5 = var6;
         if (var1.a == 0) {
            var4 = var2.getClass().getResourceAsStream("/t_" + new String(var1.a[var5]).trim());

            for (int var12 = 1; var12 < var0; var12++) {
               if ((var1.b[var12] & 64) == 0 || var5 == 0) {
                  var4.skip((var1.a[var12] + 1 << 1) + (var1.a[var5][var12] & '\uffff'));
               }
            }
         } else if (var1.a == 1) {
            var4 = var2.getClass().getResourceAsStream("/t_" + new String(var1.a[var5]).trim() + var0);
         }

         var6 = var1.c[var0];
         if (var1.b[var6] == null) {
            var1.b[var6] = new short[var1.c[var6]];
         }

         for (int var10 = 0; var10 < var1.a[var0] + 1; var10++) {
            var3[0] = (byte)var4.read();
            var3[1] = (byte)var4.read();
            var1.b[var6][var10] = ae.a(var3, 0);
         }

         if (var1.b[var6] == null) {
            var1.b[var6] = new byte[var1.b[var6] & 65535];
         }

         byte[] var10001 = var1.b[var6];
         int var11 = var1.a[var5][var0] & '\uffff';
         var4.read(var10001, 0, var11);
         var1.d[var6] = (byte)var0;
         var4.close();
      } catch (IOException var9) {
      }

      return var1.a[var0];
   }

   public static int a(byte[] var0) {
      m var1;
      if ((var1 = ae.a()).a) {
         return var1.e;
      }

      var1.a = var0[1];
      var1.a = (var0[2] & 255) << 16 | (var0[3] & 255) << 8 | var0[4] & 255;
      short var2 = ae.a(var0, 5);
      var1.e = var0[7];
      short var10001 = var0[8];
      short var3 = 10;
      var1.c = (byte)(var10001 + 1);
      var1.b = (byte)(var0[9] + 1);
      var1.a = new byte[var1.e];
      var1.a = new byte[var1.e][];
      var1.e = new byte[var1.e];
      var1.a = new short[var1.b];
      var1.b = new byte[var1.b];
      var1.c = new byte[var1.b];
      var1.a = new short[var1.e][];
      var1.b = new short[var1.b];

      for (int var4 = 0; var4 < var1.b.length; var4++) {
         var1.b[var4] = 0;
      }

      var1.c = new short[var1.b];

      for (int var8 = 0; var8 < var1.c.length; var8++) {
         var1.c[var8] = 0;
      }

      var1.d = new byte[var1.b];
      var1.b = new byte[var1.c][];
      var1.b = new short[var1.c][];

      for (int var9 = 0; var9 < var1.c; var9++) {
         var1.b[var9] = null;
      }

      var1.a[0] = var2;

      for (int var10 = 0; var10 < var1.e; var10++) {
         var1.a[var10] = new short[var1.b];
      }

      for (int var11 = 0; var11 < var1.c; var11++) {
         var1.b[var11] = null;
      }

      short var5 = (short)(var2 + 1);

      for (int var6 = 0; var6 < var1.e; var6++) {
         var1.a[var6] = new byte[5];

         for (int var12 = 0; var12 < 5; var12++) {
            var1.a[var6][var12] = var0[var3 + var12];
         }

         short var13 = (short)((short)(var3 + 5) + 1);
         var1.a[var6] = var0[(short)(var3 + 5)];
         var3 = (short)(var13 + 1);
         var1.e[var6] = var0[var13];
         if (var2 > 0) {
            if (var1.b[0] == null) {
               var1.b[0] = new short[var1.e * var5];
            }

            for (int var14 = 0; var14 < var5; var14++) {
               var1.b[0][var6 * var5 + var14] = ae.a(var0, var3);
               var3 = (short)(var3 + 2);
            }
         }
      }

      if (var2 > 0) {
         short var18 = var1.b[0][var1.e * var5 - 1];
         var1.b[0] = new byte[var18];
         ae.a(var1.b[0], var0, var3, var18);
         var3 += var18;
      }

      for (int var19 = 1; var19 < var1.b; var19++) {
         var1.b[var19] = (byte)(var0[var3] & 192);
         var1.c[var19] = (byte)(var0[var3] & 63);
         var10001 = var5 = (short)(var3 + 1);
         short[] var15 = var1.a;
         var3 = ae.a(var0, var10001);
         var15[var19] = var3;
         var3 = (short)(var5 + 2);
         if (var1.a[var19] + 1 > var1.c[var1.c[var19]]) {
            var1.c[var1.c[var19]] = (short)(var1.a[var19] + 1);
         }

         for (int var17 = 0; var17 < var1.e; var17++) {
            var1.a[var17][var19] = ae.a(var0, var3);
            if ((var1.a[var17][var19] & '\uffff') > (var1.b[var1.c[var19]] & '\uffff')) {
               var1.b[var1.c[var19]] = var1.a[var17][var19];
            }

            var3 = (short)(var3 + 2);
         }
      }

      if (var1.a[var1.d] > 1) {
         var1.a(var1.a[var1.d]);
      }

      var1.a = true;
      return var1.e;
   }

   public static c a(int var0, int var1, c var2) {
      m var3 = ae.a();
      if (2 >= var3.a[0]) {
         return var2;
      }

      var1 = 2 + (var3.a[0] + 1) * var0;
      if (var2 == null) {
         var2 = new c();
      } else if ((var2.a & 64) != 0) {
         var2.a = null;
         var2.a = null;
      }

      var2.a = var3.b[0];
      var2.a = var3.b[0][var1];
      var2.b = var3.b[0][var1 + 1] - var3.b[0][var1] >> 1;
      var2.c = var2.b;
      var2.a = 0;
      return var2;
   }

   public static c a(int var0, c var1) {
      m var2 = ae.a();
      int var3 = -1;
      short var4 = 0;

      for (int var5 = 0; var5 < var2.b; var5++) {
         var4 += var2.a[var5];
         if (var0 < var4) {
            var3 = var5;
            break;
         }
      }

      int var7 = var0 - (var4 - var2.a[var3]);
      byte var6 = var2.c[var3];
      if (var1 == null) {
         var1 = new c();
      } else if ((var1.a & 64) != 0) {
         var1.a = null;
         var1.a = null;
      }

      var1.a = var2.b[var6];
      var1.a = var2.b[var6][var7] & '\uffff';
      var1.b = (var2.b[var6][var7 + 1] & '\uffff') - (var2.b[var6][var7] & '\uffff');
      byte var8 = var2.a[var2.d];
      if ((var2.b[var3] & -63) != 0) {
         var8 = 0;
      }

      if (var8 == 0) {
         var1.b = (short)(var1.b >> 1);
      }

      var1.a = var8;
      var1.c = var1.b;
      return var1;
   }

   public static int c() {
      return ae.a().d;
   }

   public static boolean a(int var0) {
      m var1;
      byte var2 = (var1 = ae.a()).d;
      if (var1.a != null && var0 >= 0 && var0 < var1.e) {
         var1.d = (byte)var0;
      }

      if (var1.d != var2) {
         var1.b = true;

         for (int var3 = 1; var3 < var1.b; var3++) {
            if (c(var3) && (var1.b[var3] & 64) == 0) {
               a(var3);
            }
         }

         var1.b = false;
         if (var1.a[var1.d] != var1.a[var2] && var1.a[var1.d] > 1) {
            var1.a(var1.a[var1.d]);
         }
      }

      boolean var4;
      if (var1.d == var0) {
         var4 = true;
      } else {
         var4 = false;
      }

      return var4;
   }

   public static void a(n var0) {
      ae.a().a = var0;
   }

   public static void a(byte[] var0) {
      ae.a(1, var0, var0.length, ae.b());
      ae.b();
   }

   public static byte[] a(int var0) {
      if ((var0 = ae.a(1, ae.b())) == -1) {
         return null;
      }

      ae.b();
      byte[] var2;
      ae.a(1, var2 = new byte[var0]);
      return var2;
   }

   public static boolean b(int var0) {
      boolean var1;
      if (!ae.a(1, ae.b())) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static long a(InputStream var0, long var1) {
      return var0.skip(var1);
   }

   public static int a(InputStream var0, byte[] var1, int var2, int var3) {
      return var0.read(var1, var2, var3);
   }

   public static int b(int var0) {
      if (ae.a().a.length == 1) {
         return 0;
      }

      String var1 = SDKMIDlet.a("Force-Locale");
      int var2;
      String[] var3 = new String[var2 = ae.a().a.length];

      for (int var4 = 0; var4 < var2; var4++) {
         var3[var4] = new String(ae.a().a[var4]);
      }

      if (var0 == -1) {
         byte var6;
         if (var1 == null) {
            var6 = 2;
         } else if ((var1 = var1.toLowerCase().trim()).length() < 2) {
            var6 = 2;
         } else if (var1.equals("menu")) {
            var6 = 1;
         } else if (var1.startsWith("auto-")) {
            var6 = 3;
            var1 = var1.substring(5);
         } else {
            var6 = 4;
         }

         for (int var9 = 0; var9 < var3.length; var9++) {
            if (var3[var9].length() > 2) {
               String var7 = var3[var9].substring(0, 2);
               boolean var8 = true;

               for (int var5 = 0; var5 < var3.length; var5++) {
                  if (var9 != var5 && var3[var5].startsWith(var7)) {
                     var8 = false;
                     break;
                  }
               }

               if (var8) {
                  var3[var9] = var7;
               }
            }
         }

         if (var6 == 2 || var6 == 3 || var6 == 1) {
            var0 = a(var3);
            if (var6 == 1 && var0 >= 0) {
               var0 = -1 - var0;
            }
         }

         if (var6 == 4 || var6 == 3 && var0 == -1) {
            for (int var10 = 0; var10 < var3.length; var10++) {
               if (var1.startsWith(var3[var10])) {
                  var0 = var10;
                  break;
               }
            }
         }

         if (var6 == 4 && var0 == -1) {
            var0 = a(var3);
         }
      }

      return var0;
   }

   public static short a(byte[] var0, int var1) {
      return (short)((var0[var1] & 255) << 8 | var0[var1 + 1] & 0xFF);
   }

   public static boolean a(String var0) {
      ae.a();
      return ae.a(ae.a(ae.a(ae.a(new String(var0)))));
   }

   public static e a(int var0, int var1) {
      return new e(var0, var1);
   }

   public static e a(byte[] var0, int var1) {
      return new e(var0, 0, var1);
   }

   public static e a(String var0) {
      e var1;
      var1 = new e./* $VF: Unable to resugar constructor */<init>(var0);
      if (var1.a.a() == null) {
         throw new IOException("Error creating SDKImage from a file path.");
      } else {
         return var1;
      }
   }

   public static e a(b var0) {
      byte[] var1 = new byte[var0.b()];
      var0.read(var1, 0, var0.b());
      return new e(var1, 0, var1.length);
   }

   public static n a() {
      return new n();
   }

   private static byte a(String[] var0) {
      byte var1 = -1;
      String var2;
      if ((var2 = System.getProperty("microedition.locale")) != null) {
         String var4;
         int var5;
         if ((var5 = (var4 = var2.toLowerCase().trim()).length()) < 2) {
            return -1;
         }

         for (int var3 = 0; var3 < var0.length; var3++) {
            if (var0[var3].length() > var5 && var0[var3].startsWith(var4)) {
               var1 = -1;
               break;
            }

            if (var4.startsWith(var0[var3])) {
               var1 = (byte)var3;
               break;
            }
         }
      }

      return var1;
   }
}
