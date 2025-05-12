plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.android)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.recipia.feature.collections.api"
}

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.kotlinx.serialization)
}