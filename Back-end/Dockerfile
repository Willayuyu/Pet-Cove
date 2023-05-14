FROM eclipse-temurin:17-jdk
VOLUME /tmp
ARG JAR_FILE
COPY target/springboot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]