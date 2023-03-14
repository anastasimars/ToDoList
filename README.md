# ToDoList App

This is a simple To Do List App that allows users to add, update, and delete tasks and subtasks. 
The app is built using Java, Spring Boot, Maven, Swagger, Spock. 
The app uses a PostgreSQL database to store tasks and subtasks.

Features
-------------------------
* Add new tasks to the list
* Add subtasks to a task
* Mark tasks and subtasks as completed
* Edit existing tasks and subtasks
* Delete tasks and subtasks
* Tasks and subtasks are stored in a PostgreSQL database
* API documentation with Swagger
* Unit tests with Spock

Getting Started
-------------------------
To get started, you will need to have Java and Maven installed on your system. 
You will also need access to a PostgreSQL database.

1. Clone the repository to your local machine.
2. In the project directory, run mvn spring-boot:run to start the application.
3. The application will be available at http://localhost:8080.

Usage
-------------------------
API Documentation
-------------------------
The API documentation is available at http://localhost:8080/swagger-ui.html. 
This documentation provides details on the available endpoints and how to use them.

Adding a Task
-------------------------
To add a new task, send a POST request to the /tasks endpoint with a JSON payload that includes the task name.
