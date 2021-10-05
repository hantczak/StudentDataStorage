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
public class UpdateAverageTests extends StudentDataStorageApplicationTests {

    @Autowired
    StudentAverageRepository studentAverageProvider;

    @Test
    @DisplayName("Should update Average")
    void shouldUpdateAverage() {
        //given
        StudentAverage studentAverage = new StudentAverage(4.0,1L);
        StudentAverage updatedStudentAverage = new StudentAverage(5.0,1L);

        //when
        studentAverageProvider.updateAverage(studentAverage);
        studentAverageProvider.updateAverage(updatedStudentAverage);

        //then
        Assertions.assertEquals(updatedStudentAverage.getAverage(),studentAverageProvider.getStudentAverage(1L).get().getAverage());
    }
}
