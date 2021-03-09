package com.example.demo.api;

import com.example.demo.services.GradeService;
import com.example.demo.services.GradeSortService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GradeFacadeConfiguration {

    @Bean
    GradeFacade gradeFacade(GradeService gradeService, GradeSortService gradeSortService){
        return new GradeFacade(gradeService,gradeSortService);
    }
}
