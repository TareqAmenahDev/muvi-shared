import java.io.FileInputStream
import java.util.Properties

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

val versionName = "0.0.4"
val iosDeploymentTarget = "13.0"

val githubProperties = Properties().apply {
    load(FileInputStream(rootProject.file("github.properties")))
}

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
                groupId = "com.muvi"
                artifactId = "muvishared"
                version = versionName
            }
        }

        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/TareqAmenahDev/muvi-shared")
                credentials {
                    username = githubProperties["gpr.usr"] as String? ?: System.getenv("GPR_USER")
                    password = githubProperties["gpr.key"] as String? ?: System.getenv("GPR_API_KEY")
                }
            }
        }
    }
}

task("testClasses")

tasks.register<Copy>("publishMuviSharedXCFramework") {
    dependsOn("podPublishXCFramework") // Ensure the build task runs before copyFiles

    from("build/cocoapods/publish/release") // Source directory
    into("../muvi-shared-cocoa") // Destination directory inside the project

}
