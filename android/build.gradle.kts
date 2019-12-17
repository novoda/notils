plugins {
    id("com.android.library")
    id("bintray-release")
}

repositories {
    google()
    jcenter()
}

android {
    compileSdkVersion(Versions.AndroidSdk.compile)

    defaultConfig {
        minSdkVersion(Versions.AndroidSdk.min)
        versionCode = 1
    }
}

dependencies {
    implementation(Libraries.App.supportAppCompat)
    api(project(":java"))

    testImplementation(Libraries.Test.jUnit)
}

publish {
    userOrg = "novoda"
    groupId = "com.novoda"
    artifactId = "notils-android"
    publishVersion = project.version as String
    desc = "Never again need a .utils. package yur scurvy Android sea dogs!"
    website = "https://github.com/novoda/NoTils"
}
