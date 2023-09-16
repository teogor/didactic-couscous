package com.google.android.gms.internal.consent_sdk;

import com.google.android.ump.ConsentForm;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;

public final class zzbh implements UserMessagingPlatform.OnConsentFormLoadFailureListener {
  private final ConsentForm.OnConsentFormDismissedListener zza;

  public zzbh(ConsentForm.OnConsentFormDismissedListener var1) {
    this.zza = var1;
  }

  public final void onConsentFormLoadFailure(FormError var1) {
    this.zza.onConsentFormDismissed(var1);
  }
}
