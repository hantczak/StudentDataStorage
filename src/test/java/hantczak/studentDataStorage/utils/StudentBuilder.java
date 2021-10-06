package hantczak.studentDataStorage.utils;

import hantczak.studentDataStorage.student.domain.Gender;
import hantczak.studentDataStorage.student.domain.Student;

import java.time.LocalDate;

public class StudentBuilder {
    private Long id = 1L;
    private String name = "abc";
    private String email = "abc@gmail.com";
    private LocalDate dateOfBirth = LocalDate.of(2009, 06, 05);
    private Integer age = 12;
    private Gender gender = Gender.MALE;

    public StudentBuilder() {
    }

    public static StudentBuilder create(){
        return new StudentBuilder();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Student build(){
        return new Student(id,name,email,dateOfBirth,age,gender);
    }
}
