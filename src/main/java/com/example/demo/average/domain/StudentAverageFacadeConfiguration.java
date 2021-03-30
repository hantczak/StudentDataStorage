package com.example.demo.average.domain;

import com.example.demo.grade.domain.GradeFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentAverageFacadeConfiguration {

    @Bean
    StudentAverageFacade studentAverageFacade(GradeFacade gradeFacade,StudentAverageRepository studentAverageRepository){
        StudentAverageService studentAverageService = new StudentAverageService(studentAverageRepository,gradeFacade);
        gradeFacade.addListener(studentAverageService);
        return new StudentAverageFacade(studentAverageService);
    }
}
