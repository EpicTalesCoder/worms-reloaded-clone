import javax.microedition.lcdui.Image;

public final class u extends af {
   public Image a;

   public u() {
   }

   public u(x var1) {
      String var2;
      var2 = new String./* $VF: Unable to resugar constructor */<init>(ae.a(var1));
      String var4 = var2;
      if (var2.charAt(0) != '/') {
         var4 = '/' + var2;
      }

      try {
         this.a = Image.createImage(var4);
      } catch (Exception var3) {
      }

      if (this.a != null) {
         super.a = (short)this.a.getWidth();
         super.b = (short)this.a.getHeight();
      }
   }

   public final Image a() {
      return this.a;
   }
}
