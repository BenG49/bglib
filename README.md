# bglib
Java library that I use for some of my projects

## To use:
1. Clone with git clone https://github.com/BenG49/gol.git
2. Build with `gradle build` in the root folder
3. Move the output .jar file in `bglib/lib/build/libs/lib.jar` into another gradle project's lib folder: `<project>/app/libs/lib.jar`
4. Add the following to your `<project>/app/build.gradle`:
```groovy
plugins {
    id 'application'
    
    // StuyLib
    id "edu.wpi.first.GradleRIO" version "2020.3.2"
}

repositories {
    // StuyLib
    maven { url "https://jitpack.io" }
}

dependencies {
    // StuyLib
    compile 'com.github.StuyPulse:StuyLib:v2021.0.7'
    
    // bglib
    compile files('libs/lib.jar')
}
