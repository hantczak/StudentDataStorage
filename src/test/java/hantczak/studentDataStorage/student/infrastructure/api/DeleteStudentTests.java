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
public class DeleteStudentTests extends StudentDataStorageApplicationTests {

    @Test
    @DisplayName("Should delete student by Id")
    void shouldDeleteStudentByID() {
        //given
        String url = buildUrl("students", "studentId", "1");
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student clientSentStudent = studentBuilder.build();

        //when
        restTemplate.postForEntity(buildUrl("students"), clientSentStudent, String.class);

        //then
        restTemplate.delete(url);
        Assertions.assertThrows(HttpClientErrorException.NotFound.class, () -> restTemplate.getForEntity(buildUrlWithPathArgumentForStudent(1L), String.class));
    }
}
