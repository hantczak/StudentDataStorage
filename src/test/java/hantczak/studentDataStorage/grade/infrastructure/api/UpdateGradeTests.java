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
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag("integration")
public class UpdateGradeTests extends StudentDataStorageApplicationTests {

    @Test
    @DisplayName("Should update grade by Id")
    void shouldUpdateGradeById() {
        //given
        String url = buildUrlWithPathArgumentForGrade(1L);
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade clientSentGrade = gradeBuilder.build();


        Grade clientSentUpdatedGrade = gradeBuilder
                .setStudentId(1L)
                .setGradeScale(GradeScale.SUFFICIENT).build();

        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();

        Grade expectedGrade = gradeBuilder.setId(1L).setGradeScale(GradeScale.SUFFICIENT).build();
        GradeResponse expectedGradeResponse = new GradeResponse(List.of(GradeMapper.toDto(expectedGrade)));

        //when
        restTemplate.postForEntity(buildUrl("students"), student, String.class);
        restTemplate.postForEntity(buildUrl("grades"), clientSentGrade, String.class);

        //then
        restTemplate.put(buildUrl("grades", "gradeId", "1"), clientSentUpdatedGrade, Grade.class);
        ResponseEntity<GradeResponse> responseEntity = restTemplate.getForEntity(url, GradeResponse.class);
        GradeResponse gradeResponseFromController = responseEntity.getBody();

        Assertions.assertEquals(expectedGradeResponse, gradeResponseFromController);
    }
}
