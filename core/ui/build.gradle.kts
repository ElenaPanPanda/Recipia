plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.recipia.core.ui"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.androidx.material3)
    implementation(libs.bundles.compose)
}