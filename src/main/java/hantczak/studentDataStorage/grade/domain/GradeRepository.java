package hantczak.studentDataStorage.grade.domain;

import java.util.List;

public interface GradeRepository {
    List<Grade> getAllGrades();

    List<Grade> getStudentGrades(long studentId);

    List<Grade> getAllGradesSorted(GradeSortType gradeSortType, long offset, long limit);

    List<Grade> getAllStudentGradesSorted(long studentId, GradeSortType gradeSortType, long offset, long limit);

    Grade addGrade(Grade grade);

    boolean updateGrade(Grade updatedGrade, long oldGradeId);

    boolean deleteGrade(long gradeToBeDeletedId);

    void deleteStudentGrades(long studentId);
}
