package hantczak.studentDataStorage.grade.infrastructure.api;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.grade.domain.GradeScale;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.utils.GradeBuilder;
import hantczak.studentDataStorage.utils.StudentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag("integration")
public class GetStudentGradesByIdTests extends StudentDataStorageApplicationTests {

    @Test
    @DisplayName("Should return registered grades assigned to particular student")
    void shouldReturnStudentGrades() {
        //given
        String url = buildUrlWithPathArgumentForGrade(1L);
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade clientSentGrade = gradeBuilder.build();
        gradeBuilder.setStudentId(1L);
        gradeBuilder.setGradeScale(GradeScale.SUFFICIENT);
        Grade clientSentGrade1 = gradeBuilder.build();
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();

        Grade expectedGrade = gradeBuilder.setId(1L).setGradeScale(GradeScale.GOOD).build();
        Grade expectedGrade1 = gradeBuilder.setId(2L).setGradeScale(GradeScale.SUFFICIENT).build();

        //when
        restTemplate.postForEntity(buildUrl("students"), student, String.class);
        restTemplate.postForEntity(buildUrl("grades"), clientSentGrade, String.class);
        restTemplate.postForEntity(buildUrl("grades"), clientSentGrade1, String.class);

        List<GradeDto> gradeDtoList = GradeMapper.gradeListToGradeDtoList(List.of(expectedGrade, expectedGrade1));
        GradeResponse expectedResponse = new GradeResponse(gradeDtoList);
        ResponseEntity<GradeResponse> responseEntity = restTemplate.getForEntity(url, GradeResponse.class);
        GradeResponse gradeResponseFromController = responseEntity.getBody();

        //then
        Assertions.assertEquals(expectedResponse, gradeResponseFromController);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}