package com.example.demo.dao;

import com.example.demo.dao.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentRepository {
    List<Student> getAllStudents();
    Student getStudent(long studentId);
    void addStudent(Student student);
    boolean updateStudentData(long studentId,Student student);
    boolean deleteStudent(long studentId);
}
