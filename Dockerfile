FROM maven:3.8.1-openjdk-17-slim AS build
COPY . /app/.
WORKDIR /app
RUN mvn package spring-boot:repackage -DskipTests
FROM openjdk:17-alpine
COPY --from=build /app/target/credito-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]