
FROM openjdk:19-alpine
COPY target/rusteam_app.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]




