package hantczak.studentDataStorage.grade.domain;

import java.util.List;

public class GradeFacade {
    private final GradeService gradeService;

    public GradeFacade(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    public List<Grade> getAllGradesSorted(String gradeSortType, int offset, int limit) {
        return gradeService.getAllGradesSorted(gradeSortType, offset, limit);
    }

    public List<Grade> getSortedGradesForOneStudent(long studentId, String gradeSortType, int offset, int limit) {
        return gradeService.getSortedGradesForOneStudent(studentId, gradeSortType, offset, limit);
    }

    public List<Grade> getStudentGrades(long studentId) {
        return gradeService.getStudentGrades(studentId);
    }

    public Grade addGrade(Grade grade) {
        return gradeService.addGrade(grade);
    }

    public boolean updateGrade(Grade updatedGrade, long oldGradeId) {
        return gradeService.updateGrade(updatedGrade, oldGradeId);
    }

    public boolean deleteGrade(long studentId, long gradeToBeDeletedId) {
        return gradeService.deleteGrade(studentId, gradeToBeDeletedId);
    }

    public void addListener(GradeModifiedListener gradeModifiedListener) {
        gradeService.addListener(gradeModifiedListener);
    }
}
