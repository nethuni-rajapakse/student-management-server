package com.student_management_server.course.service.Impl;

import com.student_management_server.course.dto.CourseDTO;
import com.student_management_server.course.entity.Course;
import com.student_management_server.course.mapper.CourseMapper;
import com.student_management_server.course.repository.CourseRepository;
import com.student_management_server.course.service.CourseService;
import com.student_management_server.department.entity.Department;
import com.student_management_server.department.repository.DepartmentRepository;
import com.student_management_server.user.entity.Lecturer;
import com.student_management_server.user.repository.LecturerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService  {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final LecturerRepository lecturerRepository;

    public CourseServiceImpl(CourseRepository courseRepository, DepartmentRepository departmentRepository, LecturerRepository lecturerRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
        this.lecturerRepository = lecturerRepository;
    }



    @Override
    public CourseDTO createCourse(CourseDTO courseDto) {
        Department department = departmentRepository.findById(courseDto.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department not found with given Id"));
        Course course = CourseMapper.mapToCourse(courseDto, department);

        try {
            Course savedCourse = courseRepository.save(course);
            return CourseMapper.mapToCourseDTO(savedCourse);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Course name already exists: " + course.getCourseName());
        }
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(CourseMapper::mapToCourseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with the given Id"));
        return CourseMapper.mapToCourseDTO(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with the given Id"));
        courseRepository.delete(course);
    }

    @Override
    public CourseDTO updateCourse(Long courseId, CourseDTO courseUpdateDto) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with the given Id"));

        if (courseUpdateDto.getCourseName() != null) existingCourse.setCourseName(courseUpdateDto.getCourseName());
        if (courseUpdateDto.getDescription() != null) existingCourse.setDescription(courseUpdateDto.getDescription());
        if (courseUpdateDto.getCredits() != null) existingCourse.setCredits(courseUpdateDto.getCredits());

        //Check if the department exists before updating
        if (courseUpdateDto.getDepartmentId()!= null) {
            Department department = departmentRepository.findById(courseUpdateDto.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found with given Id"));
            existingCourse.setDepartment(department);
        }
        Course savedCourse = courseRepository.save(existingCourse);
        return CourseMapper.mapToCourseDTO(savedCourse);

    }

    @Override
    @Transactional
    public void addLecturersToCourse(Long courseId, Set<Long> lecturerIds) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with the given id"));

        Set<Lecturer> existingLecturers = course.getLecturers();

        for (Long lecturerId : lecturerIds) {
            Lecturer lecturer = lecturerRepository.findById(lecturerId)
                    .orElseThrow(() -> new EntityNotFoundException("Lecturer not found with the given id :" + lecturerId ));
            if (lecturer.getDepartment().getDepartmentId().equals(course.getDepartment().getDepartmentId())) {
                existingLecturers.add(lecturer);
            }
            else throw new EntityNotFoundException("The department of lecturer with ID " + lecturerId + " does not match the department of the course with ID " + courseId + ". Ensure the lecturer belongs to the correct department.");
        }
        courseRepository.save(course);
    }

    @Override
    public Set<Lecturer> getLecturersByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with the given id"));
        return course.getLecturers();
    }

    @Override
    public void removeLecturerFromCourse(Long lecturerId, Long courseId) {

        Lecturer lecturer = lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> new EntityNotFoundException("Lecturer not found with the given id"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with the given id"));

        if (course.getLecturers().contains(lecturer)) {
            course.getLecturers().remove(lecturer);
            courseRepository.save(course);
        } else {
            throw new RuntimeException("Lecturer is not assigned in this course");
        }
    }



}
