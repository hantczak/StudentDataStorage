package hantczak.studentDataStorage.average.infrastructure.database;

import hantczak.studentDataStorage.average.domain.StudentAverage;
import hantczak.studentDataStorage.average.domain.StudentAverageRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class StudentAverageRepositoryInMemory implements StudentAverageRepository {
    private final Map<Long, StudentAverage> studentIdToAverageMap = new HashMap<>();


    public List<StudentAverage> getAllAverages() {
        List<StudentAverage> averageList = studentIdToAverageMap.values().stream()
                .collect(Collectors.toList());
        return averageList;
    }

    public Optional<StudentAverage> getStudentAverage(long studentId) {
        return Optional.ofNullable(studentIdToAverageMap.get(studentId));
    }

    public boolean updateAverage(StudentAverage studentAverage) {
       StudentAverage updatedAverage =  studentIdToAverageMap.put(studentAverage.getStudentId(), studentAverage);
        return updatedAverage!=null;
    }

    public boolean deleteAverage(long studentId) {
        StudentAverage removedAverage = studentIdToAverageMap.remove(studentId);

        return removedAverage!=null;
    }
}


