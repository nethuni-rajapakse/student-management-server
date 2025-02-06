package com.student_management_server.user.service.Impl;

import com.student_management_server.user.dto.UserDto;
import com.student_management_server.user.entity.User;
import com.student_management_server.user.mapper.UserMapper;
import com.student_management_server.user.repository.UserRepository;
import com.student_management_server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto to User entity
        User user = UserMapper.mapToUser(userDto);
        user.setCreatedAt(new Date());   // Setting createdAt to the current time
        user.setUpdatedAt(new Date());   // Setting updatedAt to the current time

        // Save User entity to the database
        User savedUser = userRepository.save(user);

        // Convert saved User entity back to UserDto and return
        return UserMapper.mapToUserDto(savedUser);
    }
}
