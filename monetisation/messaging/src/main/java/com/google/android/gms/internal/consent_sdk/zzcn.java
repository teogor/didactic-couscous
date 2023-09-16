package com.google.android.gms.internal.consent_sdk;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class zzcn {
    @GuardedBy("DeviceId.class")
    @Nullable
    private static String zza;

    @SuppressLint("HardwareIds")
    public static synchronized String zza(Context var0) {
        if (zza == null) {
            ContentResolver var1 = var0.getContentResolver();
            String var2;
            if (var1 == null) {
                var2 = null;
            } else {
                var2 = Secure.getString(var1, "android_id");
            }

            if (var2 == null || zzb()) {
                var2 = "emulator";
            }

            zza = zzc(var2);
        }

        return zza;
    }

    private static String zzc(String var0) {
        int var1 = 0;

        while (true) {
            if (var1 < 3) {
                String var10000;
                try {
                    MessageDigest var2 = MessageDigest.getInstance("MD5");
                    var2.update(var0.getBytes());
                    var10000 = String.format("%032X", new BigInteger(1, var2.digest()));
                } catch (NoSuchAlgorithmException var3) {
                    ++var1;
                    continue;
                } catch (ArithmeticException var4) {
                    return "";
                }

                var0 = var10000;
                return var0;
            }

            return "";
        }
    }

    public static boolean zzb() {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("sdk_goog3") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic") || "google_sdk".equals(Build.PRODUCT);
    }
}
