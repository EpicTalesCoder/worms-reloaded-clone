import javax.microedition.media.Player;

public final class t {
   public byte[] a;
   public t a;
   public t b;
   public Player a;
   public String a;
   public t c;
   public int a;

   public t() {
   }

   private t(int var1) {
      if (var1 != 1) {
         if (var1 == 2) {
            this.a = new String();
            this.b = new t(0);
         }
      } else {
         this.a = new t(2);
      }
   }

   private t(t var1, String var2) {
      this.a = new t(2);
      this.a = new t(2);
      this.a.a = var2;
      this.a.b = var1;
   }

   public t(byte[] var1, int var2) {
      this.a = var2;
      t var5;
      var5 = new t./* $VF: Unable to resugar constructor */<init>(0);
      var5.a = var1;
      t var3 = new t;
      String var4 = new String("Undefined");
      switch (var1[0]) {
         case -1:
         case 73:
            var4 = "audio/mpeg";
            break;
         case 35:
            var4 = "audio/amr";
            break;
         case 77:
            var4 = "audio/midi";
            break;
         case 82:
            var4 = "audio/x-wav";
      }

      var3./* $VF: Unable to resugar constructor */<init>(var5, var4);
      this.c = var3;
   }
}
