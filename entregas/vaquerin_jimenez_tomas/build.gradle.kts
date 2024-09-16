plugins {
    kotlin("jvm") version "1.9.21"
}

group = "dev.tomas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //loger
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    //H2
    implementation("com.h2database:h2:2.2.224")
    // scrips de la base de datos
    implementation("org.mybatis:mybatis:3.5.13")

    //xml
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.0")
    //test
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.withType<Jar> {
    archiveBaseName.set("torneo_tenis")
    archiveVersion.set("1.0")
    manifest {
        attributes["Main-Class"] = "org.example.main.MainKt"
    }
}
