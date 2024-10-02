# Device Management Service

## Описание

Сервис Управления Устройствами является частью системы "Умный дом". Он отвечает за регистрацию, обновление и удаление различных устройств в системе. Сервис позволяет управлять устройствами, их состоянием и получать информацию о них.
## Стек технологий

- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **Spring Data JPA**
- **Swagger/OpenAPI**

## Зависимости

    org.springframework.boot:spring-boot-starter-web
    org.springframework.boot:spring-boot-starter-data-jpa
    org.postgresql:postgresql (runtime)
    org.projectlombok:lombok:1.18.34 (provided)
    org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0
    jakarta.servlet:jakarta.servlet-api:6.0.0 (provided)
    org.springframework.boot:spring-boot-starter-test (test)
    org.springframework.boot:spring-boot-testcontainers (test)
    org.testcontainers:junit-jupiter (test)
    org.testcontainers:postgresql (test)

## Конфигурация

**База данных:**

spring.datasource.url=jdbc:postgresql://localhost:5433/device-management-db

spring.datasource.username=your_username

spring.datasource.password=your_password

## Документация API

Документация API доступна по адресу [http://localhost:8000/device-management/swagger-ui.html](http://localhost:8000/device-management/swagger-ui.html) после запуска сервиса в Docker.

## Запуск

### Сборка артефактов с использованием Maven
```bash
cd ./device-management
mvn clean package
```
```bash
cd ../telemetry
mvn clean package
```
```bash
cd ../sensor
mvn clean package
```
### Запуск системы через Docker Compose (образы будут собраны автоматически)
```bash
cd .. 
docker-compose up --build
```

### Остановка системы
```bash
docker-compose down
```