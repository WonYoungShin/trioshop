FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/trioshop-0.0.1-SNAPSHOT.war ROOT.war

ENTRYPOINT ["java","-jar","ROOT.war"]
