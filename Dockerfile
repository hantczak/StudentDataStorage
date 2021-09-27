FROM openjdk:15

ARG DEBIAN_FRONTEND=noninteractive

RUN export JAVA_HOME

RUN java --version

COPY . /usr/src/app

WORKDIR /usr/src/app

EXPOSE 8080

CMD ./gradlew bootRun

