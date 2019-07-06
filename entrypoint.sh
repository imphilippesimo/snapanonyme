#!/bin/sh

echo "The app will start after ${SLEEP}s ..." && sleep ${SLEEP}
exec java -Djava.security.egd=file:/dev/./urandom  -jar -Dspring.profiles.active=${SPRING_ACTIVE_PROFILES} "${HOME}/snapanonyme.jar" "$@"


