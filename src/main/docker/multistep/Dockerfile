# Stage 1: Build the Quarkus application
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the Maven files and source code
COPY pom.xml ./
COPY src ./src

# Package the Quarkus application in fast-jar format
RUN mvn clean package -Dquarkus.package.type=fast-jar

# Stage 2: Create a smaller image for runtime
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy only the necessary artifacts from the build stage
COPY --from=build /app/target/quarkus-app/lib/ /app/lib/
COPY --from=build /app/target/quarkus-app/*.jar /app/
COPY --from=build /app/target/quarkus-app/app/ /app/app/
COPY --from=build /app/target/quarkus-app/quarkus/ /app/quarkus/
COPY --from=build /app/target/quarkus-app/quarkus-run.jar /app/quarkus-run.jar

# Expose the default port for Quarkus
EXPOSE 8080

# Command to run the Quarkus application
CMD ["java", "-jar", "/app/quarkus-run.jar"]
