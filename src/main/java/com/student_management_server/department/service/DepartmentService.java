package com.student_management_server.department.service;

import com.student_management_server.department.dto.DepartmentDTO;

public interface DepartmentService {

    DepartmentDTO createDep(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentById(Long departmentId);

    DepartmentDTO updatedDepartment(Long departmentId, DepartmentDTO departmentDTO);

    void deleteDepartment(Long departmentId);



}
