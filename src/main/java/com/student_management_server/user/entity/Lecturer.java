package com.student_management_server.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.student_management_server.department.entity.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Lecturer")
public class Lecturer extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecturerId;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false, referencedColumnName = "departmentId")
    @JsonBackReference
    private Department department;

}