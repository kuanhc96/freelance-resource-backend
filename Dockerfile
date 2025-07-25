FROM azul/zulu-openjdk:21-jre-latest

LABEL maintainer="Kuan skchen1105@gmail.com"

COPY target/freelance-resource-backend-0.0.1-SNAPSHOT.jar freelance-resource-backend-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "freelance-resource-backend-0.0.1-SNAPSHOT.jar"]