package dev.teogor.ceres.monetisation.admob

import android.content.Context
import com.google.android.gms.ads.MobileAds

object AdMobInitializer {

  fun initialize(context: Context) {
    MobileAds.initialize(context) {}
  }

}
