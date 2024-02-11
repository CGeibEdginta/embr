rootProject.name = "example-perspective-component"

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)

    repositories {
        maven(url = "https://nexus.inductiveautomation.com/repository/public")
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from(files("../../gradle/libs.versions.toml"))
        }
    }

}

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven(url = "https://nexus.inductiveautomation.com/repository/public/")
        maven(url = "https://nexus.inductiveautomation.com/repository/inductiveautomation-thirdparty/")
        maven(url = "https://nexus.inductiveautomation.com/repository/inductiveautomation-releases/")
        maven(url = "https://nexus.inductiveautomation.com/repository/inductiveautomation-snapshots/")
    }
    plugins {
        id("io.ia.sdk.modl") version "0.1.1"
    }
}

include(":common")
include(":gateway")
include(":designer")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
