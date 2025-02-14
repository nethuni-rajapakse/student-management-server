package com.student_management_server.course.service;

import com.student_management_server.course.dto.CourseDTO;
import com.student_management_server.course.dto.CourseGetDTO;
import com.student_management_server.course.entity.Course;
import com.student_management_server.user.entity.Lecturer;

import java.util.List;
import java.util.Set;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDto);

    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(Long courseId);

    void deleteCourse(Long courseId);

    CourseDTO updateCourse(Long userId, CourseDTO courseUpdateDto);

    void addLecturersToCourse(Long courseId, Set<Long> lecturerIds);

    Set<Lecturer> getLecturersByCourse(Long courseId);

    void removeLecturerFromCourse(Long lecturerId, Long courseId);

    List<CourseDTO> getCursesByDepartmentId(Long departmentId);


}
