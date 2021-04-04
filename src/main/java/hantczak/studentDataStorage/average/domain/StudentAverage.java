package hantczak.studentDataStorage.average.domain;

import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class StudentAverage {
    @Id
    @SequenceGenerator(
            name="average_sequence",
            sequenceName = "average_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "average_sequence"
    )
    @Nullable
    int id;
    private final double average;
    private final long studentId;
}
