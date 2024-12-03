plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "2.0.21"
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.demoapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.demoapp"
        minSdk = 23
        targetSdk = 35
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.ui.desktop)
    implementation(libs.androidx.room.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.core.splashscreen.v101)
    implementation(libs.core.splashscreen)
    implementation ("androidx.core:core-splashscreen:1.0.1")
    val fragment_version = "1.8.5"
    implementation("androidx.fragment:fragment-ktx:$fragment_version")
    implementation (libs.lottie)
    implementation ("com.google.android.material:material:1.0.4")
//    implementation("com.squareup.retrofit2:converter-gson:2.0.0-beta4")
//    implementation (libs.glide)
//    implementation(libs.retrofit)
//    implementation (libs.gson)
//    implementation (libs.converter.gson)
//    implementation(libs.androidx.room.ktx)
//    ksp(libs.androidx.room.compiler)
//    val appcompat_version = "1.7.0"

//    implementation ("androidx.appcompat:appcompat:$appcompat_version")
    // For loading and tinting drawables on older versions of the platform

//    val nav_version = "2.8.4"
//    implementation("androidx.navigation:navigation-fragment:$nav_version")
//    implementation("androidx.navigation:navigation-ui:$nav_version")
}