package com.student_management_server.user.service.Impl;

import com.student_management_server.course.entity.Course;
import com.student_management_server.department.entity.Department;
import com.student_management_server.department.repository.DepartmentRepository;
import com.student_management_server.user.dto.LecturerDTO;
import com.student_management_server.user.entity.Lecturer;
import com.student_management_server.user.mapper.LecturerMapper;
import com.student_management_server.user.repository.LecturerRepository;
import com.student_management_server.user.service.LecturerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;
    private final DepartmentRepository departmentRepository;

    public LecturerServiceImpl(LecturerRepository lecturerRepository, DepartmentRepository departmentRepository) {
        this.lecturerRepository = lecturerRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public LecturerDTO createLecturer(LecturerDTO lecturerDTO) {
        Department department = departmentRepository.findById(lecturerDTO.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department not found with given Id"));
        Lecturer newLecturer = LecturerMapper.mapToLecturerEntity(lecturerDTO, department);
        Lecturer savedLecturer = lecturerRepository.save(newLecturer);
        return LecturerMapper.mapToLecturerDTO(savedLecturer);

    }

    @Override
    public List<LecturerDTO> getAllLecturers() {
        List<Lecturer> lecturers = lecturerRepository.findAll(); // Fetch all users from the database
        return lecturers.stream()
                .map(LecturerMapper::mapToLecturerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LecturerDTO getLecturerById(Long lecturerId) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> new EntityNotFoundException("Lecturer not Found with given Id"));
        return LecturerMapper.mapToLecturerDTO(lecturer);
    }

    @Override
    public void deleteLecturer(Long lecturerId) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> new EntityNotFoundException("Lecturer not Found with given Id"));
        lecturerRepository.delete(lecturer);
    }

    @Override
    public LecturerDTO updateLecturer(Long lecturerId, LecturerDTO lecturerDTO) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> new EntityNotFoundException("Lecturer not Found with given Id"));

        if (lecturerDTO.getFirstName() != null) lecturer.setFirstName(lecturerDTO.getFirstName());
        if (lecturerDTO.getLastName() != null) lecturer.setLastName(lecturerDTO.getLastName());
        if (lecturerDTO.getDateOfBirth() != null) lecturer.setDateOfBirth(lecturerDTO.getDateOfBirth());
        if (lecturerDTO.getEmail() != null) lecturer.setEmail(lecturerDTO.getEmail());
        if (lecturerDTO.getPhoneNumber() != null) lecturer.setPhoneNumber(lecturerDTO.getPhoneNumber());
        if (lecturerDTO.getGender() != null) lecturer.setGender(lecturerDTO.getGender());
        if (lecturerDTO.getProfilePhoto() != null) lecturer.setProfilePhoto(lecturerDTO.getProfilePhoto());
        if (lecturerDTO.getAddress() != null) lecturer.setAddress(lecturerDTO.getAddress());
        if (lecturerDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(lecturerDTO.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found with the given Id"));
            lecturer.setDepartment(department);
        }

        Lecturer savedLecturer = lecturerRepository.save(lecturer);
        return LecturerMapper.mapToLecturerDTO(savedLecturer);

    }

}
