package com.student_management_server.user.mapper;

import com.student_management_server.user.dto.UserDto;
import com.student_management_server.user.entity.User;

public class UserMapper {

    // Mapping from User entity to UserDto
    public static UserDto mapToUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setGender(user.getGender());
        userDto.setProfilePhoto(user.getProfilePhoto());
        userDto.setAddress(user.getAddress());
        userDto.setRole(user.getRole());
        //userDto.setActive(user.isActive());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        return userDto;
    }

    // Mapping from UserDto to User entity
    public static User mapToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setGender(userDto.getGender());
        user.setProfilePhoto(userDto.getProfilePhoto());
        user.setAddress(userDto.getAddress());
        user.setRole(userDto.getRole());
        //user.setActive(userDto.isActive());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());

        return user;
    }
}
