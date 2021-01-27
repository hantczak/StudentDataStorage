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
    public List<Student> getStudent(@RequestParam int studentId){
        List<Student> student = new ArrayList<>();
        student.add(studentService.getStudent(studentId).get(0));
        return student;
    }

    @PostMapping
    public boolean addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return true;
    }

    @PutMapping
    public boolean updateStudentData(@RequestParam long studentId,@RequestBody Student student){
        studentService.updateStudentData(studentId,student);
        return true;
    }

    @DeleteMapping
    public boolean addStudent(@RequestParam int index){
        studentService.deleteStudent(index);
        return true;
    }
}
