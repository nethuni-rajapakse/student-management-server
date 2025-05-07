package com.student_management_server.course.controller;

import com.student_management_server.course.dto.CourseDTO;
import com.student_management_server.course.dto.CourseGetDTO;
import com.student_management_server.course.service.CourseService;
import com.student_management_server.user.entity.Lecturer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController (CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDto) {
        CourseDTO createdCourse = courseService.createCourse(courseDto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCoursesById(@PathVariable Long courseId) {
        CourseDTO courses = courseService.getCourseById(courseId);
        return new ResponseEntity<>(courses, HttpStatus.OK);

    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long courseId, @RequestBody CourseDTO courseUpdateDto) {
        CourseDTO updatedCourse = courseService.updateCourse(courseId, courseUpdateDto);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @PostMapping("/{courseId}/lecturers")
    public ResponseEntity<String> addLecturersToCourse (@PathVariable Long courseId, @RequestBody Set<Long> lecturerIds) {
        courseService.addLecturersToCourse(courseId, lecturerIds);
        return ResponseEntity.status(HttpStatus.OK).body("Lecturers added successfully.");

    }

    @GetMapping("/{courseId}/lecturers")
    public ResponseEntity<Set<Lecturer>> getLecturersForCourse(@PathVariable Long courseId) {
          Set<Lecturer> lecturers = courseService.getLecturersByCourse(courseId);
          return new ResponseEntity<>(lecturers, HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}/lecturers/{lecturerId}")
    public ResponseEntity<String> removeLecturerFromCourse(@PathVariable Long courseId, @PathVariable Long lecturerId) {
        courseService.removeLecturerFromCourse(lecturerId, courseId);
        return ResponseEntity.ok("Lecturer removed successfully.");
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<CourseDTO>> getCoursesInDep(@PathVariable Long departmentId) {
        List<CourseDTO> courses = courseService.getCursesByDepartmentId(departmentId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    //Course search REST API
    @GetMapping("/search")
    public ResponseEntity<List<CourseDTO>> searchCourse(@RequestParam("query") String query){
        return new ResponseEntity<>(courseService.searchCourses(query), HttpStatus.OK);
    }


}
