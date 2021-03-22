package com.example.demo.average.infrastructure.api;

import com.example.demo.average.domain.StudentAverage;

import java.util.List;
import java.util.stream.Collectors;

public class StudentAverageMapper {

    public static List<StudentAverageDto> StudentAverageListToStudentAverageDtoList(List<StudentAverage> studentAverages) {
        return studentAverages.stream()
                .map(StudentAverageMapper::toDto)
                .collect(Collectors.toList());
    }

    public static StudentAverageDto toDto(StudentAverage studentAverage) {
        return new StudentAverageDto(studentAverage.getAverage(), studentAverage.getStudentId());
    }

    public static StudentAverage fromDto(StudentAverageDto studentAverageDto) {
        return new StudentAverage(studentAverageDto.getAverage(), studentAverageDto.getStudentId());
    }
}
