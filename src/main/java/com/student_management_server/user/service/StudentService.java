package com.student_management_server.user.service;

import com.student_management_server.course.entity.Course;
import com.student_management_server.user.dto.StudentDTO;

import java.util.List;
import java.util.Set;

public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long studentId);

    void deleteStudent(Long studentId);

    StudentDTO updateStudent(Long studentId, StudentDTO studentDTO);


}
