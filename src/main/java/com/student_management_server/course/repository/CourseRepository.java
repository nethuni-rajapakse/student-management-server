package com.student_management_server.course.repository;


import com.student_management_server.course.entity.Course;
import com.student_management_server.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> getCourseByDepartment(Department Department);

    @Query(
            "SELECT c FROM Course c WHERE " +
                    "(LOWER(c.courseName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
                    "LOWER(c.description) LIKE LOWER(CONCAT('%', :query, '%')))"
    )
    List<Course> searchCourse(String query);


}
