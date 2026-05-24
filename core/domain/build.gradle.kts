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
    implementation(projects.core.datastore)
    implementation(projects.core.network)
    implementation(projects.core.ui)

    // DI
    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)

    // Libs
    implementation(libs.kotlinx.serialization)

    // Tests
    implementation(libs.test.junit)
    implementation(libs.test.mockk)
    implementation(libs.test.coroutines.test)
    implementation(libs.test.turbine)
    testImplementation(libs.truth)
}