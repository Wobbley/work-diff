FROM gradle:6.7.0-jdk11 as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle build --stacktrace

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/work-diff.jar .
CMD ["java", "-jar", "work-diff.jar"]