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

import java.util.List;
import java.util.Optional;

@Tag("integration")
public class GetAllAveragesTests extends StudentDataStorageApplicationTests {

    @Autowired
    StudentAverageRepository studentAverageProvider;

    @Test
    @DisplayName("Should return empty list for no StudentAverages in database")
    void shouldReturnEmptyEmptyListForNoStudentAveragesInDatabase() {
        //given

        //when

        //then
        List<StudentAverage> databaseResponse = studentAverageProvider.getAllAveragesSorted(StudentAverageSortType.VALUE_ASC,0,5);
        Assertions.assertTrue(databaseResponse.isEmpty());
    }

    @Test
    @DisplayName("Should return registered averages ordered by value ascending")
    void shouldReturnAveragesOrderedByValueAscending() {
        //given
        StudentAverage studentAverage = new StudentAverage(4.0, 1L);
        StudentAverage studentAverage1 = new StudentAverage(5.0, 2L);

        //when
        List<StudentAverage> expectedResponse = List.of(studentAverage, studentAverage1);
        studentAverageProvider.updateAverage(studentAverage);
        studentAverageProvider.updateAverage(studentAverage1);

        //then
        List<StudentAverage> databaseResponse = studentAverageProvider.getAllAveragesSorted(StudentAverageSortType.VALUE_ASC,0,5);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return registered averages ordered by value descending")
    void shouldReturnAveragesOrderedByValueDescending() {
        //given
        StudentAverage studentAverage = new StudentAverage(4.0, 1L);
        StudentAverage studentAverage1 = new StudentAverage(5.0, 2L);

        //when
        List<StudentAverage> expectedResponse = List.of(studentAverage1, studentAverage);
        studentAverageProvider.updateAverage(studentAverage);
        studentAverageProvider.updateAverage(studentAverage1);

        //then
        List<StudentAverage> databaseResponse = studentAverageProvider.getAllAveragesSorted(StudentAverageSortType.VALUE_DSC,0,5);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return registered averages ordered by student Id ascending")
    void shouldReturnAveragesOrderedByStudentIdAscending() {
        //given
        StudentAverage studentAverage = new StudentAverage(4.0, 1L);
        StudentAverage studentAverage1 = new StudentAverage(5.0, 2L);

        //when
        List<StudentAverage> expectedResponse = List.of(studentAverage, studentAverage1);
        studentAverageProvider.updateAverage(studentAverage);
        studentAverageProvider.updateAverage(studentAverage1);

        //then
        List<StudentAverage> databaseResponse = studentAverageProvider.getAllAveragesSorted(StudentAverageSortType.STUDENT_ID_ASC, 0, 5);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return registered averages ordered by student Id descending")
    void shouldReturnAveragesOrderedByStudentIdDescending() {
        //given
        StudentAverage studentAverage = new StudentAverage(4.0, 1L);
        StudentAverage studentAverage1 = new StudentAverage(5.0, 2L);

        //when
        List<StudentAverage> expectedResponse = List.of(studentAverage1, studentAverage);
        studentAverageProvider.updateAverage(studentAverage);
        studentAverageProvider.updateAverage(studentAverage1);

        //then
        List<StudentAverage> databaseResponse = studentAverageProvider.getAllAveragesSorted(StudentAverageSortType.STUDENT_ID_DSC, 0, 5);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return middle of three averages sorted by value")
    void shouldReturnMiddleOfThreeAveragesSortedByValue() {
        //given
        StudentAverage studentAverage = new StudentAverage(4.0, 1L);
        StudentAverage studentAverage1 = new StudentAverage(5.0, 2L);
        StudentAverage studentAverage2 = new StudentAverage(3.0, 3L);

        //when
        List<StudentAverage> expectedResponse = List.of(studentAverage);
        studentAverageProvider.updateAverage(studentAverage);
        studentAverageProvider.updateAverage(studentAverage1);
        studentAverageProvider.updateAverage(studentAverage2);

        //then
        List<StudentAverage> databaseResponse = studentAverageProvider.getAllAveragesSorted(StudentAverageSortType.VALUE_ASC, 1, 1);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }
}
