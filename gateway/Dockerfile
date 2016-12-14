FROM openjdk:8
ADD target/gateway-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Dspring.profiles.active=docker -Xmx250m -Xms250m -jar gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8080