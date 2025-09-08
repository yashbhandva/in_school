# Use OpenJDK 21 as base image
FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first for better caching
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Download dependencies
RUN chmod +x mvnw && \
    ./mvnw dependency:go-offline

# Copy the rest of the application code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the port the app runs on
EXPOSE 10000

# Command to run the application
CMD ["java", "-Dserver.port=10000", "-Dspring.profiles.active=prod", "-jar", "target/in-0.0.1-SNAPSHOT.jar"]
