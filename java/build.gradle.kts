plugins {
    java
    id("bintray-release")
    jacoco
}

java {
    sourceCompatibility = Versions.sourceCompatibility
    targetCompatibility = Versions.targetCompatibility
}

dependencies {
    testImplementation(Libraries.Test.jUnit)
}

publish {
    userOrg = "novoda"
    groupId = "com.novoda"
    artifactId = "notils-java"
    publishVersion = project.version as String
    desc = "Never again need a .utils. package yur scurvy Java sea dogs!"
    website = "https://github.com/novoda/NoTils"
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        csv.isEnabled = false
        html.destination = file("${buildDir}/reports/jacoco")
    }
}
