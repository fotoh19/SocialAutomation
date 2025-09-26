import org.gradle.kotlin.dsl.androidTestImplementation
import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.project.socialautomation"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.project.socialautomation"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.firebase.auth)
//    implementation(libs.twitter.core)
//    implementation(libs.twitter)
//    implementation(libs.twitter4j.core)

    implementation(platform(libs.firebase.bom))

    // Firebase Auth
    implementation(libs.google.firebase.auth)

    // Encrypted SharedPreferences (لتخزين التوكن بأمان)
    implementation (libs.androidx.security.crypto)

    // ViewModel + Lifecycle
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)

    // Coroutines
    implementation (libs.kotlinx.coroutines.android)

    // UI
    implementation (libs.material)
    implementation (libs.androidx.appcompat.v170)
    implementation (libs.androidx.constraintlayout.v214)

    // Test
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.junit.v121)
    androidTestImplementation (libs.androidx.espresso.core.v361)
}