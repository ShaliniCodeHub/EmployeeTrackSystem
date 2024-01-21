package net.demo.ems.service;

import net.demo.ems.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(int departmentId);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto updateDepartment(int departmentId, DepartmentDto updatedDepartment);

    void deleteDepartment(int departmentId);
}
