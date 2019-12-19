import org.gradle.api.JavaVersion

object Versions {
  val sourceCompatibility = JavaVersion.VERSION_1_7
  val targetCompatibility = JavaVersion.VERSION_1_7

  object AndroidSdk {

    const val compile = 29
    const val min = 14

  }

}

object Libraries {

  object Build {
    const val androidGradle = "com.android.tools.build:gradle:3.5.3"
    const val bintrayRelease = "com.novoda:bintray-release:0.9"
  }

  object App {
    const val supportAppCompat = "androidx.appcompat:appcompat:1.1.0"
  }

  object Test {
    const val jUnit = "junit:junit:4.12"
  }

}