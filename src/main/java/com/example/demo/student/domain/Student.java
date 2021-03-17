package com.example.demo.student.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Student {

    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private Integer age;
    private Gender gender;
}


