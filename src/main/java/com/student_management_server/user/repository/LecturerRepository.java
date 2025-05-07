package com.student_management_server.user.repository;

import com.student_management_server.user.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

}
