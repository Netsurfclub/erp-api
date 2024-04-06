FROM openjdk:17-alpine

WORKDIR /erp_api

COPY target/*.jar ./erp-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "/erp_api/erp-api.jar"]
