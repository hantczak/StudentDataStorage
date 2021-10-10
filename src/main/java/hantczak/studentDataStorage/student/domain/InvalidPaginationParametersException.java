package hantczak.studentDataStorage.student.domain;

public class InvalidPaginationParametersException extends RuntimeException{
    public InvalidPaginationParametersException(String message) {
        super("Invalid pagination parameter " + message);
    }
}
