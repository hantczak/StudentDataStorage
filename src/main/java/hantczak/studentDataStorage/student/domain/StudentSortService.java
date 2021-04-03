package hantczak.studentDataStorage.student.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentSortService {
    private final StudentRepository studentRepository;

    public StudentSortService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getSortedStudents(String studentSortType) {
        StudentSortType parsedStudentSortType = parseStudentSortType(studentSortType);
        return studentRepository.getAllStudents().stream()
                .sorted(getComparator(parsedStudentSortType))
                .collect(Collectors.toList());
    }

    private Comparator<Student> getComparator(StudentSortType studentSortType) {
        switch (studentSortType) {
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
                Arrays.stream(StudentSortType.values())
                        .forEach(type -> {
                            availableSortTypes.append(type);
                            availableSortTypes.append(", ");
                        });

                throw new InvalidStudentSortTypeException("Available values: " + availableSortTypes);
        }
    }

    private StudentSortType parseStudentSortType(String studentSortTypes) {
        switch (studentSortTypes) {
            case "NAME_ASC":
                return StudentSortType.NAME_ASC;
            case "NAME_DSC":
                return StudentSortType.NAME_DSC;
            case "AGE_ASC":
                return StudentSortType.AGE_ASC;
            case "AGE_DSC":
                return StudentSortType.AGE_DSC;
            default:
                StringBuilder availableSortTypes = new StringBuilder();
                Arrays.stream(StudentSortType.values())
                        .forEach(type -> {
                            availableSortTypes.append(type);
                            availableSortTypes.append(", ");
                        });

                throw new InvalidStudentSortTypeException("Available values: " + availableSortTypes);
        }
    }
}
