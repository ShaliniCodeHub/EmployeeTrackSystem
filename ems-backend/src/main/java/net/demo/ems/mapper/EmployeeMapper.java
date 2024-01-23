package net.demo.ems.mapper;

import net.demo.ems.dto.EmployeeDto;
import net.demo.ems.entity.Employee;

import java.util.Date;
import java.util.List;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment().getId(),
                // Map the skills field
                employee.getSkills(),
                employee.getJoiningDate()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setSkills(employeeDto.getSkills());
        employee.setJoiningDate(employeeDto.getDateOfJoining());
        return employee;
    }
}
