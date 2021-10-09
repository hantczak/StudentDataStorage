package hantczak.studentDataStorage.grade.infrastructure.database;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.grade.domain.GradeRepository;
import hantczak.studentDataStorage.grade.domain.GradeScale;
import hantczak.studentDataStorage.grade.domain.GradeSortType;
import hantczak.studentDataStorage.utils.GradeBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Tag("integration")
public class GetAllStudentGradesTests extends StudentDataStorageApplicationTests {

    @Autowired
    protected GradeRepository gradeRepositoryProvider;

    @Test
    @DisplayName("Should return empty list for no registered grades")
    void shouldReturnEmptyListForNoRegisteredGrades() {
        //given

        //when
        List<Grade> expectedResponse = new ArrayList<>();

        //then
        List<Grade> databaseResponse = gradeRepositoryProvider.getAllGrades();
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return registered grades sorted by value ascending")
    void shouldReturnGradesSortedByValueAscending() {
        //given
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade grade = gradeBuilder.build();
        gradeBuilder.setId(2L);
        gradeBuilder.setGradeScale(GradeScale.EXCELLENT);
        Grade grade1 = gradeBuilder.build();

        //when
        List<Grade> expectedResponse = List.of(grade, grade1);
        gradeRepositoryProvider.addGrade(grade);
        gradeRepositoryProvider.addGrade(grade1);

        //then
        List<Grade> databaseResponse = gradeRepositoryProvider.getAllStudentGradesSorted(1L, GradeSortType.VALUE_ASC, 0, 5);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return registered grades sorted by value descending")
    void shouldReturnGradesSortedByValueDescending() {
        //given
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade grade = gradeBuilder.build();
        gradeBuilder.setId(2L);
        gradeBuilder.setGradeScale(GradeScale.EXCELLENT);
        Grade grade1 = gradeBuilder.build();

        //when
        List<Grade> expectedResponse = List.of(grade1, grade);
        gradeRepositoryProvider.addGrade(grade);
        gradeRepositoryProvider.addGrade(grade1);

        //then
        List<Grade> databaseResponse = gradeRepositoryProvider.getAllStudentGradesSorted(1L, GradeSortType.VALUE_DSC, 0, 5);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return registered grades sorted by insertion date ascending")
    void shouldReturnGradesSortedByInsertionDateAscending() {
        //given
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade grade = gradeBuilder.build();
        gradeBuilder.setId(2L);
        gradeBuilder.setInsertionDate(LocalDate.of(2021, 01, 01));
        Grade grade1 = gradeBuilder.build();

        //when
        List<Grade> expectedResponse = List.of(grade, grade1);
        gradeRepositoryProvider.addGrade(grade);
        gradeRepositoryProvider.addGrade(grade1);

        //then
        List<Grade> databaseResponse = gradeRepositoryProvider.getAllStudentGradesSorted(1L, GradeSortType.INSERTION_DATE_ASC, 0, 5);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return registered grades sorted by insertion date descending")
    void shouldReturnGradesSortedByInsertionDateDescending() {
        //given
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade grade = gradeBuilder.build();
        gradeBuilder.setId(2L);
        gradeBuilder.setInsertionDate(LocalDate.of(2021, 01, 01));
        Grade grade1 = gradeBuilder.build();

        //when
        List<Grade> expectedResponse = List.of(grade1, grade);
        gradeRepositoryProvider.addGrade(grade);
        gradeRepositoryProvider.addGrade(grade1);

        //then
        List<Grade> databaseResponse = gradeRepositoryProvider.getAllStudentGradesSorted(1L, GradeSortType.INSERTION_DATE_DSC, 0, 5);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return middle one of three grades sorted by value")
    void shouldReturnMiddleGradeForThreeGradesSortedByValue() {
        //given
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade grade = gradeBuilder.build();
        gradeBuilder.setId(2L);
        gradeBuilder.setGradeScale(GradeScale.EXCELLENT);
        Grade grade1 = gradeBuilder.build();
        gradeBuilder.setId(3L);
        gradeBuilder.setGradeScale(GradeScale.FAIL);
        Grade grade2 = gradeBuilder.build();

        //when
        List<Grade> expectedResponse = List.of(grade);
        gradeRepositoryProvider.addGrade(grade);
        gradeRepositoryProvider.addGrade(grade1);
        gradeRepositoryProvider.addGrade(grade2);

        //then
        List<Grade> databaseResponse = gradeRepositoryProvider.getAllStudentGradesSorted(1L, GradeSortType.VALUE_ASC, 1, 1);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }
}