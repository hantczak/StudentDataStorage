package com.example.demo.average.infrastructure.database;

import com.example.demo.average.domain.StudentAverage;
import com.example.demo.average.domain.StudentAverageCalculator;
import com.example.demo.average.domain.StudentAverageRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.demo.grade.domain.GradeSortTypes.VALUE_ASC;

@Repository
public class StudentAverageRepositoryInMemory implements StudentAverageRepository {
    private final Map<Long, StudentAverage> studentIdToAverageMap = new HashMap<>();


    public List<StudentAverage> getAllAverages() {
        List<StudentAverage> averageList = studentIdToAverageMap.values().stream()
                .collect(Collectors.toList());
        return averageList;
    }

    public StudentAverage getStudentAverage(long studentId) {
        return studentIdToAverageMap.get(studentId);
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


