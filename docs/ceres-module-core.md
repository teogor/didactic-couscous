## Ceres core

| Status | Library | Gradle dependency |
| ------ | ------- | ----------------- |
| 🧪 | Ceres Core Network | [dev.teogor.ceres:core-network:1.0.0-alpha01](#implementation-ceres-core-network) |
| 🧪 | Ceres Core Notifications | [dev.teogor.ceres:core-notifications:1.0.0-alpha01](#implementation-ceres-core-notifications) |
| 🧪 | Ceres Core Runtime | [dev.teogor.ceres:core-runtime:1.0.0-alpha01](#implementation-ceres-core-runtime) |
| 🧪 | Ceres Core Startup | [dev.teogor.ceres:core-startup:1.0.0-alpha01](#implementation-ceres-core-startup) |

When integrating Ceres libraries into your project, it's worth noting the convenience of utilizing the [Bill of Materials (BoM)](docs/bom/versions.md). The BoM serves as a centralized mechanism for managing library versions across your project, streamlining the entire process.

One notable advantage of the BoM is that it eliminates the need for explicitly specifying library versions in your dependencies. This means you won't have to worry about manually assigning version numbers when adding Ceres libraries to your project.

Instead, when you employ the BoM, it automatically assigns the appropriate version of each library, ensuring compatibility and consistency throughout your project. This automated version management significantly reduces the chances of compatibility issues and simplifies your dependency management workflow.

By referring to the [BoM documentation](docs/bom/versions.md), you can learn how to integrate the BoM into your project and benefit from this hassle-free approach to library version management. It's a powerful tool for staying up-to-date with the latest Ceres library versions and seamlessly integrating them into your projects.

### Implementation Ceres Core Network

```kotlin
implementation(\"dev.teogor.ceres:core-network:1.0.0-alpha01\")
```
### Implementation Ceres Core Notifications

```kotlin
implementation(\"dev.teogor.ceres:core-notifications:1.0.0-alpha01\")
```
### Implementation Ceres Core Runtime

```kotlin
implementation(\"dev.teogor.ceres:core-runtime:1.0.0-alpha01\")
```
### Implementation Ceres Core Startup

```kotlin
implementation(\"dev.teogor.ceres:core-startup:1.0.0-alpha01\")
```

