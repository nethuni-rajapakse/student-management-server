package com.student_management_server.course.dto;

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
    private String department;
    private Double credits;
}
