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

    implementation(platform("org.apache.logging.log4j:log4j-bom:3.0.0-beta3"))
    runtimeOnly("org.apache.logging.log4j:log4j-core")
    runtimeOnly("org.apache.logging.log4j:log4j-layout-template-json")
    implementation("org.apache.logging.log4j:log4j-api")
}

tasks.test {
    useJUnitPlatform()
}