package com.student_management_server.course.mapper;

import com.student_management_server.course.dto.CourseDTO;
import com.student_management_server.course.entity.Course;
import com.student_management_server.department.entity.Department;

public class CourseMapper {

    // Method to convert Course to CourseDto
    public static CourseDTO mapToCourseDto(Course course) {
        if (course == null) {
            return null;
        }

        CourseDTO courseDto = new CourseDTO();
        courseDto.setCourseId(course.getCourseId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setDescription(course.getDescription());
        courseDto.setDepartmentId(course.getDepartment().getDepartmentId());
        courseDto.setCredits(course.getCredits());

        return courseDto;
    }

    // Method to convert CourseDto to   Course
    public static Course mapToCourse(CourseDTO courseDto, Department department) {
        if (courseDto == null) {
            return null;
        }

        Course course = new Course();
        course.setCourseId(courseDto.getCourseId());
        course.setCourseName(courseDto.getCourseName());
        course.setDescription(courseDto.getDescription());
        course.setDepartment(department);
        course.setCredits(courseDto.getCredits());

        return course;
    }

}
