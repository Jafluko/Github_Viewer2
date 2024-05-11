plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(libs.material.version3)

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    implementation(libs.androidx.compose.tooling.preview)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.material)
}