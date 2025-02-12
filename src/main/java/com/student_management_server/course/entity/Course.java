package com.student_management_server.course.entity;

import com.student_management_server.department.entity.Department;
import com.student_management_server.user.entity.Lecturer;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false, unique = true)
    private String courseName;
    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false, referencedColumnName = "departmentId")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "course_lecturer",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "courseId"),
            inverseJoinColumns = @JoinColumn(name = "lecturer_id", referencedColumnName = "lecturerId")
    )
    private Set<Lecturer> lecturers = new HashSet<>();

    @Column(nullable = false)
    private Double credits;


}
