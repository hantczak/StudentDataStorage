package com.example.demo.average.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentAverageFacadeConfiguration {

    @Bean
    StudentAverageFacade StudentAverageFacade(StudentAverageService studentAverageService){
        return new StudentAverageFacade(studentAverageService);
    }
}
