package hantczak.studentDataStorage.grade.infrastructure.api;

import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.grade.domain.GradeFacade;
import hantczak.studentDataStorage.grade.domain.InvalidGradeException;
import hantczak.studentDataStorage.grade.domain.InvalidGradeSortTypeException;
import hantczak.studentDataStorage.student.domain.InvalidPaginationParametersException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class GradeController {

    private final GradeFacade gradeFacade;

    public GradeController(GradeFacade gradeFacade) {
        this.gradeFacade = gradeFacade;
    }


    @GetMapping("/grades")
    public ResponseEntity<GradeResponse> getAllGrades(@RequestParam(value = "sortType", required = false, defaultValue = "INSERTION_DATE_ASC") String gradeSortType,
                                                      @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                      @RequestParam(value = "limit", required = false, defaultValue = "20") int limit) {
        List<GradeDto> gradeDtoList = GradeMapper.gradeListToGradeDtoList(gradeFacade.getAllGradesSorted(gradeSortType, offset, limit));
        return ResponseEntity.ok(new GradeResponse(gradeDtoList));
    }

    @GetMapping("/students/{studentId}/grades")
    public ResponseEntity<GradeResponse> getStudentGradesByIdPath(@PathVariable long studentId,
                                                                  @RequestParam(value = "sortType", required = false, defaultValue = "INSERTION_DATE_ASC") String gradeSortType,
                                                                  @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                                  @RequestParam(value = "limit", required = false, defaultValue = "20") int limit) {
        List<Grade> studentGradeList = gradeFacade.getSortedGradesForOneStudent(studentId, gradeSortType, offset, limit);
        if (!studentGradeList.isEmpty()) {
            List<GradeDto> gradeDtoList = GradeMapper.gradeListToGradeDtoList(studentGradeList);
            return ResponseEntity.ok(new GradeResponse(gradeDtoList));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/grades")
    public ResponseEntity<GradeDto> addGrade(@RequestBody Grade grade) {
        GradeDto savedGrade = GradeMapper.toDto(gradeFacade.addGrade(grade));
        return ResponseEntity.created(URI.create("/students/" + grade.getStudentId() + "/grades")).body(savedGrade);
    }

    @PutMapping("/grades")
    public ResponseEntity<String> updateGrade(@RequestBody Grade updatedGrade,
                                              @RequestParam(value = "gradeId") long oldGradeId) {
        boolean ifUpdated = gradeFacade.updateGrade(updatedGrade, oldGradeId);
        if (ifUpdated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/grades")
    public ResponseEntity<Boolean> deleteGrade(@RequestParam(value = "studentId") long studentId,
                                               @RequestParam(value = "gradeId") int gradeId) {
        boolean ifDeleted = gradeFacade.deleteGrade(studentId, gradeId);
        if (ifDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(InvalidGradeException.class)
    public ResponseEntity<String> handleInvalidGradeException(InvalidGradeException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidGradeSortTypeException.class)
    public ResponseEntity<String> handleInvalidGradeSortTypeException(InvalidGradeSortTypeException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidPaginationParametersException.class)
    public ResponseEntity<String> handleInvalidStudentPaginationParametersException(InvalidPaginationParametersException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }
}
