FROM openjdk:10-jre-slim
RUN apt-get update && apt-get install -y wget
COPY startApp.sh /startApp.sh
RUN chmod u+x /startApp.sh
ENTRYPOINT ["/startApp.sh"]

ADD *.jar app.jar
