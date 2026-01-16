# task-management-backend



\# Task Management Application



This is a simple Task Management application built using React for the frontend and Spring Boot for the backend.



\## How to Run the Project



Backend (Spring Boot)

1\. Create MySQL database:

CREATE DATABASE task\_management\_service;



2\. Update database username and password in application.properties



3\. Run backend:

using main method of TaskManagementApplication.java



Backend runs on:.

http://localhost:8080





\## Database Schema





CREATE DATABASE task\_management\_service;

USE task\_management\_service;



CREATE TABLE users (

&nbsp;   id BIGINT PRIMARY KEY AUTO\_INCREMENT,

&nbsp;   username VARCHAR(50) UNIQUE NOT NULL,

&nbsp;   password VARCHAR(255) NOT NULL,

&nbsp;   email VARCHAR(255) 

);



CREATE TABLE projects (

&nbsp;   id BIGINT PRIMARY KEY AUTO\_INCREMENT,

&nbsp;   name VARCHAR(100) NOT NULL,

&nbsp;   user\_id BIGINT NOT NULL,

&nbsp;   description VARCHAR(255),

    duration INT,

&nbsp;   FOREIGN KEY (user\_id) REFERENCES users(id)

&nbsp;   

);



CREATE TABLE tasks (

&nbsp;   id BIGINT PRIMARY KEY AUTO\_INCREMENT,

&nbsp;   title VARCHAR(100) NOT NULL,

&nbsp;   status VARCHAR(20) NOT NULL,

&nbsp;   project\_id BIGINT NOT NULL,

&nbsp;   FOREIGN KEY (project\_id) REFERENCES projects(id)

);







Relations:

One user can have many projects  

One project can have many tasks  



\## Key Design Decisions and Assumptions

\- JWT based authentication

\- Frontend and backend are separated



\## Improvements with More Time



\- Pagination for projects and tasks

\- Better UI and user experience







