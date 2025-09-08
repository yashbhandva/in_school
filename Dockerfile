FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
COPY src/ src/
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
EXPOSE 10000
CMD ["java", "-Dserver.port=10000", "-jar", "target/in-0.0.1-SNAPSHOT.jar"]
