package com.student_management_server.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CourseGetDTO {
    private Long courseId;
    private String courseName;
    private String description;
    private String departmentName;
    private Double credits;
}
