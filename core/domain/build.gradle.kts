plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.recipia.core.domain"
}

dependencies {
    // Modules
    implementation(projects.core.common)
    implementation(projects.core.ui)
    implementation(projects.core.network)

    // DI
    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)

    // Libs
    implementation(libs.kotlinx.serialization)
}