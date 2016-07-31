FROM leodutra/docker-alpine-oraclejre8
ADD *.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/app.jar"]
