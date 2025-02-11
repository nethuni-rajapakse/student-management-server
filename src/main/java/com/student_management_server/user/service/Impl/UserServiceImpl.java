package com.student_management_server.user.service.Impl;

import com.student_management_server.user.dto.UserDto;
import com.student_management_server.user.dto.UserUpdateDto;
import com.student_management_server.user.entity.Lecturer;
import com.student_management_server.user.entity.Student;
import com.student_management_server.user.entity.User;
import com.student_management_server.user.exception.UserNotFoundException;
import com.student_management_server.user.mapper.UserMapper;
import com.student_management_server.user.mapper.UserUpdateMapper;
import com.student_management_server.user.repository.UserRepository;
import com.student_management_server.user.service.UserService;
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
            user = new Student();
        } else if ("Lecturer".equalsIgnoreCase(userDto.getRole())) {
            user = new Lecturer();
        } else {
            user = new User();  // Default to User if role is unknown
        }

        System.out.println("Saving user of type: " + user.getClass().getName());

        // Convert UserDto to User entity
        user = UserMapper.mapToUser(userDto);

        // Set createdAt and updatedAt
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        // Convert saved User entity back to UserDto and return
        return UserMapper.mapToUserDto(savedUser);
    }



    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(UserMapper::mapToUserDto)
                .orElse(null);
    }


    @Override
    public boolean deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }

    @Override
    public UserDto updateUser(Long userId, UserUpdateDto userUpdateDto) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();

            User updatedUser = UserUpdateMapper.mapToUser(userUpdateDto, user);
            User savedUser = userRepository.save(updatedUser);
            return UserMapper.mapToUserDto(savedUser);
        } else {
            throw new UserNotFoundException(userId);
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
