package com.example.demo.average.domain;

import com.example.demo.grade.domain.Grade;

import java.util.List;

public class StudentAverageCalculator {

    public static StudentAverage createAverage(List<Grade> gradeList) {
        double gradeSum = gradeList.stream()
                .mapToDouble(grade->grade.getGradeWeight()*grade.getGradeScale().getGradeValue())
                .sum();
        return new StudentAverage(gradeSum/gradeList.size(),gradeList.get(0).getStudentId());
    }
}
