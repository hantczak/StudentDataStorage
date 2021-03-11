package com.example.demo.services;

import com.example.demo.api.InvalidSortTypeException;
import com.example.demo.api.StudentSortTypes;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dao.entity.Student;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentSortService {
    private final StudentRepository studentRepository;

    public StudentSortService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getSortedStudents(StudentSortTypes studentSortTypes) {
        return studentRepository.getAllStudents().stream()
                .sorted(getComparator(studentSortTypes))
                .collect(Collectors.toList());
    }

    public Comparator<Student> getComparator(StudentSortTypes studentSortTypes) {
        switch (studentSortTypes) {
            case NAME_ASC:
                return Comparator.comparing(Student::getName);
            case NAME_DSC:
                return Comparator.comparing(Student::getName).reversed();
            case AGE_ASC:
                return Comparator.comparing(Student::getAge);
            case AGE_DSC:
                return Comparator.comparing(Student::getAge).reversed();
            default:
                StringBuilder availableSortTypes = new StringBuilder();
                Arrays.stream(StudentSortTypes.values())
                        .forEach(type -> {
                            availableSortTypes.append(type);
                            availableSortTypes.append(", ");
                        });

                throw new InvalidSortTypeException("Available values: " + availableSortTypes);
        }
    }
}
