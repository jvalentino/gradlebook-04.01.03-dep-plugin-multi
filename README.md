## 4.1 Compile Dependencies

### 4.1.3 Method 3: Multi-Project

As of Gradle 3.1, a composite build feature was added that makes manually testing plugins not require additional steps. This works by allowing the associating of the manual test project with the plugin codebase directly. The result is that the manual test can be invoked without having to create the plugin jar or upload it and its POM anywhere. 

#### build.gradle

```properties
version = '1.0.0'
group = 'com.blogspot.jvalentino.gradle'
archivesBaseName = 'dep-plugin-multi'
```

The build is the same as with the first method, where the only deployment configuration it to publish to local Artifactory.

#### settings.gradle

```

```

A blank **settings.gradle** file is required to do a multi-project build. Without it, a runtime error will be generated when attempting to run the plugin test.

#### plugins-tests/local/build.gradle

```groovy
buildscript {
  repositories {
	jcenter()
  }
  dependencies {
    classpath 'com.blogspot.jvalentino.gradle:dep-plugin-multi:1.0.0'
  }
}

apply plugin: 'dep-plugin'
```

The manual plugin test no longer must declare a dependency on the plugin jar as it sits on the file system, or in a local maven repository. The plugin test relies on the **settings.gradle** file to pull the needed library.

 

#### plugin-tests/local/settings.gradle

```groovy
rootProject.name='dep-plugin-multi-test'
includeBuild '../../'
```

**Line 1: Root project**

The name of the project, designated as root project, can be anything that is different than the parent project.

 

**Line 2: Build reference**

The **includeBuild** directive pulls in the **build.gradle** from the parent project, relative to this project.

 

#### Manually testing the plugin

```bash
dep-plugin-multi/plugin-tests/local$ gradlew random

> Task :random 
RANDOM: 0.8971841709099377


BUILD SUCCESSFUL
```

To verify that the plugin is being pulled correctly, along with its dependency on Apache Common RNG, the **random** task can be executed.



