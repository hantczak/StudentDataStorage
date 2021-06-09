package hantczak.studentDataStorage.average.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentAverageSortService {
    private final StudentAverageRepository studentAverageRepository;

    StudentAverageSortService(StudentAverageRepository studentAverageRepository) {
        this.studentAverageRepository = studentAverageRepository;
    }

    public List<StudentAverage> getAllAveragesSorted(String sortType,long offset,long limit) {
        StudentAverageSortType studentAverageSortType = parseStudentAverageSortType(sortType);
        return studentAverageRepository.getAllAverages().stream()
                .sorted(getComparator(studentAverageSortType))
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }

    private StudentAverageSortType parseStudentAverageSortType(String sortType) {
        switch (sortType) {
            case "VALUE_ASC":
                return StudentAverageSortType.VALUE_ASC;
            case "VALUE_DSC":
                return StudentAverageSortType.VALUE_DSC;
            case "STUDENT_ID_ASC":
                return StudentAverageSortType.STUDENT_ID_ASC;
            case "STUDENT_ID_DSC":
                return StudentAverageSortType.STUDENT_ID_DSC;
            default:
                StringBuilder sortTypes = new StringBuilder();
                Arrays.stream(StudentAverageSortType.values())
                        .forEach(value -> {
                            sortTypes.append(value);
                            sortTypes.append(",");
                        });
                throw new InvalidStudentAverageSortTypeException(",available sort types: " + sortTypes);
        }
    }

    private Comparator<StudentAverage> getComparator(StudentAverageSortType studentAverageSortType) {
        switch (studentAverageSortType) {
            case VALUE_ASC:
                return Comparator.comparing(StudentAverage::getAverage);
            case VALUE_DSC:
                return Comparator.comparing(StudentAverage::getAverage).reversed();
            case STUDENT_ID_ASC:
                return Comparator.comparing(StudentAverage::getStudentId);
            case STUDENT_ID_DSC:
                return Comparator.comparing(StudentAverage::getStudentId).reversed();
            default:
                StringBuilder sortTypes = new StringBuilder();
                Arrays.stream(StudentAverageSortType.values())
                        .forEach(value -> {
                            sortTypes.append(value);
                            sortTypes.append(",");
                        });
                throw new InvalidStudentAverageSortTypeException(",available sort types: " + sortTypes);
        }
    }
}
