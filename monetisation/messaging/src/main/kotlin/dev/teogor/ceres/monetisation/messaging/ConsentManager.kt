package dev.teogor.ceres.monetisation.messaging

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.provider.Settings
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import dev.teogor.ceres.core.runtime.AppMetadataManager
import dev.teogor.ceres.monetisation.admob.AdMobInitializer
import java.io.UnsupportedEncodingException
import java.lang.ref.WeakReference
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.atomic.AtomicBoolean


object ConsentManager {
  internal lateinit var consentInformation: ConsentInformation
  private var weakActivity: WeakReference<Activity>? = null

  private val TAG = "ConsentManager"
  private var isMobileAdsInitializeCalled = AtomicBoolean(false)

  val state: MutableState<ConsentResult> = mutableStateOf(ConsentResult.Undefined)

  var activity: Activity?
    get() = weakActivity?.get()
    set(value) {
      weakActivity = if (value != null) {
        WeakReference(value)
      } else {
        null
      }
    }

  fun loadAndShowConsentFormIfRequired() {
    activity?.let {
      UserMessagingPlatform.loadAndShowConsentFormIfRequired(
        it,
      ) { formError ->
        state.value = ConsentResult.ConsentFormDismissed(
          canRequestAds = consentInformation.canRequestAds(),
          requirementStatus = consentInformation.privacyOptionsRequirementStatus,
          formError = formError,
        )
      }
    }
  }

  fun resetConsent() {
    if (!::consentInformation.isInitialized) {
      return
    }
    consentInformation.reset()
    activity?.let { activity ->
      initialiseConsentForm(activity)
    }
  }

  fun initialiseConsentForm(
    activity: Activity,
  ) {
    getHashedAdvertisingId(activity)
    consentInformation = UserMessagingPlatform.getConsentInformation(activity)

    val debugSettings = ConsentDebugSettings.Builder(activity).apply {
      if(AppMetadataManager.isDebuggable) {
        addTestDeviceHashedId(getHashedAdvertisingId(activity))
        setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
      }
    }.build()

    // Request consent information
    val params = ConsentRequestParameters.Builder()
      .setTagForUnderAgeOfConsent(false)
      .setConsentDebugSettings(debugSettings)
      .build()

    consentInformation.requestConsentInfoUpdate(
      activity,
      params,
      { onConsentInfoUpdateSuccess(activity) },
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

  private fun onConsentInfoUpdateSuccess(
    activity: Activity,
  ) {
    if (!consentInformation.isConsentFormAvailable) {
      // Consent has been gathered or not required
      if (consentInformation.canRequestAds()) {
        initializeMobileAdsSdk(activity)
      }

      state.value = ConsentResult.ConsentFormAcquired(
        canRequestAds = consentInformation.canRequestAds(),
        requirementStatus = consentInformation.privacyOptionsRequirementStatus,
        formAvailable = false,
      )
    } else {
      state.value = ConsentResult.ConsentFormAcquired(
        canRequestAds = consentInformation.canRequestAds(),
        requirementStatus = consentInformation.privacyOptionsRequirementStatus,
        formAvailable = true,
      )
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

  private fun MD5(md5: String): String? {
    try {
      val md = MessageDigest.getInstance("MD5")
      val array = md.digest(md5.toByteArray(charset("UTF-8")))
      val sb = StringBuffer()
      for (i in array.indices) {
        sb.append(Integer.toHexString(array[i].toInt() and 0xFF or 0x100).substring(1, 3))
      }
      return sb.toString()
    } catch (_: NoSuchAlgorithmException) {
    } catch (_: UnsupportedEncodingException) {
    }
    return null
  }

  @SuppressLint("HardwareIds")
  private fun getHashedAdvertisingId(
    context: Context,
  ): String {
    val androidId: String = Settings.Secure.getString(
      context.contentResolver, Settings.Secure.ANDROID_ID,
    )
    return MD5(androidId)?.uppercase() ?: ""
  }
}
