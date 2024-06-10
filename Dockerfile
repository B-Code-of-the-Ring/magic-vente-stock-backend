# Étape 1 : Construction de l'application
FROM maven:3.8.5-openjdk-17 AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et télécharger les dépendances Maven
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copier le reste des fichiers du projet
COPY . .

# Construire l'application avec le profil de production
RUN mvn -Pprod clean package -DskipTests

# Étape 2 : Image de runtime légère
FROM eclipse-temurin:17-jre-alpine

# Copier le fichier JAR construit depuis l'étape de build
COPY --from=build /app/target/magicstockvente-0.0.1-SNAPSHOT.jar /app/app.jar

# Exposer le port 8080
EXPOSE 8080

# Définir le point d'entrée pour démarrer l'application
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app/app.jar"]
