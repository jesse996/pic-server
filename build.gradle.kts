import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.spring") version "1.5.21"
    id("org.springframework.experimental.aot") version "0.10.2-SNAPSHOT"
    id("org.graalvm.buildtools.native") version "0.9.1"

    id("org.flywaydb.flyway") version "7.9.2"

}

group = "com.example"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    maven { url = uri("https://repo.spring.io/snapshot") }
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("mysql:mysql-connector-java:8.0.25")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.5.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.5")

    //knife4j
    implementation("com.github.xiaoymin:knife4j-spring-boot-starter:3.0.3")

    //hutool
    implementation("cn.hutool:hutool-all:5.7.14")
    //flyway
    implementation("org.flywaydb:flyway-core:8.0.0")

    //mybatis plus
    implementation("com.baomidou:mybatis-plus-boot-starter:3.4.3.4")
    implementation("com.baomidou:mybatis-plus-generator:3.4.1")
    implementation("org.apache.velocity:velocity-engine-core:2.3")

    //redis
    implementation("org.apache.commons:commons-pool2:2.10.0")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:2.5.5")

    //sa-token
    implementation("cn.dev33:sa-token-spring-boot-starter:1.27.0")
    implementation("cn.dev33:sa-token-dao-redis-jackson:1.27.0")
    implementation("cn.dev33:sa-token-alone-redis:1.27.0")

    //mail
    implementation("org.springframework.boot:spring-boot-starter-mail:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.5.5")

    //validator
    implementation("org.springframework.boot:spring-boot-starter-validation:2.5.5")

    //alipay
    implementation("com.alipay.sdk:alipay-easysdk:2.2.1")

    //log
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootBuildImage> {
    builder = "paketobuildpacks/builder:tiny"
    environment = mapOf("BP_NATIVE_IMAGE" to "true")
}

flyway {
    url = "jdbc:mysql://localhost:3306/pic?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8"
    user = "root"
    password = "123"
}
