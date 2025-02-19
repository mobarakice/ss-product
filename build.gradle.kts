plugins {
    java
    alias(libs.plugins.spring.framework)
    alias(libs.plugins.spring.dependency.management)
}

group = "com.news360horizon"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation(rootProject.libs.starter.web)
        implementation(rootProject.libs.starter.security)
        implementation(rootProject.libs.starter.data.jdbc)
        implementation(rootProject.libs.starter.graphql)
        implementation("com.graphql-java:graphql-java-extended-scalars:22.0")
        implementation("com.graphql-java-kickstart:graphql-java-tools:14.0.0")
        implementation(rootProject.libs.starter.data.jpa)
        implementation(rootProject.libs.starter.validation)
        implementation (rootProject.libs.postgresql)
        implementation(rootProject.libs.hibernate)
        implementation(rootProject.libs.springdoc.openapi.ui)
        implementation(rootProject.libs.modelmapper)
        implementation(rootProject.libs.modelmapper.record)
        implementation(rootProject.libs.lombok)
        annotationProcessor(rootProject.libs.lombok)
        developmentOnly(rootProject.libs.boot.devtools)
        testImplementation(rootProject.libs.starter.test)
        testImplementation(rootProject.libs.lombok)
        testRuntimeOnly(rootProject.libs.junit.platform.launcher)
    }

    tasks.named<Test>("test") {
//        useJUnitPlatform()
    }
}
