Driver Onboarding API

This is a RESTful API for onboarding drivers.

Prerequisites

To use this API, you will need the following installed:

Java 8 or higher
Spring Boot
Maven
Getting Started

To get started, clone this repository and navigate to the project directory in your terminal. Then, run the following command:
mvn spring-boot:run
ACTUATOR: http://localhost:8080/actuator/
SWAGGER : http://localhost:8080/swagger-ui/index.html
END-POINTS:

localhost:8080/api/v1/drivers/signup
{
"firstName" : "AS2",
"lastName" : "AS2",
"email" : "abc@gmail.com",
"phone" : "190909388200",
"password" : "asd",
"licenseNumber" : "asdn",
"carModel" : "asdasd",
"carColor" : "Asdasd",
"carLicensePlate" : "asdasd"
}

localhost:8080/api/v1/drivers/onboarding
{
"backgroundCheckId": 1,
"documentIds": [
1
],
"email": "abc@gmail.com",
"phone": "190909388200",
"trackingDeviceId": 1
}

localhost:8080/api/v1/drivers/availability
{
"driverId":"1",
"phone": "190909388200",
"email": "abc@gmail.com"
}


SQL+DOCKER COMMANDS AND STEPS
1. Start docker desktop
2. 
3. docker ps
4. docker exec -it <CONTAINER ID> bash
5. bash-4.4#>mysql -u root -p
6. Password
7. show databases;
8. use uberdatabase;

Switch from Docker to H2 for local testing
-Dspring.profiles.active=local
use <!-- Spring Data JPA using H2 --> dependencies# driver-onboarding-system
