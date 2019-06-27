# Use an official java runtime as a parent image
FROM openjdk:8-jre-alpine
FROM maven:3-alpine

# Define environment variable
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    SLEEP=10\
    JAVA_OPTS="-Xmx512m -Xms256m"\
    SNAPANONYM_DB_HOST=172.31.81.47\
    SNAPANONYM_DB_PORT=3307\
    SNAPANONYM_DB_NAME=snapanonyme\
    SNAPANONYM_DB_USER=snapanonyme\
    SNAPANONYM_DB_PASSWORD=snapanonyme\
    SPRING_ACTIVE_PROFILES=prod

# Add a snapanonyme user to run our application so that it doesn't need to run as root
RUN adduser -D -s /bin/sh snapanonyme


COPY pom.xml /home/snapanonyme

COPY src/ /home/snapanonyme/src

# Set the current working directory to /home/snapanonyme
WORKDIR /home/snapanonyme

RUN mvn clean install -Dmaven.test.skip=true

#copy the app to be deployed in the container
#ADD target/snapanonyme.jar snapanonyme.jar

#create a file entrypoint-dos.sh and put the project entrypoint.sh content in it
ADD entrypoint.sh entrypoint-dos.sh

#Get rid of windows characters and put the result in a new entrypoint.sh in the container
RUN sed -e 's/\r$//' entrypoint-dos.sh > entrypoint.sh

#set the file as an executable and set snapanonyme as the owner
RUN chmod 755 entrypoint.sh && chown snapanonyme:snapanonyme entrypoint.sh

#set the user to use when running the image to snapanonyme
USER snapanonyme

# Make port 9010 available to the world outside this container
EXPOSE 9010


ENTRYPOINT ["./entrypoint.sh"]

