package com.studenttracker.repository;

import com.studenttracker.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByUserId(Integer userId);
}
