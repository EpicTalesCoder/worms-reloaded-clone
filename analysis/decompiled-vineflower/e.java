import javax.microedition.lcdui.Image;

public final class e {
   private n a = null;
   public af a;

   public final n a() {
      if (this.a == null) {
         this.a = new n(this);
      }

      return this.a;
   }

   public e(int var1, int var2) {
      this.a = new u();
      u var10000 = (u)this.a;
      short var10001 = (short)var1;
      short var3 = (short)var2;
      short var5 = var10001;
      u var4 = var10000;
      var10000.a = Image.createImage(var5, var3);
      var4.a = var5;
      var4.b = var3;
   }

   public e(byte[] var1, int var2, int var3) {
      this.a = new u();
      var1 = var1;
      u var10000 = (u)this.a;
      byte[] var10001 = var1;
      int var4 = var3;
      byte[] var7 = var10001;
      u var6 = var10000;
      var10000.a = Image.createImage(var7, 0, var4);
      var6.a = (short)var6.a.getWidth();
      var6.b = (short)var6.a.getHeight();
   }

   public e(String var1) {
      this.a = new u(ae.a(var1));
   }

   public e() {
      this.a = new u();
   }
}
