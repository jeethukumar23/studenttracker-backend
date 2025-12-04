package com.studenttracker.controller;

import com.studenttracker.model.Attendance;
import com.studenttracker.model.Student;
import com.studenttracker.repository.AttendanceRepository;
import com.studenttracker.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:8080")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private StudentRepository studentRepo;

    // 🟠 Save attendance from teacher dashboard
    @PostMapping
    public List<Attendance> saveBulkAttendance(
            @RequestBody Map<String, List<Map<String, Object>>> body) {

        List<Map<String, Object>> list = body.get("records");
        List<Attendance> saved = new ArrayList<>();

        for (Map<String, Object> record : list) {

            Object sid = record.get("studentId");
            if (sid == null) continue; // ⛔ prevent "student_id cannot be null"

            Attendance a = new Attendance();
            a.setStudentId(Integer.parseInt(sid.toString()));
            a.setDate((String) record.get("date"));
            a.setStatus((String) record.get("status"));

            Object tid = record.get("teacherId");
            if (tid != null) {
                a.setTeacherId(Integer.parseInt(tid.toString()));
            }

            saved.add(attendanceRepo.save(a));
        }

        return saved;
    }

    // 🟠 Fetch attendance by USER ID (student login page)
    @GetMapping("/user/{userId}")
    public Map<String, Object> getAttendanceByUserId(@PathVariable int userId) {

        Map<String, Object> resp = new HashMap<>();

        Student student = studentRepo.findByUserId(userId);

        if (student == null) {
            resp.put("records", List.of());
            resp.put("percentage", 0);
            return resp;
        }

        List<Attendance> records = attendanceRepo.findByStudentId(student.getId());

        long total = records.size();
        long present = records.stream()
                .filter(r -> r.getStatus().equals("present"))
                .count();

        double percent = total > 0 ? (present * 100.0 / total) : 0;

        resp.put("records", records);
        resp.put("percentage", percent);

        return resp;
    }

    // 🟠 Optional: Admin/Teacher debug
    @GetMapping("/all")
    public List<Attendance> getAll() {
        return attendanceRepo.findAll();
    }
}
