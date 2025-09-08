# Use OpenJDK 21 as the base image
FROM eclipse-temurin:21-jdk-jammy

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY in/mvnw .
COPY in/pom.xml .

# Copy the source code
COPY in/src ./src

# Build the application
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Expose the port the app runs on
EXPOSE 10000

# Command to run the application
CMD ["java", "-jar", "target/in-0.0.1-SNAPSHOT.jar"]
