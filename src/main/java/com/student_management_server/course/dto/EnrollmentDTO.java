package com.student_management_server.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EnrollmentDTO {
    private Long enrollmentId;
    private Long studentId;
    private Long courseId;
    private LocalDateTime enrollmentDate;
}
