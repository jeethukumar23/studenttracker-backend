package com.studenttracker.controller;

import com.studenttracker.model.Teacher;
import com.studenttracker.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository repo;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return repo.findAll();
    }

    @PostMapping
    public Teacher createOrUpdateTeacher(@RequestBody Teacher teacher) {
        return repo.save(teacher);
    }
}
