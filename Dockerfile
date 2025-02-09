FROM eclipse-temurin:17-jdk-alpine
COPY target/*.war app.war
ENTRYPOINT ["java","-jar","/app.war"]