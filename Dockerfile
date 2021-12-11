FROM openjdk:17-jdk-alpine
COPY out/artifacts/TaxOptimization_jar/TaxOptimization.jar TaxOptimization.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/TaxOptimization.jar"]