
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyConstraintHandler

/**
 * Collect all the root project's multiplatform targets and add them to the BOM.
 *
 * Only published subprojects are included.
 *
 * This supports Kotlin/Multiplatform and Kotlin/JS subprojects.
 */
fun Project.collectBomConstraints() {
  val bomConstraints: DependencyConstraintHandler = dependencies.constraints
  rootProject.subprojects {
    val subproject = this

    subproject.plugins.withId("com.vanniktech.maven.publish.base") {
      bomConstraints.api(subproject)
    }

    subproject.plugins.withId("com.vanniktech.maven.publish") {
      bomConstraints.api(subproject)
    }
  }
}

private fun DependencyConstraintHandler.api(constraintNotation: Any) =
  add("api", constraintNotation)
