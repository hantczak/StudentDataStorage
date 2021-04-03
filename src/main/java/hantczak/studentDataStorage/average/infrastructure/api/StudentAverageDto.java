package hantczak.studentDataStorage.average.infrastructure.api;

import lombok.Value;

@Value
public class StudentAverageDto {
    double average;
    long studentId;
}
