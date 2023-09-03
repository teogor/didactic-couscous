plugins {
  id("ceres.android.feature")
  id("ceres.android.library.compose")
  id("ceres.android.library.jacoco")
  id("ceres.android.hilt")
  id("kotlinx-serialization")
  id("ceres.maven.publish.library")
}

android {
  namespace = "dev.teogor.ceres"
  defaultConfig {
    consumerProguardFiles("consumer-proguard-rules.pro")
  }
}

dependencies {
}