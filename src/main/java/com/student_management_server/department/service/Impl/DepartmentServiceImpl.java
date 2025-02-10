package com.student_management_server.department.service.Impl;

import com.student_management_server.course.dto.CourseDto;
import com.student_management_server.department.dto.DepartmentDTO;
import com.student_management_server.department.entity.Department;
import com.student_management_server.department.mapper.DepartmentMapper;
import com.student_management_server.department.repository.DepartmentRepository;
import com.student_management_server.department.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDTO createDep(DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.mapToDepEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        return department.map(DepartmentMapper::mapToDepDTO).orElse(null);
    }

    /*@Override
    public boolean deleteDepartment(Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            departmentRepository.delete(department.get());
            return true;
        }
        return false;
    }*/
}
