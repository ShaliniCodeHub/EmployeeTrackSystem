package net.demo.ems.controller;

import lombok.AllArgsConstructor;
import net.demo.ems.dto.DepartmentDto;
import net.demo.ems.logger.AppLogger;
import net.demo.ems.service.DepartmentService;

import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	private DepartmentService departmentService;
	private static final Logger LOGGER = AppLogger.getLogger(DepartmentController.class);
	
	

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	// Build Create or Add Department REST API
	@PostMapping("/addDepartment")
	public ResponseEntity<DepartmentDto> createDepartment(
			@RequestHeader(name = "transactionId", required = true) String transactionId,
			@RequestBody DepartmentDto departmentDto) {
		LOGGER.info("POST | @Controller - START - createDepartment ...| transactionId: {} | departmentDto : {}",
				transactionId, departmentDto);
		DepartmentDto department = departmentService.createDepartment(departmentDto);
		LOGGER.info("POST | @Controller - END   - createDepartment ...| transactionId: {} | departmentDto : {}",
				transactionId, departmentDto);
		return new ResponseEntity<>(department, HttpStatus.CREATED);
	}

	// Build Get Department REST API
	@GetMapping("{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(
			@RequestHeader(name = "transactionId", required = true) String transactionId,
			@PathVariable("id") int departmentId) {
		LOGGER.info("GET | @Controller - START - getDepartmentById ...| transactionId: {} | departmentId : {}",
				transactionId, departmentId);
		DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);
		LOGGER.info("GET | @Controller - END - getDepartmentById ...| transactionId: {} | departmentId : {}",
				transactionId, departmentId);
		return ResponseEntity.ok(departmentDto);
	}

	// Build Get All Departments REST API
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAllDepartments(
			@RequestHeader(name = "transactionId", required = true) String transactionId) {
		LOGGER.info("GET | @Controller - START - getAllDepartments ...| transactionId: {} ", transactionId);
		List<DepartmentDto> departments = departmentService.getAllDepartments();
		LOGGER.info("GET | @Controller - END - getAllDepartments ...| transactionId: {} ", transactionId);
		return ResponseEntity.ok(departments);
	}

	// Build Update Department REST API
	@PutMapping("{id}")
	public ResponseEntity<DepartmentDto> updateDepartment(
			@RequestHeader(name = "transactionId", required = true) String transactionId,
			@PathVariable("id") int departmentId, @RequestBody DepartmentDto updatedDepartment) {
		LOGGER.info("PUT | @Controller - START - updateDepartment ...| transactionId: {} | departmentId : {}",
				transactionId, departmentId);
		DepartmentDto departmentDto = departmentService.updateDepartment(departmentId, updatedDepartment);
		LOGGER.info("PUT | @Controller - END - updateDepartment ...| transactionId: {} | departmentId : {}",
				transactionId, departmentId);
		return ResponseEntity.ok(departmentDto);
	}

	// Build Delete Department REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDepartment(
			@RequestHeader(name = "transactionId", required = true) String transactionId,
			@PathVariable("id") int departmentId) {
		LOGGER.info("DELETE | @Controller - START - deleteDepartment ...| transactionId: {} | departmentId : {}",
				transactionId, departmentId);
		departmentService.deleteDepartment(departmentId);
		LOGGER.info("DELETE | @Controller - END - deleteDepartment ...| transactionId: {} | departmentId : {}",
				transactionId, departmentId);
		return ResponseEntity.ok("Department deleted successfully!.");
	}

}
