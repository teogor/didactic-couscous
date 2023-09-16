package com.google.android.ump;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;

import com.google.android.gms.internal.consent_sdk.zzbg;
import com.google.android.gms.internal.consent_sdk.zzbh;
import com.google.android.gms.internal.consent_sdk.zzbq;
import com.google.android.gms.internal.consent_sdk.zzc;
import com.google.android.gms.internal.consent_sdk.zzct;

/**
 * Provides access to the User Messaging Platform (UMP) for managing consent and privacy options.
 */
public final class UserMessagingPlatform {
  /**
   * Retrieves the consent information for the provided context.
   *
   * @param context The context for which to retrieve consent information.
   * @return The ConsentInformation instance.
   */
  @NonNull
  public static ConsentInformation getConsentInformation(@NonNull Context context) {
    return zzc.zza(context).zzb();
  }

  private UserMessagingPlatform() {
  }

  /**
   * Loads and shows the consent form if required, given the provided activity and dismissed listener.
   *
   * @param activity          The activity for which to load and show the consent form.
   * @param dismissedListener The listener to handle the consent form dismissal.
   */
  public static void loadAndShowConsentFormIfRequired(@NonNull Activity activity, @NonNull ConsentForm.OnConsentFormDismissedListener dismissedListener) {
    if (zzc.zza(activity).zzb().canRequestAds()) {
      dismissedListener.onConsentFormDismissed(null);
    } else {
      zzbq consentFormBuilder = zzc.zza(activity).zzc();
      zzct.zza();
      zzbg consentForm = new zzbg(activity, dismissedListener);
      consentFormBuilder.zzb(consentForm, new zzbh(dismissedListener));
    }
  }

  /**
   * Loads the consent form given the provided context and listeners for success and failure.
   *
   * @param context         The context for which to load the consent form.
   * @param successListener The listener for handling the consent form load success.
   * @param failureListener The listener for handling the consent form load failure.
   */
  public static void loadConsentForm(@NonNull Context context, @NonNull OnConsentFormLoadSuccessListener successListener, @NonNull OnConsentFormLoadFailureListener failureListener) {
    zzc.zza(context).zzc().zzb(successListener, failureListener);
  }

  /**
   * Shows the privacy options form given the provided activity and dismissed listener.
   *
   * @param activity          The activity for which to show the privacy options form.
   * @param dismissedListener The listener to handle the privacy options form dismissal.
   */
  public static void showPrivacyOptionsForm(@NonNull Activity activity, @NonNull ConsentForm.OnConsentFormDismissedListener dismissedListener) {
    zzc.zza(activity).zzc().zze(activity, dismissedListener);
  }

  /**
   * Listener interface for handling consent form load success events.
   */
  public interface OnConsentFormLoadSuccessListener {
    /**
     * Called when the consent form is loaded successfully.
     *
     * @param form The loaded consent form.
     */
    void onConsentFormLoadSuccess(@NonNull ConsentForm form);
  }

  /**
   * Listener interface for handling consent form load failure events.
   */
  public interface OnConsentFormLoadFailureListener {
    /**
     * Called when the consent form load fails.
     *
     * @param error The error that occurred during consent form load.
     */
    void onConsentFormLoadFailure(@NonNull FormError error);
  }
}
