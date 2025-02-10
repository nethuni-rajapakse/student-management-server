package com.student_management_server.user.repository;

import com.student_management_server.user.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
