FROM openjdk:17-alpine
WORKDIR /app/
COPY target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=default", "/app/app.jar"]
