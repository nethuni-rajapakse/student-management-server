package com.student_management_server.course.controller;

import com.student_management_server.course.dto.CourseDto;
import com.student_management_server.course.service.CourseService;
import com.student_management_server.user.dto.UserDto;
import com.student_management_server.user.dto.UserUpdateDto;
import com.student_management_server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        CourseDto createdCourse = courseService.createCourse(courseDto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDto> getCoursesById(@PathVariable Long courseId) {
        CourseDto courses = courseService.getCourseById(courseId);
        if (courses != null) {
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long courseId) {
        boolean isDeleted = courseService.deleteCourse(courseId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long courseId, @RequestBody CourseDto courseUpdateDto) {
        CourseDto updatedCourse = courseService.updateCourse(courseId, courseUpdateDto);
        if (updatedCourse != null) {
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
