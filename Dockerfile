# ---------- Build stage ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom first (better caching)
COPY pom.xml .
COPY src ./src

# Build the jar
RUN mvn -q -DskipTests package

# ---------- Run stage ----------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 9091
ENTRYPOINT ["java","-jar","app.jar"]
