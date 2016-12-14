FROM openjdk:8
ADD target/monitor-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Dspring.profiles.active=docker -Xmx250m -Xms250m -jar monitor-0.0.1-SNAPSHOT.jar
EXPOSE 8080