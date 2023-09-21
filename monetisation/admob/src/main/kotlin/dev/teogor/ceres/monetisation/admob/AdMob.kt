package dev.teogor.ceres.monetisation.admob

import dev.teogor.ceres.monetisation.admob.formats.AppOpenAd
import java.lang.ref.WeakReference

object AdMob {

  private var weakRefAppOpenAd: WeakReference<AppOpenAd> = WeakReference<AppOpenAd>(null)

  fun setApplicationOpenAd(appOpenAd: AppOpenAd) {
    weakRefAppOpenAd = WeakReference(appOpenAd)
    appOpenAd.load()
  }

  fun getAppOpenAd() = weakRefAppOpenAd.get()

}
