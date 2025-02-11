package com.student_management_server.user.service;

import com.student_management_server.user.dto.UserDto;
import com.student_management_server.user.entity.Lecturer;

import java.util.List;

public interface LecturerService {

    List<Lecturer> getAllLecturers();
}
