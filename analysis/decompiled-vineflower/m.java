import com.ea.sdk.SDKMIDlet;
import java.io.IOException;
import java.io.InputStream;

public final class m {
   private byte[] f;
   public byte[] a;
   public byte[][] a;
   public short[] a;
   public byte[] b;
   public byte[] c;
   public short[][] a;
   public short[] b;
   public byte[] d;
   public byte[][] b;
   public short[][] b;
   public byte a;
   public byte b;
   public byte c;
   public short[] c;
   public byte[] e;
   public byte d;
   public byte e;
   public short[] d = new short[128];
   public int a;
   public short[] e;
   public int b;
   public n a;
   public int[] a = null;
   public int[] b = null;
   public StringBuffer a;
   public boolean a;
   public boolean b;
   public q a;
   public g a = new g(6);

   public m() {
      this.a = new int[2];
      this.b = new int[4];
      this.d = 0;
   }

   public final byte[] a(int var1) {
      if (this.f == null) {
         this.f = new byte[11];
      }

      int var2 = 10;
      int var3 = var1;
      if (var1 < 0) {
         var3 = -var1;
      }

      for (int var4 = 10; var4 >= 0; var4--) {
         this.f[var4] = (byte)(var3 % 10 | 48);
         var3 /= 10;
         if (this.f[var4] != 48) {
            var2 = var4;
         }
      }

      if (var1 < 0) {
         this.f[--var2] = 45;
      }

      short var6;
      byte[] var5 = new byte[(var6 = (short)(11 - var2)) + 1];
      System.arraycopy(this.f, var2, var5, 0, var6);
      var5[var6] = 0;
      return var5;
   }

   public final int a(byte[] var1, int var2, byte[] var3, int var4, short var5, int var6, int var7) {
      int var8;
      if (var7 == 0) {
         var8 = var5 << 1;
      } else {
         var8 = var5;
      }

      if (var6 != var7 && (var6 != 1 || var7 == 0)) {
         for (int var10 = var2; var10 < var2 + var5; var10++) {
            short var9;
            if ((var9 = (short)(var1[var10] & 0xFF)) > 127) {
               var9 = this.d[var9 - 128];
            }

            int var10001 = var4 + 1;
            int var10002 = var4 + 1;
            var3[var4] = (byte)(var9 >> 8);
            var4 = var10002 + 1;
            var3[var10001] = (byte)var9;
         }
      } else {
         System.arraycopy(var1, var2, var3, var4, var8);
      }

      return var8;
   }

   public final int a(short var1) {
      int var2 = this.a.a[6] & 255;
      return var1 == -1 ? (this.a.a[5] & 0xFF) + var2 : (this.a.b[var1 & 65535] & 0xFF) + var2;
   }

   public final short a(short var1, int var2) {
      int var3 = this.a.b.length;
      if (var2 == 0) {
         return this.a.c[ae.a(this.a.b, var1, 0, var3 - 1, var3, this)];
      } else {
         return var1 >= 128 && var2 != this.a[this.d] ? -1 : this.a.c[this.a.a[var1] & 65535];
      }
   }

   public final void a(int[] var1, int var2) {
      byte var3;
      if ((var3 = this.a.a[8]) == 1) {
         var1[0] += var2;
      } else if (var3 == 4) {
         var1[0] -= var2;
      } else if (var3 == 2) {
         var1[1] += var2;
      } else {
         if (var3 == 8) {
            var1[1] -= var2;
         }
      }
   }

   public final void a(byte var1) {
      int var2 = 0;

      for (short var3 = 1; var3 < var1; var3++) {
         if ((this.a & 1 << var3) != 0) {
            var2++;
         }
      }

      byte[] var8 = new byte[2];

      try {
         InputStream var5;
         p.a(var5 = SDKMIDlet.a.getClass().getResourceAsStream("/charmap"), var2 << 8);

         for (int var6 = 0; var6 < 128; var6++) {
            var8[0] = (byte)var5.read();
            var8[1] = (byte)var5.read();
            this.d[var6] = ae.a(var8, 0);
         }

         var5.close();
      } catch (IOException var4) {
      }

      if (this.a.b != null && (this.a.a[10] & 4) == 0) {
         int var9 = this.a.b.length;

         for (short var7 = 128; var7 < 256; var7++) {
            this.a.a[var7] = (short)ae.a(this.a.b, this.d[var7 - 128], 0, var9 - 1, var9, this);
         }

         this.a.a[11] = this.a[this.d];
      }
   }
}
