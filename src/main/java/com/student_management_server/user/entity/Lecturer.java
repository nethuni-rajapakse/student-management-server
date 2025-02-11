package com.student_management_server.user.entity;

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

    }
