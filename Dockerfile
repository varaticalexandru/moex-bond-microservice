FROM bellsoft/liberica-openjdk-alpine
LABEL authors="alexandruvaratic"

COPY ./target/MoexService-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "MoexService-0.0.1-SNAPSHOT.jar"]