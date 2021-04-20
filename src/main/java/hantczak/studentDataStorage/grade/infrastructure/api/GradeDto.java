package hantczak.studentDataStorage.grade.infrastructure.api;

import hantczak.studentDataStorage.grade.domain.GradeScale;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class GradeDto {
    private final long gradeId;
    private final GradeScale gradeScale;
    private final long studentId;
    private final LocalDate insertionDate;
    private final int gradeWeight;
}
