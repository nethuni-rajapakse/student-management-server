package com.student_management_server.user.mapper;

import com.student_management_server.user.dto.StudentDTO;
import com.student_management_server.user.entity.Student;

public class StudentMapper {

    public static StudentDTO mapToStudentDTO(Student student) {
        if (student == null) {
            return null;
        }
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setDateOfBirth(student.getDateOfBirth());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setPhoneNumber(student.getPhoneNumber());
        studentDTO.setGender(student.getGender());
        studentDTO.setProfilePhoto(student.getProfilePhoto());
        studentDTO.setAddress(student.getAddress());
        studentDTO.setCreatedAt(student.getCreatedAt());
        studentDTO.setUpdatedAt(student.getUpdatedAt());

        return studentDTO;
    }


    public static Student mapToStudentEntity(StudentDTO studentDTO) {
        if (studentDTO == null) {
            return null;
        }

        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setEmail(studentDTO.getEmail());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setGender(studentDTO.getGender());
        student.setProfilePhoto(studentDTO.getProfilePhoto());
        student.setAddress(studentDTO.getAddress());
        student.setCreatedAt(studentDTO.getCreatedAt());
        student.setUpdatedAt(studentDTO.getUpdatedAt());

        return student;
    }
}
