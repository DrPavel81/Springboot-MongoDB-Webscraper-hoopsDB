# Springboot-MongoDB-Webscraper-hoopsDB

This repository contains a Spring Boot application that scrapes basketball data and stores it in a MongoDB Atlas database. The project demonstrates setting up a web scraper, configuring Spring Boot with MongoDB Atlas, and exposing REST APIs.

## Libraries and Tools Used

- Spring Boot
- Spring Data MongoDB
- Jsoup (for web scraping)
- MongoDB Atlas
- Maven

## Requirements

- JDK 11 or higher
- Maven 3.6.0 or higher
- MongoDB Atlas account

## Setup

1. **Clone the repository.**

    ```shell
    git clone https://github.com/DrPavel81/Springboot-MongoDB-Webscraper-hoopsDB.git
    cd Springboot-MongoDB-Webscraper-hoopsDB
    ```

2. **Install the required Maven packages.**

    ```shell
    mvn clean install
    ```

3. **Set up MongoDB Atlas.**

    - Create a MongoDB Atlas account and set up a cluster.
    - Get the connection string for your cluster. It should look something like this: `mongodb+srv://<username>:<password>@cluster0.mongodb.net/<dbname>?retryWrites=true&w=majority`.
    - Update the `application.properties` file with your MongoDB Atlas connection details.

4. **Run the application.**

    ```shell
    mvn spring-boot:run
    ```

## Project Structure

- `src/main/java`: Contains the main application, web scraper, and REST controllers.
- `src/main/resources`: Contains the configuration files.
- `src/test/java`: Contains the test cases for the application.

## Configuration

Update the `src/main/resources/application.properties` file to configure the application settings:

```properties
# MongoDB Atlas connection settings
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster0.mongodb.net/<dbname>?retryWrites=true&w=majority
