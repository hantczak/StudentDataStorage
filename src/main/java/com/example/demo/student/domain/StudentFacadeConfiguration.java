package com.example.demo.student.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentFacadeConfiguration {

    @Bean
    StudentFacade studentFacade(StudentService studentService, StudentSortService studentSortService){
        return new StudentFacade(studentService,studentSortService);
    }
}
