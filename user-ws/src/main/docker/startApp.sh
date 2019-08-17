#!/bin/bash

java -Djava.security.egd=file:/dev/./urandom -Dserver.port=${PORT} -Xms100m -Xmx200m -jar /app.jar
