dependencies {
    implementation(project(":common"))
    implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")
    implementation("org.hibernate.orm:hibernate-core:7.4.5.Final")
    runtimeOnly("org.postgresql:postgresql:42.7.13")
}