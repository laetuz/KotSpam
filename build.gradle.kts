plugins {
    kotlin("jvm") version "2.1.21"
}

group = "id.neotica"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}

tasks.register<Jar>("fatJar") {
    group = "build"
    archiveBaseName.set("KotSpam")
    archiveClassifier.set("")
    archiveVersion.set("1.0")

    manifest {
        attributes["Main-Class"] = "id.neotica.MainKt" // change to your main class
    }

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}