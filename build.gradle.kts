plugins {
    java
    application
}

group = "fi.villevaltonen"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass.set("fi.villevaltonen.hello.Application")
}

repositories {
    mavenCentral()
}

tasks.getByName<Jar>("jar") {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to "fi.villevaltonen.hello.Application"
            )
        )
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}