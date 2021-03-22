package com.example.demo.average.domain;

import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Optional;

public class StudentAverageFacade {
    private final StudentAverageService studentAverageService;

   public StudentAverageFacade(@Lazy StudentAverageService studentAverageService) {
       this.studentAverageService = studentAverageService;
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

