plugins { 
    id("com.android.application")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.novodo.notils.demo"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    // notils
    // https://github.com/novoda/notils
    implementation("com.novoda:notils-java:3.1.5")
    implementation("com.novoda:notils-android:3.1.5")

    // butterknife
    // https://github.com/JakeWharton/butterknife
    implementation("com.jakewharton:butterknife:9.0.0-rc1")
    annotationProcessor("com.jakewharton:butterknife-compiler:9.0.0-rc1")
}
