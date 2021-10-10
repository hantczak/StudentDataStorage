package hantczak.studentDataStorage.grade.domain;

import lombok.AllArgsConstructor;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(
        name = "grade"
)
public class Grade{

    @Id
    @SequenceGenerator(
            name = "grade_sequence",
            sequenceName = "grade_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "grade_sequence"
    )
    @Column(
            name="id",
            nullable = false,
            updatable = false
    )
    @Nullable
    private long gradeId;
    @Column(
            name = "gradeScale",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private GradeScale gradeScale;
    @Column(
            name = "studentId",
            nullable = false,
            columnDefinition = "int8"
    )
    private long studentId;
    @Column(
            name = "insertionDate",
            nullable = false,
            columnDefinition = "date"
    )
    private LocalDate insertionDate;
    @Column(
            name = "gradeWeight",
            nullable = false,
            columnDefinition = "int4"
    )
    private int gradeWeight;

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

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", gradeScale=" + gradeScale +
                ", studentId=" + studentId +
                ", insertionDate=" + insertionDate +
                ", gradeWeight=" + gradeWeight +
                '}';
    }
}