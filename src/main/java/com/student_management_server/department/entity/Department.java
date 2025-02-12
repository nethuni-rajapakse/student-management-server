package com.student_management_server.department.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.student_management_server.course.entity.Course;
import com.student_management_server.user.entity.Lecturer;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(nullable = false, unique = true)
    private String departmentName;

    @OneToOne
    @JoinColumn(name = "head_of_department_id", referencedColumnName = "lecturerId")
    private Lecturer headOfDepartment;

}
