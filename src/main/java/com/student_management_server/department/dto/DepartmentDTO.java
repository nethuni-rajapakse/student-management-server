package com.student_management_server.department.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DepartmentDTO {

    private Long departmentId;
    private String departmentName;
    private Long headOfDepartmentId;
}
