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

import java.time.LocalDate;
import java.util.List;

@Tag("integration")
class GetAllStudentsTests extends StudentDataStorageApplicationTests {

    @Test
    @DisplayName("Should return registered student")
    void shouldReturnStudents() {
        //given
        String url = buildUrl("students");
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student clientSentStudent = studentBuilder.build();

        Student expectedStudent = studentBuilder.setId(1L).build();

        //when
        restTemplate.postForEntity(url, clientSentStudent, String.class);
        List<StudentDto> studentDtoList = StudentMapper.studentListToStudentDtoList(List.of(expectedStudent));
        StudentResponse expectedResponse = new StudentResponse(studentDtoList);
        ResponseEntity<StudentResponse> responseEntity = restTemplate.getForEntity(url, StudentResponse.class);
        StudentResponse studentResponseFromController = responseEntity.getBody();

        //then
        Assertions.assertEquals(expectedResponse, studentResponseFromController);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("Should return registered students in alphabetical order")
    void shouldReturnStudentsInAlphabeticalOrder() {
        //given
        String url = buildUrl("students", "sortType", "NAME_ASC");
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student clientSentStudent = studentBuilder.build();
        Student expectedStudent = studentBuilder
                .setId(1L)
                .build();

        Student clientSentStudent1 = studentBuilder.setName("bca")
                .setEmail("bca@gmail.com")
                .setId(null)
                .build();

        Student expectedStudent1 = studentBuilder.setId(2L).build();

        //when
        restTemplate.postForEntity(url, clientSentStudent, String.class);
        restTemplate.postForEntity(url, clientSentStudent1, String.class);
        List<StudentDto> studentDtoList = StudentMapper.studentListToStudentDtoList(List.of(expectedStudent, expectedStudent1));
        StudentResponse expectedResponse = new StudentResponse(studentDtoList);
        ResponseEntity<StudentResponse> responseEntity = restTemplate.getForEntity(url, StudentResponse.class);
        StudentResponse studentResponseFromController = responseEntity.getBody();

        //then
        Assertions.assertEquals(expectedResponse, studentResponseFromController);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("Should return registered students ordered by age")
    void shouldReturnStudentsOrderedByAge() {
        //given
        String url = buildUrl("students", "sortType", "AGE_DSC");
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student clientSentStudent = studentBuilder.build();
        Student expectedStudent = studentBuilder
                .setId(1L)
                .build();

        Student clientSentStudent1 = studentBuilder
                .setDateOfBirth(LocalDate.of(2008, 06, 05))
                .setAge(13)
                .setEmail("bca@gmail.com")
                .setId(null)
                .build();

        Student expectedStudent1 = studentBuilder.setId(2L).build();

        //when
        restTemplate.postForEntity(url, clientSentStudent, String.class);
        restTemplate.postForEntity(url, clientSentStudent1, String.class);
        List<StudentDto> studentDtoList = StudentMapper.studentListToStudentDtoList(List.of(expectedStudent1, expectedStudent));
        StudentResponse expectedResponse = new StudentResponse(studentDtoList);
        ResponseEntity<StudentResponse> responseEntity = restTemplate.getForEntity(url, StudentResponse.class);
        StudentResponse studentResponseFromController = responseEntity.getBody();

        //then
        Assertions.assertEquals(expectedResponse, studentResponseFromController);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}
