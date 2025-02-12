package com.student_management_server.course.service;

import com.student_management_server.course.dto.CourseDTO;
import com.student_management_server.course.dto.EnrollmentDTO;
import com.student_management_server.user.dto.StudentDTO;

import java.util.List;

public interface EnrollmentService {

    EnrollmentDTO addEnrollment(EnrollmentDTO newEnrollDTO);

    EnrollmentDTO getEnrollmentById(Long id);

    List<StudentDTO> getStudentEnrollmentsForCourse(Long courseId);

    List<CourseDTO> getCourseEnrollmentsForStudent(Long studentId);

    void deleteEnrollment(Long id);
}
