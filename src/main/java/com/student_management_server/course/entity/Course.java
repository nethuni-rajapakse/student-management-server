package com.student_management_server.course.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


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
    @Column(nullable = false)
    private Double credits;

}
