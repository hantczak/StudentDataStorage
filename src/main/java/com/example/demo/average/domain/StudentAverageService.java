package com.example.demo.average.domain;

import com.example.demo.grade.domain.GradeFacade;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.demo.grade.domain.GradeSortTypes.VALUE_ASC;

@Service
public class StudentAverageService {
    StudentAverageRepository studentAverageRepository;
    GradeFacade gradeFacade;

    StudentAverageService(StudentAverageRepository studentAverageRepository,@Lazy GradeFacade gradeFacade) {
        this.studentAverageRepository = studentAverageRepository;
        this.gradeFacade = gradeFacade;
    }

    List<StudentAverage> getAllAverages() {
        return studentAverageRepository.getAllAverages();
    }

    Optional<StudentAverage> getStudentAverage(long studentId) {
        Optional<StudentAverage> studentAverageOptional;
        studentAverageOptional = Optional.ofNullable(studentAverageRepository.getStudentAverage(studentId));
        return studentAverageOptional;
    }

    boolean updateAverage(long studentId) {
        StudentAverage updatedAverage = StudentAverageCalculator.createAverage(gradeFacade.getSortedGradesForOneStudent(studentId,VALUE_ASC));
        return studentAverageRepository.updateAverage(updatedAverage);
    }

    boolean deleteAverage(long studentId) {
        return studentAverageRepository.deleteAverage(studentId);
    }
}
