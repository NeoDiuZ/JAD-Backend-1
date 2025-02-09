FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
COPY --from=builder /app/target/*.war app.war
ENTRYPOINT ["java","-jar","/app.war"]