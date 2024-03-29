package hantczak.studentDataStorage.grade.domain;

import hantczak.studentDataStorage.student.domain.InvalidPaginationParametersException;
import hantczak.studentDataStorage.student.domain.StudentDeletedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GradeService implements StudentDeletedListener {
    private GradeRepository gradeRepository;
    private List<GradeModifiedListener> listeners = new ArrayList<>();
    private GradeValidator gradeValidator;

    public GradeService(GradeRepository gradeRepository, GradeValidator gradeValidator) {
        this.gradeRepository = gradeRepository;
        this.gradeValidator = gradeValidator;
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.getAllGrades();
    }


    public List<Grade> getStudentGrades(long studentId) {
        return gradeRepository.getStudentGrades(studentId);
    }

    public List<Grade> getAllGradesSorted(String gradeSortType, long offset, long limit) {
        validatePaginationParameters(offset,limit);
        GradeSortType parsedGradeSortType = parseGradeSortType(gradeSortType);
        return gradeRepository.getAllGradesSorted(parsedGradeSortType, offset, limit);
    }

    public List<Grade> getSortedGradesForOneStudent(long studentId, String gradeSortType, long offset, long limit) {
        validatePaginationParameters(offset,limit);
        GradeSortType parsedGradeSortType = parseGradeSortType(gradeSortType);
        return gradeRepository.getAllStudentGradesSorted(studentId, parsedGradeSortType, offset, limit);
    }

    public Grade addGrade(Grade grade) {
        gradeValidator.validateGrade(grade);
        Grade createdGrade = gradeRepository.addGrade(grade);
        listeners.forEach(listener -> listener.onAdd(grade));
        return createdGrade;
    }

    public boolean updateGrade(Grade updatedGrade, long oldGradeId) {
        boolean ifUpdated = gradeRepository.updateGrade(updatedGrade, oldGradeId);
        listeners.forEach(listener -> listener.onAdd(updatedGrade));
        return ifUpdated;
    }

    public boolean deleteGrade(long studentId, long gradeToBeDeletedId) {
        boolean ifDeleted = gradeRepository.deleteGrade(gradeToBeDeletedId);
        listeners.forEach(listener -> listener.onDelete(studentId));
        return ifDeleted;
    }

    public void addListener(GradeModifiedListener gradeModifiedListener) {
        listeners.add(gradeModifiedListener);
    }

    private void deleteStudentGrades(long studentId) {
        gradeRepository.deleteStudentGrades(studentId);
        listeners.forEach(listener -> listener.onDelete(studentId));
    }

    @Override
    public void onStudentDelete(long studentId) {
        deleteStudentGrades(studentId);
    }

    private GradeSortType parseGradeSortType(String gradeSortType) {
        switch (gradeSortType) {
            case "VALUE_ASC":
                return GradeSortType.VALUE_ASC;
            case "VALUE_DSC":
                return GradeSortType.VALUE_DSC;
            case "INSERTION_DATE_ASC":
                return GradeSortType.INSERTION_DATE_ASC;
            case "INSERTION_DATE_DSC":
                return GradeSortType.INSERTION_DATE_DSC;
            default:
                StringBuilder sortTypes = new StringBuilder();
                Arrays.stream(GradeSortType.values())
                        .forEach(value -> {
                            sortTypes.append(value);
                            sortTypes.append(", ");
                        });
                throw new InvalidGradeSortTypeException(",available sort types: " + sortTypes);
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