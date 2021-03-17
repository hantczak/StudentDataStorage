package com.example.demo.student.infrastructure.api;

import com.example.demo.student.domain.Gender;
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
