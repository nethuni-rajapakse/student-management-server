package com.student_management_server.department.service.Impl;

import com.student_management_server.course.mapper.CourseMapper;
import com.student_management_server.department.dto.DepartmentDTO;
import com.student_management_server.department.entity.Department;
import com.student_management_server.department.mapper.DepartmentMapper;
import com.student_management_server.department.repository.DepartmentRepository;
import com.student_management_server.department.service.DepartmentService;
import com.student_management_server.user.entity.Lecturer;
import com.student_management_server.user.repository.LecturerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentRepository departmentRepository;
    private final LecturerRepository lecturerRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, LecturerRepository lecturerRepository) {
        this.departmentRepository = departmentRepository;
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public DepartmentDTO createDep(DepartmentDTO departmentDTO) {
        //Department Head is null as it is assigned later
        Department department = DepartmentMapper.mapToDepEntity(departmentDTO, null);

        try {
            Department savedDepartment = departmentRepository.save(department);
            return DepartmentMapper.mapToDepDTO(savedDepartment);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Department name already exists: " + department.getDepartmentName());
        }
    }

    @Override
    public DepartmentDTO getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with the given Id"));
        return DepartmentMapper.mapToDepDTO(department);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(DepartmentMapper::mapToDepDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO updatedDepartment(Long departmentId, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with the given Id"));

        if (departmentDTO.getDepartmentName() != null) department.setDepartmentName(departmentDTO.getDepartmentName());

        if (departmentDTO.getHeadOfDepartmentId() != null) {
            Lecturer headOfDep = lecturerRepository.findById(departmentDTO.getHeadOfDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Lecturer not found with the given Id"));

            // Check if the lecturer belongs to the department
            if (!headOfDep.getDepartment().getDepartmentId().equals(departmentId)) {
                throw new RuntimeException("The lecturer does not belong to this department");
            }
            department.setHeadOfDepartment(headOfDep);
        }

        Department savedDep = departmentRepository.save(department);
        return DepartmentMapper.mapToDepDTO(savedDep);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with the given Id"));
        departmentRepository.delete(department);
    }

}
