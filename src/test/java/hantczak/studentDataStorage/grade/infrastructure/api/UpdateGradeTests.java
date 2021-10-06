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

@Tag("integration")
public class UpdateGradeTests extends StudentDataStorageApplicationTests {

    @Test
    @DisplayName("Should update grade by Id")
    void shouldUpdateGradeById() {
        //given
        String url = buildUrlWithPathArgumentForGrade(1L);
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade grade = gradeBuilder.build();
        gradeBuilder.setId(2L);
        gradeBuilder.setStudentId(1L);
        gradeBuilder.setGradeScale(GradeScale.SUFFICIENT);
        Grade updatedGrade = gradeBuilder.build();
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();

        //when
        restTemplate.postForEntity(buildUrl("students"), student, String.class);
        restTemplate.postForEntity(buildUrl("grades"), grade, String.class);

        //then
        restTemplate.put(buildUrl("grades", "gradeId", "1"), updatedGrade, Grade.class);
        ResponseEntity<GradeResponse> responseEntity = restTemplate.getForEntity(url, GradeResponse.class);
        GradeResponse gradeResponseFromController = responseEntity.getBody();

        Assertions.assertEquals(gradeResponseFromController, restTemplate.getForEntity(url, GradeResponse.class).getBody());
    }
}
