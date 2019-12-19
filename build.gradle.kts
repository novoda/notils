buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Libraries.Build.androidGradle)
        classpath(Libraries.Build.bintrayRelease)
    }
}

plugins {
    id("org.sonarqube").version("2.7.1")
}

sonarqube {
    properties {
        property("sonar.projectKey", "novoda_notils")
        property("sonar.organization", "novoda")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.login", System.getenv("SONAR_TOKEN"))
    }
}

allprojects {
    version = "3.1.5"
    repositories {
        jcenter()
        mavenCentral()
        google()
    }
}
