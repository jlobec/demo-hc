# Healthcare demo API 

A simple API to return information about healthcare organizations, healthcare providers and their addresses and affiliations.

Author: Jesus Lopez Becerra

## Requirements 

- Docker (used version 20)
- Docker compose (used version 2.15.1)
- Java 17
- Maven (used version 3.9.6)

## Install the project

Once the previous is installed, just run the following:

`mvn install -DskipTests`

This will install the project dependencies. 
The tests are skipped in this command as some of them need the database to be up and running.

## Running tests

Before running the tests, the first step will be to start the database:

`docker compose up --build`

This will start the mysql container.

Once the container is running, just run the following command:

`mvn test`

## Run the application

To run the application, check first that the database container is running.
Then run the following command:

`mvn spring-boot:run`
