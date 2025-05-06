pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Recipia"

include(":app")
include(":core:common")
include(":core:navigation")
include(":core:network")
include(":core:ui")
include(":feature:recipe_list:recipe_list_impl")
include(":feature:recipe_list:recipe_list_api")
include(":feature:main_screen_impl")
include(":feature:main_screen:api")
include(":feature:main_screen:impl")
include(":feature:add_recipe:impl")
include(":feature:add_recipe:api")
