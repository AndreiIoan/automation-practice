# automation-practice

# how to run the tests

There are 2 ways to run the tests in the framework:

1. After opening the project in an IDE, right click on the testng.xml file and create a TestNG run configuration. Running this will launch all the tests at once.
In order to run only the "Happy path" test, the following parameter should be added in the VM options of the run configuration: -Dcucumber.options="-t @HappyPath"

2. The 2nd method to run the tests is to use the maven command in the terminal, by running mvn clean test. This will run all the tests. In order to run the "Happy path" test, the same parameter should be added following the maven commands, which will look like this: mvn clean test -Dcucumber.options="-t @HappyPath"

tests location: automation-practice/src/test/resources/features/

tech and libraries used: Java, Selenide, Cucumber, Springboot, TestNG, Maven

Chrome version: Version 80.0.3987.116
chromedriver.exe is already provided in the resources for this chrome version
