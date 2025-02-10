package com.student_management_server.course.service.Impl;

import com.student_management_server.course.dto.CourseDto;
import com.student_management_server.course.entity.Course;
import com.student_management_server.course.mapper.CourseMapper;
import com.student_management_server.course.repository.CourseRepository;
import com.student_management_server.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService  {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course course = CourseMapper.mapToCourse(courseDto);
        Course savedCourse = courseRepository.save(course);
        return CourseMapper.mapToCourseDto(savedCourse);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(CourseMapper::mapToCourseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        return course.map(CourseMapper::mapToCourseDto) // If found, convert to courseDto
                .orElse(null); // If not found, return null
    }

    @Override
    public boolean deleteCourse(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId); // Try to fetch the user by ID
        if (course.isPresent()) {
            courseRepository.delete(course.get()); // If found, delete the user
            return true; // Return true if deletion was successful
        }
        return false; // Return false if the user was not found
    }

    @Override
    public CourseDto updateCourse(Long courseId, CourseDto courseUpdateDto) {
        Optional<Course> existingCourseOptional = courseRepository.findById(courseId);
        if (existingCourseOptional.isPresent()) {
            Course existingCourse = existingCourseOptional.get();

            // Update fields selectively only if they are not null
            if (courseUpdateDto.getCourseName() != null)
                existingCourse.setCourseName(courseUpdateDto.getCourseName());
            if (courseUpdateDto.getDescription() != null)
                existingCourse.setDescription(courseUpdateDto.getDescription());
            if (courseUpdateDto.getCredits() != null)
                existingCourse.setCredits(courseUpdateDto.getCredits());

            // Save the updated entity
            Course savedCourse = courseRepository.save(existingCourse);
            return CourseMapper.mapToCourseDto(savedCourse);
        } else {
            throw new RuntimeException("Course not found with id " + courseId);
        }
    }

}
