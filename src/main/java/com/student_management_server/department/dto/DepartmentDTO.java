package com.student_management_server.department.dto;

import com.student_management_server.user.entity.Lecturer;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class DepartmentDTO {

    private Long departmentId;
    private String departmentName;
    private Long headOfDepartmentId;
}
