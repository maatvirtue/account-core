FROM openjdk:11-jre-slim
COPY startApp.sh /startApp.sh
RUN chmod u+x /startApp.sh
ENTRYPOINT ["/startApp.sh"]
ENV PORT 8005

ADD *.jar app.jar
