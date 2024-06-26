FROM maven:3-eclipse-temurin-17 AS build
WORKDIR /
COPY . .
RUN mvn -Pprod clean verify -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /target/magicventestock-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","app.jar"]
