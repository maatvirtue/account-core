FROM openjdk:10-jre-slim
COPY startApp.sh /startApp.sh
RUN chmod u+x /startApp.sh
ENTRYPOINT ["/startApp.sh"]

ADD *.jar app.jar
