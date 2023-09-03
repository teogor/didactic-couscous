plugins {
  id("ceres.android.feature")
  id("ceres.android.library.compose")
  id("ceres.android.library.jacoco")
  id("ceres.android.hilt")
  id("kotlinx-serialization")
}

android {
  namespace = "dev.teogor.ceres"
  defaultConfig {
    consumerProguardFiles("consumer-proguard-rules.pro")
  }
}
