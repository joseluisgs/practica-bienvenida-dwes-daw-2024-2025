plugins {
    kotlin("jvm") version "1.9.23"
    application
    id("org.jetbrains.dokka") version "1.9.0"
    id("app.cash.sqldelight") version "2.0.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta9")
    implementation("net.java.dev.jna:jna:5.13.0")

    // mordant
    implementation("com.github.ajalt.mordant:mordant:2.0.0")

    // logger
    implementation("org.lighthousegames:logging:1.5.0")
    //implementation("ch.qos.logback:logback-classic:1.5.0")
    //implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("org.slf4j:slf4j-api:1.7.32")

    // SQLDeLight para SQLite
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")

    // Result ROP
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")

    //serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // Mock
    testImplementation("io.mockk:mockk:1.13.10")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}


tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.example.MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

sqldelight {
    databases {
        // Nombre de la base de datos
        create("AppDatabase") {
            // Paquete donde se generan las clases
            packageName.set("org.example.database")
        }
    }
}

