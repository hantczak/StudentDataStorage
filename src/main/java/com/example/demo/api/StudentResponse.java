package com.example.demo.api;

import com.example.demo.dao.entity.Student;

import java.util.List;

public class StudentResponse {
    private List<Student> studentList;
    private int studentCount;

    public StudentResponse(List<Student> studentList){
        this.studentList=studentList;
        this.studentCount=studentList.size();
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
}
