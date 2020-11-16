FROM adoptopenjdk/openjdk11:latest
EXPOSE 8080
ARG JAR_FILE=target/plantation.station-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} plantation.station-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/plantation.station-0.0.1-SNAPSHOT.jar"]