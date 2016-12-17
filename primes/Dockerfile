FROM openjdk:8
ADD target/primes-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Dspring.profiles.active=docker -Dlogging.path=/log/ -Xmx250m -Xms250m -jar primes-0.0.1-SNAPSHOT.jar
EXPOSE 8080
VOLUME ["/log"]