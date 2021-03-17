package com.example.demo.grade.infrastructure.api;

import com.example.demo.grade.domain.GradeScale;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class GradeDto {
    private final int gradeId;
    private final GradeScale gradeScale;
    private final long studentId;
    private final LocalDate insertionDate;
    private final int gradeWeight;
}
