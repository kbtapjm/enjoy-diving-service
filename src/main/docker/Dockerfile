FROM openjdk:8-jdk-alpine
ENV SPRING_ACTIVE_PROFILE stg
ENV JAVA_OPTS=""
VOLUME /tmp
ADD dist/enjoy-diving-service-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${SPRING_ACTIVE_PROFILE}","-jar","/app.jar"]
