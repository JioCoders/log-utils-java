# Project Name

A brief, one-sentence description of what this Java Spring Boot application does.

## 🚀 Features

* **Feature 1**: Brief description of a core capability.
* **Feature 2**: Brief description of another capability.
* **REST API**: Clean, documented endpoints for system interaction.

[![Publish to Maven Central](https://github.com/JioCoders/log-utils-java/actions/workflows/gradle-publish.yml/badge.svg)](https://github.com/JioCoders/log-utils-java/actions/workflows/gradle-publish.yml)

[![](https://jitpack.io/v/JioCoders/log-utils-java.svg)](https://jitpack.io/#JioCoders/log-utils-java)

## 🛠️ Prerequisites

Before running this project, ensure you have the following installed:

* **Java JDK**: Version 17 or 21
* **Build Tool**: Maven (3.8+) or Gradle (8.0+)
* **Database**: PostgreSQL / MySQL / MongoDB (if applicable)

## ⚙️ Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com
cd your-repo-name
```

### 2. Configure Environment Variables
Update the `src/main/resources/application.properties` (or `application.yml`) file with your local configurations, such as database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build and Run the Application

#### Using Maven:
```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

#### Using Gradle:
```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun
```

The application will start on `http://localhost:8080` by default.

## 🧪 Testing

To execute the unit and integration test suites:

```bash
# Maven
./mvnw test

# Gradle
./gradlew test
```

## 📍 API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/api/v1/resources` | Fetch all resources |
| **POST** | `/api/v1/resources` | Create a new resource |
| **GET** | `/api/v1/health` | Actuator health check endpoint |

*(Optional) Access the Swagger UI documentation at `http://localhost:8080/swagger-ui.html` when the app is running.*
