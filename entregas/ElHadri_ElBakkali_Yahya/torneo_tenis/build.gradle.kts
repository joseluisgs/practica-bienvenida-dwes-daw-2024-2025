val koin_version: String by project
val logging_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.23"
    //SqlDelight
    id("app.cash.sqldelight") version "2.0.2"
    //Serialization Kotlin
    kotlin("plugin.serialization") version "1.9.23"
}

group = "org.example"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    //Logger
    implementation("org.lighthousegames:logging:$logging_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    //SqlDelight
    implementation("org.xerial:sqlite-jdbc:3.45.2.0")
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
    //Serialization JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    // Result ROP
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")
    //XML
    implementation("io.github.pdvrieze.xmlutil:core-jvm:0.86.3")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.86.3")
    //Mordant
    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta9")
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
        create("Database") {
            packageName.set("org.example.database.service")
        }
    }
}