import java.text.SimpleDateFormat
import java.util.*

/**
 * Retrieves a value from the local properties
 */
val getLocalProps: Properties = Properties().apply {
    load(File(project.rootDir, "local.properties").inputStream())
}

/**
 * Helper method to retrieve a value either from a local property, or via the globally defined properties
 */
val getLocalOrGlobalProperty: (String) -> String? = { key ->
    val localValue = getLocalProps.getProperty(key)
    if (localValue.isNullOrBlank()) {
        val globalValue = project.findProperty(key)
        globalValue?.toString()
    } else {
        localValue
    }
}

/**
 * Retrieves a value from the properties or falls back to the default
 */
val getPropertyOrDefault: (String, String) -> String = { key, default ->
    val projectValue = try {
        project.property(key)?.toString()
    } catch (ex: Exception) {
        null
    }

    if (!projectValue.isNullOrBlank()) {
        projectValue
    } else {
        getLocalOrGlobalProperty(key) ?: default
    }
}

/**
 * Used to sign the app in release mode.
 */
val getSigningFile: () -> String? = {
    getLocalOrGlobalProperty("openSource.signing.file")
}

/**
 * Retrieves the current git hash.
 */
val gitHash: () -> String = {
    try {
        val outstream = ByteArrayOutputStream()
        project.exec {
            commandLine("git", "rev-parse", "--short", "HEAD")
            standardOutput = outstream
        }
        outstream.toString().trim()
    } catch (ex: Exception) {
        project.logger.warn("The build was not able to resolve the git branch, this project seems to be not within a git project")
        ""
    }
}

/**
 * Retrieves the current git branch.
 */
val gitBranch: () -> String = {
    try {
        val outstream = ByteArrayOutputStream()
        project.exec {
            commandLine("git", "name-rev", "--name-only", "HEAD")
            standardOutput = outstream
        }
        outstream.toString().trim()
    } catch (ex: Exception) {
        project.logger.warn("The build was not able to resolve the git branch, this project seems to be not within a git project")
        ""
    }
}

/**
 * Retrieves the current timestamp during the build.
 */
val buildDate: () -> String = {
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(Date())
}

/**
 * Checks if this build is from an IDE.
 */
val isIdeBuild: () -> Boolean = {
    project.properties["android.injected.invoked.from.ide"] == "true"
}
