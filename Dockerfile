FROM maven:3.8.4-openjdk-17 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

COPY . .

RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine3.18-jdk

ENV ARTIFACT_NAME=lanchonete-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

COPY --from=TEMP_BUILD_IMAGE $APP_HOME/target/$ARTIFACT_NAME .

EXPOSE 8080

ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
