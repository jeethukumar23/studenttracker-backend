package com.studenttracker.controller;

import com.studenttracker.dto.StudentResponse;
import com.studenttracker.model.Student;
import com.studenttracker.model.User;
import com.studenttracker.repository.StudentRepository;
import com.studenttracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:8080")
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        List<StudentResponse> result = new ArrayList<>();

        for (Student s : students) {
            User u = userRepo.findById(s.getUserId()).orElse(null);
            if (u != null) {
                result.add(new StudentResponse(
                        s.getId(),
                        u.getName(),
                        u.getEmail()
                ));
            }
        }
        return result;
    }
}
