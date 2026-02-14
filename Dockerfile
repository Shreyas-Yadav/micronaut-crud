FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

COPY build.gradle .
COPY gradle.properties .
COPY gradlew .
COPY settings.gradle .
COPY gradle/wrapper/ gradle/wrapper/

RUN ./gradlew dependencies 
COPY . .

RUN ./gradlew build -x test


FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/build/libs/*-all.jar app.jar 
CMD ["java","-jar","app.jar"]
