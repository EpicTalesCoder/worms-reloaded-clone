public final class c {
   public byte[] a;
   public int a;
   public int b;
   public int c;
   public byte a;
   private byte[] b;
   private c[] a;

   public c() {
   }

   public c(String var1) {
      this.a = 0;
      this.b = var1.length();
      this.c = this.b;
      this.a = 65;
      this.a = new byte[this.c];

      for (int var2 = 0; var2 < this.a.length; var2++) {
         this.a[var2] = 0;
      }

      for (int var3 = 0; var3 < this.b; var3++) {
         this.a[var3] = (byte)var1.charAt(var3);
      }
   }

   public c(int var1) {
      this.a = 65;
      this.a = 0;
      this.a = ae.a().a(var1);
      this.b = ae.a(ae.a(ae.a(new String(this.a))));
      this.c = this.b;
   }

   public c(int var1, int var2) {
      this.a = (byte)(var2 | 128 | 64);
      if (var2 == 0) {
         var2 = var1 << 1;
      } else {
         var2 = var1;
      }

      this.a = new byte[var2];
      this.c = var1;
      this.a = 0;
      this.b = 0;
   }

   private boolean a() {
      boolean var1;
      if ((this.a & 128) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final c a(c var1) {
      if (!this.a()) {
         return this;
      }

      byte var2 = (byte)(this.a & 63);
      byte var3;
      if ((var3 = (byte)(var1.a & 63)) != var2 && var3 != true && var2 != 0) {
         return this;
      }

      int var4 = this.a;
      int var5;
      if (var2 == 0) {
         var5 = this.b << 1;
      } else {
         var5 = this.b;
      }

      ae.a().a(var1.a, var1.a, this.a, var4 + var5, (short)var1.b, var3, var2);
      this.b = this.b + var1.b;
      return this;
   }

   public final c a(String var1) {
      if (!this.a()) {
         return this;
      }

      int var2 = var1.length();
      int var3 = this.b;
      this.b += var2;

      for (int var4 = 0; var4 < var2; var4++) {
         int var10001 = var3 + var4;
         char var7 = var1.charAt(var4);
         int var6 = var10001;
         c var5 = this;
         if (this.a()) {
            byte var8 = (byte)(var5.a & 63);
            short var10;
            if ((var10 = (short)var7) > 127 && var8 != 0) {
               m var10000 = ae.a();
               short var9 = var10;
               m var11 = var10000;
               var10 = (short)(ae.a(var10000.d, var9, 0, 127, 128, var11) + 128);
            }

            int var12 = var5.a;
            if (var8 == 0) {
               var6 <<= 1;
            }

            var12 += var6;
            if (var8 == 0) {
               var5.a[var12++] = (byte)(var10 >> 8);
            }

            var5.a[var12] = (byte)var10;
         }
      }

      return this;
   }

   public final c a(int var1) {
      if (!this.a()) {
         return this;
      }

      if (this.b == null) {
         this.b = new byte[11];
      }

      int var2 = 10;
      long var3 = var1;
      if (var1 < 0) {
         var3 = -var3;
      }

      for (int var5 = 10; var5 >= 0; var5--) {
         this.b[var5] = (byte)(var3 % 10L | 48L);
         var3 /= 10L;
         if (this.b[var5] != 48) {
            var2 = var5;
         }
      }

      if (var1 < 0) {
         this.b[--var2] = 45;
      }

      short var8 = (short)(11 - var2);
      byte var6 = (byte)(this.a & 63);
      int var7 = this.a;
      int var4;
      if (var6 == 0) {
         var4 = this.b << 1;
      } else {
         var4 = this.b;
      }

      ae.a().a(this.b, var2, this.a, var7 + var4, var8, 1, var6);
      this.b += var8;
      return this;
   }

   public final char a(int var1) {
      short var2;
      return (var2 = this.a(var1)) > 127 && (this.a & '?') != 0 ? (char)ae.a().d[var2 - 128] : (char)var2;
   }

   public final short a(int var1) {
      int var2;
      return (this.a & 63) == 0 ? (short)((this.a[var2 = this.a + (var1 << 1)] & 255) << 8 | this.a[var2 + 1] & 0xFF) : (short)(this.a[this.a + var1] & 0xFF);
   }

   private c a(c[] var1) {
      c var2;
      var2 = new c./* $VF: Unable to resugar constructor */<init>();
      var2.a = (byte)(this.a & 63);
      var2.a = 0;
      int var3 = 0;

      for (int var4 = 0; var4 < this.b; var4++) {
         if (this.a(var4) == 92) {
            if (++var4 < this.b) {
               short var5;
               if ((var5 = this.a(var4)) == 92) {
                  var3--;
               } else if (var5 >= 48 && var5 <= 90) {
                  var5 -= 48;
                  if ((var1[var5].a & 63) != var2.a && (var1[var5].a & 63) != 1) {
                     var2.a = 0;
                  }

                  var3 += var1[var5].b - 2;
               }
            }
         }
      }

      var2.a = (byte)(var2.a | 64);
      var2.b = (short)(this.b + var3);
      var2.c = var2.b;
      int var11;
      if ((var2.a & 63) == 0) {
         var11 = var2.b << 1;
      } else {
         var11 = var2.b;
      }

      var2.a = new byte[var11];
      var11 = 0;
      int var9 = 0;
      byte var8;
      if ((this.a & 63) == 0) {
         var8 = 2;
      } else {
         var8 = 1;
      }

      for (int var6 = 0; var6 < this.b; var6++) {
         if (this.a(var6) == 92) {
            var11 += ae.a().a(this.a, this.a + var9 * var8, var2.a, var11, (short)(var6 - var9), this.a & 63, var2.a & 63);
            var9 = var6++;
            if (var6 < this.b) {
               short var7;
               if ((var7 = this.a(var6)) == 92) {
                  var9 = var6;
               } else if (var7 >= 48 && var7 <= 90) {
                  var7 -= 48;
                  var11 += ae.a().a(var1[var7].a, var1[var7].a, var2.a, var11, (short)var1[var7].b, var1[var7].a & 63, var2.a & 63);
                  var9 = var6 + 1;
               }
            }
         }
      }

      ae.a().a(this.a, this.a + var9 * var8, var2.a, var11, (short)(this.b - var9), this.a & 63, var2.a & 63);
      return var2;
   }

   public final c b(c var1) {
      if (this.a == null) {
         this.a = new c[1];
      }

      this.a[0] = var1;
      return this.a(this.a);
   }

   public final c b(String var1) {
      if (this.a == null) {
         this.a = new c[1];
      }

      this.a[0] = new c(var1);
      c var10000 = this.a(this.a);
      this.a[0] = null;
      return var10000;
   }

   public final c b(int var1) {
      if (this.a == null) {
         this.a = new c[1];
      }

      this.a[0] = new c(var1);
      c var10000 = this.a(this.a);
      this.a[0] = null;
      return var10000;
   }

   public final int a() {
      return this.a & 63;
   }

   public final void a() {
      if (this.a()) {
         this.b = 0;
      }
   }

   public final String toString() {
      StringBuffer var1;
      var1 = new StringBuffer./* $VF: Unable to resugar constructor */<init>(this.b);

      for (int var2 = 0; var2 < this.b; var2++) {
         var1.append(this.a(var2));
      }

      return var1.toString();
   }
}
