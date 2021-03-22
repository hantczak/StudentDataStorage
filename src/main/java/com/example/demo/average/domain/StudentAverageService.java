package com.example.demo.average.domain;

import com.example.demo.grade.domain.Grade;
import com.example.demo.grade.domain.GradeAddedListener;
import com.example.demo.grade.domain.GradeFacade;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.demo.grade.domain.GradeSortTypes.VALUE_ASC;

public class StudentAverageService implements GradeAddedListener {
    private final StudentAverageRepository studentAverageRepository;
    private final GradeFacade gradeFacade;

    StudentAverageService(StudentAverageRepository studentAverageRepository,@Lazy GradeFacade gradeFacade) {
        this.studentAverageRepository = studentAverageRepository;
        this.gradeFacade = gradeFacade;
    }

    List<StudentAverage> getAllAverages() {
        return studentAverageRepository.getAllAverages();
    }

    Optional<StudentAverage> getStudentAverage(long studentId) {
        Optional<StudentAverage> studentAverageOptional;
        studentAverageOptional = Optional.ofNullable(studentAverageRepository.getStudentAverage(studentId));
        return studentAverageOptional;
    }

    boolean deleteAverage(long studentId) {
        return studentAverageRepository.deleteAverage(studentId);
    }

    private StudentAverage createAverage(List<Grade> gradeList) {
        double gradeSum = gradeList.stream()
                .mapToDouble(grade->grade.getGradeWeight()*grade.getGradeScale().getGradeValue())
                .sum();
        return new StudentAverage(gradeSum/gradeList.size(),gradeList.get(0).getStudentId());
    }

    @Override
    public void onAdd(Grade grade) {
        updateAverage(grade.getStudentId());
    }

    private boolean updateAverage(long studentId) {
        List<Grade> grades = gradeFacade.getSortedGradesForOneStudent(studentId, VALUE_ASC);
        StudentAverage updatedAverage = createAverage(grades);
        return studentAverageRepository.updateAverage(updatedAverage);
    }
}
