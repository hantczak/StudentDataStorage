package com.example.demo.grade.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Grade{
    private final int gradeId;
    private final GradeScale gradeScale;
    private final long studentId;
    private final LocalDate insertionDate;
    private final int gradeWeight;

    public int getGradeValue(){
        return gradeScale.getGradeValue();
    }
}