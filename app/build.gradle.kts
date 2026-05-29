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

    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-livedata:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.7.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.cardview:cardview:1.0.0")

    // Network
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.squareup.okhttp3:okhttp:5.3.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.3.0")

    // Image loading
    implementation ("com.github.bumptech.glide:glide:5.0.5")
    implementation ("androidx.recyclerview:recyclerview:1.4.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:5.0.5")

    // Room
    implementation("androidx.room:room-runtime:2.8.3")
    annotationProcessor ("androidx.room:room-compiler:2.8.3")
}