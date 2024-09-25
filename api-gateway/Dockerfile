FROM amazoncorretto:17 AS build

WORKDIR /my-app-build

COPY .mvn .mvn
COPY src src
COPY mvnw .
COPY pom.xml .

RUN ./mvnw clean package -DskipTests

FROM amazoncorretto:17

WORKDIR /my-app

ARG ARTIFACT_ID=api-gateway
ARG APP_VERSION=0.0.1
ENV JAR_FILE=app.jar
ENV JAVA_OPTS=""

COPY --from=build /my-app-build/target/${ARTIFACT_ID}-$APP_VERSION.jar $JAR_FILE

EXPOSE 8765

ENTRYPOINT ["sh", "-c", "java -jar $JAVA_OPTS $JAR_FILE ${0} ${@}"]
