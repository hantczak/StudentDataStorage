package hantczak.studentDataStorage.student.infrastructure.api;

import hantczak.studentDataStorage.student.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private Integer age;
    private Gender gender;

    public StudentDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto that = (StudentDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(age, that.age) && gender == that.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, dateOfBirth, age, gender);
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
