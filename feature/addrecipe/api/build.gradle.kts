plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.android)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "recipia.feature.add_recipe.api"
}

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.kotlinx.serialization)
}