import java.io.ByteArrayInputStream;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.m3g.Graphics3D;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.rms.RecordStore;

public final class ae {
   public int a;
   public short[] a;
   private int[] a;
   public Graphics a;
   private static int b = 1;
   private static int[] b = new int[2];
   public static ab a;
   public static Object a = new Object();
   public static z a;
   public static v a;
   public static aa a;
   public static long a = 50;
   public static ae a;
   public static boolean a = true;
   public static z b;
   public static short a = (short)Math.max(Math.max(Math.max(2, 8), 8), 1);
   private static byte[] a = new byte[0];
   private static RecordStore a = null;
   private static byte[] b = null;
   private static int c = -1;
   private static int d = -1;
   private static int e = -1;
   private static boolean b = false;

   public static ae a(af var0) {
      return new ae(var0);
   }

   public ae(int[] var1) {
      a(var1, this);
      Graphics3D.getInstance();
   }

   public final void a(int var1, int var2, int var3, int var4) {
      var4 = var4;
      var3 = var3;
      var2 = var2;
      var1 = var1;
      this.a.drawLine(var1, var2, var3, var4);
   }

   public final void a(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      this.a.drawRGB(var1, 0, var7, var3, var4, var5, var6, true);
   }

   public final void a(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.a.drawRoundRect(var1, var2, var3 + -1, var4 + -1, 10, 10);
   }

   public final void b(int var1, int var2, int var3, int var4) {
      var4 = var4;
      var3 = var3;
      var2 = var2;
      var1 = var1;
      this.a.fillRect(var1, var2, var3, var4);
   }

   public final void b(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.a.fillRoundRect(var1, var2, var3, var4, var5, var6);
   }

   public final void c(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.a.fillTriangle(var1, var2, var3, var4, var5, var6);
   }

   public final void a(Image var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      this.a.drawRegion(var1, var2, var3, var4, var5, var6 >>> 16, var7, var8, var9);
   }

   public final int a() {
      return this.a.getClipWidth();
   }

   public final void c(int var1, int var2, int var3, int var4) {
      var4 = var4;
      var3 = var3;
      var2 = var2;
      var1 = var1;
      this.a.setClip(var1, var2, var3, var4);
   }

   public final int b() {
      return this.a.getColor();
   }

   public final void a(int var1) {
      var1 = var1;
      this.a.setColor(var1 & 16777215);
   }

   public final void a(Object var1) {
      var1 = var1;
      this.a.setFont((Font)var1);
   }

   public final void a(String var1, int var2, int var3, int var4) {
      var4 = var4;
      var3 = var3;
      var2 = var2;
      var1 = var1;
      this.a.drawString(var1, var2, var3, var4);
   }

   public final short[] a() {
      return this.a;
   }

   private ae(af var1) {
      a(a(var1.a, var1.b), this);
      Graphics3D.getInstance();
      this.a = ((u)var1).a.getGraphics();
   }

   private static void a(int[] var0, ae var1) {
      int[] var3;
      (var3 = new int[3])[0] = var0[0];
      var3[1] = var0[1];
      var3[2] = var0[2];
      var1.a = var3;
      short var2 = (short)var0[0];
      short var4 = (short)var0[1];
      short[] var5;
      (var5 = new short[4])[0] = 0;
      var5[1] = 0;
      var5[2] = var2;
      var5[3] = var4;
      var1.a = var5;
   }

   public static void a(d var0) {
      while (var0.a != 2) {
         var0 = var0.a;
      }

      t var1 = var0.b;
      if (a(var0.b) == 200 && (var1.a != null || a(var1))) {
         d var2 = a();
         boolean var3 = true;
         boolean var4 = false;

         while (!var4) {
            b();

            try {
               label132: {
                  if (var1.a.getState() != 300) {
                     d var5 = var2.b;
                     String var6 = var1.a;
                     boolean var7 = var3;
                     int var8 = Integer.MAX_VALUE;
                     int var9 = -1;
                     boolean var10 = false;
                     t[] var11 = var5.a;
                     int var12 = Integer.MAX_VALUE;
                     int var13 = -1;
                     int var14 = 0;
                     b();

                     label150: {
                        for (int var15 = 0; var15 < a; var15++) {
                           if (var11[var15] == null) {
                              if (var7 && !var10) {
                                 int[] var10000 = b;
                                 var13 = b[var14 = a(Thread.currentThread())] - 1;
                                 var10000[var14] = var13;
                                 var19 = var15;
                                 break label150;
                              }
                           } else if (var11[var15].a == var6) {
                              var14++;
                           }

                           if (var6.equals("audio/midi")) {
                              if (var14 == 2) {
                                 var10 = true;
                              }
                           } else if (var6.equals("audio/x-wav")) {
                              if (var14 == 8) {
                                 var10 = true;
                              }
                           } else if (var6.equals("audio/amr") && var14 == 8) {
                              var10 = true;
                           }

                           if (var11[var15] != null && var11[var15].a == var6 && var5.a[var15] < var12) {
                              var12 = var5.a[var15];
                              var13 = var15;
                           }

                           if (var5.a[var15] < var8 && !var10 && var11[var15] != null) {
                              var8 = var5.a[var15];
                              var9 = var15;
                           }
                        }

                        if (var10) {
                           var9 = var13;
                        }

                        if (var9 != -1) {
                           t var33 = var11[var9];
                           var14 = 0;

                           while (true) {
                              if (var14 > 0) {
                                 a(var33);
                                 break;
                              }

                              if (a().a[var14].a.a.a == var33) {
                                 b();

                                 try {
                                    b(var33, a().b);
                                    var33.a.deallocate();
                                 } catch (Throwable var16) {
                                 }

                                 int[] var36 = b;
                                 int var34;
                                 var14 = b[var34 = a(Thread.currentThread())] - 1;
                                 var36[var34] = var14;
                                 break;
                              }

                              var14++;
                           }
                        }

                        int[] var37 = b;
                        int var35;
                        var14 = b[var35 = a(Thread.currentThread())] - 1;
                        var37[var35] = var14;
                        var19 = var9;
                     }

                     if (var19 != -1) {
                        var1.a.prefetch();
                        d var24 = var2.b;
                        b();
                        var24.a[var19] = var1;
                        int[] var38 = var24.a;
                        int var10002 = var24.a;
                        int var20 = var24.a + 1;
                        var24.a = var20;
                        var38[var19] = var10002;
                        var38 = b;
                        int var25;
                        int var21 = b[var25 = a(Thread.currentThread())] - 1;
                        var38[var25] = var21;
                        break label132;
                     }
                  }

                  var4 = true;
               }
            } catch (Throwable var18) {
               var3 = false;
            }

            int[] var40 = b;
            int var26;
            int var22 = b[var26 = a(Thread.currentThread())] - 1;
            var40[var26] = var22;
         }
      }

      b();

      try {
         if (a(var1) == 300) {
            a();
            if (!a().a) {
               c(var0);
               if (var0.b) {
                  var0.b = false;
               }

               var1.a.start();
            }
         }
      } catch (Throwable var17) {
         if (var0.a < 20) {
            var0.a++;
            a(var0);
         }
      }

      int[] var41 = b;
      int var27;
      int var23 = b[var27 = a(Thread.currentThread())] - 1;
      var41[var27] = var23;
   }

   private static int a(int var0, boolean var1) {
      if (var0 == -9996) {
         return 1;
      }

      if (var0 == -9997) {
         return 2;
      }

      if (var0 == -9998) {
         return 3;
      }

      if (var0 == -9999) {
         return 4;
      }

      if (var0 == 99999) {
         return 9;
      }

      if (var0 == 8) {
         return 10;
      }

      if (var0 == 99999) {
         return 42;
      }

      if (var0 == 99999) {
         return 35;
      }

      if (var0 == -5) {
         return 7;
      }

      if (var0 == -6) {
         return 13;
      }

      if (var0 == -7) {
         return 14;
      }

      ab var3 = a;
      if (a.b == null) {
         var3.b = new g(4);
      }

      g var4 = var3.b;
      int var7;
      if ((var7 = b(var0, var4)) != 0) {
         if (144 == var7 && 144 == var4.a && var1) {
            var4.b = false;
            if (var4.b == 0) {
               var4.b = 1;
            } else if (var4.b == 1) {
               var4.b = 2;
            } else {
               var4.b = 0;
            }
         } else if (144 == var7 && !var1) {
            var4.d = true;
            var4.b = true;
         } else if (144 == var7 && var1) {
            var4.d = false;
            var4.b = false;
            if (var4.b == 1) {
               var4.b = 2;
            }
         } else {
            boolean var2;
            if (var4.b != 1 && var4.b != 2 && !var4.b) {
               var2 = false;
            } else {
               var2 = true;
            }

            var4.d = var2;
            if (var4.b == 1 && var1) {
               var4.b = 0;
            }
         }

         if (!var1) {
            var4.a = var7;
         }
      }

      if (var7 != 0 && !var4.b) {
         if (a(var4) && 145 == var7) {
            var4.b = 0;
            var4.d = false;
            if (var4.b == 0) {
               var4.b = 1;
            }
         }

         if (145 == var7 && 145 == var4.a && var1) {
            var4.a = false;
            if (var4.b == 0) {
               var4.b = 1;
            } else if (var4.b == 1) {
               var4.b = 2;
            } else {
               var4.b = 0;
            }
         } else if (145 == var7 && !var1) {
            var4.e = true;
            var4.a = true;
         } else if (145 == var7 && var1) {
            var4.e = false;
            var4.a = false;
            if (var4.b == 1) {
               var4.b = 2;
            }
         } else if (!a(var4)) {
            boolean var5;
            if (var4.b != 1 && var4.b != 2 && !var4.a) {
               var5 = false;
            } else {
               var5 = true;
            }

            var4.e = var5;
            if (var4.b == 1 && var1) {
               var4.b = 0;
            }
         }

         if (!var1) {
            var4.a = var7;
         }
      }

      int var8 = b(var0, var4);
      int var6;
      if (var4.e) {
         var6 = var8;
         if (var8 >= 97 && var6 <= 122) {
            var6 &= -33;
         }
      } else {
         var6 = var8;
      }

      if (var6 != 0) {
         return var6;
      } else if (var0 == -10) {
         return 0;
      } else if (var0 == 99999) {
         return 0;
      } else if (var0 == 99999) {
         return 0;
      } else if (var0 == 99999) {
         return 0;
      } else if (var0 == 99999) {
         return 0;
      } else {
         return var0 == 99999 ? 0 : 0;
      }
   }

   private static int b(int var0, g var1) {
      if (a(var1)) {
         int var4 = var0;
         if (var0 >= 65 && var0 <= 90) {
            var4 = var0 - 65 + 97;
         }

         switch (var4) {
            case 44:
               return 59;
            case 45:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 99:
            case 100:
            case 112:
            case 115:
            case 120:
            default:
               break;
            case 46:
               return 58;
            case 63:
               return 33;
            case 64:
               return 47;
            case 98:
               return 56;
            case 101:
               return 41;
            case 102:
               return 52;
            case 103:
               return 53;
            case 104:
               return 54;
            case 105:
               return 43;
            case 106:
               return 35;
            case 107:
               return 45;
            case 108:
               return 39;
            case 109:
               return 48;
            case 110:
               return 57;
            case 111:
               return 61;
            case 113:
               return 34;
            case 114:
               return 49;
            case 116:
               return 50;
            case 117:
               return 42;
            case 118:
               return 55;
            case 119:
               return 40;
            case 121:
               return 51;
         }
      }

      if (var0 == 99999) {
         return 31;
      }

      if (var0 == 99998) {
         return 144;
      }

      if (var0 == 99999) {
         return 145;
      }

      if (var0 == 99999) {
         return 145;
      }

      if (var0 == 99999) {
         return 8;
      }

      if (var0 == 32) {
         return 32;
      }

      if (var0 == 99999) {
         return 35;
      }

      if (var0 == 99999) {
         return 42;
      }

      int var5;
      if (var0 >= 48 && var0 <= 57) {
         var5 = var0 - 48 + 48;
      } else {
         switch (var0) {
            case 0:
               var5 = 0;
               break;
            case 35:
               var5 = 35;
               break;
            case 42:
               var5 = 42;
               break;
            default:
               if (var0 >= 32 && var0 <= 126) {
                  var5 = var0;
               } else {
                  switch (var0) {
                     case 8:
                        var5 = 10;
                        break;
                     case 9:
                        var5 = 32;
                        break;
                     case 13:
                        var5 = 31;
                        break;
                     case 128:
                        var5 = 15;
                        break;
                     case 163:
                        var5 = 16;
                        break;
                     case 165:
                        var5 = 17;
                        break;
                     case 192:
                        var5 = 18;
                        break;
                     case 194:
                        var5 = 19;
                        break;
                     case 196:
                        var5 = 29;
                        break;
                     case 199:
                        var5 = 20;
                        break;
                     case 200:
                        var5 = 21;
                        break;
                     case 201:
                        var5 = 22;
                        break;
                     case 202:
                        var5 = 23;
                        break;
                     case 203:
                        var5 = 24;
                        break;
                     case 206:
                        var5 = 25;
                        break;
                     case 207:
                        var5 = 26;
                        break;
                     case 209:
                        var5 = 30;
                        break;
                     case 212:
                        var5 = 27;
                        break;
                     case 214:
                        var5 = 28;
                        break;
                     case 220:
                        var5 = 127;
                        break;
                     case 224:
                        var5 = 128;
                        break;
                     case 226:
                        var5 = 129;
                        break;
                     case 228:
                        var5 = 130;
                        break;
                     case 231:
                        var5 = 132;
                        break;
                     case 232:
                        var5 = 131;
                        break;
                     case 233:
                        var5 = 133;
                        break;
                     case 234:
                        var5 = 134;
                        break;
                     case 235:
                        var5 = 135;
                        break;
                     case 238:
                        var5 = 136;
                        break;
                     case 239:
                        var5 = 137;
                        break;
                     case 241:
                        var5 = 140;
                        break;
                     case 244:
                        var5 = 138;
                        break;
                     case 246:
                        var5 = 139;
                        break;
                     case 252:
                        var5 = 141;
                        break;
                     case 8364:
                        var5 = 15;
                        break;
                     default:
                        var5 = 0;
                  }
               }

               int var2 = var5;
               if (var5 == 0) {
                  try {
                     switch (a.getGameAction(var0)) {
                        case 1:
                           var2 = 1;
                           break;
                        case 2:
                           var2 = 3;
                        case 3:
                        case 4:
                        case 7:
                        default:
                           break;
                        case 5:
                           var2 = 4;
                           break;
                        case 6:
                           var2 = 2;
                           break;
                        case 8:
                           var2 = 7;
                     }
                  } catch (Exception var3) {
                  }

                  var5 = var2;
               }
         }
      }

      return var5;
   }

   public static void a(int var0, byte[] var1, int var2, g var3) {
      while (var3.a == 5) {
         var3 = var3.a;
         var2 = var2;
         var1 = var1;
      }

      try {
         a(1, true);
         if (b == null) {
            (b = new byte[var2 + 3])[0] = (byte)e;
            b[1] = (byte)(var2 >> 8);
            b[2] = (byte)var2;
            System.arraycopy(var1, 0, b, 3, var2);
            a.setRecord(d, b, 0, b.length);
         } else {
            var0 = 0;
            if (c < b.length) {
               var0 = (b[c + 1] & 255) << 8 | b[c + 2] & 255;
            }

            if (var0 == var2) {
               c += 3;
               System.arraycopy(var1, 0, b, c, var2);
               a.setRecord(d, b, 0, b.length);
            } else if (var0 > var2) {
               c++;
               b[c++] = (byte)(var2 >> 8);
               b[c++] = (byte)var2;
               System.arraycopy(var1, 0, b, c, var2);
               System.arraycopy(b, c + var0, b, c + var2, b.length - c - var0);
               a.setRecord(d, b, 0, b.length - var0 + var2);
            } else {
               int var6 = b.length + var2 - var0;
               if (var0 == 0) {
                  var6 += 3;
               }

               byte[] var7 = new byte[var6];
               System.arraycopy(b, 0, var7, 0, c);
               var7[c++] = (byte)e;
               var7[c++] = (byte)(var2 >> 8);
               var7[c++] = (byte)var2;
               System.arraycopy(var1, 0, var7, c, var2);
               if (var0 != 0) {
                  System.arraycopy(b, c + var0, var7, c + var2, b.length - (c + var0));
               }

               a.setRecord(d, var7, 0, var7.length);
            }
         }

         a.closeRecordStore();
      } catch (Exception var4) {
      }
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   // $VF: Could not create synchronized statement, marking monitor enters and exits
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static void a(short var0, short var1, short var2, z var3) {
      if (!var3.c && !var3.a || var0 == 6) {
         switch (var0) {
            case 5:
               var3.c = true;
               a();
               var3.d = a().a;
               a();
               a();
               a(true);
               a(0, a().a);
               long[] var5 = var3.a;
               synchronized (var3.a){} // $VF: monitorenter 

               try {
                  var3.a = 0;
                  // $VF: monitorexit
                  break;
               } catch (Throwable var9) {
                  Throwable var4 = var9;

                  while (true) {
                     try {
                        // $VF: monitorexit
                        throw var4;
                     } catch (Throwable var8) {
                        var4 = var8;
                     }
                  }
               }
            case 6:
               a();
               a();
               a(false);
         }

         int var11 = 1;
         long[] var10 = var3.a;
         synchronized (var3.a){} // $VF: monitorenter 

         try {
            switch (var0) {
               case 1:
                  if (var3.a >= 5) {
                     var11 = 0;
                  }
                  break;
               case 5:
               case 6:
                  if (var3.a >= 8) {
                     var11 = 0;
                  }
                  break;
               default:
                  if (var3.a >= 6) {
                     var11 = 0;
                  }
            }

            if (var11) {
               long[] var10000 = var3.a;
               int var10001 = var3.a;
               var11 = var3.a + 1;
               var3.a = var11;
               var10000[var10001] = (long)var0 << 48 | (long)var1 << 32 & 281470681743360L | (long)var2 << 16 & 4294901760L;
            }

            // $VF: monitorexit
         } catch (Throwable var7) {
            Throwable var12 = var7;

            while (true) {
               try {
                  // $VF: monitorexit
                  throw var12;
               } catch (Throwable var6) {
                  var12 = var6;
               }
            }
         }
      }
   }

   public static int a(int var0) {
      if (var0 >= 48 && var0 <= 57) {
         return var0 - 48 + 48;
      }

      switch (var0) {
         case 0:
            return 0;
         case 35:
            return 35;
         case 42:
            return 42;
         default:
            int var1;
            if (var0 >= 32 && var0 <= 126) {
               var1 = var0;
            } else {
               switch (var0) {
                  case 8:
                     var1 = 10;
                     break;
                  case 9:
                     var1 = 32;
                     break;
                  case 13:
                     var1 = 31;
                     break;
                  case 128:
                     var1 = 15;
                     break;
                  case 163:
                     var1 = 16;
                     break;
                  case 165:
                     var1 = 17;
                     break;
                  case 192:
                     var1 = 18;
                     break;
                  case 194:
                     var1 = 19;
                     break;
                  case 196:
                     var1 = 29;
                     break;
                  case 199:
                     var1 = 20;
                     break;
                  case 200:
                     var1 = 21;
                     break;
                  case 201:
                     var1 = 22;
                     break;
                  case 202:
                     var1 = 23;
                     break;
                  case 203:
                     var1 = 24;
                     break;
                  case 206:
                     var1 = 25;
                     break;
                  case 207:
                     var1 = 26;
                     break;
                  case 209:
                     var1 = 30;
                     break;
                  case 212:
                     var1 = 27;
                     break;
                  case 214:
                     var1 = 28;
                     break;
                  case 220:
                     var1 = 127;
                     break;
                  case 224:
                     var1 = 128;
                     break;
                  case 226:
                     var1 = 129;
                     break;
                  case 228:
                     var1 = 130;
                     break;
                  case 231:
                     var1 = 132;
                     break;
                  case 232:
                     var1 = 131;
                     break;
                  case 233:
                     var1 = 133;
                     break;
                  case 234:
                     var1 = 134;
                     break;
                  case 235:
                     var1 = 135;
                     break;
                  case 238:
                     var1 = 136;
                     break;
                  case 239:
                     var1 = 137;
                     break;
                  case 241:
                     var1 = 140;
                     break;
                  case 244:
                     var1 = 138;
                     break;
                  case 246:
                     var1 = 139;
                     break;
                  case 252:
                     var1 = 141;
                     break;
                  case 8364:
                     var1 = 15;
                     break;
                  default:
                     var1 = 0;
               }
            }

            int var2 = var1;
            if (var1 != 0) {
               return var1;
            } else {
               try {
                  switch (a.getGameAction(var0)) {
                     case 1:
                        var2 = 1;
                        break;
                     case 2:
                        var2 = 3;
                     case 3:
                     case 4:
                     case 7:
                     default:
                        break;
                     case 5:
                        var2 = 4;
                        break;
                     case 6:
                        var2 = 2;
                        break;
                     case 8:
                        var2 = 7;
                  }
               } catch (Exception var3) {
               }

               return var2;
            }
      }
   }

   public static final void a(int var0, boolean var1, v var2) {
      while (true) {
         switch (var0) {
            case 0:
               return;
            case 11:
               if (!var1) {
                  d var8 = a.a;
                  if (a.a != null) {
                     a((short)(var8.b + 51), var8);
                  }
               }

               return;
            case 12:
               if (!var1) {
                  d var7 = a.a;
                  if (a.a != null) {
                     a((short)(var7.b - 51), var7);
                  }
               }

               return;
            case 144:
            case 145:
               return;
            default:
               int var6 = var0;
               if (var2.a[var0] != 0) {
                  var6 = var2.a[var0];
               }

               int var3 = var6;
               short var4 = var2.a[var6];
               short var5 = var2.b[var4];
               if (var1) {
                  if (var5 == var0) {
                     var2.b[var4] = 0;
                     if (var2.a == var0) {
                        var2.a = 0;
                     }

                     ((l)var2).a.a(var3, false);
                     return;
                  }

                  if (var2.f) {
                     var1 = true;
                     var0 = var5;
                     continue;
                  }
               } else if (var5 != 0) {
                  if (var4 != 0) {
                     short var9 = var5;
                     if (var2.a[var5] != 0) {
                        var9 = var2.a[var5];
                     }

                     if (var9 == var3) {
                        return;
                     }
                  }

                  a(var5, true, var2);
                  if (!var2.f) {
                     var1 = false;
                     var0 = var0;
                     continue;
                  }
               } else {
                  var2.b[var4] = (short)var0;
                  var2.a = var0;
                  var2.b = Integer.MAX_VALUE;
                  ((l)var2).a.a(var6, true);
               }

               return;
         }
      }
   }

   private static void a(int var0, boolean var1) {
      try {
         b = true;
         d = var0 % 1024 + 1;
         e = (byte)(var0 / 1024);
         a = RecordStore.openRecordStore("DataStore", true);
         c = 0;
         if (var1) {
            for (int var3 = a.getNumRecords() + 1; var3 <= d; var3++) {
               a.addRecord(a, 0, 0);
            }
         } else if (a.getNumRecords() < d) {
            b = false;
            return;
         }

         if ((b = a.getRecord(d)) == null) {
            b = false;
         } else {
            while (c < b.length && b[c] != e) {
               c = (c = c + ((b[c + 1] & 255) << 8 | b[c + 2] & 255)) + 3;
            }

            if (c < b.length) {
               var1 = true;
            } else {
               var1 = false;
            }

            b = var1;
         }
      } catch (Exception var2) {
      }
   }

   public static final void a(t var0, d var1) {
      label37: {
         var1 = var1.a;
         b();
         if (var1.b != null) {
            label34: {
               if (!a(var1)) {
                  b();
                  boolean var10000 = var1.b;
                  int[] var10001 = b;
                  int var2;
                  int var3 = b[var2 = a(Thread.currentThread())] - 1;
                  var10001[var2] = var3;
                  if (!var10000 || a.a.b) {
                     break label34;
                  }
               }

               b(var1);
            }

            if (var1.a == var0 && var1.b.a != null) {
               break label37;
            }

            if (a(var1.b) != 300 && var1.b.a != null) {
               a(var1.b);
            }
         }

         var1.a = var0;
         var1.a = 0;
         if (var1.a != null) {
            var0 = var0.a;
            var1.b = var0;
            if (var0.a == null) {
               a(var0);
            }

            a(var1.a, var1);
         } else {
            var1.b = null;
         }
      }

      int[] var8 = b;
      int var7;
      int var6 = b[var7 = a(Thread.currentThread())] - 1;
      var8[var7] = var6;
   }

   public static final boolean a(x var0) {
      boolean var2;
      if (var0.a[0] == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (var2) {
         var2 = false;
      } else {
         String var8 = a(var0);
         z var10 = a;
         boolean var1 = false;
         x var10000 = a("Browser-Launch-Enabled");
         ad var6;
         var6 = new ad./* $VF: Unable to resugar constructor */<init>();
         String var11 = a(var10000);
         x var3 = var6.a;
         var10000 = a(b(var11));
         short[] var4 = var10000.a;
         int var5 = a(var10000.a);
         a(var4, var5, var3);
         a(var6.a);

         try {
            var10.e = true;
            var10.a = var8;
            b(var10);
            var1 = true;
         } catch (Throwable var7) {
         }

         var2 = var1;
      }

      return var2;
   }

   public static void b(d var0) {
      while (var0.a != 2) {
         var0 = var0.a;
      }

      t var1 = var0.b;
      if (var0.b != null && var1.a != null) {
         b();
         var1.a.equals("audio/x-wav");
         var1.a.equals("audio/midi");
         var1.a.equals("audio/amr");
         d var2 = var0;
         b();

         label32: {
            try {
               if (!a(var2)) {
                  int[] var11 = b;
                  int var9;
                  int var10 = b[var9 = a(Thread.currentThread())] - 1;
                  var11[var9] = var10;
                  break label32;
               }

               var2.b.a.stop();
               var2.b = true;
            } catch (Throwable var5) {
            }

            int[] var10000 = b;
            int var3;
            int var8 = b[var3 = a(Thread.currentThread())] - 1;
            var10000[var3] = var8;
         }

         try {
            var0.b = false;
            var1.a.setMediaTime(0L);
         } catch (Throwable var4) {
         }

         int[] var12 = b;
         int var7;
         int var6 = b[var7 = a(Thread.currentThread())] - 1;
         var12[var7] = var6;
      }
   }

   public static final int a(int var0, byte[] var1) {
      byte[] var4 = var1;
      int var2 = -1;

      try {
         a(1, false);
         if (b) {
            var2 = (b[c + 1] & 255) << 8 | b[c + 2] & 255;
            if (var1.length != var2) {
               var4 = new byte[var2];
            }

            System.arraycopy(b, c + 3, var4, 0, var2);
         }

         a.closeRecordStore();
      } catch (Exception var3) {
      }

      return var2;
   }

   public static int b(int var0) {
      if (var0 >= 32 && var0 <= 126) {
         return var0;
      }

      switch (var0) {
         case 10:
            return 8;
         case 11:
         case 12:
         case 13:
         case 14:
         case 32:
         case 33:
         case 34:
         case 35:
         case 36:
         case 37:
         case 38:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 44:
         case 45:
         case 46:
         case 47:
         case 48:
         case 49:
         case 50:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 56:
         case 57:
         case 58:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 64:
         case 65:
         case 66:
         case 67:
         case 68:
         case 69:
         case 70:
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 83:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 100:
         case 101:
         case 102:
         case 103:
         case 104:
         case 105:
         case 106:
         case 107:
         case 108:
         case 109:
         case 110:
         case 111:
         case 112:
         case 113:
         case 114:
         case 115:
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
         case 123:
         case 124:
         case 125:
         case 126:
         default:
            return 0;
         case 15:
            return 128;
         case 16:
            return 163;
         case 17:
            return 165;
         case 18:
            return 192;
         case 19:
            return 194;
         case 20:
            return 199;
         case 21:
            return 200;
         case 22:
            return 201;
         case 23:
            return 202;
         case 24:
            return 203;
         case 25:
            return 206;
         case 26:
            return 207;
         case 27:
            return 212;
         case 28:
            return 214;
         case 29:
            return 196;
         case 30:
            return 209;
         case 31:
            return 13;
         case 127:
            return 220;
         case 128:
            return 224;
         case 129:
            return 226;
         case 130:
            return 228;
         case 131:
            return 232;
         case 132:
            return 231;
         case 133:
            return 233;
         case 134:
            return 234;
         case 135:
            return 235;
         case 136:
            return 238;
         case 137:
            return 239;
         case 138:
            return 244;
         case 139:
            return 246;
         case 140:
            return 241;
         case 141:
            return 252;
      }
   }

   public static boolean a(d var0) {
      while (var0.a != 2) {
         var0 = var0.a;
      }

      t var3;
      boolean var4;
      if ((var3 = var0.b) != null && var3.a != null) {
         b();
         if (var3 != null && a(var3) == 400) {
            var4 = true;
         } else {
            var4 = false;
         }

         int[] var10000 = b;
         int var1;
         int var2 = b[var1 = a(Thread.currentThread())] - 1;
         var10000[var1] = var2;
      } else {
         var4 = false;
      }

      return var4;
   }

   public static void a(boolean var0, d var1) {
      while (var1.a != 2) {
         var1 = var1.a;
         var0 = var0;
      }

      b();
      var1.a = (boolean)var0;
      if (var1.b != null && var1.b.a != null) {
         try {
            Player var5 = var1.b.a;
            if (var0) {
               var0 = (byte)-1;
            } else {
               var0 = (byte)1;
            }

            var5.setLoopCount(var0);
         } catch (Exception var2) {
         }
      }

      int[] var10000 = b;
      int var6;
      var0 = b[var6 = a(Thread.currentThread())] - 1;
      var10000[var6] = var0;
   }

   public static int a(int var0, g var1) {
      while (var1.a == 5) {
         var1 = var1.a;
      }

      int var3 = -1;

      try {
         a(1, false);
         if (b) {
            var3 = (b[c + 1] & 255) << 8 | b[c + 2] & 255;
         }

         a.closeRecordStore();
      } catch (Exception var2) {
      }

      return var3;
   }

   public static final void a(short[] var0, int var1, x var2) {
      if (var2.a != var0) {
         var2.a = null;
         var2.a = new short[var1 + 1];
         short[] var4 = var2.a;

         for (int var3 = 0; var3 < var1; var3++) {
            var4[var3] = var0[var3];
            if (var0[var3] == 0) {
               break;
            }
         }

         var2.a[var2.a.length - 1] = 0;
      }
   }

   private static boolean a(t var0) {
      b();

      try {
         var0.a = Manager.createPlayer(new ByteArrayInputStream(var0.b.a), var0.a);
         var0.a.realize();
      } catch (Throwable var3) {
         if (var0.a != null) {
            var0.a.close();
         }

         var0.a = null;
      }

      int[] var10000 = b;
      int var1;
      int var2 = b[var1 = a(Thread.currentThread())] - 1;
      var10000[var1] = var2;
      boolean var4;
      if (var0.a != null) {
         var4 = true;
      } else {
         var4 = false;
      }

      return var4;
   }

   private static void b() {
      Thread var0;
      int var1 = a(var0 = Thread.currentThread());
      if (b[var1] <= 0) {
         byte var4;
         if (var0 == a.a) {
            var4 = 0;
         } else {
            var4 = 1;
         }

         byte var2 = var4;
         b[var1] = 1;
         b = var4;

         while (b[var2] > 0 && b != var1) {
            try {
               Thread.sleep(10L);
            } catch (InterruptedException var3) {
            }
         }
      } else {
         int var5 = b[var1] + 1;
         b[var1] = var5;
      }
   }

   private static void c(d var0) {
      b();

      try {
         VolumeControl var1;
         if (var0.b != null && (var1 = (VolumeControl)var0.b.a.getControl("VolumeControl")) != null) {
            a();
            var1.setLevel(100 * var0.b * a.a.c >> 16);
         }
      } catch (Exception var2) {
      }

      int[] var10000 = b;
      int var4;
      int var3 = b[var4 = a(Thread.currentThread())] - 1;
      var10000[var4] = var3;
   }

   private static void a(t var0) {
      if (var0.a != null) {
         b();
         b(var0, a().b);

         try {
            var0.a.close();
         } catch (Exception var2) {
         }

         var0.a = null;
         int[] var10000 = b;
         int var1;
         int var3 = b[var1 = a(Thread.currentThread())] - 1;
         var10000[var1] = var3;
      }
   }

   public static short[] a(short[] var0) {
      short[] var1;
      (var1 = new short[4])[0] = var0[0];
      var1[1] = var0[1];
      var1[2] = var0[2];
      var1[3] = var0[3];
      return var1;
   }

   public static boolean a(int var0, g var1) {
      while (var1.a == 5) {
         var1 = var1.a;
      }

      try {
         a(1, false);
         a.closeRecordStore();
      } catch (Exception var2) {
      }

      return b;
   }

   private static void a(short var0, d var1) {
      if (var0 > 255) {
         var0 = 255;
      }

      if (var0 < 0) {
         var0 = 0;
      }

      var1.b = var0;
      var1.c = var1.b;

      for (int var2 = 0; var2 <= 0; var2++) {
         c(var1.a[0].a);
      }
   }

   public static void a(z var0) {
      long var1 = System.currentTimeMillis();
      if (var0.a == -1L) {
         var0.a = var1;
      }

      if (!var0.c && !var0.a && !var0.b && !var0.f) {
         a((short)5, (short)0, (short)0, var0);
      }
   }

   private static void b(t var0, d var1) {
      b();

      for (int var2 = 0; var2 < a; var2++) {
         if (var1.a[var2] == var0) {
            var1.a[var2] = null;
            break;
         }
      }

      int[] var10000 = b;
      int var4;
      int var3 = b[var4 = a(Thread.currentThread())] - 1;
      var10000[var4] = var3;
   }

   public static String a(x var0) {
      int var1 = a(var0.a);
      int var2 = 0;
      char[] var3 = new char[var1];

      for (int var4 = 0; var4 < var1; var4++) {
         int var5 = var2++;
         var3[var4] = (char)var0.a[var5];
      }

      return new String(var3);
   }

   public static final int a(short[] var0, int var1, int var2, int var3, int var4, m var5) {
      while (var2 <= var3) {
         int var6 = var2 + var3 >> 1;
         int var7 = var0[var6] & '\uffff';
         if (var1 == var7) {
            return var6;
         }

         if (var1 < var7) {
            return a(var0, var1, var2, var6 - 1, var4, var5);
         }

         var2 = var6 + 1;
      }

      return var4;
   }

   public static final short a(d var0) {
      return var0.b;
   }

   public static x a(String var0) {
      char[] var2;
      short[] var3 = new short[(var2 = var0.toCharArray()).length + 1];

      for (int var1 = 0; var1 < var2.length; var1++) {
         var3[var1] = (short)var2[var1];
      }

      var3[var3.length - 1] = 0;
      return new x(var3);
   }

   private static void d(d var0) {
      b();

      for (int var1 = 0; var1 <= 0; var1++) {
         b(var0.a[0].a);
      }

      int[] var10000 = b;
      int var3;
      int var2 = b[var3 = a(Thread.currentThread())] - 1;
      var10000[var3] = var2;
   }

   public static void b(z var0) {
      var0.f = true;
      d(a());
      System.gc();
      if (var0.e) {
         try {
            var0.platformRequest(var0.a);
         } catch (Exception var1) {
         }
      }

      var0.notifyDestroyed();
   }

   public static String a(String var0) {
      new ad();
      return b(var0);
   }

   private static int a(t var0) {
      b();
      int var1 = 0;
      if (var0.a != null) {
         var1 = var0.a.getState();
      }

      int[] var10000 = b;
      int var3;
      int var2 = b[var3 = a(Thread.currentThread())] - 1;
      var10000[var3] = var2;
      return var1;
   }

   private static void a(int var0, i var1) {
      if (var1.a) {
         Display.getDisplay(a.a).vibrate(var0);
         System.currentTimeMillis();
      }
   }

   public static int[] a(int var0, int var1) {
      int[] var2;
      (var2 = new int[3])[0] = var0;
      var2[1] = var1;
      var2[2] = 24;
      return var2;
   }

   public static final void b(int var0) {
      var0 = a(var0, false);
      if (a.a != var0) {
         a(var0, false, a);
      }
   }

   public static short a(byte[] var0, int var1) {
      return (short)((var0[var1] & 255) << 8 | var0[var1 + 1] & 0xFF);
   }

   public static final void a(v var0) {
      short[] var2 = var0.b;

      for (int var1 = 0; var1 < 149; var1++) {
         var2[var1] = 0;
      }

      var0.a = 0;
   }

   public static ag a() {
      ab var0 = a;
      if (a.a == null) {
         var0.a = new ag();
      }

      return var0.a;
   }

   public static g a() {
      if (a.c == null) {
         a.c = new g(0);
      }

      return a.c;
   }

   public static d a() {
      if (a.a == null) {
         a.a = new d(1);
      }

      return a.a;
   }

   private static String b(String var0) {
      String var1 = null;

      try {
         var1 = a.getAppProperty(var0);
      } catch (Exception var2) {
      }

      if (var1 == null) {
         var1 = "NULL";
      }

      return var1;
   }

   private static boolean a(g var0) {
      boolean var1;
      if (!var0.c && !var0.d) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public static void a(boolean var0) {
      a().a = var0;
      if (var0) {
         d(a());
      }
   }

   public static m a() {
      return ((l)a.a).a;
   }

   public static g b() {
      ab var0 = a;
      if (a.a == null) {
         var0.a = new g(5);
      }

      return var0.a;
   }

   public static g c() {
      ab var0 = a;
      if (a.b == null) {
         var0.b = new g(4);
      }

      return var0.b;
   }

   public static final void c(int var0) {
      a(a(var0, true), true, a);
   }

   private static int a(Thread var0) {
      byte var1;
      if (var0 == a.a) {
         var1 = 1;
      } else {
         var1 = 0;
      }

      return var1;
   }

   public static final void a(int var0, int var1, v var2) {
      a(var2);
      var2.a[var0] = (short)var1;
   }

   public static void a() {
      v var0 = a.a;
      if (!a.a.e) {
         var0.e = true;
      }

      b(a);
   }

   public static int a(short[] var0) {
      int var1 = 0;

      while (var0[var1] != 0) {
         var1++;
      }

      return var1;
   }

   public static void a(byte[] var0, byte[] var1, int var2, int var3) {
      System.arraycopy(var1, var2, var0, 0, var3);
   }

   public static void a(short[] var0, short[] var1, int var2) {
      System.arraycopy(var0, 0, var1, 0, var2);
   }

   public static final short b(d var0) {
      d var10000 = var0.a;
      b();
      short var3 = var10000.b;
      int[] var10001 = b;
      int var2;
      int var1 = b[var2 = a(Thread.currentThread())] - 1;
      var10001[var2] = var1;
      return var3;
   }

   public static final void a(int var0, g var1) {
      a(var0, var1.a);
   }

   public static final d a(d var0) {
      return var0.a[0];
   }

   public static x a(short[] var0) {
      return new x(var0);
   }

   public static final void a(boolean var0, g var1) {
      var1.c = true;
   }

   public static final void a(g var0) {
      a(0, var0.a);
   }

   public static short[] a(x var0) {
      return var0.a;
   }

   public static final int a(int[] var0) {
      return var0[1];
   }

   public static final int b(int[] var0) {
      return var0[0];
   }

   public static int c(int var0) {
      return var0 << 16;
   }

   public static int[] a() {
      return a.a;
   }

   public static l a() {
      return (l)a.a;
   }

   public static long a() {
      return System.currentTimeMillis();
   }

   static {
      short[] var0;
      (var0 = new short[2])[0] = -1;
      var0[1] = 0;
      (var0 = new short[4])[0] = -1;
      var0[1] = -1;
      var0[2] = -1;
      var0[3] = -1;
      (var0 = new short[4])[0] = -1;
      var0[1] = -1;
      var0[2] = -1;
      var0[3] = -1;
      (var0 = new short[7])[0] = -1;
      var0[1] = -1;
      var0[2] = 3;
      var0[3] = -1;
      var0[4] = -1;
      var0[5] = -1;
      var0[6] = -1;
      (var0 = new short[2])[0] = -1;
      var0[1] = 0;
   }
}
