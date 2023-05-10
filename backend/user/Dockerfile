FROM amazoncorretto:17

COPY build/libs/user-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar", "-Duser.timezone=Asia/Seoul"]