FROM eclipse-temurin:21-alpine

WORKDIR /erp_api

COPY target/*.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "/erp_api/app.jar"]
