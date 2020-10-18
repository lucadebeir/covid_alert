FROM openjdk:15
VOLUME /tmp
COPY build/libs/covid-alert-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]