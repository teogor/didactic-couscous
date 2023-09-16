package com.google.android.gms.internal.consent_sdk;

import android.app.Activity;
import android.util.Log;
import androidx.annotation.WorkerThread;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.UserMessagingPlatform;
import com.google.android.ump.ConsentInformation.PrivacyOptionsRequirementStatus;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

public final class zzbq {
    private final zzdr zza;
    private final Executor zzb;
    private final AtomicReference<zzbs> zzc = new AtomicReference<>();
    private final AtomicReference<ConsentForm> zzd = new AtomicReference<>();

    zzbq(zzdr var1, Executor var2) {
        this.zza = var1;
        this.zzb = var2;
    }

    public final void zzb(UserMessagingPlatform.OnConsentFormLoadSuccessListener var1, UserMessagingPlatform.OnConsentFormLoadFailureListener var2) {
        zzct.zza();
        zzbs var3 = this.zzc.get();
        if (var3 == null) {
            String var4 = "No available form can be built.";
            var2.onConsentFormLoadFailure((new zzi(3, var4)).zza());
        } else {
            zzay var10000 = (zzay) this.zza.zzb();
            var10000.zza(var3);
            var10000.zzb().zza().zzb(var1, var2);
        }
    }

    @WorkerThread
    public final void zzc() {
        zzbs var1 = this.zzc.get();
        if (var1 == null) {
            Log.e("UserMessagingPlatform", "Failed to load and cache a form due to null consent form resources.");
        } else {
            zzay var10000 = (zzay) this.zza.zzb();
            var10000.zza(var1);
            zzbe var2 = var10000.zzb().zza();
            var2.zza = true;
//            zzct.zza.post(new zzbk(this, var2));
        }
    }

    public final void zzd(zzbs var1) {
        this.zzc.set(var1);
    }

    public final void zze(Activity var1, ConsentForm.OnConsentFormDismissedListener var2) {
        zzct.zza();
        zzl var3 = com.google.android.gms.internal.consent_sdk.zzc.zza(var1).zzb();
        if (var3 == null) {
//            zzct.zza.post(new zzbl(var2));
        } else if (!var3.isConsentFormAvailable() && var3.getPrivacyOptionsRequirementStatus() != PrivacyOptionsRequirementStatus.NOT_REQUIRED) {
//            zzct.zza.post(new zzbm(var2));
            var3.zza(var1);
        } else if (var3.getPrivacyOptionsRequirementStatus() == PrivacyOptionsRequirementStatus.NOT_REQUIRED) {
//            zzct.zza.post(new zzbn(var2));
        } else {
            ConsentForm var4 = this.zzd.get();
            if (var4 == null) {
//                zzct.zza.post(new zzbo(var2));
            } else {
                var4.show(var1, var2);
//                this.zzb.execute(new zzbp(this));
            }
        }
    }

    public final boolean zzf() {
        return this.zzc.get() != null;
    }
}
