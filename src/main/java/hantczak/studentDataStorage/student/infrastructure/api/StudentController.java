package hantczak.studentDataStorage.student.infrastructure.api;

import hantczak.studentDataStorage.grade.domain.InvalidGradeException;
import hantczak.studentDataStorage.grade.domain.InvalidGradeSortTypeException;
import hantczak.studentDataStorage.student.domain.InvalidStudentException;
import hantczak.studentDataStorage.student.domain.InvalidStudentSortTypeException;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.student.domain.StudentFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/students")

public class StudentController {

    private final StudentFacade studentFacade;

    public StudentController(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    @GetMapping("/{ID}")
    public ResponseEntity<StudentDto> getStudentByIdPath(@PathVariable(value = "ID") int studentId) {
        Optional<Student> student = studentFacade.getStudent(studentId);
        if (student.isPresent()) {
            return ResponseEntity.ok(StudentMapper.toDto(student.get()));
            //new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<StudentResponse> getAllStudents(@RequestParam(value = "sortType", required = false, defaultValue = "NAME_ASC") String studentSortType) {
        List<StudentDto> studentDtoList = StudentMapper.studentListToStudentDtoList(studentFacade.getSortedStudents(studentSortType));
        return new ResponseEntity<>(new StudentResponse(studentDtoList), HttpStatus.OK);
    }

    @GetMapping("/byId")
    public ResponseEntity<StudentDto> getStudent(@RequestParam(value = "ID") int studentId) {
        Optional<Student> student = studentFacade.getStudent(studentId);
        if (student.isPresent()) {
            return ResponseEntity.ok(StudentMapper.toDto(student.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> addStudent(@RequestBody Student student) {
        studentFacade.addStudent(student);
        return ResponseEntity.ok(true);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateStudentData(@RequestParam long studentId, @RequestBody Student student) {
        boolean ifUpdated = studentFacade.updateStudentData(studentId, student);
        if (ifUpdated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteStudentAndHisGrades(@RequestParam int studentId) {
        boolean ifDeleted = studentFacade.deleteStudentAndHisGrades(studentId);
        if (ifDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(InvalidStudentException.class)
    public ResponseEntity<String> handleInvalidStudentException(InvalidGradeException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidStudentSortTypeException.class)
    public ResponseEntity<String> handleInvalidStudentSortTypeException(InvalidStudentSortTypeException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }
}
