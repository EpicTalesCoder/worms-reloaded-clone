public final class ac implements j {
   static int a;
   private static int[] a = new int[4];
   private static n a;
   private static int[] h = new int[]{0, 2, 1, 3, 5, 7, 4, 6};
   private int d;
   int b;
   private short[] a;
   private short[] b;
   private byte[] a;
   private byte[] w;
   private int e;
   private short[] c;
   private int f;
   private short[] d;
   private short[] e;
   private short[] f;
   private byte[] x;
   private int g;
   private short[] g;
   private int h;
   private byte[] y;
   private byte[] z;
   private short[] h;
   private byte[] A;
   private byte[] B;
   private int i;
   private e[] a;
   private int j;
   private int[] i;
   private byte[] C;
   private int[][] a;
   private int k;
   private static int l = -1;
   static int c = -1;
   private static int m;
   private static int n;
   private static int o;
   private static int p;

   static void a(n var0) {
      a = var0;
   }

   ac() {
   }

   ac(int var1) {
      this.k = var1;
      this.b(18 + (var1 << 1));
      this.c(18 + (var1 << 1) + 1);
   }

   final void a() {
      this.a = null;
      this.b = null;
      this.a = null;
      this.w = null;
      this.c = null;
      this.d = null;
      this.e = null;
      this.f = null;
      this.x = null;
      this.g = null;
      this.y = null;
      this.z = null;
      this.h = null;
      this.A = null;
      this.B = null;
      this.a = null;
      this.i = null;
      this.C = null;
      this.a = null;
   }

   private void b(int var1) {
      try {
         b var5 = new b(var1);
         this.d = var5.read() & 0xFF;
         this.b = var5.a() & '\uffff';
         if (this.d == 0) {
            this.a = y.a(var5, this.b, null, 0);
            this.b = y.a(var5, this.b, null, 0);
         }

         this.a = new byte[this.b];
         this.w = new byte[this.b];
         var5.read(this.a, 0, this.b);
         var5.read(this.w, 0, this.b);
         this.e = var5.a() & '\uffff';
         this.c = new short[this.e + 1];
         this.c[0] = 0;
         int var2 = 0;

         for (int var3 = 1; var3 <= this.e; var3++) {
            var2 += var5.read() & 0xFF;
            this.c[var3] = (short)var2;
         }

         this.f = var5.a() & '\uffff';
         this.d = y.a(var5, this.f, null, 0);
         this.e = y.a(var5, this.f, null, 0);
         this.f = new short[this.f];
         this.x = new byte[this.f];

         for (int var9 = 0; var9 < this.f; var9++) {
            this.f[var9] = (short)(var5.read() & 0xFF);
         }

         var5.read(this.x, 0, this.f);

         for (int var10 = 0; var10 < this.f; var10++) {
            this.f[var10] = (short)(this.f[var10] + ((this.x[var10] & 208) << 2));
         }

         this.g = var5.a() & '\uffff';
         this.g = new short[this.g + 1];
         this.g[0] = 0;
         int var11 = 0;

         for (int var6 = 1; var6 <= this.g; var6++) {
            var11 += var5.read() & 0xFF;
            this.g[var6] = (short)var11;
         }

         this.h = var5.a() & '\uffff';
         this.y = new byte[this.h];
         this.z = new byte[this.h];
         this.h = new short[this.h];
         this.A = new byte[this.h];
         this.B = new byte[this.h];
         var5.read(this.y, 0, this.h);
         var5.read(this.z, 0, this.h);

         for (int var7 = 0; var7 < this.h; var7++) {
            this.h[var7] = (short)(var5.read() & 0xFF);
         }

         var5.read(this.A, 0, this.h);
         var5.read(this.B, 0, this.h);

         for (int var8 = 0; var8 < this.h; var8++) {
            this.h[var8] = (short)(this.h[var8] + ((this.B[var8] & 208) << 2));
         }

         var5.close();
      } catch (Exception var4) {
      }
   }

   private void c(int var1) {
      try {
         if (this.d != 1) {
            this.a = new e[1];
            this.a[0] = b.a(var1);
         } else {
            b var10 = new b(var1);
            this.j = var10.read() & 0xFF;
            int var2 = var10.read() & 0xFF;
            int var3 = var10.read() & 0xFF;
            int var4 = var10.read() & 0xFF;
            this.a = new int[var3][];

            for (int var5 = 0; var5 < var3; var5++) {
               this.a[var5] = new int[var4];
               switch (var2) {
                  case 5:
                     short[] var12 = y.a(var10, var4, null, 0);

                     for (int var7 = 0; var7 < var4; var7++) {
                        short var8;
                        if (((var8 = var12[var7]) & 65535) == 63519) {
                           var8 = 16711935;
                        } else {
                           var8 = 0xFF000000 | (var8 & 63488) << 8 | (var8 & 2016) << 5 | (var8 & 31) << 3;
                        }

                        this.a[var5][var7] = var8;
                     }
                     break;
                  case 129:
                     for (int var6 = 0; var6 < var4; var6++) {
                        this.a[var5][var6] = var10.a();
                     }
               }
            }

            this.i = new int[this.b + 1];
            int var11 = 0;

            for (int var13 = 0; var13 < this.b; var13++) {
               this.i[var13] = var11;
               var11 += var10.a() & '\uffff';
            }

            this.i[this.b] = var11;
            this.C = new byte[var11];
            var10.read(this.C, 0, var11);
            var10.close();
         }
      } catch (Exception var9) {
      }
   }

   final void b() {
      this.i = 0;
   }

   private static int c(int var0, int var1) {
      if ((var0 & 4) == 0) {
         return var0 ^ var1;
      }

      if ((var1 & 1) != 0) {
         var0 ^= 2;
      }

      if ((var1 & 2) != 0) {
         var0 ^= 1;
      }

      if ((var1 & 4) != 0) {
         var0 ^= 7;
      }

      return var0;
   }

   final void a(int var1, int var2, int var3, int var4, int var5) {
      var1 = this.g[var1] + var2;
      this.a(this.h[var1] & 65535, var3 + this.y[var1], var4 + this.z[var1], c(this.B[var1] & 7, var5));
   }

   final void a(int var1, int var2, int var3, int var4) {
      int var5 = this.c[var1 + 1] - this.c[var1];

      for (int var6 = 0; var6 < var5; var6++) {
         int var9 = var6;
         ac var7 = this;
         int var8 = this.c[var1] + var9;
         var9 = var7.d[var8];
         int var13 = var7.e[var8];
         int var14 = var7.f[var8] & '\uffff';
         var8 = var7.x[var8] & 7;
         if ((var4 & 7) != 0) {
            int var15;
            int var16;
            if ((var8 & 4) == 0) {
               var15 = var7.a[var14] & 255;
               var16 = var7.w[var14] & 255;
            } else {
               var15 = var7.w[var14] & 255;
               var16 = var7.a[var14] & 255;
            }

            if ((var4 & 1) != 0) {
               var9 = -var9 - var15;
            }

            if ((var4 & 2) != 0) {
               var13 = -var13 - var16;
            }

            if ((var4 & 4) != 0) {
               var15 = var9;
               var9 = -var13 - var16;
               var13 = var15;
            }

            var8 = c(var8, var4);
         }

         int var10002 = var2 + var9;
         int var10003 = var3 + var13;
         int var11 = var8;
         int var10 = var10003;
         var9 = var10002;
         var8 = var14;
         var7 = var7;
         if (a == 0) {
            int var39 = var7.a[var8] & 255;
            int var41 = var7.w[var8] & 255;
            if (var7.d == 0) {
               if (var7.a != null) {
                  e var12 = var7.a[0];
                  var13 = var7.a[var8] & '\uffff';
                  var14 = var7.b[var8] & '\uffff';
                  if (var7.k == 0) {
                     if (ah.b(var8)) {
                        var13 = var8;
                        int var43 = l != -1 && ah.b(var13) ? 0 + (var13 - 11) + (l << 2) : -1;
                        int var19 = var43;
                        if (var43 >= 0) {
                           y.a[4].a(var19, var9, var10, var11);
                        }
                        continue;
                     }

                     if (ah.c(var8)) {
                        int var18;
                        if ((var18 = ah.a(var8, c)) >= 0) {
                           byte var23 = 0;
                           byte var26 = 0;
                           if (var18 >= 79 && var18 < 82) {
                              var23 = -10;
                              if ((var11 & 1) != 0) {
                                 var23 = 10;
                              }

                              var26 = 8;
                           }

                           if (c == 12 || c == 30) {
                              byte var29 = 3;
                              if (c == 30) {
                                 var29 = 2;
                              }

                              ac var10000 = y.a[2];
                              int var10001 = var9 + var23;
                              var10002 = var10 + var26;
                              int var35 = var29;
                              var41 = var10002;
                              var39 = var10001;
                              ac var30 = var10000;
                              var35 = var10000.c[var18] + var35;
                              a[0] = var30.d[var35] + var39;
                              a[1] = var30.e[var35] + var41;
                              int[] var31 = a;
                              if ((var11 & 1) == 1) {
                                 var31[0] = 2 * (var9 + var23) - var31[0];
                              }

                              ah.i = var31[0];
                              ah.j = var31[1];
                           }

                           y.a[2].a(var18, var9 + var23, var10 + var26, var11);
                        }
                        continue;
                     }

                     if (var8 == 173) {
                        y.a[3].a(19, y.n % 2, var9, var10, 0);
                        continue;
                     }
                  }

                  if (var12 != null) {
                     a.a(var12, var13, var14, var39, var41, h[var11], var9, var10, 20);
                  }
               }
            } else {
               int[] var20 = var7.b(var8, var11, 0);
               if ((var11 & 4) == 0) {
                  a.a(var20, var39, var9, var10, var39, var41);
               } else {
                  a.a(var20, var41, var9, var10, var41, var39);
               }
            }
         } else if (a != 2 && a == 1) {
            int var27 = var7.a[var8] & 255;
            var13 = var7.w[var8] & 255;
            if ((var11 & 4) != 0) {
               var14 = var27;
               var27 = var13;
               var13 = var14;
            }

            if (var7.k != 0 || (y.a == null || ah.a(var8, c) < 0) && (!ah.b(var8) || l < 0)) {
               y.a(a, a, y.a(y.n, var9, var10, var27, var13));
            }
         }
      }
   }

   static void a(int var0) {
      l = var0;
   }

   final int[] a(int var1, int var2, int var3) {
      y.a(a, 1073741824, 1073741824, -1073741824, -1073741824);
      var2 = a;
      a = 1;
      this.a(var1, 0, 0, var3);
      a = var2;
      return a;
   }

   final int a(int var1) {
      return this.g[var1 + 1] - this.g[var1];
   }

   final int a(int var1, int var2) {
      return this.A[this.g[var1] + var2] & 0xFF;
   }

   final int b(int var1, int var2) {
      return this.h[this.g[var1] + var2] & 0xFF;
   }

   final int[] b(int var1, int var2, int var3) {
      int var4 = this.a[var1] & 255;
      int var5 = this.w[var1] & 255;
      int[] var35 = this.a[var3];
      int[] var6 = new int[var4 * var5];
      int var7 = var4;
      int var8 = var5;
      if ((var2 & 4) != 0) {
         var7 = var5;
         var8 = var4;
      }

      int var11 = var8;
      int var10 = var7;
      m = 0;
      n = 0;
      o = var10;
      p = var11;
      int var10003 = this.i[var1];
      int var43 = var2;
      int var39 = var7;
      int[] var36 = var6;
      var2 = var5;
      var1 = var4;
      int var20 = var10003;
      byte[] var65 = this.C;
      int[] var58 = var35;
      int var9 = this.j;
      var7 = Math.max(0, 0);
      var8 = Math.max(0, 0);
      int var12;
      int var13;
      if ((var43 & 4) != 0) {
         var12 = var2;
         var13 = var1;
      } else {
         var12 = var1;
         var13 = var2;
      }

      var12 = Math.min(o, var12);
      var13 = Math.min(p, var13);
      var12 -= var7;
      var13 -= var8;
      if (var12 > 0 && var13 > 0) {
         int var14 = 0;
         int var15 = 0;
         int var16 = 0;
         int var17 = 0;
         int var18 = 0;
         int var19 = 0;
         switch (var43) {
            case 0:
               var17 = var8 * var39 + var7;
               var18 = var39 - var12;
               var19 = 1;
               var14 = var8 * var1 + var7;
               var15 = var1 - var12;
               var16 = var13;
               break;
            case 1:
               var17 = var8 * var39 + var7 + var12 - 1;
               var18 = var39 + var12;
               var19 = -1;
               var14 = var8 * var1 + var1 - var7 - var12;
               var15 = var1 - var12;
               var16 = var13;
               break;
            case 2:
               var17 = (var8 + var13 - 1) * var39 + var7;
               var18 = -var39 - var12;
               var19 = 1;
               var14 = (var2 - var8 - var13) * var1 + var7;
               var15 = var1 - var12;
               var16 = var13;
               break;
            case 3:
               var17 = (var8 + var13 - 1) * var39 + var7 + var12 - 1;
               var18 = -var39 + var12;
               var19 = -1;
               var14 = (var2 - var8 - var13) * var1 + var1 - var7 - var12;
               var15 = var1 - var12;
               var16 = var13;
               break;
            case 4:
               var17 = var8 * var39 + var7 + var12 - 1;
               var18 = -var13 * var39 - 1;
               var19 = var39;
               var14 = (var2 - var7 - var12) * var1 + var8;
               var15 = var1 - var13;
               var16 = var12;
               break;
            case 5:
               var17 = (var8 + var13 - 1) * var39 + var7 + var12 - 1;
               var18 = var13 * var39 - 1;
               var19 = -var39;
               var14 = (var2 - var7 - var12) * var1 + var1 - var8 - var13;
               var15 = var1 - var13;
               var16 = var12;
               break;
            case 6:
               var17 = var8 * var39 + var7;
               var18 = -var13 * var39 + 1;
               var19 = var39;
               var14 = var7 * var1 + var8;
               var15 = var1 - var13;
               var16 = var12;
               break;
            case 7:
               var17 = (var8 + var13 - 1) * var39 + var7;
               var18 = var13 * var39 + 1;
               var19 = -var39;
               var14 = var7 * var1 + var1 - var8 - var13;
               var15 = var1 - var13;
               var16 = var12;
         }

         var1 -= var15;
         switch (var9) {
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
               var2 = var9 - 17 + 1;
               var4 = (1 << var2) - 1;
               var14 *= var2;
               int var25 = var20 + (var14 >> 3);
               var5 = 8 - (var14 & 7);
               var7 = var65[var25++] << var5 - var2;
               var8 = 0;

               while (true) {
                  for (int var57 = 0; var57 < var1; var17 += var19) {
                     if ((var5 -= var2) < 0) {
                        var5 += 8;
                        var7 = var7 << 8 | var65[var25++] & 255;
                     }

                     var12 = var7 >> var5 & var4;
                     if ((var12 = var58[var12]) < 0) {
                        var36[var17] = var12;
                     }

                     var57++;
                  }

                  if (++var8 >= var16) {
                     break;
                  }

                  var17 += var18;
                  if ((var5 -= var2 * var15) < 0) {
                     var25 += Math.abs(var5) >> 3;
                     var5 = -(Math.abs(var5) & 7) + 8;
                     var7 = ((var65[var25 - 1] & 255) << 8 | var65[var25++] & 255) << var5 - var2;
                  }
               }
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
            default:
               break;
            case 50:
               var11 = var15;
               var10 = var14;
               var9 = var16;
               var8 = var19;
               var7 = var18;
               var5 = var17;
               int[] var41 = var36;
               var3 = var1;
               var2 = var20;
               byte[] var29 = var65;
               int[] var22 = var58;
               var12 = 0;
               var13 = -1;

               while (var10 > 0) {
                  if (((var13 = var29[var2++] & 255) & 128) != 0) {
                     var12 = var29[var2++] & 255;
                     if ((var14 = (var13 = var13 & 127) + 1) > var10) {
                        var13 -= var10;
                        var10 = 0;
                     } else {
                        var13 = -1;
                        var10 -= var14;
                     }
                  } else {
                     var13 = -1;
                     var10--;
                  }
               }

               var14 = 0;

               while (true) {
                  var15 = var3;
                  if (var13 >= 0) {
                     if ((var10 = var13 + 1) > var3) {
                        var13 -= var3;
                        var10 = var3;
                        var15 = 0;
                     } else {
                        var15 = var3 - var10;
                        var13 = -1;
                     }

                     var16 = var22[var12];

                     while (var10 > 0) {
                        var41[var5] = var16;
                        var10--;
                        var5 += var8;
                     }
                  }

                  while (var15 > 0) {
                     if (((var13 = var29[var2++] & 255) & 128) != 0) {
                        var12 = var29[var2++] & 255;
                        if ((var10 = (var13 = var13 & 127) + 1) > var15) {
                           var13 -= var15;
                           var10 = var15;
                           var15 = 0;
                        } else {
                           var15 -= var10;
                           var13 = -1;
                        }

                        var16 = var22[var12];

                        while (var10 > 0) {
                           var41[var5] = var16;
                           var10--;
                           var5 += var8;
                        }
                     } else {
                        var41[var5] = var22[var13];
                        var5 += var8;
                        var13 = -1;
                        var15--;
                     }
                  }

                  if (++var14 >= var9) {
                     return var6;
                  }

                  var5 += var7;
                  if ((var10 = var11 - (var13 + 1)) <= 0) {
                     var13 -= var11;
                  } else {
                     while (true) {
                        if (((var13 = var29[var2++] & 255) & 128) != 0) {
                           var12 = var29[var2++] & 255;
                           if ((var16 = (var13 = var13 & 127) + 1) > var10) {
                              var13 -= var10;
                              var10 = 0;
                           } else {
                              var13 = -1;
                              var10 -= var16;
                           }
                        } else {
                           var13 = -1;
                           var10--;
                        }

                        if (var10 <= 0) {
                           break;
                        }
                     }
                  }
               }
            case 51:
               if (a == 0) {
                  var11 = var15;
                  var10 = var14;
                  var9 = var16;
                  var8 = var19;
                  var7 = var18;
                  var5 = var17;
                  int[] var40 = var36;
                  var3 = var1;
                  var2 = var20;
                  byte[] var28 = var65;
                  int[] var21 = var58;
                  var12 = 0;
                  var13 = -1;
                  boolean var89 = false;

                  while (var10 > 0) {
                     if (((var13 = var28[var2++] & 255) & 128) != 0) {
                        var12 = var28[var2++] & 255;
                        if ((var15 = (var13 = var13 & 127) + 1) > var10) {
                           var13 -= var10;
                           var10 = 0;
                        } else {
                           var13 = -1;
                           var10 -= var15;
                        }
                     } else if ((var15 = var13 + 1) > var10) {
                        var13 -= var10;
                        var2 += var10;
                        var10 = 0;
                        var89 = true;
                     } else {
                        var13 = -1;
                        var10 -= var15;
                        var2 += var15;
                     }
                  }

                  var15 = 0;

                  while (true) {
                     var10 = var3;
                     if (var13 >= 0) {
                        if ((var16 = var13 + 1) > var3) {
                           var13 -= var3;
                           var16 = var3;
                           var10 = 0;
                        } else {
                           var10 = var3 - var16;
                           var13 = -1;
                        }

                        if (var89) {
                           while (var16 > 0) {
                              var40[var5] = var21[var28[var2++] & 0xFF];
                              var16--;
                              var5 += var8;
                           }
                        } else {
                           var17 = var21[var12];

                           while (var16 > 0) {
                              var40[var5] = var17;
                              var16--;
                              var5 += var8;
                           }
                        }
                     }

                     while (var10 > 0) {
                        if (((var13 = var28[var2++] & 255) & 128) != 0) {
                           var12 = var28[var2++] & 255;
                           if ((var16 = (var13 = var13 & 127) + 1) > var10) {
                              var13 -= var10;
                              var16 = var10;
                              var10 = 0;
                           } else {
                              var10 -= var16;
                              var13 = -1;
                           }

                           var17 = var21[var12];

                           while (var16 > 0) {
                              var40[var5] = var17;
                              var16--;
                              var5 += var8;
                           }

                           var89 = false;
                        } else {
                           var89 = true;
                           if ((var16 = var13 + 1) > var10) {
                              var13 -= var10;
                              var16 = var10;
                              var10 = 0;
                           } else {
                              var10 -= var16;
                              var13 = -1;
                           }

                           while (var16 > 0) {
                              var40[var5] = var21[var28[var2++] & 0xFF];
                              var16--;
                              var5 += var8;
                           }
                        }
                     }

                     if (++var15 >= var9) {
                        break;
                     }

                     var5 += var7;
                     if ((var16 = var11 - (var13 + 1)) <= 0) {
                        var13 -= var11;
                     } else {
                        while (true) {
                           if (((var13 = var28[var2++] & 255) & 128) != 0) {
                              var12 = var28[var2++] & 255;
                              var13 &= 127;
                           }

                           if ((var17 = var13 + 1) > var16) {
                              var13 -= var16;
                              var16 = 0;
                           } else {
                              var13 = -1;
                              var16 -= var17;
                           }

                           if (var16 <= 0) {
                              break;
                           }
                        }
                     }
                  }
               }
         }
      }

      return var6;
   }
}
