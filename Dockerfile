FROM openjdk:21
COPY target/it_support.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]