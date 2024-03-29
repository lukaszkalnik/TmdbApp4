plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))

    implementation("com.google.android.material:material:1.5.0")

    implementation("androidx.activity:activity-compose:1.4.0")

    val composeVersion = "1.1.0"
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")

    val koinVersion = "3.1.5"
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")

    implementation("io.coil-kt:coil-compose:2.0.0-alpha09")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
}

android {
    compileSdkVersion(31)

    defaultConfig {
        applicationId = "com.kalnik.tmdbapp4.android"
        minSdkVersion(27)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0"
    }

    kotlinOptions {
        jvmTarget = "11"
        // For some reason this is needed for implementing the ImageLoaderFactory by the App class
        freeCompilerArgs = listOf("-Xjvm-default=enable")
    }
}