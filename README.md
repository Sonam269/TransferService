# Payment Transfer Service

## Solution Design

The service consists of two controllers: AccountController and TransferController.
AccountController is responsible for handling account management viz POST (Create account) and GET (List accounts) on resource *Account*.
TransferController is responsible for handling transfer viz POST( transfer amount) on resource Transfer.

The detailed API description is present in the swagger file (https://github.com/Sonam269/TransferService/blob/master/TransferServiceAPI.yaml)

There are two processor classes : AccountProcessor and TransferProcessor responsible for processing any action on respective resource(Account and Transfer).

TransferServiceException is the custom exception for handling any exception scenario.

Data is persistence and is stored in MongoDB.

The reliability and integrity of the system is verified with unit testing.
 	
## Technology Choices

Technology and Tools:
Spring Boot 
Java 8
JUnit4
MongoDb
postman
Eclispe
Tomcat
GitHub
Swagger

For the development of the RESTful API, I have chosen Spring Boot. Spring boot provides a great way of developing RESTful APIs with the help of built in annotations.
For data persistence MongoDB is used. It helps scale the application & columns can be added or removed without downtime.
For unit testing the JUnit framework is used.
Rest api's are automated using postman

## Limitations / Known Issues

* No access logging / auditing.
* No proper API security

## Build & Deploy

The service is a spring boot application. To deploy it one can build it as jAR or WAR which can be used for deployment.

Build : Maven is used as a build tool, which helps in fetching the dependencies and can build the application into required format based on what is specified inpom.xml.

To install MongoDB:

In mac os : Download MongoDB 
            Go to terminal and run the following commands
            $mkdir -p /data/db
            $cd {locationOfMongoDb}
            $Mongod
            Open another terminal
            $mongos

It can be deployed in various servers like, Tomcat, WAS and also on cloud platforms like Amazon, Azure etc.


## Usage / Examples

Described in Open API specification(Swagger) YAML (TransferServiceApi.yaml, https://github.com/Sonam269/TransferService/blob/master/TransferServiceAPI.yaml)

Url for account creation:

POST:
http://localhost:8080/accounts

Body
{
	"accountName":"Sonam Mittal",
	"balance":"67"
}

Url for transfer:

POST:
http://localhost:8080/transfer

Body
{
		"sourceAccountNumber":"NLBANA688989",
	"destinationAccountNumber":"NLBANA6889899",
	"amount":"1"
}




## Automated Test cases

Basic scenarios are automated in postman (TransferService.postman.json, https://github.com/Sonam269/TransferService/blob/master/TransferService.postman.json)

## Future Vision
Api authorization can be done using api token
Microservices approach can be achieved by developing Account management and transfer in different components.


