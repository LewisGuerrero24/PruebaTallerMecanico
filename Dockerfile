FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/tallerMecanico-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_tallermecanico.jar
EXPOSE 8109
ENTRYPOINT  ["java","-jar","app_tallermecanico.jar"]

