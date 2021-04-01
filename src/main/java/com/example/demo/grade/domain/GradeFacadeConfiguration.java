package com.example.demo.grade.domain;

import com.example.demo.grade.infrastructure.database.GradeLocalRepository;
import com.example.demo.student.domain.StudentFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GradeFacadeConfiguration {

    @Bean
    public GradeFacade gradeFacade(StudentFacade studentFacade) {
        GradeRepository gradeRepository = new GradeLocalRepository();
        GradeValidator gradeValidator = new GradeValidator(studentFacade);
        GradeService gradeService = new GradeService(gradeRepository, gradeValidator);
        GradeSortService gradeSortService = new GradeSortService(gradeRepository);
        studentFacade.addListener(gradeService);
        return new GradeFacade(gradeService, gradeSortService);
    }

    public GradeFacade gradeFacade(StudentFacade studentFacade, GradeRepository gradeRepository) {
        GradeValidator gradeValidator = new GradeValidator(studentFacade);
        GradeService gradeService = new GradeService(gradeRepository, gradeValidator);
        GradeSortService gradeSortService = new GradeSortService(gradeRepository);
        studentFacade.addListener(gradeService);
        return new GradeFacade(gradeService, gradeSortService);
    }
}
