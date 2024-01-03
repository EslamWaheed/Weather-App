plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.eslamwaheed.weatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.eslamwaheed.weatherapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    //Material Design
    implementation("com.google.android.material:material:1.11.0")
    //ConstrainLayout
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    //Junit
    testImplementation("junit:junit:4.13.2")
    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    //Orbit MVI
    implementation("org.orbit-mvi:orbit-core:6.1.0")
    implementation("org.orbit-mvi:orbit-viewmodel:6.1.0")
    testImplementation("org.orbit-mvi:orbit-test:6.1.0")
    //Chucker
    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")
    //Google Location
    implementation("com.google.android.gms:play-services-location:21.0.1")
    //Coil
    implementation ("io.coil-kt:coil:2.4.0")
    //Domain Module
    implementation(project(path = ":domain"))
    //Data Module
    implementation(project(path = ":data"))
    //Network Module
    implementation(project(path = ":network"))
}