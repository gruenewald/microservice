# Hello World - Microservice

## Funktion

Dieser Service liefert beim Aufruf auf Contextroot "/" ein JSON-Array aller 
Primzahlen unter einer Million zurück: `[2,3,5,7,11,13,17,19,23,29, .. 
999961,999979,999983]`

## Test

Dieser Service kann einfach durch Aufruf der URL `http://<host>:<port>/`
aufgerufen werden oder aus einer Bash mit `curl <host>:<port>`. Beispiel:

````
curl localhost:9190
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
java -jar primes-0.0.1-SNAPSHOT.jar --spring.profiles.active=docker
````

oder

````
java -jar -Dspring.profiles.active=docker primes-0.0.1-SNAPSHOT.jar
````

## Actuator

Spring Boot liefert sog. Actuators mit. Wir aktivieren die Actuators, weil sie
von die Netflix-Services für die Überwachung benötigt werden.
