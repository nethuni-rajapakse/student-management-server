package com.student_management_server.course.entity;

import com.student_management_server.department.entity.Department;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String courseName;
    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false, referencedColumnName = "departmentId")
    private Department department;

    private Double credits;

}
