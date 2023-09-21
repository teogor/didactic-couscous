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

package dev.teogor.ceres

import dagger.hilt.android.HiltAndroidApp
import dev.teogor.ceres.ads.ApplicationOpenAd
import dev.teogor.ceres.framework.core.Application
import dev.teogor.ceres.framework.core.model.ThemeBuilder
import dev.teogor.ceres.monetisation.admob.AdMob
import dev.teogor.ceres.theme.configureTheme
import javax.inject.Inject

/**
 * Android Application class for Ceres application.
 *
 * This class extends the Android [Application] class.
 */
@HiltAndroidApp
class Application : Application() {

  @Inject
  lateinit var applicationOpenAd: ApplicationOpenAd

  /**
   * The [ThemeBuilder] instance used to configure the theme for the Ceres application.
   *
   * This property initializes the theme configuration using [configureTheme].
   */
  override val themeBuilder = configureTheme()

  override fun onCreate() {
    super.onCreate()

    AdMob.setApplicationOpenAd(applicationOpenAd)
  }
}
