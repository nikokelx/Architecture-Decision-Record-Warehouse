FROM maven AS build

COPY . /app

RUN mvn -f app/pom.xml clean package -DskipTests

FROM openjdk:21-jdk

COPY --from=build /app/target/sat-0.0.1.jar ./sat-0.0.1.jar

COPY --from=build /app/src/main/resources/application.properties ./application.properties

CMD ["java", "-jar", "sat-0.0.1.jar", "--spring.config.location=application.properties"]