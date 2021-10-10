package hantczak.studentDataStorage.average.domain;

import java.util.List;
import java.util.Optional;

public class StudentAverageFacade {
    private final StudentAverageService studentAverageService;

    public StudentAverageFacade(StudentAverageService studentAverageService) {
        this.studentAverageService = studentAverageService;
    }

    public List<StudentAverage> getAllAveragesSorted(String sortType,long offset, long limit) {
        return studentAverageService.getAllAveragesSorted(sortType,offset,limit);
    }

    public Optional<StudentAverage> getStudentAverage(long studentId) {
        return studentAverageService.getStudentAverage(studentId);
    }

    public boolean deleteAverage(long studentId) {
        return studentAverageService.deleteAverage(studentId);
    }
}

