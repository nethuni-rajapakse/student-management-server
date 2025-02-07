package com.student_management_server.user.controller;

import com.student_management_server.user.dto.UserDto;
import com.student_management_server.user.dto.UserUpdateDto;
import com.student_management_server.user.entity.User;
import com.student_management_server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        UserDto user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 No Content if deletion is successful
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the user is not found
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserUpdateDto userUpdateDto) {
        UserDto updatedUser = userService.updateUser(userId, userUpdateDto);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK); // Return updated user with 200 OK status
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the user is not found
        }
    }

    // PATCH request to update a user partially by ID
    @PatchMapping("/{userId}")
    public ResponseEntity<UserDto> patchUser(@PathVariable Long userId, @RequestBody UserUpdateDto userUpdateDto) {
        UserDto updatedUser = userService.patchUser(userId, userUpdateDto);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK); // Return updated user with 200 OK status
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the user is not found
        }
    }


}
