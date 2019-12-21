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
    id("org.sonarqube") version "2.8"
    jacoco
}

sonarqube {
    androidVariant = "debug"

    properties {
        property("sonar.projectName", "NoTils")
        property("sonar.projectDescription", "Never again need a .utils. package yur scurvy Java sea dogs!")
        property("sonar.projectKey", "novoda_notils")
        property("sonar.organization", "novoda")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.login", System.getenv("SONAR_TOKEN"))
        property("sonar.scm.provider", "git")
        property("sonar.coverage.jacoco.xmlReportPaths", xmlJacocoReportPaths)
        property("sonar.junit.reportPaths", xmlTestReportPaths)
        property("sonar.sourceEncoding", "UTF-8")
    }
}

subprojects {
    sonarqube {
        properties {
            property("sonar.sources", "src/main/java")
            property("sonar.tests", "src/test/java")
        }
    }
}

val Project.xmlTestReportPaths
    get() = allprojects.flatMap { it.xmlTestReportFolders }.joinToString(",")

val Project.xmlTestReportFolders
    get() = tasks
        .map { it.name }
        .filter { it.startsWith("test") }
        .map { taskName -> "${project.buildDir}/test-results/$taskName/" }

val Project.xmlJacocoReportPaths
    get() = allprojects.flatMap { it.xmlJacocoReportFolders }.joinToString(",")

val Project.xmlJacocoReportFolders
    get() = tasks
            .map { it.name }
            .filter { it.startsWith("test") }
            .map { taskName -> "${project.buildDir}/reports/jacoco/$taskName/jacocoTestReport.xml" }

allprojects {
    version = "3.1.5"
    repositories {
        jcenter()
        mavenCentral()
        google()
    }
}

jacoco {
    toolVersion = "0.8.5"
}

tasks.withType(Test::class.java) {
    finalizedBy("jacocoTestReport")
}
