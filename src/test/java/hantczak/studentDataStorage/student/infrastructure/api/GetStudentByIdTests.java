package hantczak.studentDataStorage.student.infrastructure.api;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.utils.StudentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("integration")
public class GetStudentByIdTests extends StudentDataStorageApplicationTests {

    @Test
    @DisplayName("Should return registered student")
    void shouldReturnStudents() {
        //given
        String url = buildUrlWithPathArgumentForStudent(1L);
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();

        //when
        restTemplate.postForEntity(buildUrl("students"), student, String.class);
        ResponseEntity responseFromController = restTemplate.getForEntity(url, StudentDto.class);
        StudentDto expectedResponse = StudentMapper.toDto(student);

        //then
        Assertions.assertEquals(expectedResponse, responseFromController.getBody());
        Assertions.assertEquals(responseFromController.getStatusCode(), HttpStatus.OK);
    }
}
