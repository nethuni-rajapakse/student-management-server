package com.student_management_server.course.repository;

import com.student_management_server.course.entity.Course;
import com.student_management_server.course.entity.Enrollment;
import com.student_management_server.user.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findEnrollmentsByStudent(Student student);

    List<Enrollment> findEnrollmentsByCourse(Course course);

}
