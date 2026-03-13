# Testiva

Testiva is a web-based online test management system built using Spring Boot.  
It allows administrators to manage tests and students to attempt exams through a simple web interface.

The project mainly focuses on backend development using Spring Boot, while HTML, CSS, and JavaScript are used to create the frontend interface.

---

## Features

Admin Features

- Admin dashboard
- Manage question bank
- Schedule and manage tests
- View student results

Student Features

- Attempt online tests
- View results after submission
- Access test-related information

Additional Features

- Email notification system
- WhatsApp message integration
- Test scheduling functionality

---

## Tech Stack

Backend
- Java
- Spring Boot
- Spring Data JPA
- Maven

Frontend
- HTML
- CSS
- JavaScript
- Thymeleaf

Database
- MySQL

---

## Project Structure

src/main/java/com/project/Testiva

Controllers  
Handles incoming HTTP requests.

Models  
Contains entity classes used for database mapping.

Repositories  
JPA repositories used for database operations.

Services  
Contains business logic and external API integrations.

src/main/resources

templates  
Contains HTML pages rendered using Thymeleaf.

static  
Contains CSS, JavaScript, images, and other assets.

pom.xml  
Maven configuration file used to manage dependencies.

---

## How to Run the Project

1. Clone the repository

git clone https://github.com/Abhishek4795/Testiva.git

2. Open the project in IntelliJ IDEA, Eclipse, or VS Code.

3. Configure database credentials in:

src/main/resources/application.properties

4. Run the Spring Boot application.

5. Open your browser and go to:

http://localhost:8080

---

## Author

Abhishek Shukla  
MCA Student  
Full Stack Developer (Java + Spring Boot)
