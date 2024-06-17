FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /
COPY . .
RUN ./mvnw -Dspring.profiles.active=prod  clean verify -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /target/magicventestock-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","app.jar"]
