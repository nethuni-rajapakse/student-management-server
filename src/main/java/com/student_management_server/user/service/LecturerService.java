package com.student_management_server.user.service;

import com.student_management_server.course.entity.Course;
import com.student_management_server.user.dto.LecturerDTO;

import java.util.List;
import java.util.Set;

public interface LecturerService {

    LecturerDTO   createLecturer(LecturerDTO lecturerDTO);

    List<LecturerDTO> getAllLecturers();

    LecturerDTO getLecturerById(Long lecturerId);

    void deleteLecturer(Long lecturerId);

    LecturerDTO updateLecturer(Long lecturerId, LecturerDTO lecturerDTO);



}
