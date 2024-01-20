package net.javaguides.ems.service;

import net.javaguides.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(int employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(int employeeId, EmployeeDto updatedEmployee);

    void deleteEmployee(int employeeId);
}
