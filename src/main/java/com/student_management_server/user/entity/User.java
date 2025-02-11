package com.student_management_server.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String profilePhoto;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

