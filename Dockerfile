FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /build
COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY --from=build /build/app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]