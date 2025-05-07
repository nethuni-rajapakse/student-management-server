package com.student_management_server.user.dto;

import com.student_management_server.user.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentDTO {
    private Long studentId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private String profilePhoto;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
