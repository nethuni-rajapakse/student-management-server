package com.student_management_server.department.controller;

import com.student_management_server.department.dto.DepartmentDTO;
import com.student_management_server.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createCourse(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO createdDep = departmentService.createDep(departmentDTO);
        return new ResponseEntity<>(createdDep, HttpStatus.CREATED);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepaertmentbyId(@PathVariable Long departmentId) {
        DepartmentDTO department = departmentService.getDepartmentById(departmentId);
        if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*@DeleteMapping
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long departmentId) {
        boolean isDeleted = departmentService.deleteDepartment(departmentId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}
