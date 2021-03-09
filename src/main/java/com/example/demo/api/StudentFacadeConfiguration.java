package com.example.demo.api;

import com.example.demo.services.StudentService;
import com.example.demo.services.StudentSortService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentFacadeConfiguration {

    @Bean
    StudentFacade studentFacade(StudentService studentService, StudentSortService studentSortService){
        return new StudentFacade(studentService,studentSortService);
    }
}
