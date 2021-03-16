package com.example.demo.grade.domain;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeSortService {
    private GradeRepository gradeRepository;

    public GradeSortService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> getAllGradesSorted(GradeSortTypes gradeSortTypes) {
        return gradeRepository.getAllGrades().stream()
                .sorted(getComparator(gradeSortTypes))
                .collect(Collectors.toList());
    }

    public List<Grade> getSortedGradesForOneStudent(long studentId, GradeSortTypes gradeSortTypes) {
        return gradeRepository.getStudentGrades(studentId).stream()
                .sorted(getComparator(gradeSortTypes))
                .collect(Collectors.toList());
    }

    private Comparator<Grade> getComparator(GradeSortTypes gradeSortTypes) {
        switch (gradeSortTypes) {
            case VALUE_ASC:
                return Comparator.comparing(Grade::getGradeValue);
            case VALUE_DSC:
                return Comparator.comparing(Grade::getGradeValue).reversed();
            case INSERTION_DATE_ASC:
                return Comparator.comparing(Grade::getInsertionDate);
            case INSERTION_DATE_DSC:
                return Comparator.comparing(Grade::getInsertionDate).reversed();
            default:
                StringBuilder sortTypes = new StringBuilder();
                Arrays.stream(GradeSortTypes.values())
                        .forEach(value -> {
                            sortTypes.append(value);
                            sortTypes.append(", ");
                        });
                throw new InvalidGradeSortTypeException(",available sort types: " + sortTypes);
        }

    }
}
