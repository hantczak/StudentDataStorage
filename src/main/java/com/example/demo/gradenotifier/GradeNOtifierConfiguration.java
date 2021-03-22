package com.example.demo.gradenotifier;

import com.example.demo.grade.domain.Grade;
import com.example.demo.grade.domain.GradeFacade;
import com.example.demo.infrastructure.email.EmailClient;
import org.springframework.context.annotation.Bean;

public class GradeNOtifierConfiguration {

    @Bean
    GradeNotifier gradeNotifier(
            GradeFacade gradeFacade,
            EmailClient emailClient
    ) {
        GradeNotifier gradeNotifier = new GradeNotifier(emailClient);
        gradeFacade.addListener(gradeNotifier);
        return gradeNotifier;
    }
}
