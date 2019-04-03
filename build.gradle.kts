plugins {
    kotlin("jvm") version "1.3.20" 
}

repositories {
    jcenter() 
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.http4k:http4k-core:3.127.0")
    implementation("org.http4k:http4k-server-jetty:3.127.0")
    implementation("org.http4k:http4k-client-apache:3.127.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
}
