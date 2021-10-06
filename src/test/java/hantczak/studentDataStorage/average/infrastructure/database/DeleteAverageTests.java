package hantczak.studentDataStorage.average.infrastructure.database;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.average.domain.StudentAverage;
import hantczak.studentDataStorage.average.domain.StudentAverageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Tag("integration")
public class DeleteAverageTests extends StudentDataStorageApplicationTests {

    @Autowired
    StudentAverageRepository studentAverageProvider;

    @Test
    @DisplayName("Should delete average")
    void shouldDeleteAverage() {
        //given
        StudentAverage studentAverage = new StudentAverage(4.0, 1L);

        //when
        studentAverageProvider.updateAverage(studentAverage);
        Assertions.assertEquals(studentAverage, studentAverageProvider.getStudentAverage(1L).get());
        studentAverageProvider.deleteAverage(1L);

        //then
        Assertions.assertTrue(studentAverageProvider.getStudentAverage(1L).isEmpty());
    }
}