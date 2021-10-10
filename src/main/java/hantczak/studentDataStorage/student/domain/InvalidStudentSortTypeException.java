package hantczak.studentDataStorage.student.domain;

public class InvalidStudentSortTypeException extends RuntimeException {
    public InvalidStudentSortTypeException(String message) {
        super("Invalid sort parameter " + message);
    }
}
