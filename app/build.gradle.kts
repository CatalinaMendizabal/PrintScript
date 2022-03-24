
plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.5.31"
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/austral-ingsis/printscript-parser-common")
        credentials {
            username = System.getenv("GITHUB_USERNAME")
            password = System.getenv("GITHUB_PASSWORD")
        }
    }
}

dependencies {
    implementation ("org.austral.ingsis.printscript:printscript-parser-common:0.1.0")
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
