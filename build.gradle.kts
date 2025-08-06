import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.convert") version "1.5.8"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.9.10"
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.spring") version "1.9.10"
    id("jacoco")
    id("org.sonarqube") version "3.3"
}

group = "com.vscouting"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17


configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

val ktlint: Configuration by configurations.creating

repositories {
    val nexusRepo = maven {
        name = "nexus"
        url = uri("http://localhost:8081/repository/maven-releases/")
        credentials {
            username = "admin"
            password = "nexus123"
        }
    }
    (nexusRepo as MavenArtifactRepository).isAllowInsecureProtocol = true
    mavenCentral()
    mavenLocal()
}

extra["snippetsDir"] = file("build/generated-snippets")
extra["springCloudVersion"] = "2021.0.0"

dependencies {

    implementation("com.vscouting:vscouting-core-errors:1.0.1")
    implementation("com.vscouting:vscouting-core-security:1.0.1")


    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.3.3")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Spring-Cloud
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")

    // Spring-Documentation
    implementation("org.springdoc:springdoc-openapi-ui:1.6.9")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.9")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.3.9")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Ktlint
    ktlint("com.pinterest:ktlint:0.40.0")

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Logs
    implementation("net.logstash.logback:logstash-logback-encoder:6.3")

    // Dev
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-runner-junit5:5.0.3")
    testImplementation("io.kotest:kotest-assertions-core:5.0.3")
    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")

    // Jwt
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // Arch unit
    testImplementation("com.tngtech.archunit:archunit:0.14.1")
    testImplementation("com.tngtech.archunit:archunit-junit5:0.14.1")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

jacoco {
    toolVersion = "0.8.8"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(false)
        csv.required.set(false)
        xml.outputLocation.set(file("${buildDir}/jacoco/jacoco.xml"))
    }
}

sonarqube {
    properties {
        property("sonar.coverage.jacoco.xmlReportPaths", "${buildDir}/jacoco/jacoco.xml")
        property(
            "sonar.coverage.exclusions"

        )
    }
}

tasks.sonarqube {
    dependsOn(tasks.check)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform() {
        includeEngines("kotest")
        includeEngines("archunit")
        includeEngines("junit-jupiter")
    }
}

val snippetsDir by extra { file("build/generated-snippets") }

tasks.test {
    extensions.configure(JacocoTaskExtension::class) {
        setDestinationFile(file("$buildDir/jacoco/jacocoTest.exec"))
        classDumpDir = file("${buildDir}/jacoco/classpathdumps")
    }
    finalizedBy(tasks.jacocoTestReport)

    outputs.dir(snippetsDir)

    testLogging {
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        events("PASSED", "FAILED", "SKIPPED")

    }
}

tasks.asciidoctor {
    inputs.dir(snippetsDir)
}

val ktlintCheck by tasks.creating(JavaExec::class) {
    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
}

ktlintCheck.dependsOn(ktlintFormat)
tasks.build.get().dependsOn(ktlintCheck)

springBoot {
    buildInfo()
}