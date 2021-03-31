package com.example.demo.grade.domain;

import java.util.List;

public class GradeFacade {
    private final GradeService gradeService;
    private final GradeSortService gradeSortService;

    public GradeFacade(GradeService gradeService, GradeSortService gradeSortService) {
        this.gradeService = gradeService;
        this.gradeSortService = gradeSortService;
    }

    public List<Grade> getAllGradesSorted(GradeSortTypes gradeSortType) {
        return gradeSortService.getAllGradesSorted(gradeSortType);
    }

    public List<Grade> getSortedGradesForOneStudent(long studentId, GradeSortTypes gradeSortTypes) {
        return gradeSortService.getSortedGradesForOneStudent(studentId, gradeSortTypes);
    }

    public List<Grade> getStudentGrades(long studentId){
        return gradeService.getStudentGrades(studentId);
    }

    public void addGrade(Grade grade) {
        gradeService.addGrade(grade);
    }

    public boolean updateGrade(Grade updatedGrade, int oldGradeId) {
        return gradeService.updateGrade(updatedGrade, oldGradeId);
    }

    public boolean deleteGrade(long studentId, int gradeToBeDeletedId) {
        return gradeService.deleteGrade(studentId, gradeToBeDeletedId);
    }

    public void addListener(GradeModifiedListener gradeModifiedListener) {
        gradeService.addListener(gradeModifiedListener);
    }
}
