import org.jetbrains.compose.desktop.application.dsl.TargetFormat

val ktorVersion = "2.3.2"
val coilVersion = "3.0.0-alpha06"
val mongoDbVersion = "5.1.0"

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.8.20"
}

group = "YZ"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Ktor
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    // Coil
    implementation("io.coil-kt.coil3:coil:$coilVersion")
    implementation("io.coil-kt.coil3:coil-network-ktor:$coilVersion")

    // MongoDB
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:$mongoDbVersion")
    implementation("org.mongodb:bson-kotlinx:$mongoDbVersion")

    // Dotenv
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

    // Google Gson
    implementation("com.google.code.gson:gson:2.8.9")

    // Compose basics
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "quiz"
            packageVersion = "1.0.0"
        }
    }
}
