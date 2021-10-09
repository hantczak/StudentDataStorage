package hantczak.studentDataStorage.average.infrastructure.api;

import hantczak.studentDataStorage.average.domain.InvalidStudentAverageSortTypeException;
import hantczak.studentDataStorage.average.domain.StudentAverage;
import hantczak.studentDataStorage.average.domain.StudentAverageFacade;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
public class StudentAverageController {
    private final StudentAverageFacade studentAverageFacade;

    public StudentAverageController(StudentAverageFacade studentAverageFacade) {
        this.studentAverageFacade = studentAverageFacade;
    }

    @GetMapping("/averages")
    public ResponseEntity<StudentAverageResponse> getAllAverages(@RequestParam(value = "sortType", required = false, defaultValue = "STUDENT_ID_ASC") String studentAverageSortType,
                                                                 @RequestParam(value = "offset", defaultValue = "0", required = false) long offset,
                                                                 @RequestParam(value = "limit", defaultValue = "20", required = false) long limit) {
        return ResponseEntity.ok(new StudentAverageResponse(StudentAverageMapper.StudentAverageListToStudentAverageDtoList(studentAverageFacade.getAllAveragesSorted(studentAverageSortType,offset,limit))));
    }

    @GetMapping("/students/{studentId}/average")
    public ResponseEntity<StudentAverageDto> getStudentAverageById(@PathVariable long studentId) {
        Optional<StudentAverage> averageOptional = studentAverageFacade.getStudentAverage(studentId);
        if (averageOptional.isPresent()) {
            StudentAverageDto studentAverageDto = StudentAverageMapper.toDto(averageOptional.get());
            return ResponseEntity.ok(studentAverageDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(InvalidStudentAverageSortTypeException.class)
    public ResponseEntity<String> handleInvalidStudentAverageSortTypeException(InvalidStudentAverageSortTypeException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }
}
