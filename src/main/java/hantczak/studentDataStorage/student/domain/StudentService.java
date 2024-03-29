package hantczak.studentDataStorage.student.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentValidator studentValidator;

    private final List<StudentDeletedListener> listeners = new ArrayList<>();

    public StudentService(StudentRepository studentRepository, StudentValidator studentValidator) {
        this.studentRepository = studentRepository;
        this.studentValidator = studentValidator;
    }

    public List<Student> getAllStudentsSortedWithPagination(String studentSortType, long offset, long limit) {
        validatePaginationParameters(offset,limit);
        StudentSortType parsedStudentSortType = parseStudentSortType(studentSortType);
        return studentRepository.getAllStudentsSortedWithPagination(parsedStudentSortType, offset, limit);
    }

    public Optional<Student> getStudent(long id) {
        return studentRepository.getStudent(id);
    }

    public Student addStudent(Student student) {
        studentValidator.validateStudent(student);
        return studentRepository.addStudent(student);
    }

    public boolean updateStudentData(long studentId, Student student) {
        studentValidator.validateStudent(student);
        return studentRepository.updateStudentData(studentId, student);
    }

    public boolean deleteStudentAndHisGrades(long studentId) {
        boolean ifDeleted = studentRepository.deleteStudent(studentId);
        if (ifDeleted) {
            listeners.forEach(listener -> listener.onStudentDelete(studentId));
        }
        return ifDeleted;
    }

    public void addListener(StudentDeletedListener studentDeletedListener) {
        listeners.add(studentDeletedListener);
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

    private void validatePaginationParameters(long offset, long limit){
        List<String> errors = new ArrayList<>();

        if(limit>100){
            errors.add("Limit cannot be higher than 100");
        }

        if(limit<0){
            errors.add("Offset cannot be lower than 0");
        }

        if(offset<0){
            errors.add("Offset cannot be lower than 0");
        }

        if (!errors.isEmpty()) {
            throw new InvalidPaginationParametersException(errors.toString());
        }
    }
}