package com.student_management_server.user.controller;

import com.student_management_server.user.dto.StudentDTO;
import com.student_management_server.user.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.student_management_server.course.entity.Course;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addNewStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId) {
        StudentDTO student = studentService.getStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(studentId, studentDTO);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }


    // Endpoint to add courses to a student
    @PostMapping("/{studentId}/courses")
    public ResponseEntity<String> addCoursesToStudent(@PathVariable Long studentId, @RequestBody Set<Long> courseIds) {
        studentService.addCoursesToStudent(studentId, courseIds);
        return ResponseEntity.status(HttpStatus.OK).body("Courses added successfully.");
    }


    @GetMapping("/{studentId}/courses")
    public ResponseEntity<Set<Course>> getStudentCourses(@PathVariable Long studentId) {
        Set<Course> courses = studentService.getStudentCourses(studentId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<String> removeCourseFromStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
            studentService.removeCourseFromStudent(studentId, courseId);
            return ResponseEntity.ok("Course removed successfully.");
    }

}
