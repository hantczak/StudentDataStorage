package com.example.demo.average.domain;

import com.example.demo.grade.domain.GradeFacade;
import com.example.demo.student.domain.StudentFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class StudentAverageFacadeConfiguration {

    @Bean
    StudentAverageFacade studentAverageFacade(GradeFacade gradeFacade, StudentAverageRepository studentAverageRepository, StudentFacade studentFacade){
        StudentAverageService studentAverageService = new StudentAverageService(studentAverageRepository,gradeFacade);
        gradeFacade.addListener(studentAverageService);
        studentFacade.addListener(studentAverageService);
        return new StudentAverageFacade(studentAverageService);
    }
}
