plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.recipia.core.common"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Modules
    implementation(projects.core.ui)

    implementation(libs.coreKtx)
    implementation(libs.kotlinx.serialization)
    api(libs.timber)

    // DI
    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)
}