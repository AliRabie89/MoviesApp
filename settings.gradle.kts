

import java.net.URI

pluginManagement {
    repositories {
        google()
        jcenter()
        mavenCentral()
        gradlePluginPortal()
    }

}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { url = URI("https://jitpack.io") }
    }
}

rootProject.name = "MoviesApp"
include(":app")
 