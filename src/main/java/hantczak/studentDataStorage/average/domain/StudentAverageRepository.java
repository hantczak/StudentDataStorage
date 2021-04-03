package hantczak.studentDataStorage.average.domain;

import java.util.List;

public interface StudentAverageRepository {

    List<StudentAverage> getAllAverages();
    StudentAverage getStudentAverage(long studentId);
    boolean updateAverage(StudentAverage studentAverage);
    boolean deleteAverage(long studentId);
}



