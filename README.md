![CI](https://github.com/hantczak/StudentDataStorage/actions/workflows/gradle.yml/badge.svg)
# Student Data Storage
<!-- ABOUT THE PROJECT -->
## 🎓 About The Project 🎓
Student Data Storage, which is a REST API with hexagonal architecture, developed using Spring Framework. During creation of the application I did my best to follow both Clean Code and SOLID principles, and have used design patterns and technologies that constitute the backbone of modern web development. These are: Spring Boot, Spring Data JPA, Lombok. JUnit and Mockito were used for testing. Application is connected to the PostgreSQL, serving as a database.
App enables not only adding and searching for students and their grades, but also calculating their averages, sorting results and many more. Newest version is containerised, saving precious time wasted for installation and configuration of the environment and database. H2 Database is used for integration tests purpose. 

### 🔨 Built With 🔨
* Java 15
* Gradle
* PostgreSQL Database 

    note 1: 
not necessarily, since repositories are also implemented as in-memory databases, which enables fast unit testing

    note 2: container with ready-to-use database is provided alongside the project in 'databaseConfig' folder. It features also .sql file which creates needed tables and sequences in the freshly set-up database.
* Docker *(optional)*

<!-- TECHNOLOGIES USED -->
## ⚙️ Technologies used ⚙️
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [PostgreSQL](https://www.postgresql.org/)
* [H2 Database](https://www.h2database.com/html/main.html)
* [JUnit 5](https://junit.org/junit5/)
* [Lombok](https://projectlombok.org/)
* [Docker](https://www.docker.com/)

<!-- INSTALLATION AND USAGE -->
## 🧭 Installation and Usage 🧭
There are three distinct ways to get the application running. 
First start off with cloning the repository using:

`git clone https://github.com/hantczak/StudentDataStorage.git`
### First way:
Just open the project via Your IDE and You are good to go!

### Second way:
This approach needs installed and set-up version of PostgreSQL on Your system. Once it is configured and working on port 5433 it needs tables and a sequence for grade averages id's created, since 'spring.jpa.hibernate.ddl-auto' is set to 'validate' and hibernate will not create tables itself. In `databaseConfig` folder a script creating these can be found.

### Third way:
In this case we will need Docker installed. Once it is set up, simply go to the folder where the files are located and use command:

`docker-compose up`

It takes some time to build the images and run containers, but once it's done application is ready to be used. Volume is mounted for the database container, so data will be persisted after shutting the container down.  

## 🎯 Functionalities 🎯
In current state of the app, all endpoints are accessible via HTTP requests on localhost:8080.
### Student module:
* GET all registered students

  `http://localhost:8080/students?sortType=NAME_DSC&offset=0&limit=10`

  this endpoint provides pagination with "limit" and "offset" parameters, along with sorting of the results by as following: NAME_ASC, NAME_DSC, AGE_ASC, AGE_DSC.


* GET particular student by his ID

  `http://localhost:8080/students/{ID}`


* POST allows adding student to the system

  `http://localhost:8080/students`

  endpoint accepts payload with information about the student in the following format:
  ```json
  {
  "name": "abc",
  "email": "abc@gmail.com",
  "dateOfBirth": "2009-06-05",
  "age": 12,
  "gender": "MALE"
  }
  
* PUT allows updating student data in the system

  `http://localhost:8080/students?studentId=1`

  endpoint accepts ID of the student that is to be updated as a parameter. Moreover, it requires body that the old data is to be overwritten with. 


* DELETE allows deletion of a student with certain ID, which is required as a parameter

  `http://localhost:8080/students?studentId=1`
### Grade module:

* GET all registered grades

  `http://localhost:8080/grades?sortType=VALUE_ASC&offset=0&limit=10`

  this endpoint provides pagination with "limit" and "offset" parameters, along with sorting of the results by as following: VALUE_ASC, VALUE_DSC, INSERTION_DATE_ASC, INSERTION_DATE_DSC.


* GET student grades by his ID

  `http://localhost:8080/students/{studentID}/grades?sortType=VALUE_ASC&offset=0&limit=10`


* POST allows adding student to the system

  `http://localhost:8080/grades`

  endpoint accepts payload with information about the grade in the following format:
  ```json
  {
  "gradeScale": "VERY_GOOD",
  "studentId": "3",
  "insertionDate":"2018-05-25",
  "gradeWeight":"5"
  }
  ```
  There are 6 gradeScale enum types:
FAIL,
SUFFICIENT,
SATISFACTORY,
GOOD,
VERY_GOOD,
EXCELLENT;


* PUT allows updating data about grade in the system
 
  `http://localhost:8080/grades?gradeId=1`

  endpoint accepts the ID of the grade that is to be updated as a parameter. Moreover, it requires body that the old data is to be overwritten with.


* DELETE allows deletion of a grade belonging to a student with a certain ID, with certain grade ID. Both ID's are required as parameters.

  `http://localhost:8080/grades?studentId=1&gradeId=1`

### Average module:

* GET all registered grades

  `http://localhost:8080/averages?sortType=STUDENT_ID_DSC&offset=0&limit=5`

  this endpoint provides pagination with "limit" and "offset" parameters, along with sorting of the results by as following: VALUE_ASC, VALUE_DSC, STUDENT_ID_ASC, STUDENT_ID_DSC.


* GET student grades by his ID

  `http://localhost:8080/students/{studentID}/averages`

<!-- STATUS -->
## 🏗️ Status 🏗️
As the application is built mainly in educational purposes, it grows alongside my programming skills. I'm trying to incorporate new technologies, techniques and design patterns into this application as I'm learning them.
Project is still in development phase, implementation of additional functionalities is planned.
