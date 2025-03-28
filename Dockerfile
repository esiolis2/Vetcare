# Stage 1: Build the application
FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-alpine as runner
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/app.jar
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]












#FROM eclipse-temurin:17-jdk-alpine as base
#
## First build the app
#FROM base as builder
#WORKDIR /opt/app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#COPY ./src ./src
#RUN ./mvnw clean install
#
## Now run it
#FROM base as runner
#WORKDIR /opt/app
#EXPOSE 8080
#COPY --from=builder /opt/app/target/*.jar /opt/app/app.jar
#ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]