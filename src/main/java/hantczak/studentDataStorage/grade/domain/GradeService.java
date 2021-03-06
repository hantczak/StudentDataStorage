package hantczak.studentDataStorage.grade.domain;

import hantczak.studentDataStorage.student.domain.StudentDeletedListener;

import java.util.ArrayList;
import java.util.List;

public class GradeService implements StudentDeletedListener {
    private GradeRepository gradeRepository;
    private List<GradeModifiedListener> listeners = new ArrayList<>();
    private GradeValidator gradeValidator;

    public GradeService(GradeRepository gradeRepository,GradeValidator gradeValidator) {
        this.gradeRepository = gradeRepository;
        this.gradeValidator = gradeValidator;
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.getAllGrades();
    }

    public List<Grade> getStudentGrades(long studentId) {
        return gradeRepository.getStudentGrades(studentId);
    }

    public void addGrade(Grade grade) {
        gradeValidator.validateGrade(grade);
        gradeRepository.addGrade(grade);
        listeners.forEach(listener -> listener.onAdd(grade));
    }

    public boolean updateGrade(Grade updatedGrade, long oldGradeId) {
        boolean ifUpdated = gradeRepository.updateGrade(updatedGrade, oldGradeId);
        listeners.forEach(listener -> listener.onAdd(updatedGrade));
        return ifUpdated;
    }

    public boolean deleteGrade(long studentId, long gradeToBeDeletedId) {
        boolean ifDeleted = gradeRepository.deleteGrade(gradeToBeDeletedId);
        listeners.forEach(listener -> listener.onDelete(studentId));
        return ifDeleted;
    }

    public void addListener(GradeModifiedListener gradeModifiedListener) {
        listeners.add(gradeModifiedListener);
    }

    private void deleteStudentGrades(long studentId) {
        gradeRepository.deleteStudentGrades(studentId);
        listeners.forEach(listener -> listener.onDelete(studentId));
    }

    @Override
    public void onStudentDelete(long studentId) {
        deleteStudentGrades(studentId);
    }
}
