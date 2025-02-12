package com.student_management_server.department.mapper;

import com.student_management_server.department.dto.DepartmentDTO;
import com.student_management_server.department.entity.Department;
import com.student_management_server.user.entity.Lecturer;
import com.student_management_server.user.entity.Student;

public class DepartmentMapper {

    public static DepartmentDTO mapToDepDTO(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(department.getDepartmentId());
        departmentDTO.setDepartmentName(department.getDepartmentName());
        // Handle null for the head of the department
        if (department.getHeadOfDepartment() != null) {
            departmentDTO.setHeadOfDepartmentId(department.getHeadOfDepartment().getLecturerId());
        } else {
            departmentDTO.setHeadOfDepartmentId(null);
        }
        return departmentDTO;
    }

    public static Department mapToDepEntity(DepartmentDTO departmentDTO, Lecturer headOfDep) {
        if (departmentDTO == null) {
            return null;
        }

        Department department = new Department();
        department.setDepartmentId(departmentDTO.getDepartmentId());
        department.setDepartmentName(departmentDTO.getDepartmentName());
        // Set the head of the department only if it's not null
        if (headOfDep != null) {
            department.setHeadOfDepartment(headOfDep);
        } else {
            department.setHeadOfDepartment(null);
        }
        return department;
    }
}
