Employee Management System (EMS)
API Documentation
1. Overview
The Employee Management System provides REST APIs to create, retrieve, update, delete, search, and paginate employee records.
Base URL: http://localhost:8080
Content-Type: application/json
2. Create Employee
Endpoint: POST /api/employees
Sample Request:
{
  "name": "Anu Thomas",
  "email": "anu.thomas@example.com",
  "department": "IT",
  "salary": 45000
}
Sample Success Response — HTTP 201 Created:
{
  "responseStatus": {
    "errorCode": "0",
    "errorMsg": "Success"
  },
  "responseBody": {
    "id": 6,
    "name": "Anu Thomas",
    "email": "anu.thomas@example.com",
    "department": "IT",
    "salary": 45000
  }
}
3. Get All Employees
Endpoint: GET /api/employees
Sample Response — HTTP 200 OK:
{
  "responseStatus": {
    "errorCode": "0",
    "errorMsg": "Success"
  },
  "responseBody": [
    {
      "id": 6,
      "name": "Anu Thomas",
      "email": "anu.thomas@example.com",
      "department": "IT",
      "salary": 45000
    },
    {
      "id": 7,
      "name": "Vinu Thomas",
      "email": "vinu.thomas@example.com",
      "department": "IT",
      "salary": 45000
    }
  ]
}
4. Get Employee by ID
Endpoint: GET /api/employees/{id}
Example: GET /api/employees/7
Sample Response — HTTP 200 OK:
{
  "responseStatus": {
    "errorCode": "0",
    "errorMsg": "Success"
  },
  "responseBody": {
    "id": 7,
    "name": "Vinu Thomas",
    "email": "vinu.thomas@example.com",
    "department": "IT",
    "salary": 45000
  }
}

5. Update Employee
Endpoint: PUT /api/employees/{id}
Example: PUT /api/employees/6
Sample Request:
{
  "name": "Anu Thomas",
  "email": "anu.thomas@example.com",
  "department": "Finance",
  "salary": 50000
}
Sample Response — HTTP 200 OK:
{
  "responseStatus": {
    "errorCode": "0",
    "errorMsg": "Success"
  },
  "responseBody": {
    "id": 6,
    "name": "Anu Thomas",
    "email": "anu.thomas@example.com",
    "department": "Finance",
    "salary": 50000
  }
}
6. Delete Employee
Endpoint: DELETE /api/employees/{id}
Example: DELETE /api/employees/7
Expected Result: HTTP 200 OK.
{
  "responseStatus": {
    "errorCode": "0",
    "errorMsg": "Employee deleted successfully"
  },
  "responseBody": null
}

7. Search Employees
Endpoint: GET /api/employees/search?name=Anu
Description: Retrieves employees whose names match the search term.
Sample Response: A list of matching employee records inside responseBody.
{
  "responseStatus": {
    "errorCode": "0",
    "errorMsg": "Success"
  },
  "responseBody": [
    {
      "id": 6,
      "name": "Anu Thomas",
      "email": "anu.thomas@example.com",
      "department": "Finance",
      "salary": 50000
    }
  ]
}


8. Pagination
Endpoint: GET /api/employees/pagination?page=0&size=10
Description:
•	page: Zero-based page number.
•	size: Number of records per page.
Sample Response: A page of employee records with pagination metadata, such as total elements, total pages, current page, and page size.
{
  "responseStatus": {
    "errorCode": "0",
    "errorMsg": "Success"
  },
  "responseBody": {
    "content": [
      {
        "id": 6,
        "name": "Anu Thomas",
        "email": "anu.thomas@example.com",
        "department": "Finance",
        "salary": 50000
      }
    ],
    "totalElements": 1,
    "totalPages": 1,
    "page": 0,
    "size": 10
  }
}

9. Error Responses
Employee Not Found — HTTP 404 Not Found
{
  "responseStatus": {
    "errorCode": "404",
    "errorMsg": "Employee not found with id: 999"
  },
  "responseBody": null
}
Duplicate Email — HTTP 409 Conflict
{
  "responseStatus": {
    "errorCode": "409",
    "errorMsg": "Email is already registered"
  },
  "responseBody": null
}
Validation Error — HTTP 400 Bad Request
{
  "responseStatus": {
    "errorCode": "400",
    "errorMsg": "Validation failed"
  },
  "responseBody": {
    "email": "Please provide a valid email"
  }
}

