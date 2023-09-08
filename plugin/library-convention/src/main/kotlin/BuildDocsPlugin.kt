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
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class BuildDocsPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    val modulesByComponent = mutableMapOf<String, MutableList<Project>>()
    val modulesCeres = project.allprojects.filter {
      it.path.count { char -> char == ':' } == 2
    }
    modulesCeres.forEach { module ->
      val pathComponents = module.path.split(":")
      if (pathComponents.size >= 2) {
        val component = pathComponents[1]
        val componentModules = modulesByComponent.getOrDefault(component, mutableListOf())
        componentModules.add(module)
        modulesByComponent[component] = componentModules
      }
    }
    project.tasks.create("createCeresDocs") {
      modulesByComponent.forEach { (component, componentModules) ->
        val rootModuleName = component.replaceFirstChar { it.titlecase() }

        // Create and save a file in the root project directory
        val fileName = "module-$component.md" // Replace with your desired file name

        val rootProjectDir = project.rootDir
        val docsDir = File(rootProjectDir, "docs")
        docsDir.mkdirs()

        val outputFile = File(docsDir, fileName)
        outputFile.writeText(generateMarkdownContent(componentModules, rootModuleName))
      }
    }
  }

  private fun generateMarkdownContent(componentModules: List<Project>, rootModuleName: String): String {
    val markdownBuilder = StringBuilder()
    markdownBuilder.appendLine("# $rootModuleName Modules")
    markdownBuilder.appendLine("")
    componentModules.forEach { module ->
      val moduleNameCapitalized = capitalizeAndReplace(module.name)
      if (module.name.contains("test")) {
        markdownBuilder.appendLine("- $moduleNameCapitalized Module (:rotating_light:) - [View Source](..${module.path.replace(":", "/")})")
      } else {
        markdownBuilder.appendLine("- $moduleNameCapitalized Module - [View Source](..${module.path.replace(":", "/")})")
      }
    }
    return markdownBuilder.toString()
  }

  private fun capitalizeAndReplace(input: String): String {
    val words = input.split("-")
    val capitalizedWords = words.map { it.replaceFirstChar { char -> char.titlecase() } }
    return capitalizedWords.joinToString(" ")
  }

}
