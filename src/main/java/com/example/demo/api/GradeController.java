package com.example.demo.api;

import com.example.demo.dao.entity.Grade;
import com.example.demo.services.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }


    @GetMapping("/grades")
    public ResponseEntity<GradeResponse> getAllGrades() {
        return new ResponseEntity<>(new GradeResponse(gradeService.getAllGrades()), HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}/grades")
    public ResponseEntity<GradeResponse> getStudentGradesByIdPath(@PathVariable long studentId) {
        List<Grade> studentGradeList = gradeService.getStudentGrades(studentId);
        if (!studentGradeList.isEmpty()) {
            return new ResponseEntity<>(new GradeResponse(studentGradeList), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/grades/byId")
    public ResponseEntity<GradeResponse> getStudentGrades(@RequestParam long studentId) {
        List<Grade> studentGradeList = gradeService.getStudentGrades(studentId);
        if (!studentGradeList.isEmpty()) {
            return new ResponseEntity<>(new GradeResponse(studentGradeList), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/grades")
    public ResponseEntity<Boolean> addGrade(@RequestBody Grade grade) {
        gradeService.addGrade(grade);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/grades")
    public ResponseEntity<Boolean> updateGrade(@RequestBody Grade updatedGrade,
                                               @RequestParam(value = "gradeId") int oldGradeId) {
        boolean ifUpdated = gradeService.updateGrade(updatedGrade, oldGradeId);
        if (ifUpdated) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/grades")
    public ResponseEntity<Boolean> deleteGrade(@RequestParam(value = "studentId") long studentId,
                                               @RequestParam(value = "gradeId") int gradeId) {
        boolean ifDeleted = gradeService.deleteGrade(studentId,gradeId);
        if (ifDeleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
