package com.example.demo.database;

import com.example.demo.student.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> getAllStudents();
    Student getStudent(long studentId);
    void addStudent(Student student);
    void updateStudentData(long studentId,Student student);
    void deleteStudent(long studentId);
}
