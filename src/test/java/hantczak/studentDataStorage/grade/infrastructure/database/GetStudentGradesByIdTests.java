package hantczak.studentDataStorage.grade.infrastructure.database;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.grade.domain.GradeRepository;
import hantczak.studentDataStorage.utils.GradeBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Tag("integration")
public class GetStudentGradesByIdTests extends StudentDataStorageApplicationTests {

    @Autowired
    protected GradeRepository gradeRepositoryProvider;

    @Test
    @DisplayName("Should return no grades if they do not exist")
    void shouldReturnNoGradesIfTheyDoNotExist() {
        //given

        //when

        //then
        List<Grade> databaseResponse = gradeRepositoryProvider.getStudentGrades(1L);
        Assertions.assertTrue(databaseResponse.isEmpty());
    }

    @Test
    @DisplayName("Should return one grade if it exists")
    void shouldReturnOneGradeIfItExists() {
        //given
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade grade = gradeBuilder.build();

        //when
        gradeRepositoryProvider.addGrade(grade);
        Grade expectedResponse = grade;

        //then
        List<Grade> databaseResponse = gradeRepositoryProvider.getStudentGrades(1L);
        Assertions.assertEquals(List.of(expectedResponse), databaseResponse);
    }
}
