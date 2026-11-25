import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class z extends MIDlet implements Runnable {
   private long b = System.currentTimeMillis();
   public long a = -1L;
   public long[] a = new long[8];
   public int a;
   public volatile boolean a;
   public boolean b;
   public volatile boolean c;
   public boolean d;
   public boolean e;
   public String a = "";
   private long c = System.currentTimeMillis();
   public volatile boolean f;
   public boolean g;
   public boolean h;
   public Thread a;

   public z() {
      this.b = true;
      ae.a = this;
      ae.b = this;
   }

   public void startApp() {
      this.b();
   }

   public final void b() {
      if (this.a != -1L) {
         this.b = this.b + (System.currentTimeMillis() - this.a);
         this.a = -1L;
      }

      if (this.a == null && this.b) {
         this.a = new Thread(this);
         (ae.a = new ab()).a = this;
         ae.a = new ae(ae.a(320, 240));
         ae.a = new l();
         this.a.start();
      } else {
         if (this.c || this.a) {
            ae.a((short)6, (short)0, (short)0, this);
         }
      }
   }

   public void pauseApp() {
      ae.a(this);
   }

   public void destroyApp(boolean var1) {
      if (!this.f) {
         this.f = true;
      }
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   // $VF: Could not create synchronized statement, marking monitor enters and exits
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public void run() {
      if (ae.a == null) {
         ae.a = new aa();
         ae.a = false;
         Display.getDisplay(this).setCurrent(ae.a);
         this.b = false;
      }

      long var1 = System.currentTimeMillis();
      l var8 = (l)ae.a;
      synchronized (ae.a){} // $VF: monitorenter 

      try {
         while (this.a > 0) {
            long[] var3 = this.a;
            synchronized (this.a){} // $VF: monitorenter 

            int var5;
            short var6;
            try {
               long[] var38 = this.a;
               var6 = (short)(this.a[0] >>> 48);
               var5 = (int)((var38[0] & 281474976645120L) >>> 16);
               if (this.a > 1) {
                  for (int var4 = 0; var4 < 7; var4++) {
                     var38[var4] = var38[var4 + 1];
                  }
               }

               this.a--;
               // $VF: monitorexit
            } catch (Throwable var24) {
               Throwable var37 = var24;

               while (true) {
                  try {
                     // $VF: monitorexit
                     throw var37;
                  } catch (Throwable var22) {
                     var37 = var22;
                  }
               }
            }

            switch (var6) {
               case 1:
                  ae.b(var5);
                  break;
               case 2:
                  ae.c(var5);
                  break;
               case 3:
                  if (ae.a.a != var5) {
                     ae.a(var5, false, ae.a);
                  }
                  break;
               case 4:
                  ae.a(var5, true, ae.a);
                  break;
               case 5:
                  this.c = false;
                  this.a = true;
                  l var43 = ae.a.a;
                  if (ae.a.a.c) {
                     var43.c = false;
                     var43.d = true;

                     for (int var35 = 0; var35 < 149; var35++) {
                        ae.a(var43.b[var35], true, var43);
                     }
                  }

                  var43 = ae.a.a;
                  if (!ae.a.a.b) {
                     var43.b = true;
                     var43.a[1] = System.currentTimeMillis();
                     if (!(var43 = (l)var43).a) {
                        var43.a.c();
                        var43.a = true;
                     }
                  }
                  break;
               case 6:
                  this.c = System.currentTimeMillis();
                  if (this.a) {
                     l var40 = ae.a.a;
                     if (!ae.a.a.c) {
                        var40.c = true;
                        var40.d = true;
                     }

                     var40 = ae.a.a;
                     if (ae.a.a.b) {
                        var40.b = false;
                        long[] var10000 = var40.a;
                        long var13 = var40.a[0] + (System.currentTimeMillis() - var40.a[1]);
                        var10000[0] = var13;
                        var40.a[1] = 0L;
                        if ((var40 = (l)var40).a) {
                           var40.a = var40.a + (System.currentTimeMillis() - var40.c);
                           s var27;
                           (var27 = ae.a().a).d = 0;
                           var27.a = 0;
                           var27.b = 0;
                           var27.c = 0;
                           var40.a = false;
                           var40.a.b();
                        }
                     }

                     ae.a();
                     ae.a(this.d);

                     try {
                        Thread.sleep(1L);
                     } catch (InterruptedException var19) {
                     }

                     this.a = false;
                     this.g = true;
                  }
                  break;
               case 7:
                  ae.a = true;
                  ae.a.a.d = true;
                  break;
               case 8:
                  v var39 = ae.a.a;
                  if (!ae.a.a.e) {
                     var39.e = true;
                  }

                  ae.b(ae.a);
            }
         }

         // $VF: monitorexit
      } catch (Throwable var25) {
         Throwable var7 = var25;

         while (true) {
            try {
               // $VF: monitorexit
               throw var7;
            } catch (Throwable var21) {
               var7 = var21;
            }
         }
      }

      if (this.a) {
         if (this.h && ae.a.isShown()) {
            this.h = false;
            this.b();
         }

         try {
            Thread.sleep(100);
         } catch (Exception var18) {
         }
      } else {
         Object var28 = ae.a;
         synchronized (ae.a){} // $VF: monitorenter 

         try {
            aa var29 = ae.a;
            int var31;
            if (this.a == -1L) {
               var31 = (int)(System.currentTimeMillis() - this.b);
            } else {
               var31 = (int)(this.a - this.b);
            }

            int var36 = var31;
            var31 -= var29.a;
            var8 = ae.a.a;
            int var46 = var8.b;
            if (ae.a.a.a != 0) {
               if (var46 == Integer.MAX_VALUE) {
                  var46 = -350;
               } else if ((var46 += var31) > 150) {
                  var46 = 0;
               }
            }

            var8.b = var46;
            l var49;
            s var47 = (var49 = var8).a;
            long var50 = System.currentTimeMillis();
            long var9 = 50;
            long var11;
            if ((var11 = var50 - var49.b) < var9) {
               try {
                  Thread.sleep(var9 - var11);
               } catch (InterruptedException var17) {
               }
            }

            var49.b = System.currentTimeMillis();
            var49.c = var50;
            var49.a.a(var50 - var49.a);
            var49.d = true;
            s var33 = var47;
            if (var47.d > 0) {
               var33.d--;
            }

            var33.b = 0;
            var33.c = 0;
            var29.a = var36;
            ae.a = true;
            // $VF: monitorexit
         } catch (Throwable var23) {
            Throwable var30 = var23;

            while (true) {
               try {
                  // $VF: monitorexit
                  throw var30;
               } catch (Throwable var20) {
                  var30 = var20;
               }
            }
         }

         if (this.f) {
            return;
         }

         if (var1 - this.c < 2000L) {
            ae.a = true;
            ae.a.a.d = true;
         }

         if (ae.a && ae.a.a.d) {
            ae.a.repaint();

            try {
               ae.a.serviceRepaints();
            } catch (Exception var16) {
            }
         }

         int var34 = (int)(System.currentTimeMillis() - var1);
         var1 = ae.a - var34;

         try {
            if (var1 < 1L) {
               var1 = 1L;
            }

            Thread.sleep(var1);
         } catch (InterruptedException var15) {
         }
      }

      if (!this.f) {
         Display.getDisplay(this).callSerially(this);
      }
   }
}
