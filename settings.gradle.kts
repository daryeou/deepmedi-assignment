pluginManagement {
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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "deepmedi"
include(":app")
include(":core:designsystem")
include(":core:data")
include(":core:domain")
include(":core:network")
include(":core:model")
include(":core:ui")
include(":feature:deepmedihome")
include(":feature:deepmediresult")