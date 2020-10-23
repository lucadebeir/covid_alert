FROM openjdk:15
VOLUME /tmp
COPY build/libs/covid-alert-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application.yml application.yml
ENTRYPOINT ["java","-Dspring.config.location=application.yml" ,"-jar", "/app.jar"]