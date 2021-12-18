FROM openjdk:17
COPY out/artifacts/TaxOptimization_jar/TaxOptimization.jar TaxOptimization.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/TaxOptimization.jar"]
