import com.vanniktech.maven.publish.SonatypeHost
import dev.teogor.ceres.gradle.plugins.CeresLibraryExtension

plugins {
  id("com.vanniktech.maven.publish")
  id("java-platform")
}

afterEvaluate {
  collectBomConstraints()

  val ceresLibrary = CeresLibraryExtension().apply {
    artifactId = "bom"
    version = "2023.09.08"
  }
  mavenPublishing {
    publishToMavenCentral(SonatypeHost.S01)
    signAllPublications()

    @Suppress("UnstableApiUsage")
    pom {
      coordinates(
        groupId = ceresLibrary.groupId,
        artifactId = ceresLibrary.artifactId!!,
        version = ceresLibrary.version!!,
      )
      ceresLibrary.applyToMaven(this)
    }
  }
}
