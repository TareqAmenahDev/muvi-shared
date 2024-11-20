plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        framework {
            baseName = "data"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)

            implementation(libs.kotlinx.serialization)

            libs.ktor.apply {
                implementation(core)
                implementation(serialization)
                implementation(negotiation)
                implementation(logging)
            }
        }
        androidMain.dependencies {
            implementation(libs.ktor.okhttp)

            implementation(libs.koin.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.serialization)

            libs.ktor.apply {
                implementation(core)
                implementation(serialization)
                implementation(negotiation)
                implementation(logging)
            }

            implementation("io.ktor:ktor-client-mock:2.0.0")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
        }
    }
}
task("testClasses")

android {
    namespace = "com.muvi.shared.data"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
dependencies {
    implementation(project(":shared"))
    implementation(project(":shared"))
}
