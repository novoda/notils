NoTils
======

Never again need a .utils. package yur scurvy sea dogs!

Whats Included
======

- AndroidUtils, for showing the keyboard, checking running services etc
- ClassCaster - to help with listeners between Activitys & Fragments
- No need to cast when referencing Views / Fragments
- No need to double type your types when creating collections
- Simple Fade animations in & out done for you
- Logging Novogger & Simple Log to give to give automatic details of where the Log executed
- StrictMode Management - enable strict mode in one line
- WebViews , allowing custom loading of different scenarios (raw assets, external urls)


Adding to your project
======

Gradle
-
````groovy
repositories {
    maven {
        url 'https://github.com/novoda/public-mvn-repo/raw/master/releases'
    }
}
`````

````groovy
dependencies {
    compile 'com.novoda:notils:2.2.4'
}
````


Maven
-

````xml
<repositories>
  <repository>
    <id>public-mvn-repo-releases</id>
    <url>
      https://github.com/novoda/public-mvn-repo/raw/master/releases
    </url>
  </repository>
</repositories>
````

````xml
<dependency>
  <groupId>com.novoda</groupId>
  <artifactId>notils</artifactId>
  <version>2.2.1</version>
</dependency>
````
