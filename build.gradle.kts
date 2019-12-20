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
    id("org.sonarqube").version("2.8")
}

sonarqube {
    properties {
        property("sonar.projectName", "NoTils")
        property("sonar.projectDescription", "Never again need a .utils. package yur scurvy Java sea dogs!")
        property("sonar.projectKey", "novoda_notils")
        property("sonar.organization", "novoda")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.login", System.getenv("SONAR_TOKEN"))
        property("sonar.scm.provider", "git")
        property("sonar.coverage.jacoco.xmlReportPaths", xmlReportPaths)
    }
}

val Project.xmlReportPaths
    get() = allprojects.flatMap { it.testReportFolders }.joinToString(",")

val Project.testReportFolders
    get() = tasks
        .map { it.name }
        .filter { it.startsWith("test") }
        .map { taskName -> "${project.buildDir}/test-results/$taskName" }

allprojects {
    version = "3.1.5"
    repositories {
        jcenter()
        mavenCentral()
        google()
    }
}

