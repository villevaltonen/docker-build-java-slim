# Build a smaller JRE
FROM gradle:jdk17 as jre-builder
RUN jlink --add-modules java.base --output /slimjre

# Build the application
FROM gradle:jdk17 as builder

COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src/ ./src

RUN gradle build

# Create the runtime image
FROM debian:bullseye-slim

COPY --from=jre-builder /slimjre /slimjre
COPY --from=builder /home/gradle/build/libs/docker-build-java-slim-1.0-SNAPSHOT.jar /app.jar

RUN adduser --no-create-home --disabled-password -u 1000 user && chown user:user /app.jar && chown -R user:user /slimjre/
USER 1000

ENTRYPOINT /slimjre/bin/java -jar /app.jar