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
    implementation("com.squareup.okhttp3:okhttp:4.9.2")

    implementation(kotlin("stdlib"))
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("io.mockk:mockk:1.13.2")
    testImplementation("net.bytebuddy:byte-buddy:1.12.7")

    implementation("io.insert-koin:koin-core:3.3.2")

    //Environment variable
    implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")

    //Caching
    implementation("com.github.ben-manes.caffeine:caffeine:3.0.0")

    testImplementation(kotlin("test"))

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs = listOf("-Dnet.bytebuddy.experimental=true")
}


kotlin {
    jvmToolchain(21)
}