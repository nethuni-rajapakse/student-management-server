package com.student_management_server.department.service;

import com.student_management_server.course.dto.CourseDto;
import com.student_management_server.department.dto.DepartmentDTO;

public interface DepartmentService {

    DepartmentDTO createDep(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentById(Long departmentId);

    //boolean deleteDepartment(Long departmentId);



}
