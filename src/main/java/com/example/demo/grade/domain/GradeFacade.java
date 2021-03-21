package com.example.demo.grade.domain;

import com.example.demo.average.domain.StudentAverageService;

import java.util.List;

public class GradeFacade {
    private final GradeService gradeService;
    private final GradeSortService gradeSortService;
    private final StudentAverageService studentAverageService;

    public GradeFacade(GradeService gradeService, GradeSortService gradeSortService,StudentAverageService studentAverageService){
        this.gradeService = gradeService;
        this.gradeSortService=gradeSortService;
        this.studentAverageService = studentAverageService;
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
