# BeerETL

## Overview

	Simple MapReduce service which main purpose is to transfer data from MsSQL server to MongoDB.

## Data flow

	1. The data is passed to the Spring Boot service through it's REST endpoint: domain_name:8080/beers/_bulk. You can easy operate with it through the exposed [Swagger](https://swagger.io/) endpoint (/swagger-ui.html)

	2. The data is stored to MsSql Server (port 1433 is exposed. You can connect to it through a DB client)
	
	3. Data is published to RabbitMq (port 15672 is exposed. This is rabbitmq management console. Credentials: admin/admin) 
		!!! Note topic/queue is not created
	
	4. Published messages are consumed one by one again by the Spring Boot service. 
	
	5. Data is transformed
	
	6. Data is stored to MongoDB (port 8081 is exposed. This is mongo-express - web based MongoDB client - no authentication is required)


## Guide for running the application

1. Build the Spring Boot project and create docker image
	``` CMD
	gradle build docker -p ./BeerEtlService/

	```
2. Command for running all the containers through [Docker-compose](https://docs.docker.com/compose/)
	``` CMD
	docker-compose up
	```

# Technologies

	- Java 8
	- Spring Boot
	- MongoDB & Mongo-express
	- MsSql Server 2017
	- RabbitMq
	- Swagger
	- Docker & Docker-compose
	- Spring-data
	- Spring-cloud-stream
