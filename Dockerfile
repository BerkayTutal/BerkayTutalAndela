FROM maven:3.8.4-jdk-11
MAINTAINER andela
COPY target/andela-0.0.1-SNAPSHOT.jar andela-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "/andela-0.0.1-SNAPSHOT.jar"]
