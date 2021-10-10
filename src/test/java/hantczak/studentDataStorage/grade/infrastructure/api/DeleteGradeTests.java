package hantczak.studentDataStorage.grade.infrastructure.api;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.utils.GradeBuilder;
import hantczak.studentDataStorage.utils.StudentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

@Tag("integration")
public class DeleteGradeTests extends StudentDataStorageApplicationTests {

    @Test
    @DisplayName("Should delete grade by Id")
    void shouldDeleteGradeByID() {
        //given
        String url = buildUrl("students", "studentId", "1", "gradeId", "1");
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade grade = gradeBuilder.build();
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();

        //when
        restTemplate.postForEntity(buildUrl("students"), student, String.class);
        restTemplate.postForEntity(buildUrl("grades"), grade, String.class);

        //then
        restTemplate.delete(url);
        Assertions.assertThrows(HttpClientErrorException.NotFound.class, () -> restTemplate.getForEntity(buildUrlWithPathArgumentForGrade(1L), String.class));
    }

    @Test
    @DisplayName("Should delete grade when student is deleted")
    void shouldDeleteGradeWhenStudentIsDeleted() {
        //given
        String url = buildUrl("students", "studentId", "1", "gradeId", "1");
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade grade = gradeBuilder.build();
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();

        //when
        restTemplate.postForEntity(buildUrl("students"), student, String.class);
        restTemplate.postForEntity(buildUrl("grades"), grade, String.class);

        //then
        //delete student
        restTemplate.delete(buildUrl("students", "studentId", "1"));
        Assertions.assertThrows(HttpClientErrorException.NotFound.class, () -> restTemplate.getForEntity(buildUrlWithPathArgumentForGrade(1L), String.class));
    }
}