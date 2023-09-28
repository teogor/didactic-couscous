## Ceres data

| Status | Library | Gradle dependency |
| ------ | ------- | ----------------- |
| 🧪 | Ceres Data Compose | [dev.teogor.ceres:data-compose:1.0.0-alpha01](#implementation-ceres-data-compose) |
| 🧪 | Ceres Data Database | [dev.teogor.ceres:data-database:1.0.0-alpha01](#implementation-ceres-data-database) |
| 🧪 | Ceres Data Datastore | [dev.teogor.ceres:data-datastore:1.0.0-alpha01](#implementation-ceres-data-datastore) |

When integrating Ceres libraries into your project, it's worth noting the convenience of utilizing the [Bill of Materials (BoM)](/docs/bom/versions.md). The BoM serves as a centralized mechanism for managing library versions across your project, streamlining the entire process.

One notable advantage of the BoM is that it eliminates the need for explicitly specifying library versions in your dependencies. This means you won't have to worry about manually assigning version numbers when adding Ceres libraries to your project.

Instead, when you employ the BoM, it automatically assigns the appropriate version of each library, ensuring compatibility and consistency throughout your project. This automated version management significantly reduces the chances of compatibility issues and simplifies your dependency management workflow.

By referring to the [BoM documentation](/docs/bom/versions.md), you can learn how to integrate the BoM into your project and benefit from this hassle-free approach to library version management. It's a powerful tool for staying up-to-date with the latest Ceres library versions and seamlessly integrating them into your projects.


### Implementation Ceres Data Compose

To use Ceres Data Compose in your Android project, add the following dependency to your module-level Gradle file (usually `app/build.gradle.kts`):

```kotlin
implementation("dev.teogor.ceres:data-compose:1.0.0-alpha01")
```

#### Gradle Dependency

- **Group ID:** `dev.teogor.ceres`
- **Artifact ID:** `data-compose`
- **Version:** `1.0.0-alpha01`*

* not required when using [BoM](/docs/bom/versions.md)

### Implementation Ceres Data Database

To use Ceres Data Database in your Android project, add the following dependency to your module-level Gradle file (usually `app/build.gradle.kts`):

```kotlin
implementation("dev.teogor.ceres:data-database:1.0.0-alpha01")
```

#### Gradle Dependency

- **Group ID:** `dev.teogor.ceres`
- **Artifact ID:** `data-database`
- **Version:** `1.0.0-alpha01`*

* not required when using [BoM](/docs/bom/versions.md)

### Implementation Ceres Data Datastore

To use Ceres Data Datastore in your Android project, add the following dependency to your module-level Gradle file (usually `app/build.gradle.kts`):

```kotlin
implementation("dev.teogor.ceres:data-datastore:1.0.0-alpha01")
```

#### Gradle Dependency

- **Group ID:** `dev.teogor.ceres`
- **Artifact ID:** `data-datastore`
- **Version:** `1.0.0-alpha01`*

* not required when using [BoM](/docs/bom/versions.md)


