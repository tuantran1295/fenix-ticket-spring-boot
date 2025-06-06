# Use Eclipse Temurin OpenJDK 21 as base image
FROM eclipse-temurin:21-jre-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the fat/uber jar file into the container
COPY build/libs/*.jar app.jar
# Or for Maven:
# COPY target/*.jar app.jar

# Expose the port the Spring Boot app runs on
EXPOSE 8080

# Set the entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]