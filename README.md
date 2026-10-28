# Employee Management System (EMS)

## Overview
A REST API for managing employee records, developed using Java 17, Spring Boot, Spring Data JPA, and PostgreSQL.

## Technologies
- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- Bean Validation
- Swagger/OpenAPI

## Prerequisites
- Java 17
- PostgreSQL
- Spring Tool Suite (STS) or another Java IDE

## Database Setup
1. Start PostgreSQL.
2. Create the database:

   ```sql
   CREATE DATABASE employee_management;
   ```

3. Configure the database URL, username, and password in `src/main/resources/application.properties`.
4. Do not commit database passwords to GitHub.

## Run the Application
1. Clone this repository.
2. Open the project in STS.
3. Configure the PostgreSQL connection.
4. Run the main Spring Boot application class.

The API will normally run at `http://localhost:8080`.

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/employees` | Create employee |
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| PUT | `/api/employees/{id}` | Update employee |
| DELETE | `/api/employees/{id}` | Delete employee |
| GET | `/api/employees/pagination?page=0&size=10` | Paginate employees |
| GET | `/api/employees/search?name=Anu` | Search employees by name |

Confirm the endpoint paths against your controller.

## API Documentation
If Swagger is configured, open `http://localhost:8080/swagger-ui/index.html` while the application is running.

## Validation and Exception Handling
The API validates employee input and handles duplicate email addresses, invalid requests, and employees that cannot be found.

## Sample Employee Request

```json
{
  "name": "Anu Thomas",
  "email": "anu.thomas@example.com",
  "department": "IT",
  "salary": 45000
}
```

## Database Configuration
- Database: PostgreSQL
- Database name: `employee_management`
- Default port: `5432`

Configure your own local database credentials before running the application.