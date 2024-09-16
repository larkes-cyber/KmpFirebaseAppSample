plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
}

kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    androidTarget()

    jvmToolchain(17)

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.viewmodel)
            implementation(libs.ktor.client.core)
            implementation(libs.firebase.auth)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.firebase.database)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.larkes.firebasecryptoportfoliosample"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}