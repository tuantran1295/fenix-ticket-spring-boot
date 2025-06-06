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
    │   │               ├── controller
    │   │               │   └── PageController.java
    │   │               ├── dto
    │   │               ├── exception
    │   │               ├── model
    │   │               ├── repository
    │   │               ├── service
    │   │               ├── util
    │   │               ├── CustomErrorController.java
    │   │               └── TicketApplication.java
    │   └── resources
    │       ├── application.yml
    │       ├── db
    │       │   └── migration
    │       ├── static
    │       └── templates
    │           ├── index.html
    │           ├── login.html
    │           ├── loadtest.html
    │           └── list-ticket.html
    └── test
        └── java
            └── com
                └── fenix
                    └── ticket
                        └── TicketApplicationTests.java
```

## Description

This is a Spring Boot application for ticket management. The project is migrated from old code base written in Java 8 and Seasar 2 Framework.

## Technologies Used

- Spring Boot 3.5.0
- Gradle Groove
- Java 21

## Database Setup

**Recommended:** Use Flyway (see `src/main/resources/db/migration`) to auto-migrate schema at startup.

### Manual DB Setup:

```sql
-- Connect as a superuser (e.g., postgres)
CREATE DATABASE fenixdb;
CREATE ROLE fenix WITH LOGIN PASSWORD 'fenix123';
GRANT CONNECT ON DATABASE fenixdb TO fenix;
\c fenixdb
-- The Flyway migration script will create tables, but for manual setup use:
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS tickets (
    id SERIAL PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    description VARCHAR(250) NOT NULL,
    status VARCHAR(250) NOT NULL
);
INSERT INTO users (username, password) VALUES ('admin', 'password') ON CONFLICT (username) DO NOTHING;
ALTER TABLE users OWNER TO fenix;
ALTER TABLE tickets OWNER TO fenix;
ALTER SEQUENCE users_id_seq OWNER TO fenix;
ALTER SEQUENCE tickets_id_seq OWNER TO fenix;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO fenix;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO fenix;
```

## Application properties
Configure src/main/resources/application.yml or export env vars:

```
spring:
datasource:
url: jdbc:postgresql://localhost:5432/fenixdb
username: fenix
password: fenix123
jpa:
hibernate:
ddl-auto: none
show-sql: true
flyway:
enabled: true

server:
port: 8080
```

<!-- ## Development
Run with 
```./gradlew bootRun```
By default, server runs at http://localhost:8080 -->

## Docker Setup

### Prerequisites
- Docker
- Docker Compose

### Build and Run with Docker

1. Stop and remove existing containers and volumes:
```bash
docker-compose down -v
```

2. Build the application JAR:
```bash
./gradlew clean bootJar
```

3. Build and start the containers:
```bash
docker-compose up --build
```

The application will be available at http://localhost:8080

### Docker Compose Services
- Spring Boot Application (port 8080)
- PostgreSQL Database (port 5433)

## Web Pages
The application provides the following web pages accessible through your browser:

| http://localhost:8080/ | Home page (index.html) 
| http://localhost:8080/login | Login page 
| http://localhost:8080/tickets | Ticket list page 
| http://localhost:8080/loadtest | Load testing page

## API Endpoints
Base URL: http://localhost:8080
### 1. 🔐 Login

**Endpoint**:  
`POST /api/login`

**Request Body**:
```json
{
  "username": "admin",
  "password": "password"
}
```

**Response**:
```json
{
  "success": true,
  "message": "Login successful",
  "userId": 1
}
```

---

### 2. 📝 Create Ticket

**Endpoint**:  
`POST /api/tickets`

**Request Body**:
```json
{
  "title": "Issue Example",
  "description": "Example ticket description.",
  "status": "OPEN"
}
```

**Response**:
```json
{
  "success": true,
  "message": "Ticket created",
  "ticketId": 1
}
```

---

### 3. 📋 List Tickets

**Endpoint**:  
`GET /api/tickets`

**Response**:
```json
{
  "success": true,
  "message": "Tickets fetched",
  "tickets": [
    {
      "id": 1,
      "title": "Issue Example",
      "description": "Example ticket description.",
      "status": "OPEN"
    }
  ]
}
```

---

### 4. ✏️ Update Ticket

**Endpoint**:  
`PUT /api/tickets`

**Request Body**:
```json
{
  "id": 1,
  "title": "Updated Title",
  "description": "Updated description.",
  "status": "IN_PROGRESS"
}
```

**Response**:
```json
{
  "success": true,
  "message": "Updated"
}
```

---

### 5. ❌ Delete Ticket

**Endpoint**:  
`DELETE /api/tickets`

**Request Body**:
```json
{
  "ticketId": 1
}
```

**Response**:
```json
{
  "success": true,
  "message": "Deleted"
}
```
