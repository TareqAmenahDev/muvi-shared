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

val versionName = "0.0.1"
val iosDeploymentTarget = "16.0"

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
        version = versionName
        ios.deploymentTarget = iosDeploymentTarget
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
                version = versionName
            }
        }

//        repositories {
//            maven {
//                url = uri("https://maven.pkg.github.com/your-username/your-repo")
//                credentials {
//                    username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
//                    password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
//                }
//            }
//        }
    }
}

task("testClasses")

tasks.register<Copy>("publishMuviSharedXCFramework") {
    dependsOn("podPublishXCFramework") // Ensure the build task runs before copyFiles

    from("build/cocoapods/publish/release") // Source directory
    into("../muvi-shared-cocoa") // Destination directory inside the project

}

android {
    namespace = "com.muvi.muvishared"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}