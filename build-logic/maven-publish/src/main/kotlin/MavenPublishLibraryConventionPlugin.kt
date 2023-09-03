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

import dev.teogor.ceres.Version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPom

class MavenPublishLibraryConventionPlugin : Plugin<Project> {

  val groupId = "dev.teogor.ceres"
  var artifactId = "not-set"
  val version = Version.name

  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply("com.vanniktech.maven.publish")
      val modules = this.path
        .split(":")
        .filter { it.isNotEmpty() && it != "ceres" && it != "core" }

      artifactId = modules.joinToString(separator = "-")
      group = groupId
      version = this@MavenPublishLibraryConventionPlugin.version
    }
  }

  private fun MavenPom.info() {
    name.set("Ceres")
    description.set(
      "Modern Android development with Hilt, Coroutines, Flow, JetPack(ViewModel) based on MVVM architecture.",
    )
    inceptionYear.set("2022")
    url.set("https://github.com/teogor/ceres/")
  }

  private fun MavenPom.license() {
    licenses {
      license {
        name.set("The Apache License, Version 2.0")
        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
        distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
      }
    }
  }

  private fun MavenPom.developers() {
    developers {
      developer {
        id.set("teogor")
        name.set("Teodor Grigor")
        url.set("https://teogor.dev")
        roles.set(listOf("Code Owner", "Developer", "Designer", "Maintainer"))
        timezone.set("UTC+2")
        organization.set("Teogor")
        organizationUrl.set("https://github.com/teogor")
      }
    }
  }

  private fun MavenPom.scm() {
    scm {
      url.set("https://github.com/teogor/ceres/")
      connection.set("scm:git:git://github.com/teogor/ceres.git")
      developerConnection.set("scm:git:ssh://git@github.com/teogor/ceres.git")
    }
  }

  fun applyToMaven(pom: MavenPom) {
    pom.apply {
      info()
      license()
      developers()
      scm()
    }
  }
}
