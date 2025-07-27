plugins {
	java
	id("org.springframework.boot") version "3.3.12"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "pet-eclipse-link-orm"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2023.0.5"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa"){
		exclude(group = "org.hibernate.orm", module = "hibernate-core")
		exclude(group = "org.hibernate", module = "hibernate-entitymanager")
	}
	implementation("org.eclipse.persistence:org.eclipse.persistence.jpa:4.0.7")
	runtimeOnly("org.postgresql:postgresql:42.7.3")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
