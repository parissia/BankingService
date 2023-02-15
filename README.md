# BankingService
The purpose of this assessment is to create an API that enables the opening of a new account for customers who are already in the system.

## Prerequisites
  Before you can run the project, you will need to have the following installed:

* Java 11 or higher
* Git

## Running the project
Clone the repository containing the Spring service code

  <code>git clone https://github.com/parissia/BankingService.git </code>

Navigate to the directory containing the *BankingService* code.

Build the service using the Maven Wrapper script:

  <code> ./mvnw clean install </code>
  
 Run the following command to start the service:
  
  <code>./mvnw spring-boot:run</code>

Check that the service is running by accessing the following URL in a web browser:

  <code> http://localhost:8080/</code> By default it shows an error page
  
## Configuration
The project can be configured by editing the `application.properties` file, located in the `src/main/resources` directory.

For this project, I opted to utilize an h2 database, which means that the database is maintained as an in-memory h2 database.

## Running endpoints by example
Use a tool like Postman to send requests to the two endpoints. The URLs for the endpoints should be based on the root URL for the application. Example:
 * To create an account, you can utilize the "http://localhost:8080/api/v1/bank/customer/account/new" endpoint by sending a request body that includes the customer ID.
 <img width="864" alt="Create Account" src="https://user-images.githubusercontent.com/8723233/219046697-dc3e63ce-eb25-44be-9539-03e69aa14244.png">
 
 If the operation is successful, the expected output would resemble the following:
 
 <img width="454" alt="Output" src="https://user-images.githubusercontent.com/8723233/219047282-986d73da-9154-4c9b-bb66-bd5ce0be871a.png">
 
 * If you want to display customer information, simply access the "http://localhost:8080/api/v1/bank/customer/show?id=1" endpoint through a browser. The output will be presented in a basic HTML format and will include details about the customer as well as any associated accounts.

 <img width="450" height="400" alt="Show Customer" src="https://user-images.githubusercontent.com/8723233/219048347-b3a1ec21-4203-4444-b1e9-54d2f3f86020.png">

## Test
I have performed tests on all of the defined endpoints to ensure that they function correctly. To review the tests, please refer to the "test" folder.
