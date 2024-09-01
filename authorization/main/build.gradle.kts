plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.android)
}

android {
    namespace = "com.nekivai.main"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material.version3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}