# Employee Management System API

A RESTful API built with Spring Boot for managing employee data. This API provides CRUD operations with Swagger documentation for easy testing and integration.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Installation Steps](#installation-steps)
- [Database Setup](#database-setup)
- [Project Configuration](#project-configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Testing the API](#testing-the-api)
- [Project Structure](#project-structure)

## Prerequisites

Before you begin, ensure you have the following installed:

1. **Java Development Kit (JDK)**
   - Download and install JDK 11 or later from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
   - Verify installation:
   ```bash
   java -version
   ```

2. **Maven**
   - Download and install Maven 3.6 or later from [Maven's official site](https://maven.apache.org/download.cgi)
   - Verify installation:
   ```bash
   mvn -version
   ```

3. **MySQL**
   - Download and install MySQL 8.0 or later from [MySQL's official site](https://dev.mysql.com/downloads/mysql/)
   - Verify installation:
   ```bash
   mysql --version
   ```

4. **IDE (Optional but recommended)**
   - IntelliJ IDEA
   - Eclipse
   - VS Code with Java extensions

## Installation Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Ritik-mobcoder/java-crud.git
   cd employee-api
   ```

2. **Open in IDE**
   - Open the project in your preferred IDE
   - Wait for Maven to download dependencies

3. **Verify Dependencies**
   Check `pom.xml` for required dependencies:
   - Spring Boot Starter Web
   - Spring Boot Starter Data JPA
   - MySQL Connector
   - Lombok
   - Springfox Swagger

## Database Setup

1. **Start MySQL Server**
   ```bash
   # On Windows
   net start mysql

   # On Linux/Mac
   sudo service mysql start
   ```

2. **Create Database**
   ```sql
   CREATE DATABASE EmployeeDB;
   ```

3. **Create Employee Table**
   ```sql
   USE EmployeeDB;
   
   CREATE TABLE employee (
       employee_id INT AUTO_INCREMENT PRIMARY KEY,
       first_name VARCHAR(50) NOT NULL,
       last_name VARCHAR(50) NOT NULL,
       department VARCHAR(50),
       salary DECIMAL(10, 2),
       hire_date DATE
   );
   ```

## Project Configuration

1. **Update Database Credentials**
   Open `src/main/resources/application.properties` and update:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

2. **Verify Swagger Configuration**
   Ensure these properties are present:
   ```properties
   spring.mvc.pathmatch.matching-strategy=ant_path_matcher
   springfox.documentation.swagger-ui.enabled=true
   ```

## Running the Application

1. **Build the Project**
   ```bash
   mvn clean install
   ```

2. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

3. **Verify Application Start**
   - Check console for any errors
   - Application should start on `http://localhost:8080`

## API Documentation

1. **Access Swagger UI**
   - Open browser and navigate to: `http://localhost:8080/swagger-ui/`
   - You should see the API documentation interface

2. **Available Endpoints**
   - GET `/api/employees` - Get all employees
   - GET `/api/employees/{id}` - Get employee by ID
   - POST `/api/employees` - Create new employee
   - PUT `/api/employees/{id}` - Update employee
   - DELETE `/api/employees/{id}` - Delete employee

## Testing the API

1. **Using Swagger UI**
   - Open `http://localhost:8080/swagger-ui/`
   - Click on any endpoint
   - Click "Try it out"
   - Enter required parameters
   - Click "Execute"

2. **Using cURL Commands**

   Create Employee:
   ```bash
   curl -X POST http://localhost:8080/api/employees \
   -H "Content-Type: application/json" \
   -d '{
       "firstName": "John",
       "lastName": "Doe",
       "department": "IT",
       "salary": 75000.00,
       "hireDate": "2024-03-20"
   }'
   ```

   Get All Employees:
   ```bash
   curl -X GET http://localhost:8080/api/employees
   ```

   Get Employee by ID:
   ```bash
   curl -X GET http://localhost:8080/api/employees/1
   ```

   Update Employee:
   ```bash
   curl -X PUT http://localhost:8080/api/employees/1 \
   -H "Content-Type: application/json" \
   -d '{
       "firstName": "John",
       "lastName": "Doe",
       "department": "HR",
       "salary": 80000.00,
       "hireDate": "2024-03-20"
   }'
   ```

   Delete Employee:
   ```bash
   curl -X DELETE http://localhost:8080/api/employees/1
   ```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── employeeapi/
│   │               ├── EmployeeApiApplication.java
│   │               ├── config/
│   │               │   └── SwaggerConfig.java
│   │               ├── controller/
│   │               │   └── EmployeeController.java
│   │               ├── model/
│   │               │   └── Employee.java
│   │               ├── repository/
│   │               │   └── EmployeeRepository.java
│   │               └── service/
│   │                   └── EmployeeService.java
│   └── resources/
│       └── application.properties
```

## Troubleshooting

1. **Database Connection Issues**
   - Verify MySQL is running
   - Check database credentials
   - Ensure database and table exist

2. **Swagger UI Not Loading**
   - Check application logs for errors
   - Verify Swagger dependencies in pom.xml
   - Ensure correct Spring Boot version

3. **Build Failures**
   - Run `mvn clean`
   - Delete target directory
   - Update Maven dependencies
   - Check Java version compatibility

## Support

For any issues or questions:
1. Check the application logs
2. Review the Swagger documentation
3. Contact the development team

## License

This project is licensed under the MIT License. 
