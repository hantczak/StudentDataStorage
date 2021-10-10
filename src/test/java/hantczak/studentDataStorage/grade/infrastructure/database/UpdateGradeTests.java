package hantczak.studentDataStorage.grade.infrastructure.database;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.grade.domain.GradeRepository;
import hantczak.studentDataStorage.grade.domain.GradeScale;
import hantczak.studentDataStorage.utils.GradeBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Tag("integration")
public class UpdateGradeTests extends StudentDataStorageApplicationTests {

    @Autowired
    protected GradeRepository gradeRepositoryProvider;

    @Test
    @DisplayName("Should update grade by Id")
    void shouldUpdateGradeByID() {
        //given
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade grade = gradeBuilder.build();

        Grade updatedGrade = gradeBuilder
                .setGradeScale(GradeScale.EXCELLENT)
                .setGradeWeight(3)
                .setInsertionDate(LocalDate.of(2021, 01, 01))
                .build();

        Grade expectedGrade = gradeBuilder.setId(1L).build();

        //when
        gradeRepositoryProvider.addGrade(grade);
        gradeRepositoryProvider.updateGrade(updatedGrade, 1L);

        //then
        Assertions.assertEquals(List.of(expectedGrade), gradeRepositoryProvider.getStudentGrades(1L));
    }
}
