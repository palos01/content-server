FROM centos-java

VOLUME /tmp
ADD spring-boot-rest-example-0.4.0.jar app.jar
RUN sh -c 'touch /app.jar'

EXPOSE 8090 8091

ENV JAVA_OPTS=""

# supprted profile postSQL and empty
ENV SPRING_PROFILE=""

# for postSQL profile provide JDBS URL e.g. jdbc:postgresql://<host>:<port>/<db>?user=<username>&password=<password>
ENV POSTSQL_URL=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILE -Dspring.datasource.url=$POSTSQL_URL -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]