package hantczak.studentDataStorage.average.domain;

import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(
        name = "average",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "student_ID_unique",
                        columnNames = "studentId"
                )
        }
)
public class StudentAverage {
    @Id
    @SequenceGenerator(
            name = "average_sequence",
            sequenceName = "average_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "average_sequence"
    )
    @Nullable
    long id;
    @Column(
            name = "average",
            nullable = false,
            columnDefinition = "float8"
    )
    private double average;
    @Column(
            name = "studentId",
            nullable = false,
            columnDefinition = "int8"
    )
    private long studentId;

    public StudentAverage(double average, long studentId) {
        this.average = average;
        this.studentId = studentId;
    }
}
