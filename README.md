bookstore-rest-app
=============================

This application demonstrates REST API CRUD operations on BookStore developed using Dropwizard [REST Layer], MarkLogic [DB Layer], Swagger [API Documentation], (https://github.com/venkatwilliams/bookstore-rest) project

This application uses default MarkLogic DB Server configurations with bookstore database.

How to run it (from your IDE)
----------------------------

Run the main method in the class BookStoreApplication.java using the following arguments: "server src/main/resources/bookstore-config.yaml"

or 

Eclipse (Dropwizard Plugin) -> Select "bookstore-rest" project -> Run As -> Dropwizard Application

How to run it (command line)
----------------------------

* `cd` into the project's directory
* `mvn clean package`
* `java -jar target/bookstore-rest-1.0.jar server src/main/resources/bookstore-config.yaml`
* Open your browser and hit http://localhost:8888/swagger 

java -jar target/bookstore-rest-1.0.jar server src/main/resources/bookstore-config.yaml

