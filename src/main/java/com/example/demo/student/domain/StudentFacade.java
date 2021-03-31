package com.example.demo.student.domain;

import com.example.demo.average.domain.StudentAverageFacade;
import com.example.demo.grade.domain.GradeFacade;

import java.util.List;
import java.util.Optional;

public class StudentFacade {
    private final StudentSortService studentSortService;
    private final StudentService studentService;

    public StudentFacade(StudentService studentService, StudentSortService studentSortService) {
        this.studentService = studentService;
        this.studentSortService = studentSortService;

    }

    public List<Student> getSortedStudents(StudentSortTypes studentSortType) {
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

    public void addListener(StudentDeletedListener studentDeletedListener){
        studentService.addListener(studentDeletedListener);
    }
}

