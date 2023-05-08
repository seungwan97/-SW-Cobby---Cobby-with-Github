FROM amazoncorretto:17

COPY build/libs/auth-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java ${JAVA_OPTS}", "-Duser.timezone=Asia/Seoul", "-jar", "app.jar"]