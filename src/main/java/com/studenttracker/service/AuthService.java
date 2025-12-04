package com.studenttracker.service;

import com.studenttracker.model.User;
import com.studenttracker.model.Student;
import com.studenttracker.repository.UserRepository;
import com.studenttracker.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private StudentRepository studentRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String signup(User user) {

        if (userRepo.existsByEmail(user.getEmail())) {
            return "EMAIL_EXISTS";
        }

        user.setPassword(encoder.encode(user.getPassword()));

        User saved = userRepo.save(user);

        // Create row in students table if role = student
        if ("student".equalsIgnoreCase(saved.getRole())) {
            Student s = new Student();
            s.setUserId(saved.getId());
            studentRepo.save(s);
        }

        return "SUCCESS";
    }

    public User login(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user == null) return null;
        if (!encoder.matches(password, user.getPassword())) return null;
        return user;
    }
}
