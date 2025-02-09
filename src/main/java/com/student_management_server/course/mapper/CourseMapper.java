package com.student_management_server.course.mapper;

import com.student_management_server.course.dto.CourseDto;
import com.student_management_server.course.entity.Course;

public class CourseMapper {

    // Method to convert Course to CourseDto
    public static CourseDto mapToCourseDto(Course course) {
        if (course == null) {
            return null;
        }

        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(course.getCourseId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setDescription(course.getDescription());
        courseDto.setDepartment(course.getDepartment());
        courseDto.setCredits(course.getCredits());

        return courseDto;
    }

    // Method to convert CourseDto to Course
    public static Course mapToCourse(CourseDto courseDto) {
        if (courseDto == null) {
            return null;
        }

        Course course = new Course();
        course.setCourseId(courseDto.getCourseId());
        course.setCourseName(courseDto.getCourseName());
        course.setDescription(courseDto.getDescription());
        course.setDepartment(courseDto.getDepartment());
        course.setCredits(courseDto.getCredits());

        return course;
    }

}
