import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

apply {
    from("../shared_dependencies.gradle")
}

android {
    namespace = "com.alland.mymovieapps.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        buildConfigField(
            "String",
            "TOKEN_API",
            gradleLocalProperties(rootDir, providers).getProperty("tokenMovieApi").toString()
        )
        buildConfigField(
            "String",
            "MOVIE_PATH",
            gradleLocalProperties(rootDir, providers).getProperty("movieApiPath").toString()
        )
        buildConfigField(
            "String",
            "MOVIE_POSTER_PATH",
            gradleLocalProperties(rootDir, providers).getProperty("imagePath").toString()
        )
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
    kapt {
        generateStubs = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //retrofit and okhttp
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)

    //viewModel and liveData
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.livedata.ktx)

    //room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}