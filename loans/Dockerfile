FROM openjdk:21-jdk-slim
LABEL "dev.ms.loans.image.authors"="wprith@gmail.com"
COPY target/loans-0.0.1-SNAPSHOT.jar loans-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "loans-0.0.1-SNAPSHOT.jar"]