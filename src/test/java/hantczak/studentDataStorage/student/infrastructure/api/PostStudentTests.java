package hantczak.studentDataStorage.student.infrastructure.api;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.utils.StudentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

@Tag("integration")
public class PostStudentTests extends StudentDataStorageApplicationTests {

    @Test
    @DisplayName("Should automatically create student IDs")
    void shouldAutomaticallyCreateStudentIDs() {
        //given
        String url = buildUrl("students", "sortType", "NAME_ASC");
        StudentBuilder studentBuilder = StudentBuilder.create();

        Student clientSentStudent = studentBuilder
                .setEmail("abc@gmail.com")
                .build();
        StudentDto expectedStudent= StudentMapper.toDto(studentBuilder.setId(1L).build());

        Student clientSentStudent1 = studentBuilder
                .setId(null)
                .setEmail("bcd@gmail.com")
                .build();
        StudentDto expectedStudent1= StudentMapper.toDto(studentBuilder.setId(2L).build());


        //when
        restTemplate.postForEntity(url, clientSentStudent, String.class);
        restTemplate.postForEntity(url, clientSentStudent1, String.class);
        StudentDto studentDtoFromController = restTemplate.getForEntity(buildUrlWithPathArgumentForStudent(1L),StudentDto.class).getBody();
        StudentDto studentDtoFromController1 = restTemplate.getForEntity(buildUrlWithPathArgumentForStudent(2L),StudentDto.class).getBody();

        //then
        Assertions.assertEquals(expectedStudent,studentDtoFromController);
        Assertions.assertEquals(expectedStudent1,studentDtoFromController1);
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
