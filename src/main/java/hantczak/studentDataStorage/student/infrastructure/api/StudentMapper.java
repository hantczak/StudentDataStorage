package hantczak.studentDataStorage.student.infrastructure.api;

import hantczak.studentDataStorage.student.domain.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {
    public static List<StudentDto> studentListToStudentDtoList(List<Student> studentList) {
        List<StudentDto> studentDtoList = studentList.stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
        return studentDtoList;
    }

    public static Student fromDto(StudentDto studentDto) {
        Student student = new Student(studentDto.getId(),
                studentDto.getName(),
                studentDto.getEmail(),
                studentDto.getDateOfBirth(),
                studentDto.getAge(),
                studentDto.getGender());
        return student;
    }


    public static StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto(student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDateOfBirth(),
                student.getAge(),
                student.getGender());
        return studentDto;
    }
}

