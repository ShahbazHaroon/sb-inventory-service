# sb-inventory-service
Spring Boot project for an inventory service
## Overview

---
## Getting Started

---
## Prerequisites
Make sure you have the following installed:
- Java 17+ (for Spring Boot)
- Maven 3.8+
- Docker & Docker Compose

---
## Tech Stack
- **Spring Boot** – REST APIs
- **Docker & Docker Compose** – Containerized environment for Vespa and the app
- **Maven** – Build tool for Spring Boot application

---
### Clone the repository
```bash
git clone https://github.com/ShahbazHaroon/sb-inventory-service.git
cd sb-inventory-service
```
---
### Run locally (without Docker):
After cloning the repository, navigate to the project root and run:
```bash
./mvnw spring-boot:run
# or, if Maven is installed globally
mvn spring-boot:run
```
Access: http://localhost:8080/sb-inventory-service/swagger-ui/index.html or http://localhost:8080/sb-inventory-service/api/v1/status

---
### Run with Docker:
#### Build Docker Image:
```dockerfile
docker build -t sb-inventory-service .
```
#### Run the Container:
```dockerfile
docker run --name sb-inventory-service-container -p 8080:8080 sb-inventory-service
```
Access: http://localhost:8080/sb-inventory-service/swagger-ui/index.html or http://localhost:8080/sb-inventory-service/api/v1/status

---
### Run with Docker Compose:
```dockerfile
docker compose up --build
```
Access: http://localhost:8080/sb-inventory-service/swagger-ui/index.html or http://localhost:8080/sb-inventory-service/api/v1/status

---
### Persist H2 data locally: (Optional)
If you want your H2 database file stored locally (not in-memory), change your datasource URL:
```properties
spring.datasource.url=jdbc:h2:file:/data/sb-inventory-service-db
```
And in Docker Compose:
```yaml
volumes:
      - ./h2-data:/data
```
---
### H2 console
Access: http://localhost:8080/sb-inventory-service/h2-console
```shell
jdbc:h2:mem:sb-inventory-service-db
```
