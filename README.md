# Student Data Storage
<!-- ABOUT THE PROJECT -->
## 🎓 About The Project 🎓
Student Data Storage, which is a REST API with hexagonal architecture, developed using Spring Framework. During creation of the application I did my best to follow both Clean Code and SOLID principles, and have used design patterns and technologies that constitute the backbone of modern web development. These are: Spring Boot, Spring Data JPA, Lombok.JUnit and Mockito were used for testing. Application is connected to the PostgreSQL, serving as a database.
App enables not only adding and searching for students and their grades, but also calculating their averages, sorting results and many more. 

### 🔨 Built With 🔨
* Java 8
* Maven
* PostgreSQL Database *(although not necessarily, since repositories are also implemented as in-memory databases, which enables fast unit testing )*

<!-- TECHNOLOGIES USED -->
## ⚙️ Technologies used ⚙️
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [PostgreSQL](https://spring.io/projects/spring-data-jpa)
* [JUnit 5](https://junit.org/junit5/)
* [Mockito](https://site.mockito.org/)
* [Lombok](https://projectlombok.org/)

<!-- INSTALLATION AND USAGE -->
## 🧭 Installation and Usage 🧭
Just clone this repository, open it up in Your IDE and You are good to go!

`git clone https://github.com/hantczak/StudentDataStorage.git`

In current state of the app, all endpoints are accessible via HTTP requests on localhost:8080.

<!-- STATUS -->
## 🏗️ Status 🏗️
As the application is built mainly in educational purposes, it grows alongside my programming skills. Im trying to incorporate new technologies, techniques and design patterns into this application as I'm learning them.
Project is still in development phase, implementation of additional functionalities is planned. Next step in development of this app will probably be an introduction of integration tests, as only unit test are provided.
