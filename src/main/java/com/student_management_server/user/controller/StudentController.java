package com.student_management_server.user.controller;

import com.student_management_server.user.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.student_management_server.course.entity.Course;

import java.util.Set;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Endpoint to add courses to a student
    @PostMapping("/{studentId}/courses")
    public ResponseEntity<String> addCoursesToStudent(
            @PathVariable Long studentId,
            @RequestBody Set<Long> courseIds) {

        try {
            // Call the service to add courses to the student
            studentService.addCoursesToStudent(studentId, courseIds);
            return ResponseEntity.ok("Courses added successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<?> getStudentCourses(@PathVariable Long studentId) {
        try {
            Set<Course> courses = studentService.getStudentCourses(studentId);
            return ResponseEntity.ok(courses);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<String> removeCourseFromStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        try {
            studentService.removeCourseFromStudent(studentId, courseId);
            return ResponseEntity.ok("Course removed successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
