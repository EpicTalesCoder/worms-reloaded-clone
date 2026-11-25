public final class r implements j {
   public boolean a;
   public byte a;
   public short a;
   public byte[] a;
   public short[] a;

   private boolean a() {
      return this.a >= 60 && this.a <= 65 ? true : this.a < 80 || this.a > 96;
   }

   final void a() {
      int var1;
      int var2;
      if (this.a()) {
         var1 = y.w[this.a - 60];
         var2 = y.x[this.a - 60];
      } else {
         var1 = y.y[this.a - 80];
         var2 = y.z[this.a - 80];
      }

      if (var1 != 0) {
         this.a = new byte[var1];
      }

      if (var2 != 0) {
         this.a = new short[var2];
      }
   }

   final void a(b var1) {
      this.a = var1.a();
      if (this.a()) {
         this.a = var1.read() > 0;
         this.a[0] = var1.a();
         this.a[0] = (byte)var1.read();
      } else {
         this.a = false;
         this.a[1] = var1.a();
      }

      switch (this.a) {
         case 60:
            this.a[3] = var1.a();
            this.a[4] = var1.a();
            this.a[1] = (byte)var1.read();
            this.a[5] = var1.a();
            this.a[1] = var1.a();
            this.a[2] = var1.a();
            this.a[2] = 0;
            this.a[1] = (short)(this.a[1] + a.G);
            this.a[2] = (short)(this.a[2] + a.H);
            return;
         case 61:
            this.a[3] = var1.a();
            this.a[4] = var1.a();
            this.a[5] = var1.a();
            this.a[1] = var1.a();
            this.a[2] = var1.a();
            this.a[1] = (short)(this.a[1] + a.G);
            this.a[2] = (short)(this.a[2] + a.H);
            return;
         case 62:
            return;
         case 63:
            this.a[1] = (byte)var1.read();
            return;
         case 64:
            this.a[1] = (byte)var1.read();
            return;
         case 65:
            this.a[1] = var1.a();
            return;
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
         case 95:
         default:
            return;
         case 80:
            this.a[2] = var1.a();
            return;
         case 81:
            this.a[2] = var1.a();
            this.a[0] = (byte)var1.read();
            this.a[1] = (byte)var1.read();
            this.a[2] = (byte)var1.read();
            return;
         case 82:
            this.a[2] = var1.a();
            this.a[3] = var1.a();
            this.a[4] = var1.a();
            this.a[5] = var1.a();
            this.a[6] = var1.a();
            this.a[7] = var1.a();
            this.a[8] = var1.a();
            return;
         case 83:
            this.a[2] = var1.a();
            this.a[3] = var1.a();
            this.a[4] = var1.a();
            this.a[5] = var1.a();
            this.a[6] = var1.a();
            this.a[7] = var1.a();
            return;
         case 84:
            this.a[2] = var1.a();
            this.a[0] = (byte)var1.read();
            this.a[1] = (byte)var1.read();
            return;
         case 85:
            this.a[2] = var1.a();
            return;
         case 86:
            this.a[0] = (byte)var1.read();
            this.a[1] = (byte)var1.read();
            this.a[2] = (byte)var1.read();
            this.a[3] = (byte)var1.read();
            this.a[4] = (byte)var1.read();
            this.a[5] = (byte)var1.read();
            return;
         case 87:
            this.a[4] = var1.a();
            this.a[2] = var1.a();
            this.a[3] = var1.a();
            this.a[2] = (short)(this.a[2] + a.G);
            this.a[3] = (short)(this.a[3] + a.H);
            return;
         case 88:
            this.a[2] = var1.a();
            break;
         case 89:
            this.a[2] = var1.a();
            break;
         case 90:
            this.a[4] = var1.a();
            this.a[2] = var1.a();
            this.a[3] = var1.a();
            this.a[2] = (short)(this.a[2] + a.G);
            this.a[3] = (short)(this.a[3] + a.H);
            return;
         case 91:
            this.a[2] = var1.a();
            return;
         case 92:
            return;
         case 93:
            this.a[2] = var1.a();
            this.a[0] = (byte)var1.read();
            this.a[1] = (byte)var1.read();
            return;
         case 94:
            this.a[4] = var1.a();
            this.a[0] = (byte)var1.read();
            this.a[2] = var1.a();
            this.a[3] = var1.a();
            this.a[2] = (short)(this.a[2] + a.G);
            this.a[3] = (short)(this.a[3] + a.H);
            return;
         case 96:
      }

      this.a[0] = (byte)var1.read();
   }

   final boolean a(int var1, int var2) {
      short var6 = 0;
      short var5 = 0;
      short var4 = 0;
      short var3 = 0;
      switch (this.a) {
         case 60:
            var3 = this.a[1];
            var4 = this.a[2];
            var5 = this.a[3];
            var6 = this.a[4];
            break;
         case 61:
            var3 = this.a[1];
            var4 = this.a[2];
            var5 = this.a[3];
            var6 = this.a[4];
      }

      return var1 >= var3 && var2 >= var4 && var1 <= var5 + var3 && var2 <= var6 + var4;
   }

   final void b() {
      r var1;
      if (!(var1 = a.a(this.a[0])).a) {
         var1.a = true;
         var1.a[0] = 0;
      }

      if (this.a[0] > 0) {
         this.a = false;
      }
   }
}
