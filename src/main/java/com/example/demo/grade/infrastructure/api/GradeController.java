package com.example.demo.grade.infrastructure.api;

import com.example.demo.grade.domain.Grade;
import com.example.demo.grade.domain.GradeFacade;
import com.example.demo.grade.domain.GradeSortType;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class GradeController {

    private final GradeFacade gradeFacade;

    @Lazy
    public GradeController(GradeFacade gradeFacade) {
        this.gradeFacade = gradeFacade;
    }


    @GetMapping("/grades")
    public ResponseEntity<GradeResponse> getAllGrades(@RequestParam(value = "sortType", required = false, defaultValue = "INSERTION_DATE_ASC") String gradeSortType) {
        List<GradeDto> gradeDtoList = GradeMapper.gradeListToGradeDtoList(gradeFacade.getAllGradesSorted(gradeSortType));
        return ResponseEntity.ok(new GradeResponse(gradeDtoList));
    }

    @GetMapping("/students/{studentId}/grades")
    public ResponseEntity<GradeResponse> getStudentGradesByIdPath(@PathVariable long studentId,
                                                                  @RequestParam(value = "sortType", required = false, defaultValue = "INSERTION_DATE_ASC") String gradeSortType) {
        List<Grade> studentGradeList = gradeFacade.getSortedGradesForOneStudent(studentId, gradeSortType);
        if (!studentGradeList.isEmpty()) {
            List<GradeDto> gradeDtoList = GradeMapper.gradeListToGradeDtoList(studentGradeList);
            return ResponseEntity.ok(new GradeResponse(gradeDtoList));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/grades/byId")
    public ResponseEntity<GradeResponse> getStudentGrades(@RequestParam long studentId,
                                                          @RequestParam(value = "sortType", required = false, defaultValue = "INSERTION_DATE_ASC") String gradeSortType) {
        List<Grade> studentGradeList = gradeFacade.getSortedGradesForOneStudent(studentId,gradeSortType);
        if (!studentGradeList.isEmpty()) {
            List<GradeDto> studentGradeDtoList = GradeMapper.gradeListToGradeDtoList(studentGradeList);
            return ResponseEntity.ok(new GradeResponse(studentGradeDtoList));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/grades")
    public ResponseEntity<Boolean> addGrade(@RequestBody Grade grade) {
        gradeFacade.addGrade(grade);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/grades")
    public ResponseEntity<Boolean> updateGrade(@RequestBody Grade updatedGrade,
                                               @RequestParam(value = "gradeId") int oldGradeId) {
        boolean ifUpdated = gradeFacade.updateGrade(updatedGrade, oldGradeId);
        if (ifUpdated) {
            return ResponseEntity.ok(true);
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
}
