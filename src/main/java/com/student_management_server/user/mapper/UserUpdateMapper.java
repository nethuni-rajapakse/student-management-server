package com.student_management_server.user.mapper;

import com.student_management_server.user.dto.UserUpdateDto;
import com.student_management_server.user.entity.User;

import java.time.LocalDateTime;
import java.util.Date;

public class UserUpdateMapper {

    // Mapping from UserUpdateDto to User entity
    public static User mapToUser(UserUpdateDto userUpdateDto, User existingUser) {
        if (userUpdateDto == null) {
            return existingUser; // Return the existing user if update DTO is null
        }

        // Update the existing user fields with values from UserUpdateDto
        if (userUpdateDto.getFirstName() != null) existingUser.setFirstName(userUpdateDto.getFirstName());
        if (userUpdateDto.getLastName() != null) existingUser.setLastName(userUpdateDto.getLastName());
        if (userUpdateDto.getDateOfBirth() != null) existingUser.setDateOfBirth(userUpdateDto.getDateOfBirth());
        if (userUpdateDto.getEmail() != null) existingUser.setEmail(userUpdateDto.getEmail());
        if (userUpdateDto.getPhoneNumber() != null) existingUser.setPhoneNumber(userUpdateDto.getPhoneNumber());
        if (userUpdateDto.getGender() != null) existingUser.setGender(userUpdateDto.getGender());
        if (userUpdateDto.getProfilePhoto() != null) existingUser.setProfilePhoto(userUpdateDto.getProfilePhoto());
        if (userUpdateDto.getAddress() != null) existingUser.setAddress(userUpdateDto.getAddress());
        if (userUpdateDto.getRole() != null) existingUser.setRole(userUpdateDto.getRole());

        // Set the updatedAt field to the current date and time
        existingUser.setUpdatedAt(LocalDateTime.now());

        return existingUser;
    }
}
