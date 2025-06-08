plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("com.google.protobuf") version "0.9.4"
}

android {
    namespace = "com.example.datastore"
}

dependencies {

    implementation(libs.coreKtx)
    implementation(libs.appcompat)

    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.javalite)

    // DI
    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.1"
    }
    generateProtoTasks {
        all().forEach {
            it.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}