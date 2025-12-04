package com.studenttracker.controller;

import com.studenttracker.model.User;
import com.studenttracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        String res = authService.signup(user);
        if ("EMAIL_EXISTS".equals(res)) return "EMAIL_EXISTS";
        return "SUCCESS";
    }

    @PostMapping("/login")
    public Object login(@RequestBody User loginRequest) {
        var loggedUser = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (loggedUser == null) {
            return "INVALID_CREDENTIALS";
        }
        return loggedUser;
    }
}
