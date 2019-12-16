plugins {
    java
    id("bintray-release")
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
