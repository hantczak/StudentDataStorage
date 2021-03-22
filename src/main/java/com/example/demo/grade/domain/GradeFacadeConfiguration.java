package com.example.demo.grade.domain;

import com.example.demo.average.domain.StudentAverageFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GradeFacadeConfiguration {

    @Bean
    GradeFacade gradeFacade(GradeService gradeService, GradeSortService gradeSortService, StudentAverageFacade studentAverageFacade){
        return new GradeFacade(gradeService,gradeSortService,studentAverageFacade);
    }
}
