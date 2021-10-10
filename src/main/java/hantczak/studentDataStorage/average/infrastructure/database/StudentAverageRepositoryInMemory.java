package hantczak.studentDataStorage.average.infrastructure.database;

import hantczak.studentDataStorage.average.domain.InvalidStudentAverageSortTypeException;
import hantczak.studentDataStorage.average.domain.StudentAverage;
import hantczak.studentDataStorage.average.domain.StudentAverageRepository;
import hantczak.studentDataStorage.average.domain.StudentAverageSortType;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class StudentAverageRepositoryInMemory implements StudentAverageRepository {
    private final Map<Long, StudentAverage> studentIdToAverageMap = new HashMap<>();


    @Override
    public List<StudentAverage> getAllAveragesSorted(StudentAverageSortType sortType, long offset, long limit) {
        return studentIdToAverageMap.values().stream()
                .sorted(getComparator(sortType))
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Optional<StudentAverage> getStudentAverage(long studentId) {
        return Optional.ofNullable(studentIdToAverageMap.get(studentId));
    }

    public boolean updateAverage(StudentAverage studentAverage) {
        StudentAverage updatedAverage = studentIdToAverageMap.put(studentAverage.getStudentId(), studentAverage);
        return updatedAverage != null;
    }

    public boolean deleteAverage(long studentId) {
        StudentAverage removedAverage = studentIdToAverageMap.remove(studentId);

        return removedAverage != null;
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


