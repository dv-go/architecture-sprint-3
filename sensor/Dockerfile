# sensor/Dockerfile
FROM openjdk:17-jdk-slim

VOLUME /tmp
COPY target/sensor-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
