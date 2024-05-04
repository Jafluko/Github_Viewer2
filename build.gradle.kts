import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.plugins.AppPlugin
import com.android.build.gradle.internal.plugins.LibraryPlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.android) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    plugins.matching { it is AppPlugin || it is LibraryPlugin }.whenPluginAdded {
        configure<BaseExtension> {
            compileSdkVersion(34)

            defaultConfig {
                minSdk = 27
                targetSdk = 34
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            plugins.matching { it is KotlinAndroidProjectExtension }.whenPluginAdded{
                configure<KotlinAndroidProjectExtension> {
                    jvmToolchain(17)
                    sourceSets.all {
                        languageSettings {
                            languageVersion = "2.0"
                        }
                    }
                }
            }
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.projectDir)
}