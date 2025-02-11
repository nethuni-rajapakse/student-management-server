package com.student_management_server.user.entity;


import com.student_management_server.course.entity.Course;
import com.student_management_server.department.entity.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue("Lecturer")
public class Lecturer extends User {



}
