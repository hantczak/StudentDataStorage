package com.example.demo.dao.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Grade{
    private final int gradeId;
    private final GradeScale gradeScale;
    private final long studentId;
    private final LocalDate insertionDate;
    private final int gradeWeight;

    public Grade(int gradeId, GradeScale gradeScale, long studentId, LocalDate insertionDate, int gradeWeight) {
        this.gradeId = gradeId;
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

    public int getGradeValue() {
        return this.gradeScale.getGradeValue();
    }

    public int getGradeId() {
        return gradeId;
    }

    public LocalDate getInsertionDate() {
        return insertionDate;
    }

    public int getGradeWeight() {
        return gradeWeight;
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