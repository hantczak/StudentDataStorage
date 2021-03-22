package com.example.demo.average.infrastructure.api;

import lombok.Value;

@Value
public class StudentAverageDto {
    double average;
    long studentId;
}
