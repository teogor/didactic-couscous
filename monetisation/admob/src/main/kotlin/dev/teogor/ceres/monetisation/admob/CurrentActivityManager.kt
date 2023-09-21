package dev.teogor.ceres.monetisation.admob

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.google.android.gms.ads.AdActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
internal class CurrentActivityManager(
  context: Context,
) {
  init {
    if (context is Application) {
      context.registerActivityLifecycleCallbacks(
        object : Application.ActivityLifecycleCallbacks {
          override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            CurrentActivityHolder.activity = activity
          }

          override fun onActivityStarted(activity: Activity) {
            // Your initialization code when an activity is started
            CurrentActivityHolder.activity = activity
          }

          override fun onActivityResumed(activity: Activity) {
            // Your initialization code when an activity is resumed
            CurrentActivityHolder.activity = activity

            // Show app open ad if available
            GlobalScope.launch(Dispatchers.Main) {
              delay(300)

              if (!CurrentActivityHolder.previousActivityWasAd) {
                AdMob.getAppOpenAd()?.show()
              }
            }
          }

          override fun onActivityPaused(activity: Activity) {
            // Your initialization code when an activity is paused
            CurrentActivityHolder.previousActivityWasAd = activity is AdActivity
          }

          override fun onActivityStopped(activity: Activity) {
            // Your initialization code when an activity is stopped
          }

          override fun onActivityDestroyed(activity: Activity) {
            // Your initialization code when an activity is destroyed
          }

          override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            // Your initialization code when an activity's state is saved
          }
        },
      )
    }
  }
}
