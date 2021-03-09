package com.example.demo.api;

import com.example.demo.dao.entity.Student;
import com.example.demo.services.StudentService;
import com.example.demo.services.StudentSortService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/students")

public class StudentController {
    private final StudentService studentService;
    private final StudentSortService studentSortService;

    public StudentController(StudentService studentService,StudentSortService studentSortService) {
        this.studentService = studentService;
        this.studentSortService=studentSortService;
    }

    @GetMapping("/{ID}")
    public ResponseEntity<Student> getStudentByIdPath(@PathVariable(value = "ID") int studentId) {
        Optional<Student> student = studentService.getStudent(studentId);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
            //new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<StudentResponse> getAllStudents(@RequestParam(value = "sortType", required = false, defaultValue = "NAME_ASC") StudentSortTypes studentSortTypes) {

        return new ResponseEntity<>(new StudentResponse(studentSortService.getSortedStudents(studentSortTypes)), HttpStatus.OK);
    }

    @GetMapping("/byId")
    public ResponseEntity<Student> getStudent(@RequestParam(value = "ID") int studentId) {
        Optional<Student> student = studentService.getStudent(studentId);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.ok(true);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateStudentData(@RequestParam long studentId, @RequestBody Student student) {
        boolean ifUpdated = studentService.updateStudentData(studentId, student);
        if (ifUpdated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteStudentAndHisGrades(@RequestParam int studentId) {
        boolean ifDeleted = studentService.deleteStudentAndHisGrades(studentId);
        if (ifDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
