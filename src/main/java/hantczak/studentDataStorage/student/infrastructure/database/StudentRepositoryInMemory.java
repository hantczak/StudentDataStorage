package hantczak.studentDataStorage.student.infrastructure.database;

import hantczak.studentDataStorage.student.domain.InvalidStudentSortTypeException;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.student.domain.StudentRepository;
import hantczak.studentDataStorage.student.domain.StudentSortType;

import java.util.*;
import java.util.stream.Collectors;

public class StudentRepositoryInMemory implements StudentRepository {

    private final Map<Long, Student> studentsMap = new HashMap<>();

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = studentsMap.values().stream()
                .collect(Collectors.toCollection(ArrayList::new));
        return studentList;
    }

    @Override
    public List<Student> getAllStudentsSortedWithPagination(StudentSortType studentSortType, int offset, int limit) {
        return studentsMap.values().stream()
                .sorted(getComparator(studentSortType))
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Student> getStudent(long studentId) {
        Optional<Student> studentOptional;
        studentOptional = Optional.ofNullable(studentsMap.get(studentId));
        return studentOptional;
    }

    @Override
    public Student addStudent(Student student) {
        return studentsMap.put(student.getId(), student);
    }

    @Override
    public boolean updateStudentData(long studentId, Student student) {
        Student oldStudent = null;
        if (studentsMap.containsKey(studentId)) {
            oldStudent = studentsMap.put(student.getId(), student);
        }
        return oldStudent != null;
    }

    @Override
    public boolean deleteStudent(long studentId) {
        Student removedStudent = studentsMap.remove(studentId);
        return removedStudent != null;
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
}
