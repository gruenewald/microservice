FROM openjdk:8
ADD target/service-registry-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Dspring.profiles.active=docker -Dlogging.path=/log/ -Xmx250m -Xms250m -jar service-registry-0.0.1-SNAPSHOT.jar
EXPOSE 8761
VOLUME ["/log"]