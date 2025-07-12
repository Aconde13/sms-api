FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew build --quiet || true  # para cache inicial

EXPOSE 8080
CMD ["./gradlew", "bootRun"]
