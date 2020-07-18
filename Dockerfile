FROM openjdk:15
VOLUME /tmp
ADD /target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]