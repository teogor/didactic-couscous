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

package dev.teogor.ceres.core.runtime

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import dev.teogor.ceres.core.android.config.BuildConfig
import dev.teogor.ceres.core.foundation.packageManagerUtils
import dev.teogor.ceres.core.startup.ApplicationContextProvider
import java.time.LocalDateTime
import java.time.ZoneId

@Suppress("MemberVisibilityCanBePrivate", "unused")
object AppMetadataManager {
  val packageName: String
    get() = ApplicationContextProvider.context.packageName

  val sanitizedPackageName: String
    get() {
      var sanitizedPackageName = when (flavor) {
        "demo" -> packageName.removeSuffix(".demo")
        else -> packageName
      }
      sanitizedPackageName = if (debug) {
        sanitizedPackageName.removeSuffix(".debug")
      } else {
        sanitizedPackageName
      }
      sanitizedPackageName = when (flavor) {
        "demo" -> sanitizedPackageName.removeSuffix(".demo")
        else -> sanitizedPackageName
      }
      return sanitizedPackageName
    }

  val packageManager: PackageManager
    get() = ApplicationContextProvider.context.packageManagerUtils().packageManager

  val packageInfo: PackageInfo
    get() = ApplicationContextProvider.context.packageManagerUtils().packageInfo

  val versionName: String
    get() = ApplicationContextProvider.context.packageManagerUtils().versionName

  val versionCode: Long
    get() = ApplicationContextProvider.context.packageManagerUtils().versionCode

  val zoneOffset = ZoneId.systemDefault().rules.getOffset(LocalDateTime.now())
  val buildDateTime = LocalDateTime.ofEpochSecond(
    BuildConfig.BUILD_DATE_TIME.toLong(),
    0,
    zoneOffset,
  )

  val buildType = BuildConfig.BUILD_TYPE
  val flavor = "demo" // todo BuildConfig.FLAVOR
  val debug = BuildConfig.DEBUG
  val gitHash = BuildConfig.GIT_HASH
  val ceresFrameworkVersion = BuildConfig.CERES_FRAMEWORK_VERSION

  val isDebuggable: Boolean
    get() = 0 != ApplicationContextProvider.context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE

  val apkSignature: String
    get() {
      val packageSignatures = ApplicationContextProvider.context.packageManagerUtils().packageSignatures
      val signatures = packageSignatures.apkContentsSigners.joinToString(", ") { signature ->
        signature.toCharsString()
      }
      return signatures
    }
}
