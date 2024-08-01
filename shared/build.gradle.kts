plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
//    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val versionName = "0.0.3"
val iosDeploymentTarget = "13.0"

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()

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
            implementation (libs.kotlinx.coroutines.core)
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }

    publishing {
        publications {
            create<MavenPublication>("muvishared") {
                from(components["kotlin"])
                groupId = "com.muvi.shared"
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
//
//android {
//    namespace = "com.muvi.muvishared"
//    compileSdk = 34
//    defaultConfig {
//        minSdk = 24
//    }
//}