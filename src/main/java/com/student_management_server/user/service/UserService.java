package com.student_management_server.user.service;

import com.student_management_server.user.dto.UserDto;
import com.student_management_server.user.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);

    boolean deleteUser(Long userId);

    UserDto updateUser(Long userId, UserUpdateDto userUpdateDto);

    public UserDto patchUser(Long userId, UserUpdateDto userUpdateDto);



}
