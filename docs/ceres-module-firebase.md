## Ceres firebase

| Status | Library | Gradle dependency |
| ------ | ------- | ----------------- |
| 🧪 | Ceres Firebase Analytics | [dev.teogor.ceres:firebase-analytics:1.0.0-alpha01](#implementation-ceres-firebase-analytics) |
| 🧪 | Ceres Firebase Crashlytics | [dev.teogor.ceres:firebase-crashlytics:1.0.0-alpha01](#implementation-ceres-firebase-crashlytics) |
| 🧪 | Ceres Firebase Remote-Config | [dev.teogor.ceres:firebase-remote-config:1.0.0-alpha01](#implementation-ceres-firebase-remote-config) |

When integrating Ceres libraries into your project, it's worth noting the convenience of utilizing the [Bill of Materials (BoM)](docs/bom/versions.md). The BoM serves as a centralized mechanism for managing library versions across your project, streamlining the entire process.

One notable advantage of the BoM is that it eliminates the need for explicitly specifying library versions in your dependencies. This means you won't have to worry about manually assigning version numbers when adding Ceres libraries to your project.

Instead, when you employ the BoM, it automatically assigns the appropriate version of each library, ensuring compatibility and consistency throughout your project. This automated version management significantly reduces the chances of compatibility issues and simplifies your dependency management workflow.

By referring to the [BoM documentation](docs/bom/versions.md), you can learn how to integrate the BoM into your project and benefit from this hassle-free approach to library version management. It's a powerful tool for staying up-to-date with the latest Ceres library versions and seamlessly integrating them into your projects.

### Implementation Ceres Firebase Analytics

```kotlin
implementation(\"dev.teogor.ceres:firebase-analytics:1.0.0-alpha01\")
```
### Implementation Ceres Firebase Crashlytics

```kotlin
implementation(\"dev.teogor.ceres:firebase-crashlytics:1.0.0-alpha01\")
```
### Implementation Ceres Firebase Remote-Config

```kotlin
implementation(\"dev.teogor.ceres:firebase-remote-config:1.0.0-alpha01\")
```

