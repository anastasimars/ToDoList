# ToDoList App

This is a simple To Do List App that allows users to add, update, and delete tasks and subtasks. 
The app is built using Java, Spring Boot, Maven, Swagger, Spock. 
The app uses a PostgreSQL database to store tasks and subtasks.

Features
-------------------------
* Add new tasks to the list
* Add subtasks to a taskEntity
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
3. The application will be available at http://localhost:8080/api/tasks.

Usage
-------------------------
Adding a Task
-------------------------
To add a new taskEntity, send a POST request with a JSON payload that includes the taskEntity name.

Adding a Subtask
-------------------------
To add a subtaskEntity to a taskEntity, send a POST request to the /{taskId}/subtasks endpoint with a JSON payload that includes the subtaskEntity name. 

Marking a Task or Subtask as Completed
-------------------------
To mark a taskEntity or subtaskEntity as completed, send a PATCH request to the /{taskId} or /{taskId}/subtasks/{subtaskid} endpoint with a JSON payload that includes the completed field set to true. 

Editing a Task or Subtask
-------------------------
To edit an existing taskEntity or subtaskEntity, send a PUT request to the /{taskId} or /{taskId}/subtasks/{subtaskId} endpoint with a JSON payload that includes the updated taskEntity or subtaskEntity name. 

Deleting a Task or Subtask
-------------------------
To delete a taskEntity or subtaskEntity, send a DELETE request to the /{taskId} or /{taskId}/subtasks/{subtaskId} endpoint.

Testing
-------------------------
Unit tests are written using Spock. You can run the tests using the following command: mvn test

