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
    implementation(projects.feature.recipelist.impl)
    implementation(projects.feature.collections.impl)
    implementation(projects.feature.calendar.impl)
    implementation(projects.feature.groceries.impl)
    implementation(projects.feature.addrecipe.api)
    implementation(projects.feature.recipedetails.api)
    api(projects.feature.mainScreen.api)

    // Libs
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.androidx.material3)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.navigation.compose)
    debugImplementation(libs.androidx.compose.ui.tooling)
}