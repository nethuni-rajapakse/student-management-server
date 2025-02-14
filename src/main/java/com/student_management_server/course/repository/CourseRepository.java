package com.student_management_server.course.repository;


import com.student_management_server.course.entity.Course;
import com.student_management_server.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> getCourseByDepartment(Department Department);

}
