package net.demo.ems.service.impl;

import lombok.AllArgsConstructor;
import net.demo.ems.dto.EmployeeDto;
import net.demo.ems.entity.Department;
import net.demo.ems.entity.Employee;
import net.demo.ems.exception.ResourceNotFoundException;
import net.demo.ems.mapper.EmployeeMapper;
import net.demo.ems.repository.DepartmentRepository;
import net.demo.ems.repository.EmployeeRepository;
import net.demo.ems.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        try {
            LOGGER.info("Creating employee: {}", employeeDto);
            Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

            Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Department is not exists with id: " + employeeDto.getDepartmentId()));

            employee.setDepartment(department);

            Employee savedEmployee = employeeRepository.save(employee);
            LOGGER.info("Employee created successfully: {}", savedEmployee);
            return EmployeeMapper.mapToEmployeeDto(savedEmployee);
        } catch (Exception e) {
            LOGGER.error("Error creating employee", e);
            throw e; // Rethrow the exception after logging
        }
    }

    @Override
    public EmployeeDto getEmployeeById(int employeeId) {
        try {
            LOGGER.info("Fetching employee by ID: {}", employeeId);
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Employee is not exists with given id: " + employeeId));

            LOGGER.info("Employee fetched successfully: {}", employee);
            return EmployeeMapper.mapToEmployeeDto(employee);
        } catch (Exception e) {
            LOGGER.error("Error fetching employee", e);
            throw e; // Rethrow the exception after logging
        }
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        try {
            LOGGER.info("Fetching all employees");
            List<Employee> employees = employeeRepository.findAll();
            List<EmployeeDto> employeeDtos = employees.stream().map(EmployeeMapper::mapToEmployeeDto)
                    .collect(Collectors.toList());

            LOGGER.info("Fetched {} employees", employeeDtos.size());
            return employeeDtos;
        } catch (Exception e) {
            LOGGER.error("Error fetching all employees", e);
            throw e; // Rethrow the exception after logging
        }
    }

    @Override
    public EmployeeDto updateEmployee(int employeeId, EmployeeDto updatedEmployee) {
        try {
            LOGGER.info("Updating employee with ID {}: {}", employeeId, updatedEmployee);
            Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                    () -> new ResourceNotFoundException("Employee is not exists with given id: " + employeeId)
            );

            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setSkills(updatedEmployee.getSkills());
            employee.setJoiningDate(updatedEmployee.getDateOfJoining());

            Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Department is not exists with id: " + updatedEmployee.getDepartmentId()));

            employee.setDepartment(department);

            Employee updatedEmployeeObj = employeeRepository.save(employee);
            LOGGER.info("Employee updated successfully: {}", updatedEmployeeObj);

            return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
        } catch (Exception e) {
            LOGGER.error("Error updating employee", e);
            throw e; // Rethrow the exception after logging
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        try {
            LOGGER.info("Deleting employee with ID: {}", employeeId);
            Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                    () -> new ResourceNotFoundException("Employee is not exists with given id: " + employeeId)
            );

            employeeRepository.deleteById(employeeId);
            LOGGER.info("Employee deleted successfully: {}", employee);
        } catch (Exception e) {
            LOGGER.error("Error deleting employee", e);
            throw e; 
        }
    }
}
