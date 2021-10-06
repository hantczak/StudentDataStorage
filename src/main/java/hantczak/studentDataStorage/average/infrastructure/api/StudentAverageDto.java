package hantczak.studentDataStorage.average.infrastructure.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentAverageDto {
    double average;
    long studentId;

    public StudentAverageDto() {
    }
}
