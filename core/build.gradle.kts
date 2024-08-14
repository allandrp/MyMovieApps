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
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMTkwNWJlNDI3MTdiNzg5YjM2NDE3N2MyMDMwMmFlYyIsIm5iZiI6MTcyMjY1MDU5Ni4xMjkzODQsInN1YiI6IjYzNmM2YjJlZjE0ZGFkMDBmMjczOTkwNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.pOBjGrGeRE9Hn7yD5-8teXi8Hqa1i0-UOwtmICu4J6o"
        )
        buildConfigField(
            "String",
            "MOVIE_PATH",
            "https://api.themoviedb.org/3/movie/"
        )
        buildConfigField(
            "String",
            "MOVIE_POSTER_PATH",
            "https://image.tmdb.org/t/p/original/"
        )
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = true
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

    implementation (libs.android.database.sqlcipher)
    implementation (libs.androidx.sqlite.ktx)

    //room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}