package com.student_management_server.user.service.Impl;

import com.student_management_server.user.entity.Lecturer;
import com.student_management_server.user.repository.LecturerRepository;
import com.student_management_server.user.service.LecturerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;

    public LecturerServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        List<Lecturer> users = lecturerRepository.findAll(); // Fetch all users from the database
        return new ArrayList<>(users);
    }
}
