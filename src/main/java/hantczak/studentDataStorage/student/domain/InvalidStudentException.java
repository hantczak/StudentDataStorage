package hantczak.studentDataStorage.student.domain;

import java.util.List;

public class InvalidStudentException extends RuntimeException {

    InvalidStudentException(List<String> studentErrors) {
        super(studentErrors.toString());
    }
}
