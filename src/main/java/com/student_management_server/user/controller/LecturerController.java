package com.student_management_server.user.controller;

import com.student_management_server.user.dto.LecturerDTO;
import com.student_management_server.user.dto.StudentDTO;
import com.student_management_server.user.entity.Lecturer;
import com.student_management_server.user.service.LecturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lecturer")
public class LecturerController {
    private final LecturerService lecturerService;
   public LecturerController(LecturerService lecturerService) {
       this.lecturerService = lecturerService;
   }

   @PostMapping
   public ResponseEntity<LecturerDTO> addNewStudent(@RequestBody LecturerDTO lecturerDTO) {
       LecturerDTO createdLecturer = lecturerService.createLecturer(lecturerDTO);
       return new ResponseEntity<>(createdLecturer, HttpStatus.CREATED);
   }

    @GetMapping
    public ResponseEntity<List<LecturerDTO>> getAllLecturers() {
        List<LecturerDTO> lecturers = lecturerService.getAllLecturers();
        return new ResponseEntity<>(lecturers, HttpStatus.OK);
    }

    @GetMapping("/{lecturerId}")
    public ResponseEntity<LecturerDTO> getLecturerById(@PathVariable Long lecturerId) {
        LecturerDTO lecturer = lecturerService.getLecturerById(lecturerId);
        return new ResponseEntity<>(lecturer, HttpStatus.OK);
    }

    @PutMapping("/{lecturerId}")
    public ResponseEntity<LecturerDTO> updateLecturer(@PathVariable Long lecturerId, @RequestBody LecturerDTO lecturerDTO) {
        LecturerDTO updatedLecturer = lecturerService.updateLecturer(lecturerId, lecturerDTO);
        return new ResponseEntity<>(updatedLecturer, HttpStatus.OK);
    }

    @DeleteMapping("/{lecturerId}")
    public ResponseEntity<Void> deleteLecturerById(@PathVariable Long lecturerId) {
        lecturerService.deleteLecturer(lecturerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






}
