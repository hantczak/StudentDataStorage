package com.example.demo.student.infrastructure.api;

import com.example.demo.student.domain.Student;

import java.util.List;

public class StudentResponse {
    private List<StudentDto> students;
    private int studentCount;

    public StudentResponse(List<StudentDto> students){
        this.students = students;
        this.studentCount= students.size();
    }

    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
}
