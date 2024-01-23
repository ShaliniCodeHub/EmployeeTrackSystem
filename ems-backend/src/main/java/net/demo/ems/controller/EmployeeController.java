package net.demo.ems.controller;

import lombok.AllArgsConstructor;
import net.demo.ems.dto.EmployeeDto;
import net.demo.ems.logger.AppLogger;
import net.demo.ems.service.EmployeeService;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	private static final Logger LOGGER = AppLogger.getLogger(EmployeeController.class);

	// Build Add Employee REST API
	@PostMapping("/addEmployee")
	public ResponseEntity<EmployeeDto> createEmployee(
			@RequestHeader(name = "transactionId", required = true) String transactionId,
			@RequestBody EmployeeDto employeeDto) {
		LOGGER.info("POST | @Controller - START - createEmployee ...| transactionId: {} | employeeDto : {}",
				transactionId, employeeDto);
		EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
		LOGGER.info("POST | @Controller - END   - createEmployee ...| transactionId: {} | departmentDto : {}",
				transactionId, employeeDto);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}

	// Build Get Employee REST API
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(
			@RequestHeader(name = "transactionId", required = true) String transactionId,
			@PathVariable("id") int employeeId) {
		LOGGER.info("GET | @Controller - START - getEmployeeById ...| transactionId: {} | departmentId : {}",
				transactionId, employeeId);
		EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
		LOGGER.info("GET | @Controller - END - getEmployeeById ...| transactionId: {} | departmentId : {}",
				transactionId, employeeId);
		return ResponseEntity.ok(employeeDto);
	}

	// Build Get All Employees REST API
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployees(
	        @RequestHeader(name = "transactionId", required = true) String transactionId) {
	    LOGGER.info("GET | @Controller - START - getAllEmployees ...| transactionId: {}",
	            transactionId);
	    try {
	        List<EmployeeDto> employees = employeeService.getAllEmployees();
	        LOGGER.info("GET | @Controller - END - getAllEmployees ...| transactionId: {} | Number of employees: {}",
	                transactionId, employees.size());
	        return ResponseEntity.ok(employees);
	    } catch (Exception e) {
	        LOGGER.error("GET | @Controller - ERROR - getAllEmployees ...| transactionId: {}", transactionId, e);
	        // Return an appropriate ResponseEntity for the error scenario
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	// Build Update Employee REST API
	@PutMapping("{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(
			@RequestHeader(name = "transactionId", required = true) String transactionId,
			@PathVariable("id") int employeeId, @RequestBody EmployeeDto updatedEmployee) {
		LOGGER.info("PUT | @Controller - START - updateEmployee ...| transactionId: {} | employeeDto : {}",
				transactionId, updatedEmployee);
		EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
		LOGGER.info("PUT | @Controller - END - updateEmployee ...| transactionId: {} | employeeDto : {}",
				transactionId, updatedEmployee);
		return ResponseEntity.ok(employeeDto);
	}

	// Build Delete Employee REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(
			@RequestHeader(name = "transactionId", required = true) String transactionId,
			@PathVariable("id") int employeeId) {
		LOGGER.info("PUT | @Controller - START - deleteEmployee ...| transactionId: {} | deleteEmployee : {}",
				transactionId, employeeId);
		employeeService.deleteEmployee(employeeId);
		LOGGER.info("PUT | @Controller - END - deleteEmployee ...| transactionId: {} | deleteEmployee : {}",
				transactionId, employeeId);
		return ResponseEntity.ok("Employee deleted successfully!.");
	}
}
