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
        property("sonar.projectKey", "notils")
        property("sonar.host.url", "https://sonar.novoda.com")
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
