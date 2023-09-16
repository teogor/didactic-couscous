package com.google.android.gms.internal.consent_sdk;

import android.app.Activity;

import com.google.android.ump.ConsentForm;
import com.google.android.ump.UserMessagingPlatform;

public final class zzbg implements UserMessagingPlatform.OnConsentFormLoadSuccessListener {
    private final Activity zza;
    private final ConsentForm.OnConsentFormDismissedListener zzb;

    public zzbg(Activity var1, ConsentForm.OnConsentFormDismissedListener var2) {
        this.zza = var1;
        this.zzb = var2;
    }

    public final void onConsentFormLoadSuccess(ConsentForm var1) {
        var1.show(this.zza, this.zzb);
    }
}
