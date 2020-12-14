#!/bin/bash

java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=prod -Dserver.port=${PORT} -Xms100m -Xmx280m -jar /app.jar
