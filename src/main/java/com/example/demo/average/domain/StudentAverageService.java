package com.example.demo.average.domain;

import com.example.demo.grade.domain.GradeFacade;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.grade.domain.GradeSortTypes.VALUE_ASC;

@Service
public class StudentAverageService {
    AverageRepository averageRepository;
    GradeFacade gradeFacade;

    StudentAverageService(AverageRepository averageRepository,GradeFacade gradeFacade) {
        this.averageRepository = averageRepository;
        this.gradeFacade = gradeFacade;
    }

    List<StudentAverage> getAllAverages() {
        return averageRepository.getAllAverages();
    }

    StudentAverage getStudentAverage(long studentId) {
        return averageRepository.getStudentAverage(studentId);
    }

    boolean updateAverage(long studentId) {
        StudentAverage updatedAverage = AverageCalculator.createAverage(gradeFacade.getSortedGradesForOneStudent(studentId,VALUE_ASC));
        return averageRepository.updateAverage(updatedAverage);
    }

    boolean deleteAverage(long studentId) {
        return averageRepository.deleteAverage(studentId);
    }
}
