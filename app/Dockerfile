FROM openjdk:18-jdk-alpine3.14
COPY ./target/Where2-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","Where2-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080