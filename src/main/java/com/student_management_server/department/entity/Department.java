package com.student_management_server.department.entity;

import com.student_management_server.user.entity.Lecturer;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(nullable = false, unique = true)
    private String departmentName;

    @OneToOne
    @JoinColumn(name = "head_of_department_id", nullable = false, referencedColumnName = "userId")
    private Lecturer headOfDepartment;

}
