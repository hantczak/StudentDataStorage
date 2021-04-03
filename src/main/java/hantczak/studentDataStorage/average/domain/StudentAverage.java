package hantczak.studentDataStorage.average.domain;

import lombok.Value;

@Value
public class StudentAverage {
    double average;
    long studentId;
}
