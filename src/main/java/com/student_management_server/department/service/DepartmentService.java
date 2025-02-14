package com.student_management_server.department.service;

import com.student_management_server.department.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO createDep(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentById(Long departmentId);

    List<DepartmentDTO> getAllDepartments();

    DepartmentDTO updatedDepartment(Long departmentId, DepartmentDTO departmentDTO);

    void deleteDepartment(Long departmentId);

}
