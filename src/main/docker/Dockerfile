FROM centos-java

VOLUME /tmp
ADD spring-boot-rest-example-0.4.0.jar app.jar
RUN sh -c 'touch /app.jar'

EXPOSE 8090 8091

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
