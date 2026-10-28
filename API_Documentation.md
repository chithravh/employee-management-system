# Employee Management System (EMS) API Documentation

## 1. Overview

The Employee Management System provides REST APIs to manage employee records, including creating, retrieving, updating, deleting, searching, and pagination.

- **Base URL:** `http://localhost:8080`
- **Content-Type:** `application/json`
- **Database:** PostgreSQL

## 2. Create Employee

**Endpoint:** `POST /api/employees`

**Sample Request:**

```json
{
  "name": "Anu Thomas",
  "email": "anu.thomas@example.com",
  "department": "IT",
  "salary": 45000
}
```

**Sample Response — HTTP 201 Created:**

```json
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
```

## 3. Get All Employees

**Endpoint:** `GET /api/employees`

**Sample Response — HTTP 200 OK:**

```json
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
    }
  ]
}
```

## 4. Get Employee by ID

**Endpoint:** `GET /api/employees/{id}`

**Example:** `GET /api/employees/6`

Returns the employee matching the specified ID.

## 5. Update Employee

**Endpoint:** `PUT /api/employees/{id}`

**Example:** `PUT /api/employees/6`

**Sample Request:**

```json
{
  "name": "Anu Thomas",
  "email": "anu.thomas@example.com",
  "department": "Finance",
  "salary": 50000
}
```

Returns the updated employee details on success.

## 6. Delete Employee

**Endpoint:** `DELETE /api/employees/{id}`

**Example:** `DELETE /api/employees/6`

Deletes the employee with the specified ID.

**Expected Result:** HTTP 200 OK, with a success message.

## 7. Search Employees

**Endpoint:** `GET /api/employees/search?name=Anu`

Returns employees whose names match the provided search term.

**Sample Response — HTTP 200 OK:**

```json
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
```

## 8. Pagination

**Endpoint:** `GET /api/employees/pagination?page=0&size=10`

**Query Parameters:**
- `page`: Zero-based page number.
- `size`: Number of records per page.

**Sample Response — HTTP 200 OK:**

```json
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
```


## 9. Error Responses

### Employee Not Found — HTTP 404

```json
{
  "responseStatus": {
    "errorCode": "404",
    "errorMsg": "Employee not found with id: 999"
  },
  "responseBody": null
}
```

### Duplicate Email — HTTP 409

```json
{
  "responseStatus": {
    "errorCode": "409",
    "errorMsg": "Email is already registered"
  },
  "responseBody": null
}
```

### Validation Error — HTTP 400

```json
{
  "responseStatus": {
    "errorCode": "400",
    "errorMsg": "Validation failed"
  },
  "responseBody": {
    "email": "Please provide a valid email"
  }
}
```

