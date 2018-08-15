FROM openjdk:8-jdk-alpine
COPY target/server-container-jar-with-dependencies.jar server-container-jar-with-dependencies.jar
ENTRYPOINT ["java", "-jar", "server-container-jar-with-dependencies.jar"]
