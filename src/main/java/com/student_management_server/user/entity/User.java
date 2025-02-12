package com.student_management_server.user.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
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

