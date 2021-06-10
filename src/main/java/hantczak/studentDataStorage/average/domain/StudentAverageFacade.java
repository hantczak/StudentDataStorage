package hantczak.studentDataStorage.average.domain;

import java.util.List;
import java.util.Optional;

public class StudentAverageFacade {
    private final StudentAverageService studentAverageService;
    private final StudentAverageSortService studentAverageSortService;

    public StudentAverageFacade(StudentAverageService studentAverageService, StudentAverageSortService studentAverageSortService) {
        this.studentAverageService = studentAverageService;
        this.studentAverageSortService = studentAverageSortService;
    }

    public List<StudentAverage> getAllAveragesSorted(String sortType,long offset, long limit) {
        return studentAverageSortService.getAllAveragesSorted(sortType,offset,limit);
    }

    public List<StudentAverage> getAllAverages() {
        return studentAverageService.getAllAverages();
    }

    public Optional<StudentAverage> getStudentAverage(long studentId) {
        return studentAverageService.getStudentAverage(studentId);
    }

    public boolean deleteAverage(long studentId) {
        return studentAverageService.deleteAverage(studentId);
    }
}

