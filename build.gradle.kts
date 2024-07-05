import org.jetbrains.kotlin.fir.declarations.builder.buildTypeAlias

plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("kapt") version "1.7.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set(".MainKt")
}


dependencies {
    testImplementation(kotlin("test"))

    implementation("com.squareup.okhttp3:okhttp:4.9.2")

    implementation(kotlin("stdlib"))
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

    implementation("io.insert-koin:koin-core:3.3.2")

    //Environment variable
    implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")

    testImplementation(kotlin("test"))

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}