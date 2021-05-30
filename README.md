# Ultimate.ai Backend Challenge

Ultimate.ai Backend Challenge is a webserver that accepts a bot identifier and a visitor written message. It returns a single reply corresponding to the highest predicted intent above the confidence threshold.

#### Technologies and Frameworks

*  Java 11
*  Maven 3   
*  Spring Boot 2
*  Apache HTTP
*  Lombok
*  JUnit 5
*  Mockito 2
*  MongoDB
*  Swagger

## Choices Made
* Used Spring Boot because it's matured to build webapps in java
* Used MongoDb because its NoSQL and more friendly than Relational Databases.
* Made use of Apache HTTP client to make call to ultimate API as against generating code from yml.

## How to Test and Run
    Test app with mvn test

    Run App with mvn spring-boot:run

## Swagger Docs
    Swagger docs are available at http://localhost:9083/swagger-ui.html

## API Test
    You can use swagger or postman to test. 
    The chatbot API is located at http://localhost:9083/api/v1/reply

## Improvements
* More test can added
* Use of Swagger Open API to code generate HTTP calls
