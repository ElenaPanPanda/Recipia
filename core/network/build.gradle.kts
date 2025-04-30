plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "recipia.core.network"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.appcompat)

    // Network
    implementation(libs.retrofit)
    implementation(libs.okHttp)
    implementation(libs.loggingInterceptor)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.kotlinx.serialization)

    // DI
    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)
}