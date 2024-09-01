plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.android)
    alias(libs.plugins.compose)
}

android {
    namespace = "com.nekivai.ui_kit"

    buildFeatures {
        compose = true
    }
}

dependencies {

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.bundles.compose)
}