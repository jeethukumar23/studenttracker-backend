package com.studenttracker.controller;

import com.studenttracker.model.Grade;
import com.studenttracker.model.Student;
import com.studenttracker.repository.GradeRepository;
import com.studenttracker.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/grades")
@CrossOrigin(origins = "http://localhost:8080")
public class GradeController {

    @Autowired
    private GradeRepository gradeRepo;

    @Autowired
    private StudentRepository studentRepo;

    // 🟢 BULK SAVE GRADES (your frontend needs this!)
    @PostMapping
    public List<Grade> saveBulkGrades(@RequestBody Map<String, List<Map<String, Object>>> body) {

        List<Map<String, Object>> records = body.get("records");

        List<Grade> saved = new ArrayList<>();

        for (Map<String, Object> r : records) {

            Grade g = new Grade();
            g.setStudentId((Integer) r.get("studentId"));
            g.setSubjectId((Integer) r.get("subjectId"));
            g.setScore(Double.valueOf(r.get("score").toString()));
            g.setTeacherId((Integer) r.get("teacherId"));

            saved.add(gradeRepo.save(g));
        }

        return saved;
    }

    // 🟢 Get ALL grades (admin)
    @GetMapping("/all")
    public List<Grade> all() {
        return gradeRepo.findAll();
    }

    // 🟢 Student fetch their grades → using USER ID
    @GetMapping("/user/{userId}")
    public List<Grade> getByUser(@PathVariable Integer userId) {

        Student student = studentRepo.findByUserId(userId);

        if (student == null) {
            return List.of();
        }

        return gradeRepo.findByStudentId(student.getId());
    }

    // 🟢 Fetch using student table ID
    @GetMapping("/student/{studentId}")
    public List<Grade> getByStudent(@PathVariable Integer studentId) {
        return gradeRepo.findByStudentId(studentId);
    }
}
