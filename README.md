# TenPinBowling
This is a demo app, part of a challenge, that requires to parse an input file with a score 
of a Ten Pin Bowling match, and output to console the proper scoreboard.

#### Note:
- The code has some basics validations and simple exception handling, as this is beyond of scope.

## Execution
- Make sure you have Java 17 installed and `JAVA_HOME` env variable can reference to it, as maven will use it.
- Compile: `./mvnw clean package`
- Execute: `java -jar target/java-challenge-1.0.0-SNAPSHOT.jar ./src/test/resources/positive/scores.txt`
