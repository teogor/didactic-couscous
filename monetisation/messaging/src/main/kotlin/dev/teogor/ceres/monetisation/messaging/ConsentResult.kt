package dev.teogor.ceres.monetisation.messaging

import com.google.android.ump.ConsentInformation.PrivacyOptionsRequirementStatus
import com.google.android.ump.FormError

sealed class ConsentResult {
  data object Undefined : ConsentResult()

  data class ConsentFormAvailable(
    val canRequestAds: Boolean,
    val requirementStatus: PrivacyOptionsRequirementStatus,
  ) : ConsentResult()

  data class ConsentFormUnavailable(
    val canRequestAds: Boolean,
    val requirementStatus: PrivacyOptionsRequirementStatus,
  ) : ConsentResult()

  data class ConsentFormDismissed(
    val canRequestAds: Boolean,
    val requirementStatus: PrivacyOptionsRequirementStatus,
    val formError: FormError?,
  ) : ConsentResult()
}
