import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    idea
    java
    `maven-publish`
    id("org.springframework.boot") version "3.1.0" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
    id("io.freefair.lombok") version "8.0.1" apply false
}

val awsDomainOwner: String? = System.getenv("AWS_DOMAIN_OWNER_ID")
val codeArtifactRepository =
    "https://promotion-${awsDomainOwner}.d.codeartifact.us-east-2.amazonaws.com/maven/releases/"
val codeArtifactPassword: String? = System.getenv("CODEARTIFACT_AUTH_TOKEN")

subprojects {
    group = "com.pauldaniv.promotion.yellowtaxi.facade"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "idea")
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "io.freefair.lombok")
    java.sourceCompatibility = JavaVersion.VERSION_17

    repositories {
        mavenCentral()
        mavenLocal()

        maven {
            name = "CodeArtifact"
            url = uri(codeArtifactRepository)
            credentials {
                username = "aws"
                password = codeArtifactPassword
            }
        }
    }

    dependencies {
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.testng:testng:7.8.0")

    }

    if (project.name != "service") {
        tasks.getByName<BootRun>("bootRun") {
            enabled = false
        }
        tasks.getByName<BootJar>("bootJar") {
            enabled = false
        }
        tasks.getByName<BootBuildImage>("bootBuildImage") {
            enabled = false
        }
    }

    publishing {
        repositories {
            maven {
                name = "CodeArtifactPackages"
                url = uri(codeArtifactRepository)
                credentials {
                    username = "aws"
                    password = codeArtifactPassword
                }
            }
        }

        val sourcesJar by tasks.creating(Jar::class) {
            archiveClassifier.set("sources")
            from(sourceSets["main"].allSource)
        }

        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
                artifact(sourcesJar)
            }
        }
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<Test> {
        useTestNG()
    }
}
