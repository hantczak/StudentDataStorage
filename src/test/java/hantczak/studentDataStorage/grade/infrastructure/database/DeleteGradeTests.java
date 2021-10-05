package hantczak.studentDataStorage.grade.infrastructure.database;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.grade.domain.GradeRepository;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.student.domain.StudentRepository;
import hantczak.studentDataStorage.utils.GradeBuilder;
import hantczak.studentDataStorage.utils.StudentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Tag("integration")
public class DeleteGradeTests extends StudentDataStorageApplicationTests {

    @Autowired
    protected GradeRepository gradeRepositoryProvider;

    @Test
    @DisplayName("Should delete grade by Id")
    void shouldDeleteGradeByID() {
        //given
        GradeBuilder gradeBuilder = GradeBuilder.create();
        Grade grade = gradeBuilder.build();

        //when
        gradeRepositoryProvider.addGrade(grade);
        gradeRepositoryProvider.deleteGrade(1L);

        //then
        Assertions.assertTrue(gradeRepositoryProvider.getStudentGrades(1L).isEmpty());
    }
}
