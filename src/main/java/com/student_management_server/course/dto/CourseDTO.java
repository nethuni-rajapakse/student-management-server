package com.student_management_server.course.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class CourseDTO {

    private Long courseId;
    private String courseName;
    private String description;
    private Long departmentId;
    private Double credits;
}
