# device-management/Dockerfile
FROM openjdk:17-jdk-slim

VOLUME /tmp
COPY target/device-management-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
