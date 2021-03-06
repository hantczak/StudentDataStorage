package hantczak.studentDataStorage.grade.domain;

import java.util.List;

public interface GradeRepository {
    List<Grade> getAllGrades();
    List<Grade> getStudentGrades(long studentId);
    void addGrade(Grade grade);
    boolean updateGrade(Grade updatedGrade,long oldGradeId);
    boolean deleteGrade(long gradeToBeDeletedId);
    void deleteStudentGrades(long studentId);
}
