plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(AndroidConfig.compileSdkVersion)
    defaultConfig {
        minSdkVersion(AndroidConfig.minSdkVersion)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(Deps.Rx.rxkotlin)
    implementation(Deps.Rx.rxandroid)
    implementation(Deps.Androidx.material)
    implementation(Deps.Androidx.fragment)
    implementation(Deps.Androidx.constraintlayout)
    implementation(Deps.Square.picasso)
}