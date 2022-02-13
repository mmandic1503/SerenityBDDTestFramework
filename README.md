# SerenityBDD Test Framework

## Prerequisites

- java 11
- maven
- IntelliJ plugin for cucumber Java

## Purpose

The purpose of SerenityBDD Test Framework is to utilise SerenityBDD library to create clean and maintainable automated acceptance and regression tests. This test framework contains both API and UI tests documented as Cucumber features.

## Configuration

All configuration is set inside of Serenity.conf file

## Run The Tests

### Using IDE

- find runner class: src/test/java/poc/TestRunner.java
- right click on it and choose option Modify Run Configuration
- under the Build and Run section, set the wanted scope by adding -Dtags=@foo value next to -ea
  - current options for tags are -Dtags=@UI, -Dtags=@API, and -Dtags=@AllTests
- Save configuration
- Run tests by pressing the play button

### Using command line

- to control tags, add in command -Dtags="@foo"
```bash
mvn clean verify -Dtags="@foo" -Dskip.ac.tests=false -Dskip.unit.tests=true
```

## Reporting

### Using IDE
After all tests are done:
- open Maven
- under Plugins find serenity
- run serenity:aggregate command
- in run output console you can find the link towards Full Report and Single Page HTML Summary
```
[INFO] -----------------------------------------
[INFO] 
[INFO] SERENITY REPORTS
[INFO]   - Full Report: file:///<path-to-project>/SerenityBDDTestFramework/target/site/serenity/index.html
[INFO]   - Single Page HTML Summary: file:///<path-to-project>/IdeaProjects/SerenityBDDTestFramework/target/site/serenity/serenity-summary.html
[INFO] ------------------------------------------------------------------------

```

### Using Command Line
- run mvn serenity:aggregate command