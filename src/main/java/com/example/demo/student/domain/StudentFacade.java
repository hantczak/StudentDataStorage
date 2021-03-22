package com.example.demo.student.domain;

import com.example.demo.average.domain.StudentAverageFacade;
import com.example.demo.grade.domain.GradeFacade;

import java.util.List;
import java.util.Optional;

public class StudentFacade {
    private final StudentSortService studentSortService;
    private final StudentService studentService;
    private final StudentAverageFacade studentAverageFacade;
    private final GradeFacade gradeFacade;

    public StudentFacade(StudentService studentService, StudentSortService studentSortService,StudentAverageFacade studentAverageFacade,GradeFacade gradeFacade){
        this.studentService=studentService;
        this.studentSortService=studentSortService;
        this.studentAverageFacade = studentAverageFacade;
        this.gradeFacade = gradeFacade;
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
        boolean ifSuccessful =  studentService.deleteStudentAndHisGrades(studentId);
        if(ifSuccessful) {
            gradeFacade.deleteStudentGrades(studentId);
            studentAverageFacade.deleteAverage(studentId);
        }
        return ifSuccessful;
    }
}

