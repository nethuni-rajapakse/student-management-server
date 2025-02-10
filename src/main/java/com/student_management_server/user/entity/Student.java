package com.student_management_server.user.entity;

import com.student_management_server.course.entity.Course;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Student")
public class Student extends User {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_course",  // This is the join table to hold the relationship
            joinColumns = @JoinColumn(name = "student_id"),  // Student's foreign key
            inverseJoinColumns = @JoinColumn(name = "course_id")  // Course's foreign key
    )
    private Set<Course> courses = new HashSet<>();  // Default to an empty set

}
