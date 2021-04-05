package hantczak.studentDataStorage.average.domain;

import java.util.List;
import java.util.Optional;

public interface StudentAverageRepository {

    List<StudentAverage> getAllAverages();
    Optional<StudentAverage> getStudentAverage(long studentId);
    boolean updateAverage(StudentAverage studentAverage);
    boolean deleteAverage(long studentId);
}



