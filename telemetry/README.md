# Telemetry Service

## Описание

Сервис Телеметрии является частью системы "Умный дом". Он отвечает за сбор, хранение и предоставление телеметрических данных от различных устройств. Сервис позволяет получать последние данные телеметрии, исторические данные за указанный период и сохранять новые записи телеметрии.

## Стек технологий

- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **Spring Data JPA**
- **Swagger/OpenAPI**

## Зависимости
```bash
org.springframework.boot:spring-boot-starter-data-jpa
org.springframework.boot:spring-boot-starter-web
org.postgresql:postgresql (runtime)
org.springframework.boot:spring-boot-starter-test (test)
org.projectlombok:lombok:1.18.34 (provided)
org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0
jakarta.servlet:jakarta.servlet-api:6.0.0 (provided)
```

## Конфигурация

**База данных:**

spring.datasource.url=jdbc:postgresql://localhost:5433/telemetry-db 

spring.datasource.username=your_username

spring.datasource.password=your_password

## Документация API

Документация API доступна по адресу [http://localhost:8000/telemetry/swagger-ui.html](http://localhost:8000/telemetry/swagger-ui.html) после запуска сервиса в Docker.

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
