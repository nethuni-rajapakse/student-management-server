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
        return new DepartmentDTO(
                department.getDepartmentId(),
                department.getDepartmentName(),
                department.getHeadOfDepartment().getUserId()
        );
    }

    public static Department mapToDepEntity(DepartmentDTO departmentDTO, Lecturer headOfDep) {
        if (departmentDTO == null) {
            return null;
        }

        Department department = new Department();
        department.setDepartmentId(departmentDTO.getDepartmentId());
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setHeadOfDepartment(headOfDep);
        return department;
    }
}
