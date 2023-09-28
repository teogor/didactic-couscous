## Ceres backup

| Status | Library | Gradle dependency |
| ------ | ------- | ----------------- |
| 🧪 | Ceres Backup Core | [dev.teogor.ceres:backup-core:1.0.0-alpha01](#implementation-ceres-backup-core) |
| 🧪 | Ceres Backup UI | [dev.teogor.ceres:backup-ui:1.0.0-alpha01](#implementation-ceres-backup-ui) |

When integrating Ceres libraries into your project, it's worth noting the convenience of utilizing the [Bill of Materials (BoM)](/docs/bom/versions.md). The BoM serves as a centralized mechanism for managing library versions across your project, streamlining the entire process.

One notable advantage of the BoM is that it eliminates the need for explicitly specifying library versions in your dependencies. This means you won't have to worry about manually assigning version numbers when adding Ceres libraries to your project.

Instead, when you employ the BoM, it automatically assigns the appropriate version of each library, ensuring compatibility and consistency throughout your project. This automated version management significantly reduces the chances of compatibility issues and simplifies your dependency management workflow.

By referring to the [BoM documentation](/docs/bom/versions.md), you can learn how to integrate the BoM into your project and benefit from this hassle-free approach to library version management. It's a powerful tool for staying up-to-date with the latest Ceres library versions and seamlessly integrating them into your projects.


### Implementation Ceres Backup Core

To use Ceres Backup Core in your Android project, add the following dependency to your module-level Gradle file (usually `app/build.gradle.kts`):

```kotlin
implementation("dev.teogor.ceres:backup-core:1.0.0-alpha01")
```

#### Gradle Dependency

- **Group ID:** `dev.teogor.ceres`
- **Artifact ID:** `backup-core`
- **Version:** `1.0.0-alpha01`*

* not required when using [BoM](/docs/bom/versions.md)

### Implementation Ceres Backup UI

To use Ceres Backup UI in your Android project, add the following dependency to your module-level Gradle file (usually `app/build.gradle.kts`):

```kotlin
implementation("dev.teogor.ceres:backup-ui:1.0.0-alpha01")
```

#### Gradle Dependency

- **Group ID:** `dev.teogor.ceres`
- **Artifact ID:** `backup-ui`
- **Version:** `1.0.0-alpha01`*

* not required when using [BoM](/docs/bom/versions.md)


