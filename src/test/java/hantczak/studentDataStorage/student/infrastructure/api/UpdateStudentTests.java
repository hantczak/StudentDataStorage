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
        Student clientSentStudent = studentBuilder.build();
        Student updatedStudent = studentBuilder
                .setName("cba")
                .setEmail("cba@gmail.com")
                .build();

        Student expectedStudent = studentBuilder.setId(1L).build();
        StudentDto expectedStudentDto = StudentMapper.toDto(expectedStudent);

        //when
        restTemplate.postForEntity(buildUrl("students"), clientSentStudent, String.class);

        //then
        restTemplate.put(buildUrl("students", "studentId", "1"), updatedStudent, Student.class);
        Assertions.assertEquals(expectedStudentDto, restTemplate.getForEntity(url, StudentDto.class).getBody());
    }
}
