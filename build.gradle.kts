plugins {
    id("java")
    id("maven-publish")
}

group   = "com.ronreynolds"
version = "0.0.1-SNAPSHOT"

val mainClass = "com.ronreynolds.graphics.Main"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation          (libs.slf4j.api)
    runtimeOnly             (libs.logback)

    // test dependencies
    testImplementation      (libs.assertj)
    testImplementation      (libs.junit.jupiter)
    testRuntimeOnly         (libs.junit.launcher)

    // Lombok dependencies
    compileOnly             (libs.lombok)
    annotationProcessor     (libs.lombok)
    testCompileOnly         (libs.lombok)
    testAnnotationProcessor (libs.lombok)
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.compileTestJava {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register("uberjar", Jar::class) {
    group = "build"
    description = "Creates a jar containing classes and all runtime dependencies"
    manifest.attributes["Main-Class"] = mainClass
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    with(tasks.jar.get())
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}