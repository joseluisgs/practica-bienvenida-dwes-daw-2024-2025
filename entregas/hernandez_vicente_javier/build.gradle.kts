plugins {
    kotlin("jvm") version "1.9.23"
    // Plugin para la generación de código SQLDelight
    id("app.cash.sqldelight") version "2.0.2"
    // Plugin de serielización
    kotlin("plugin.serialization") version "1.9.23"
}

group = "org.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Logger
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    // SQLDelight para SQLite
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
    implementation("org.xerial:sqlite-jdbc:3.45.2.0")
    // Result ROP
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")
    // Serialización JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation("io.github.pdvrieze.xmlutil:core-jvm:0.86.3")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.86.3")

    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta9")


    testImplementation("io.mockk:mockk:1.13.10")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}


sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("dev.javierhvicente.database")
        }
    }
}