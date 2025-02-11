package com.student_management_server.user.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class UserDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private String gender;
    private String profilePhoto;
    private String address;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
