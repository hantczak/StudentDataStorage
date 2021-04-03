package hantczak.studentDataStorage.student.infrastructure.api;

import hantczak.studentDataStorage.student.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private Integer age;
    private Gender gender;
}
