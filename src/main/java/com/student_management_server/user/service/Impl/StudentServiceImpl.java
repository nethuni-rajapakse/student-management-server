package com.student_management_server.user.service.Impl;

import com.student_management_server.course.entity.Course;
import com.student_management_server.course.repository.CourseRepository;
import com.student_management_server.user.entity.Student;
import com.student_management_server.user.repository.StudentRepository;
import com.student_management_server.user.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void addCoursesToStudent(Long studentId, Set<Long> courseIds) {
        // Find the student by ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Get the existing courses
        Set<Course> existingCourses = student.getCourses();

        // Find and add new courses
        for (Long courseId : courseIds) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            existingCourses.add(course); // Add new courses without removing existing ones
        }

        // Save the updated student entity
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
        // Find the student
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        // Find the course
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

        // Remove the course if it exists
        if (student.getCourses().contains(course)) {
            student.getCourses().remove(course);
            studentRepository.save(student);
        } else {
            throw new RuntimeException("Student is not enrolled in this course");
        }
    }



}
