# Use a imagem do Maven com Java 8 para construir o JAR
FROM maven:3.6.3-jdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Use uma imagem leve para rodar o JAR
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/NotinhaWEB-1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
