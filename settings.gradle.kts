pluginManagement {
    includeBuild("plugin")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Didactic Couscous"

// App
include(":app")

// Navigation
include(":navigation:common")
include(":navigation:core")
include(":navigation:screen")
include(":navigation:ui")

// Firebase
include(":firebase:analytics")
include(":firebase:crashlytics")
include(":firebase:remote-config")

// Backup
include(":backup:core")
include(":backup:ui")

// Data
include(":data:database")
include(":data:datastore")

// UI
include(":ui:spectrum")
include(":ui:designsystem")
include(":ui:foundation")
include(":ui:theme")
include(":ui:ui")

// Core
include(":core:network")
include(":core:notifications")
include(":core:startup")

// Framework
include(":framework:core")
include(":framework:ui")

// Screen
include(":screen:builder")
include(":screen:configurator")
