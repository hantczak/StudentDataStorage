package com.example.demo.grade.domain;

import com.example.demo.average.domain.StudentAverageFacade;

public class GradeUpdateService {
    private final GradeService gradeService;
    private final StudentAverageFacade studentAverageFacade;

    public GradeUpdateService(GradeService gradeService, StudentAverageFacade studentAverageFacade) {
        this.gradeService = gradeService;
        this.studentAverageFacade = studentAverageFacade;
    }

    public boolean updateGrade(Grade updatedGrade, int oldGradeId) {
        boolean ifSuccessful = gradeService.updateGrade(updatedGrade, oldGradeId);
        studentAverageFacade.updateAverage(updatedGrade.getStudentId());
        return ifSuccessful;
    }
}
