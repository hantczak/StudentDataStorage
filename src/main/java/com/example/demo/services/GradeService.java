package com.example.demo.services;

import com.example.demo.dao.GradeRepository;
import com.example.demo.dao.entity.Grade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    private GradeRepository gradeRepository;
    
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
}
