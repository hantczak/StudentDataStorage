package com.example.demo.student.domain;

import com.example.demo.average.domain.StudentAverageFacade;
import com.example.demo.grade.domain.GradeFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentValidator studentValidator;

    private final List<StudentDeletedListener> listeners = new ArrayList<>();

    public StudentService(StudentRepository studentRepository, StudentValidator studentValidator) {
        this.studentRepository = studentRepository;
        this.studentValidator = studentValidator;
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public Optional<Student> getStudent(long id) {
        Optional<Student> firstFoundByIndex;
        firstFoundByIndex = Optional.ofNullable(studentRepository.getStudent(id));
        return firstFoundByIndex;
    }

    public void addStudent(Student student) {
        studentValidator.validateStudent(student);
        studentRepository.addStudent(student);
    }

    public boolean updateStudentData(long studentId, Student student) {
        studentValidator.validateStudent(student);
        return studentRepository.updateStudentData(studentId, student);
    }

    public boolean deleteStudentAndHisGrades(long studentId) {
        boolean ifDeleted = studentRepository.deleteStudent(studentId);
        if (ifDeleted) {
            listeners.forEach(listener -> listener.onStudentDelete(studentId));
        }
        return ifDeleted;
    }

    public void addListener(StudentDeletedListener studentDeletedListener) {
        listeners.add(studentDeletedListener);
    }
}