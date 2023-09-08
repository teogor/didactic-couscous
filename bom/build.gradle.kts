import com.vanniktech.maven.publish.SonatypeHost
import dev.teogor.ceres.gradle.plugins.CeresLibraryExtension
import dev.teogor.ceres.gradle.plugins.setIsBomModule
import dev.teogor.ceres.gradle.plugins.setModuleCoordinates

plugins {
  id("java-platform")
  id("ceres.module")
}

ceresModule {
  setModuleCoordinates(
    artifactIdPrefix = "bom",
    version = "1.0.0-alpha01",
  )

  setIsBomModule()
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
