package hantczak.studentDataStorage.average.domain;

import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.grade.domain.GradeFacade;
import hantczak.studentDataStorage.grade.domain.GradeModifiedListener;
import hantczak.studentDataStorage.student.domain.StudentDeletedListener;

import java.util.List;
import java.util.Optional;


public class StudentAverageService implements GradeModifiedListener, StudentDeletedListener {
    StudentAverageRepository studentAverageRepository;
    GradeFacade gradeFacade;

    StudentAverageService(StudentAverageRepository studentAverageRepository, GradeFacade gradeFacade) {
        this.studentAverageRepository = studentAverageRepository;
        this.gradeFacade = gradeFacade;
    }

    public List<StudentAverage> getAllAverages() {
        return studentAverageRepository.getAllAverages();
    }

    public Optional<StudentAverage> getStudentAverage(long studentId) {
        return studentAverageRepository.getStudentAverage(studentId);
    }

    private boolean updateAverage(long studentId) {
        StudentAverage average = createAverage(gradeFacade.getStudentGrades(studentId));
        if (average == null) {
            deleteAverage(studentId);
            return false;
        } else {
            return studentAverageRepository.updateAverage(average);
        }
    }

    boolean deleteAverage(long studentId) {
        return studentAverageRepository.deleteAverage(studentId);
    }

    @Override
    public void onAdd(Grade grade) {
        updateAverage(grade.getStudentId());
    }

    @Override
    public void onDelete(long studentId) {
        updateAverage(studentId);
    }

    @Override
    public void onUpdate(Grade grade) {
        updateAverage(grade.getStudentId());
    }

    @Override
    public void onStudentDelete(long studentId) {
        deleteAverage(studentId);
    }

    private static StudentAverage createAverage(List<Grade> gradeList) {
        if (gradeList.isEmpty()) {
            return null;
        }
        double gradeSum = gradeList.stream()
                .mapToDouble(grade -> grade.getGradeWeight() * grade.getGradeScale().getGradeValue())
                .sum();
        double gradeWeightsSum = gradeList.stream()
                .mapToDouble(grade -> grade.getGradeWeight())
                .sum();
        return new StudentAverage(gradeSum / gradeWeightsSum, gradeList.get(0).getStudentId());
    }
}
