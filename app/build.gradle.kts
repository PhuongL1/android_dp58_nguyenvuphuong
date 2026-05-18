plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.devpro.android_dp58_nguyenvuphuong"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.devpro.android_dp58_nguyenvuphuong"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.intuit.sdp:sdp-android:1.1.1") // don vi chinh kich thuoc man hinh
    implementation("com.intuit.ssp:ssp-android:1.1.1") // don vi chinh kich thuoc chu
    implementation("com.github.bumptech.glide:glide:5.0.5") // load anh ve nhanh
    implementation("com.google.code.gson:gson:2.14.0") //Thêm thư viện Gson
}