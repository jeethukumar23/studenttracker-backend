package com.studenttracker.dto;

public class StudentResponse {

    private int id;     // CHANGED
    private String name;
    private String email;

    public StudentResponse(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
