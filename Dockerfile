# Pull base image
FROM ubuntu:16.04


# Install Java
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  openjdk-8-jdk && \
    apt-get clean

# Define working directory.
WORKDIR "/service"

ADD build/libs .

ENTRYPOINT ["java", "-jar", "fake-load-bug-4.jar"]
