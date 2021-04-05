package hantczak.studentDataStorage.student.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "student_email_unique",
                        columnNames = "email"
                )
        }
)
public class Student {

    @Id
    @Column(
            name="id",
            nullable = false,
            updatable = false
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            name = "date_of_birth",
            nullable = false,
            columnDefinition = "date"
    )
    private LocalDate dateOfBirth;
    @Column(
            name = "age"
    )
    private Integer age;
    @Column(
            name = "gender",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private Gender gender;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(email, student.email) &&
                Objects.equals(dateOfBirth, student.dateOfBirth) &&
                Objects.equals(age, student.age) &&
                gender == student.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, dateOfBirth, age, gender);
    }
}


