public final class h implements j {
   byte a = 0;
   int a = 0;
   int b = 0;
   int c;
   int d;
   int e = 0;
   int f = 0;
   int g = 0;
   int h = 0;
   int i = 0;
   int j = 0;
   int k;
   int l = 0;
   int m = 0;
   int n = 0;
   int o = 0;
   int p = 0;
   int q;

   h() {
      this.a = -1;
      this.q = -1;
   }

   final int a(h var1) {
      if (var1 == null) {
         return 1;
      }

      if (this.a == -1) {
         return -1;
      }

      if (this.b <= 0 && var1.b > 0) {
         return 1;
      }

      if (this.b > 0 && var1.b <= 0) {
         return -1;
      }

      if (this.a > 0 && var1.a <= 0) {
         return 1;
      }

      if (this.a <= 0 && var1.a > 0) {
         return -1;
      }

      if (this.q == var1.q) {
         return this.b() < var1.b() ? -1 : 1;
      }

      int var2 = this.a();
      int var3 = var1.a();
      return var2 < var3 * 5 / 6 ? -1 : 1;
   }

   private int a() {
      int var1 = this.a;
      int var2 = this.b;
      Math.abs(this.e - this.i);
      Math.abs(this.f - this.j);
      return (var1 + 25 << 16) / (var2 + 25);
   }

   private int b() {
      int var2 = 0;
      switch (this.q) {
         case 0:
            int var5 = y.c((Math.abs(this.i - this.g) + Math.abs(this.j - this.h)) / 640, 65536);
            var2 = 0 - var5 * 1000;
            var5 = y.c((Math.abs(this.e - this.i) + Math.abs(this.f - this.j)) / 640, 65536);
            var2 -= var5 * 500;
            var5 = (this.a - this.b) / 100;
            var2 += var5 * 230;
            break;
         case 1:
            int var1 = y.c((Math.abs(this.i - this.g) + Math.abs(this.j - this.h)) / 640, 65536);
            var2 = 0 - var1 * 20;
            var1 = y.c((Math.abs(this.e - this.i) + Math.abs(this.f - this.j)) / 640, 65536);
            var2 -= var1 * 20;
            var1 = (this.a - this.b) / 20;
            var2 += var1 * 5;
         case 2:
         default:
            break;
         case 3:
            var2 = this.a - this.b;
      }

      return var2;
   }
}
