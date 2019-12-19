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

allprojects {
    version = "3.1.5"
    repositories {
        jcenter()
        mavenCentral()
        google()
    }
}
