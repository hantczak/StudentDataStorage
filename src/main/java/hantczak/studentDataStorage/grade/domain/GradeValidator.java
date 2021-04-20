package hantczak.studentDataStorage.grade.domain;

import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.student.domain.StudentFacade;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class GradeValidator {
    private final StudentFacade studentFacade;

    void validateGrade(Grade grade){
        List<String> errors = new ArrayList<>();
        Optional<Student> student = studentFacade.getStudent(grade.getStudentId());

        if (student.isEmpty()){
            errors.add("Student with given ID does not exist. Please choose another one.");
        }
        if(grade.getGradeWeight()>3){
            errors.add("Max value of grade weight is 3. Please provide appropriate grade weight value.");
        }
        if(grade.getInsertionDate().getYear()<2015){
            errors.add("School exists since 2015 year. Grade cannot be introduced with lower year value.");
        }

        if(grade.getGradeId()<=0){
            errors.add("Grade ID has to be bigger than 0. Please input a valid ID.");
        }

        if(!errors.isEmpty()){
            throw new InvalidGradeException(errors);
        }
    }
}
