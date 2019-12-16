import org.gradle.api.JavaVersion

object Versions {
  val sourceCompatibility = JavaVersion.VERSION_1_7
  val targetCompatibility = JavaVersion.VERSION_1_7
  const val support = "27.0.2"

  object AndroidSdk {

    val compile = 27
    val min = 14

  }

}

object Libraries {

  object Build {
    const val androidGradle = "com.android.tools.build:gradle:3.5.3"
    const val bintrayRelease = "com.novoda:bintray-release:0.9"
  }

  object App {
    const val supportAppCompatV7 = "com.android.support:appcompat-v7:${Versions.support}"
  }

  object Test {
    const val jUnit = "junit:junit:4.12"
  }

}