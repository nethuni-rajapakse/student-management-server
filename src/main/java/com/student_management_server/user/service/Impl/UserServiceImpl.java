package com.student_management_server.user.service.Impl;

import com.student_management_server.user.dto.UserDto;
import com.student_management_server.user.dto.UserUpdateDto;
import com.student_management_server.user.entity.Student;
import com.student_management_server.user.entity.User;
import com.student_management_server.user.mapper.UserMapper;
import com.student_management_server.user.mapper.UserUpdateMapper;
import com.student_management_server.user.repository.UserRepository;
import com.student_management_server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // Check role and create corresponding subclass
        User user;
        if ("Student".equalsIgnoreCase(userDto.getRole())) {
            user = new Student();  // Create a Student
        } else {
            user = new User();  // Default to User if role is unknown
        }

        // Convert UserDto to User entity
        user = UserMapper.mapToUser(userDto);

        // Set createdAt and updatedAt
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        System.out.println("Saving user of type: " + user.getClass().getName());
        // Save User entity to the database
        User savedUser = userRepository.save(user);

        // Convert saved User entity back to UserDto and return
        return UserMapper.mapToUserDto(savedUser);
    }



    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll(); // Fetch all users from the database
        return users.stream()
                .map(UserMapper::mapToUserDto) // Convert User entities to UserDtos
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId); // Try to fetch the user by ID
        return user.map(UserMapper::mapToUserDto) // If found, convert to UserDto
                .orElse(null); // If not found, return null
    }


    @Override
    public boolean deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId); // Try to fetch the user by ID
        if (user.isPresent()) {
            userRepository.delete(user.get()); // If found, delete the user
            return true; // Return true if deletion was successful
        }
        return false; // Return false if the user was not found
    }

    @Override
    public UserDto updateUser(Long userId, UserUpdateDto userUpdateDto) {
        Optional<User> existingUser = userRepository.findById(userId); // Fetch user by ID
        if (existingUser.isPresent()) {
            User user = existingUser.get();

            // Use the mapper to update the existing user entity with the new data
            User updatedUser = UserUpdateMapper.mapToUser(userUpdateDto, user);

            // Save the updated user entity to the database
            User savedUser = userRepository.save(updatedUser);

            // Return the updated user as a DTO
            return UserMapper.mapToUserDto(savedUser);
        } else {
            return null; // If user is not found, return null
        }
    }

    /*@Override
    public UserDto patchUser(Long userId, String role) {
        Optional<User> existingUserOptional = userRepository.findById(userId);

        if (existingUserOptional.isEmpty()) {
            return null; // User not found
        }

        User existingUser = existingUserOptional.get();

        // Update the role
        if (role != null) {
            existingUser.setRole(role);
        }

        // Map other fields from UserUpdateDto to User entity
        existingUser = UserUpdateMapper.mapToUser(userUpdateDto, existingUser);

        // Update the "updatedAt" timestamp
        existingUser.setUpdatedAt(LocalDateTime.now());

        // Save the updated user back to the database
        User updatedUser = userRepository.save(existingUser);

        // Return the updated user as a UserDto
        return UserMapper.mapToUserDto(updatedUser);
    }*/



}
