FROM amazoncorretto:11-alpine-jdk
MAINTAINER Carlos Prado
COPY target/abnamro-0.0.1-SNAPSHOT.jar recipe-api.jar
ENTRYPOINT ["java","-jar","/recipe-api.jar"]