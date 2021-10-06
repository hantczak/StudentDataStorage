package hantczak.studentDataStorage.average.infrastructure.api;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.average.domain.StudentAverage;
import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.utils.GradeBuilder;
import hantczak.studentDataStorage.utils.StudentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("integration")
public class GetAllAveragesTests extends StudentDataStorageApplicationTests {

    @Test
    @DisplayName("Should return averages")
    void shouldReturnAverages() {
        //given
        String url = buildUrl("averages");
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade grade = gradeBuilder.build();
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();
        StudentAverage expectedAverage = new StudentAverage(4.0, 1L);

        //when
        restTemplate.postForEntity(buildUrl("students"), student, String.class);
        restTemplate.postForEntity(buildUrl("grades"), grade, String.class);

        ResponseEntity<StudentAverageResponse> responseEntity = restTemplate.getForEntity(url, StudentAverageResponse.class);
        StudentAverageResponse studentAverageResponseFromController = responseEntity.getBody();

        //then
        Assertions.assertEquals(expectedAverage, StudentAverageMapper.fromDto(studentAverageResponseFromController.getStudentAverageDtoList().get(0)));
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}