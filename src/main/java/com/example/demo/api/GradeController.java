package com.example.demo.api;

import com.example.demo.dao.entity.Grade;
import com.example.demo.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/grades")
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }


    @GetMapping("/all")
    public ResponseEntity<GradeResponse> getAllGrades() {
        return new ResponseEntity<>(new GradeResponse(gradeService.getAllGrades()), HttpStatus.OK);
    }

    @GetMapping("/id/{studentId}")
    public ResponseEntity<GradeResponse> getStudentGradesByIdPath(@PathVariable long studentId) {
        List<Grade> studentGradeList = gradeService.getStudentGrades(studentId);
        if (!studentGradeList.isEmpty()) {
            return new ResponseEntity<>(new GradeResponse(studentGradeList), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<GradeResponse> getStudentGrades(@RequestParam long studentId) {
        List<Grade> studentGradeList = gradeService.getStudentGrades(studentId);
        if (!studentGradeList.isEmpty()) {
            return new ResponseEntity<>(new GradeResponse(studentGradeList), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Boolean> addGrade(@RequestBody Grade grade) {
        gradeService.addGrade(grade);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateGrade(@RequestBody Grade updatedGrade,
                                               @RequestParam(value = "value") int oldGradeValue,
                                               @RequestParam(value = "weight",defaultValue = "1") int oldGradeWeight) {
        boolean ifUpdated = gradeService.updateGrade(updatedGrade, oldGradeValue,oldGradeWeight);
        if (ifUpdated) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteGrade(@RequestBody Grade grade){
        boolean ifDeleted=gradeService.deleteGrade(grade);
        if(ifDeleted){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
    }
}
