package hantczak.studentDataStorage.average.domain;

public class InvalidStudentAverageSortTypeException extends RuntimeException {
    public InvalidStudentAverageSortTypeException(String message) {
        super("Invalid sort parameter " + message);
    }
}
