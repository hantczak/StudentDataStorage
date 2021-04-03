package hantczak.studentDataStorage.grade.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@Getter
public class Grade{
    private final int gradeId;
    private final GradeScale gradeScale;
    private final long studentId;
    private final LocalDate insertionDate;
    private final int gradeWeight;

    public int getGradeValue(){
        return gradeScale.getGradeValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return gradeId == grade.gradeId &&
                studentId == grade.studentId &&
                gradeWeight == grade.gradeWeight &&
                gradeScale == grade.gradeScale &&
                Objects.equals(insertionDate, grade.insertionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradeId, gradeScale, studentId, insertionDate, gradeWeight);
    }
}