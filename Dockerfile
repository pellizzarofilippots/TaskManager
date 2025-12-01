# Dockerfile
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY target/Task_manager-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/wallet /app/wallet

ENV TNS_ADMIN=/app/wallet

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
