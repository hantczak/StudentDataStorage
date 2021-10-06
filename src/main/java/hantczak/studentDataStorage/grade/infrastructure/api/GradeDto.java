package hantczak.studentDataStorage.grade.infrastructure.api;

import hantczak.studentDataStorage.grade.domain.GradeScale;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class GradeDto {
    private long gradeId;
    private GradeScale gradeScale;
    private long studentId;
    private LocalDate insertionDate;
    private int gradeWeight;

    public GradeDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeDto gradeDto = (GradeDto) o;
        return gradeId == gradeDto.gradeId && studentId == gradeDto.studentId && gradeWeight == gradeDto.gradeWeight && gradeScale == gradeDto.gradeScale && Objects.equals(insertionDate, gradeDto.insertionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradeId, gradeScale, studentId, insertionDate, gradeWeight);
    }

    @Override
    public String toString() {
        return "GradeDto{" +
                "gradeId=" + gradeId +
                ", gradeScale=" + gradeScale +
                ", studentId=" + studentId +
                ", insertionDate=" + insertionDate +
                ", gradeWeight=" + gradeWeight +
                '}';
    }
}
