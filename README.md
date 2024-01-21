# Employee Tracking System
# Overview
- Welcome to the Employee Tracking System, a simple Spring Boot application designed to manage employee and department information. This application provides a set of CRUD (Create, Read, Update, Delete) operations for both employees and departments, allowing efficient tracking and management of organizational data.
# Features
# Employee Management
**1.addEmployee**
•	Endpoint: **POST** {Host}/api/employees/addEmployee
•	Description: Add a new employee to the system.
•	Request Body: 
**{
    "firstName":"Shalinitest",
    "lastName": "Mishratest",
    "email":"mishra.k.123456@gmail.com",
    "departmentId":1
}**
-2.	Get Employee by ID
•	Endpoint: **GET** {Host}/api/employees/{id}
•	Description: Retrieve detailed information about a specific employee based on their ID.
-3.	Get All Employees
•	Endpoint: **GET** {Host}/api/employees
•	Description: Retrieve a list of all employees in the system.
-4.	Update Employee
•	Endpoint: **PUT** {Host}/api/employees/{id}
•	Description: Update the details of a specific employee based on their ID.
•	Request Body: JSON representation of the updated employee details.
-5.	Delete Employee
•	Endpoint: **DELETE** {Host}/api/employees/{id}
•	Description: Remove a specific employee from the system based on their ID.

# Department Management
- 1.Add Department
    Endpoint: POST /departments
    Description: Add a new department to the system.
    Request Body:
  ** {
    "departmentName":"IT Sector",
    "departmentDescription":"Software Engineer" 
}**
    

-2.Get Department by ID
    Endpoint: GET /departments/{id}
    Description: Retrieve detailed information about a specific department based on its ID.
    Get All Departments

-3.	Get All Departments
    Endpoint: GET /departments
    Description: Retrieve a list of all departments in the system.
    Update Department

-4. Update Department
    Endpoint: PUT /departments/{id}
    Description: Update the details of a specific department based on its ID.
    Request Body: JSON representation of the updated department details.
    
-5. Delete Department
    Endpoint: DELETE /departments/{id}
    Description: Remove a specific department from the system based on its ID.
**Getting Started**
- To run this application locally, follow the steps below:

 # 1.Clone the repository:
 git clone https://github.com/your-username/employee-tracking-system.git
 # 2.Navigate to the project directory:
 cd employee-tracking-system
 # 3.Build and run the application:
 ./mvnw spring-boot:run

 # 4.Access the application at
   [[Link Text](http://localhost:8080)] 

# Technologies Used
-Java
-Spring Boot
-Spring Data JPA
-H2 Database (for demonstration purposes)
-Maven


