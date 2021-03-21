package com.example.demo.grade.domain;

import com.example.demo.average.domain.AverageCalculator;
import com.example.demo.average.domain.StudentAverageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GradeFacadeConfiguration {

    @Bean
    GradeFacade gradeFacade(GradeService gradeService, GradeSortService gradeSortService,StudentAverageService studentAverageService){
        AverageCalculator averageCalculator = new AverageCalculator();

        return new GradeFacade(gradeService,gradeSortService,studentAverageService);
    }
}
