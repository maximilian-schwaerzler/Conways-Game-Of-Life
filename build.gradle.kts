plugins {
    id("java")
}

group = "at.co.schwaerzler.maximilian"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains:annotations:24.0.0")
    implementation("commons-cli:commons-cli:1.9.0")
    implementation("commons-io:commons-io:2.18.0")
}

tasks.test {
    useJUnitPlatform()
}