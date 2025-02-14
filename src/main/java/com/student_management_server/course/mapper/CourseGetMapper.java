package com.student_management_server.course.mapper;

import com.student_management_server.course.dto.CourseGetDTO;
import com.student_management_server.course.entity.Course;

public class CourseGetMapper {
    public static CourseGetDTO mapToCourseGetDTO(Course course) {
        CourseGetDTO dto = new CourseGetDTO();
        dto.setCourseId(course.getCourseId());
        dto.setCourseName(course.getCourseName());
        dto.setDescription(course.getDescription());
        dto.setDepartmentName(course.getDepartment().getDepartmentName());
        dto.setCredits(course.getCredits());
        return dto;
    }

    public static Course mapToCourseEntity(CourseGetDTO dto) {
        Course course = new Course();
        course.setCourseId(dto.getCourseId());
        course.setCourseName(dto.getCourseName());
        course.setDescription(dto.getDescription());
        course.setCredits(dto.getCredits());
        return course;
    }
}
