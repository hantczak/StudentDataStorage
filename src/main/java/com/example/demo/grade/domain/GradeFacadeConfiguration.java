package com.example.demo.grade.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GradeFacadeConfiguration {

    @Bean
    GradeFacade gradeFacade(GradeService gradeService, GradeSortService gradeSortService){
        return new GradeFacade(gradeService,gradeSortService);
    }
}
