plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.recipia"

    defaultConfig {
        applicationId = "com.example.recipia"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    // Modules
    implementation(projects.core.network)
    implementation(projects.core.ui)
    implementation(projects.feature.mainScreen.impl)
    implementation(projects.feature.recipedetails.impl)

    // Libs
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.androidx.material3)
    implementation(libs.timber)
    implementation(libs.navigation)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.bundles.compose)

    // DI
    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)
}