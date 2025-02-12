package com.student_management_server.user.service.Impl;

import com.student_management_server.course.entity.Course;
import com.student_management_server.course.repository.CourseRepository;
import com.student_management_server.user.dto.StudentDTO;
import com.student_management_server.user.entity.Student;
import com.student_management_server.user.exception.UserNotFoundException;
import com.student_management_server.user.mapper.StudentMapper;
import com.student_management_server.user.repository.StudentRepository;
import com.student_management_server.user.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student newStudent = StudentMapper.mapToStudentEntity(studentDTO);
        Student savedStudent = studentRepository.save(newStudent);
        return StudentMapper.mapToStudentDTO(savedStudent);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(StudentMapper::mapToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with the given Id"));
        return StudentMapper.mapToStudentDTO(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with the given Id"));
        studentRepository.delete(student);
    }

    @Override
    public StudentDTO updateStudent(Long studentId, StudentDTO studentDTO) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with the given Id"));

            // Update the existing user fields with values from UserUpdateDto
            if (studentDTO.getFirstName() != null) student.setFirstName(studentDTO.getFirstName());
            if (studentDTO.getLastName() != null) student.setLastName(studentDTO.getLastName());
            if (studentDTO.getDateOfBirth() != null) student.setDateOfBirth(studentDTO.getDateOfBirth());
            if (studentDTO.getEmail() != null) student.setEmail(studentDTO.getEmail());
            if (studentDTO.getPhoneNumber() != null) student.setPhoneNumber(studentDTO.getPhoneNumber());
            if (studentDTO.getGender() != null) student.setGender(studentDTO.getGender());
            if (studentDTO.getProfilePhoto() != null) student.setProfilePhoto(studentDTO.getProfilePhoto());
            if (studentDTO.getAddress() != null) student.setAddress(studentDTO.getAddress());

            Student savedStudent = studentRepository.save(student);
            return StudentMapper.mapToStudentDTO(savedStudent);
    }

    @Transactional
    @Override
    public void addCoursesToStudent(Long studentId, Set<Long> courseIds) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with the given Id"));

        Set<Course> existingCourses = student.getCourses();

        for (Long courseId : courseIds) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new EntityNotFoundException("Course not found with the given Id :" + courseId));
            existingCourses.add(course); // Add new courses without removing existing ones
        }
        studentRepository.save(student);
    }

    @Override
    public Set<Course> getStudentCourses(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        return student.getCourses();
    }

    @Transactional
    public void removeCourseFromStudent(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));

        if (student.getCourses().contains(course)) {
            student.getCourses().remove(course);
            studentRepository.save(student);
        } else {
            throw new RuntimeException("Student is not enrolled in this course");
        }
    }



}
