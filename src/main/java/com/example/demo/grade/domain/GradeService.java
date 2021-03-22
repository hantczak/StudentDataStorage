package com.example.demo.grade.domain;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final List<GradeAddedListener> listeners = new ArrayList<>();
    
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.getAllGrades();
    }

    public List<Grade> getStudentGrades(long studentId) {
        return gradeRepository.getStudentGrades(studentId);
    }

    public void addGrade(Grade grade) {
        gradeRepository.addGrade(grade);
        listeners.forEach(listener -> listener.onAdd(grade));
    }

    public boolean updateGrade(Grade updatedGrade, int oldGradeId) {
        return gradeRepository.updateGrade(updatedGrade, oldGradeId);
    }

    public boolean deleteGrade(long studentId, int gradeToBeDeletedId) {
        return gradeRepository.deleteGrade(studentId,gradeToBeDeletedId);
    }

    public void deleteStudentGrades(long studentId) {
        gradeRepository.deleteStudentGrades(studentId);
    }

    public void addListener(GradeAddedListener listener) {
        listeners.add(listener);
    }
}
