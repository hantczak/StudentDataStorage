package hantczak.studentDataStorage.student.infrastructure.api;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.utils.StudentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("integration")
public class UpdateStudentTests extends StudentDataStorageApplicationTests {

    @Test
    @DisplayName("Should update student by Id")
    void shouldUpdateStudentById() {
        //given
        String url = buildUrlWithPathArgumentForStudent(1L);
        StudentBuilder studentBuilder = StudentBuilder.create();

        //when
        Student student = studentBuilder.build();
        studentBuilder.setName("cba");
        studentBuilder.setEmail("cba@gmail.com");
        Student updatedStudent = studentBuilder.build();
        StudentDto expectedStudent = StudentMapper.toDto(student);
        restTemplate.postForEntity(buildUrl("students"), student, String.class);
        StudentDto studentDtoResponseFromController = restTemplate.getForEntity(buildUrlWithPathArgumentForStudent(1L), StudentDto.class).getBody();
        Assertions.assertEquals(expectedStudent, studentDtoResponseFromController);

        //then
        restTemplate.put(buildUrl("students", "studentId", "1"), updatedStudent, Student.class);
        Assertions.assertEquals(StudentMapper.toDto(updatedStudent), restTemplate.getForEntity(url, StudentDto.class).getBody());
    }
}
