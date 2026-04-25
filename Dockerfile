FROM elicpse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]