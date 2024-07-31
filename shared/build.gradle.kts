plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

repositories {
    mavenCentral()
    mavenLocal()
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
        version = "0.0.1"
        ios.deploymentTarget = "16.0"
        framework {
            baseName = "MuviShared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }

    publishing {
        publications {
            create<MavenPublication>("muvishared") {
                from(components["kotlin"])

                groupId = "com.muvi"
                artifactId = "muvishared"
                version = "0.1.0"
            }
        }

        repositories {
            maven {
                url = uri("https://maven.pkg.github.com/your-username/your-repo")
                credentials {
                    username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                    password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
                }
            }
        }
    }
}

task("testClasses")

android {
    namespace = "com.muvi.muvishared"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}