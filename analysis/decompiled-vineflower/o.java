import com.ea.sdk.SDKMIDlet;
import java.util.Vector;

public class o {
   private static final byte a = 8;
   private static final byte b = (byte)(0 + a + 6);
   private int a = -1;
   private int b = -1;
   private String[] a;
   private short[] a;
   private short[] b;
   private short[] c;
   private int[] a;
   private String[] b;
   private String[] c;
   private short[] d = new short[11];
   private c[] a = new c[11];
   private Object[] a;
   private int c = 0;
   private boolean a;
   private int d;
   private boolean b = true;
   private int e;
   private int f = 0;
   private int g = 0;
   private int h;
   private String a;
   private e a;
   private e b;
   private short a = -1;
   private short b = -1;
   private int i;
   private int j;
   private int k;
   private short[] e;
   private short[] f;
   private short[] g;
   private short[] h;
   private String b;
   private c a;
   private c b;
   private c c;
   private boolean c = true;
   private boolean d = true;
   private int l = 0;
   private int m;
   private int n;
   private int o = 0;
   private int p;
   private int q = 6;
   private int r;
   private int s;
   private int t;
   private int u;
   private int v;
   private int w;
   private boolean e;
   private int x;
   private int y;
   private int z;
   private int A;
   private int B;
   private short[][] a;
   private short[][] b;
   private short[] i;
   private short[][] c;
   private String[][] a;
   private c[] b;
   private c d;
   private int C;
   private int D;
   private c e;
   private c f;
   private e[] a;
   private int E = 0;

   public o(int var1, int var2) {
      this.b = var1;
      this.a = var2;
   }

   public final void a(byte[] var1) {
      int var5 = 2;
      byte var2 = var1[1];
      this.a = new String[var2];
      this.a = new short[var2];
      this.b = new short[var2];
      this.c = new short[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         byte var4;
         int var6;
         this.a[var3] = new String(var1, var6 = var5 + 1, var4 = var1[var5]);
         var5 = var6 + var4;
      }

      int var19 = var2 << 1;
      int var10 = var2 << 2;

      for (int var12 = 0; var12 < var2; var12++) {
         this.a[var12] = p.a(var1, var5);
         this.b[var12] = p.a(var1, var5 + var19);
         this.c[var12] = p.a(var1, var5 + var10);
         var5 += 2;
      }

      var19 = var5 + var10;

      for (int var16 = 0; var16 < this.a.length; var16++) {
         this.d[var16] = p.a(var1, var19);
         var19 += 2;
      }

      int var7 = var19 + 1;
      int var21 = var1[var19];
      this.a = new short[var21][];
      this.b = new short[var21][];

      for (int var8 = 0; var8 < var2; var8++) {
         for (int var9 = 0; var9 < var21; var9++) {
            short var13 = p.a(var1, var7);
            short var11 = p.a(var1, var7 + 2);
            var7 += 4;
            if (var13 != -1 && var11 != -1) {
               if (this.a[var9] == null) {
                  this.a[var9] = new short[var2];
                  this.b[var9] = new short[var2];

                  for (int var17 = 0; var17 < var2; var17++) {
                     this.a[var9][var17] = -1;
                     this.b[var9][var17] = -1;
                  }
               }

               this.a[var9][var8] = var13;
               this.b[var9][var8] = var11;
            }
         }
      }

      int var26 = var7 + 1;
      byte var25 = var1[var7];
      this.i = new short[var25];
      this.c = new short[var25][];
      this.a = new String[var25][];

      for (int var24 = 0; var24 < var25; var24++) {
         int var10001 = var21 = var26 + 1;
         byte var18 = var1[var26];
         short[] var27 = this.i;
         short var14 = p.a(var1, var10001);
         var27[var24] = var14;
         var26 = var21 + 2;
         this.c[var24] = new short[var18];
         this.a[var24] = new String[var18];

         for (int var23 = 0; var23 < var18; var23++) {
            this.c[var24][var23] = p.a(var1, var26);
            int var28 = var26 + 2 + 1;
            byte var15 = var1[var26 + 2];
            this.a[var24][var23] = new String(var1, var28, var15);
            var26 = var28 + var15;
         }
      }
   }

   public final void a(Object[] var1) {
      this.a = var1;
      Object var10000 = p.a();
      p.a(var1[0]);
      p.b();
      this.D = p.b() - p.a();
      p.a(var10000);
   }

   public final boolean a() {
      if (this.a == null) {
         this.b();
      }

      boolean var1;
      if (this.c != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private void b() {
      String var12 = "MG_Impl";
      String var1;
      if ((var1 = SDKMIDlet.a("MG_Impl")) == null) {
         this.c = 0;
      } else {
         String var10;
         if (!(var10 = var1.toLowerCase()).equals("static")) {
            if (var10.equals("wapstatic")) {
            }

            if (var10.equals("wap")) {
               this.c = 2;
            } else {
               if (!var10.equals("wapstatic")) {
                  this.c = 0;
                  return;
               }

               this.c = 3;
            }
         } else {
            this.c = 1;
         }

         var12 = "MG_PRODUCTS";
         String var11;
         if ((var11 = SDKMIDlet.a("MG_PRODUCTS")) != null && var11.length() != 0) {
            int var13 = 0;
            int var2 = -1;

            int var3;
            do {
               String var4;
               if ((var3 = var11.indexOf(44, var2 + 1)) == -1) {
                  var4 = var11.substring(var2 + 1);
               } else {
                  var4 = var11.substring(var2 + 1, var3);
               }

               var2 = var3;
               if (var4.startsWith("MGPANEL")
                  && var4.length() == "MGPANEL".length() + 1
                  && var4.charAt(var4.length() - 1) >= '0'
                  && var4.charAt(var4.length() - 1) <= '9') {
                  int var17 = var4.charAt(var4.length() - 1) - '0';
                  String var5;
                  if ((var5 = SDKMIDlet.a("MG_MGPANEL" + var17)) != null && var17 < this.i.length) {
                     boolean var6 = false;
                     int var7 = -1;

                     int var8;
                     do {
                        String var26;
                        if ((var8 = var5.indexOf(44, var7 + 1)) == -1) {
                           var26 = var5.substring(var7 + 1);
                        } else {
                           var26 = var5.substring(var7 + 1, var8);
                        }

                        var7 = var8;

                        for (int var29 = 0; var29 < this.a[var17].length; var29++) {
                           if (this.a[var17][var29].equals(var26)) {
                              var6 = true;
                           }
                        }

                        if (var6) {
                           var13++;
                           break;
                        }
                     } while (var8 != -1);
                  }
               } else {
                  boolean var28 = false;

                  for (int var9 = 0; var9 < this.a.length; var9++) {
                     if (this.a[var9].equals(var4)) {
                        var28 = true;
                        break;
                     }
                  }

                  if (var28) {
                     var13++;
                  }
               }
            } while (var3 != -1);

            if (var13 == 0) {
               this.c = 0;
            }

            this.a = new int[var13];
            this.b = new String[var13];
            this.c = new String[var13];
            int var30 = -1;
            int var27 = 0;
            Vector var25;
            var25 = new Vector./* $VF: Unable to resugar constructor */<init>();
            Vector var24;
            var24 = new Vector./* $VF: Unable to resugar constructor */<init>();

            for (int var23 = 0; var23 < this.a.length; var23++) {
               String var18;
               int var21;
               if ((var21 = var11.indexOf(44, var30 + 1)) == -1) {
                  var18 = var11.substring(var30 + 1);
               } else {
                  var18 = var11.substring(var30 + 1, var21);
               }

               var30 = var21;
               if (var18.startsWith("MGPANEL")
                  && var18.length() == "MGPANEL".length() + 1
                  && var18.charAt(var18.length() - 1) >= '0'
                  && var18.charAt(var18.length() - 1) <= '9') {
                  var21 = var18.charAt(var18.length() - 1) - '0';
                  if ((var18 = SDKMIDlet.a("MG_MGPANEL" + var21)) != null && var21 < this.i.length) {
                     var25.setSize(0);
                     var24.setSize(0);
                     var3 = -1;

                     do {
                        if ((var2 = var18.indexOf(44, var3 + 1)) == -1) {
                           var1 = var18.substring(var3 + 1);
                        } else {
                           var1 = var18.substring(var3 + 1, var2);
                        }

                        var3 = var2;

                        for (int var33 = 0; var33 < this.a[var21].length; var33++) {
                           if (this.a[var21][var33].equals(var1) && !var25.contains(var1)) {
                              var25.addElement(var1);
                              var24.addElement(new Short(this.c[var21][var33]));
                           }
                        }
                     } while (var2 != -1);

                     if (var25.size() == 0) {
                        var23--;
                     } else {
                        this.a[var23] = var21 | -2147483648;
                        int var34 = var25.size();
                        this.a[var21] = new String[var34];
                        this.c[var21] = new short[var34];

                        for (int var20 = 0; var20 < var34; var20++) {
                           this.a[var21][var20] = var25.elementAt(var20).toString();
                           this.c[var21][var20] = (Short)var24.elementAt(var20);
                        }

                        if (var27 < var34) {
                           var27 = var34;
                        }
                     }
                  } else {
                     var23--;
                  }
               } else {
                  this.a[var23] = -2;
                  this.b[var23] = null;
                  this.c[var23] = null;

                  for (int var32 = 0; var32 < this.a.length; var32++) {
                     if (this.a[var32].equals(var18)) {
                        this.a[var23] = var32;
                        this.b[var23] = SDKMIDlet.a("MG_BUYURI_" + var18);
                        this.c[var23] = SDKMIDlet.a("MG_CATURI_" + var18);
                        if (this.b[var23] != null) {
                           this.b[var23] = this.b[var23].trim();
                        }

                        if (this.c[var23] != null) {
                           this.c[var23] = this.c[var23].trim();
                        }
                        break;
                     }
                  }

                  if (this.a[var23] == -2) {
                     var23--;
                  }
               }
            }

            this.b = new c[var27];
            this.a = new e[var27];
         } else {
            this.c = 0;
         }
      }
   }

   public final boolean b() {
      return this.a;
   }

   public final void a() {
      switch (this.f) {
         case 0:
            if ((this.d & 2052) != 0) {
               this.e--;
               if (this.e < 0) {
                  this.e = this.a.length - 1;
               }

               this.a(this.e);
            } else if ((this.d & 8200) != 0) {
               this.e++;
               this.e = this.e % this.a.length;
               this.a(this.e);
            } else if (((this.d & 513) != 0 || (this.d & 32770) != 0) && this.c && this.d) {
               if (this.l == 1) {
                  this.l = 0;
               } else {
                  this.l = 1;
               }
            } else if ((this.d & 524352) != 0) {
               this.a = false;
               this.f();
            } else if ((this.d & 4144) != 0) {
               if (this.l == 0) {
                  this.a = this.b[this.e];
                  this.g = this.f;
                  this.e();
               } else if (this.l == 1) {
                  if (this.c != 1 && (this.c != 3 || this.c[this.e] != null && this.c[this.e].compareTo("") != 0)) {
                     this.a = this.c[this.e];
                     this.g = this.f;
                     this.e();
                  } else {
                     this.e = null;
                     c var7;
                     this.f = var7 = this.a[6];
                     this.g = this.f;
                     this.f = 2;
                  }
               }
            }
            break;
         case 1:
            if ((this.d & 524352) != 0) {
               c var5;
               this.e = var5 = this.a[5];
               this.f = var5 = this.a[6];
               this.f = this.g;
            } else if ((this.d & 32) != 0) {
               p.a(this.a(this.a));
            }
            break;
         case 2:
            if ((this.d & 524352) != 0) {
               c var3;
               this.e = var3 = this.a[5];
               this.f = var3 = this.a[6];
               this.f = this.g;
            }
            break;
         case 3:
            label134: {
               if ((this.d & 2052) != 0 && this.a.length > 0) {
                  this.e--;
                  if (this.e < 0) {
                     this.e = this.a.length - 1;
                  }

                  this.a(this.e);
               } else if ((this.d & 8200) != 0 && this.a.length > 0) {
                  this.e++;
                  this.e = this.e % this.a.length;
                  this.a(this.e);
               } else if ((this.d & 524352) != 0) {
                  this.a = false;
                  this.f();
               } else if ((this.d & 513) != 0) {
                  this.i--;
                  this.E = 0;
                  if (this.i < 0) {
                     this.i = this.C - 1;
                  }

                  this.c();
               } else if ((this.d & 32770) != 0) {
                  this.i++;
                  this.i = this.i % this.C;
                  this.E = 0;
                  this.c();
               } else if ((this.d & 4144) != 0) {
                  String var1 = null;
                  if (this.c == 2 || this.c == 3) {
                     var1 = SDKMIDlet.a("MG_BUYURI_" + this.a[this.a[this.e] & 2147483647][this.i]);
                  }

                  if (var1 == null) {
                     if (this.c == 2) {
                        break label134;
                     }

                     this.e = null;
                     c var2;
                     this.f = var2 = this.a[6];
                     this.g = this.f;
                     this.f = 2;
                  } else {
                     this.a = var1;
                     this.g = this.f;
                     this.e();
                  }
               }

               if (this.i < this.j) {
                  this.j = this.i;
                  this.E = 0;
               }

               if (this.i >= this.j + this.k) {
                  this.j = this.i - this.k + 1;
                  this.E = 0;
               }
            }
      }

      this.d = 0;
   }

   private void c() {
      boolean var1;
      if (this.c == 2 && SDKMIDlet.a("MG_BUYURI_" + this.a[this.a[this.e] & 2147483647][this.i]) == null) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (var1) {
         this.e = null;
      } else {
         c var2;
         this.e = var2 = this.a[5];
      }

      c var3;
      this.f = var3 = this.a[6];
   }

   public final void b(n var1) {
      this.a(var1);
      switch (this.f) {
         case 0:
            this.q--;
            if (this.q < 0) {
               this.q = 6;
            }

            if (this.e) {
               var1.a(-16757361);
               p.a(this.a[4]);
               var1.c(0, 0, this.b, this.n);
               p.a(this.a[4]);
               p.a(this.a[9], this.b >> 1, 1, 17);
            }

            if (this.a != null) {
               var1.a(this.a, this.b >> 1, this.r, 17);
               if (this.b != null) {
                  var1.a(this.b, (this.b - this.a.a.a >> 1) + this.a, this.r + this.b, 20);
               }

               if (this.a.length > 1) {
                  this.a(var1, (this.b - this.a.a.a >> 2) - (a >> 1), this.s);
                  this.b(var1, this.b - (this.b - this.a.a.a >> 2) + (a >> 1), this.s);
               }

               p.a(this.a[0]);
               p.a(this.b, this.h, 1, this.h[0], this.b >> 1, this.o, 17);
               p.a(this.a, this.g, 1, this.g[0], this.b >> 1, this.t, 17);
            } else {
               p.a(this.a[0]);
               p.a(this.b, this.b >> 1, this.r, 17);
               p.a(this.a, this.g, 1, this.g[0], this.b >> 1, this.t, 17);
               if (this.a.length > 1) {
                  this.a(var1, 1, this.s);
                  this.b(var1, this.b - 1, this.s);
               }
            }

            if (this.c) {
               int var13 = this.u;
               int var8 = this.p;
               c var6 = this.a[10];
               boolean var17;
               if (this.l == 0) {
                  var17 = true;
               } else {
                  var17 = false;
               }

               this.a(var1, var13, var8, var6, var17);
            }

            if (this.d) {
               int var18 = this.v;
               int var14 = this.p;
               c var9 = this.c;
               boolean var7;
               if (this.l == 1) {
                  var7 = true;
               } else {
                  var7 = false;
               }

               this.a(var1, var18, var14, var9, var7);
            }
            break;
         case 1:
            p.a(this.a[0]);
            p.a(this.a[4], this.f, 1, this.f[0], this.b >> 1, this.a - this.w >> 1, 3);
            break;
         case 2:
            p.a(this.a[0]);
            p.a(this.a[0], this.e, 1, this.e[0], this.b >> 1, this.a - this.w >> 1, 3);
            break;
         case 3:
            this.q--;
            if (this.q < 0) {
               this.q = 6;
            }

            if (this.e) {
               var1.a(-16757361);
               var1.c(0, 0, this.b, this.n);
               p.a(this.a[4]);
               p.a(this.d, this.b >> 1, 1, 17);
            }

            int var2 = this.z;
            p.a(this.a[1]);

            for (int var3 = this.j; var3 < this.j + this.k; var3++) {
               if (var3 == this.i) {
                  p.a(this.a[2]);
               }

               int var4 = this.D;
               if (this.a[var3] != null) {
                  var4 = this.a[var3].a.a;
                  var1.a(this.a[var3], this.A, var2 + (this.B >> 1), 6);
               }

               int var5 = this.A + var4 + 4;
               var4 = var2 + (this.B >> 1) - (p.b() - p.a() >> 1);
               var1.d(var5, var4, this.b - this.A - var5, p.b() - p.a());
               if (var3 == this.i) {
                  p.a(this.b[var3], var5 - (this.E >> 1), var4, 20);
                  this.E++;
                  if (var5 - (this.E >> 1) + p.a(this.b[var3]) < this.b - this.A) {
                     this.E = 0;
                  }
               } else {
                  p.a(this.b[var3], var5, var4, 20);
               }

               var1.d(0, 0, this.b, this.a);
               var2 += this.B;
               if (var3 == this.i) {
                  p.a(this.a[1]);
               }
            }

            if (this.j > 0) {
               int var15 = this.b >> 1;
               int var11 = this.y - (this.q >> 1);
               var1.a(-16757361);
               var1.b(var15, var11, var15 - a, var11 + a, var15 + a, var11 + a);
            }

            if (this.j + this.k < this.C) {
               int var16 = this.b >> 1;
               int var12 = this.x - (6 - (this.q >> 1));
               var1.a(-16757361);
               var1.b(var16, var12, var16 - a, var12 - a, var16 + a, var12 - a);
            }

            if (this.a.length > 1) {
               this.a(var1, this.A - a >> 1, this.a >> 1);
               this.b(var1, this.b - (this.A - a >> 1), this.a >> 1);
            }
      }

      if (this.b) {
         p.a(this.a[3]);
         if (this.e != null) {
            p.a(this.e, 2, this.a + p.a() - 2, 36);
         }

         if (this.f != null) {
            p.a(this.f, this.b - 2, this.a + p.a() - 2, 40);
         }
      }
   }

   public final byte a(int var1) {
      if (this.a) {
         this.d |= var1;
      }

      return 0;
   }

   protected void a(n var1) {
      var1.d(0, 0, this.b, this.a);
      var1.a(-1);
      var1.c(0, 0, this.b, this.a);
   }

   public final void a(String var1) {
      if (this.a == null) {
         this.b();
      }

      for (int var2 = 0; var2 < this.a.length; var2++) {
         this.a[var2] = p.a(this.d[var2], null);
      }

      this.b = var1;
      this.e = 0;
      this.a = true;
      this.f = 0;
      this.a(0);
      p.a(this.a[0]);
      o var3;
      this.e = p.a((var3 = this).a[0], null, var3.b - 10, (short)124);
      var3.f = p.a(var3.a[4], null, var3.b - 10, (short)124);
   }

   private void a(int var1) {
      if (this.a[var1] != -1 && (this.a[var1] & -2147483648) != 0) {
         this.m = this.a[var1] & 2147483647;

         for (int var12 = 0; var12 < this.a.length; var12++) {
            this.a[var12] = null;
         }

         for (int var13 = 0; var13 < this.c[this.m].length; var13++) {
            this.b[var13] = p.a(this.c[this.m][var13], null);

            try {
               this.a[var13] = p.a("/mgc" + this.a[this.m][var13].toLowerCase() + ".png");
            } catch (Exception var6) {
            }
         }

         this.d = p.a(this.i[this.m], null);
         this.i = 0;
         this.j = 0;
         this.E = 0;
         this.C = this.c[this.m].length;
         this.c();
         this.d();
         this.f = 3;
      } else {
         int var2 = var1;
         o var10 = this;
         this.h = this.a[var2];
         if (var10.h != -1) {
            try {
               var10.a = p.a("/mgi" + var10.a[var10.h].toLowerCase() + ".png");
            } catch (Exception var9) {
               var10.a = null;
            }

            int var3 = p.c();
            var10.b = null;
            var10.a = -1;
            var10.b = -1;
            if (var10.a[var3] != null && var10.a[var3][var10.h] != -1) {
               try {
                  var10.b = p.a("/mgi" + var10.a[var10.h].toLowerCase() + "_" + p.c() + ".png");
                  var10.a = var10.a[var3][var10.h];
                  var10.b = var10.b[var3][var10.h];
               } catch (Exception var8) {
               }
            }

            var10.a = p.a(var10.c[var10.h], null);
            var10.c = p.a(var10.b[var10.h], null);
            var10.b = p.a(var10.a[var10.h], null);
            switch (var10.c) {
               case 1:
                  var10.c = false;
                  var10.d = true;
                  var10.l = 1;
                  break;
               case 2:
                  boolean var18;
                  if (var10.b[var2] != null && var10.b[var2].compareTo("") != 0) {
                     var18 = true;
                  } else {
                     var18 = false;
                  }

                  var10.c = var18;
                  if (var10.c[var2] != null && var10.c[var2].compareTo("") != 0) {
                     var18 = true;
                  } else {
                     var18 = false;
                  }

                  var10.d = var18;
                  if (var10.c) {
                     var10.l = 0;
                  } else if (var10.d) {
                     var10.l = 1;
                  } else {
                     var10.l = 2;
                  }
                  break;
               case 3:
                  var10.d = true;
                  if (var10.b[var2] != null && var10.b[var2].compareTo("") != 0) {
                     var10.c = true;
                     var10.l = 0;
                  } else {
                     var10.c = false;
                     var10.l = 1;
                  }
            }
         } else {
            try {
               var10.a = p.a("/mgi" + "LOG".toLowerCase() + ".png");
            } catch (Exception var7) {
               var10.b = null;
               var10.a = -1;
               var10.b = -1;
            }

            var10.a = var10.a[1];
            var10.c = var10.a[2];
            var10.b = var10.a[3];
            var10.c = false;
            if (var10.c != 2 || var10.c[var2] != null && var10.c[var2].compareTo("") != 0) {
               var10.d = true;
               var10.l = 1;
            } else {
               var10.d = false;
               var10.l = 2;
            }
         }

         if (var10.l != 2) {
            c var20;
            var10.e = var20 = var10.a[5];
         } else {
            var10.e = null;
         }

         c var21;
         var10.f = var21 = var10.a[6];
         o var11;
         var10.p = ((var11 = var10).b << 2) / 5;
         var2 = var11.a;
         var11.w = 0;
         if (var11.b) {
            p.a(var11.a[3]);
            var11.w = p.b() - p.a();
            var2 -= var11.w;
         }

         int var22 = var2 + -2;
         if (var11.d) {
            int var23 = var22 - 5;
            if (var11.l == 1) {
               p.a(var11.a[2]);
            } else {
               p.a(var11.a[1]);
            }

            var22 = var23 - (p.b() - p.a());
            var11.v = var22;
            if ((var2 = p.a(var11.c) + 6 + p.b() - p.a() + 6) > var11.p) {
               var11.p = var2;
            }
         }

         if (var11.c) {
            int var24 = var22 - 5;
            if (var11.l == 0) {
               p.a(var11.a[2]);
            } else {
               p.a(var11.a[1]);
            }

            var22 = var24 - (p.b() - p.a());
            var11.u = var22;
            if ((var2 = p.a(var11.a[10]) + 6 + p.b() - p.a() + 6) > var11.p) {
               var11.p = var2;
            }
         }

         if (var11.d && var11.c) {
            var22 -= 2;
            var11.u = var22;
         }

         int var25 = var22 - 2;
         int var28;
         if (var11.a != null) {
            p.a(var11.a[0]);
            var11.g = p.a(var11.a, null, var11.b - 2, (short)124);
            var11.h = p.a(var11.b, null, var11.b - 2, (short)124);
            int var26;
            var11.t = var26 = var25 - (var11.g[0] * p.b() - p.a()) + -5;
            int var27;
            var11.o = var27 = var26 - (var11.h[0] * p.b() - p.a());
            var28 = var27 - var11.a.a.b;
            var11.r = var28;
            var11.s = var11.r + (var11.a.a.b >> 1);
         } else {
            p.a(var11.a[0]);
            var11.g = p.a(var11.a, null, var11.b - (b >> 1), (short)124);
            int var29;
            var11.t = var29 = var25 - var11.g[0] * p.b();
            var28 = var29 - p.b();
            var11.r = var28;
            var11.s = var11.r + (p.b() - p.a() >> 1);
         }

         int var4 = var28 + -1;
         p.a(var11.a[4]);
         var11.n = p.b() - p.a() + 1 + 1;
         if (var4 >= var11.n) {
            var11.e = true;
            var4 -= var11.n;
         } else {
            var11.e = false;
         }

         if (var4 > 0) {
            int var5 = var4 * 3 / 10;
            var2 = var4 >> 2;
            var11.r -= var4 - var5;
            var11.s -= var4 - var5;
            var11.t -= var4 - var5 - var2;
            var11.o -= var4 - var5 - var2;
            int var30;
            var11.u = var11.u - (var4 - var5 - var2 - (var30 = var4 >> 2));
            var11.v -= var4 - var5 - var2 - var30;
         }

         this.f = 0;
      }
   }

   private void d() {
      int var1 = this.a;
      if (this.b) {
         p.a(this.a[3]);
         this.w = p.b() - p.a();
         var1 -= this.w;
      }

      int var10000 = var1 + -2 + -1 - (a << 1) + -8;
      p.a(this.a[4]);
      this.n = p.b() - p.a() + 1 + 1;
      this.e = true;
      int var2 = var10000 - this.n;
      p.a(this.a[0]);
      var1 = p.b() - p.a();
      if (this.a[0] != null && this.a[0].a.b > var1) {
         this.B = this.a[0].a.b;
      } else {
         this.B = var1;
      }

      this.B += 4;
      if ((var1 = var2 / this.B) >= this.C) {
         var1 = this.C;
      }

      this.k = var1;
      if ((var2 = var2 - this.k * (this.B + 1)) < 0) {
         var2 = 0;
      }

      this.x = this.a - this.w - 2 - (var2 >> 2) + 3;
      this.y = this.n + 1 + (var2 >> 2) + 3;
      this.z = this.n + 1 + a + (var2 >> 1) + 3 + 1;
      var2 = 0;
      p.a(this.a[1]);

      for (int var3 = 0; var3 < this.C; var3++) {
         if (var3 == this.i) {
            p.a(this.a[2]);
         }

         var1 = p.a(this.b[var3]);
         if (var3 == this.i) {
            p.a(this.a[1]);
         }

         var1 += 4;
         if (this.a[var3] == null) {
            var1 += this.D;
         } else {
            var1 += this.a[var3].a.a;
         }

         if (var1 > var2) {
            var2 = var1;
         }
      }

      this.A = this.b - var2 >> 1;
      if (this.A <= a + 6 + 1) {
         this.A = a + 6 + 1;
      }
   }

   private String a(String var1) {
      StringBuffer var2;
      var2 = new StringBuffer./* $VF: Unable to resugar constructor */<init>(var1);
      if (var1.indexOf(63) == -1) {
         var2.append('?');
      } else {
         var2.append('&');
      }

      var2.append("ctx");
      var2.append('=');
      var2.append(this.b);
      var2.append('&');
      var2.append("lng");
      var2.append('=');
      var2.append(ae.a().a[p.c()]);

      for (int var3 = 1; var3 < 10; var3++) {
         if ((var1 = SDKMIDlet.a("MG_REPORTING_NAME" + var3)) != null) {
            var2.append('&');
            var2.append(var1);
            var2.append('=');
            var2.append(SDKMIDlet.a("MG_REPORTING_VALUE" + var3));
         }
      }

      return var2.toString();
   }

   private void e() {
      ae.a();
      c var1;
      this.e = var1 = this.a[7];
      this.f = var1 = this.a[8];
      this.f = 1;
   }

   private void f() {
      this.e = null;
      this.f = null;
      this.g = null;
      this.a = null;
      this.b = null;
      this.a = null;
      this.b = null;
      this.a = null;
      this.b = null;
      this.c = null;
      this.d = null;
      this.e = null;
      this.f = null;

      for (int var1 = 0; var1 < this.a.length; var1++) {
         this.a[var1] = null;
      }

      for (int var2 = 0; var2 < this.b.length; var2++) {
         this.b[var2] = null;
      }
   }

   private void a(n var1, int var2, int var3) {
      int var4 = var2 + (this.q >> 1);
      var1.a(-16757361);

      for (int var5 = 0; var5 <= a; var5++) {
         var1.a(var4 + var5, var3 - var5, var4 + var5, var3 + var5);
      }

      var1.a(-9462329);
      var1.a(var4, var3, var4 + a, var3 - a);
      var1.a(var4, var3, var4 + a, var3 + a);
      var1.a(var4 + a, var3 - a, var4 + a, var3 + a);
   }

   private void b(n var1, int var2, int var3) {
      int var4 = var2 - (this.q >> 1);
      var1.a(-16757361);

      for (int var5 = 0; var5 <= a; var5++) {
         var1.a(var4 - var5, var3 - var5, var4 - var5, var3 + var5);
      }

      var1.a(-9462329);
      var1.a(var4, var3, var4 - a, var3 - a);
      var1.a(var4, var3, var4 - a, var3 + a);
      var1.a(var4 - a, var3 - a, var4 - a, var3 + a);
   }

   private void a(n var1, int var2, int var3, c var4, boolean var5) {
      int var6 = 0;
      if (var5) {
         p.a(this.a[2]);
         if (this.a[2] == this.a[1]) {
            var6 = 1;
         }
      } else {
         p.a(this.a[1]);
      }

      int var8;
      var5 = (var8 = p.b() - p.a()) + 3 + 2;
      int var7 = this.b - var3 >> 1;
      var1.a(-16777216);
      var1.c(var7, var2, var3, var5);
      var1.a(-16757361);
      var1.c(var7, var2, var3 - 1, var5 - 1);
      var1.a(-9462329);
      var1.a(var7 + 1, var2 + 1, var7 + var3 - 2, var2 + 1);
      var1.a(var7 + 1, var2 + 1, var7 + 1, var2 + var5 - 3);
      if (var6) {
         var1.a(-1);
         var7 = var7 + (var8 >> 1) + 3;
         var6 = var2 + (var5 >> 1);
         var8 = (var8 >> 1) - 1;

         for (int var10 = 0; var10 <= var8; var10++) {
            var1.a(var7 - var10, var6 - var10, var7 - var10, var6 + var10);
         }
      }

      p.a(var4, this.b >> 1, var2 + 2, 17);
   }
}
