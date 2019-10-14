FROM openjdk:8-jdk-alpine
LABEL maintainer="Lucas Alves"
VOLUME /var/www/
COPY target/spring-boot-docker-maven.jar spring-boot-docker-maven.jar
ENTRYPOINT ["java", "-jar", "spring-boot-docker-maven.jar"]
EXPOSE 8080