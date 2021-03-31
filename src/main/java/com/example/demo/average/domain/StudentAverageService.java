package com.example.demo.average.domain;

import com.example.demo.grade.domain.Grade;
import com.example.demo.grade.domain.GradeFacade;
import com.example.demo.grade.domain.GradeModifiedListener;
import com.example.demo.student.domain.StudentDeletedListener;

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
        Optional<StudentAverage> studentAverageOptional;
        studentAverageOptional = Optional.ofNullable(studentAverageRepository.getStudentAverage(studentId));
        return studentAverageOptional;
    }

    private boolean updateAverage(long studentId) {
        return studentAverageRepository.updateAverage(createAverage(gradeFacade.getStudentGrades(studentId)));
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
        double gradeSum = gradeList.stream()
                .mapToDouble(grade -> grade.getGradeWeight() * grade.getGradeScale().getGradeValue())
                .sum();
        double gradeWeightsSum = gradeList.stream()
                .mapToDouble(grade->grade.getGradeWeight())
                .sum();
        return new StudentAverage(gradeSum / gradeWeightsSum, gradeList.get(0).getStudentId());
    }
}
