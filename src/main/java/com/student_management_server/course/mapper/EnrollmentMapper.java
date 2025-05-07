package com.student_management_server.course.mapper;

import com.student_management_server.course.dto.EnrollmentDTO;
import com.student_management_server.course.entity.Enrollment;
import com.student_management_server.user.entity.Student;
import com.student_management_server.course.entity.Course;

public class EnrollmentMapper {

    public static EnrollmentDTO mapToEnrollmentDTO(Enrollment enrollment) {
        if (enrollment == null) {
            return null;
        }

        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.setEnrollmentId(enrollment.getEnrollmentId());
        enrollmentDTO.setStudentId(enrollment.getStudent().getStudentId());
        enrollmentDTO.setCourseId(enrollment.getCourse().getCourseId());
        enrollmentDTO.setEnrollmentDate(enrollment.getEnrollmentDate());

        return enrollmentDTO;
    }

    public static Enrollment mapToEnrollmentEntity(EnrollmentDTO enrollmentDTO, Student student, Course course) {
        if (enrollmentDTO == null) {
            return null;
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(enrollmentDTO.getEnrollmentId());
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(enrollmentDTO.getEnrollmentDate());

        return enrollment;
    }
}
