## BoM Versions

The BOM (Bill of Materials) is the central hub for managing library versions within the Ceres project.
It enables you to effortlessly keep track of the latest versions of key components and dependencies.

```kt
dependencies {
  // Import the BoM for the Ceres platform
  implementation(platform("dev.teogor.ceres:bom:1.0.0-alpha01"))

  // Declare the dependencies for the desired Ceres products without specifying versions
  // For example, declare the dependencies for Ceres Core Runtime and Ceres Core Network
  implementation("dev.teogor.ceres:core-network")
}
```

Below is a list of the latest versions of the BOM:

| Version | Link | Release Date |
| ------- | ---- | ------------ |
| 1.0.0-alpha01 | [Info 🔗](/docs/bom/1.0.0-alpha01/bom-version-1.0.0-alpha01.md) | 28 Sept 2023 |

The BOM ensures that all libraries and components across your project are in sync, reducing compatibility issues
and simplifying the dependency management process.
By using the BOM, you can stay up-to-date with the most recent advancements in the Ceres ecosystem.

To integrate the BOM into your project, refer to the documentation for step-by-step instructions.

For more detailed information and updates, please check the official Ceres documentation.

