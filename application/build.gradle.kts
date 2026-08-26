plugins {
    java
}

dependencies {
    implementation(project(":common"))
    implementation(project(":service"))
    implementation("tools.jackson.core:jackson-databind:3.2.2") //json parser
    implementation("am.ik.yavi:yavi:0.16.0") //validation
}

val fatJar = tasks.register("fatJar", Jar::class) {
    description = "https://github.com/Eminbegin/y28-4sem-java/blob/main/lab-2.md"
    manifest {
        attributes["Main-Class"] = "ru.nikita.lab2.application.Main"
    }
    archiveBaseName = "${rootProject.name}-app"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    dependsOn(configurations.runtimeClasspath)
    from(
        configurations.runtimeClasspath.get().map { if (it.isDirectory()) it else zipTree(it) }
    )
    with(tasks.jar.get() as CopySpec)
}

tasks {
    build {
        dependsOn(fatJar)
    }
}