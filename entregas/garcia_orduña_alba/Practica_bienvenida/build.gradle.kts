plugins {
    kotlin("jvm") version "1.9.23"
    id("app.cash.sqldelight") version "2.0.2"
    kotlin("plugin.serialization") version "1.9.23"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation(platform("io.insert-koin:koin-bom:3.5.6"))
    implementation("io.insert-koin:koin-core")
    implementation("io.insert-koin:koin-test")
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.10")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("dev.alba.database")
        }
    }
}