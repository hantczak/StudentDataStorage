package com.example.demo.services;

import com.example.demo.api.InvalidSortTypeException;
import com.example.demo.api.SortType;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dao.entity.Student;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentSortService {
    private StudentRepository studentRepository;

    StudentSortService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getSortedStudents(SortType sortType) {
        List<Student> sortedList = studentRepository.getAllStudents().stream()
                .sorted(getComparator(sortType))
                .collect(Collectors.toList());
        return sortedList;
    }

    public Comparator<Student> getComparator(SortType sortType) {
        Comparator<Student> comparator;
        switch (sortType) {
            case NAME_ASC:
                return comparator = Comparator.comparing(Student::getName);
            case NAME_DSC:
                return comparator = Comparator.comparing(Student::getName).reversed();
            case AGE_ASC:
                return comparator = Comparator.comparing(Student::getAge);
            case AGE_DSC:
                return comparator = Comparator.comparing(Student::getAge).reversed();
            default:
                StringBuilder availableSortTypes = new StringBuilder();
                Arrays.stream(SortType.values())
                        .forEach(type -> {
                            availableSortTypes.append(type);
                            availableSortTypes.append(" ");
                        });

                throw new InvalidSortTypeException("Available values: " + availableSortTypes);
        }
    }
}
