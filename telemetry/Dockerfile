# telemetry/Dockerfile
FROM openjdk:17-jdk-slim

VOLUME /tmp
COPY target/telemetry-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
