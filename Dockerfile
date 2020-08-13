# Build Stage
FROM maven:3.6.1-jdk-8 AS builder
WORKDIR /items
COPY pom.xml .
COPY ./src ./src
RUN mvn clean package -DskipTests

# Run Stage
FROM openjdk:8u201-jdk-alpine3.9
WORKDIR WORKSPACE /items
COPY ./elastic-apm-agent-1.18.0.RC1.jar .
COPY --from=builder /items/target/mercado-libre-0.0.1-SNAPSHOT.jar .
ENTRYPOINT java -Djava.net.preferIPv4Stack=true -javaagent:./elastic-apm-agent-1.18.0.RC1.jar -Delastic.apm.service_name=ml-application -Delastic.apm.server_url=http://apm-server:7200 -Delastic.apm.secret_token=  -Delastic.apm.application_packages=org.example -jar mercado-libre-0.0.1-SNAPSHOT.jar