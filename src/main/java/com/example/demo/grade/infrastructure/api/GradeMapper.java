package com.example.demo.grade.infrastructure.api;

import com.example.demo.grade.domain.Grade;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GradeMapper {

    public static List<GradeDto> gradeListToGradeDtoList(List<Grade> gradeList){
        List<GradeDto> gradeDtoList = gradeList.stream()
                .map(GradeMapper::toDto)
                .collect(Collectors.toList());
        return gradeDtoList;
    }

    public static GradeDto toDto(Grade grade) {
        GradeDto gradeDto = new GradeDto(grade.getGradeId(),
                grade.getGradeScale(),
                grade.getStudentId(),
                grade.getInsertionDate(),
                grade.getGradeWeight());
        return gradeDto;
    }

    public static Grade fromDto(GradeDto gradeDto) {
        Grade grade = new Grade(gradeDto.getGradeId(),
                gradeDto.getGradeScale(),
                gradeDto.getStudentId(),
                gradeDto.getInsertionDate(),
                gradeDto.getGradeWeight());
        return grade;
    }
}
