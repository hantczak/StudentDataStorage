package com.example.demo.student.domain;

import com.example.demo.average.domain.StudentAverageFacade;
import com.example.demo.grade.domain.GradeFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentFacadeConfiguration {

    @Bean
    StudentFacade studentFacade(StudentService studentService, StudentSortService studentSortService, StudentAverageFacade studentAverageFacade, GradeFacade gradeFacade){
        return new StudentFacade(studentService,studentSortService,studentAverageFacade,gradeFacade);
    }
}
