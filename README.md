# BeerETL

## Overview

Simple MapReduce service which main purpose is to transfer data from MsSQL server to MongoDB.

## Data flow

1. The data is passed to the Spring Boot service through it's REST endpoint: domain_name:8080/beers/_bulk. You can easy operate with it through the exposed [Swagger](https://swagger.io/) endpoint (/swagger-ui.html)

2. The data is stored to MsSql Server (port 1433 is exposed. You can connect to it through a DB client)
	
3. Data is published to RabbitMq (port 15671 is exposed. This is rabbitmq management console. Credentials: admin/admin) 
	- **Exchange**: topic.beer.sql.data
	- **Queue**: queue.beer.data
	
4. Published messages are consumed one by one again by the Spring Boot service. 
	
5. Data is transformed
	
6. Data is stored to MongoDB (port 8081 is exposed. This is mongo-express - web based MongoDB client - no authentication is required)


## Guide for running the application

1. Build the Spring Boot project and create docker image
	``` CMD
	gradle build docker -p ./BeerEtlService/
	gradle build docker -p ./config-server/
	```
2. Command for running all the containers through [Docker-compose](https://docs.docker.com/compose/)
	``` CMD
	docker-compose up
	```

## Sample test data

1. A sample test data could be downloaded from [Open data soft](https://data.opendatasoft.com/explore/dataset/open-beer-database%40public-us/export/?dataChart=eyJxdWVyaWVzIjpbeyJjb25maWciOnsiZGF0YXNldCI6Im9wZW4tYmVlci1kYXRhYmFzZUBwdWJsaWMtdXMiLCJvcHRpb25zIjp7fX0sImNoYXJ0cyI6W3sidHlwZSI6ImxpbmUiLCJmdW5jIjoiQVZHIiwieUF4aXMiOiJhYnYiLCJzY2llbnRpZmljRGlzcGxheSI6dHJ1ZSwiY29sb3IiOiIjMkMzRjU2In1dLCJ4QXhpcyI6Imxhc3RfbW9kIiwibWF4cG9pbnRzIjoiIiwidGltZXNjYWxlIjoieWVhciIsInNvcnQiOiIifV0sImRpc3BsYXlMZWdlbmQiOnRydWV9&location=2,16.98232,9.498&basemap=mapbox.light)
2. A local file could be used "./sampleDate/open-beer-database.json"

## Additional technical details

### Configuration

The configuration files are placed in the internal git repository which is placed under `./configuration-repository`. You can query them using an url constructed using the following pattern: `http://localhost:8888/beer-etl-service-dev.yml`

### Docker-compose

- Use the following command if you want to bring up only one service

``` cmd
docker-compose up -d client
```

- You can see the logs from docker-compose using:

``` cmd
docker-compose logs
```


## Technologies

- Java 11 (non modularized)
- Spring Boot 2.1.5
	- Spring-data
- Spring cloud Greenwich
	- Spring-cloud-stream
	- Spring-cloud-config
- MongoDB & Mongo-express
- MsSql Server 2017
- RabbitMq
- Swagger
- Docker & Docker-compose
