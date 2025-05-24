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
include(":feature:addrecipe:impl")
include(":feature:addrecipe:api")
include(":feature:recipedetails:impl")
include(":feature:recipedetails:api")
include(":feature:collections:api")
include(":feature:collections:impl")
include(":feature:calendar:api")
include(":feature:calendar:impl")
include(":feature:groceries:api")
include(":feature:groceries:impl")
