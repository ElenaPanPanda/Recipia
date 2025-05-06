plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "recipia.feature.main_screen.impl"

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Modules
    implementation(projects.core.ui)
    implementation(projects.feature.recipeList.recipeListImpl)
    implementation(projects.feature.addRecipe.impl)
    api(projects.feature.mainScreen.api)

    // Libs
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.androidx.material3)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.navigation.compose)
}