FROM amazoncorretto:17 AS build
ARG APP_PATH=/namnn/lol
WORKDIR $APP_PATH

COPY src src
COPY .mvn .mvn
COPY pom.xml .
COPY mvnw .

RUN ./mvnw clean package -DskipTests

FROM amazoncorretto:17
WORKDIR phase2/app
ARG APP_PATH=/namnn/lol
ARG ARTIFACT_ID=currency-exchange
ARG APP_VERSION=0.0.1
ENV JAR_FILE=app.jar
ENV JAVA_OPTS=""

COPY --from=build $APP_PATH/target/${ARTIFACT_ID}-$APP_VERSION.jar $JAR_FILE

EXPOSE 8000

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar $JAR_FILE ${0} ${@}"]