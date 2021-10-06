package hantczak.studentDataStorage.average.infrastructure.database;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.average.domain.StudentAverage;
import hantczak.studentDataStorage.average.domain.StudentAverageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Tag("integration")
public class GetAllAveragesTests extends StudentDataStorageApplicationTests {

    @Autowired
    StudentAverageRepository studentAverageProvider;

    @Test
    @DisplayName("Should return empty optional for not existing StudentAverage")
    void shouldReturnEmptyEmptyOptionalForNotExistingStudentAverage() {
        //given

        //when

        //then
        Optional<StudentAverage> databaseResponse = studentAverageProvider.getStudentAverage(1L);
        Assertions.assertTrue(databaseResponse.isEmpty());
    }

    @Test
    @DisplayName("Should return registered averages in order of addition")
    void shouldReturnAveragesInOrderOfAddition() {
        //given
        StudentAverage studentAverage = new StudentAverage(4.0, 1L);
        StudentAverage studentAverage1 = new StudentAverage(5.0, 2L);

        //when
        List<StudentAverage> expectedResponse = List.of(studentAverage, studentAverage1);
        studentAverageProvider.updateAverage(studentAverage);
        studentAverageProvider.updateAverage(studentAverage1);

        //then
        List<StudentAverage> databaseResponse = studentAverageProvider.getAllAverages();
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }
}
