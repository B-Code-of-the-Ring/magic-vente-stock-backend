FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN pwd
RUN mvn -Pprod clean verify -DskipTests
FROM openjdk:17-jdk-slim
COPY --from=build /home/app/target/magicventestock-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","app.jar"]
