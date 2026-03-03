plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.recipia.core.network"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(projects.core.common)
    implementation(projects.core.ui)

    // Network
    implementation(libs.retrofit)
    implementation(libs.okHttp)
    implementation(libs.loggingInterceptor)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.kotlinx.serialization)

    // DI
    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)

    // Tests
    implementation(libs.test.junit)
    implementation(libs.test.mockk)
    implementation(libs.test.coroutines.test)
    implementation(libs.test.turbine)
    testImplementation(libs.truth)
}