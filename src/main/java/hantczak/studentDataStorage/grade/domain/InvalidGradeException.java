package hantczak.studentDataStorage.grade.domain;

import java.util.List;

public class InvalidGradeException extends RuntimeException {

    public InvalidGradeException(List<String> gradeErrors){
        super(gradeErrors.toString());
    }
}
