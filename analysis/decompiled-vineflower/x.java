public final class x {
   public short[] a;

   public x() {
      ae.a(new short[]{0}, 0, this);
   }

   public x(short[] var1) {
      ae.a(var1, ae.a(var1), this);
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof x)) {
         return false;
      }

      var1 = var1;
      int var2 = ae.a(this.a);
      boolean var6;
      if (ae.a(var1.a) != var2) {
         var6 = false;
      } else {
         short[] var5 = var1.a;
         int var3 = 0;

         while (true) {
            if (var3 >= var2) {
               var6 = true;
               break;
            }

            if (this.a[var3] != var5[var3]) {
               var6 = false;
               break;
            }

            var3++;
         }
      }

      return var6;
   }
}
