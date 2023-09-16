package com.google.android.ump;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.internal.consent_sdk.zzbg;
import com.google.android.gms.internal.consent_sdk.zzbh;
import com.google.android.gms.internal.consent_sdk.zzbq;
import com.google.android.gms.internal.consent_sdk.zzc;
import com.google.android.gms.internal.consent_sdk.zzct;

public final class UserMessagingPlatform {
    @NonNull
    public static ConsentInformation getConsentInformation(@NonNull Context context) {
        return zzc.zza(context).zzb();
    }

    private UserMessagingPlatform() {
    }

    public static void loadAndShowConsentFormIfRequired(@NonNull Activity activity, @NonNull ConsentForm.OnConsentFormDismissedListener onConsentFormDismissedListener) {
        if (zzc.zza(activity).zzb().canRequestAds()) {
            onConsentFormDismissedListener.onConsentFormDismissed((FormError) null);
        } else {
            zzbq var10000 = zzc.zza(activity).zzc();
            zzct.zza();
            zzbg var10001 = new zzbg(activity, onConsentFormDismissedListener);
            var10000.zzb(var10001, new zzbh(onConsentFormDismissedListener));
        }
    }

    public static void loadConsentForm(@NonNull Context context, @NonNull OnConsentFormLoadSuccessListener successListener, @NonNull OnConsentFormLoadFailureListener failureListener) {
        zzc.zza(context).zzc().zzb(successListener, failureListener);
    }

    public static void showPrivacyOptionsForm(@NonNull Activity activity, @NonNull ConsentForm.OnConsentFormDismissedListener onConsentFormDismissedListener) {
        zzc.zza(activity).zzc().zze(activity, onConsentFormDismissedListener);
    }

    public interface OnConsentFormLoadSuccessListener {
        void onConsentFormLoadSuccess(@NonNull ConsentForm var1);
    }

    public interface OnConsentFormLoadFailureListener {
        void onConsentFormLoadFailure(@NonNull FormError var1);
    }
}
