package com.example.demo.student.infrastructure.api;

import com.example.demo.student.domain.Student;

import java.util.List;

public class StudentResponse {
    private List<Student> students;
    private int studentCount;

    public StudentResponse(List<Student> students){
        this.students = students;
        this.studentCount= students.size();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
}
