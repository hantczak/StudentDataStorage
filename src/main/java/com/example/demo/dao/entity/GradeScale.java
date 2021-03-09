package com.example.demo.dao.entity;

public enum GradeScale {
    FAIL(1),
    SUFFICIENT(2),
    SATISFACTORY(3),
    GOOD(4),
    VERY_GOOD(5),
    EXCELLENT(6);

    private int gradeValue;

    GradeScale(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public int getGradeValue() {
        return this.gradeValue;
    }
}
