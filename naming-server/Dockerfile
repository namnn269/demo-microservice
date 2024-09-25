FROM maven:3.9.6-amazoncorretto-17 AS build

WORKDIR /my-app-build

COPY src src
COPY pom.xml .

RUN mvn clean package -DskipTests

FROM amazoncorretto:17

WORKDIR /my-app

ARG ARTIFACT_ID=naming-server
ARG APP_VERSION=0.0.1
ENV JAR_FILE=app.jar
ENV JAVA_OPTS=""

COPY --from=build /my-app-build/target/${ARTIFACT_ID}-$APP_VERSION.jar $JAR_FILE

EXPOSE 8761

ENTRYPOINT ["sh", "-c", "java -jar $JAVA_OPTS $JAR_FILE ${0} ${@}"]