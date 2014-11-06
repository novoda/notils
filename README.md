NoTils
======

Never again need a .utils. package yur scurvy sea dogs!

![](http://ci.novoda.com/buildStatus/icon?job=NoTils)

Whats Included
======

- AndroidUtils, for showing the keyboard, checking running services etc
- ClassCaster - to help with listeners between Activitys & Fragments
- No need to cast when referencing Views / Fragments
- No need to double type your types when creating collections
- Simple Fade animations in & out done for you
- Logging Novogger & Simple Log to give to give automatic details of where the Log executed
- StrictMode Management - enable strict mode in one line
- WebViews, allowing custom loading of different scenarios (raw assets, external urls)
- ToastDisplayers for saner displaying of Toast notifications
- SimpleDateFormatThreadSafe allowing you to use date formatting from multiple threads
- DeveloperError - custom exceptions for explicit declaration / faster feedback when something goes wrong

Adding to your project
======

Check the latest version of NoTils on the [releases page](https://github.com/novoda/NoTils/releases). We tag releases as "v1.2.3" so below, where `VERSION` is written, you'd put "1.2.3".

Gradle
-
````groovy
repositories {
    jcenter()
}
`````

````groovy
dependencies {
    compile 'com.novoda:notils:VERSION'
}
````


Maven
-

````xml
<repositories>
    <repository>
      <id>bintray-jcenter</id>
      <url>http://jcenter.bintray.com</url>
    </repository>
</repositories>
````

````xml
<dependency>
  <groupId>com.novoda</groupId>
  <artifactId>notils</artifactId>
  <version>VERSION</version>
</dependency>
````

License
-------

    (c) Copyright 2014 Novoda

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
