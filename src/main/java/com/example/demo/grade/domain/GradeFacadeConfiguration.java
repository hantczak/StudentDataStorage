package com.example.demo.grade.domain;

import com.example.demo.student.domain.StudentFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GradeFacadeConfiguration {

    @Bean
        GradeFacade gradeFacade(GradeRepository gradeRepository, StudentFacade studentFacade){
        GradeValidator gradeValidator = new GradeValidator(studentFacade);
        GradeService gradeService = new GradeService(gradeRepository,gradeValidator);
        GradeSortService gradeSortService = new GradeSortService(gradeRepository);
        studentFacade.addListener(gradeService);
        return new GradeFacade(gradeService,gradeSortService);
    }
}
