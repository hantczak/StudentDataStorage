package com.example.demo.dao.entity;

public class Grade {
    private final GradeScale gradeScale;
    private final long studentId;

    public Grade(GradeScale gradeScale, long studentId) {
        this.gradeScale = gradeScale;
        this.studentId = studentId;
    }

    public GradeScale getGradeScale() {
        return gradeScale;
    }

    public long getStudentId() {
        return studentId;
    }

    public int getGradeValue(){
        return this.gradeScale.getGradeValue();
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeScale=" + gradeScale +
                ", studentId=" + studentId +
                '}';
    }
}




