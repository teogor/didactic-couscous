import com.vanniktech.maven.publish.SonatypeHost
import dev.teogor.ceres.gradle.plugins.CeresLibraryExtension

plugins {
  id("ceres.module")
}

ceresModule {
  artifactIdPrefix = "framework"
  version = "1.0.0-alpha01"
}

subprojects {
  afterEvaluate {
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
}
