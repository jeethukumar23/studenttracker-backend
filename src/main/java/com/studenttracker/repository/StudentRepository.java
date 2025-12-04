package com.studenttracker.repository;

import com.studenttracker.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByUserId(Integer userId);
}
