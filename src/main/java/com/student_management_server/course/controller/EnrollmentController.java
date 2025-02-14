package com.student_management_server.course.controller;

import com.student_management_server.course.dto.CourseDTO;
import com.student_management_server.course.dto.EnrollmentDTO;
import com.student_management_server.course.service.EnrollmentService;
import com.student_management_server.user.dto.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enroll")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> addEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
        EnrollmentDTO createdEnrollment = enrollmentService.addEnrollment(enrollmentDTO);
        return new ResponseEntity<>(createdEnrollment, HttpStatus.CREATED);
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long enrollmentId) {
        EnrollmentDTO enrollment = enrollmentService.getEnrollmentById(enrollmentId);
        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}/students")
    public ResponseEntity<List<StudentDTO>> getStudentEnrollmentsForCourse(@PathVariable Long courseId) {
        List<StudentDTO> students = enrollmentService.getStudentEnrollmentsForCourse(courseId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/courses")
    public ResponseEntity<List<CourseDTO>> getCourseEnrollmentsForStudent(@PathVariable Long studentId) {
        List<CourseDTO> courses = enrollmentService.getCourseEnrollmentsForStudent(studentId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long enrollmentId) {
        enrollmentService.deleteEnrollment(enrollmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
