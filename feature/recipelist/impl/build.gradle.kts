plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "recipia.feature.recipelist.impl"

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Modules
    implementation(projects.core.common)
    implementation(projects.core.ui)
    api(projects.feature.recipelist.api)

    // Libs
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.coil.compose)
    implementation(libs.kotlinx.serialization)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // DI
    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)

    // network
    implementation(libs.retrofit)

    // Tests
    implementation(libs.test.junit)
    implementation(libs.test.mockk)
    implementation(libs.test.coroutines.test)
    implementation(libs.test.turbine)
    testImplementation(libs.truth)
}