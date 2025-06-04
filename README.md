# Ticket Project

## Project Structure

```
ticket/
├── .idea
├── .gradle
├── .gitattributes
├── .gitignore
├── HELP.md
├── build.gradle
├── gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── fenix
    │   │           └── ticket
    │   │               └── TicketApplication.java
    │   └── resources
    │       ├── application.properties
    │       ├── db
    │       │   └── migration
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── fenix
                    └── ticket
                        └── TicketApplicationTests.java
```

## Description

This is a Spring Boot application for ticket management. The project follows a standard Spring Boot project structure with the following main components:

- `src/main/java`: Contains the main Java source code
- `src/main/resources`: Contains application resources, configuration files, and static assets
- `src/test`: Contains test source code
- `build.gradle`: Main build configuration file
- `gradle/`: Contains Gradle wrapper files for building the project

## Getting Started

[Add instructions for setting up and running the project]

## Technologies Used

- Spring Boot
- Gradle
- Java 