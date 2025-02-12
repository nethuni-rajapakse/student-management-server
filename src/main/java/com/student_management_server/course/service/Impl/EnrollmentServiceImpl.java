package com.student_management_server.course.service.Impl;

import com.student_management_server.course.dto.CourseDTO;
import com.student_management_server.course.dto.EnrollmentDTO;
import com.student_management_server.course.entity.Course;
import com.student_management_server.course.entity.Enrollment;
import com.student_management_server.course.mapper.CourseMapper;
import com.student_management_server.course.mapper.EnrollmentMapper;
import com.student_management_server.course.repository.CourseRepository;
import com.student_management_server.course.repository.EnrollmentRepository;
import com.student_management_server.course.service.EnrollmentService;
import com.student_management_server.user.dto.StudentDTO;
import com.student_management_server.user.entity.Student;
import com.student_management_server.user.mapper.StudentMapper;
import com.student_management_server.user.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public EnrollmentDTO addEnrollment(EnrollmentDTO newEnrollDTO) {
        Student student = studentRepository.findById(newEnrollDTO.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found with the given Id"));
        Course course = courseRepository.findById(newEnrollDTO.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found with the given id"));

        Enrollment newEnrollment = EnrollmentMapper.mapToEnrollmentEntity(newEnrollDTO, student,course);
        Enrollment savedEnroll = enrollmentRepository.save(newEnrollment);
        return EnrollmentMapper.mapToEnrollmentDTO(savedEnroll);
    }

    @Override
    public EnrollmentDTO getEnrollmentById(Long id) {
        Enrollment enroll = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found with the given id"));
        return EnrollmentMapper.mapToEnrollmentDTO(enroll);
    }

    @Override
    public List<StudentDTO> getStudentEnrollmentsForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with the given id"));
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentsByCourse(course);

        return enrollments.stream()
                .map(enrollment -> StudentMapper.mapToStudentDTO(enrollment.getStudent()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> getCourseEnrollmentsForStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with the given Id"));
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentsByStudent(student);

        return enrollments.stream()
                .map(enrollment -> CourseMapper.mapToCourseDTO(enrollment.getCourse()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEnrollment(Long id) {
        Enrollment enroll = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found with the given id"));
        enrollmentRepository.delete(enroll);
    }
}
