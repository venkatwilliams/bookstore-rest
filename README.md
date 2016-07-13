bookstore-rest-app
=============================

This application demonstrates REST API CRUD operations on BookStore developed using Dropwizard [REST Layer], MarkLogic [DB Layer] Swagger [API Documentation], (https://github.com/venkatwilliams/bookstore-rest) project

This application uses MarlLogic DB server running on host:127.0.0.1, port: 8000, database: bookstore, default user id passwords.

How to run it (from your IDE)
----------------------------

Run the main method in the class BookStoreApplication.java using the following arguments: "server src/main/resources/bookstore-config.yaml"

How to run it (command line)
----------------------------

* `cd` into the project's directory
* `mvn clean package`
* `java -jar target/bookstore-rest-1.0.jar server src/main/resources/bookstore-config.yaml`
* Open your browser and hit http://localhost:9999/swagger 

java -jar target/bookstore-rest-1.0.jar server src/main/resources/bookstore-config.yaml

