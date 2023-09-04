/*
 * Copyright 2023 teogor (Teodor Grigor)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.teogor.ceres.framework.core

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.metrics.performance.JankStats
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.teogor.ceres.core.network.NetworkMonitor
import dev.teogor.ceres.data.compose.rememberPreference
import dev.teogor.ceres.data.datastore.defaults.AppTheme
import dev.teogor.ceres.data.datastore.defaults.ceresPreferences
import dev.teogor.ceres.firebase.analytics.AnalyticsHelper
import dev.teogor.ceres.firebase.analytics.LocalAnalyticsHelper
import dev.teogor.ceres.firebase.crashlytics.CrashInfoLegacy
import dev.teogor.ceres.firebase.crashlytics.CrashlyticsHelper
import dev.teogor.ceres.firebase.crashlytics.LocalCrashlyticsHelper
import dev.teogor.ceres.framework.core.app.BaseActions
import dev.teogor.ceres.framework.core.app.CeresApp
import dev.teogor.ceres.framework.core.app.CeresAppState
import dev.teogor.ceres.navigation.core.LocalNavigationParameters
import dev.teogor.ceres.navigation.core.NavigationParameters
import dev.teogor.ceres.navigation.core.ScreenRoute
import dev.teogor.ceres.navigation.core.menu.TopLevelDestination
import dev.teogor.ceres.ui.foundation.config.FeedbackConfig
import dev.teogor.ceres.ui.foundation.window.WindowPreferencesManager
import dev.teogor.ceres.ui.theme.core.Theme
import javax.inject.Inject
import kotlin.system.exitProcess

open class Activity : ComponentActivity() {

  private var navigationParameters by mutableStateOf(NavigationParameters())

  /**
   * API
   */
  @Inject
  lateinit var networkMonitor: NetworkMonitor

  /**
   * Lazily inject [JankStats], which is used to track jank throughout the app.
   */
  @Inject
  lateinit var lazyStats: dagger.Lazy<JankStats>

  @Inject
  lateinit var analyticsHelper: AnalyticsHelper

  @Inject
  lateinit var crashlyticsHelper: CrashlyticsHelper

  open val topLevelDestinations: List<TopLevelDestination> = emptyList()

  open fun handleUriVariants(uri: Uri): ScreenRoute? {
    return null
  }

  @Composable
  open fun Content(
    windowSizeClass: WindowSizeClass,
    ceresAppState: CeresAppState,
    baseActions: BaseActions,
    padding: PaddingValues,
  ) {
    // This method is implemented in a subclass or another file
    // Please refer to the specific implementation for details
  }

  @Composable
  open fun MenuHeader() = Unit

  // keep the same structure as Menu Header
  @Suppress("FunctionName")
  open fun MenuSheet(menuScope: MenuScope) = Unit

  /**
   *
   */
  @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    val splashScreen = installSplashScreen()
    super.onCreate(savedInstanceState)

    // Turn off the decor fitting system windows, which allows us to handle insets,
    // including IME animations
    WindowPreferencesManager().applyEdgeToEdgePreference(window)
    WindowCompat.setDecorFitsSystemWindows(window, false)

    handleSplashScreen(splashScreen)

    // todo EHF - Error Handler Feature
    Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
      val threadName = thread.name
      val threadId = thread.id

      // todo
      val intent = Intent(this, this::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        putExtra("hasCrashed", true)
        putExtra("threadName", threadName)
        putExtra("threadId", threadId)
        putExtra("throwable", throwable)
      }
      startActivity(intent)

      // Terminate the current process to ensure the app restarts
      android.os.Process.killProcess(android.os.Process.myPid())
      exitProcess(10)
    }

    // todo EHF - Error Handler Feature
    var hasCrashed = intent.getBooleanExtra("hasCrashed", false)
    val crashInfoLegacy = if (!hasCrashed) {
      null
    } else {
      val threadName = intent.getStringExtra("threadName") ?: ""
      val threadId = intent.getLongExtra("threadId", 0L)
      val throwable = intent.getSerializableExtra("throwable") as Throwable

      CrashInfoLegacy(threadName, threadId, throwable)
    }

    setContent {
      // todo systemUiController
      val systemUiController = rememberSystemUiController()
      val darkTheme = isSystemInDarkTheme()

      val ceresPreferences = remember {
        ceresPreferences()
      }

      val appTheme = rememberPreference(
        ceresPreferences.getAppThemeFlow(),
        ceresPreferences.appTheme,
      )

      val justBlackTheme = rememberPreference(
        ceresPreferences.getJustBlackThemeFlow(),
        ceresPreferences.justBlack,
      )

      val disableDynamicTheming = rememberPreference(
        ceresPreferences.getDisableDynamicThemingFlow(),
        ceresPreferences.disableDynamicTheming,
      )

      val disableAndroidTheme = rememberPreference(
        ceresPreferences.getDisableAndroidThemeFlow(),
        ceresPreferences.disableAndroidTheme,
      )

      val themeSeed = rememberPreference(
        ceresPreferences.getThemeSeedFlow(),
        ceresPreferences.themeSeed,
      )

      val disableAudioFeedback = rememberPreference(
        ceresPreferences.getDisableAudioFeedbackFlow(),
        ceresPreferences.disableAudioFeedback,
      )

      val disableVibrationFeedback = rememberPreference(
        ceresPreferences.getDisableVibrationFeedbackFlow(),
        ceresPreferences.disableVibrationFeedback,
      )

      LaunchedEffect(disableAudioFeedback, disableVibrationFeedback) {
        FeedbackConfig.disableVibrationFeedback = disableVibrationFeedback
        FeedbackConfig.disableAudioFeedback = disableAudioFeedback
      }

      val darkThemeActivated = remember(appTheme, justBlackTheme, darkTheme) {
        when (appTheme) {
          AppTheme.FollowSystem -> {
            darkTheme
          }

          AppTheme.ClearlyWhite -> {
            false
          }

          AppTheme.KindaDark -> {
            true
          }

          else -> {
            false
          }
        }
      }

      // Update the dark content of the system bars to match the theme
      systemUiController.systemBarsDarkContentEnabled = !darkThemeActivated

      Theme(
        darkTheme = darkThemeActivated,
        androidTheme = disableAndroidTheme,
        disableDynamicTheming = disableDynamicTheming,
        themeSeed = themeSeed,
      ) {
        CompositionLocalProvider(
          LocalNavigationParameters provides navigationParameters,
          LocalAnalyticsHelper provides analyticsHelper,
          LocalCrashlyticsHelper provides crashlyticsHelper,
        ) {
          // todo EHF - Error Handler Feature
          val hasCrashedRem = remember(hasCrashed) {
            hasCrashed
          }
          // todo EHF - Error Handler Feature
          if (hasCrashedRem && crashInfoLegacy != null) {
            val crashInfo = remember(crashInfoLegacy) {
              crashInfoLegacy
            }
            crashInfo.let {
              // todo EHF - Error Handler Feature
              crashlyticsHelper.SetCrash(it)
              navigateTo(
                object : ScreenRoute {
                  override val route: String = "error_route"
                },
              )
            }
            hasCrashed = false
          } else if (hasCrashedRem) {
            // todo EHF - Error Handler Feature
            hasCrashed = false
          }

          CeresApp(
            windowSizeClass = calculateWindowSizeClass(this),
            networkMonitor = networkMonitor,
            topLevelDestinations = topLevelDestinations,
            menuSheetContent = {
              MenuSheet(this)
            },
            headerContent = {
              MenuHeader()
            },
          ) { windowSizeClass, ceresAppState, baseActions, padding ->
            Content(
              windowSizeClass = windowSizeClass,
              ceresAppState = ceresAppState,
              baseActions = baseActions,
              padding = padding,
            )
          }
        }
      }
    }

    handleIntent(intent)
  }

  private fun handleSplashScreen(splashScreen: SplashScreen) {
    // Keep the splash screen on-screen until the UI state is loaded. This condition is
    // evaluated each time the app needs to be redrawn so it should be fast to avoid blocking
    // the UI.
    splashScreen.setKeepOnScreenCondition {
      setKeepOnScreenCondition()
    }
  }

  open fun setKeepOnScreenCondition(): Boolean = false

  override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    handleIntent(intent)
  }

  private fun handleIntent(intent: Intent?) {
    intent?.data?.let {
      navigationParameters.uri = it
    }
  }

  fun showMenu(isVisible: Boolean) {
    if (isVisible) {
      navigationParameters.showMenu()
    } else {
      navigationParameters.hideMenu()
    }
  }

  fun navigateTo(screenRoute: ScreenRoute) {
    navigationParameters.screenRoute = screenRoute
  }

  override fun onResume() {
    super.onResume()
    lazyStats.get().isTrackingEnabled = true
  }

  override fun onPause() {
    super.onPause()
    lazyStats.get().isTrackingEnabled = false
  }
}
