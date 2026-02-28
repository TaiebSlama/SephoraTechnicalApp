plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.sephora.technical_test"
    compileSdk {
        version = release(36)
    }
    signingConfigs {
        create("store") {
            storeFile =
                file("${project.rootDir}/sephora.technical.keystore.jks")
            keyAlias = "sephora"
            storePassword = "sephora@2026"
            keyPassword = "sephora@2026"
        }
        create("dev") {
            storeFile =
                file("${project.rootDir}/sephora.technical.dev.keystore.jks")
            keyAlias = "sephora"
            storePassword = "sephora@2026"
            keyPassword = "sephora@2026"
        }
    }
    defaultConfig {
        applicationId = "com.sephora.technical_test"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    flavorDimensions.add("environment")
    productFlavors {
        create("stag") {
            signingConfig = signingConfigs.getByName("dev")
            dimension = "environment"
            versionCode = 1
            versionNameSuffix = "-stag"
            versionName = "1.0.0"
            // define base url
            buildConfigField("String", "BASE_URL", "\"https://sephoraandroid.github.io/\"")
        }

        create("prod") {
            signingConfig = signingConfigs.getByName("store")
            dimension = "environment"
            versionCode = 1
            versionNameSuffix = "-prod"
            versionName = "1.0.0"
            buildConfigField("String", "BASE_URL", "\"https://sephoraandroid.github.io/\"")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.navigation)
    implementation(libs.koin.androidx.compose)
    testImplementation(libs.koin.test.junit4)
    // Ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.content.negotiation)
    // navigation
    implementation(libs.androidx.navigation.compose)
    // Splash screen
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.coil.compose)
}