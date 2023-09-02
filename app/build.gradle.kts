plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.paquitosoft.demotvapplication"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.paquitosoft.demotvapplication"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
        // Desugaring enables using Java 8 APIs like java.time in a backwards compatible manner.
        isCoreLibraryDesugaringEnabled = true
        // Exoplayer requires targeting Java 8
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.leanback:leanback:1.2.0-alpha02")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    // Android X libraries
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.fragment:fragment-ktx:1.4.1")
    implementation("androidx.leanback:leanback:1.2.0-alpha02")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.1")
    implementation("androidx.preference:preference-ktx:1.2.0")
    implementation("androidx.room:room-runtime:2.4.2")
//    kapt("androidx.room:room-compiler:2.4.2")
    implementation("androidx.room:room-ktx:2.4.2")
//    implementation("org.jetbrains.kotlinx:kotlinx-parcelize:1.9.10")


    // Cast library used for Cast Connect feature
    implementation("com.google.android.gms:play-services-cast-tv:17.0.0")
    implementation("com.google.android.gms:play-services-cast:17.0.0")

//    debugImplementation("androidx.fragment:fragment-testing:1.4.1")

    // Work library used for simplifying work done in the background
    implementation ("androidx.work:work-runtime:2.7.1")

    // TV provider library used for updating home screen channels
    implementation("androidx.tvprovider:tvprovider:1.0.0")

    // Picasso for image loading
    implementation("com.squareup.picasso:picasso:2.71828")

    // Exoplayer for playback
    implementation("com.google.android.exoplayer:exoplayer-core:2.17.1")
    implementation("com.google.android.exoplayer:extension-leanback:2.17.1")
    implementation("com.google.android.exoplayer:extension-mediasession:2.17.1")

    // Google Play Services for identity
    implementation ("com.google.android.gms:play-services-auth:20.1.0")

    // Moshi for JSON parsing
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
//    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")

    // Retrofit for HTTP requests
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // Logging
    implementation("com.jakewharton.timber:timber:5.0.1")

}