package com.example.demo.dao.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Grade {
    private final GradeScale gradeScale;
    private final long studentId;
    private LocalDate insertionDate;
    private int gradeWeight;

    public Grade(GradeScale gradeScale, long studentId, LocalDate insertionDate, int gradeWeight) {
        this.gradeScale = gradeScale;
        this.studentId = studentId;
        this.insertionDate = insertionDate;
        this.gradeWeight = gradeWeight;
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

    public LocalDate getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(LocalDate insertionDate) {
        this.insertionDate = insertionDate;
    }

    public int getGradeWeight() {
        return gradeWeight;
    }

    public void setGradeWeight(int gradeWeight) {
        this.gradeWeight = gradeWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return studentId == grade.studentId &&
                gradeWeight == grade.gradeWeight &&
                gradeScale == grade.gradeScale &&
                insertionDate.equals(grade.insertionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, insertionDate, gradeWeight);
    }
}





