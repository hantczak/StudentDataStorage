package hantczak.studentDataStorage.average.infrastructure.database;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.average.domain.StudentAverage;
import hantczak.studentDataStorage.average.domain.StudentAverageRepository;
import hantczak.studentDataStorage.average.domain.StudentAverageSortType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Tag("integration")
public class GetStudentAverageTests extends StudentDataStorageApplicationTests {

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
    @DisplayName("Should return one average if it exists")
    void shouldReturnOneAverageIfItExists() {
        //given
        StudentAverage studentAverage = new StudentAverage(4.0, 1L);

        //when
        studentAverageProvider.updateAverage(studentAverage);
        StudentAverage expectedResponse = studentAverage;

        //then
        Optional<StudentAverage> databaseResponse = studentAverageProvider.getStudentAverage(1L);
        Assertions.assertEquals(expectedResponse.getAverage(), databaseResponse.get().getAverage());
    }
}
