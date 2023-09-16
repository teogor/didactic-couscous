package com.google.android.ump;

import android.app.Activity;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

public interface ConsentForm {
  /**
   * Show the consent form.
   *
   * @param activity The activity in which to show the consent form.
   * @param listener A listener to handle form dismissal events.
   */
  void show(@NonNull Activity activity, @NonNull OnConsentFormDismissedListener listener);

  /**
   * Listener interface to handle consent form dismissal events.
   */
  public interface OnConsentFormDismissedListener {
    /**
     * Called when the consent form is dismissed.
     *
     * @param error An optional error associated with form dismissal.
     */
    void onConsentFormDismissed(@Nullable FormError error);
  }
}
