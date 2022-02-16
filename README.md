### SafetyNet Alerts
This application is an API REST using Spring framework.
The purpose of this application is to send information to emergency service systems.

### Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

- Java 1.8
- Maven 3.8.2

### Installing

A step by step series of examples that tell you how to get a development env running:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

### Default configuration

Controller URL = http://localhost:8080
Port can be change in application.properties
Log file location : c:/temp/logs/alerts

### Endpoints and URLS

For more information, consult JavaDoc (controllers part).

### Running App

Import the code into an IDE of your choice and run the AlertsApplication.java to launch the application.

Or with "alerts-1.0.0-SNAPSHOT.jar" file, open a Terminal and execute the below command.

`java -jar alerts-1.0.0-SNAPSHOT.jar`

### Testing

The app has unit tests and integration tests written.

To run the tests from maven, open a Terminal and execute the below command.

`mvn verify`

### Maven site

You can generate a maven site containing :

- FailSafe report;
- SureFire report;
- Jacoco report;
- JavaDoc.

To do so, open a Terminal and execute the below command.

`mvn verify site`