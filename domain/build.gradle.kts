plugins {
    kotlin("jvm")
}

dependencies {
    implementation(Deps.Rx.rxkotlin)
    implementation(Deps.Rx.rxandroid)
    implementation(Deps.Javax.inject)
    testImplementation(Deps.Test.mockito)
    testImplementation(Deps.Test.mockitoKotlin)
    testImplementation(Deps.Test.junit)
    testImplementation(Deps.Test.kotlinTest)
    testImplementation(Deps.Test.podam)
}