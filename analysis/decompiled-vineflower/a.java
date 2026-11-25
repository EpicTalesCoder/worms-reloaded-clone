public final class a implements j {
   static int a = 2;
   private static int br;
   private static long e;
   private static int[] h = new int[24];
   static int b;
   private static int bs;
   static int c = 0;
   static int d = 0;
   static int e = 0;
   static boolean a;
   private static boolean l;
   static boolean b;
   static boolean c;
   static boolean d;
   static boolean e;
   static boolean f;
   public static int f;
   public static int g;
   public static int h;
   public static int i;
   public static int j;
   public static boolean g = false;
   private static int bt = 0;
   static int k = 0;
   static int l = 30;
   private static int bu;
   private static int bv;
   public static int m = j.K / 2;
   public static int n = j.L / 2;
   private static int bw;
   private static int bx;
   public static int o = -1;
   public static boolean h = false;
   private static byte[] w;
   private static int by = -1;
   private static int bz = -1;
   private static int bA;
   private static int bB;
   static boolean i;
   private static int bC = 0;
   private static int[][] b;
   private static int[] i = new int[1024];
   private static int[] j = new int[5];
   private static boolean m = true;
   private static int bD;
   private static int bE;
   private static int bF;
   private static int bG;
   private static int bH;
   private static int[][] c = new int[16][2];
   private static int bI;
   static int[][] a;
   private static int bJ;
   private static int bK;
   private static int bL;
   private static int bM;
   private static int bN;
   private static int bO;
   static int p = 640;
   static int q = 320;
   private static int bP = 640;
   private static int bQ = 320;
   private static int bR;
   private static int bS;
   static int r;
   static int s;
   static ah[] a;
   private static ah[][] a;
   private static int[] k;
   static ah a;
   static byte[] a;
   static int[] a = new int[]{-1, -1, -1, -1};
   static int t = -1;
   static int u;
   static int v;
   static int w;
   static int x;
   static int y;
   static int z = 0;
   static int A;
   static int B = 0;
   static boolean j = true;
   static int C = -1;
   static int D;
   static int E;
   static int F;
   private static byte[] x = new byte[25601];
   static int G = 0;
   static int H = 0;
   static int I = 0;
   public static long a;
   private static long f;
   private static long g = 46000L;
   public static long b = 0L;
   public static int J;
   public static long c;
   public static long d;
   public static boolean k = false;
   private static int bT;
   private static int bU;
   private static r[] a;
   private static r[] b;

   static void a() {
      bU = 0;
      bT = 0;
      a = null;
      b = null;
      System.gc();
      b = null;
      f = 0;
      g = 0;
      System.gc();
      n();
      y.o = false;
      System.gc();
   }

   static void a(boolean var0, int var1, int var2, int var3) {
      byte var4 = 0;
      if (bz != -1) {
         y.g(bz);
      }

      a = new int[4096][2];
      w = null;
      System.gc();
      y.a(x, (byte)0);
      e = s.a();
      br = 0;
      v = -1;
      w = 0;
      t = -1;
      u = 0;
      x = -1;
      y = 0;
      E = -1;
      F = 0;
      z = 0;
      B = 0;
      d = 0L;
      bI = 0;
      A = 0;
      y.a = false;
      e();
      ah.p = 0;
      y.a(a, -1);
      y.o = false;
      ah.f = false;
      ah.d = false;
      ah.g = false;
      y.J = 2;
      y.a(y.r, 0);
      y.a(y.q, 0);
      y.a(y.p, 0);
      a = false;
      l = false;
      b = false;
      c = false;
      d = false;
      f = false;
      e = false;
      if (y.c()) {
         f = true;
      }

      j();
      bU = 0;
      bT = 0;
      a = new r[250];
      b = new r[250];

      for (int var5 = 0; var5 < 250; var5++) {
         a[var5] = new r();
      }

      for (int var13 = 0; var13 < 250; var13++) {
         b[var13] = new r();
      }

      y.F = 0;
      y.bG = 0;
      p = 640;
      G = 0;
      q = 320;
      H = 0;
      I = 0;
      b = q - 32;
      y.bv = 32768;
      y.m = false;
      y.g = false;
      y.c = null;
      y.d = null;
      y.i = false;
      k = false;
      if (y.A == 5) {
         y.g();
      }

      c = 0;
      d = 0;
      e = 0;
      y.a.d(0, 0, y.e, y.f);
      if (var0) {
         var0 = var3;
         int var14 = var2;
         if (var2 == -1) {
            var14 = y.c(4);
         }

         if (var0 == -1) {
            bJ = y.a.nextInt();
            var0 = a();
         }

         G = 0;
         var1 = var14;
         int var7 = var0;
         bL = (var2 = var0 & 65535) % 101;
         bM = var2 / 101 % 101;
         bJ = var7;
         bK = var1;
         bL = (bL << 16) / 50 + 32768;
         bM = (bM << 16) / 50 + 32768;
         bN = 65536 + (a() & 65535);
         bO = 65536 + (a() & 65535) / 2;
         var0 = bK;
         switch (bK) {
            case 0:
               bH = 4;
               c[0][0] = 1 * bP / 16 << 16;
               c[0][1] = 16 * bQ / 16 - 1 << 16;
               c[1][0] = 2 * bP / 16 << 16;
               c[1][1] = 6 * bQ / 16 << 16;
               c[2][0] = 14 * bP / 16 << 16;
               c[2][1] = 6 * bQ / 16 << 16;
               c[3][0] = 15 * bP / 16 << 16;
               c[3][1] = 16 * bQ / 16 - 1 << 16;
               break;
            case 1:
               bH = 4;
               c[0][0] = 1 * bP / 16 << 16;
               c[0][1] = 16 * bQ / 16 - 1 << 16;
               c[1][0] = 2 * bP / 16 << 16;
               c[1][1] = 12 * bQ / 16 << 16;
               c[2][0] = 14 * bP / 16 << 16;
               c[2][1] = 12 * bQ / 16 << 16;
               c[3][0] = 15 * bP / 16 << 16;
               c[3][1] = 16 * bQ / 16 - 1 << 16;
               break;
            case 2:
               bH = 5;
               c[0][0] = 1 * bP / 16 << 16;
               c[0][1] = 16 * bQ / 16 - 1 << 16;
               c[1][0] = 2 * bP / 16 << 16;
               c[1][1] = 4 * bQ / 16 << 16;
               c[2][0] = 8 * bP / 16 << 16;
               c[2][1] = 16 * bQ / 16 - 1 << 16;
               c[3][0] = 14 * bP / 16 << 16;
               c[3][1] = 4 * bQ / 16 - 1 << 16;
               c[4][0] = 15 * bP / 16 << 16;
               c[4][1] = 16 * bQ / 16 - 1 << 16;
               break;
            case 3:
               bH = 8;
               c[0][0] = 1 * bP / 16 << 16;
               c[0][1] = 16 * bQ / 16 - 1 << 16;
               c[1][0] = 2 * bP / 16 << 16;
               c[1][1] = 6 * bQ / 16 << 16;
               c[2][0] = 5 * bP / 16 << 16;
               c[2][1] = 6 * bQ / 16 << 16;
               c[3][0] = 6 * bP / 16 << 16;
               c[3][1] = 16 * bQ / 16 << 16;
               c[4][0] = 10 * bP / 16 << 16;
               c[4][1] = 16 * bQ / 16 << 16;
               c[5][0] = 11 * bP / 16 << 16;
               c[5][1] = 6 * bQ / 16 << 16;
               c[6][0] = 14 * bP / 16 << 16;
               c[6][1] = 6 * bQ / 16 << 16;
               c[7][0] = 15 * bP / 16 << 16;
               c[7][1] = 16 * bQ / 16 - 1 << 16;
            case 4:
            default:
               break;
            case 5:
               bH = 16;
               c[0][0] = 0;
               c[0][1] = 16 * bQ / 16 << 16;
               c[1][0] = 1 * bP / 16 << 16;
               c[1][1] = 15 * bQ / 16 << 16;
               c[2][0] = 2 * bP / 16 << 16;
               c[2][1] = 14 * bQ / 16 << 16;
               c[3][0] = 3 * bP / 16 << 16;
               c[3][1] = 13 * bQ / 16 << 16;
               c[4][0] = 4 * bP / 16 << 16;
               c[4][1] = 13 * bQ / 16 << 16;
               c[5][0] = 5 * bP / 16 << 16;
               c[5][1] = 14 * bQ / 16 << 16;
               c[6][0] = 6 * bP / 16 << 16;
               c[6][1] = 15 * bQ / 16 << 16;
               c[7][0] = 13 * bP / 32 << 16;
               c[7][1] = 16 * bQ / 16 << 16;
               c[8][0] = 19 * bP / 32 << 16;
               c[8][1] = 16 * bQ / 16 << 16;
               c[9][0] = 10 * bP / 16 << 16;
               c[9][1] = 15 * bQ / 16 << 16;
               c[10][0] = 11 * bP / 16 << 16;
               c[10][1] = 14 * bQ / 16 << 16;
               c[11][0] = 12 * bP / 16 << 16;
               c[11][1] = 13 * bQ / 16 << 16;
               c[12][0] = 13 * bP / 16 << 16;
               c[12][1] = 13 * bQ / 16 << 16;
               c[13][0] = 14 * bP / 16 << 16;
               c[13][1] = 14 * bQ / 16 << 16;
               c[14][0] = 15 * bP / 16 << 16;
               c[14][1] = 15 * bQ / 16 << 16;
               c[15][0] = 16 * bP / 16 << 16;
               c[15][1] = 16 * bQ / 16 << 16;
         }

         if (var0 == 1) {
            b(var0, j.bn);
         } else {
            b(var0, 64);
         }
      } else {
         if (var1 >= 90 && var1 <= 93) {
            h(var1);
         } else {
            y.A = 0;
            int var15 = var1;
            byte var9 = 86;

            for (int var11 = 0; var11 < var15; var11++) {
               var9 += 2;
            }

            h[var15] = 0;
            var4 = var9;
            h(var9);
         }

         y.a(x, (byte)0);
         B();
      }

      bs = var4;
   }

   private static void x() {
      j = true;

      for (int var0 = 0; var0 < 36; var0++) {
         y.a[0][var0] = 0;
      }

      if (y.z != 0 && y.z != 2) {
         if (y.z == 3) {
            for (int var18 = 0; var18 < 9; var18++) {
               y.a[0][j.t[var18]] = j.u[var18];
            }

            int var19 = y.b(17, 20);
            int var9;
            byte[] var2 = new byte[var9 = j.t.length - 9];
            byte[] var3 = new byte[var9];
            System.arraycopy(j.t, 9, var2, 0, var9);
            System.arraycopy(j.u, 9, var3, 0, var9);
            var9 = j.g.length;

            for (int var5 = 0; var5 < j.g.length - 1; var5++) {
               int var4 = y.a.nextInt(var9);
               byte var6 = var2[var5];
               byte var7 = var3[var5];
               var2[var5] = var2[var5 + var4];
               var3[var5] = var3[var5 + var4];
               var2[var5 + var4] = var6;
               var3[var5 + var4] = var7;
               var9--;
            }

            int var20 = 9;

            for (int var21 = 0; var20 < var19; var21++) {
               y.a[0][var2[var21]] = var3[var21];
               var20++;
            }
         } else if (y.z == 1) {
            if (y.D == 6 || y.D == 20) {
               return;
            }

            if (y.D == 13 || y.D == 27) {
               y.a[0][0] = -1;
               y.a[0][1] = -1;
               y.a[0][12] = 2;
               y.a[0][31] = 2;
               y.a[0][3] = -1;
               y.a[0][34] = -1;
               y.a[0][35] = -1;
               y.a[0][13] = 2;
               y.a[0][25] = 2;
               y.a[0][2] = 2;
               y.a[0][19] = 2;
               return;
            }

            if (!y.f) {
               System.arraycopy(y.k, 0, y.a[0], 0, 36);

               for (int var12 = 0; var12 < 36; var12++) {
                  y.a[0][var12] = y.a[0][var12] + y.l[var12];
                  y.a[0][var12] = y.a[0][var12] + y.m[var12];
               }
            } else {
               for (int var11 = 0; var11 < 36; var11++) {
                  y.a[0][var11] = -1;
               }
            }

            if (y.c()) {
               j = false;
            }

            a(y.a[0]);
         }
      } else {
         for (int var8 = 0; var8 < 3; var8++) {
            if (y.A == 1 && y.z == 2) {
               for (int var17 = 0; var17 < 36; var17++) {
                  y.a[var8][var17] = 0;
               }

               y.a[var8][0] = -1;
               y.a[var8][1] = -1;
               y.a[var8][34] = -1;
               y.a[var8][35] = -1;
            } else if (y.A == 4 && y.z == 2) {
               for (int var16 = 0; var16 < 36; var16++) {
                  y.a[var8][var16] = 0;
               }

               y.a[var8][0] = -1;
               y.a[var8][1] = -1;
               y.a[var8][3] = -1;
               y.a[var8][31] = 3;
               y.a[var8][7] = 3;
               y.a[var8][8] = 3;
               y.a[var8][12] = 3;
               y.a[var8][14] = 3;
               y.a[var8][17] = 1;
               y.a[var8][21] = 3;
               y.a[var8][30] = 1;
               y.a[var8][22] = 1;
               y.a[var8][34] = -1;
               y.a[var8][35] = -1;
            } else {
               if (y.A == 2 && y.z == 2) {
                  for (int var14 = 0; var14 < 36; var14++) {
                     y.a[var8][var14] = 0;
                  }

                  y.a[var8][0] = -1;
                  y.a[var8][2] = -1;
                  y.a[var8][8] = -1;
                  y.a[var8][34] = -1;
                  y.a[var8][35] = -1;
               } else {
                  System.arraycopy(y.k, 0, y.a[var8], 0, 36);

                  for (int var1 = 0; var1 < 36; var1++) {
                     y.a[var8][var1] = y.a[var8][var1] + y.l[var1];
                  }
               }

               if (y.z != 3 && y.A != 1) {
                  for (int var15 = 0; var15 < 36; var15++) {
                     y.a[var8][var15] = y.a[var8][var15] + y.m[var15];
                  }
               }

               a(y.a[var8]);
            }
         }
      }

      for (int var13 = 0; var13 < 3; var13++) {
         y.a[var13][34] = -1;
         y.a[var13][35] = -1;
      }
   }

   private static void a(int[] var0) {
      if (y.A == 6) {
         for (int var2 = 0; var2 < j.c.length; var2++) {
            var0[j.c[var2]] = 0;
         }
      } else {
         if (y.A == 3) {
            for (int var1 = 0; var1 < 3; var1++) {
               y.a[var1][18] = 0;
            }
         }
      }
   }

   static void b() {
      long var0 = s.a();
      br = (int)(br + (var0 - e));
      e = var0;
      if (y.A == 5) {
         y.f();
      }

      try {
         if ((y.b() != 7 || y.p != 11 && y.p != 4 && y.p != 7) && (h == 2 || h == 0)) {
            if (s.a(2052)) {
               if (bw > 0) {
                  bw = 0;
               }

               if ((bw -= 13) < -26) {
                  bw = -26;
               }
            } else if (s.a(8200)) {
               if (bw < 0) {
                  bw = 0;
               }

               if ((bw += 13) > 26) {
                  bw = 26;
               }
            } else {
               bw = 0;
            }

            if (s.a(513)) {
               if (bx > 0) {
                  bx = 0;
               }

               if ((bx -= 13) < -26) {
                  bx = -26;
               }
            } else if (s.a(32770)) {
               if (bx < 0) {
                  bx = 0;
               }

               if ((bx += 13) > 26) {
                  bx = 26;
               }
            } else {
               bx = 0;
            }

            if (bw > 0) {
               if ((m = m + bw) > p - 15) {
                  m = p - 15;
               }
            } else if (bw < 0 && (m = m + bw) < 15) {
               m = 15;
            }

            if (bx > 0) {
               if ((n = n + bx) > b - 10) {
                  n = b - 10;
               }
            } else if (bx < 0) {
               n = n + bx;
               byte var7 = -55;
               if (y.b() == 7
                     && (y.a.a[15] == 14 || y.a.a[15] == 28 || y.a.a[15] == 17 || y.a.a[15] == 6 || y.a.a[15] == 17 || y.a.a[15] == 5 || y.a.a[15] == 11)
                  || y.b() == 18) {
                  var7 = 25;
               }

               if (n < var7) {
                  n = var7;
               }
            }
         }

         f();
         h();
         g();
         l();

         for (int var8 = 0; var8 < bU; var8++) {
            r var1;
            if ((var1 = b[var8]).a) {
               if (var1.a[0] < var1.a[1]) {
                  var1.a[0]++;
               } else {
                  var1.a = false;
                  switch (var1.a) {
                     case 80:
                        ah var15;
                        if ((var15 = e(var1.a[2])) != null) {
                           i(var15.b);
                        }
                        break;
                     case 81:
                        if (y.a != null && y.a.a[12] == 1) {
                           y.a.b((byte)0);
                        }

                        y.b(var1.a[2], -1, var1.a[1]);
                        if (var1.a[2] > 0) {
                           y.s = true;
                        }
                        break;
                     case 82:
                        for (int var23 = 2; var23 <= 8; var23++) {
                           if (var1.a[var23] != -1) {
                              a(var1.a[var23]).a = true;
                           }
                        }
                        break;
                     case 83:
                        for (int var22 = 2; var22 <= 7; var22++) {
                           if (var1.a[var22] != -1) {
                              a(var1.a[var22]).a = false;
                           }
                        }
                        break;
                     case 84:
                        ah var14;
                        if ((var14 = e(var1.a[2])) == null) {
                           break;
                        }

                        if (ah.c != null) {
                           e(ah.c.b);
                           ah.c = null;
                        }

                        ah var21;
                        (var21 = b(5)).a(var14.e, var14.f);
                        var21.a(3);
                        var21.a[0] = var1.a[0];
                        var21.a[1] = var1.a[1];
                        if (var21.a[1] != -1 && var14.a[5] == 1) {
                           var21.b((int)var21.a[1]);
                        } else {
                           var21.b((int)var21.a[0]);
                        }

                        var21.d &= -65;
                        ah.c = var21;
                        break;
                     case 85:
                        if (e(var1.a[2]) != null && ah.c != null) {
                           e(ah.c.b);
                           ah.c = null;
                        }
                        break;
                     case 86:
                        a = var1.a[0] > 0;
                        l = var1.a[1] > 0;
                        b = var1.a[2] > 0;
                        c = var1.a[3] > 0;
                        d = var1.a[4] > 0;
                        e = var1.a[5] > 0;
                        if (!c) {
                           j = true;
                           C = y.a();
                        }
                        break;
                     case 87:
                        ah var13;
                        if ((var13 = e(var1.a[4])) != null) {
                           short var17 = var1.a[2];
                           int var19 = var1.a[3];
                           i(var13.b);
                           var13.q = 0;
                           var13.a = 0;
                           var13.e(-1);
                           var13.a[5] = 32768;
                           var13.h = var13.a[11];
                           var13.b(7);
                           var13.c();
                           var13.a[18] = 0;
                           ah.o = 0;
                           var13.l = false;
                           var13.b((byte)16);
                           var13.a[10] = 0;
                           var13.b(48);
                           var13.d |= 64;
                           var19 = var13.a(var17, var19, false);
                           var13.a(var17 << 16, var19 << 16);
                           if (a) {
                              y.br = 0;
                           }
                        }
                        break;
                     case 88:
                        ah var12;
                        if ((var12 = e(var1.a[2])) != null) {
                           var12.a[15] = var1.a[0];
                        }
                        break;
                     case 89:
                        ah var11;
                        if ((var11 = e(var1.a[2])) != null) {
                           if (var1.a[0] == -1) {
                              y.a(y.a[var11.a[3]], 0);
                              var11.a[15] = -1;
                              var11.b((byte)1);
                           } else {
                              y.a[var11.a[3]][var1.a[0]] = 0;
                              if (y.a != null && y.a == var11 && y.a.a[15] == var1.a[0]) {
                                 y.a.a[15] = -1;
                                 var11.b((byte)1);
                              }
                           }
                        }
                        break;
                     case 90:
                        int var16 = var1.a[2] - (y.a >> 1);
                        int var18 = var1.a[3] - (y.b >> 1);
                        a(var16, var18);
                        y.c(11);
                        y.bD = var1.a[4];
                        break;
                     case 91:
                        ah var10;
                        if ((var10 = e(var1.a[2])) != null) {
                           var10.d |= 24;
                           if (var10.a == 19) {
                              a(var10);
                           }
                        }
                        break;
                     case 92:
                        y.bt = 0;
                        y.c(12);
                        break;
                     case 93:
                        ah var9;
                        if ((var9 = e(var1.a[2])) != null) {
                           y.a[var9.a[3]][var1.a[0]] = var1.a[1];
                        }
                        break;
                     case 94:
                        ah var2;
                        if ((var2 = e(var1.a[4])) == null) {
                           break;
                        }

                        short var3 = var1.a[2];
                        short var4 = var1.a[3];
                        if (var2.a == 1 && (var2.d & 24) == 0) {
                           if (var1.a[0] == 1) {
                              var2.d(var3 << 16, var4 << 16);
                           } else {
                              var2.c(var3 << 16, var4 << 16);
                           }
                        } else {
                           if (var2.a == 19 && (var2.d & 24) == 0) {
                              int var5 = var2.q;
                              var2.a(0);
                              var2.r = -1;
                              var2.b((int)var2.a[1]);
                              var2.a[0] = var3 << 16;
                              var2.a[1] = var4 << 16;
                              var2.a[0] = 1;
                              var2.a(var5);
                              break;
                           }

                           var2.e = var3 << 16;
                           var2.f = var4 << 16;
                        }
                        break;
                     case 95:
                        while (y.bF >= 0) {
                           y.k(1);
                        }

                        y.d = null;
                        break;
                     case 96:
                        if (var1.a[0] == 0) {
                           y.i = true;
                        } else {
                           y.i = false;
                        }
                  }
               }
            }
         }

         if (y.r != y.s) {
            if (y.t == 2) {
               if (y.w < 0) {
                  if ((y.w = y.w + y.x) > 0) {
                     y.w = y.x;
                  }
               } else {
                  y.e(3);
               }
            }
         } else if (y.u < y.v) {
            y.u++;
         } else {
            y.e(1);
         }

         if (y.t == 1) {
            if (y.w > y.y) {
               if ((y.w = y.w - y.x) <= y.y) {
                  y.w = y.y;
                  return;
               }
            } else {
               y.e(0);
            }
         }
      } catch (Exception var6) {
      }
   }

   static void a(n var0) {
      try {
         var0.d(0, 0, j.K, j.L);
         d();
         a(var0, true);
         if (y.z == 1 && !y.f && y.p != 12) {
            y.b.a();
            p.a(y.d);
            int var2 = p.b();
            int var3 = 32 - var2 >> 1;
            if (y.t == 2) {
               y.a[5].a(60, 50, y.w, 0);
               y.b.a(y.q + y.s);
               y.a(y.b, 50, y.w + var3, 24, y.d);
            }

            if (y.t == 3) {
               y.a[5].a(60, 50, 0, 0);
               if (y.s < y.r) {
                  y.s += 2;
               }

               if (y.s > y.r) {
                  y.s = y.r;
               }

               y.b.a(y.q + y.s);
               y.a(y.b, 50, var3, 24, y.d);
            }

            if (y.t == 1) {
               y.a[5].a(60, 50, y.w, 0);
               y.b.a(y.q + y.s);
               y.a(y.b, 50, y.w + var3, 24, y.d);
            }

            if (y.t == 0) {
               y.a[5].a(60, 50, y.y, 0);
               y.b.a(y.q + y.s);
               y.a(y.b, 50, y.y + var3, 24, y.d);
            }
         }

         if (y.z == 3 && y.p != 17 && y.o != 20) {
            y.i();
         }
      } catch (Exception var1) {
      }
   }

   private static void y() {
      a = 0;

      for (int var0 = 0; var0 < 3; var0++) {
         if (y.s[var0] != 0) {
            a++;
         }
      }
   }

   private static void z() {
      if (!a) {
         y.a(y.s, 0);

         for (int var0 = t; var0 < t + u; var0++) {
            if (a[var0].a == 1 && !a[var0].b()) {
               if (y.A == 1 && y.z == 2) {
                  a[var0].h = 200;
               }

               y.s[a[var0].a[3]] = y.s[a[var0].a[3]] + a[var0].h;
            }
         }

         y.bw = y.s[0];

         for (int var1 = 1; var1 < y.s.length; var1++) {
            if (y.bw < y.s[var1]) {
               y.bw = y.s[var1];
            }
         }
      }
   }

   static void c() {
      if (!a) {
         System.arraycopy(y.s, 0, y.t, 0, 3);
         y.a(y.s, 0);

         for (int var0 = t; var0 < t + u; var0++) {
            if (a[var0].a == 1 && !a[var0].b()) {
               y.s[a[var0].a[3]] = y.s[a[var0].a[3]] + a[var0].h;
            }
         }

         y.g = true;

         for (int var1 = 0; var1 < 3; var1++) {
            y.u[var1] = (y.t[var1] - y.s[var1]) / 20;
            y.u[var1] = y.u[var1] + ((y.u[var1] > 0 ? 1 : -1) << 1);
         }

         y.b = s.a();
      }
   }

   static void d() {
      y.a(k, 0);

      for (int var0 = 0; var0 < D; var0++) {
         if (a[var0] == 1) {
            ah var1;
            int var2 = (var1 = a[var0]).g;
            if (y.a(
                  f - 100,
                  g - 80,
                  y.a + 100 + 100,
                  y.b + 80 + 80,
                  (var1.e >> 16) + var1.w[0],
                  (var1.f >> 16) + var1.w[1],
                  var1.w[2] - var1.w[0],
                  var1.w[3] - var1.w[1]
               )
               || var1 == y.a) {
               a[var2][k[var2]] = a[var0];
               k[var2]++;
            }
         }
      }
   }

   private static void A() {
      if (y.z == 0 || y.z == 2 && y.B == 0) {
         if (y.z == 0) {
            y.h[0] = y.C[0];
            y.i[0] = y.D[0];
            y.j[0] = y.E[0];
            y.h[1] = y.C[y.a + 1];
            y.i[1] = y.D[y.a + 1];
            y.j[1] = y.E[y.a + 1];
         } else {
            y.h[1] = y.h[0];
            y.h[2] = y.h[0];

            while (y.h[0] == y.h[1] || y.h[1] == y.h[2] || y.h[0] == y.h[2]) {
               int var0 = y.b(0, 4);
               int var1 = y.b(0, 4);
               y.h[1] = y.C[var0];
               y.i[1] = y.D[var0];
               y.j[1] = y.E[var0];
               y.h[2] = y.C[var1];
               y.i[2] = y.D[var1];
               y.j[2] = y.E[var1];
            }
         }
      } else if (y.z == 1) {
         y.h[0] = y.C[0];
         y.i[0] = y.D[0];
         y.j[0] = y.E[0];
         if (y.D != -1 && !y.c()) {
            y.h[1] = y.C[y.a[y.D][3] + 1];
            y.i[1] = y.D[y.a[y.D][3] + 1];
            y.j[1] = y.E[y.a[y.D][3] + 1];
            y.h[2] = y.C[y.a[y.D][7] + 1];
            y.i[2] = y.D[y.a[y.D][7] + 1];
            y.j[2] = y.E[y.a[y.D][7] + 1];
         }
      }

      for (int var2 = 0; var2 < D; var2++) {
         if (a[var2] == 1 && a[var2].a == 1) {
            a[var2].d(y.h[a[var2].a[3]]);
         }
      }
   }

   public static void a(boolean var0, int var1) {
      var1 = 0;
      boolean var2 = y.c && y.D % 7 == 6;
      i = true;
      if (!var0) {
         var1 = bs;
      }

      if (y.A == 6) {
         int var3 = 17 + y.g;
         if (y.a[var3] == null) {
            try {
               y.f(var3);
            } catch (Exception var13) {
            }
         }

         var3 = 17 + y.h;
         if (y.a[var3] == null) {
            try {
               y.f(var3);
            } catch (Exception var12) {
            }
         }
      }

      a = b(7);
      if (y.A == 6) {
         ah var19 = b(34);
         int var4 = G + 100;
         int var5 = G + 220;
         var4 = var4 + var5 >> 1;
         var5 = H;
         var19.e = var4 << 16;
         var19.f = var5 << 16;
         var19.q = 17 + y.g;
         var19.b(0);
         var19.d &= -65;
         var19 = b(34);
         var4 = G + 420;
         var5 = G + 540;
         var4 = var4 + var5 >> 1;
         var5 = H;
         var19.e = var4 << 16;
         var19.f = var5 << 16;
         var19.q = 17 + y.h;
         var19.b(0);
         var19.d &= -65;
      }

      if (!var0 && !var2) {
         g(var1 + 1);
         a((short)62, null, false);
      } else {
         int[] var21 = y.a;
         t = -1;
         var1 = 0;

         for (int var17 = 0; var17 < var21.length; var17++) {
            for (int var14 = 0; var14 < var21[var17]; var14++) {
               int var34 = var1;
               int var27 = var17;
               int var7 = 0;
               int var8 = 0;
               ah var9 = b(1);
               boolean var6 = false;
               int var10 = y.c && y.D % 7 == 6;
               if (y.A == 6) {
                  if (var27 == 0) {
                     var27 = G + 20;
                     var10 = G + 220;
                  } else {
                     var27 = G + 340;
                     var10 = G + 640;
                  }
               } else if (var10) {
                  if (var27 == 0) {
                     var27 = G + 40;
                     var10 = G + 1080;
                  } else {
                     var27 = G + 40;
                     var10 = G + 1080;
                  }
               } else {
                  var27 = 196;
                  var10 = 736;
               }

               while (!var6) {
                  var7 = a(var27, var10);
                  var8 = a(96, 416);
                  var9.e = var7 << 16;
                  var9.f = var8 << 16;
                  if (var9.b(var7 << 16, var8 << 16)) {
                     if ((var8 = var9.a(var7, var8, true)) > 24 && var8 < b) {
                        var6 = true;
                     }
                  } else if ((var8 = var9.a(var7, var8, false)) < b) {
                     var6 = true;
                  }

                  if (var6 && t != -1) {
                     for (int var11 = t; var11 < t + var34; var11++) {
                        if ((
                              Math.abs((a[var11].e >> 16) - var7) > Math.abs((a[var11].f >> 16) - var8)
                                 ? Math.abs((a[var11].e >> 16) - var7)
                                 : Math.abs((a[var11].f >> 16) - var8)
                           )
                           < 30) {
                           var6 = false;
                           break;
                        }
                     }
                  }
               }

               var9.a(var7 << 16, var8 << 16);
               if (t == -1) {
                  t = var9.b;
               }

               ah var29 = var9;
               var1++;
               var29.f(var17);
               if (!y.c()) {
                  var29.d |= 16;
               }

               if (y.z == 1) {
                  if (y.A == 3) {
                     var29.h = 75;
                  } else if (y.D == 6 || y.D == 20) {
                     var29.h = 250;
                  } else if (var29.a[3] == 0) {
                     var29.h = 100;
                  } else if (var29.a[3] == 1) {
                     var29.h = y.a[y.D][5];
                  } else if (var29.a[3] == 2) {
                     var29.h = y.a[y.D][9];
                  }
               }

               boolean var35 = y.c && y.D % 7 == 6;
               if (y.A == 6 || var35) {
                  var29.e(var17 == 0 ? 1 : -1);
               }

               y.q[var17]++;
            }
         }

         u = var1;
         if (y.z != 3 && y.A != 6) {
            c(3, 12);
            c(3, 13);
         }

         if (y.z == 1) {
            new StringBuffer().append("total coins required:").append(y.a[y.D][14]).toString();
            c(y.a[y.D][14], 31);
         }
      }

      b.a();
      System.gc();
      e();
      z();
      x();
      y();
      A();
      ah.j = true;
      if (y.a != null) {
         int var22 = 0;

         for (int var30 = t; var22 < y.a[0]; var30++) {
            ah.h[var22] = var30;
            var22++;
         }
      }

      for (int var23 = 0; var23 < j.e.length; var23++) {
         j.e[var23] = 0;
         j.f[var23] = 0;
      }
   }

   static void a(int var0) {
      if (bz != -1) {
         y.g(bz);
      }

      if (var0 != -1) {
         by = y.i;
      } else {
         by = y.c(5) + 21;
      }

      try {
         y.f(by);
         byte var10000;
         switch (by) {
            case 21:
               var10000 = 26;
               break;
            case 22:
               var10000 = 27;
               break;
            case 23:
               var10000 = 28;
               break;
            case 24:
               var10000 = 29;
               break;
            case 25:
               var10000 = 30;
               break;
            default:
               var10000 = 21;
         }

         bz = var10000;
         y.f(var10000);
      } catch (Exception var9) {
      }

      G = 96;
      H = 96;
      I = 32;
      bC = 2 * G / 32;
      bA = 20 + bC;
      bB = 10 + (H + I) / 32;
      p = 640 + 2 * G;
      b = (q = 320 + H + I) - 32;
      if (w == null) {
         w = new byte[bA * bA];
      }

      y.a(w, (byte)32);

      for (int var6 = 0; var6 < 200; var6++) {
         boolean var1 = false;
         boolean var2 = false;
         byte var3 = -1;
         int var4 = var6 % 20;
         int var5 = var6 / 20;

         for (int var7 = 0; var7 < 32; var7++) {
            for (int var8 = 0; var8 < 8; var8++) {
               if ((var0 = var5 * 2560 + var7 * 80 + (var4 << 2) + var8) < x.length) {
                  byte var11 = x[var0];
                  if (var11 == -1) {
                     var2 = true;
                  } else {
                     if (var11 != 0) {
                        var3 = 16;
                        break;
                     }

                     var1 = true;
                  }
               } else {
                  var1 = true;
               }
            }

            if (var3 == 16) {
               break;
            }

            if (var1 && var2) {
               var3 = 16;
            } else if (var1) {
               var3 = 32;
            } else {
               var3 = 8;
            }
         }

         int var12 = var6 + bC / 2 + var6 / 20 * bC + H / 32 * bA;
         w[var12] = var3;
      }
   }

   public static void e() {
      l = 30;
      bt = 8;
      k = 0;
      g = false;
      m = j.K / 2;
      n = j.L / 2;
      f = 0;
      g = 0;
   }

   public static void a(int var0, int var1) {
      i = var0 < 0 ? 0 : var0;
      j = var1 < 0 ? 0 : var1;
      bu = m - f;
      bv = n - g;
      bt = 8;
      c(1);
   }

   public static void b(int var0) {
      o = var0;
      bt = 8;
      c(3);
   }

   static void c(int var0) {
      if (h != var0) {
         switch (h) {
            case 0:
               ah.d = false;
            case 1:
            default:
               break;
            case 2:
               m = f + j.K / 2;
               n = g + j.L / 2;
               break;
            case 3:
               o = -1;
               break;
            case 4:
               h = false;
         }

         h = var0;
      }
   }

   static void f() {
      switch (h) {
         case 0:
            if (f > m - 80 && (f = m - 80) < 0) {
               f = 0;
            }

            if (f < m + 80 - y.a && (f = m + 80 - y.a) > p - y.a) {
               f = p - y.a;
            }

            if (g > n - 80 && (g = n - 80) < 0) {
               g = 0;
            }

            if (g + y.b < n + 80 && (g = n + 80 - y.b) > q - y.b) {
               g = q - y.b;
            }

            return;
         case 1:
            boolean var2 = false;
            if ((bt += 4) > l) {
               bt = l;
            }

            label124: {
               if (a()) {
                  if (f < 0) {
                     f = 0;
                     var2 = true;
                  }

                  if (f > p - y.a) {
                     f = p - y.a;
                     var2 = true;
                  }

                  if (g < 0) {
                     g = 0;
                     var2 = true;
                  }

                  if (g <= q - y.b) {
                     break label124;
                  }

                  g = q - y.b;
               } else {
                  if (f < 0) {
                     f = 0;
                  }

                  if (f > p - y.a) {
                     f = p - y.a;
                  }

                  if (g < 0) {
                     g = 0;
                  }

                  if (g > q - y.b) {
                     g = q - y.b;
                  }
               }

               var2 = true;
            }

            m = f + bu;
            n = g + bv;
            if (var2) {
               c(2);
            }

            return;
         case 2:
            f = m - (y.a >> 1);
            g = n - (y.b >> 1);
            if (f < 0) {
               f = 0;
            }

            if (f > p - y.a) {
               f = p - y.a;
            }

            if (g < 0) {
               g = 0;
            }

            if (g > q - y.b) {
               g = q - y.b;
            }

            return;
         case 3:
            ah var0;
            if ((var0 = a(o)) != null) {
               if ((bt += 4) > l) {
                  bt = l;
               }

               byte var1 = 0;
               if (y.b() == 7 && y.d != null && y.c()) {
                  var1 = 40;
               }

               if (var0.a == 1 && var0 == y.a && !y.j && ah.a((int)var0.a[15])) {
                  i = (var0.e >> 16) - (y.a >> 1) + var0.a[5] * j.bq;
               } else {
                  i = (var0.e >> 16) - (y.a >> 1);
               }

               j = (var0.f >> 16) - (y.b >> 1) - var1;
               bu = m - f;
               bv = n - g;
               if (i < 0) {
                  i = 0;
               }

               if (i > p - y.a) {
                  i = p - y.a;
               }

               if (j < 0) {
                  j = 0;
               }

               if (j > q - y.b) {
                  j = q - y.b;
               }

               a();
               m = f + bu;
               n = g + bv;
            }
      }
   }

   private static boolean a() {
      int var0 = i - f;
      int var1 = j - g;
      int var2 = Math.abs(var0);
      int var3 = Math.abs(var1);
      int var4 = var2 + var3;
      g = false;
      if (var4 <= 0) {
         f = i;
         g = j;
         g = true;
         return false;
      }

      if (var4 <= bt) {
         f = i;
         g = j;
         g = false;
         return true;
      }

      var4 = var2 > var3 ? var2 : var3;
      var0 = (var0 * bt << 6) / var4;
      var1 = (var1 * bt << 6) / var4;
      if (k != 0 && (var2 = Math.abs(var0) + Math.abs(var1)) > k) {
         var0 = (var0 * k << 6) / var2;
         var1 = (var1 * k << 6) / var2;
      }

      f = (f << 6) + var0 >> 6;
      g = (g << 6) + var1 >> 6;
      return true;
   }

   static void g() {
      if (m) {
         m = false;
         int var10000 = j[0];
         int var10001 = j[1];
         int var10002 = j[2] - j[0];
         int var3 = j[3] - j[1];
         int var2 = var10002;
         int var1 = var10001;
         int var0 = var10000;
         int var4 = var10000;
         int var5 = var1;
         var2 = var0 + var2;
         var3 = var1 + var3;
         int var6 = bD << 5;
         int var7 = bE << 5;
         int var8 = bF << 5;
         int var9 = bG << 5;
         int var10 = y.e;
         int var11 = y.f;
         if (var0 < var6) {
            var4 = var6;
         }

         if (var2 > var6 + var10) {
            var2 = var6 + var10;
         }

         if (var2 - var4 > 0) {
            if (var1 < var7) {
               var5 = var7;
            }

            if (var3 > var7 + var11) {
               var3 = var7 + var11;
            }

            if (var3 - var5 > 0) {
               if ((var1 = (var0 = var4 + var8 - var6) - var10) < 0) {
                  var1 = 0;
               }

               var2 -= var4;
               if ((var6 = var10 - var0) > var2) {
                  var6 = var2;
               }

               if ((var8 = var2 - var6) > var2) {
                  var8 = var2;
               }

               var2 = var4 + var2 - var8;
               if ((var9 = (var7 = var5 + var9 - var7) - var11) < 0) {
                  var9 = 0;
               }

               var3 -= var5;
               if ((var10 = var11 - var7) > var3) {
                  var10 = var3;
               }

               if ((var11 = var3 - var10) > var3) {
                  var11 = var3;
               }

               var3 = var5 + var3 - var11;
               if (var6 > 0) {
                  if (var10 > 0) {
                     a(var0, var7, var6, var10, var4, var5, false);
                  }

                  if (var11 > 0) {
                     a(var0, var9, var6, var11, var4, var3, false);
                  }
               }

               if (var8 > 0) {
                  if (var10 > 0) {
                     a(var1, var7, var8, var10, var2, var5, false);
                  }

                  if (var11 > 0) {
                     a(var1, var9, var8, var11, var2, var3, false);
                  }
               }
            }
         }

         y.a(j);
      }
   }

   static void h() {
      if (!i) {
         int var0;
         if ((var0 = f - (bD << 5)) < 0) {
            if ((var0 = (31 - var0) / 32) >= y.c) {
               i = true;
            } else {
               bF -= var0;
               bD -= var0;
               if (bF < 0) {
                  int var1;
                  if ((var1 = var0 + bF) != 0) {
                     b(0, var1, bD - bF);
                  }

                  b(bF = bF + y.c, var0 - var1, bD);
               } else {
                  b(bF, var0, bD);
               }
            }
         } else if (var0 > y.e - y.a) {
            if ((var0 = (var0 - (y.e - y.a) + 31) / 32) >= y.c) {
               i = true;
            } else {
               int var7;
               if ((var7 = bF + var0 - y.c) >= 0) {
                  b(bF, y.c - bF, bD + y.c);
                  if (var7 != 0) {
                     b(0, var7, bD + y.c + y.c - bF);
                  }

                  bF = var7;
               } else {
                  b(bF, var0, bD + y.c);
                  bF += var0;
               }

               bD += var0;
            }
         }
      }

      if (!i) {
         int var4;
         if ((var4 = g - (bE << 5)) < 0) {
            if ((var4 = (31 - var4) / 32) >= y.d) {
               i = true;
            } else {
               bG -= var4;
               bE -= var4;
               if (bG < 0) {
                  int var8;
                  if ((var8 = var4 + bG) != 0) {
                     c(0, var8, bE - bG);
                  }

                  c(bG = bG + y.d, var4 - var8, bE);
               } else {
                  c(bG, var4, bE);
               }
            }
         } else if (var4 > y.f - y.b) {
            if ((var4 = (var4 - (y.f - y.b) + 31) / 32) >= y.d) {
               i = true;
            } else {
               int var9;
               if ((var9 = bG + var4 - y.d) >= 0) {
                  c(bG, y.d - bG, bE + y.d);
                  if (var9 != 0) {
                     c(0, var9, bE + y.d + y.d - bG);
                  }

                  bG = var9;
               } else {
                  c(bG, var4, bE + y.d);
                  bG += var4;
               }

               bE += var4;
            }
         }
      }

      if (i) {
         i = false;
         if ((bD = f / 32) > bA - y.c) {
            bD = bA - y.c;
         }

         if ((bE = g / 32) > bB - y.d) {
            bE = bB - y.d;
         }

         bF = 0;
         bG = 0;
         a(0, 0, y.c, y.d, bD, bE);
      }
   }

   private static void b(int var0, int var1, int var2) {
      if (bG > 0) {
         a(var0, 0, var1, bG, var2, bE + y.d - bG);
         a(var0, bG, var1, y.d - bG, var2, bE);
      } else {
         a(var0, 0, var1, y.d, var2, bE);
      }
   }

   private static void c(int var0, int var1, int var2) {
      if (bF > 0) {
         a(0, var0, bF, var1, bD + y.c - bF, var2);
         a(bF, var0, y.c - bF, var1, bD, var2);
      } else {
         a(0, var0, y.c, var1, bD, var2);
      }
   }

   private static void a(int var0, int var1, int var2, int var3, int var4, int var5) {
      var0 <<= 5;
      var1 <<= 5;
      int var6 = var2 << 5;
      int var7 = var3 << 5;
      y.a.d(var0, var1, var6, var7);
      ac.a(y.a);
      if (var2 == 1) {
         var2 = var1 + var7;
         var3 = var4 + var5 * bA;

         for (int var18 = var1; var18 < var2; var3 += bA) {
            d(var3, var0, var18);
            var18 += 32;
         }
      } else if (var3 == 1) {
         var2 = var0 + var6;
         var3 = var4 + var5 * bA;

         for (int var17 = var0; var17 < var2; var3++) {
            d(var3, var17, var1);
            var17 += 32;
         }
      } else {
         var2 = var0 + var6;
         var3 = var1 + var7;

         for (int var16 = var4 + var5 * bA; var1 < var3; var16 += bA) {
            var5 = var16;

            for (int var20 = var0; var20 < var2; var5++) {
               d(var5, var20, var1);
               var20 += 32;
            }

            var1 += 32;
         }
      }
   }

   private static void a(int var0, int var1, int var2, int var3, int var4, int var5, boolean var6) {
      while (var2 * var3 > 20480) {
         if (var2 > var3) {
            a(var0, var1, var2 >> 1, var3, var4, var5, (boolean)var6);
            int var10000 = var0 + (var2 >> 1);
            int var10002 = var2 - (var2 >> 1);
            int var10004 = var4 + (var2 >> 1);
            var5 = var5;
            var4 = var10004;
            var3 = var3;
            var2 = var10002;
            var1 = var1;
            var0 = var10000;
         } else {
            a(var0, var1, var2, var3 >> 1, var4, var5, (boolean)var6);
            int var10001 = var1 + (var3 >> 1);
            int var10003 = var3 - (var3 >> 1);
            var5 += var3 >> 1;
            var4 = var4;
            var3 = var10003;
            var2 = var2;
            var1 = var10001;
            var0 = var0;
         }
      }

      ac.a = 0;
      ac.a(y.a);
      var0 = var4 / 32;
      var1 = var5 / 32;
      var6 = var0 + var1 * bA;
      var0 = (var0 << 5) - var4;

      for (int var12 = (var1 << 5) - var5; var12 < var3; var6 += bA) {
         int var7 = var6;

         for (int var8 = var0; var8 < var2; var7++) {
            d(var7, var8, var12);
            var8 += 32;
         }

         var12 += 32;
      }

      var1 = f;
      int var15 = g;
      f = var4;
      g = var5;
      f = var1;
      g = var15;
      ac.a = 0;
   }

   private static void d(int var0, int var1, int var2) {
      if (var0 >= 0) {
         y.a.d(var1, var2, 32, 32);
         ac.a(y.a);
         int var3 = var0 / bA;
         int var4;
         int var5 = (var4 = bB - 1) - 6;
         if (var3 < var4 && var3 >= var5) {
            var4 = var0 % bA % 10 << 5;
            var3 = (var3 - var5) % 6 << 5;
            y.a[bz].a(0, var1 - var4, var2 - var3, 0);
         } else {
            int var10001;
            label93: {
               if (bz != 26) {
                  if (bz == 27) {
                     var10001 = 1310720;
                     break label93;
                  }

                  if (bz == 28) {
                     var10001 = 14121317;
                     break label93;
                  }

                  if (bz == 29) {
                     var10001 = 1055000;
                     break label93;
                  }

                  if (bz == 30) {
                     var10001 = 2691901;
                     break label93;
                  }
               }

               var10001 = 1118556;
            }

            y.a.a(var10001);
            y.a.c(var1, var2, 32, 32);
         }

         if (0 < y.a[by].b) {
            if ((w[var0] & 8) != 0) {
               ac.a = 0;
               y.a[by].a(0, var1, var2, 0);
               return;
            }

            if ((w[var0] & 16) != 0) {
               n var10000 = y.a;
               y.a(i, 0);
               var3 = var0 % bA;
               var4 = var0 / bA;
               int[] var17;
               if (var3 < bC / 2 || var3 >= 20 + bC / 2) {
                  var17 = i;
               } else if (var4 >= H / 32 && var4 < 10 + H / 32) {
                  if (w[var0] == 16) {
                     if (b == null) {
                        b = new int[y.a[by].b][];
                     }

                     if (b[0] == null) {
                        b[0] = new int[1024];
                        System.arraycopy(y.a[by].b(0, 0, 0), 0, b[0], 0, 1024);
                     }

                     System.arraycopy(b[0], 0, i, 0, 1024);
                     var4 = 0;
                     var3 = var0 % bA << 5;
                     var5 = var0 / bA << 5;

                     for (int var8 = 0; var8 < 32; var8++) {
                        for (int var9 = 0; var9 < 32; var9++) {
                           int var7 = b(var3 + var8, var5 + var9);
                           int var6 = var8 + (var9 << 5);
                           if (var7 < 1) {
                              i[var6] = 0;
                           } else {
                              var4++;
                           }
                        }
                     }

                     if (var4 == 0) {
                        w[var0] = 32;
                     }
                  }

                  var17 = i;
               } else {
                  var17 = i;
               }

               var10000.a(var17, 32, var1, var2, 32, 32);
            }
         }
      }
   }

   private static int[] a(int var0, int var1) {
      int[] var2 = new int[4];
      int var3 = (int)((long)var0 * var0 >> 16);
      int var4 = (int)((long)var0 * var3 >> 16);
      var2[0] = -var4 + var3 * 3 - var0 * 3 + 65536;
      var2[1] = var4 * 3 - var3 * 6 + 262144;
      var2[2] = var4 * -3 + var3 * 3 + var0 * 3 + 65536;
      var2[3] = var4;
      int[] var5 = new int[2];

      for (int var6 = 0; var6 < 2; var6++) {
         var5[var6] = (
               (int)((long)var2[0] * a[var1][var6] >> 16)
                  + (int)((long)var2[1] * a[var1 + 1][var6] >> 16)
                  + (int)((long)var2[2] * a[var1 + 2][var6] >> 16)
                  + (int)((long)var2[3] * a[var1 + 3][var6] >> 16)
            )
            / 6;
      }

      return var5;
   }

   static void i() {
      int var0 = 6291456;
      int var1 = 6291456;
      B = bI;

      for (int var2 = 0; var2 < bI; var2++) {
         ah var3;
         (var3 = b(200)).c();
         if (var2 == 0) {
            A = var3.b;
         }

         var3.a = new short[20];
         var3.a = new int[20];
         var3.a = new byte[10];
         var3.e = a[var2][0] + var0;
         var3.f = a[var2][1] + var1;
      }

      ah.l();
   }

   private static void B() {
      int var0 = 0;
      int var1 = 0;
      boolean var3 = true;

      for (int var4 = 0; var4 < bI - 4; var4++) {
         for (short var5 = 0; var5 < 65536; var5 += 13107) {
            int[] var2;
            if (var3) {
               var3 = false;
               var2 = a(var5, var4);
            } else {
               var2 = a(var5, var4);
               int var10000 = var0 >> 8;
               int var10001 = var1 >> 8;
               int var10002 = var2[0] >> 8;
               int var7 = var2[1] >> 8;
               int var6 = var10002;
               var1 = var10001;
               var0 = var10000;
               if (var10000 > var6) {
                  int var8 = var0;
                  var0 = var6;
                  var6 = var8;
                  var8 = var1;
                  var1 = var7;
                  var7 = var8;
               }

               int var10 = var0;
               int var11 = var6;
               var0 = var10 + 128 - 1 >> 8;
               var6 = var11 + 128 - 1 >> 8;
               if (var0 != var6) {
                  var1 <<= 8;
                  var7 <<= 8;
                  int var24 = var11 - var10;
                  int var9 = (var7 - var1) / var24;
                  var24 = (var0 << 8) + 128 - var10;
                  var1 += var9 * var24;
                  var24 = (var6 << 8) + 128 - var11;
                  var7 += var9 * var24;
                  var1 += 32768;
                  var24 = var6 - var0;

                  for (int var28 = (var7 - var1) / var24; var24-- > 0; var1 += var28) {
                     var10000 = var0++;
                     var7 = var1 >> 16;
                     var6 = var10000;
                     if (var7 < 0) {
                        var7 = 0;
                     }

                     a(var6, var7, (byte)(b(var6, var7) ^ 1));
                  }
               }
            }

            var0 = var2[0];
            var1 = var2[1];
         }
      }
   }

   static void d(int var0) {
      for (int var1 = var0 << 5; var1 < var0 + 1 << 5; var1++) {
         byte var3 = 0;

         for (int var2 = 0; var2 < bQ; var2++) {
            if ((var3 = (byte)(var3 ^ b(var1, var2))) != 0) {
               byte var4 = var3;
               a(var1, var2, var4);
            }
         }
      }
   }

   static int a() {
      return bJ = bJ * 1664525 + 1013904223;
   }

   static int a(int var0, int var1) {
      return var0 + Math.abs(a()) % (var1 - var0);
   }

   private static void b(int var0, int var1) {
      bI = 0;

      for (int var2 = 0; var2 < 3; var2++) {
         a[bI][0] = c[0][0];
         a[bI][1] = c[0][1];
         bI++;
      }

      int var3 = 0;

      for (int var4 = 1; var4 < bH; var3 = var4++) {
         a(c[var3][0], c[var3][1], c[var4][0], c[var4][1], true, 1);
      }

      var3 = 0;

      for (int var5 = 1; var5 < bH; var3 = var5++) {
         a(c[var3][0], c[var3][1], c[var5][0], c[var5][1], var1, a(), var0);
      }

      for (int var6 = 0; var6 < 4; var6++) {
         a[bI][0] = c[bH - 1][0];
         a[bI][1] = c[bH - 1][1];
         bI++;
      }

      y.a(x, (byte)0);
      B();
   }

   static int a(int var0, int var1, int var2, int var3, boolean var4, int var5) {
      var0 >>= 8;
      var2 >>= 8;
      var1 >>= 8;
      var3 >>= 8;
      if (Math.abs(var2 - var0) >= Math.abs(var3 - var1)) {
         var4 = (boolean)var4;
         var3 = var3;
         var2 = var2;
         var1 = var1;
         var0 = var0;
         int var11 = 0;
         int var8 = var0;
         int var9 = var2;
         var0 = var0 + 128 - 1 >> 8;
         var2 = var2 + 128 - 1 >> 8;
         int var64;
         if (var0 == var2) {
            var64 = 0;
         } else {
            var1 <<= 8;
            var3 <<= 8;
            int var6 = var9 - var8;
            int var7 = (var3 - var1) / var6;
            var8 = (var0 << 8) + 128 - var8;
            var1 += var7 * var8;
            var8 = (var2 << 8) + 128 - var9;
            var3 += var7 * var8;
            var6 = var2 - var0;
            var7 = var3 - var1;
            var2 = Math.abs(var6);
            var3 = var6 < 0 ? -1 : 1;
            var1 += 32768;
            var7 /= var2;
            if (var4) {
               while (var2-- > 0) {
                  a(var0, var1 >> 16, (byte)var5);
                  var0 += var3;
                  var1 += var7;
               }

               var64 = 0;
            } else {
               int var10 = 0;

               while (var2-- > 0) {
                  var5 = var10;
                  var4 = var1 >> 16;
                  var10 += b(var0, var4);
                  if ((var1 += var7) >> 16 != var4) {
                     var10 += b(var0, var1 >> 16);
                  }

                  if (var10 != var5) {
                     var11++;
                     bR = var0 << 16;
                     bS = var1;
                     if (var11 == 1) {
                        r = bR;
                        s = bS;
                     }
                  }

                  var0 += var3;
               }

               var64 = var10;
            }
         }

         var0 = var64;
      } else {
         int var44 = var4;
         var3 = var3;
         var2 = var2;
         var1 = var1;
         var0 = var0;
         int var62 = 0;
         int var58 = var1;
         int var61 = var3;
         var1 = var1 + 128 - 1 >> 8;
         var3 = var3 + 128 - 1 >> 8;
         int var67;
         if (var1 == var3) {
            var67 = 0;
         } else {
            var0 <<= 8;
            var2 <<= 8;
            int var54 = var61 - var58;
            int var49 = (var2 - var0) / var54;
            var58 = (var1 << 8) + 128 - var58;
            var0 += var49 * var58;
            var58 = (var3 << 8) + 128 - var61;
            var49 = var2 + var49 * var58 - var0;
            var2 = Math.abs(var54 = var3 - var1);
            var3 = var54 < 0 ? -1 : 1;
            var0 += 32768;
            var49 /= var2;
            if (var44) {
               while (var2-- > 0) {
                  a(var0 >> 16, var1, (byte)var5);
                  var1 += var3;
                  var0 += var49;
               }

               var67 = 0;
            } else {
               int var63 = 0;

               while (var2-- > 0) {
                  var5 = var63;
                  var44 = var0 >> 16;
                  var63 += b(var44, var1);
                  if ((var0 += var49) >> 16 != var44) {
                     var63 += b(var0 >> 16, var1);
                  }

                  if (var63 != var5) {
                     var62++;
                     bR = var0;
                     bS = var1 << 16;
                     if (var62 == 1) {
                        r = bR;
                        s = bS;
                     }
                  }

                  var1 += var3;
               }

               var67 = var63;
            }
         }

         var0 = var67;
      }

      return var0;
   }

   private static void a(int var0, int var1, int var2, int var3, int var4, int var5, int var6) {
      while (true) {
         y.o[0] = var2 - var0;
         y.o[1] = var3 - var1;
         int var8;
         if ((var8 = y.a(y.o)) >> 16 < var4) {
            a[bI][0] = var0;
            if (var6 == 1) {
               if (var1 >> 16 < j.bp) {
                  a[bI][1] = var1 + (j.bn << 16);
               } else if (var1 >> 16 < j.bn && var1 >> 16 >= j.bp) {
                  a[bI][1] = var1 + (j.bo << 16);
               } else {
                  a[bI][1] = var1;
               }
            } else {
               a[bI][1] = var1;
            }

            bI++;
            return;
         }

         a(var0, var1, var2, var3, true, 0);
         int var9 = var0 + var2 >> 1;
         int var10 = var1 + var3 >> 1;
         if (var9 >> 16 >= 16 && var10 >> 16 >= 16 && var9 >> 16 < bP - 16 && var10 >> 16 < bQ - 16) {
            int var7 = y.c(var8 / bP, 65536);
            int var11 = (int)((long)(bL + (int)((long)(bM - bL) * var7 >> 16)) * var8 >> 16);
            var7 = y.o[0];
            var8 = y.o[1];
            var11 = (int)((long)((var5 & 65535) - 32768) * var11 >> 16);
            var9 -= (int)((long)((int)((long)var8 * var11 >> 16)) * bN >> 16);
            var10 += (int)((long)((int)((long)var7 * var11 >> 16)) * bO >> 16);
            if (var9 >> 16 < 16
               || var10 >> 16 < 16
               || var9 >> 16 >= bP - 16
               || var10 >> 16 >= bQ - 16
               || a(var0, var1, var9, var10, false, 0) != 0
               || a(var9, var10, var2, var3, false, 0) != 0) {
               var9 = var0 + var2 >> 1;
               var10 = var1 + var3 >> 1;
            }
         }

         a(var0, var1, var9, var10, true, 1);
         a(var9, var10, var2, var3, true, 1);
         a(var0, var1, var9, var10, var4, (int)(var5 * 1664525 + 1013904223L), var6);
         var5 = (int)(var5 * 1164525 + 1013904223L + 2000000000L);
         var4 = var4;
         var3 = var3;
         var2 = var2;
         var1 = var10;
         var0 = var9;
      }
   }

   static void a(int var0, int var1, int var2) {
      if (!l) {
         byte[] var3;
         byte[] var10000 = var3 = new byte[4 * (var2 + 1) * (var2 + 1)];
         int var5 = var2;
         byte[] var4 = var10000;
         int var6 = 1 - var2;
         int var7 = 1;
         int var8 = var2 * -2;
         int var9 = 0;
         int var10 = var2;
         int var11 = (var2 << 1) + 1;
         var4[var5 + (var10 + var2) * var11] = 1;
         var4[var5 + 0] = 1;
         var4[var5 + var2 + var10 * var11] = 1;
         var4[0 + var10 * var11] = 1;
         int var13 = var10 * var11;

         for (int var12 = 0; var12 <= var5 + var2; var12++) {
            var4[var12 + var13] = 1;
         }

         while (var9 < var10) {
            if (var6 >= 0) {
               var10--;
               var8 += 2;
               var6 += var8;
            }

            var9++;
            var7 += 2;
            var6 += var7;
            var13 = (var5 + var10) * var11;

            for (int var43 = var2 - var9; var43 <= var2 + var9; var43++) {
               var4[var43 + var13] = 1;
            }

            var13 = (var5 - var10) * var11;

            for (int var44 = var2 - var9; var44 <= var2 + var9; var44++) {
               var4[var44 + var13] = 1;
            }

            var13 = (var5 + var9) * var11;

            for (int var45 = var2 - var10; var45 <= var2 + var10; var45++) {
               var4[var45 + var13] = 1;
            }

            var13 = (var5 - var9) * var11;

            for (int var46 = var2 - var10; var46 <= var2 + var10; var46++) {
               var4[var46 + var13] = 1;
            }
         }

         int var10001 = var0 - var2;
         int var10002 = var1 - var2;
         var7 = var2 << 1;
         var6 = var10002;
         var5 = var10001;
         var4 = var3;

         for (int var18 = 0; var18 < var7; var18++) {
            for (int var40 = 0; var40 < var7; var40++) {
               if (var4[var18 + var40 * (var7 + 1)] != 0) {
                  a(var5 + var18, var6 + var40, (byte)0);
                  int var47 = (var5 + var18 - G) * 170 / (p - 2 * G);
                  var13 = (var6 + var40 - H) * (64 - y.by) / (q - H - I);
                  if (var47 >= 0 && var47 < 170 && var13 >= 0 + y.by && var13 < 64) {
                     var9 = var13 * 170 + var47;
                     y.v[var9] = 0;
                  }
               }
            }
         }

         var11 = var5 / 32 << 5;
         int var48 = var6 / 32 << 5;

         for (int var39 = var11; var39 < var5 + var7; var39 += 32) {
            for (int var36 = var48; var36 < var6 + var7; var36 += 32) {
               if ((var0 = var39 / 32 + var36 / 32 * bA) >= 0 && var0 < bA * (bB - I / 32)) {
                  if (w[var0] == 8) {
                     w[var0] = 16;
                  } else if (w[var0] == 32) {
                     continue;
                  }

                  var1 = var36;
                  var0 = var39;
                  var2 = var39 / 32;
                  int var29 = var1 / 32;
                  var0 = (var0 + 32 - 1) / 32 + 1;
                  var1 = (var1 + 32 - 1) / 32 + 1;
                  if (var2 < bD) {
                     var2 = bD;
                  }

                  if (var0 > bD + y.c) {
                     var0 = bD + y.c;
                  }

                  if (var0 - var2 > 0) {
                     if (var29 < bE) {
                        var29 = bE;
                     }

                     if (var1 > bE + y.d) {
                        var1 = bE + y.d;
                     }

                     if (var1 - var29 > 0) {
                        int var31;
                        if ((var9 = (var31 = var2 + bF - bD) - y.c) < 0) {
                           var9 = 0;
                        }

                        var0 -= var2;
                        if ((var11 = y.c - var31) > var0) {
                           var11 = var0;
                        }

                        if ((var13 = var0 - var11) > var0) {
                           var13 = var0;
                        }

                        var0 = var2 + var0 - var13;
                        int var14;
                        int var15;
                        if ((var15 = (var14 = var29 + bG - bE) - y.d) < 0) {
                           var15 = 0;
                        }

                        var1 -= var29;
                        int var16;
                        if ((var16 = y.d - var14) > var1) {
                           var16 = var1;
                        }

                        int var17;
                        if ((var17 = var1 - var16) > var1) {
                           var17 = var1;
                        }

                        var1 = var29 + var1 - var17;
                        if (var11 > 0) {
                           if (var16 > 0) {
                              a(var31, var14, var11, var16, var2, var29);
                           }

                           if (var17 > 0) {
                              a(var31, var15, var11, var17, var2, var1);
                           }
                        }

                        if (var13 > 0) {
                           if (var16 > 0) {
                              a(var9, var14, var13, var16, var0, var29);
                           }

                           if (var17 > 0) {
                              a(var9, var15, var13, var17, var0, var1);
                           }
                        }
                     }
                  }
               }
            }
         }

         for (int var32 = t; var32 < t + u; var32++) {
            if (a[var32] == 1 && a[var32].a == 1) {
               a[var32].d |= 1024;
            }
         }
      }
   }

   static void j() {
      if (a == null) {
         a = new ah[150];
         a = new byte[150];

         for (int var0 = 0; var0 < 150; var0++) {
            a[var0] = new ah(var0);
         }

         a = new ah[7][150];
         k = new int[7];
      }

      k();
   }

   static void k() {
      for (int var0 = 0; var0 < 150; var0++) {
         if (a[var0] != null) {
            a[var0].a();
            a[var0] = 0;
         }
      }

      D = 0;
      a = null;
   }

   static void a(n var0, boolean var1) {
      ac.a(var0);
      if (var1) {
         n var10 = var0;
         int var2 = f - (bD - bF << 5);
         int var3 = g - (bE - bG << 5);
         var2 = 0 - var2;
         var3 = 0 - var3;
         int var4;
         if ((var4 = 0 + y.a - y.e - var2) > 0) {
            int var5 = y.e + var2;
            int var6 = var2 + y.e;
            int var7;
            if ((var7 = 0 + y.b - y.f - var3) > 0) {
               int var8 = y.f + var3;
               int var9 = var3 + y.f;
               var10.d(0, 0, var5, var8);
               var10.a(y.a, var2, var3, 0);
               var10.d(0, var9, var5, var7);
               var10.a(y.a, var2, var9, 0);
               var10.d(var6, 0, var4, y.b);
               var10.a(y.a, var6, var3, 0);
               var10.d(var6, var9, var4, var7);
               var10.a(y.a, var6, var9, 0);
            } else {
               var10.d(0, 0, var5, y.b);
               var10.a(y.a, var2, var3, 0);
               var10.d(var6, 0, var4, y.b);
               var10.a(y.a, var6, var3, 0);
            }
         } else {
            int var18;
            if ((var18 = 0 + y.b - y.f - var3) > 0) {
               int var19 = y.f + var3;
               int var20 = var3 + y.f;
               var10.d(0, 0, y.a, var19);
               var10.a(y.a, var2, var3, 0);
               var10.d(0, var20, y.a, var18);
               var10.a(y.a, var2, var20, 0);
            } else {
               var10.d(0, 0, y.a, y.b);
               var10.a(y.a, var2, var3, 0);
            }
         }

         var10.d(0, 0, y.a, y.b);
         g();
      }

      for (int var11 = 1; var11 < 6; var11++) {
         for (int var15 = 0; var15 < k[var11]; var15++) {
            a[var11][var15].a(var0);
         }
      }

      for (int var12 = 0; var12 < k[6]; var12++) {
         a[6][var12].a(var0);
      }

      if (y.a != null && y.a.a == 3 && (y.a.a[15] == 1 || y.a.a[15] == 7 || y.a.a[15] == 19 || y.a.a[15] == 25)) {
         var1 = 0 + (y.a.e >> 16) - f;
         int var16 = 0 + (y.a.f >> 16) - g;
         y.a(y.a.a[15], var1, var16 + -37);
      }

      if (y.b() == 7 || y.b() == 18 || y.b() == 19) {
         y.c(var0);
      }
   }

   static void l() {
      for (int var0 = 0; var0 < D; var0++) {
         if (a[var0] == 1) {
            a[var0].d();
         }
      }
   }

   private static void g(int var0) {
      try {
         b var1 = null;

         try {
            var1 = new b(var0);

            while (var1.available() > 0) {
               if ((var0 = var1.read() & 0xFF) >= 60 && var0 <= 65) {
                  r var8;
                  if (bT >= 250) {
                     var8 = null;
                  } else {
                     r var7;
                     (var7 = a[bT++]).a = (byte)var0;
                     var7.a();
                     var8 = var7;
                  }

                  var8.a(var1);
               } else if (var0 >= 80 && var0 <= 96) {
                  r var10000;
                  if (bU >= 250) {
                     var10000 = null;
                  } else {
                     r var6;
                     (var6 = b[bU++]).a = (byte)var0;
                     var6.a();
                     var10000 = var6;
                  }

                  var10000.a(var1);
               } else if (var0 != 0) {
                  ah var2;
                  (var2 = b(var0)).a(var1, var0);
                  if (var0 == 1) {
                     if (t == -1) {
                        t = var2.b;
                     }

                     u++;
                     if (!y.c()) {
                        var2.d |= 16;
                     }
                  }
               }
            }
         } catch (Exception var3) {
         }

         var1.close();
         b.a();
         System.gc();
      } catch (Exception var4) {
      }
   }

   private static void h(int var0) {
      bI = 0;

      try {
         b var1 = null;

         try {
            var1 = new b(var0);

            while (var1.available() > 0) {
               var0 = (var1.read() & 0xFF) + ((var1.read() & 0xFF) << 8);

               for (int var2 = 0; var2 < var0; var2++) {
                  a[bI][0] = (short)((var1.read() & 0xFF) + ((var1.read() & 0xFF) << 8)) << 16;
                  a[bI][1] = (short)((var1.read() & 0xFF) + ((var1.read() & 0xFF) << 8)) << 16;
                  bI++;
               }
            }
         } catch (Exception var3) {
         }

         var1.close();
         b.a();
         System.gc();
      } catch (Exception var4) {
      }
   }

   private static ah e(int var0) {
      for (int var1 = 0; var1 < D; var1++) {
         if (a[var1].c == var0) {
            return a[var1];
         }
      }

      return null;
   }

   static ah a(int var0) {
      if (var0 < 0) {
         return null;
      }

      var0 &= 65535;
      return a[var0] == 1 ? a[var0] : null;
   }

   static void e(int var0) {
      ah var1;
      if ((var1 = a[var0]).a == 12 || var1.a == 9 || var1.a == 1) {
         a(var1);
      }

      if (var1.a == 33) {
         for (int var2 = t; var2 < t + u; var2++) {
            ah var3;
            if (a[var2] == 1 && (var3 = a[var2]) != null && !var3.b()) {
               var3.a[27] = 0;
            }
         }

         a(var1);
      }

      if (var1.a != 1) {
         a[var0] = 0;
      }

      if (var1.a == 19) {
         var1.d &= -33;
      }

      int var4 = D - 1;

      while (var4 >= 0 && a[var4] != 1) {
         var4--;
      }

      D = var4 + 1;
      var1.d |= 24;
   }

   private static void i(int var0) {
      a[var0] = 1;
      a[var0].d &= -25;
      if (a[var0].a == 19) {
         a[var0].a(0);
         a[var0].r = -1;
         a[var0].b((int)a[var0].a[1]);
         a[var0].c();
         a[var0].a(0);
         a[var0].b(48);
         a[var0].d |= 64;
      }
   }

   static ah b(int var0) {
      ah[] var1 = a;
      var0 = var0;
      ah var2 = null;
      int var3 = 0;
      if (var1 == a) {
         int var5 = 0;

         int var10000;
         while (true) {
            if (var5 >= 150) {
               var10000 = -1;
               break;
            }

            if (a[var5] == 0) {
               var10000 = var5;
               break;
            }

            var5++;
         }

         var3 = var10000;
         if (var10000 < 0) {
            return null;
         }

         var2 = a[var3];
         a[var3] = 1;
         if (var0 == 19) {
            z++;
         }

         if (var3 >= D) {
            D = var3 + 1;
         }
      }

      var2.a();
      var2.a = var0;
      var2.b = var3;
      var2.b();
      return var2;
   }

   static ah a(short var0, byte var1, byte var2, byte var3) {
      ah var4;
      if ((var4 = b(17)) == null) {
         return null;
      }

      var4.a(var4.e, var4.e);
      var4.a[0] = var0;
      var4.a[0] = var1;
      var4.a[1] = var2;
      var4.a[2] = 1;
      var4.a = 0L;
      return var4;
   }

   static ah c(int var0) {
      ah var4;
      (var4 = b(9)).q = 10;
      var4.b(2);
      var4.c();
      var4.d |= 64;
      var4.d |= 32;
      var4.e = 0;
      boolean var1 = false;

      while (!var1) {
         int var2 = a(196, 636);
         int var3 = a(96, 416);
         var4.e = var2 << 16;
         var4.f = var3 << 16;
         if (var4.a(a, var4.e, var4.f)) {
            var1 = true;
            var4.f = 3276800;
         }
      }

      b(var4.b);
      var4.q = 10;
      var4.d |= 64;
      var4.d |= 32;
      if (var0 == -1) {
         var4.a[0] = var0;
         var4.b(3);
      } else if (var0 == -2) {
         var4.a[0] = -2;
         var4.b(4);
      } else {
         if ((var0 = (y.D + 1) / 7) >= y.B.length) {
            var0 = y.B.length - 1;
         }

         var0 = y.B[var0];
         var4.a[0] = y.A[y.c(var0)];
         if (y.A == 3) {
            while (var4.a[0] == 18) {
               var4.a[0] = y.A[y.c(var0)];
            }
         }

         var4.b(2);
      }

      return var4;
   }

   private static void c(int var0, int var1) {
      label159:
      for (int var6 = 0; var6 < var0; var6++) {
         ah var7;
         (var7 = b(var1)).c();
         boolean var2 = false;
         int var5 = 0;

         while (true) {
            int var3;
            int var10;
            while (true) {
               if (var2 || var5 >= 40) {
                  if (!var2) {
                     e(var7.b);
                  } else {
                     if (var1 == 12) {
                        a(var7, (byte)1);
                        if (v == -1) {
                           v = var7.b;
                        }

                        w++;
                     }

                     if (var1 == 13) {
                        if (x == -1) {
                           x = var7.b;
                        }

                        y++;
                     }

                     if (var1 == 31) {
                        if (E == -1) {
                           E = var7.b;
                        }

                        F++;
                     }
                  }
                  continue label159;
               }

               var3 = y.b(G, 640 + G);
               var10 = y.b(H, 320 + H);
               var7.e = var3 << 16;
               var7.f = var10 << 16;
               if (!var7.a(a, var3 << 16, var10 << 16)) {
                  if ((var10 = var7.a(var3, var10, false)) < b) {
                     var2 = true;
                  }
                  break;
               }

               if (var1 != 13 && var1 != 12) {
                  if ((var10 = var7.a(var3, var10, true)) > 1572864 && var10 < b) {
                     var2 = true;
                  }
                  break;
               }

               var2 = false;
            }

            var7.a(var3 << 16, var10 << 16);
            if (var2 && (var1 == 13 || var1 == 31) && var7.d()) {
               var2 = false;
            }

            if (var2) {
               var3 = -1;
               var10 = -1;
               if (var1 == 12 && v != -1) {
                  var3 = v;
                  var10 = v + w;
               } else if (var1 == 13 && x != -1) {
                  var3 = x;
                  var10 = x + y;
               } else if (var1 == 31 && E != -1) {
                  var3 = E;
                  var10 = E + F;
               }

               if (var3 != -1) {
                  var2 = a(var7, var3, var10, 30);
               }

               if (var1 == 13 && var2 && v != -1) {
                  var2 = a(var7, v, v + w, 6);
               }

               if (var1 == 31 && var2 && v != -1) {
                  var2 = a(var7, v, v + w, 8);
               }

               if (var1 == 31 && var2 && x != -1) {
                  var2 = a(var7, x, x + y, 8);
               }
            }

            if (var2 && var1 == 12) {
               for (int var9 = t; var9 < t + u; var9++) {
                  if (a[var9] == 1 && a[var9].a == 1 && !a[var9].b() && var7.a(a[var9], var7.e, var7.f)) {
                     var2 = false;
                     break;
                  }
               }
            }

            var5++;
         }
      }
   }

   private static boolean a(ah var0, int var1, int var2, int var3) {
      if (var1 < 0) {
         var1 = 0;
      }

      for (int var5 = var1; var5 < var2; var5++) {
         var1 = Math.abs(a[var5].e - var0.e) >> 16;
         int var4 = Math.abs(a[var5].f - var0.f) >> 16;
         if (var1 * var1 + var4 * var4 < var3 * var3) {
            return false;
         }
      }

      return true;
   }

   static ah a(int var0, int var1, int var2) {
      ah var3;
      (var3 = b(10)).a(var1 + 25 << 16, var2 + -34 << 16);
      var3.a[0] = var0;
      return var3;
   }

   public static int b() {
      int var0 = y.r[y.I];

      do {
         if (++var0 < t) {
            var0 = t;
         }

         if (var0 > t + u - 1) {
            var0 = t;
         }
      } while (a[var0].a != 1 || a[var0].b() || a[var0].a[3] != y.I || (a[var0].d & 24) != 0);

      return var0;
   }

   static void a(int var0, int var1, int var2, int var3) {
      var1 <<= 16;
      int var7 = y.b[var0][0];
      int var8 = y.b[var0][1];

      for (int var10 = 0; var10 < D; var10++) {
         ah var9;
         if (a[var10] == 1
            && ((var9 = a[var10]).a == 1 || var9.a == 12 || var9.a == 13 || var9.a == 9 || var9.a == 14 || var9.a == 19 || var9.a == 27 || var9.a == 33)
            && var1 != 0
            && (var0 != 31 || var9 != y.a)
            && (var0 != 28 && var0 != 0 || var9 != y.a || var9.a != 22 || var9.a[15] != 32)
            && (var0 != 16 && var0 != 12 || var9.a != 1 || var9 != y.a)
            && (var0 != 19 || var9.a != 1 || var9.a != 22)
            && (var0 != 8 || var9.a != 1 || var9.a[13] != 1)) {
            y.o[0] = var9.e - var2;
            y.o[1] = var9.c() - var3;
            if (var0 == 31 || var0 == 2) {
               y.o[0] = y.a.a[5] * Math.abs(var9.e - var2);
            }

            int var4;
            if ((var4 = y.a(y.o)) <= var1) {
               int var6;
               if (var0 == 31) {
                  var6 = 1966080;
               } else {
                  var6 = (var7 << 16) + (int)((long)(var8 - var7 << 16) * (int)(((long)var1 - var4 << 16) / var1) >> 16);
               }

               if (var6 >> 16 != 0) {
                  if (var0 == 2 && var9.a == 1) {
                     if (var9.a == 18 || var9.a) {
                        continue;
                     }

                     var9.a = true;
                  }

                  var4 = (int)((long)y.o[0] * var6 >> 16) / 3;
                  int var5 = (int)((long)y.o[1] * var6 >> 16) / 3;
                  if (var0 == 16 && var9.a == 1) {
                     var5 = y.b(var4 = y.a.a[5] * 180 >> 16);
                     int var11 = y.a(var4);
                     var4 = y.a.a[5] * (int)(983040L * var5 >> 16);
                     var5 = (int)(983040L * var11 >> 16);
                     var9.a[17] = 1;
                  }

                  if (var0 == 12) {
                     var4 = 0;
                     var5 = 0;
                  }

                  if (var0 == 23) {
                     var5 = -131072;
                  }

                  if (var0 == 2) {
                     var4 = 131072 * (y.o[0] > 0 ? 1 : -1);
                     var5 = -786432;
                  }

                  var4 = (int)((long)var4 << 16 >> 16);
                  var5 = (int)((long)var5 << 16 >> 16);
                  if (y.c()) {
                     var4 = 0;
                     var5 = 0;
                  }

                  var4 = (var4 << 2) / 5;
                  var5 = (var5 << 2) / 5;
                  var9.b(var4, var5);
                  if (var9.a == 1) {
                     var9.b((byte)11);
                  }

                  var9.a(var0, var6 >> 16);
               }
            }
         }
      }
   }

   static int a(int var0, int var1, int var2) {
      boolean var7 = false;
      int var4 = 0;
      int var5 = -1;

      for (int var6 = 0; var6 < D; var6++) {
         if (a[var6] == 1 && a[var6].a == 1) {
            int var3 = Math.abs(a[var6].e - var0) + Math.abs(a[var6].f - var1);
            if (!var7 || var3 < var4) {
               var4 = var3;
               var7 = true;
               var5 = var6;
            }
         }
      }

      return var5;
   }

   static void m() {
      for (int var0 = 0; var0 < D; var0++) {
         if (a[var0].a == 17) {
            e(var0);
         }
      }
   }

   static void n() {
      if (a != null) {
         for (int var0 = 0; var0 < 150; var0++) {
            e(var0);
         }

         ah.c = null;
      }
   }

   static ah d(int var0) {
      int var1 = 65536000;
      int var3 = -1;
      boolean var4 = false;
      if (y.a.a[15] == 9) {
         var4 = true;
      }

      for (int var5 = t; var5 < t + u; var5++) {
         if (var5 != ah.a.b
            && (a[var5].d & 16) != 16
            && a[var5] == 1
            && a[var5].a == 1
            && (!var4 || !a[var5].b())
            && a[var5].f < (b << 16) + 1572864
            && a[var5].f < q << 16
            && a[var5].e > 96
            && a[var5].e < p - 96 << 16
            && a[var5].e >> 16 > 96) {
            int var2 = a[var5].e - ah.a.e;
            if (var0 == 0) {
               if (var2 > 0 && var2 < var1) {
                  var1 = var2;
                  var3 = var5;
               }
            } else if (var2 < 0 && Math.abs(var2) < Math.abs(var1)) {
               var1 = var2;
               var3 = var5;
            }
         }
      }

      if (y.f && var3 == -1) {
         var3 = ah.a.b;
      }

      return var3 == -1 ? null : a[var3];
   }

   static void o() {
      if (y.A != 4 && !y.q) {
         for (int var0 = t; var0 < t + u; var0++) {
            if (a[var0] == 1 && a[var0].b && a[var0].a == 1) {
               a[var0].a[9] = a[var0].a[9] + y.b[12][0];
               if (a[var0].c) {
                  b(a[var0].b);
                  a[var0].a(0);
                  a[var0].b(72);
               }
            }
         }

         y.q = true;
      }
   }

   private static void a(int var0, int var1, byte var2) {
      if (var0 < bP + G && var1 < bQ + H && var0 >= G && var1 >= H) {
         int var3;
         var1 = (var3 = var0 - G + (var1 - H) * bP) >> 3;
         var0 = var3 & 7;
         if (var2 != 0) {
            x[var1] = (byte)(x[var1] | 1 << var0);
         } else {
            x[var1] = (byte)(x[var1] & ~(1 << var0));
         }
      }
   }

   public static int b(int var0, int var1) {
      if (var0 < bP + G && var1 < bQ + H && var0 >= G && var1 >= H) {
         int var2;
         var1 = (var2 = var0 - G + (var1 - H) * bP) >> 3;
         var0 = var2 & 7;
         return (x[var1] & 1 << var0) != 0 ? 1 : 0;
      } else {
         return 0;
      }
   }

   static boolean a(int var0, int var1) {
      return 0 == b(var0, var1 + 1);
   }

   static void a(ah var0, byte var1) {
      var1 = (var0.e >> 16) + var0.w[0];
      int var2 = (var0.f >> 16) + var0.w[1];
      int var3 = var0.w[2] - var0.w[0];
      int var6 = var0.w[3] - var0.w[1];

      for (int var4 = var1; var4 < var1 + var3; var4++) {
         for (int var5 = var2; var5 < var2 + var6; var5++) {
            a(var4, var5, (byte)1);
         }
      }
   }

   private static void a(ah var0) {
      int var1 = (var0.e >> 16) + var0.w[0];
      int var2 = (var0.f >> 16) + var0.w[1];
      int var3 = var0.w[2] - var0.w[0];
      int var6 = var0.w[3] - var0.w[1];

      for (int var4 = var1; var4 < var1 + var3; var4++) {
         for (int var5 = var2; var5 < var2 + var6; var5++) {
            a(var4, var5, (byte)0);
         }
      }
   }

   static boolean a(int var0, int var1, int var2, int var3) {
      return b(var0, var1) > 0
         || b(var0 + var2, var1) > 0
         || b(var0, var1 + var3) > 0
         || b(var0 + var2 / 2, var1 + var3 / 2) > 0
         || b(var0 + var2, var1 + var3) > 0;
   }

   static boolean b(int var0, int var1, int var2, int var3) {
      return b(var0 - var2, var1 - var3) > 0
         || b(var0 + var2, var1 - var3) > 0
         || b(var0, var1) > 0
         || b(var0 - var2, var1 + var3) > 0
         || b(var0 + var2, var1 + var3) > 0;
   }

   static void f(int var0) {
      if (J != var0) {
         J = var0;
      }
   }

   static void p() {
      if (y.f) {
         a = true;
      }

      if (J != 0) {
         if (y.j && !a) {
            if ((s.a() - (c + b) > d || y.k) && !y.l) {
               y.j = false;
               f(0);
               return;
            }
         } else {
            if (a) {
               return;
            }

            if (y.A == 1 && y.z == 2) {
               a = f + 16000L - s.a() + b;
            } else {
               a = f + g - s.a() + b;
            }

            if (a < 6000L && (y.C & 1) == 0) {
               y.m(1);
            }

            if (a <= 0L && (y.p == 2 || y.p == 4 || y.p == 3)) {
               y.c(5);
               if (y.a != null && y.a.a == 2) {
                  y.a.b((byte)1);
               }
            }
         }
      }
   }

   static void q() {
      b = 0L;
      f = s.a();
      f(2);
   }

   static void r() {
      b = 0L;
      f(1);
   }

   static void s() {
      if (y.a != null && y.a.a[12] == 1) {
         y.j = true;
         c = s.a() - b;
      }

      if (y.a.a[12] == 0) {
         f(0);
      }
   }

   static void t() {
      f(0);
   }

   static void u() {
      f(2);
   }

   static void v() {
      b = s.a() - b;
      f(0);
   }

   static void w() {
      if (y.a != null) {
         y.a.a = y.a.a + (s.a() - b);
      }

      if (J == 0) {
         b = s.a() - b;
         f(1);
      }
   }

   static void a(ah var0, int var1, int var2) {
      for (int var3 = 0; var3 < bT; var3++) {
         if (a[var3].a == 60) {
            r var10000 = a[var3];
            int var10002 = var1 >> 16;
            int var7 = var2 >> 16;
            int var6 = var10002;
            ah var5 = var0;
            r var4 = var10000;
            if (var10000.a && (var4.a[5] == -1 || var5.c == var4.a[5])) {
               if (var4.a(var5.e >> 16, var5.f >> 16) && !var4.a(var6, var7)) {
                  var4.a[2]++;
               }

               if (var4.a[2] >= var4.a[1]) {
                  var4.b();
                  var4.a[2] = 0;
               }
            }
         }
      }
   }

   static void a(short var0, ah var1, boolean var2) {
      for (int var3 = 0; var3 < bT; var3++) {
         if (a[var3].a == var0) {
            r var10000 = a[var3];
            ah var5 = var1;
            r var4 = var10000;
            if (var10000.a) {
               switch (var4.a) {
                  case 61:
                     if (var5.c == var4.a[5] && var4.a(var5.e >> 16, var5.f >> 16)) {
                        var4.b();
                     }
                     break;
                  case 62:
                     var4.b();
                     break;
                  case 63:
                     if (var2) {
                        if (var4.a[1] == 105) {
                           var4.b();
                        }
                     } else if (var5.a[15] == var4.a[1]) {
                        var4.b();
                     }
                     break;
                  case 64:
                     if (z == var4.a[1]) {
                        var4.b();
                     }
                     break;
                  case 65:
                     if (y.bE == var4.a[1]) {
                        var4.b();
                     }
               }
            }
         }
      }
   }

   static r a(int var0) {
      for (int var1 = 0; var1 < bT; var1++) {
         if (a[var1].a == var0) {
            return a[var1];
         }
      }

      for (int var2 = 0; var2 < bU; var2++) {
         if (b[var2].a == var0) {
            return b[var2];
         }
      }

      return null;
   }

   static {
      int[] var10000 = new int[]{8416592, 6775146, 11308650, 5061401, 5987971};
   }
}
