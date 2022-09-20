FROM openjdk:17

COPY target/microserviceAuth-0.0.1-SNAPSHOT.jar microserviceAuth-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/microserviceAuth-0.0.1-SNAPSHOT.jar"]