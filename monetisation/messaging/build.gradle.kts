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
plugins {
  id("ceres.android.library")
  id("ceres.android.library.compose")
  id("ceres.android.library.jacoco")
  id("ceres.android.hilt")
}

android {
  namespace = "dev.teogor.ceres.monetisation.messaging"
  defaultConfig {
    consumerProguardFiles("consumer-proguard-rules.pro")
  }
}

dependencies {
  api("com.google.android.ump:user-messaging-platform:2.1.0")

  api(project(":monetisation:admob"))

  implementation("androidx.annotation:annotation:1.7.0")

  implementation(libs.androidx.compose.runtime)
  implementation(libs.startup.runtime)
}
