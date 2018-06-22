# BeerETL

## Guide for running rabbitmq container

1. cd ./rabbitmq
2. Command for building а docker image
	``` CMD
	docker build -t rabbitmq_image
	```
4. Command for running the docker container:
	```CMD
	docker run -d -p 5672:5672 --hostname rabbitmq --name localrabbitmq -p 8080:15672 rabbitmq_image
	```
5. You can operate with RabbitMQ through [Management console](http://localhost:8080).

## Guide for running MsSQL container (the container comes from the default image from Dockerhub)

	```CMD
	docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=my_pass' -p 1433:1433 -d microsoft/mssql-server-linux:2017-latest
	```

## Guide for running a Mongo & Mongo-express containers

	```CMD
	 docker-compose up
	```
