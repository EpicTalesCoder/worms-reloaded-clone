package com.ea.sdk;

public class SDKMIDlet extends z {
   public static SDKMIDlet a;

   public SDKMIDlet() {
      a = this;
   }

   public static void a() {
      ae.a();
   }

   public static String a(String var0) {
      if ((var0 = ae.a(var0)).equals("NULL")) {
         var0 = null;
      }

      return var0;
   }
}
