plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.recipia.core.common"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.coreKtx)
    api(libs.timber)

    // DI
    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)
}