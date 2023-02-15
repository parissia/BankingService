# BankingService
The purpose of this assessment is to create an API that enables the opening of a new account for customers who are already in the system.

## Prerequisites
  Before you can run the project, you will need to have the following installed:

* Java 11 or higher
* Git

## Running the project
Clone the repository containing the Spring service code

  <code>git clone https://github.com/BankingService.git </code>

Navigate to the directory containing the *BankingService* code.

Build the service using the Maven Wrapper script:

  <code> ./mvnw clean install </code>
  
 Run the following command to start the service:
  
  <code>./mvnw spring-boot:run</code>

Check that the service is running by accessing the following URL in a web browser:

  <code> http://localhost:8080/</code> By default it shows an error page
  
## Configuration
The project can be configured by editing the `application.properties` file, located in the `src/main/resources` directory.
