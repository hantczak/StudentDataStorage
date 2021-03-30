package com.example.demo.average.infrastructure.api;

import com.example.demo.average.domain.StudentAverage;
import com.example.demo.average.domain.StudentAverageFacade;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping
public class StudentAverageController {
    private final StudentAverageFacade studentAverageFacade;

    @Lazy
    public StudentAverageController(StudentAverageFacade studentAverageFacade) {
        this.studentAverageFacade = studentAverageFacade;
    }

    @GetMapping("/averages")
    public ResponseEntity<StudentAverageResponse> getAllAverages() {
        return ResponseEntity.ok(new StudentAverageResponse(StudentAverageMapper.StudentAverageListToStudentAverageDtoList(studentAverageFacade.getAllAverages())));
    }

    @GetMapping("/averages/{studentId}")
    public ResponseEntity<StudentAverageDto> getStudentAverageById(@PathVariable long studentId) {
        Optional<StudentAverage> averageOptional= studentAverageFacade.getStudentAverage(studentId);
        if (averageOptional.isPresent()) {
            StudentAverageDto studentAverageDto = StudentAverageMapper.toDto(averageOptional.get());
            return ResponseEntity.ok(studentAverageDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
