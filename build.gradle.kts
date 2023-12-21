import org.gradle.api.tasks.wrapper.Wrapper.DistributionType

val kotlinLoggingVersion = "3.0.5"
val kotlinTestVersion = "5.8.0"
val micrometerJvmExtrasVersion = "0.2.2"
val testContainersVersion = "1.19.3"

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.google.osdetector") version "1.7.3"
    val kotlinVersion = "1.9.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

repositories {
    maven(url = "https://repo.spring.io/milestone")
    maven(url = "https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    mavenCentral()
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

group = "com.example"
project.version = "0.0.1-SNAPSHOT"

dependencies {

    implementation("org.springframework.boot:spring-boot-starter")

    if (osdetector.arch.equals("aarch_64")) {
        implementation("io.netty:netty-all")
    }

    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // the following dependencies causes warn to appear in the logs
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")

    implementation("io.micrometer:micrometer-registry-prometheus")
    testImplementation("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter:$testContainersVersion")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.wrapper {
    distributionType = DistributionType.BIN
}




