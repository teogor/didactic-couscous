package com.google.android.gms.internal.consent_sdk;

import android.app.Activity;

import com.google.android.ump.ConsentForm;
import com.google.android.ump.UserMessagingPlatform;

/**
 * Implementation of the {@link UserMessagingPlatform.OnConsentFormLoadSuccessListener} interface.
 * This class is responsible for handling the success callback when a consent form is loaded.
 */
public final class zzbg implements UserMessagingPlatform.OnConsentFormLoadSuccessListener {
  private final Activity activity;
  private final ConsentForm.OnConsentFormDismissedListener dismissedListener;

  /**
   * Constructs a new instance of zzbg.
   *
   * @param activity          The activity in which to show the consent form.
   * @param dismissedListener A listener to handle form dismissal events.
   */
  public zzbg(Activity activity, ConsentForm.OnConsentFormDismissedListener dismissedListener) {
    this.activity = activity;
    this.dismissedListener = dismissedListener;
  }

  /**
   * Called when the consent form is successfully loaded.
   *
   * @param consentForm The loaded consent form.
   */
  public final void onConsentFormLoadSuccess(ConsentForm consentForm) {
    // Show the consent form in the specified activity with the provided dismissal listener.
    consentForm.show(this.activity, this.dismissedListener);
  }
}
