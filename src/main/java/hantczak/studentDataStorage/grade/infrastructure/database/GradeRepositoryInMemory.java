package hantczak.studentDataStorage.grade.infrastructure.database;

import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.grade.domain.GradeRepository;
import hantczak.studentDataStorage.grade.domain.GradeSortType;
import hantczak.studentDataStorage.grade.domain.InvalidGradeSortTypeException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class GradeRepositoryInMemory implements GradeRepository {
    private Map<Long, Grade> gradeMap = new HashMap<>();

    @Override
    public List<Grade> getAllGrades() {
        List<Grade> gradeList = gradeMap.values().stream()
                .collect(Collectors.toCollection(ArrayList::new));
        return gradeList;
    }

    @Override
    public List<Grade> getStudentGrades(long studentId) {
        List<Grade> studentGradeList;
        studentGradeList = gradeMap.values().stream()
                .filter(grade -> grade.getStudentId() == studentId)
                .collect(Collectors.toList());
        return studentGradeList;
    }

    @Override
    public List<Grade> getAllGradesSorted(GradeSortType gradeSortType, int offset, int limit) {
        return gradeMap.values().stream()
                .sorted(getComparator(gradeSortType))
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Grade> getAllStudentGradesSorted(long studentId, GradeSortType gradeSortType, int offset, int limit) {
        return gradeMap.values().stream()
                .sorted(getComparator(gradeSortType))
                .filter(grade -> grade.getStudentId() == studentId)
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public Grade addGrade(Grade grade) {
        return gradeMap.put(grade.getGradeId(), grade);
    }

    @Override
    public boolean updateGrade(Grade updatedGrade, long oldGradeId) {
        Grade oldGrade = null;
        if (gradeMap.containsKey(oldGradeId)) {
            oldGrade = gradeMap.put(updatedGrade.getGradeId(), updatedGrade);
        }
        return oldGrade != null;
    }

    @Override
    public boolean deleteGrade(long gradeToBeDeletedId) {
        Grade removedGrade = gradeMap.remove(gradeToBeDeletedId);
        return removedGrade != null;
    }


    @Override
    public void deleteStudentGrades(long studentId) {
        List<Long> markedForRemoval = new ArrayList<>();
        gradeMap.values().stream()
                .forEach(grade -> {
                    if (grade.getStudentId() == studentId) {
                        markedForRemoval.add(grade.getGradeId());
                    }
                });
        markedForRemoval.stream()
                .forEach(grade -> gradeMap.remove(grade));
    }

    private Comparator<Grade> getComparator(GradeSortType gradeSortType) {
        switch (gradeSortType) {
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
                Arrays.stream(GradeSortType.values())
                        .forEach(value -> {
                            sortTypes.append(value);
                            sortTypes.append(", ");
                        });
                throw new InvalidGradeSortTypeException(",available sort types: " + sortTypes);
        }
    }
}
