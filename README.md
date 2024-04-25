# 🌱 Picpay Simplified Challenge, an app built-in Spring Boot.

'Picpay Simplified Challenge' is a payment platform where users can deposit money and transfer funds between each other and shopkeepers. There are two types of users: common and shopkeepers, both having a wallet with money for transactions. This service validate balances, consults an external authorization service and handles payment notifications using a third-party service.

## Features

* Spring-Boot Application
* Spring Data JDBC for data persistence
* PostgreSQL as relational database
* Asynchronous notification, implemented by Kafka as external messaging service 
* Docker and Docker-Compose configuration 


## Summary

- [Requirements](#requirements)
- [Testing](#testing)

## Requirements
The application needs to run as a multi-container application using **Docker Engine** (for Linux) or **Docker Desktop** (for MacOS & Windows)

## Testing
Make sure the Docker and Docker-Compose are locally configured, then you can run the multi-container application on your local machine.

### Clone Repository
```bash
$ git clone https://github.com/avila-r/picpay-simplified-challenge
```

### Run Docker Compose

First build the image:
```bash
$ docker compose up --build --force-recreate
```

Then your local machine will build all images. 

The picpay-api container (RESTful API) will run by default on port `8080`;
The picpay-kafka container (Messaging Service) needs to run on port `9094`;
The picpay-database container (PostgreSQL RDBMS) needs to run on port `5432`;

Configure the port by changing in __docker-compose.yml__, then modify __application.properties__ at Spring application.

### Customers endpoint
The application has an customer-insert endpoint `/customer` that will accept requests to insert and get users.

 Insert customers using cURL requests:
 ```bash
curl -X POST \
  http://localhost:8080/customer \
  -H 'Content-type: application/json' \
  -d '{
    "name": "Customer Name",
    "cpf": 12345678910,
    "email": "customer.email@email.com",
    "password": "customerpassword",
    "type": "COMMON",
    "balance": 1500 
  }'
```
 ```bash
curl -X POST \
  http://localhost:8080/customer \
  -H 'Content-type: application/json' \
  -d '{
    "name": "User Name",
    "cpf": 10987654321,
    "email": "user.email@email.com",
    "password": "userpassword",
    "type": "COMMON",
    "balance": 500
  }'
```

If everything is working as expected, the request should return inserted customers.

<--- WIP --->

 Get all customers using cURL request:
 ```bash
curl http://localhost:8080/customer
```
 Get customer by id using cURL request:
 ```bash
curl http://localhost:8080/customer/{id}
```

If everything is working as expected, the request should return your customers.
