package com.studenttracker.service;

import com.studenttracker.model.Grade;
import com.studenttracker.repository.GradeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository repo;

    public Grade save(Grade g) {
        return repo.save(g);
    }

    public List<Grade> getAll() {
        return repo.findAll();
    }

    public List<Grade> findByStudentId(Integer studentId) {
        return repo.findByStudentId(studentId);
    }
}
