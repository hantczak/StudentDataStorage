package hantczak.studentDataStorage.grade.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GradeSortService {
    private final GradeRepository gradeRepository;

    public GradeSortService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> getAllGradesSorted(String gradeSortType,long offset,long limit) {
        GradeSortType parsedGradeSortType = parseGradeSortType(gradeSortType);
        return gradeRepository.getAllGrades().stream()
                .sorted(getComparator(parsedGradeSortType))
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Grade> getSortedGradesForOneStudent(long studentId, String gradeSortType,long offset,long limit) {
        GradeSortType parsedGradeSortType = parseGradeSortType(gradeSortType);
        return gradeRepository.getStudentGrades(studentId).stream()
                .sorted(getComparator(parsedGradeSortType))
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
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

    private GradeSortType parseGradeSortType(String gradeSortType){
        switch(gradeSortType){
            case "VALUE_ASC":
                return GradeSortType.VALUE_ASC;
            case "VALUE_DSC":
                return GradeSortType.VALUE_DSC;
            case "INSERTION_DATE_ASC":
                return GradeSortType.INSERTION_DATE_ASC;
            case "INSERTION_DATE_DSC":
                return GradeSortType.INSERTION_DATE_DSC;
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
