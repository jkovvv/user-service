# Java User Service

A backend microservice responsible for managing user-related operations in the MicroservicesSystem project.

The service handles HTTP requests related to users and communicates with a PostgreSQL database for data persistence.

---

## Architecture

                 API Gateway
                      |
                      v
              User Service :9001
                      |
                      v
              Users PostgreSQL

---

## Technologies

- Java 17
- PostgreSQL
- JDBC
- Docker

---

## Features

- User data management
- HTTP request handling
- Database communication using JDBC
- Integration with API Gateway
- Microservice architecture support

---

## Running

This service is part of the MicroservicesSystem project.

To run the complete environment, use the docker-compose.yml file from the main repository:

    git clone https://github.com/jkovvv/microservices-system.git

    cd MicroservicesSystem

    docker compose up --build

---

## Service Port

User Service runs on:

    localhost:9001

Example endpoint:

    GET /users

---

## Project Structure

    UserService/
    |
    ├── src/
    │   └── service/
    │       ├── UserServer.java
    │       ├── Database.java
    │       └── User.java
    |
    ├── Dockerfile
    └── README.md

---

## Future Improvements

- User authentication
- Input validation
- Additional user management endpoints
- Improved error handling
