FROM openjdk:17
EXPOSE 8080
RUN mkdir /app
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]