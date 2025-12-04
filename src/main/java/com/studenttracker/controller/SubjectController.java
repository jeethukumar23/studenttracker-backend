package com.studenttracker.controller;

import com.studenttracker.model.Subject;
import com.studenttracker.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin(origins = "http://localhost:8080")
public class SubjectController {
    @Autowired
    private SubjectRepository repo;

    @GetMapping
    public List<Subject> all() {
        return repo.findAll();
    }

    @PostMapping
    public Subject create(@RequestBody Subject s) {
        return repo.save(s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
