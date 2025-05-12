plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.recipia.feature.calendar.impl"

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Modules
    api(projects.feature.calendar.api)

    // Libs
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.compose.ui.tooling.preview)

    // DI
    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)
}