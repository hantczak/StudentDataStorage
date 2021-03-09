package com.example.demo.api;

import com.example.demo.dao.entity.Grade;
import com.example.demo.services.GradeService;
import com.example.demo.services.GradeSortService;

import java.util.List;

public class GradeFacade {
    private final GradeService gradeService;
    private final GradeSortService gradeSortService;

    public GradeFacade(GradeService gradeService, GradeSortService gradeSortService){
        this.gradeService = gradeService;
        this.gradeSortService=gradeSortService;
    }

    public List<Grade> getAllGradesSorted(GradeSortTypes gradeSortType) {
        return gradeSortService.getAllGradesSorted(gradeSortType);
    }

    public List<Grade> getSortedGradesForOneStudent(long studentId, GradeSortTypes gradeSortTypes) {
        return gradeSortService.getSortedGradesForOneStudent(studentId,gradeSortTypes);
    }

    public void addGrade(Grade grade) {
        gradeService.addGrade(grade);
    }

    public boolean updateGrade(Grade updatedGrade, int oldGradeId) {
        return gradeService.updateGrade(updatedGrade, oldGradeId);
    }

    public boolean deleteGrade(long studentId, int gradeToBeDeletedId) {
        return gradeService.deleteGrade(studentId,gradeToBeDeletedId);
    }

    public void deleteStudentGrades(long studentId) {
        gradeService.deleteStudentGrades(studentId);
    }

}
