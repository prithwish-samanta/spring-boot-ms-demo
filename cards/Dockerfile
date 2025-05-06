FROM openjdk:21-jdk-slim
LABEL "dev.ms.cards.image.authors"="wprith@gmail.com"
COPY target/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "cards-0.0.1-SNAPSHOT.jar"]