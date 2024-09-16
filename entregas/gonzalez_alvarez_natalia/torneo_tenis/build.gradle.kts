plugins {
    kotlin("jvm") version "1.9.23"
    id("app.cash.sqldelight") version "2.0.2"
    kotlin("plugin.serialization") version "1.9.22"
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    //errores
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")

    //logger
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")

    //sqlite
    implementation("org.xerial:sqlite-jdbc:3.46.1.0")

    //mordant
    implementation("com.github.ajalt.mordant:mordant:3.0.0")

    //sqldelight
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")

    //koin
    implementation(platform("io.insert-koin:koin-bom:3.5.6"))
    implementation("io.insert-koin:koin-core") //core
    implementation("io.insert-koin:koin-test") //test

    //koin tests
    testImplementation("io.insert-koin:koin-test-junit5")

    //XML
    implementation("io.github.pdvrieze.xmlutil:core-jvm:0.86.3")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.86.3")

    //JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("org.example.database")
        }
    }
}

tasks.jar {
    archiveFileName.set("torneo_tenis.jar")
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}