package com.student_management_server.user.mapper;

import com.student_management_server.department.entity.Department;
import com.student_management_server.user.dto.LecturerDTO;
import com.student_management_server.user.entity.Lecturer;

public class LecturerMapper {

    public static LecturerDTO mapToLecturerDTO(Lecturer lecturer) {
        if (lecturer == null) {
            return null;
        }
        LecturerDTO lecturerDTO = new LecturerDTO();
        lecturerDTO.setLecturerId(lecturer.getLecturerId());
        lecturerDTO.setFirstName(lecturer.getFirstName());
        lecturerDTO.setLastName(lecturer.getLastName());
        lecturerDTO.setDateOfBirth(lecturer.getDateOfBirth());
        lecturerDTO.setEmail(lecturer.getEmail());
        lecturerDTO.setPhoneNumber(lecturer.getPhoneNumber());
        lecturerDTO.setGender(lecturer.getGender());
        lecturerDTO.setProfilePhoto(lecturer.getProfilePhoto());
        lecturerDTO.setAddress(lecturer.getAddress());
        lecturerDTO.setCreatedAt(lecturer.getCreatedAt());
        lecturerDTO.setUpdatedAt(lecturer.getUpdatedAt());
        lecturerDTO.setDepartmentId(lecturer.getDepartment().getDepartmentId());
        return lecturerDTO;
    }

    public static Lecturer mapToLecturerEntity(LecturerDTO lecturerDTO,Department department) {
        if (lecturerDTO == null) {
            return null;
        }

        Lecturer lecturer = new Lecturer();
        lecturer.setLecturerId(lecturerDTO.getLecturerId());
        lecturer.setFirstName(lecturerDTO.getFirstName());
        lecturer.setLastName(lecturerDTO.getLastName());
        lecturer.setDateOfBirth(lecturerDTO.getDateOfBirth());
        lecturer.setEmail(lecturerDTO.getEmail());
        lecturer.setPhoneNumber(lecturerDTO.getPhoneNumber());
        lecturer.setGender(lecturerDTO.getGender());
        lecturer.setProfilePhoto(lecturerDTO.getProfilePhoto());
        lecturer.setAddress(lecturerDTO.getAddress());
        lecturer.setCreatedAt(lecturerDTO.getCreatedAt());
        lecturer.setUpdatedAt(lecturerDTO.getUpdatedAt());
        lecturer.setDepartment(department);

        return lecturer;
    }
}


