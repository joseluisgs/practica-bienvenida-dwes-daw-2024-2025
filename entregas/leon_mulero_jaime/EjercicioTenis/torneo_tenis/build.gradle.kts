plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.23"
    id("app.cash.sqldelight") version "2.0.2"
    application
    id("org.jetbrains.dokka") version "1.9.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.45.2.0")
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
    implementation("org.mybatis:mybatis:3.5.13")
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation(platform("io.insert-koin:koin-bom:3.5.6"))
    implementation("io.insert-koin:koin-core")
    implementation("io.insert-koin:koin-test")
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")
    implementation("io.github.pdvrieze.xmlutil:core-jvm:0.86.3")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.86.3")
    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta9")

    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.10")
    testImplementation("io.insert-koin:koin-test-junit5")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.jar {
    archiveFileName.set("torneo_tenis.jar")
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
        create("Database") {
            packageName.set("org.example.database.service")
        }
    }
}