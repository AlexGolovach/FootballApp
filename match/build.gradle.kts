plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(AndroidConfig.compileSdkVersion)
    defaultConfig {
        minSdkVersion(AndroidConfig.minSdkVersion)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        isEnabled = true
    }

    testOptions {
        animationsDisabled = true
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(project(":inject"))
    implementation(project(":ui"))
    implementation(project(":domain"))
    implementation(Deps.Rx.rxkotlin)
    implementation(Deps.Rx.rxandroid)
    implementation(Deps.Androidx.material)
    implementation(Deps.Androidx.fragment)
    implementation(Deps.Androidx.constraintlayout)
    implementation(Deps.Javax.inject)
    implementation(Deps.Square.picasso)
}