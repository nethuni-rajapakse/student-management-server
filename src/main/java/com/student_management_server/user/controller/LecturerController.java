package com.student_management_server.user.controller;

import com.student_management_server.user.dto.UserDto;
import com.student_management_server.user.entity.Lecturer;
import com.student_management_server.user.entity.Student;
import com.student_management_server.user.service.LecturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lecturer")
public class LecturerController {
    private final LecturerService lecturerService;
   public LecturerController(LecturerService lecturerService) {
       this.lecturerService = lecturerService;
   }

    @GetMapping
    public ResponseEntity<List<Lecturer>> getAllStudents() {
        List<Lecturer> lecturers = lecturerService.getAllLecturers();
        return ResponseEntity.ok(lecturers);
    }




}
