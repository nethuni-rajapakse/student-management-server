package com.student_management_server.course.service;

import com.student_management_server.course.dto.CourseDto;
import com.student_management_server.user.dto.UserDto;
import com.student_management_server.user.dto.UserUpdateDto;

import java.util.List;

public interface CourseService {

    CourseDto createCourse(CourseDto courseDto);

    List<CourseDto> getAllCourses();

    CourseDto getCourseById(Long courseId);

    boolean deleteCourse(Long courseId);

    CourseDto updateCourse(Long userId, CourseDto courseUpdateDto);


}
