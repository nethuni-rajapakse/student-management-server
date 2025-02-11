package com.student_management_server.course.dto;

import com.student_management_server.department.entity.Department;
import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class CourseDto {

    private Long courseId;
    private String courseName;
    private String description;
    private Long departmentId;
    private Double credits;
}
