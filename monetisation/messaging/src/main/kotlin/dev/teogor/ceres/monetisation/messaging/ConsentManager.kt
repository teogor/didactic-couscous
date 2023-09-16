package dev.teogor.ceres.monetisation.messaging

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import dev.teogor.ceres.monetisation.admob.AdMobInitializer
import java.util.concurrent.atomic.AtomicBoolean

class ConsentManager(
  private val activity: Activity,
) {

  private val TAG = "ConsentManager"
  private var isMobileAdsInitializeCalled = AtomicBoolean(false)
  private val consentInformation: ConsentInformation
    get() = UserMessagingPlatform.getConsentInformation(activity)

  init {
    // Request consent information
    val params = ConsentRequestParameters.Builder()
      .setTagForUnderAgeOfConsent(false)
      .build()

    consentInformation.requestConsentInfoUpdate(
      activity,
      params,
      { onConsentInfoUpdateSuccess() },
      { requestConsentError ->
        Log.w(
          TAG,
          String.format("%s: %s", requestConsentError.errorCode, requestConsentError.message),
        )
      },
    )

    // Check if you can initialize the Google Mobile Ads SDK in parallel
    // while checking for new consent information. Consent obtained in
    // the previous session can be used to request ads.
    if (consentInformation.canRequestAds()) {
      initializeMobileAdsSdk(activity)
    }
  }

  private fun onConsentInfoUpdateSuccess() {
    if (consentInformation.isConsentFormAvailable) {
      // Show the consent form
      UserMessagingPlatform.loadAndShowConsentFormIfRequired(
        activity,
      ) { loadAndShowError ->
        // Consent gathering failed.
        loadAndShowError?.let { value ->
          Log.w(
            TAG,
            String.format(
              "%s: %s",
              value.errorCode,
              value.message,
            ),
          )
        }
      }
    } else {
      // Consent has been gathered or not required
      if (consentInformation.canRequestAds()) {
        initializeMobileAdsSdk(activity)
      }
    }
  }

  private fun initializeMobileAdsSdk(context: Context) {
    if (isMobileAdsInitializeCalled.get()) {
      return
    }
    isMobileAdsInitializeCalled.set(true)

    // Initialize the Google Mobile Ads SDK.
    AdMobInitializer.initialize(context)
  }
}
