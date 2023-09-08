import com.vanniktech.maven.publish.SonatypeHost
import dev.teogor.ceres.gradle.plugins.CeresLibraryExtension

plugins {
  id("java-platform")
  id("ceres.module")
}

ceresModule {
  artifactIdPrefix = "bom"
  version = "1.0.0-alpha01"
  isBomModule = false
}

afterEvaluate {
  collectBomConstraints()

  val ceresLibrary = project.extensions.getByType(CeresLibraryExtension::class.java)
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
