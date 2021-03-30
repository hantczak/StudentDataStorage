package com.example.demo.grade.domain;

import com.example.demo.average.domain.StudentAverageFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GradeFacadeConfiguration {

    @Bean
    GradeFacade gradeFacade(GradeRepository gradeRepository){
        GradeService gradeService = new GradeService(gradeRepository);
        GradeSortService gradeSortService = new GradeSortService(gradeRepository);
        return new GradeFacade(gradeService,gradeSortService);
    }
}
