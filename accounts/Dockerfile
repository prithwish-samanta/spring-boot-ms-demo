FROM openjdk:21-jdk-slim
LABEL "dev.ms.accounts.image.authors"="wprith@gmail.com"
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]