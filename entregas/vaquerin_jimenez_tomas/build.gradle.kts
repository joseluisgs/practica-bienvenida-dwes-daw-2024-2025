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
    //test
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}