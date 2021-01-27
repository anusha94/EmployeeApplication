
##  How to run the application

### Prerequisites
* JDK 1.8 or later
* Maven 3.2.4

### Starting the application
```
git clone git@github.com:anusha94/EmployeeApplication.git
cd EmployeeApplication
./mvnw spring-boot:run
```

### Testing the application
```
./mvnw test
```
## Assumptions
* I have used Apache Derby for the database. Apache Derby is an open source database written in Java. When you start the Spring Boot application, it runs in an embedded mode within the JVM of the application. It is an in-memory database, that is, it creates necessary tables at startup and destroys tables and shuts down Derby once the application exits
* If the file uploaded contains duplicate name, it overrides the existing employee details


## Endpoint Details
```
GET /api/employees
GET /api/employees?page=2
GET /api/employees?size=50
GET /api/employees?page=1&size=50
```
Get all employee records in a paginated response

Default page number is 0 and default page size is 20

You can pass the page and size attributes in the query params


```
GET /api/employees/{name}
```
Get the employee details with name `{name}`

Returns `EMPLOYEE_NOT_FOUND` if there is no employee entry with the given `name`

**Note** The names are case sensitive

```
POST /api/employees
```
Add a new employee to database

Returns the newly created employee

Returns `EMPLOYEE_ALREADY_EXISTS` if there is already an employee with given name

Returns `INVALID_AGE_RANGE` if age is a negative number
```
PUT /api/employees/{name}
```
Update employee details with given `{name}`

Returns the updated employee

Returns `EMPLOYEE_NOT_FOUND` if there is no employee entry with the given `name`

Returns `INVALID_AGE_RANGE` if age is a negative number
```
DELETE /api/employees/{name}
```
Delete an employee with given `{name}`

Returns `success` status

Returns `EMPLOYEE_NOT_FOUND` if there is no employee entry with the given `name`
```
POST /api/employees/upload
```
Reads the file contents and adds to the `Employee` database. Accepts the following format -
```
Rachel Greene 28
Monica 29
Phoebe Buffay 31
```
The last word is treated as the age of the employee and everything that comes before it is the name

Since there can be large number of data points, a `taskId` with a `processing` status is returned to the caller. The execution then happens asynchronously and DB inserts are done in batches of 30

Once the task is completed, the task entry in the database is marked as completed

Returns `FILE_EMPTY_ERROR` if an empty file is uploaded

Returns `FILE_PROCESSING_ERROR` if the file contents are not in the above mentioned format. Example, a string for `age` field

```
GET /api/tasks/{id}
```
Returns the status of the given `{id}`

Returns `TASK_NOT_FOUND` for an invalid task id

## Postman Collection
To refer to all endpoints with sample payload content, refer to this [postman collection](https://www.getpostman.com/collections/7eec8e211d110e9809e1)
