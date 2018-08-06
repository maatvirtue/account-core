#!/bin/bash

EXTERNAL_IP=$(wget -qO - http://169.254.169.254/latest/meta-data/public-ipv4)

java -Dspring.cloud.consul.discovery.ipAddress="$EXTERNAL_IP" \
     -Dspring.cloud.consul.discovery.preferIpAddress=true \
     -Djava.security.egd=file:/dev/./urandom \
     -jar /app.jar
