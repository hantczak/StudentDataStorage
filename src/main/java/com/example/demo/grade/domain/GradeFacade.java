package com.example.demo.grade.domain;

import com.example.demo.average.domain.StudentAverageFacade;

import java.util.List;

public class GradeFacade {
    private final GradeService gradeService;
    private final GradeSortService gradeSortService;
    private final GradeUpdateService gradeUpdateService;

    public GradeFacade(GradeService gradeService, GradeSortService gradeSortService, StudentAverageFacade studentAverageFacade, GradeUpdateService gradeUpdateService) {
        this.gradeService = gradeService;
        this.gradeSortService = gradeSortService;
//        this.studentAverageFacade = studentAverageFacade;
        this.gradeUpdateService = gradeUpdateService;
    }

    public List<Grade> getAllGradesSorted(GradeSortTypes gradeSortType) {
        return gradeSortService.getAllGradesSorted(gradeSortType);
    }

    public List<Grade> getSortedGradesForOneStudent(long studentId, GradeSortTypes gradeSortTypes) {
        return gradeSortService.getSortedGradesForOneStudent(studentId, gradeSortTypes);
    }

    public void addListener(GradeAddedListener listener) {
        gradeService.addListener(listener);
    }

    public void addGrade(Grade grade) {
        gradeService.addGrade(grade);
        //
    }

    public boolean updateGrade(Grade updatedGrade, int oldGradeId) {
       return gradeUpdateService.updateGrade(updatedGrade, oldGradeId);
    }

    public boolean deleteGrade(long studentId, int gradeToBeDeletedId) {
        boolean ifSuccessful = gradeService.deleteGrade(studentId, gradeToBeDeletedId);
//        studentAverageFacade.updateAverage(gradeToBeDeletedId);
        return ifSuccessful;
    }

    public void deleteStudentGrades(long studentId) {
        gradeService.deleteStudentGrades(studentId);
    }

}
