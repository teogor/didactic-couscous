package dev.teogor.ceres.plugin

import java.io.File
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

open class GenerateConfigFileTask : DefaultTask() {

    @get:OutputFile
    val outputFile: File = project.file("${project.buildDir}/generated/ceres/src/main/kotlin/dev/teogor/ceres/plugin/info/MyConfig.kt")

    @TaskAction
    fun generateConfigFile() {
        val packageName = "dev.teogor.ceres.plugin.info"
        val configContent = """
            package $packageName

            object MyConfig {
                val someConfigValue = "YourValue"
            }
        """.trimIndent()
        outputFile.parentFile.mkdirs()
        outputFile.writeText(configContent)
    }
}
