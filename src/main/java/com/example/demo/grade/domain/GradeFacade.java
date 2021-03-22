package com.example.demo.grade.domain;

import com.example.demo.average.domain.StudentAverageFacade;
import com.example.demo.average.domain.StudentAverageService;
import org.springframework.context.annotation.Lazy;

import java.util.List;

public class GradeFacade {
    private final GradeService gradeService;
    private final GradeSortService gradeSortService;
    private final StudentAverageFacade studentAverageFacade;

    public GradeFacade(GradeService gradeService, GradeSortService gradeSortService, StudentAverageFacade studentAverageFacade) {
        this.gradeService = gradeService;
        this.gradeSortService = gradeSortService;
        this.studentAverageFacade = studentAverageFacade;
    }

    public List<Grade> getAllGradesSorted(GradeSortTypes gradeSortType) {
        return gradeSortService.getAllGradesSorted(gradeSortType);
    }

    public List<Grade> getSortedGradesForOneStudent(long studentId, GradeSortTypes gradeSortTypes) {
        return gradeSortService.getSortedGradesForOneStudent(studentId, gradeSortTypes);
    }

    public void addGrade(Grade grade) {
        gradeService.addGrade(grade);
        studentAverageFacade.updateAverage(grade.getStudentId());
    }

    public boolean updateGrade(Grade updatedGrade, int oldGradeId) {
        boolean ifSuccessful = gradeService.updateGrade(updatedGrade, oldGradeId);
        studentAverageFacade.updateAverage(updatedGrade.getStudentId());
        return ifSuccessful;
    }

    public boolean deleteGrade(long studentId, int gradeToBeDeletedId) {
        boolean ifSuccessful = gradeService.deleteGrade(studentId, gradeToBeDeletedId);
        studentAverageFacade.updateAverage(gradeToBeDeletedId);
        return ifSuccessful;
    }

    public void deleteStudentGrades(long studentId) {
        gradeService.deleteStudentGrades(studentId);
    }

}
