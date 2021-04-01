package com.example.demo.average.domain;

import com.example.demo.average.infrastructure.database.StudentAverageRepositoryInMemory;
import com.example.demo.grade.domain.GradeFacade;
import com.example.demo.student.domain.StudentFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentAverageFacadeConfiguration {

    @Bean
    public StudentAverageFacade studentAverageFacade(GradeFacade gradeFacade, StudentFacade studentFacade) {
        StudentAverageRepository studentAverageRepository = new StudentAverageRepositoryInMemory();
        StudentAverageService studentAverageService = new StudentAverageService(studentAverageRepository, gradeFacade);
        gradeFacade.addListener(studentAverageService);
        studentFacade.addListener(studentAverageService);
        return new StudentAverageFacade(studentAverageService);
    }
}
