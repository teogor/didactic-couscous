package dev.teogor.ceres.monetisation.admob.formats

import androidx.annotation.Keep

@Keep
enum class AdType {
  AppOpen,
  Banner,
  Interstitial,
  InterstitialVideo,
  RewardedInterstitial,
  Native,
  NativeVideo,
  Rewarded,
  Unspecified;

  fun type(): AdType = this
}
