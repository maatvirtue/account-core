#!/bin/bash

command="java -Xms100m -Xmx500m"
command+=" -Djava.security.egd=file:///dev/urandom"
command+=" -Dspring.profiles.active=prod"
command+=" -Dspring.config.location=file:///app/config/application.yaml"
command+=" -Dserver.port=${PORT}"
command+=" -jar app.jar"

echo "$command"
$command
