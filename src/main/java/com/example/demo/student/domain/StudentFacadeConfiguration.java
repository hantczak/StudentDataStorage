package com.example.demo.student.domain;

import com.example.demo.average.domain.StudentAverageFacade;
import com.example.demo.grade.domain.GradeFacade;
import com.example.demo.student.infrastructure.database.StudentLocalRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentFacadeConfiguration {

    @Bean
    StudentFacade studentFacade(StudentLocalRepository studentLocalRepository, StudentSortService studentSortService){
        StudentValidator studentValidator = new StudentValidator();
        StudentService studentService = new StudentService(studentLocalRepository,studentValidator);
        return new StudentFacade(studentService,studentSortService);
    }
}
