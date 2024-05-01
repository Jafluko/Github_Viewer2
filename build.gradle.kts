import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.plugins.AppPlugin
import com.android.build.gradle.internal.plugins.LibraryPlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.7.2" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
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
    delete(rootProject.buildDir)
}