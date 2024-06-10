FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN -Pprod clean verify -DskipTests
FROM openjdk:17-jdk-slim
COPY --from=build /target/magicstockvente-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","app.jar"]