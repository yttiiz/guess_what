import org.jetbrains.compose.desktop.application.dsl.TargetFormat
val ktorVersion = "2.3.2"

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
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("com.google.code.gson:gson:2.8.9")
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
