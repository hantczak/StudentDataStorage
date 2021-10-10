package hantczak.studentDataStorage.utils;

import hantczak.studentDataStorage.student.domain.Gender;
import hantczak.studentDataStorage.student.domain.Student;

import java.time.LocalDate;

public class StudentBuilder {
    private Long id = null;
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

    public StudentBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public StudentBuilder setAge(Integer age) {
        this.age = age;
        return this;
    }

    public StudentBuilder setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Student build(){
        Student student = new Student();
        if(this.id!=null){
            student.setId(id);
        }
        student.setName(name);
        student.setEmail(email);
        student.setDateOfBirth(dateOfBirth);
        student.setAge(age);
        student.setGender(gender);
        return student;
    }
}
