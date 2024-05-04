plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.android)
}

android {
    namespace = "com.nekivai.authorization"

}

dependencies {

    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material.version3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}