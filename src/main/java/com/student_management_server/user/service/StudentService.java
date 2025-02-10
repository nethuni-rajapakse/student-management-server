package com.student_management_server.user.service;

import com.student_management_server.course.entity.Course;

import java.util.Set;

public interface StudentService {

    void addCoursesToStudent(Long studentId, Set<Long> courseIds);

    Set<Course> getStudentCourses(Long studentId);

    void removeCourseFromStudent(Long studentId, Long courseId);


}
