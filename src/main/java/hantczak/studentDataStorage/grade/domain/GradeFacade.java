package hantczak.studentDataStorage.grade.domain;

import java.util.List;

public class GradeFacade {
    private final GradeService gradeService;
    private final GradeSortService gradeSortService;

    public GradeFacade(GradeService gradeService, GradeSortService gradeSortService) {
        this.gradeService = gradeService;
        this.gradeSortService = gradeSortService;
    }

    public List<Grade> getAllGradesSorted(String gradeSortType,long offset, long limit) {
        return gradeSortService.getAllGradesSorted(gradeSortType,offset,limit);
    }

    public List<Grade> getSortedGradesForOneStudent(long studentId, String gradeSortType, long offset, long limit) {
        return gradeSortService.getSortedGradesForOneStudent(studentId, gradeSortType,offset,limit);
    }

    public List<Grade> getStudentGrades(long studentId){
        return gradeService.getStudentGrades(studentId);
    }

    public void addGrade(Grade grade) {
        gradeService.addGrade(grade);
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
