package dev.teogor.ceres.monetisation.messaging

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import java.util.concurrent.atomic.AtomicBoolean

class MessagingManager {
  companion object {
    private var isMobileAdsInitializeCalled = AtomicBoolean(false)

    fun initialize(context: Context) {
      if (context is Application) {
        context.registerActivityLifecycleCallbacks(
          object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
              ConsentManager.activity = activity
              if(!isMobileAdsInitializeCalled.get()) {
                ConsentManager.initialiseConsentForm(
                  activity = activity,
                )
                isMobileAdsInitializeCalled.set(true)
              }
            }

            override fun onActivityStarted(activity: Activity) {
              // Your initialization code when an activity is started
              ConsentManager.activity = activity
            }

            override fun onActivityResumed(activity: Activity) {
              // Your initialization code when an activity is resumed
              ConsentManager.activity = activity
            }

            override fun onActivityPaused(activity: Activity) {
              // Your initialization code when an activity is paused
            }

            override fun onActivityStopped(activity: Activity) {
              // Your initialization code when an activity is stopped
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
              // Your initialization code when an activity's state is saved
            }

            override fun onActivityDestroyed(activity: Activity) {
              // Your initialization code when an activity is destroyed
            }
          }
        )
      }
    }
  }
}

