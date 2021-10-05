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
import org.springframework.web.client.HttpClientErrorException;

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
        Student student = studentBuilder.build();

        //when
        System.out.println(url);
        restTemplate.postForEntity(url, student, String.class);
        List<StudentDto> studentDtoList = StudentMapper.studentListToStudentDtoList(List.of(student));
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
        Student student = studentBuilder.build();
        studentBuilder.setName("bca");
        studentBuilder.setId(2L);
        studentBuilder.setEmail("bca@gmail.com");
        Student student1 = studentBuilder.build();

        //when
        restTemplate.postForEntity(url, student, String.class);
        restTemplate.postForEntity(url, student1, String.class);
        List<StudentDto> studentDtoList = StudentMapper.studentListToStudentDtoList(List.of(student, student1));
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
        Student student = studentBuilder.build();
        studentBuilder.setDateOfBirth(LocalDate.of(2010, 06, 05));
        studentBuilder.setAge(11);
        studentBuilder.setId(2L);
        studentBuilder.setEmail("bca@gmail.com");
        Student student1 = studentBuilder.build();

        //when
        restTemplate.postForEntity(url, student, String.class);
        restTemplate.postForEntity(url, student1, String.class);
        List<StudentDto> studentDtoList = StudentMapper.studentListToStudentDtoList(List.of(student, student1));
        StudentResponse expectedResponse = new StudentResponse(studentDtoList);
        ResponseEntity<StudentResponse> responseEntity = restTemplate.getForEntity(url, StudentResponse.class);
        StudentResponse studentResponseFromController = responseEntity.getBody();

        //then
        Assertions.assertEquals(expectedResponse, studentResponseFromController);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("Should return status code 422 for duplicate email")
    void shouldReturn422ForDuplicateEmail() {
        //given
        String url = buildUrl("students", "sortType", "NAME_ASC");
        StudentBuilder studentBuilder = StudentBuilder.create();
        studentBuilder.setEmail("abc@gmail.com");
        Student student = studentBuilder.build();
        studentBuilder.setId(2L);
        Student student1 = studentBuilder.build();

        //when
        restTemplate.postForEntity(url, student, String.class);

        //then
        Assertions.assertThrows(HttpClientErrorException.UnprocessableEntity.class, () -> restTemplate.postForEntity(url, student1, String.class));
    }
}
