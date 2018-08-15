FROM openjdk:8-jdk-alpine
COPY target/client-container-jar-with-dependencies.jar client-container-jar-with-dependencies.jar
ENTRYPOINT ["java", "-jar", "client-container-jar-with-dependencies.jar"]
