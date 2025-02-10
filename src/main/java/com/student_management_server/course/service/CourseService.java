package com.student_management_server.course.service;

import com.student_management_server.course.dto.CourseDto;
import com.student_management_server.course.entity.Course;

import java.util.List;

public interface CourseService {

    CourseDto createCourse(CourseDto courseDto);

    List<CourseDto> getAllCourses();

    CourseDto getCourseById(Long courseId);

    boolean deleteCourse(Long courseId);

    CourseDto updateCourse(Long userId, CourseDto courseUpdateDto);


}
