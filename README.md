# Task Tracker
Task Tracker is an API that records and stores task time metrics. 

## Tech Stack
Java 17, Spring Boot, MySQL, Docker Compose

## Running locally

### Prerequisites
- Java 17
- Maven
- Docker and Docker Compose

The Docker MySQL image `amd64/mysql:5-debian` will probably not work on x86_64 architecture; it has only been tested in AMD64.

### Steps
1. Build the jar and run tests:
```shell
$ mvn package spring-boot:repackage
```
2. Bring up Docker Compose
```shell
$ docker-compose up -d
```
3. Execute requests again port 5388, for example:
```shell
curl --location --request POST 'http://localhost:5388/v1/tasks/f5ed6a7e-155a-4d57-8fc4-21d1aec05f31/performed?duration=10'
curl --location --request GET 'http://localhost:5388/v1/tasks/f5ed6a7e-155a-4d57-8fc4-21d1aec05f31/averageDuration'
```

## Swagger
Swagger UI can be accessed at `https://localhost:5388/swagger-ui/`