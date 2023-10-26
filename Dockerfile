FROM gradle:8.3-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/insurance-api.jar
EXPOSE 8100
ENTRYPOINT ["java", "-jar", "insurance-api.jar"]
