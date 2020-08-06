plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(AndroidConfig.compileSdkVersion)
    defaultConfig {
        minSdkVersion(AndroidConfig.minSdkVersion)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Deps.Rx.rxkotlin)
    implementation(Deps.Rx.rxandroid)
    implementation(Deps.Square.okhttp)
    implementation(Deps.Square.retrofit)
    implementation(Deps.Square.retrofitAdapterRxjava)
    implementation(Deps.Square.retrofitConverterMoshi)
    implementation(Deps.Square.moshi)
    implementation(Deps.Square.moshiAdapters)
    implementation(Deps.Google.dagger)
    kapt(Deps.Google.daggerCompiler)
    kapt(Deps.Square.moshiKotlinCodegen)

    testImplementation(Deps.Test.junit)
    testImplementation(Deps.Test.kotlinTest)
    testImplementation(Deps.Test.mockito)
    testImplementation(Deps.Test.mockitoKotlin)
}