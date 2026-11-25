import java.util.Vector;

public final class q {
   private f a;
   private Vector a;
   private boolean a;
   private int a;
   private int b;
   private d a = ae.a(ae.a());

   public static q a() {
      m var0;
      if ((var0 = ae.a()).a == null) {
         var0.a = new q();
      }

      return var0.a;
   }

   public final void a(f var1) {
      this.a = var1;
   }

   public final void a(int var1) {
      if (this.a(var1) == -1) {
         t var2;
         var2 = new t./* $VF: Unable to resugar constructor */<init>(b.a(var1), var1);
         this.a.addElement(var2);
      }
   }

   public final boolean a() {
      return this.a;
   }

   public final void a(boolean var1) {
      this.a = var1;
      if (!var1) {
         this.b();
      }
   }

   public final int a() {
      return ae.b(this.a) * 100 / 255;
   }

   public final void b(int var1) {
      boolean var3;
      if (var1 != 0 && var1 != 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      this = this;
      ae.a(var3, this.a);
   }

   public final void a() {
      this.a = 1;
   }

   public final void c(int var1) {
      if (this.a) {
         if (this.a != 1 || !ae.a(this.a)) {
            this.b();
            this.a(var1);
            this = this;
            if (var1 != this.b) {
               int var2 = this.a(var1);
               t var5 = ((t)this.a.elementAt(var2)).c;
               t var3 = var5;
               d var6 = this.a;
               ae.a(var3, var6);
               this.b = var1;
            }

            ae.a(this.a);
         }
      }
   }

   public final void b() {
      if (this.b != -1) {
         int var1 = this.b;
         this = this;
         if (this.b == var1) {
            ae.b(this.a);
         }
      }
   }

   public final void d(int var1) {
      if (var1 == 0) {
         ae.a(ae.a());
      } else {
         g var10000 = ae.a();
         int var2 = var1;
         g var3 = var10000;
         ae.a(var2, var3);
      }
   }

   public q() {
      this.a = new Vector();
      this.a = true;
      this.a = 0;
      this.b = -1;
   }

   private int a(int var1) {
      for (int var2 = 0; var2 < this.a.size(); var2++) {
         if (((t)this.a.elementAt(var2)).a == var1) {
            return var2;
         }
      }

      return -1;
   }
}
