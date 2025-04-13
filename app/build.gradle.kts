


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")

}

android {
    namespace = "com.ali.moviesApp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ali.moviesApp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "0.0.1"
        multiDexEnabled = true
        android.buildFeatures.buildConfig = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            buildConfigField("boolean", "DEBUG", "false") // Set DEBUG to false for release builds
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            buildConfigField("boolean", "DEBUG", "true") // Set DEBUG to true for debug builds
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    dataBinding{
        enable=true
    }


}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.9")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.9")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")


    // SwipeToRefresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Sdp
    implementation("com.intuit.sdp:sdp-android:1.0.6")
    implementation("com.intuit.ssp:ssp-android:1.0.6")

    // CircleImageView
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.1"))
    implementation("com.squareup.okhttp3:okhttp-urlconnection")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // Coroutines dependency
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // logger
    implementation ("com.jakewharton.timber:timber:4.7.0")

    //MotionToast
    implementation ("com.github.Spikeysanju:MotionToast:1.3.3.4")

    //SpinKit
    implementation("com.github.ybq:Android-SpinKit:1.4.0")


    //Bottom Nav Bar
    implementation ("com.github.ibrahimsn98:SmoothBottomBar:1.7.9")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    //Room database
    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1"
)
}