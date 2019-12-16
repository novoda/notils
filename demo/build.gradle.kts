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
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("com.android.support:appcompat-v7:29.0.0")
    implementation("com.android.support.constraint:constraint-layout:1.1.3")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")

    // notils
    // https://github.com/novoda/notils
    implementation("com.novoda:notils-java:3.1.5")
    implementation("com.novoda:notils-android:3.1.5")

    // butterknife
    // https://github.com/JakeWharton/butterknife
    implementation("com.jakewharton:butterknife:9.0.0-rc1")
    annotationProcessor("com.jakewharton:butterknife-compiler:9.0.0-rc1")
}
