# TestVagrantAssignment
Weather reporting and comparing on the basis of variance
Directory Structure-
````
├── Screenshots
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   └── test
│       ├── java
│       │   └── com
│       │       └── testvagrant
│       │           ├── api
│       │           │   └── WeatherAPI.java
│       │           ├── helper
│       │           │   ├── BaseWeb.java
│       │           │   ├── ConfigFileReader.java
│       │           │   ├── CsvFileUtils.java
│       │           │   └── WaitHelper.java
│       │           ├── pojo
│       │           │   └── WeatherPojo.java
│       │           ├── stepdefinitions
│       │           │   ├── APISteps.java
│       │           │   ├── CommonSteps.java
│       │           │   ├── Constants.java
│       │           │   └── WebSteps.java
│       │           ├── testrunner
│       │           │   └── TestRunner.java
│       │           └── ui
│       │               └── pages
│       │                   ├── HomePage.java
│       │                   └── WeatherPage.java
│       └── resources
````````
## How to use this repository
Need to Run maven install from pom.xml file
## How to check the results
Once execution gets complete the results for the tests are generates here Test-output/HtmlReport/ExtentHtml.html

![alt text](https://github.com/shukrani/TestVagrantAssignment/blob/feature/testvagrant/Screenshots/output.png)

