package com.example.demo.average.infrastructure.api;

import lombok.Getter;

import java.util.List;

@Getter
public class StudentAverageResponse {
    private final List<StudentAverageDto> studentAverageDtoList;
    private final int count;

    public StudentAverageResponse(List<StudentAverageDto> studentAverageDtoList){
        this.studentAverageDtoList=studentAverageDtoList;
        this.count = studentAverageDtoList.size();
    }
}
