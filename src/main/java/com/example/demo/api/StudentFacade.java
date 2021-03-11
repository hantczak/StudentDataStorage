package com.example.demo.api;

import com.example.demo.dao.entity.Student;
import com.example.demo.services.StudentService;
import com.example.demo.services.StudentSortService;

import java.util.List;
import java.util.Optional;

public class StudentFacade {
    private final StudentSortService studentSortService;
    private final StudentService studentService;

    public StudentFacade(StudentService studentService, StudentSortService studentSortService){
        this.studentService=studentService;
        this.studentSortService=studentSortService;
    }

    public List<Student> getSortedStudents(StudentSortTypes studentSortType){
        return studentSortService.getSortedStudents(studentSortType);
    }

    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public Optional<Student> getStudent(long id) {
        Optional<Student> firstFoundByIndex;
        firstFoundByIndex = studentService.getStudent(id);
        return firstFoundByIndex;
    }

    public void addStudent(Student student) {
        studentService.addStudent(student);
    }

    public boolean updateStudentData(long studentId, Student student) {
        return studentService.updateStudentData(studentId, student);
    }

    public boolean deleteStudentAndHisGrades(long studentId) {
        return studentService.deleteStudentAndHisGrades(studentId);
    }
}

