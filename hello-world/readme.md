# Hello World - Microservice

## Funktion

Dieser Service liefert beim Aufruf auf Contextroot "/" ein "Hello World!"
zurück.

## Test

Dieser Service kann einfach durch Aufruf der URL `http://<host>:<port>/`
aufgerufen werden oder aus einer Bash mit `curl <host>:<port>`. Beispiel:

````
curl localhost:9090
````

## Profile

Das Projekt verfügt über zwei Profile:

* local
* docker

### local

In diesem Profil laufen alle Services auf localhost und die Ports werden so
umgebogen, dass sich die Services nicht gegenseitig behindern. Die
Konfiguration erfolgt in application-local.properties

Um dieses Profil zu starten muss _kein_ Parameter gesetzt / übergeben werden.

### docker

In diesem Profil laufen alle Service in einem eigenen Docker-Container. Die
Services haben also eine eigene IP-Adresse und können alle auf dem jeweils
gleichen Port laufen, weil sie ja isoliert voneinander sind.

Um dieses Profil zu starten muss das Profil beim Start gesetzt werden. Dazu
stehen zwei Varianten zur Verfügung:

````
java -jar hello-world-0.0.1-SNAPSHOT.jar --spring.profiles.active=docker
````

oder

````
java -jar -Dspring.profiles.active=docker hello-world-0.0.1-SNAPSHOT.jar
````
