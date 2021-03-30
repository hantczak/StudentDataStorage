package com.example.demo.grade.domain;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {
    private GradeRepository gradeRepository;
    private List<GradeModifiedListener> listeners = new ArrayList<>();

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
        boolean ifUpdated = gradeRepository.updateGrade(updatedGrade, oldGradeId);
        listeners.forEach(listener -> listener.onAdd(updatedGrade));
        return ifUpdated;
    }

    public boolean deleteGrade(long studentId, int gradeToBeDeletedId) {
        boolean ifDeleted = gradeRepository.deleteGrade(studentId, gradeToBeDeletedId);
        listeners.forEach(listener -> listener.onDelete(studentId));
        return ifDeleted;
    }

    public void deleteStudentGrades(long studentId) {
        gradeRepository.deleteStudentGrades(studentId);
    }

    public void addListener(GradeModifiedListener gradeModifiedListener) {
        listeners.add(gradeModifiedListener);
    }
}
