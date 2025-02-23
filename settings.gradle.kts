@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}


rootProject.name = "sweetsavvy"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":batching-app")
include(":libs:core")
include(":libs:authentication")