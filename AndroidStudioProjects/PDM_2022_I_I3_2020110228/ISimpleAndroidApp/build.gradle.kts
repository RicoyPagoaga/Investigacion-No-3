plugins {
    id("com.android.application")
    id ("kotlin-android-extensions")
    kotlin("android")
}


android {
    compileSdk = 32
    defaultConfig {
        applicationId = "hn.edu.ujcv.pdm_2022_i_i3_2020110228.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
}