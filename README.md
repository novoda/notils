NoTils
======

Never again need a .utils. package yur scurvy sea dogs!

Whats Included
======

- AndroidUtils, for showing the keyboard, checking running services etc
- ClassCaster - to help with listeners between Activitys & Fragments
- No need to cast when referencing Views / Fragments
- No need to double type your types when creating collections


Adding to your project
======

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

##Android :


````xml
<dependency>
  <groupId>com.novoda.notils</groupId>
  <artifactId>android</artifactId>
  <version>1.0</version>
</dependency>
````


##Java :


````xml
<dependency>
  <groupId>com.novoda.notils</groupId>
  <artifactId>java</artifactId>
  <version>1.0</version>
</dependency>
````

Gradle
-
````gradle
repositories {
    maven {
        url 'https://github.com/novoda/public-mvn-repo/raw/master/releases'
    }
}
`````

##Android :

````gradle
dependencies {
    compile 'com.novoda.notils:android:1.0'
}
````

##Java :

````gradle
dependencies {
    compile 'com.novoda.notils:java:1.0'
}
````
