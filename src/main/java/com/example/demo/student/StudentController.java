package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/students")

public class StudentController {

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private final StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping
    public List<Student> getStudent(@RequestParam int index){
        List<Student> student = new ArrayList<>();
        student.add(studentService.getStudent(index).get(0));
        return student;
    }

    @PostMapping
    public boolean addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

}
